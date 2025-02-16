package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.model.Column;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import com.sqldomaingen.model.Relationship.RelationshipType;


@NoArgsConstructor
@Component
public class EntityGenerator {

    private static final Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

    public void generate(List<Table> tables, String outputDir, String packageName, boolean overwrite, boolean useBuilder) {
        logger.info("Starting entity generation...");

        Map<String, Table> tablesMap = tables.stream()
                .collect(Collectors.toMap(Table::getName, t -> t));
        logger.debug("📄 Created tablesMap with keys: {}", tablesMap.keySet());

        RelationshipResolver resolver = new RelationshipResolver(tablesMap);
        resolver.resolveRelationshipsForAllTables();
        logger.info("✅ RelationshipResolver initialized and all relationships resolved for tables: {}", tablesMap.keySet());

        for (Table table : tables) {
            logger.debug("Processing table: {}", table.getName());

            String entityContent = createEntityContent(table, packageName, useBuilder);
            logger.debug("📄 Generated entity content for table '{}':\n{}", table.getName(), entityContent);
            Path outputPath = Paths.get(outputDir, NamingConverter.toPascalCase(table.getName()) + ".java");
            String fileName = outputPath.toString();
            logger.debug("📂 Output path for table '{}': {}", table.getName(), fileName);

            if (!overwrite && outputPath.toFile().exists()) {
                logger.warn("File already exists, skipping: {}", fileName);
                continue;
            }
            try {
                writeToFile(fileName, entityContent);
                logger.info("Generated entity for table: {}", table.getName());
            } catch (IOException e) {
                logger.error("Failed to write entity file for table: {}", table.getName(), e);
            }
        }
        logger.info("Entity generation complete. Output directory: {}", outputDir);
    }



    public String createEntityContent(Table table, String packageName, boolean useBuilder) {
        StringBuilder entityBuilder = new StringBuilder();

        generatePackageAndImports(entityBuilder, packageName, table);
        generateClassAnnotations(entityBuilder, table, useBuilder);
        generateFields(entityBuilder, table);

        entityBuilder.append("}\n");

        logger.debug("Generated entity content for table '{}':\n{}", table.getName(), entityBuilder);
        return entityBuilder.toString();
    }



    public void generatePackageAndImports(StringBuilder builder, String packageName, Table table) {
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import jakarta.persistence.*;\n");
        builder.append("import lombok.*;\n");

        boolean hasBigDecimal = false;
        boolean hasLocalDate = false;
        boolean hasLocalDateTime = false;
        boolean hasList = false;

        for (Column column : table.getColumns()) {
            if (column.getJavaType().equals("java.math.BigDecimal")) hasBigDecimal = true;
            if (column.getJavaType().equals("java.time.LocalDate")) hasLocalDate = true;
            if (column.getJavaType().equals("java.time.LocalDateTime")) hasLocalDateTime = true;
        }

        // Έλεγχος για σχέσεις OneToMany ή ManyToMany
        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY ||
                    relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) {
                hasList = true;
                break;
            }
        }

        if (hasBigDecimal) builder.append("import java.math.BigDecimal;\n");
        if (hasLocalDate) builder.append("import java.time.LocalDate;\n");
        if (hasLocalDateTime) builder.append("import java.time.LocalDateTime;\n");
        if (hasList) builder.append("import java.util.List;\nimport java.util.ArrayList;\n");

        builder.append("\n");
    }




    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");
        builder.append("@Table(name = \"").append(NamingConverter.toSnakeCase(table.getName())).append("\")\n");
        builder.append("@Getter\n@Setter\n");
        builder.append("@ToString\n");

        if (useBuilder) {
            builder.append("@Builder\n");
        }
        builder.append("@NoArgsConstructor\n@AllArgsConstructor\n");
        builder.append("public class ").append(NamingConverter.toPascalCase(table.getName())).append(" {\n\n");
    }

    public void generateFields(StringBuilder builder, Table table) {
        for (Column column : table.getColumns()) {
            logger.debug("Processing column: {}", column.getName());

            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder, column);
            } else if (column.isForeignKey()) {
                addRelationshipField(builder, column, table);
            } else {
                addColumnField(builder, column);
            }
        }

        // Προσθήκη μόνο των inverse relationships από το ίδιο το table
        for (Relationship relationship : table.getRelationships()) {
            if ((relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY ||
                    relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) &&
                    relationship.getMappedBy() != null) {

                logger.debug("🔄 Adding inverse relationship field for table '{}', target '{}', type: {}, mappedBy: '{}'",
                        relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy());

                addInverseRelationshipField(builder, relationship);
            }
        }
    }

    private void addPrimaryKeyAnnotations(StringBuilder builder, Column column) {
        builder.append("    @Id\n");
        builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");

        // Χρησιμοποιούμε την κοινή μέθοδο addColumnField
        addColumnField(builder, column);
    }

    public void addRelationshipField(StringBuilder builder, Column column, Table table) {
        logger.debug("🔵 Resolving relationship for column: {} in table: {}", column.getName(), table.getName());

        Optional<Relationship> relationshipOpt = table.getRelationships().stream()
                .filter(rel -> rel.getSourceTable().equals(table.getName()) && rel.getSourceColumn().equals(column.getName()))
                .findFirst();

        if (relationshipOpt.isEmpty()) {
            logger.warn("⚠️ Skipping relationship field for column '{}' because no relationship was found.", column.getName());
            return;
        }

        Relationship relationship = relationshipOpt.get();
        logger.debug("💬 Relationship details -> Source: {}, Target: {}, Type: {}, MappedBy: {}",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy());

        String targetEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
        String fieldName = relationship.getMappedBy() != null ? relationship.getMappedBy() : NamingConverter.toCamelCase(column.getName());

        switch (relationship.getRelationshipType()) {
            case ONETOONE:
                builder.append("    @OneToOne(fetch = FetchType.LAZY");
                if (relationship.getMappedBy() != null) {
                    builder.append(", mappedBy = \"").append(relationship.getMappedBy()).append("\"");
                }
                builder.append(")\n");

                if (relationship.getMappedBy() == null) {
                    builder.append("    @JoinColumn(name = \"").append(column.getName())
                            .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\"");

                    if (column.isUnique()) {
                        builder.append(", unique = true");
                    }
                    addOnDeleteAndOnUpdate(builder, relationship);
                    builder.append(")\n");
                }

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
                break;

            case MANYTOONE:
                builder.append("    @ManyToOne(fetch = FetchType.LAZY)\n");
                builder.append("    @JoinColumn(name = \"").append(column.getName())
                        .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\"");
                addOnDeleteAndOnUpdate(builder, relationship);
                builder.append(")\n");

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
                break;

            default:
                logger.warn("⚠️ Relationship type {} is not handled here for column: {}", relationship.getRelationshipType(), column.getName());
        }
    }



    public void addInverseRelationshipField(StringBuilder builder, Relationship relationship) {
        String targetEntity;
        String fieldName;

        if (relationship.getRelationshipType() == RelationshipType.ONETOMANY) {
            // Για OneToMany θέλουμε το sourceTable γιατί είναι το "παιδί"
            targetEntity = NamingConverter.toPascalCase(relationship.getSourceTable());
            fieldName = NamingConverter.toCamelCasePlural(relationship.getSourceTable());
        } else {
            // Για ManyToMany κρατάμε το targetTable
            targetEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
            fieldName = NamingConverter.toCamelCasePlural(relationship.getTargetTable());
        }

        logger.debug("🔄 Creating inverse relationship field for table '{}' -> '{}', type: {}, mappedBy: '{}'",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy());

        switch (relationship.getRelationshipType()) {
            case ONETOMANY:
                builder.append("    @OneToMany(mappedBy = \"")
                        .append(relationship.getMappedBy())
                        .append("\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)\n");
                builder.append("    private List<").append(targetEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
                break;

            case MANYTOMANY:
                builder.append("    @ManyToMany");
                if (relationship.getMappedBy() != null) {
                    builder.append("(mappedBy = \"").append(relationship.getMappedBy()).append("\", fetch = FetchType.LAZY)");
                } else {
                    builder.append("(fetch = FetchType.LAZY)");
                }
                builder.append("\n");

                if (relationship.getMappedBy() == null) {
                    builder.append("    @JoinTable(name = \"").append(relationship.getJoinTableName())
                            .append("\", joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn()).append("\"), ")
                            .append("inverseJoinColumns = @JoinColumn(name = \"").append(relationship.getInverseJoinColumn()).append("\"))\n");
                }

                builder.append("    private List<").append(targetEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
                break;

            default:
                logger.warn("⚠️ Relationship type {} is not handled here for inverse relationships.", relationship.getRelationshipType());
        }
    }


    private void addOnDeleteAndOnUpdate(StringBuilder builder, Relationship relationship) {
        if (relationship.getOnDelete() != null || relationship.getOnUpdate() != null) {
            builder.append(", foreignKey = @ForeignKey(name = \"fk_").append(relationship.getSourceColumn())
                    .append("_").append(relationship.getTargetColumn()).append("\", value = ConstraintMode.CONSTRAINT)");
        }
    }


    private void addColumnField(StringBuilder builder, Column column) {

        if (!column.isForeignKey()) {
            builder.append("    @Column(name = \"").append(column.getName()).append("\"");
        }

        if (column.getSqlType().startsWith("VARCHAR") || column.getSqlType().startsWith("CHAR")) {
            builder.append(", length = ").append(column.getLength());
        }

        if (column.getSqlType().startsWith("DECIMAL") || column.getSqlType().startsWith("NUMERIC")) {
            builder.append(", precision = ").append(column.getPrecision())
                    .append(", scale = ").append(column.getScale());
        }

        if (column.isUnique()) {
            builder.append(", unique = true");
        }

        if (!column.isNullable()) {
            builder.append(", nullable = false");
        }

        if (column.getDefaultValue() != null) {
            builder.append(", columnDefinition = \"DEFAULT '").append(column.getDefaultValue()).append("'\"");
        }

        if (!column.isForeignKey()) {
            builder.append(")");
        }

        builder.append("\n    private ").append(column.getJavaType()).append(" ")
                .append(NamingConverter.toCamelCase(column.getName())).append(";\n\n");
    }


    public void writeToFile(String filePath, String content) throws IOException {
        Objects.requireNonNull(filePath, "File path cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));

        logger.debug("File written successfully: {}", filePath);
    }
}