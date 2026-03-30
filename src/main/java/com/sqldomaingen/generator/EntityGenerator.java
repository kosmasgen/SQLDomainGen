package com.sqldomaingen.generator;

import com.sqldomaingen.model.*;
import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import com.sqldomaingen.util.TypeMapper;
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

    /**
     * Generates entity classes for all non-pure-join tables.
     * <p>
     * Pure join tables are skipped because they are represented as synthetic
     * many-to-many metadata on their parent entities.
     *
     * @param tables the parsed tables
     * @param outputDir the root output directory
     * @param basePackage the base package name
     * @param overwrite whether existing files should be overwritten
     * @param useBuilder whether Lombok builder should be generated
     */
    public void generate(List<Table> tables,
                         String outputDir,
                         String basePackage,
                         boolean overwrite,
                         boolean useBuilder) {

        log.info("Starting entity generation...");

        if (tables == null || tables.isEmpty()) {
            log.warn("No tables provided for entity generation.");
            return;
        }

        Path entityDir = PackageResolver.resolvePath(outputDir, basePackage, "entity");

        try {
            Files.createDirectories(entityDir);
        } catch (IOException exception) {
            log.error("Failed to create entity output directory: {}", entityDir, exception);
            return;
        }

        Map<String, Table> tablesMap = tables.stream()
                .collect(Collectors.toMap(Table::getName, table -> table, (existing, replacement) -> existing, LinkedHashMap::new));

        this.tableMap.clear();
        this.tableMap.putAll(tablesMap);

        log.debug("Created tablesMap with keys: {}", tablesMap.keySet());

        for (Table table : this.tableMap.values()) {
            table.setRelationships(new ArrayList<>());
            table.setManyToManyRelations(new ArrayList<>());
            table.setPureJoinTable(false);
        }

        RelationshipResolver resolver = new RelationshipResolver(this.tableMap);
        resolver.resolveRelationshipsForAllTables();
        log.info("RelationshipResolver initialized and relationships resolved.");

        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        for (Table originalTable : tables) {
            Table resolvedTable = this.tableMap.getOrDefault(originalTable.getName(), originalTable);

            log.debug("Processing table: {}", resolvedTable.getName());

            if (resolvedTable.isPureJoinTable()) {
                log.info("Skipping pure join table entity generation for table: {}", resolvedTable.getName());
                continue;
            }

            String rawTableName = resolvedTable.getName();
            String tableName = rawTableName != null && rawTableName.contains(".")
                    ? rawTableName.substring(rawTableName.indexOf('.') + 1)
                    : rawTableName;

            String entityName = NamingConverter.toPascalCase(tableName);

            String entityContent = createEntityContent(resolvedTable, entityPackage, useBuilder);
            Path entityOutputPath = entityDir.resolve(entityName + ".java");

            if (!overwrite && Files.exists(entityOutputPath)) {
                log.warn("Entity file already exists, skipping: {}", entityOutputPath);
            } else {
                try {
                    writeToFile(entityOutputPath.toString(), entityContent);
                    log.info("Generated entity for table: {}", resolvedTable.getName());
                } catch (IOException exception) {
                    log.error("Failed to write entity file for table: {}", resolvedTable.getName(), exception);
                }
            }

            if (hasCompositePrimaryKey(resolvedTable)) {
                String pkClassName = getEmbeddedIdTypeName(resolvedTable);
                String pkContent = createEmbeddedIdClassContent(resolvedTable, entityPackage);
                Path pkOutputPath = entityDir.resolve(pkClassName + ".java");

                if (!overwrite && Files.exists(pkOutputPath)) {
                    log.warn("PK file already exists, skipping: {}", pkOutputPath);
                } else {
                    try {
                        writeToFile(pkOutputPath.toString(), pkContent);
                        log.info("Generated composite PK class '{}' for table: {}", pkClassName, resolvedTable.getName());
                    } catch (IOException exception) {
                        log.error("Failed to write composite PK file '{}' for table: {}", pkClassName, resolvedTable.getName(), exception);
                    }
                }
            }
        }

        log.info("Entity generation complete. Output directory: {}", entityDir.toAbsolutePath());
    }



    /**
     * Creates the embedded id class content for a table with a composite primary key.
     *
     * @param table the source table
     * @param packageName the target package name
     * @return the generated embedded id class content
     */
    private String createEmbeddedIdClassContent(Table table, String packageName) {
        StringBuilder builder = new StringBuilder();

        String pkClassName = getEmbeddedIdTypeName(table);
        List<Column> pkColumns = getPrimaryKeyColumns(table);

        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import jakarta.persistence.*;\n");
        builder.append("import lombok.*;\n");
        builder.append("import java.io.Serializable;\n");

        Set<String> imports = new TreeSet<>();
        for (Column column : pkColumns) {
            if (column == null) {
                continue;
            }

            String javaType = resolveJavaType(column);
            if (javaType == null || javaType.isBlank()) {
                continue;
            }

            if (javaType.startsWith("java.time.")) {
                imports.add("import " + javaType + ";");
            } else if (javaType.startsWith("java.math.")) {
                imports.add("import " + javaType + ";");
            } else if (javaType.startsWith("java.util.") && !javaType.equals("java.util.List")) {
                imports.add("import " + javaType + ";");
            }
        }

        if (!imports.isEmpty()) {
            for (String currentImport : imports) {
                builder.append(currentImport).append("\n");
            }
        }

        builder.append("\n");
        builder.append("@Embeddable\n");
        builder.append("@Getter\n");
        builder.append("@Setter\n");
        builder.append("@NoArgsConstructor\n");
        builder.append("@AllArgsConstructor\n");
        builder.append("@EqualsAndHashCode\n");
        builder.append("public class ").append(pkClassName).append(" implements Serializable {\n\n");

        for (Column column : pkColumns) {
            if (column == null) {
                continue;
            }

            String columnName = GeneratorSupport.unquoteIdentifier(column.getName());

            builder.append("    @Column(name = \"").append(columnName).append("\"");
            if (!column.isNullable()) {
                builder.append(", nullable = false");
            }
            builder.append(")\n");

            builder.append("    private ")
                    .append(toSimpleJavaType(resolveJavaType(column)))
                    .append(" ")
                    .append(NamingConverter.toCamelCase(columnName))
                    .append(";\n\n");
        }

        builder.append("}\n");

        return builder.toString();
    }


    public String createEntityContent(Table table, String packageName, boolean useBuilder) {
        StringBuilder entityBuilder = new StringBuilder();

        generatePackageAndImports(entityBuilder, packageName, table);
        generateClassAnnotations(entityBuilder, table, useBuilder);
        generateFields(entityBuilder, table, useBuilder);

        entityBuilder.append("}\n");

        log.debug("Generated entity content for table '{}':\n{}", table.getName(), entityBuilder);
        return entityBuilder.toString();
    }



    /**
     * Generates the package declaration and required imports for an entity class.
     *
     * @param builder the string builder receiving the generated content
     * @param packageName the target package name
     * @param table the source table metadata
     */
    public void generatePackageAndImports(StringBuilder builder, String packageName, Table table) {
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import jakarta.persistence.*;\n");
        builder.append("import lombok.*;\n");

        Set<String> imports = new TreeSet<>();

        boolean needsCreationTimestamp = false;
        boolean needsUpdateTimestamp = false;
        boolean needsUuidImport = false;
        boolean needsJsonImports = false;

        boolean compositePrimaryKey = hasCompositePrimaryKey(table);

        imports.add("import org.hibernate.envers.Audited;");

        for (Column column : table.getColumns()) {
            if (column == null) {
                continue;
            }

            if (compositePrimaryKey && column.isPrimaryKey()) {
                continue;
            }

            String javaType = resolveJavaType(column);

            if (javaType != null && !javaType.isBlank() && javaType.startsWith("java.")) {
                imports.add("import " + javaType + ";");
            }

            if (javaType != null && !javaType.isBlank() && isUuidType(javaType)) {
                needsUuidImport = true;
            }

            if (shouldUseCreationTimestamp(column)) {
                needsCreationTimestamp = true;
            }

            if (shouldUseUpdateTimestamp(column)) {
                needsUpdateTimestamp = true;
            }

            if (TypeMapper.isJsonType(column)) {
                needsJsonImports = true;
            }
        }

        boolean needsCollectionImports = table.getRelationships().stream()
                .anyMatch(relationship ->
                        relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY
                                || relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY);

        if (!needsCollectionImports) {
            needsCollectionImports = hasSyntheticManyToManyRelations(table);
        }

        if (needsCollectionImports) {
            imports.add("import java.util.List;");
            imports.add("import java.util.ArrayList;");
        }

        if (needsUuidImport) {
            imports.add("import java.util.UUID;");
        }

        if (needsCreationTimestamp) {
            imports.add("import org.hibernate.annotations.CreationTimestamp;");
        }

        if (needsUpdateTimestamp) {
            imports.add("import org.hibernate.annotations.UpdateTimestamp;");
        }

        if (needsJsonImports) {
            imports.add("import org.hibernate.annotations.JdbcTypeCode;");
            imports.add("import org.hibernate.type.SqlTypes;");
        }

        for (String currentImport : imports) {
            builder.append(currentImport).append("\n");
        }

        builder.append("\n");
    }


    private boolean isUuidType(String javaType) {
        String t = javaType.trim();
        return t.equalsIgnoreCase("UUID") || t.equals("java.util.UUID") || t.endsWith(".UUID");
    }


    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");
        builder.append("@Audited\n");

        String rawTableName = table.getName();
        int lastDot = rawTableName.lastIndexOf('.');
        String tableName = (lastDot >= 0) ? rawTableName.substring(lastDot + 1) : rawTableName;

        builder.append("@Table(name = \"")
                .append(NamingConverter.toSnakeCase(tableName))
                .append("\"");

        List<UniqueConstraint> uniqueConstraints = table.getUniqueConstraints();

        if (uniqueConstraints != null && !uniqueConstraints.isEmpty()) {
            List<UniqueConstraint> composite = uniqueConstraints.stream()
                    .filter(uc -> uc.getColumns() != null && uc.getColumns().size() > 1)
                    .toList();

            if (!composite.isEmpty()) {
                builder.append(", uniqueConstraints = ");

                if (composite.size() == 1) {
                    appendUniqueConstraint(builder, composite.get(0));
                } else {
                    builder.append("{");

                    for (int i = 0; i < composite.size(); i++) {
                        if (i > 0) builder.append(", ");
                        appendUniqueConstraint(builder, composite.get(i));
                    }

                    builder.append("}");
                }
            }
        }

        builder.append(")\n");
        builder.append("@Getter\n");
        builder.append("@Setter\n");

        if (useBuilder) {
            builder.append("@Builder\n");
        }

        builder.append("@NoArgsConstructor\n");
        builder.append("@AllArgsConstructor\n");

        String className = NamingConverter.toPascalCase(tableName);
        builder.append("public class ").append(className).append(" {\n\n");
    }

    private void appendUniqueConstraint(StringBuilder builder, UniqueConstraint uc) {
        builder.append("@UniqueConstraint(columnNames = {");

        List<String> columns = uc.getColumns();

        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) builder.append(", ");

            builder.append("\"")
                    .append(GeneratorSupport.unquoteIdentifier(columns.get(i)))
                    .append("\"");
        }

        builder.append("})");
    }



    /**
     * Generates all entity fields including:
     * <ul>
     *     <li>basic columns</li>
     *     <li>primary keys</li>
     *     <li>standard FK relationships</li>
     *     <li>inverse collections</li>
     *     <li>synthetic many-to-many fields from pure join tables</li>
     * </ul>
     *
     * @param builder the builder receiving field content
     * @param table the source table metadata
     * @param useBuilder true when Lombok builder is enabled for the entity
     */
    public void generateFields(StringBuilder builder, Table table, boolean useBuilder) {
        Set<String> generatedFieldNames = new HashSet<>();

        boolean compositePrimaryKey = hasCompositePrimaryKey(table);
        List<Column> primaryKeyColumns = compositePrimaryKey
                ? getPrimaryKeyColumns(table)
                : Collections.emptyList();

        boolean compositeJoinTable = compositePrimaryKey && isCompositeKeyJoinTable(table);

        if (compositePrimaryKey) {
            String embeddedIdTypeName = getEmbeddedIdTypeName(table);

            builder.append("    @EmbeddedId\n");
            builder.append("    private ").append(embeddedIdTypeName).append(" id;\n\n");
        }

        for (Column column : table.getColumns()) {
            log.debug("Processing column: {}", column.getName());

            if (compositePrimaryKey && isCompositeKeyColumn(column, primaryKeyColumns)) {
                if (compositeJoinTable) {
                    addCompositeKeyRelationshipField(builder, column, table, generatedFieldNames);
                }
                continue;
            }

            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder, column, useBuilder);
                continue;
            }

            boolean hasResolvedRelationship = findRelationshipForColumn(table, column).isPresent();

            if (column.isForeignKey() || hasResolvedRelationship) {
                addRelationshipField(builder, column, table, generatedFieldNames);
                continue;
            }

            addColumnField(builder, column, useBuilder);
        }

        if (!compositeJoinTable) {

            for (Relationship relationship : table.getRelationships()) {
                boolean isInverseCollectionSide =
                        (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY
                                || relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY)
                                && relationship.getMappedBy() != null;

                if (!isInverseCollectionSide) {
                    continue;
                }

                log.debug("Adding inverse relationship field. sourceTable='{}', target='{}', type={}, mappedBy='{}'",
                        relationship.getSourceTable(),
                        relationship.getTargetTable(),
                        relationship.getRelationshipType(),
                        relationship.getMappedBy());

                addInverseRelationshipField(builder, relationship, generatedFieldNames, useBuilder);
            }

            for (Relationship relationship : table.getRelationships()) {
                boolean isManyToManyOwnerSide =
                        relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY
                                && relationship.getMappedBy() == null
                                && relationship.getJoinTableName() != null;

                if (!isManyToManyOwnerSide) {
                    continue;
                }

                log.debug("Adding ManyToMany owning-side field. sourceTable='{}', target='{}', joinTable='{}'",
                        relationship.getSourceTable(),
                        relationship.getTargetTable(),
                        relationship.getJoinTableName());

                String expectedFieldName = NamingConverter.toCamelCasePlural(relationship.getTargetTable());
                if (generatedFieldNames.contains(expectedFieldName)) {
                    log.warn("Skipping ManyToMany owning-side field '{}': already generated", expectedFieldName);
                    continue;
                }

                addManyToManyParentSide(builder, relationship, generatedFieldNames, useBuilder);
            }

            for (ManyToManyRelation manyToManyRelation : table.getManyToManyRelations()) {
                addSyntheticManyToManyField(builder, manyToManyRelation, generatedFieldNames, useBuilder);
            }

            for (Relationship relationship : table.getRelationships()) {
                boolean isInverseOneToOne =
                        relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE
                                && relationship.getMappedBy() != null
                                && relationship.getSourceTable().equals(table.getName());

                if (!isInverseOneToOne) {
                    continue;
                }

                String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
                String fieldName = java.beans.Introspector.decapitalize(targetEntity);
                String mappedBy = relationship.getMappedBy();

                if (generatedFieldNames.contains(fieldName)) {
                    log.warn("Skipping inverse OneToOne field '{}': already generated", fieldName);
                    continue;
                }
                generatedFieldNames.add(fieldName);

                log.debug("Adding inverse @OneToOne for '{}', mappedBy='{}'", targetEntity, mappedBy);

                builder.append("    @OneToOne(mappedBy = \"").append(mappedBy).append("\", fetch = FetchType.LAZY)\n");
                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }
        }
    }

    /**
     * Generates a synthetic pure many-to-many field using metadata registered on the table.
     *
     * @param builder the builder receiving generated content
     * @param manyToManyRelation the synthetic many-to-many metadata
     * @param generatedFieldNames already generated field names for duplicate protection
     * @param useBuilder true when Lombok builder is enabled for the entity
     */
    public void addSyntheticManyToManyField(StringBuilder builder,
                                            ManyToManyRelation manyToManyRelation,
                                            Set<String> generatedFieldNames,
                                            boolean useBuilder) {
        if (manyToManyRelation == null) {
            return;
        }

        String fieldName = manyToManyRelation.getFieldName();
        if (fieldName == null || fieldName.isBlank()) {
            log.warn("Skipping synthetic ManyToMany field because fieldName is blank.");
            return;
        }

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("Skipping synthetic ManyToMany field '{}': already generated", fieldName);
            return;
        }

        generatedFieldNames.add(fieldName);

        String targetEntity = manyToManyRelation.getTargetEntityName();

        if (manyToManyRelation.isOwningSide()) {
            builder.append("    @ManyToMany(fetch = FetchType.LAZY)\n");
            builder.append("    @JoinTable(name = \"")
                    .append(stripSchema(manyToManyRelation.getJoinTableName()))
                    .append("\", joinColumns = @JoinColumn(name = \"")
                    .append(manyToManyRelation.getJoinColumnName())
                    .append("\"), inverseJoinColumns = @JoinColumn(name = \"")
                    .append(manyToManyRelation.getInverseJoinColumnName())
                    .append("\"))\n");
        } else {
            builder.append("    @ManyToMany(mappedBy = \"")
                    .append(manyToManyRelation.getMappedBy())
                    .append("\", fetch = FetchType.LAZY)\n");
        }

        addBuilderDefaultAnnotation(builder, useBuilder, true);

        builder.append("    private List<")
                .append(targetEntity)
                .append("> ")
                .append(fieldName)
                .append(" = new ArrayList<>();\n\n");
    }

    /**
     * Checks whether the table contains synthetic many-to-many metadata.
     *
     * @param table the table to inspect
     * @return true when synthetic many-to-many relations exist
     */
    private boolean hasSyntheticManyToManyRelations(Table table) {
        return table != null
                && table.getManyToManyRelations() != null
                && !table.getManyToManyRelations().isEmpty();
    }



    private String getEmbeddedIdTypeName(Table table) {
        String rawTableName = table != null ? table.getName() : null;
        String simpleTableName = stripSchema(rawTableName);
        return NamingConverter.toPascalCase(simpleTableName) + "PK";
    }

    private boolean isCompositeKeyJoinTable(Table table) {
        if (!hasCompositePrimaryKey(table)) {
            return false;
        }

        List<Column> pkColumns = getPrimaryKeyColumns(table);

        // Composite join-like PK table if every PK column:
        // - is marked as FK OR
        // - has a resolved relationship OR
        // - at least looks like FK by naming (e.g. xxx_id)
        return pkColumns.stream().allMatch(col ->
                col.isForeignKey()
                        || findRelationshipForColumn(table, col).isPresent()
                        || looksLikeForeignKeyColumn(col)
        );
    }

    private boolean hasCompositePrimaryKey(Table table) {
        return getPrimaryKeyColumns(table).size() > 1;
    }

    private List<Column> getPrimaryKeyColumns(Table table) {
        if (table == null || table.getColumns() == null) {
            return Collections.emptyList();
        }

        return table.getColumns().stream()
                .filter(Column::isPrimaryKey)
                .collect(Collectors.toList());
    }



    private boolean isCompositeKeyColumn(Column column, List<Column> compositeKeyColumns) {
        if (column == null || compositeKeyColumns == null || compositeKeyColumns.isEmpty()) {
            return false;
        }

        String columnName = column.getName();
        if (columnName == null) {
            return false;
        }

        return compositeKeyColumns.stream()
                .map(Column::getName)
                .filter(Objects::nonNull)
                .anyMatch(columnName::equals);
    }

    private void addCompositeKeyRelationshipField(StringBuilder builder,
                                                  Column column,
                                                  Table table,
                                                  Set<String> generatedFieldNames) {
        log.debug("🔷 Generating composite-key relationship field for column '{}' in table '{}'",
                column.getName(), table.getName());

        Optional<Relationship> relationshipOpt = findRelationshipForColumn(table, column);

        if (relationshipOpt.isEmpty()) {
            relationshipOpt = inferCompositePkRelationship(table, column);
        }

        if (relationshipOpt.isEmpty()) {
            log.warn("⚠️ Composite PK column '{}' in table '{}' has no resolved/inferred relationship. " +
                            "Keeping it only inside EmbeddedId (no @MapsId field generated).",
                    column.getName(), table.getName());
            return;
        }

        Relationship relationship = relationshipOpt.get();

        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = NamingConverter.toCamelCase(column.getName().replaceAll("(?i)_id$", ""));
        String embeddedIdFieldName = NamingConverter.toCamelCase(column.getName());

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping composite relationship field '{}': already generated", fieldName);
            return;
        }
        generatedFieldNames.add(fieldName);

        boolean requiredFk = isNotNullColumn(column);

        builder.append("    @MapsId(\"").append(embeddedIdFieldName).append("\")\n");
        builder.append("    @ManyToOne(fetch = FetchType.LAZY)\n");
        builder.append("    @JoinColumn(name = \"").append(column.getName()).append("\"");

        String targetColumn = relationship.getTargetColumn();
        if (shouldIncludeReferencedColumn(targetColumn)) {
            builder.append(", referencedColumnName = \"").append(targetColumn).append("\"");
        }

        if (requiredFk) {
            builder.append(", nullable = false");
        }

        addOnDeleteAndOnUpdate(builder, relationship);
        builder.append(")\n");

        builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
    }

    private Optional<Relationship> findRelationshipForColumn(Table table, Column column) {
        if (table == null || column == null || table.getRelationships() == null) {
            return Optional.empty();
        }

        return table.getRelationships().stream()
                .filter(rel -> Objects.equals(stripSchema(rel.getSourceTable()), stripSchema(table.getName())))
                .filter(rel -> Objects.equals(rel.getSourceColumn(), column.getName()))
                .findFirst();
    }

    private boolean looksLikeForeignKeyColumn(Column column) {
        if (column == null || column.getName() == null) {
            return false;
        }
        return column.getName().toLowerCase(Locale.ROOT).endsWith("_id");
    }

    private Optional<Relationship> inferCompositePkRelationship(Table sourceTable, Column sourceColumn) {
        if (sourceTable == null || sourceColumn == null || sourceColumn.getName() == null) {
            return Optional.empty();
        }

        // 1) Prefer explicit FK metadata if present (even if relationship resolver did not attach it)
        if (sourceColumn.getReferencedTable() != null && !sourceColumn.getReferencedTable().isBlank()) {
            Optional<Table> explicitTarget = findTargetTableInGeneratorMap(sourceColumn.getReferencedTable());

            Relationship rel = new Relationship();
            rel.setSourceTable(sourceTable.getName());
            rel.setSourceColumn(sourceColumn.getName());
            rel.setRelationshipType(Relationship.RelationshipType.MANYTOONE);
            rel.setOnDelete(sourceColumn.getOnDelete());
            rel.setOnUpdate(sourceColumn.getOnUpdate());

            if (explicitTarget.isPresent()) {
                Table targetTable = explicitTarget.get();
                String targetColumnName = resolveTargetColumnName(targetTable, sourceColumn.getReferencedColumn());

                rel.setTargetTable(targetTable.getName());
                rel.setTargetColumn(targetColumnName);

                log.info("🧩 Inferred composite PK relationship from explicit FK metadata (mapped target): {}.{} -> {}.{}",
                        rel.getSourceTable(), rel.getSourceColumn(), rel.getTargetTable(), rel.getTargetColumn());

                return Optional.of(rel);
            }

            // Fallback even if tableMap is not populated (e.g. direct unit test on createEntityContent)
            rel.setTargetTable(sourceColumn.getReferencedTable());
            String referencedColumn = sourceColumn.getReferencedColumn();
            rel.setTargetColumn((referencedColumn == null || referencedColumn.isBlank()) ? "id" : referencedColumn);

            log.info("🧩 Inferred composite PK relationship from explicit FK metadata (raw target): {}.{} -> {}.{}",
                    rel.getSourceTable(), rel.getSourceColumn(), rel.getTargetTable(), rel.getTargetColumn());

            return Optional.of(rel);
        }

        // 2) Fallback heuristic from column name (e.g. business_location_id -> business_location)
        if (!looksLikeForeignKeyColumn(sourceColumn)) {
            return Optional.empty();
        }

        Optional<Table> targetTableOpt = findTargetTableHeuristically(sourceTable, sourceColumn);
        if (targetTableOpt.isEmpty()) {
            return Optional.empty();
        }

        Table targetTable = targetTableOpt.get();
        String targetColumnName = firstPrimaryKeyColumnName(targetTable).orElse("id");

        Relationship rel = new Relationship();
        rel.setSourceTable(sourceTable.getName());
        rel.setSourceColumn(sourceColumn.getName());
        rel.setTargetTable(targetTable.getName());
        rel.setTargetColumn(targetColumnName);
        rel.setRelationshipType(Relationship.RelationshipType.MANYTOONE);
        rel.setOnDelete(sourceColumn.getOnDelete());
        rel.setOnUpdate(sourceColumn.getOnUpdate());

        log.info("🧩 Inferred composite PK relationship heuristically: {}.{} -> {}.{}",
                rel.getSourceTable(), rel.getSourceColumn(), rel.getTargetTable(), rel.getTargetColumn());

        return Optional.of(rel);
    }


    private Optional<Table> findTargetTableHeuristically(Table sourceTable, Column sourceColumn) {
        if (this.tableMap.isEmpty()) {
            return Optional.empty();
        }

        String columnName = sourceColumn.getName();
        String base = columnName.replaceFirst("(?i)_id$", "").trim();

        if (base.isBlank() || base.equalsIgnoreCase(columnName)) {
            return Optional.empty();
        }

        List<String> candidates = buildTargetTableNameCandidates(base);
        String sourceSchema = extractSchema(sourceTable != null ? sourceTable.getName() : null);

        // 1) Prefer same schema
        for (String candidate : candidates) {
            for (Table t : new LinkedHashSet<>(this.tableMap.values())) {
                if (t == null || t.getName() == null) {
                    continue;
                }

                String tSchema = extractSchema(t.getName());
                String tNameNoSchema = stripSchema(t.getName());

                if (sameSchema(sourceSchema, tSchema) && namesMatchLoosely(candidate, tNameNoSchema)) {
                    return Optional.of(t);
                }
            }
        }

        // 2) Any schema
        for (String candidate : candidates) {
            for (Table t : new LinkedHashSet<>(this.tableMap.values())) {
                if (t == null || t.getName() == null) {
                    continue;
                }

                String tNameNoSchema = stripSchema(t.getName());
                if (namesMatchLoosely(candidate, tNameNoSchema)) {
                    return Optional.of(t);
                }
            }
        }

        log.warn("⚠️ Could not infer target table for composite PK column '{}.{}' (candidates={})",
                sourceTable != null ? sourceTable.getName() : "null",
                sourceColumn.getName(),
                candidates);

        return Optional.empty();
    }

    private Optional<Table> findTargetTableInGeneratorMap(String referencedTableRaw) {
        if (referencedTableRaw == null || referencedTableRaw.isBlank() || this.tableMap.isEmpty()) {
            return Optional.empty();
        }

        String raw = referencedTableRaw.trim();

        // Exact key
        Table direct = this.tableMap.get(raw);
        if (direct != null) {
            return Optional.of(direct);
        }

        // Strip schema exact
        String rawNoSchema = stripSchema(raw);
        for (Table t : new LinkedHashSet<>(this.tableMap.values())) {
            if (t == null || t.getName() == null) {
                continue;
            }

            if (Objects.equals(stripSchema(t.getName()), rawNoSchema)) {
                return Optional.of(t);
            }
        }

        // Loose match
        for (Table t : new LinkedHashSet<>(this.tableMap.values())) {
            if (t == null || t.getName() == null) {
                continue;
            }

            if (namesMatchLoosely(rawNoSchema, stripSchema(t.getName()))) {
                return Optional.of(t);
            }
        }

        return Optional.empty();
    }

    private String resolveTargetColumnName(Table targetTable, String referencedColumnRaw) {
        if (targetTable == null) {
            return "id";
        }

        if (referencedColumnRaw != null && !referencedColumnRaw.isBlank()) {
            Optional<String> exact = targetTable.getColumns().stream()
                    .filter(Objects::nonNull)
                    .map(Column::getName)
                    .filter(Objects::nonNull)
                    .filter(c -> c.equalsIgnoreCase(referencedColumnRaw.trim()))
                    .findFirst();

            if (exact.isPresent()) {
                return exact.get();
            }
        }

        return firstPrimaryKeyColumnName(targetTable).orElse("id");
    }

    private boolean sameSchema(String s1, String s2) {
        return normalizeLooseName(s1).equals(normalizeLooseName(s2));
    }

    private boolean namesMatchLoosely(String a, String b) {
        return normalizeLooseName(a).equals(normalizeLooseName(b));
    }

    private String normalizeLooseName(String value) {
        if (value == null) {
            return "";
        }

        String v = unquoteIdentifier(value).trim().toLowerCase(Locale.ROOT);
        return v.replaceAll("[^a-z0-9]", "");
    }

    private String unquoteIdentifier(String value) {
        if (value == null) {
            return null;
        }
        return value.replace("\"", "");
    }


    private List<String> buildTargetTableNameCandidates(String baseName) {
        LinkedHashSet<String> candidates = new LinkedHashSet<>();
        if (baseName == null || baseName.isBlank()) {
            return new ArrayList<>();
        }

        String base = baseName.trim();
        candidates.add(base);
        candidates.add(base + "s");
        candidates.add(base + "es");

        if (base.endsWith("y") && base.length() > 1) {
            candidates.add(base.substring(0, base.length() - 1) + "ies");
        }

        return new ArrayList<>(candidates);
    }

    private Optional<String> firstPrimaryKeyColumnName(Table table) {
        if (table == null || table.getColumns() == null) {
            return Optional.empty();
        }

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getName)
                .filter(Objects::nonNull)
                .findFirst();
    }
    private String extractSchema(String rawTableName) {
        if (rawTableName == null) {
            return null;
        }

        String cleaned = unquoteIdentifier(rawTableName.trim());
        int dot = cleaned.lastIndexOf('.');

        if (dot <= 0) {
            return null;
        }

        return cleaned.substring(0, dot);
    }



    private String toSimpleJavaType(String javaType) {
        String type = javaType == null ? "" : javaType.trim();

        if (type.startsWith("java.lang.")) {
            return type.substring("java.lang.".length());
        }
        if (type.startsWith("java.time.")) {
            return type.substring("java.time.".length());
        }
        if (type.startsWith("java.math.")) {
            return type.substring("java.math.".length());
        }
        if (type.startsWith("java.util.")) {
            return type.substring("java.util.".length());
        }
        return type;
    }



    /**
     * Adds primary key annotations and generates the primary key field.
     *
     * @param builder the builder receiving generated content
     * @param column the primary key column metadata
     * @param useBuilder true when Lombok builder is enabled for the entity
     */
    private void addPrimaryKeyAnnotations(StringBuilder builder, Column column, boolean useBuilder) {
        builder.append("    @Id\n");

        if (!column.isForeignKey()) {
            if (column.isIdentity()) {
                builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            } else if (shouldUseUuidGeneration(column)) {
                builder.append("    @GeneratedValue(strategy = GenerationType.UUID)\n");
            }
        }

        addColumnField(builder, column, useBuilder);
    }

    private boolean shouldUseUuidGeneration(Column column) {
        if (column == null) {
            return false;
        }

        if (!isUuidType(column.getJavaType())) {
            return false;
        }

        String defaultValue = column.getDefaultValue();
        if (defaultValue == null || defaultValue.isBlank()) {
            return false;
        }

        String normalizedDefaultValue = defaultValue.trim().toLowerCase(Locale.ROOT);
        return normalizedDefaultValue.contains("gen_random_uuid()");
    }



    // parent  Side Table
    public void addRelationshipField(StringBuilder builder, Column column, Table table, Set<String> generatedFieldNames) {
        log.debug("🔵 Resolving relationship for column: {} in table: {}", column.getName(), table.getName());

        // 3. addRelationshipField()
        Optional<Relationship> relationshipOpt = table.getRelationships().stream()
                .filter(rel -> Objects.equals(stripSchema(rel.getSourceTable()), stripSchema(table.getName())))
                .filter(rel -> Objects.equals(rel.getSourceColumn(), column.getName()))
                .findFirst();

        if (relationshipOpt.isEmpty()) {
            log.warn("⚠️ No relationship found for FK column '{}'. Falling back to scalar field.", column.getName());
            addUnresolvedForeignKeyScalarField(builder, column);
            return;
        }

        Relationship relationship = relationshipOpt.get();
        log.debug("💬 Relationship details -> Source: {}, Target: {}, Type: {}, MappedBy: {}, JoinTable: {}",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(),
                relationship.getMappedBy(), relationship.getJoinTableName());

        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = NamingConverter.toCamelCase(column.getName().replaceAll("(?i)_id$", ""));

        if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY &&
                relationship.getJoinTableName() != null) {
            fieldName = NamingConverter.toCamelCasePlural(stripSchema(relationship.getTargetTable()));
        }

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping relationship field '{}': already generated (possible duplicate)", fieldName);
            return;
        }

        generatedFieldNames.add(fieldName);

        boolean requiredFk = isNotNullColumn(column);

        switch (relationship.getRelationshipType()) {

            case ONETOONE -> {
                // If mappedBy exists, we are on the inverse side; do not generate the owning field here.
                if (relationship.getMappedBy() != null) {
                    log.debug("⏭️ Skipping ONETOONE field '{}' because it's inverse side (mappedBy = '{}')",
                            fieldName, relationship.getMappedBy());
                    return;
                }

                builder.append("    @OneToOne(fetch = FetchType.LAZY)\n");
                builder.append("    @JoinColumn(name = \"").append(column.getName()).append("\"");

                String targetColumn = relationship.getTargetColumn();
                if (shouldIncludeReferencedColumn(targetColumn)) {
                    builder.append(", referencedColumnName = \"").append(targetColumn).append("\"");
                }

                if (requiredFk) {
                    builder.append(", nullable = false");
                }

                if (column.isUnique()) {
                    builder.append(", unique = true");
                }

                addOnDeleteAndOnUpdate(builder, relationship);
                builder.append(")\n");

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }

            case MANYTOONE -> {
                builder.append("    @ManyToOne(fetch = FetchType.LAZY)\n");
                builder.append("    @JoinColumn(name = \"").append(column.getName()).append("\"");

                String targetColumn = relationship.getTargetColumn();
                if (shouldIncludeReferencedColumn(targetColumn)) {
                    builder.append(", referencedColumnName = \"").append(targetColumn).append("\"");
                }

                if (requiredFk) {
                    builder.append(", nullable = false");
                }

                addOnDeleteAndOnUpdate(builder, relationship);
                builder.append(")\n");

                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }

            case MANYTOMANY -> {
                log.debug("⏭️ Skipping MANYTOMANY generation here; handled elsewhere. Field='{}'", fieldName);
            }

            default -> log.warn("⚠️ Relationship type {} is not handled here for column: {}",
                    relationship.getRelationshipType(), column.getName());
        }
    }

    private boolean shouldIncludeReferencedColumn(String targetColumn) {
        if (targetColumn == null) {
            return false;
        }
        String tc = targetColumn.trim();
        if (tc.isEmpty()) {
            return false;
        }
        return !tc.equalsIgnoreCase("id");
    }

    private boolean isNotNullColumn(Column column) {
        try {
            var m = column.getClass().getMethod("isNullable");
            Object v = m.invoke(column);
            if (v instanceof Boolean b) {
                return !b;
            }
        } catch (Exception ignored) {
        }

        try {
            var m = column.getClass().getMethod("isNotNull");
            Object v = m.invoke(column);
            if (v instanceof Boolean b) {
                return b;
            }
        } catch (Exception ignored) {
        }

        try {
            var m = column.getClass().getMethod("getNullable");
            Object v = m.invoke(column);
            if (v instanceof Boolean b) {
                return !b;
            }
        } catch (Exception ignored) {
        }

        return false;
    }


    /**
     * Generates the owning side of a many-to-many relationship.
     *
     * @param builder the builder receiving generated content
     * @param relationship the many-to-many relationship metadata
     * @param generatedFieldNames already generated field names for duplicate protection
     * @param useBuilder true when Lombok builder is enabled for the entity
     */
    public void addManyToManyParentSide(StringBuilder builder,
                                        Relationship relationship,
                                        Set<String> generatedFieldNames,
                                        boolean useBuilder) {
        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = NamingConverter.toCamelCasePlural(stripSchema(relationship.getTargetTable()));

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping ManyToMany owning-side field '{}': already generated (likely duplicate)", fieldName);
            return;
        }
        generatedFieldNames.add(fieldName);

        String joinTableName = stripSchema(relationship.getJoinTableName());

        builder.append("    @ManyToMany(fetch = FetchType.LAZY)\n")
                .append("    @JoinTable(\n")
                .append("        name = \"").append(joinTableName).append("\",\n")
                .append("        joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn()).append("\", nullable = false),\n")
                .append("        inverseJoinColumns = @JoinColumn(name = \"").append(relationship.getInverseJoinColumn()).append("\", nullable = false)\n")
                .append("    )\n");

        addBuilderDefaultAnnotation(builder, useBuilder, true);

        builder.append("    private List<").append(targetEntity).append("> ")
                .append(fieldName).append(" = new ArrayList<>();\n\n");
    }

    /**
     * Generates inverse-side collection fields for one-to-many and many-to-many relationships.
     *
     * @param builder the builder receiving generated content
     * @param relationship the inverse relationship metadata
     * @param generatedFieldNames already generated field names for duplicate protection
     * @param useBuilder true when Lombok builder is enabled for the entity
     */
    public void addInverseRelationshipField(StringBuilder builder,
                                            Relationship relationship,
                                            Set<String> generatedFieldNames,
                                            boolean useBuilder) {
        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = NamingConverter.toCamelCasePlural(stripSchema(relationship.getTargetTable()));

        if (generatedFieldNames.contains(fieldName)) {
            log.warn("⚠️ Skipping inverse field '{}': already generated (probably duplicate relationship)", fieldName);
            return;
        }

        generatedFieldNames.add(fieldName);

        log.debug("🔄 Creating inverse relationship field for table '{}' -> '{}', type: {}, mappedBy: '{}', fieldName: '{}'",
                relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType(), relationship.getMappedBy(), fieldName);

        switch (relationship.getRelationshipType()) {

            case ONETOMANY -> {
                if (relationship.getMappedBy() == null || relationship.getMappedBy().isBlank()) {
                    log.warn("⚠️ Skipping OneToMany inverse field '{}' because mappedBy is missing.", fieldName);
                    return;
                }

                builder.append("    @OneToMany(mappedBy = \"")
                        .append(relationship.getMappedBy())
                        .append("\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)\n");

                addBuilderDefaultAnnotation(builder, useBuilder, true);

                builder.append("    private List<").append(targetEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
            }

            case MANYTOMANY -> {
                if (relationship.getMappedBy() == null || relationship.getMappedBy().isBlank()) {
                    log.warn("⚠️ Skipping ManyToMany inverse field '{}' because mappedBy is missing.", fieldName);
                    return;
                }

                builder.append("    @ManyToMany(mappedBy = \"").append(relationship.getMappedBy())
                        .append("\", fetch = FetchType.LAZY)\n");

                addBuilderDefaultAnnotation(builder, useBuilder, true);

                builder.append("    private List<").append(targetEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
            }

            default -> log.warn("⚠️ Relationship type {} is not handled here for inverse relationships.", relationship.getRelationshipType());
        }
    }

    private void addOnDeleteAndOnUpdate(StringBuilder builder, Relationship relationship) {
        if (relationship == null) {
            return;
        }

        if (relationship.getOnDelete() == null && relationship.getOnUpdate() == null) {
            return;
        }

        String sourceCol = relationship.getSourceColumn();
        String targetCol = relationship.getTargetColumn();

        if (sourceCol == null || sourceCol.isBlank() || targetCol == null || targetCol.isBlank()) {
            log.warn("⚠️ Skipping foreignKey name because source/target columns are missing. sourceColumn='{}', targetColumn='{}'",
                    sourceCol, targetCol);
            return;
        }

        builder.append(", foreignKey = @ForeignKey(name = \"fk_")
                .append(sourceCol)
                .append("_")
                .append(targetCol)
                .append("\", value = ConstraintMode.CONSTRAINT)");
    }



    /**
     * Generates a simple column field with JPA annotations.
     * Handles JSON/JSONB columns explicitly for Hibernate.
     *
     * @param builder the builder receiving generated content
     * @param column the column metadata
     * @param useBuilder whether Lombok builder is enabled
     */
    private void addColumnField(StringBuilder builder, Column column, boolean useBuilder) {
        boolean isForeignKey = column.isForeignKey();
        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        String javaType = resolveJavaType(column);
        String sqlType = normalizeSqlType(column.getSqlType());

        boolean isJsonColumn = TypeMapper.isJsonType(column);
        boolean isNumeric19LongColumn = isNumeric19LongColumn(column, javaType);
        boolean isGeneratedStoredColumn = column.getGeneratedAs() != null && !column.getGeneratedAs().isBlank();

        if (shouldUseCreationTimestamp(column)) {
            builder.append("    @CreationTimestamp\n");
        } else if (shouldUseUpdateTimestamp(column)) {
            builder.append("    @UpdateTimestamp\n");
        }

        if (!isForeignKey) {
            if (isJsonColumn) {
                builder.append("    @JdbcTypeCode(SqlTypes.JSON)\n");

                builder.append("    @Column(name = \"")
                        .append(columnName)
                        .append("\", columnDefinition = \"")
                        .append(TypeMapper.getJsonColumnDefinition(column))
                        .append("\"");

                if (column.isUnique()) {
                    builder.append(", unique = true");
                }

                if (!column.isNullable()) {
                    builder.append(", nullable = false");
                }

                builder.append(")\n");
            } else {
                builder.append("    @Column(name = \"").append(columnName).append("\"");

                int length = column.getLength();
                boolean isCharLike = sqlType.startsWith("VARCHAR") || sqlType.startsWith("CHAR");

                if ("TEXT".equals(sqlType)) {
                    builder.append(", columnDefinition = \"text\"");
                }

                if (isCharLike && length > 0 && length != 255) {
                    builder.append(", length = ").append(length);
                }

                if (!isNumeric19LongColumn
                        && (sqlType.startsWith("DECIMAL") || sqlType.startsWith("NUMERIC"))
                        && column.getPrecision() > 0) {
                    builder.append(", precision = ").append(column.getPrecision());

                    if (column.getScale() > 0) {
                        builder.append(", scale = ").append(column.getScale());
                    }
                }

                if (column.isUnique()) {
                    builder.append(", unique = true");
                }

                if (!column.isNullable()) {
                    builder.append(", nullable = false");
                }

                if (isCreationTimestampColumnName(columnName)) {
                    builder.append(", updatable = false");
                }

                if (isGeneratedStoredColumn) {
                    builder.append(", insertable = false, updatable = false");
                }

                builder.append(")\n");
            }
        }

        String cleanedType = toSimpleJavaType(javaType);
        String booleanDefaultInitializer = resolveBooleanDefaultInitializer(column, columnName);
        boolean hasInitializer = booleanDefaultInitializer != null;

        addBuilderDefaultAnnotation(builder, useBuilder, hasInitializer);

        builder.append("    private ")
                .append(cleanedType)
                .append(" ")
                .append(column.getFieldName());

        if (hasInitializer) {
            builder.append(" = ").append(booleanDefaultInitializer);
        }

        builder.append(";\n\n");
    }



    private boolean isLocalDateTimeType(String javaType) {
        return "java.time.LocalDateTime".equals(javaType) || "LocalDateTime".equals(javaType);
    }

    private boolean isCreationTimestampColumnName(String columnName) {
        if (columnName == null) {
            return false;
        }
        String name = columnName.trim().toLowerCase(Locale.ROOT);
        return name.equals("created_at") || name.equals("date_created");
    }

    private boolean isUpdateTimestampColumnName(String columnName) {
        if (columnName == null) {
            return false;
        }
        String name = columnName.trim().toLowerCase(Locale.ROOT);
        return name.equals("updated_at") || name.equals("last_updated");
    }

    private String normalizeSqlType(String sqlType) {
        if (sqlType == null) {
            return "";
        }
        return sqlType.trim().toUpperCase();
    }


    public void writeToFile(String filePath, String content) throws IOException {
        Objects.requireNonNull(filePath, "File path cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);

        log.debug("File written successfully: {}", filePath);
    }

    /**
     * Converts parsed tables into generator entity metadata.
     * <p>
     * This method creates:
     * <ul>
     *     <li>scalar fields for normal columns</li>
     *     <li>owning-side relation fields for resolved FK relationships</li>
     *     <li>inverse relation fields for ONETOMANY and inverse ONETOONE</li>
     *     <li>synthetic MANYTOMANY collection fields from pure join tables</li>
     * </ul>
     *
     * @param tables the parsed tables
     * @return the generated entity metadata list
     */
    public List<Entity> toEntities(List<Table> tables) {
        List<Entity> result = new ArrayList<>();

        for (Table table : tables) {
            if (table.isPureJoinTable()) {
                continue;
            }

            Entity entity = new Entity();
            entity.setName(resolveEntityName(table));

            List<Field> fields = new ArrayList<>();
            Set<String> generatedFieldNames = new HashSet<>();

            addColumnAndOwningRelationFields(table, fields, generatedFieldNames);
            addInverseRelationshipFields(table, fields, generatedFieldNames);
            addSyntheticManyToManyFields(table, fields, generatedFieldNames);

            entity.setFields(fields);
            result.add(entity);
        }

        return result;
    }

    /**
     * Resolves the entity class name for the given table.
     *
     * @param table the source table
     * @return the generated entity name
     */
    private String resolveEntityName(Table table) {
        String rawTableName = table.getName();
        String tableName = rawTableName != null && rawTableName.contains(".")
                ? rawTableName.substring(rawTableName.indexOf('.') + 1)
                : rawTableName;

        return NamingConverter.toPascalCase(tableName);
    }

    /**
     * Adds scalar fields and owning-side relation fields to the target field list.
     *
     * @param table the source table
     * @param fields the generated fields
     * @param generatedFieldNames already generated field names
     */
    private void addColumnAndOwningRelationFields(Table table,
                                                  List<Field> fields,
                                                  Set<String> generatedFieldNames) {
        for (Column column : table.getColumns()) {
            Optional<Relationship> relationship = findOwningRelationship(table, column);

            if (relationship.isPresent()) {
                Field relationField = createOwningRelationField(column, relationship.get());

                if (generatedFieldNames.add(relationField.getName())) {
                    fields.add(relationField);
                }
                continue;
            }

            Field scalarField = createScalarField(column);

            if (generatedFieldNames.add(scalarField.getName())) {
                fields.add(scalarField);
            }
        }
    }


    /**
     * Finds the owning-side relationship for the given foreign key column.
     *
     * @param table the source table
     * @param column the source column
     * @return the resolved owning-side relationship if present
     */
    private Optional<Relationship> findOwningRelationship(Table table, Column column) {
        if (column == null || !column.isForeignKey()) {
            return Optional.empty();
        }

        return table.getRelationships().stream()
                .filter(relationship ->
                        relationship.getSourceTable().equals(table.getName())
                                && relationship.getSourceColumn() != null
                                && relationship.getSourceColumn().equals(column.getName())
                                && relationship.getMappedBy() == null
                                && (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                                || relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE))
                .findFirst();
    }

    /**
     * Creates a scalar non-relationship field from a database column.
     *
     * @param column the source column
     * @return the generated scalar field
     */
    private Field createScalarField(Column column) {
        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());

        return Field.builder()
                .name(column.getFieldName())
                .type(resolveJavaType(column))
                .primaryKey(column.isPrimaryKey())
                .foreignKey(column.isForeignKey())
                .unique(column.isUnique())
                .nullable(column.isNullable())
                .length(column.getLength())
                .columnName(columnName)
                .build();
    }


    /**
     * Creates an owning-side relation field from a foreign key column and a resolved relationship.
     *
     * @param column the FK column
     * @param relationship the resolved relationship
     * @return the generated relation field
     */
    private Field createOwningRelationField(Column column, Relationship relationship) {
        String relationFieldName = resolveRelationFieldName(column.getName());
        String referencedEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));

        return Field.builder()
                .name(relationFieldName)
                .type(referencedEntity)
                .primaryKey(column.isPrimaryKey())
                .foreignKey(true)
                .unique(column.isUnique())
                .nullable(column.isNullable())
                .length(column.getLength())
                .columnName(column.getName())
                .referencedEntity(referencedEntity)
                .referencedColumn(relationship.getTargetColumn())
                .mappedBy(null)
                .cascade("ALL")
                .orphanRemoval(false)
                .relationKind(toRelationKind(relationship.getRelationshipType()))
                .collection(false)
                .owningSide(true)
                .build();
    }


    /**
     * Resolves a relation field name from a foreign key column name.
     * Example:
     * company_status_id -> companyStatus
     * parent_company_id -> parentCompany
     *
     * @param columnName the physical FK column name
     * @return the generated relation field name
     */
    private String resolveRelationFieldName(String columnName) {
        if (columnName == null || columnName.isBlank()) {
            return columnName;
        }

        String normalizedName = columnName.endsWith("_id")
                ? columnName.substring(0, columnName.length() - 3)
                : columnName;

        return NamingConverter.toCamelCase(normalizedName);
    }


    /**
     * Adds inverse-side fields derived from resolved relationships.
     *
     * @param table the source table
     * @param fields the generated fields
     * @param generatedFieldNames already generated field names
     */
    private void addInverseRelationshipFields(Table table,
                                              List<Field> fields,
                                              Set<String> generatedFieldNames) {
        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getMappedBy() == null || relationship.getMappedBy().isBlank()) {
                continue;
            }

            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY) {
                Field inverseCollectionField = createInverseCollectionField(relationship);

                if (generatedFieldNames.add(inverseCollectionField.getName())) {
                    fields.add(inverseCollectionField);
                }
            }

            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE) {
                Field inverseOneToOneField = createInverseOneToOneField(relationship);

                if (generatedFieldNames.add(inverseOneToOneField.getName())) {
                    fields.add(inverseOneToOneField);
                }
            }
        }
    }

    /**
     * Creates an inverse one-to-many collection field.
     *
     * @param relationship the inverse relationship metadata
     * @return the generated collection field
     */
    private Field createInverseCollectionField(Relationship relationship) {
        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = NamingConverter.toCamelCasePlural(stripSchema(relationship.getTargetTable()));

        return Field.builder()
                .name(fieldName)
                .type("List<" + targetEntity + ">")
                .foreignKey(false)
                .referencedEntity(targetEntity)
                .mappedBy(relationship.getMappedBy())
                .cascade("ALL")
                .orphanRemoval(true)
                .relationKind(Field.RelationKind.ONE_TO_MANY)
                .collection(true)
                .owningSide(false)
                .build();
    }

    /**
     * Creates an inverse one-to-one field.
     *
     * @param relationship the inverse relationship metadata
     * @return the generated inverse reference field
     */
    private Field createInverseOneToOneField(Relationship relationship) {
        String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
        String fieldName = Character.toLowerCase(targetEntity.charAt(0)) + targetEntity.substring(1);

        return Field.builder()
                .name(fieldName)
                .type(targetEntity)
                .foreignKey(false)
                .referencedEntity(targetEntity)
                .mappedBy(relationship.getMappedBy())
                .cascade("ALL")
                .orphanRemoval(false)
                .relationKind(Field.RelationKind.ONE_TO_ONE)
                .collection(false)
                .owningSide(false)
                .build();
    }


    /**
     * Adds synthetic many-to-many fields defined on the table metadata.
     *
     * @param table the source table
     * @param fields the generated fields
     * @param generatedFieldNames already generated field names
     */
    private void addSyntheticManyToManyFields(Table table,
                                              List<Field> fields,
                                              Set<String> generatedFieldNames) {
        if (table.getManyToManyRelations() == null || table.getManyToManyRelations().isEmpty()) {
            return;
        }

        for (ManyToManyRelation relation : table.getManyToManyRelations()) {
            Field field = Field.builder()
                    .name(relation.getFieldName())
                    .type("List<" + relation.getTargetEntityName() + ">")
                    .foreignKey(false)
                    .referencedEntity(relation.getTargetEntityName())
                    .mappedBy(relation.getMappedBy())
                    .cascade("ALL")
                    .orphanRemoval(false)
                    .relationKind(Field.RelationKind.MANY_TO_MANY)
                    .collection(true)
                    .owningSide(relation.isOwningSide())
                    .joinTableName(relation.getJoinTableName())
                    .joinColumnName(relation.getJoinColumnName())
                    .inverseJoinColumnName(relation.getInverseJoinColumnName())
                    .build();

            if (generatedFieldNames.add(field.getName())) {
                fields.add(field);
            }
        }
    }

    /**
     * Converts the relationship model type to the field relation kind.
     *
     * @param relationshipType the relationship type from the resolver
     * @return the mapped field relation kind
     */
    private Field.RelationKind toRelationKind(Relationship.RelationshipType relationshipType) {
        return switch (relationshipType) {
            case ONETOONE -> Field.RelationKind.ONE_TO_ONE;
            case MANYTOONE -> Field.RelationKind.MANY_TO_ONE;
            case ONETOMANY -> Field.RelationKind.ONE_TO_MANY;
            case MANYTOMANY -> Field.RelationKind.MANY_TO_MANY;
        };
    }


    /**
     * Removes a schema prefix from a table name.
     *
     * @param tableName the raw table name
     * @return the schema-free table name
     */
    private String stripSchema(String tableName) {
        if (tableName == null) {
            return null;
        }

        return tableName.contains(".")
                ? tableName.substring(tableName.indexOf('.') + 1)
                : tableName;
    }

    /**
     * Generates a scalar field for a foreign key column when no relationship
     * could be resolved to a generated entity.
     *
     * @param builder the builder receiving the generated content
     * @param column the unresolved foreign key column
     */
    private void addUnresolvedForeignKeyScalarField(StringBuilder builder, Column column) {
        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        String javaType = resolveJavaType(column);
        String cleanedType = toSimpleJavaType(javaType);
        String sqlType = normalizeSqlType(column.getSqlType());
        boolean isNumeric19LongColumn = isNumeric19LongColumn(column, javaType);

        builder.append("    // TODO: Foreign key '")
                .append(columnName)
                .append("' was not resolved to a generated entity relationship. ")
                .append("Convert to @ManyToOne when the target entity becomes available.\n");

        builder.append("    @Column(name = \"").append(columnName).append("\"");

        int length = column.getLength();
        boolean isCharLike = sqlType.startsWith("VARCHAR") || sqlType.startsWith("CHAR");

        if (isCharLike && length > 0 && length != 255) {
            builder.append(", length = ").append(length);
        }

        if (!isNumeric19LongColumn
                && (sqlType.startsWith("DECIMAL") || sqlType.startsWith("NUMERIC"))
                && column.getPrecision() > 0) {
            builder.append(", precision = ").append(column.getPrecision());

            if (column.getScale() > 0) {
                builder.append(", scale = ").append(column.getScale());
            }
        }

        if (column.isUnique()) {
            builder.append(", unique = true");
        }

        if (!column.isNullable()) {
            builder.append(", nullable = false");
        }

        builder.append(")\n");

        builder.append("    private ")
                .append(cleanedType)
                .append(" ")
                .append(column.getFieldName())
                .append(";\n\n");
    }


    /**
     * Adds Lombok builder default annotation when builder generation is enabled
     * and the field is initialized inline.
     *
     * @param builder the target builder
     * @param useBuilder true when Lombok builder is enabled for the entity
     * @param hasInitializer true when the field will be generated with an inline initializer
     */
    private void addBuilderDefaultAnnotation(StringBuilder builder,
                                             boolean useBuilder,
                                             boolean hasInitializer) {
        if (!useBuilder || !hasInitializer) {
            return;
        }

        builder.append("    @Builder.Default\n");
    }

    /**
     * Resolves the inline boolean default initializer for a column.
     *
     * @param column the source column metadata
     * @param columnName the physical column name used for logging
     * @return {@code "true"} or {@code "false"} when a supported boolean default exists, otherwise {@code null}
     */
    private String resolveBooleanDefaultInitializer(Column column, String columnName) {
        if (column == null) {
            return null;
        }

        String javaType = toSimpleJavaType(column.getJavaType());
        if (!"Boolean".equals(javaType) || column.getDefaultValue() == null) {
            return null;
        }

        String defaultValue = column.getDefaultValue().trim().toLowerCase(Locale.ROOT);

        if (defaultValue.equals("true") || defaultValue.equals("1")) {
            return "true";
        }

        if (defaultValue.equals("false") || defaultValue.equals("0")) {
            return "false";
        }

        log.warn("Boolean column '{}' has an unsupported default value: {}", columnName, defaultValue);
        return null;
    }


    private boolean shouldUseCreationTimestamp(Column column) {
        if (column == null) {
            return false;
        }

        String javaType = column.getJavaType();
        if (!isLocalDateTimeType(javaType)) {
            return false;
        }

        String columnName = column.getName();
        if (!isCreationTimestampColumnName(columnName)) {
            return false;
        }

        String defaultValue = column.getDefaultValue();
        if (defaultValue == null || defaultValue.isBlank()) {
            return false;
        }

        String normalizedDefaultValue = defaultValue.trim().toLowerCase(Locale.ROOT);
        return normalizedDefaultValue.contains("now()")
                || normalizedDefaultValue.equals("current_timestamp")
                || normalizedDefaultValue.equals("localtimestamp");
    }

    private boolean shouldUseUpdateTimestamp(Column column) {
        if (column == null) {
            return false;
        }

        String javaType = column.getJavaType();
        if (!isLocalDateTimeType(javaType)) {
            return false;
        }

        String columnName = column.getName();
        if (!isUpdateTimestampColumnName(columnName)) {
            return false;
        }

        return false;
    }


    /**
     * Checks whether a column is mapped as Long from a numeric(19) or decimal(19) SQL type.
     *
     * @param column the source column
     * @param resolvedJavaType the resolved Java type
     * @return true when the column is a numeric(19) long mapping
     */
    private boolean isNumeric19LongColumn(Column column, String resolvedJavaType) {
        if (column == null || resolvedJavaType == null || !"Long".equals(resolvedJavaType)) {
            return false;
        }

        String sqlType = column.getSqlType() == null ? "" : column.getSqlType().trim().toLowerCase(Locale.ROOT);

        return (sqlType.equals("numeric(19)") || sqlType.equals("decimal(19)"))
                && column.getScale() == 0;
    }


    /**
     * Resolves the effective Java type for a column, overriding numeric(19) to Long.
     *
     * @param column the source column
     * @return the effective Java type
     */
    private String resolveJavaType(Column column) {
        if (column == null) {
            return "";
        }

        String sqlType = column.getSqlType() == null ? "" : column.getSqlType().trim().toLowerCase(Locale.ROOT);

        if ((sqlType.equals("numeric(19)") || sqlType.equals("decimal(19)"))
                && column.getScale() == 0) {
            return "Long";
        }

        return column.getJavaType() == null ? "" : column.getJavaType();
    }


}