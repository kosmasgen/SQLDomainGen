package com.sqldomaingen.generator;

import com.sqldomaingen.model.*;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
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

    public void generate(List<Table> tables,
                         String outputDir,
                         String basePackage,
                         boolean overwrite,
                         boolean useBuilder) {

        log.info("Starting entity generation...");

        Path entityDir = PackageResolver.resolvePath(outputDir, basePackage, "entity");

        Map<String, Table> tablesMap = tables.stream()
                .collect(Collectors.toMap(Table::getName, t -> t));

        this.tableMap.clear();
        this.tableMap.putAll(tablesMap);

        log.debug("Created tablesMap with keys: {}", tablesMap.keySet());

        RelationshipResolver resolver = new RelationshipResolver(tablesMap);
        resolver.resolveRelationshipsForAllTables();
        log.info("RelationshipResolver initialized and relationships resolved.");

        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        for (Table table : tables) {
            log.debug("Processing table: {}", table.getName());

            String rawTableName = table.getName();
            String tableName = rawTableName != null && rawTableName.contains(".")
                    ? rawTableName.substring(rawTableName.indexOf('.') + 1)
                    : rawTableName;

            String entityName = NamingConverter.toPascalCase(tableName);

            String entityContent = createEntityContent(table, entityPackage, useBuilder);
            Path entityOutputPath = entityDir.resolve(entityName + ".java");

            if (!overwrite && Files.exists(entityOutputPath)) {
                log.warn("Entity file already exists, skipping: {}", entityOutputPath);
            } else {
                try {
                    writeToFile(entityOutputPath.toString(), entityContent);
                    log.info("Generated entity for table: {}", table.getName());
                } catch (IOException e) {
                    log.error("Failed to write entity file for table: {}", table.getName(), e);
                }
            }

            // Generate external @Embeddable PK class for composite primary keys
            if (hasCompositePrimaryKey(table)) {
                String pkClassName = getEmbeddedIdTypeName(table);
                String pkContent = createEmbeddedIdClassContent(table, entityPackage);
                Path pkOutputPath = entityDir.resolve(pkClassName + ".java");

                if (!overwrite && Files.exists(pkOutputPath)) {
                    log.warn("PK file already exists, skipping: {}", pkOutputPath);
                } else {
                    try {
                        writeToFile(pkOutputPath.toString(), pkContent);
                        log.info("Generated composite PK class '{}' for table: {}", pkClassName, table.getName());
                    } catch (IOException e) {
                        log.error("Failed to write composite PK file '{}' for table: {}", pkClassName, table.getName(), e);
                    }
                }
            }
        }

        log.info("Entity generation complete. Output directory: {}", entityDir.toAbsolutePath());
    }

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

            String javaType = column.getJavaType();
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
            for (String imp : imports) {
                builder.append(imp).append("\n");
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

            builder.append("    @Column(name = \"").append(column.getName()).append("\"");
            if (!column.isNullable()) {
                builder.append(", nullable = false");
            }
            builder.append(")\n");

            builder.append("    private ")
                    .append(toSimpleJavaType(column.getJavaType()))
                    .append(" ")
                    .append(NamingConverter.toCamelCase(column.getName()))
                    .append(";\n\n");
        }

        builder.append("}\n");

        return builder.toString();
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
        boolean needsUuidImport = false;
        boolean needsGenericGenerator = false;

        boolean compositePrimaryKey = hasCompositePrimaryKey(table);

        // Collect imports needed for entity fields only (not for EmbeddedId PK fields)
        for (Column column : table.getColumns()) {
            if (column == null) {
                continue;
            }

            // In composite PK entities, PK columns live in the external PK class, not in the entity fields
            if (compositePrimaryKey && column.isPrimaryKey()) {
                continue;
            }

            String javaType = column.getJavaType();
            if (javaType == null || javaType.isBlank()) {
                continue;
            }

            // Import fully qualified java.* types used by entity fields
            if (javaType.startsWith("java.")) {
                imports.add("import " + javaType + ";");
            }

            // UUID import only if entity has an actual UUID field (non-EmbeddedId PK columns in composite case are skipped above)
            if (isUuidType(javaType)) {
                needsUuidImport = true;
            }

            String columnName = column.getName() == null ? "" : column.getName().toLowerCase(Locale.ROOT);

            // Support both created_at / updated_at and date_created / last_updated
            if ((columnName.equals("created_at") || columnName.equals("date_created"))
                    && "java.time.LocalDateTime".equals(javaType)) {
                needsCreationTimestamp = true;
            }

            if ((columnName.equals("updated_at") || columnName.equals("last_updated"))
                    && "java.time.LocalDateTime".equals(javaType)) {
                needsUpdateTimestamp = true;
            }
        }

        // GenericGenerator only for standalone UUID primary key entities (never for composite @EmbeddedId entities)
        if (!compositePrimaryKey) {
            for (Column column : table.getColumns()) {
                if (column == null) {
                    continue;
                }

                String javaType = column.getJavaType();
                if (column.isPrimaryKey() && javaType != null && isUuidType(javaType)) {
                    needsGenericGenerator = true;
                    break;
                }
            }
        }

        // Collections are needed if any relationship produces List/ArrayList fields
        for (Relationship relationship : table.getRelationships()) {
            if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOMANY
                    || relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) {
                imports.add("import java.util.List;");
                imports.add("import java.util.ArrayList;");
                break;
            }
        }

        if (needsUuidImport) {
            imports.add("import java.util.UUID;");
        }

        if (needsGenericGenerator) {
            imports.add("import org.hibernate.annotations.GenericGenerator;");
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
    private boolean isUuidType(String javaType) {
        String t = javaType.trim();
        return t.equalsIgnoreCase("UUID") || t.equals("java.util.UUID") || t.endsWith(".UUID");
    }


    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");

        // Strip schema prefix (e.g., "public.") if present
        String rawTableName = table.getName();
        int lastDot = rawTableName.lastIndexOf('.');
        String tableName = (lastDot >= 0) ? rawTableName.substring(lastDot + 1) : rawTableName;

        builder.append("@Table(name = \"").append(NamingConverter.toSnakeCase(tableName)).append("\")\n");
        builder.append("@Getter\n");
        builder.append("@Setter\n");

        if (useBuilder) {
            builder.append("@Builder\n");
        }

        builder.append("@NoArgsConstructor\n");
        builder.append("@AllArgsConstructor\n");

        // Convert table name to PascalCase for the class name
        String className = NamingConverter.toPascalCase(tableName);
        builder.append("public class ").append(className).append(" {\n\n");
    }



    public void generateFields(StringBuilder builder, Table table) {
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

        // Pass 1: Generate fields from columns
        for (Column column : table.getColumns()) {
            log.debug("Processing column: {}", column.getName());

            // Composite PK columns are represented in EmbeddedId.
            // If this is a classic join-table composite PK, generate @MapsId + relationship fields.
            if (compositePrimaryKey && isCompositeKeyColumn(column, primaryKeyColumns)) {
                if (compositeJoinTable) {
                    addCompositeKeyRelationshipField(builder, column, table, generatedFieldNames);
                }
                continue;
            }

            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder, column);
                continue;
            }

            // Be slightly more robust: if relationship exists, generate relationship field
            // even if column.isForeignKey() is not properly set by upstream parsing/model mapping.
            boolean hasResolvedRelationship = findRelationshipForColumn(table, column).isPresent();

            if (column.isForeignKey() || hasResolvedRelationship) {
                addRelationshipField(builder, column, table, generatedFieldNames);
                continue;
            }

            addColumnField(builder, column);
        }

        // For classic composite join-table entities, skip inverse collection / synthetic many-to-many generation.
        // The join entity should expose parent references (+ extra columns), not collection navigation fields.
        if (!compositeJoinTable) {

            // Pass 2: Generate inverse relationship fields (mappedBy side) for OneToMany and ManyToMany
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

                addInverseRelationshipField(builder, relationship, generatedFieldNames);
            }

            // Pass 3: Generate owning side for ManyToMany (JoinTable side)
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
                    log.warn("⚠️ Skipping ManyToMany owning-side field '{}': already generated", expectedFieldName);
                    continue;
                }

                addManyToManyParentSide(builder, relationship, generatedFieldNames);
            }

            // Pass 4: Generate inverse side for OneToOne when mappedBy is present
            for (Relationship relationship : table.getRelationships()) {
                boolean isInverseOneToOne =
                        relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE
                                && relationship.getMappedBy() != null
                                && relationship.getSourceTable().equals(table.getName());

                if (!isInverseOneToOne) {
                    continue;
                }

                String targetEntity = NamingConverter.toPascalCase(stripSchema(relationship.getTargetTable()));
                String fieldName = NamingConverter.toCamelCase(stripSchema(relationship.getTargetTable()));
                String mappedBy = relationship.getMappedBy();

                if (generatedFieldNames.contains(fieldName)) {
                    log.warn("⚠️ Skipping inverse OneToOne field '{}': already generated", fieldName);
                    continue;
                }
                generatedFieldNames.add(fieldName);

                log.debug("Adding inverse @OneToOne for '{}', mappedBy='{}'", targetEntity, mappedBy);

                builder.append("    @OneToOne(mappedBy = \"").append(mappedBy).append("\", fetch = FetchType.LAZY)\n");
                builder.append("    private ").append(targetEntity).append(" ").append(fieldName).append(";\n\n");
            }
        }

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
                .filter(rel -> Objects.equals(rel.getSourceTable(), table.getName()))
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

        // Compare "business_location" with "businesslocation"
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



    private void addPrimaryKeyAnnotations(StringBuilder builder, Column column) {
        builder.append("    @Id\n");

        String javaType = column.getJavaType() == null ? "" : column.getJavaType();
        String simpleType = toSimpleJavaType(javaType);

        log.debug("🧪 [PK Generation] Column: {}, JavaType: {}, simpleType: {}, isForeignKey: {}",
                column.getName(), javaType, simpleType, column.isForeignKey());

        // Never auto-generate FK primary keys (common in composite PK / join tables)
        if (!column.isForeignKey()) {
            if (isUuidType(javaType)) {
                log.info("✅ UUID detected for standalone primary key: {}", column.getName());
                builder.append("    @GeneratedValue(generator = \"UUID\")\n");
                builder.append("    @GenericGenerator(name = \"UUID\", strategy = \"org.hibernate.id.UUIDGenerator\")\n");
            } else if ("Long".equalsIgnoreCase(simpleType) || "Integer".equalsIgnoreCase(simpleType)) {
                builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            }
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


    public void addManyToManyParentSide(StringBuilder builder, Relationship relationship, Set<String> generatedFieldNames) {
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
                .append("    )\n")
                .append("    private List<").append(targetEntity).append("> ")
                .append(fieldName).append(" = new ArrayList<>();\n\n");
    }

    // Inverse side (mappedBy) for OneToMany / ManyToMany
    public void addInverseRelationshipField(StringBuilder builder, Relationship relationship, Set<String> generatedFieldNames) {
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
                builder.append("    private List<").append(targetEntity).append("> ")
                        .append(fieldName).append(" = new ArrayList<>();\n\n");
            }

            default -> log.warn("⚠️ Relationship type {} is not handled here for inverse relationships.", relationship.getRelationshipType());
        }
    }

    private String stripSchema(String rawName) {
        if (rawName == null) {
            return null;
        }
        int lastDot = rawName.lastIndexOf('.');
        return (lastDot >= 0) ? rawName.substring(lastDot + 1) : rawName;
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



    private void addColumnField(StringBuilder builder, Column column) {
        boolean isForeignKey = column.isForeignKey();
        String columnName = column.getName() == null ? "" : column.getName();
        String javaType = column.getJavaType() == null ? "" : column.getJavaType();

        if (isTemporalAuditColumn(javaType, columnName)) {
            if (isCreationTimestampColumnName(columnName)) {
                builder.append("    @CreationTimestamp\n");
            } else if (isUpdateTimestampColumnName(columnName)) {
                builder.append("    @UpdateTimestamp\n");
            }
        }

        if (!isForeignKey) {
            builder.append("    @Column(name = \"").append(column.getName()).append("\"");

            String sqlType = normalizeSqlType(column.getSqlType());

            if ((sqlType.startsWith("VARCHAR") || sqlType.startsWith("CHAR")) && column.getLength() > 0) {
                builder.append(", length = ").append(column.getLength());
            }

            if ((sqlType.startsWith("DECIMAL") || sqlType.startsWith("NUMERIC")) && column.getPrecision() > 0) {
                builder.append(", precision = ").append(column.getPrecision());

                if (column.getScale() >= 0) {
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

            builder.append(")");
        }

        String cleanedType = toSimpleJavaType(javaType);

        builder.append("\n    private ").append(cleanedType).append(" ")
                .append(NamingConverter.toCamelCase(column.getName()));

        if ("Boolean".equals(cleanedType) && column.getDefaultValue() != null) {
            String defVal = column.getDefaultValue().trim().toLowerCase();

            if (defVal.equals("true") || defVal.equals("1")) {
                builder.append(" = true");
            } else if (defVal.equals("false") || defVal.equals("0")) {
                builder.append(" = false");
            } else {
                log.warn("⚠️ Boolean column '{}' has an unsupported default value: {}", column.getName(), defVal);
            }
        }

        builder.append(";\n\n");
    }

    private boolean isTemporalAuditColumn(String javaType, String columnName) {
        return isLocalDateTimeType(javaType)
                && (isCreationTimestampColumnName(columnName) || isUpdateTimestampColumnName(columnName));
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

    public List<Entity> toEntities(List<Table> tables) {
        List<Entity> result = new ArrayList<>();

        for (Table table : tables) {
            Entity entity = new Entity();

            String rawTableName = table.getName();
            String tableName = rawTableName.contains(".")
                    ? rawTableName.substring(rawTableName.indexOf(".") + 1)
                    : rawTableName;

            entity.setName(NamingConverter.toPascalCase(tableName));

            List<Field> fields = new ArrayList<>();

            for (Column col : table.getColumns()) {
                Field field = new Field();

                field.setName(NamingConverter.toCamelCase(col.getName()));
                field.setType(col.getJavaType());

                field.setPrimaryKey(col.isPrimaryKey());
                field.setForeignKey(col.isForeignKey());
                field.setUnique(col.isUnique());
                field.setNullable(col.isNullable());

                field.setLength(col.getLength());
                field.setColumnName(col.getName());

                // Relationship metadata
                if (col.isForeignKey()) {
                    table.getRelationships().stream()
                            .filter(rel ->
                                    rel.getSourceTable().equals(table.getName()) &&
                                            rel.getSourceColumn() != null &&
                                            rel.getSourceColumn().equals(col.getName())
                            )
                            .findFirst()
                            .ifPresent(rel -> {
                                field.setReferencedEntity(
                                        NamingConverter.toPascalCase(rel.getTargetTable())
                                );
                                field.setReferencedColumn(rel.getTargetColumn());
                                field.setMappedBy(rel.getMappedBy());
                                field.setCascade("ALL");
                                field.setOrphanRemoval(
                                        rel.getRelationshipType() == Relationship.RelationshipType.ONETOMANY
                                );
                            });
                }

                fields.add(field);
            }

            entity.setFields(fields);
            result.add(entity);
        }

        return result;
    }

}