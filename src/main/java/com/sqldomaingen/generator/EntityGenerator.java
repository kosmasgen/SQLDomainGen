package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.sqldomaingen.model.Column;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


@NoArgsConstructor
@Component
public class EntityGenerator {

    private static final Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

    public void generate(List<Table> tables, String outputDir, String packageName, boolean overwrite, boolean useBuilder) {
        logger.info("Starting entity generation...");

        for (Table table : tables) {
            logger.debug("Processing table: {}", table.getName());

            String entityContent = createEntityContent(table, packageName, useBuilder);
            Path outputPath = Paths.get(outputDir, NamingConverter.toPascalCase(table.getName()) + ".java");
            String fileName = outputPath.toString();

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

        for (Column column : table.getColumns()) {
            if (column.getJavaType().equals("java.math.BigDecimal")) hasBigDecimal = true;
            if (column.getJavaType().equals("java.time.LocalDate")) hasLocalDate = true;
            if (column.getJavaType().equals("java.time.LocalDateTime")) hasLocalDateTime = true;
        }

        if (hasBigDecimal) builder.append("import java.math.BigDecimal;\n");
        if (hasLocalDate) builder.append("import java.time.LocalDate;\n");
        if (hasLocalDateTime) builder.append("import java.time.LocalDateTime;\n");

        builder.append("\n");
    }

    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");
        builder.append("@Table(name = \"").append(table.getName()).append("\")\n");
        builder.append("@Getter\n@Setter\n");
        builder.append("@ToString\n");

        if (useBuilder) {
            builder.append("@Builder\n");
        }
        builder.append("@NoArgsConstructor\n@AllArgsConstructor\n");
        builder.append("public class ").append(NamingConverter.toPascalCase(table.getName())).append(" {\n\n");
    }

    public void generateFields(StringBuilder builder, Table table) {
        RelationshipResolver resolver = new RelationshipResolver();

        table.getColumns().forEach(column -> {
            logger.debug("Processing column: {}", column.getName());

            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder);
            }

            if (column.isForeignKey()) {
                addForeignKeyAnnotations(builder, column, table, resolver, Map.of());
            }

            addColumnField(builder, column);
        });
    }

    private void addPrimaryKeyAnnotations(StringBuilder builder) {
        builder.append("    @Id\n");
        builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
    }

    private void addForeignKeyAnnotations(StringBuilder builder, Column column, Table table, RelationshipResolver resolver, Map<String, Table> allTables) {
        Relationship relationship = resolver.createRelationship(column, table, allTables);

        if (relationship == null) {
            logger.warn("Skipping foreign key annotation for column '{}' because no relationship was found.", column.getName());
            return;
        }

        switch (relationship.getRelationshipType()) {
            case ONETOONE:
                builder.append("    @OneToOne\n");
                break;
            case MANYTOONE:
                builder.append("    @ManyToOne\n");
                break;
            case ONETOMANY:
                builder.append("    @OneToMany(mappedBy = \"").append(relationship.getSourceColumn()).append("\")\n");
                return;
            case MANYTOMANY:
                builder.append("    @ManyToMany\n");
                builder.append("    @JoinTable(name = \"").append(relationship.getJoinTableName())
                        .append("\", joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn())
                        .append("\"), inverseJoinColumns = @JoinColumn(name = \"").append(relationship.getInverseJoinColumn())
                        .append("\"))\n");
                return;
        }

        builder.append("    @JoinColumn(name = \"").append(relationship.getSourceColumn())
                .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\"");
        addOnDeleteAndOnUpdate(builder, relationship);
        builder.append(")\n");

        if (relationship.getOnDelete() != null) {
            builder.append("    @OnDelete(action = OnDeleteAction.").append(relationship.getOnDelete()).append(")\n");
        }
    }

    private void addOnDeleteAndOnUpdate(StringBuilder builder, Relationship relationship) {
        if (relationship.getOnDelete() != null || relationship.getOnUpdate() != null) {
            builder.append(", foreignKey = @ForeignKey(name = \"fk_").append(relationship.getSourceColumn())
                    .append("_").append(relationship.getTargetColumn()).append("\", value = ConstraintMode.CONSTRAINT)");
        }
    }

    private void addColumnField(StringBuilder builder, Column column) {
        builder.append("    @Column(name = \"").append(column.getName()).append("\"");

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

        if (column.getCheckConstraint() != null) {
            builder.append(", columnDefinition = \"CHECK (").append(column.getCheckConstraint()).append(")\"");
        }

        builder.append(")\n");
        builder.append("    private ").append(column.getJavaType()).append(" ")
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
