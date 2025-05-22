package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.model.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@NoArgsConstructor
@Getter
@Component
public class EntityGenerator {

    private final Map<String, Table> tableMap = new HashMap<>();

    public void generate(List<Table> tables, String outputDir, String packageName, boolean overwrite, boolean useBuilder) {
        log.info("Starting entity generation...");

        // Δημιουργία του tableMap
        Map<String, Table> tablesMap = tables.stream()
                .collect(Collectors.toMap(Table::getName, t -> t));
        log.debug("📄 Created tablesMap with keys: {}", tablesMap.keySet());

        // Ανάλυση των σχέσεων
        RelationshipResolver resolver = new RelationshipResolver(tablesMap);
        resolver.resolveRelationshipsForAllTables();
        log.info("✅ RelationshipResolver initialized and all relationships resolved for tables: {}", tablesMap.keySet());

        // Παραγωγή των entities
        for (Table table : tables) {
            log.debug("Processing table: {}", table.getName());

            String entityContent = createEntityContent(table, packageName, useBuilder);
            Path outputPath = Paths.get(outputDir, NamingConverter.toPascalCase(table.getName()) + ".java");

            if (!overwrite && outputPath.toFile().exists()) {
                log.warn("File already exists, skipping: {}", outputPath);
                continue;
            }

            try {
                writeToFile(outputPath.toString(), entityContent);
                log.info("Generated entity for table: {}", table.getName());
            } catch (IOException e) {
                log.error("Failed to write entity file for table: {}", table.getName(), e);
            }
        }
        log.info("✅ Entity generation complete. Output directory: {}", outputDir);

    }


    public String createEntityContent(Table table, String packageName, boolean useBuilder) {
        StringBuilder entityBuilder = new StringBuilder();

        generatePackageAndImports(entityBuilder, packageName, table);
        generateClassAnnotations(entityBuilder, table, useBuilder);
        generateFields(entityBuilder, table);

        entityBuilder.append("}\n");

        log.debug("Generated entity content for table '{}':\n{}", table.getName(), entityBuilder);
        return entityBuilder.toString();
    }


    public void generatePackageAndImports(StringBuilder builder, String packageName, Table table) {
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import jakarta.persistence.*;\n");
        builder.append("import lombok.*;\n");

        Set<String> imports = new TreeSet<>();
        boolean needsCreationTimestamp = false;
        boolean needsUpdateTimestamp = false;

        for (Column column : table.getColumns()) {
            String javaType = column.getJavaType();

            if (javaType.startsWith("java.")) {
                imports.add("import " + javaType + ";");
            }

            if ("UUID".equalsIgnoreCase(javaType)) {
                imports.add("import java.util.UUID;");
                imports.add("import org.hibernate.annotations.GenericGenerator;");
            }


            String columnName = column.getName().toLowerCase();
            if (columnName.equals("created_at") && javaType.equals("java.time.LocalDateTime")) {
                needsCreationTimestamp = true;
            }
            if (columnName.equals("updated_at") && javaType.equals("java.time.LocalDateTime")) {
                needsUpdateTimestamp = true;
            }
        }

        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY ||
                    relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) {
                imports.add("import java.util.List;");
                imports.add("import java.util.ArrayList;");
                break;
            }
        }

        if (needsCreationTimestamp) {
            imports.add("import org.hibernate.annotations.CreationTimestamp;");
        }
        if (needsUpdateTimestamp) {
            imports.add("import org.hibernate.annotations.UpdateTimestamp;");
        }

        for (String imp : imports) {
            builder.append(imp).append("\n");
        }

        builder.append("\n");
    }


    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");

        // ✅ Αφαιρούμε το schema (π.χ. "public.")
        String rawTableName = table.getName();
        String tableName = rawTableName.contains(".") ? rawTableName.substring(rawTableName.indexOf(".") + 1) : rawTableName;

        builder.append("@Table(name = \"").append(NamingConverter.toSnakeCase(tableName)).append("\")\n");
        builder.append("@Getter\n@Setter\n");
        builder.append("@ToString\n");

        if (useBuilder) {
            builder.append("@Builder\n");
        }
        builder.append("@NoArgsConstructor\n@AllArgsConstructor\n");

        // ✅ Μετατροπή του ονόματος της κλάσης σε PascalCase
        String className = NamingConverter.toPascalCase(tableName);
        builder.append("public class ").append(className).append(" {\n\n");
    }


    public void generateFields(StringBuilder builder, Table table) {
        Set<String> generatedFieldNames = new HashSet<>();

        for (Column column : table.getColumns()) {
            log.debug("Processing column: {}", column.getName());

            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder, column);
            } else if (column.isForeignKey()) {
                addRelationshipField(builder, column, table, generatedFieldNames); // 🔁
            } else {
                addColumnField(builder, column);
            }
        }

        for (Relationship relationship : table.getRelationships()) {
            if ((relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY ||
                    relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) &&
                    relationship.getMappedBy() != null) {

                log.debug("🔄 Adding inverse relationship field for table '{}', target '{}', type: {}, mappedBy: '{}' ",
                        relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy());

                addInverseRelationshipField(builder, relationship, generatedFieldNames); // 🔁
            }
        }

        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY
                    && relationship.getMappedBy() == null
                    && relationship.getJoinTableName() != null) {

                log.debug("🔵 Adding ManyToMany Parent Side field for table '{}', target '{}', joinTable '{}'",
                        relationship.getSourceTable(), relationship.getTargetTable(), relationship.getJoinTableName());

                addManyToManyParentSide(builder, relationship, generatedFieldNames); // 🔁
            }
        }

        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE &&
                    relationship.getMappedBy() != null &&
                    relationship.getSourceTable().equals(table.getName())) {

                String targetEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
                String fieldName = NamingConverter.toCamelCase(NamingConverter.toSnakeCase(relationship.getTargetTable()));
                String mappedBy = relationship.getMappedBy(); // αυτό θα πάει στο mappedBy

                if (generatedFieldNames.contains(fieldName)) {
                    log.warn("⚠️ Skipping inverse OneToOne field '{}': already generated", fieldName);
                    continue;
                }
                generatedFieldNames.add(fieldName);

                log.debug("🔁 Adding inverse @OneToOne for '{}', mappedBy: '{}'", targetEntity, mappedBy);

                builder.append("    @OneToOne(mappedBy = \"").append(mappedBy).append("\", fetch = FetchType.LAZY)\n");
                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }
        }

    }


    private void addPrimaryKeyAnnotations(StringBuilder builder, Column column) {
        builder.append("    @Id\n");

        String javaType = column.getJavaType();
        log.debug("🧪 [PK Generation] Column: {}, JavaType: {}", column.getName(), javaType);

        if (javaType.toLowerCase().endsWith("uuid")) {
            log.info("✅ UUID detected for primary key: {}", column.getName());

            builder.append("    @GeneratedValue(generator = \"UUID\")\n");
            builder.append("    @GenericGenerator(name = \"UUID\", strategy = \"org.hibernate.id.UUIDGenerator\")\n");
        } else if ("Long".equalsIgnoreCase(javaType) || "Integer".equalsIgnoreCase(javaType)) {
            builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
        }

        addColumnField(builder, column);
    }


    // parent  Side Table
    public void addRelationshipField(StringBuilder builder, Column column, Table table, Set<String> generatedFieldNames) {
        log.debug("🔵 Resolving relationship for column: {} in table: {}", column.getName(), table.getName());

        Optional<Relationship> relationshipOpt = table.getRelationships().stream()
                .filter(rel -> rel.getSourceTable().equals(table.getName()) &&
                        (rel.getSourceColumn() == null || rel.getSourceColumn().equals(column.getName())))
                .findFirst();

        if (relationshipOpt.isEmpty()) {
            log.warn("⚠️ Skipping relationship field for column '{}' because no relationship was found.", column.getName());
            return;
        }

        Relationship relationship = relationshipOpt.get();
        log.debug("💬 Relationship details -> Source: {}, Target: {}, Type: {}, MappedBy: {}, JoinTable: {}",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(),
                relationship.getMappedBy(), relationship.getJoinTableName());

        String targetEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
        String fieldName = NamingConverter.toCamelCase(column.getName().replaceAll("_id$", ""));

        if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY &&
                relationship.getJoinTableName() != null) {
            fieldName = NamingConverter.toCamelCasePlural(relationship.getTargetTable());
        }

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping relationship field '{}': already generated (possible duplicate)", fieldName);
            return;
        }

        generatedFieldNames.add(fieldName);

        switch (relationship.getRelationshipType()) {
            case ONETOONE -> {
                // Αν υπάρχει mappedBy, σημαίνει ότι είμαστε στην inverse (child) πλευρά — δεν γράφουμε τίποτα εδώ
                if (relationship.getMappedBy() != null) {
                    log.debug("⏭️ Skipping ONETOONE parent field '{}' because it's inverse side (mappedBy = '{}')",
                            fieldName, relationship.getMappedBy());
                    return;
                }

                builder.append("    @OneToOne(fetch = FetchType.LAZY)\n");
                builder.append("    @JoinColumn(name = \"").append(column.getName())
                        .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\"");

                if (column.isUnique()) {
                    builder.append(", unique = true");
                }

                addOnDeleteAndOnUpdate(builder, relationship);
                builder.append(")\n");

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }


            case MANYTOONE -> {
                builder.append("    @ManyToOne(fetch = FetchType.LAZY)\n");
                builder.append("    @JoinColumn(name = \"").append(column.getName())
                        .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\"");
                addOnDeleteAndOnUpdate(builder, relationship);
                builder.append(")\n");

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }

            case MANYTOMANY -> {
                if (relationship.getJoinTableName() != null) {
                    builder.append("    @ManyToMany(fetch = FetchType.LAZY)\n");
                    builder.append("    @JoinTable(\n")
                            .append("        name = \"").append(relationship.getJoinTableName()).append("\",\n")
                            .append("        joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn()).append("\"),\n")
                            .append("        inverseJoinColumns = @JoinColumn(name = \"").append(relationship.getInverseJoinColumn()).append("\")\n")
                            .append("    )\n");
                    builder.append("    private List<").append(targetEntity).append("> ").append(fieldName).append(" = new ArrayList<>();\n\n");
                }
            }

            default ->
                    log.warn("⚠️ Relationship type {} is not handled here for column: {}", relationship.getRelationshipType(), column.getName());
        }
    }


    public void addManyToManyParentSide(StringBuilder builder, Relationship relationship, Set<String> generatedFieldNames) {
        String targetEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
        String fieldName = NamingConverter.toCamelCasePlural(relationship.getTargetTable());

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping ManyToMany parent field '{}': already generated (likely duplicate)", fieldName);
            return;
        }
        generatedFieldNames.add(fieldName);

        builder.append("    @ManyToMany(fetch = FetchType.LAZY)\n")
                .append("    @JoinTable(\n")
                .append("        name = \"").append(relationship.getJoinTableName()).append("\",\n")
                .append("        joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn()).append("\"),\n")
                .append("        inverseJoinColumns = @JoinColumn(name = \"").append(relationship.getInverseJoinColumn()).append("\")\n")
                .append("    )\n")
                .append("    private List<").append(targetEntity).append("> ").append(fieldName).append(" = new ArrayList<>();\n\n");
    }


    // Child side Table
    public void addInverseRelationshipField(StringBuilder builder, Relationship relationship, Set<String> generatedFieldNames) {
        String sourceEntity = NamingConverter.toPascalCase(relationship.getTargetTable());
        String fieldName;

        if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) {
            fieldName = relationship.getMappedBy() != null
                    ? relationship.getMappedBy()
                    : NamingConverter.toCamelCasePlural(relationship.getTargetTable());
        } else {
            fieldName = NamingConverter.toCamelCasePlural(relationship.getTargetTable());
        }

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping inverse field '{}': already generated (probably duplicate relationship)", fieldName);
            return;
        }

        generatedFieldNames.add(fieldName);

        log.debug("🔄 Creating inverse relationship field for table '{}' -> '{}', type: {}, mappedBy: '{}' FieldName: '{}'",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy(), fieldName);

        switch (relationship.getRelationshipType()) {
            case ONETOMANY -> {
                builder.append("    @OneToMany(mappedBy = \"")
                        .append(relationship.getMappedBy())
                        .append("\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)\n");
                builder.append("    private List<").append(sourceEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
            }
            case MANYTOMANY -> {
                builder.append("    @ManyToMany(mappedBy = \"").append(relationship.getMappedBy())
                        .append("\", fetch = FetchType.LAZY)\n");
                builder.append("    private List<").append(sourceEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
            }
            default ->
                    log.warn("⚠️ Relationship type {} is not handled here for inverse relationships.", relationship.getRelationshipType());
        }
    }


    private void addOnDeleteAndOnUpdate(StringBuilder builder, Relationship relationship) {
        if (relationship.getOnDelete() != null || relationship.getOnUpdate() != null) {
            builder.append(", foreignKey = @ForeignKey(name = \"fk_").append(relationship.getSourceColumn())
                    .append("_").append(relationship.getTargetColumn()).append("\", value = ConstraintMode.CONSTRAINT)");
        }
    }


    private void addColumnField(StringBuilder builder, Column column) {
        boolean isForeignKey = column.isForeignKey();
        String columnName = column.getName().toLowerCase();
        String javaType = column.getJavaType();

        if (columnName.equals("created_at") && javaType.equals("java.time.LocalDateTime")) {
            builder.append("    @CreationTimestamp\n");
        } else if (columnName.equals("updated_at") && javaType.equals("java.time.LocalDateTime")) {
            builder.append("    @UpdateTimestamp\n");
        }

        if (!isForeignKey) {
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

            if (columnName.equals("created_at")) {
                builder.append(", updatable = false");
            }

            builder.append(")");
        }

        String cleanedType = javaType;
        if (javaType.startsWith("java.time.")) {
            cleanedType = javaType.substring("java.time.".length());
        } else if (javaType.startsWith("java.math.")) {
            cleanedType = javaType.substring("java.math.".length());
        } else if (javaType.startsWith("java.util.")) {
            cleanedType = javaType.substring("java.util.".length());
        }

        builder.append("\n    private ").append(cleanedType).append(" ")
                .append(NamingConverter.toCamelCase(column.getName()));

        if (cleanedType.equals("Boolean") && column.getDefaultValue() != null) {
            String defVal = column.getDefaultValue().trim().toLowerCase();

            if (defVal.equals("true") || defVal.equals("1")) {
                builder.append(" = true");
            } else if (defVal.equals("false") || defVal.equals("0")) {
                builder.append(" = false");
            } else {
                log.warn("⚠️ Boolean column '{}' έχει μη αναγνωρίσιμη default τιμή: {}", column.getName(), defVal);
            }
        }

        builder.append(";\n\n");
    }


    public void writeToFile(String filePath, String content) throws IOException {
        Objects.requireNonNull(filePath, "File path cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);

        log.debug("File written successfully: {}", filePath);
    }
}