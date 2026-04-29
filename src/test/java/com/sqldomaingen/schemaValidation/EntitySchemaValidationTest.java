package com.sqldomaingen.schemaValidation;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.Constants;
import com.sqldomaingen.util.TypeMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.fail;

/**
 * Validates generated entity source files against the SQL schema file.
 *
 * <p>This test reads generated Java source files directly from the generated project output
 * and validates:
 * <ul>
 *     <li>presence of {@code @Entity}, {@code @Table}, and {@code @Id}/{@code @EmbeddedId},</li>
 *     <li>field-to-column mappings against the SQL schema,</li>
 *     <li>join column and join table mappings,</li>
 *     <li>basic relation declarations,</li>
 *     <li>foreign-key-aware relation consistency for local schema tables,</li>
 *     <li>graceful handling of external references to tables not present in the parsed schema,</li>
 *     <li>missing fields or missing annotations, and</li>
 *     <li>source-level structural consistency without compiling the generated project.</li>
 * </ul>
 */
class EntitySchemaValidationTest {

    /**
     * Validates generated entity source files against the SQL schema.
     *
     * @throws Exception if file reading fails
     */
    @Test
    void shouldValidateGeneratedEntitySourceFilesAgainstSchema() throws Exception {
        List<String> violations = new ArrayList<>();

        printValidationChecklist();

        if (!Files.exists(Constants.SCHEMA_PATH)) {
            fail("Missing schema file: " + Constants.SCHEMA_PATH.toAbsolutePath());
        }

        if (!Files.exists(Constants.GENERATED_JAVA_ROOT)) {
            fail("Missing generated Java root: " + Constants.GENERATED_JAVA_ROOT.toAbsolutePath());
        }

        String sql = Files.readString(Constants.SCHEMA_PATH);
        Map<String, TableDefinition> schemaTables = parseSchema(sql);

        List<JavaEntityDefinition> entityDefinitions = findGeneratedEntityDefinitions(violations);
        printEntitiesWithTodoComments(entityDefinitions);

        if (entityDefinitions.isEmpty()) {
            violations.add("No generated entity source files were found under: " + Constants.GENERATED_JAVA_ROOT.toAbsolutePath());
            fail(buildViolationReport(violations));
        }

        Map<String, JavaEntityDefinition> entityBySimpleName = entityDefinitions.stream()
                .collect(Collectors.toMap(JavaEntityDefinition::simpleName, entity -> entity, (left, right) -> left, LinkedHashMap::new));

        for (JavaEntityDefinition entityDefinition : entityDefinitions) {
            validateEntityDefinition(entityDefinition, schemaTables, entityBySimpleName, violations);
        }

        if (!violations.isEmpty()) {
            fail(buildViolationReport(violations));
        }
    }

    /**
     * Finds generated Java entity source files and parses them into entity definitions.
     *
     * @param violations collected violations
     * @return parsed entity definitions
     * @throws IOException if file traversal fails
     */
    private List<JavaEntityDefinition> findGeneratedEntityDefinitions(List<String> violations) throws IOException {
        List<JavaEntityDefinition> entityDefinitions = new ArrayList<>();

        try (var paths = Files.walk(Constants.GENERATED_JAVA_ROOT)) {
            List<Path> javaFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(path -> path.toString().replace("\\", "/").contains("/entity/"))
                    .sorted(Comparator.comparing(Path::toString))
                    .toList();

            for (Path javaFile : javaFiles) {
                try {
                    String content = Files.readString(javaFile);
                    JavaEntityDefinition entityDefinition = parseJavaEntityFile(javaFile, content);

                    if (entityDefinition != null && (entityDefinition.isEntity() || entityDefinition.isEmbeddable())) {
                        entityDefinitions.add(entityDefinition);
                    }
                } catch (Exception exception) {
                    violations.add("Could not parse entity file: " + javaFile + " -> " + exception.getMessage());
                }
            }
        }

        return entityDefinitions;
    }

    /**
     * Parses one generated Java entity file.
     *
     * @param javaFile Java source file path
     * @param content  file content
     * @return parsed entity definition or null if the file is not an entity-related type
     */
    private JavaEntityDefinition parseJavaEntityFile(Path javaFile, String content) {
        String normalizedContent = stripComments(content);

        String packageName = extractPackageName(normalizedContent);
        String className = extractClassName(normalizedContent);

        if (className == null || className.isBlank()) {
            return null;
        }

        boolean isEntity = normalizedContent.contains("@Entity");
        boolean isMappedSuperclass = normalizedContent.contains("@MappedSuperclass");
        boolean isEmbeddable = normalizedContent.contains("@Embeddable");
        boolean hasTodoComment = content.contains("TODO");

        String tableName = extractTableName(normalizedContent);
        String tableSchema = extractTableSchema(normalizedContent);

        List<JavaFieldDefinition> fieldDefinitions = parseJavaFields(normalizedContent);

        return new JavaEntityDefinition(
                javaFile,
                packageName,
                className,
                isEntity,
                isMappedSuperclass,
                isEmbeddable,
                hasTodoComment,
                tableSchema,
                tableName,
                fieldDefinitions
        );
    }

    /**
     * Validates one parsed entity definition against the SQL schema.
     *
     * @param entityDefinition   parsed entity definition
     * @param schemaTables       parsed schema tables
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateEntityDefinition(
            JavaEntityDefinition entityDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        if (entityDefinition.isEmbeddable()) {
            return;
        }

        if (entityDefinition.tableName() == null || entityDefinition.tableName().isBlank()) {
            violations.add("[" + entityDefinition.displayName() + "] Missing @Table or blank @Table(name)");
            return;
        }

        String requestedTableName = buildPhysicalTableName(entityDefinition.tableSchema(), entityDefinition.tableName());
        TableDefinition tableDefinition = resolveTableDefinition(
                schemaTables,
                entityDefinition.tableSchema(),
                entityDefinition.tableName()
        );

        if (tableDefinition == null) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Table not found in SQL schema: " + requestedTableName
            );
            return;
        }

        validateIdentifier(entityDefinition, violations);
        validateDuplicateFieldNames(entityDefinition, violations);
        validateSimpleColumns(entityDefinition, tableDefinition, entityBySimpleName, violations);
        validateJavaTypes(entityDefinition, tableDefinition, violations);
        validateColumnConstraints(entityDefinition, tableDefinition, violations);
        validateRelations(entityDefinition, tableDefinition, schemaTables, entityBySimpleName, violations);
        validateForeignKeyCoverage(entityDefinition, tableDefinition, schemaTables, entityBySimpleName, violations);
        validateMissingTableColumns(entityDefinition, tableDefinition, entityBySimpleName, violations);
    }


    private void validateJavaTypes(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            List<String> violations
    ) {
        for (ColumnDefinition columnDefinition : tableDefinition.columns().values()) {
            JavaFieldDefinition javaFieldDefinition = findJavaFieldForColumn(entityDefinition, columnDefinition);

            if (javaFieldDefinition == null) {
                continue;
            }

            if (javaFieldDefinition.hasAnnotation("ManyToOne")
                    || javaFieldDefinition.hasAnnotation("OneToOne")
                    || javaFieldDefinition.hasAnnotation("OneToMany")
                    || javaFieldDefinition.hasAnnotation("ManyToMany")
                    || javaFieldDefinition.hasAnnotation("Transient")) {
                continue;
            }

            String expectedJavaType = TypeMapper.mapToJavaType(toModelColumn(columnDefinition));
            String actualJavaType = simpleType(javaFieldDefinition.type());

            if (expectedJavaType == null || expectedJavaType.isBlank()) {
                continue;
            }

            if (!simpleType(expectedJavaType).equals(actualJavaType)) {
                violations.add("""
                    [%s] Java type mismatch for column '%s':
                      SQL type: %s
                      Expected Java: %s
                      Actual Java: %s
                    """.formatted(
                        entityDefinition.displayName(),
                        columnDefinition.name(),
                        columnDefinition.sqlType(),
                        simpleType(expectedJavaType),
                        actualJavaType
                ));
            }
        }
    }

    /**
     * Resolves a SQL table definition by full name first and then by unqualified table name.
     *
     * @param schemaTables parsed schema tables
     * @param schema       schema name from @Table
     * @param tableName    table name from @Table
     * @return matching table definition or null
     */
    private TableDefinition resolveTableDefinition(
            Map<String, TableDefinition> schemaTables,
            String schema,
            String tableName
    ) {
        String fullTableName = buildPhysicalTableName(schema, tableName);

        TableDefinition exactMatch = schemaTables.get(normalizeName(fullTableName));
        if (exactMatch != null) {
            return exactMatch;
        }

        String unqualifiedTableName = sanitizeIdentifier(tableName);

        List<TableDefinition> matches = schemaTables.values().stream()
                .filter(table -> normalizeName(extractUnqualifiedTableName(table.fullName()))
                        .equals(normalizeName(unqualifiedTableName)))
                .toList();

        if (matches.size() == 1) {
            return matches.getFirst();
        }

        return null;
    }

    /**
     * Resolves the embedded id definition for a field annotated with {@code @EmbeddedId}.
     *
     * @param field              parsed field
     * @param entityBySimpleName parsed entities by simple name
     * @return embedded id definition or null
     */
    private JavaEntityDefinition resolveEmbeddedIdDefinition(
            JavaFieldDefinition field,
            Map<String, JavaEntityDefinition> entityBySimpleName
    ) {
        return entityBySimpleName.get(simpleTypeName(field.type()));
    }


    /**
     * Extracts the unqualified table name from a schema-qualified name.
     *
     * @param fullTableName full physical table name
     * @return unqualified table name
     */
    private String extractUnqualifiedTableName(String fullTableName) {
        String sanitized = sanitizeIdentifier(fullTableName);

        if (sanitized.contains(".")) {
            return sanitized.substring(sanitized.lastIndexOf('.') + 1);
        }

        return sanitized;
    }

    /**
     * Verifies that an entity declares an identifier field.
     *
     * @param entityDefinition parsed entity definition
     * @param violations       collected violations
     */
    private void validateIdentifier(JavaEntityDefinition entityDefinition, List<String> violations) {
        boolean hasIdentifier = entityDefinition.fields().stream()
                .anyMatch(field -> field.hasAnnotation("Id") || field.hasAnnotation("EmbeddedId"));

        if (!hasIdentifier) {
            violations.add("[" + entityDefinition.displayName() + "] Missing @Id or @EmbeddedId");
        }
    }

    /**
     * Validates simple field-to-column mappings.
     *
     * @param entityDefinition   parsed entity definition
     * @param tableDefinition    parsed SQL table definition
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateSimpleColumns(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        for (JavaFieldDefinition field : entityDefinition.fields()) {
            if (shouldSkipSimpleColumnValidation(field, entityBySimpleName)) {
                continue;
            }

            if (field.hasAnnotation("EmbeddedId")) {
                JavaEntityDefinition embeddedIdDefinition = resolveEmbeddedIdDefinition(field, entityBySimpleName);

                if (embeddedIdDefinition != null) {
                    for (JavaFieldDefinition embeddedField : embeddedIdDefinition.fields()) {
                        if (embeddedField.hasAnnotation("Transient")) {
                            continue;
                        }

                        String expectedColumnName = resolveColumnName(embeddedField);
                        ColumnDefinition columnDefinition = tableDefinition.columns().get(normalizeName(expectedColumnName));

                        if (columnDefinition == null) {
                            violations.add(
                                    "[" + entityDefinition.displayName() + "] Missing DB column for embedded id field '"
                                            + embeddedField.name() + "' -> expected column '" + expectedColumnName
                                            + "' in table '" + tableDefinition.fullName() + "'"
                            );
                        }
                    }
                }

                continue;
            }

            String expectedColumnName = resolveColumnName(field);
            ColumnDefinition columnDefinition = tableDefinition.columns().get(normalizeName(expectedColumnName));

            if (columnDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] Missing DB column for field '" + field.name()
                                + "' -> expected column '" + expectedColumnName
                                + "' in table '" + tableDefinition.fullName() + "'"
                );
            }
        }
    }

    /**
     * Validates relation declarations and join mappings.
     *
     * @param entityDefinition   parsed entity definition
     * @param tableDefinition    parsed SQL table definition
     * @param schemaTables       parsed SQL tables
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateRelations(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        for (JavaFieldDefinition field : entityDefinition.fields()) {
            if (field.hasAnnotation("ManyToOne")) {
                validateManyToOneField(entityDefinition, field, tableDefinition, schemaTables, entityBySimpleName, violations);
            }

            if (field.hasAnnotation("OneToOne")) {
                validateOneToOneField(entityDefinition, field, tableDefinition, schemaTables, entityBySimpleName, violations);
            }

            if (field.hasAnnotation("OneToMany")) {
                validateOneToManyField(entityDefinition, field, entityBySimpleName, violations);
            }

            if (field.hasAnnotation("ManyToMany")) {
                validateManyToManyField(entityDefinition, field, schemaTables, violations);
            }
        }
    }

    /**
     * Validates a ManyToOne field.
     *
     * @param entityDefinition   parsed entity definition
     * @param field              relation field
     * @param tableDefinition    owning table definition
     * @param schemaTables       parsed SQL tables
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateManyToOneField(
            JavaEntityDefinition entityDefinition,
            JavaFieldDefinition field,
            TableDefinition tableDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        List<String> joinColumnNames = resolveJoinColumnNames(field);

        if (joinColumnNames.isEmpty()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] @ManyToOne field '" + field.name()
                            + "' is missing @JoinColumn or @JoinColumns"
            );
            return;
        }

        for (String joinColumnName : joinColumnNames) {
            ColumnDefinition localColumnDefinition = tableDefinition.columns().get(normalizeName(joinColumnName));

            if (localColumnDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] @ManyToOne field '" + field.name()
                                + "' has join column '" + joinColumnName
                                + "' that does not exist in table '" + tableDefinition.fullName() + "'"
                );
                continue;
            }

            ForeignKeyDefinition foreignKeyDefinition = findForeignKeyBySourceColumn(tableDefinition, joinColumnName);

            if (foreignKeyDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] @ManyToOne field '" + field.name()
                                + "' uses join column '" + joinColumnName
                                + "' but no matching foreign key was found in table '" + tableDefinition.fullName() + "'"
                );
                continue;
            }

            validateRelationTargetEntityWhenResolvable(
                    entityDefinition,
                    field,
                    foreignKeyDefinition,
                    schemaTables,
                    entityBySimpleName,
                    violations
            );
        }
    }

    /**
     * Validates a OneToOne field.
     *
     * @param entityDefinition   parsed entity definition
     * @param field              relation field
     * @param tableDefinition    owning table definition
     * @param schemaTables       parsed SQL tables
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateOneToOneField(
            JavaEntityDefinition entityDefinition,
            JavaFieldDefinition field,
            TableDefinition tableDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        boolean inverseSide = field.annotationAttribute("OneToOne", "mappedBy") != null
                && !field.annotationAttribute("OneToOne", "mappedBy").isBlank();

        if (inverseSide) {
            return;
        }

        List<String> joinColumnNames = resolveJoinColumnNames(field);

        if (joinColumnNames.isEmpty()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Owning @OneToOne field '" + field.name()
                            + "' must declare @JoinColumn or @JoinColumns"
            );
            return;
        }

        for (String joinColumnName : joinColumnNames) {
            ColumnDefinition localColumnDefinition = tableDefinition.columns().get(normalizeName(joinColumnName));

            if (localColumnDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] @OneToOne field '" + field.name()
                                + "' has join column '" + joinColumnName
                                + "' that does not exist in table '" + tableDefinition.fullName() + "'"
                );
                continue;
            }

            ForeignKeyDefinition foreignKeyDefinition = findForeignKeyBySourceColumn(tableDefinition, joinColumnName);

            if (foreignKeyDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] @OneToOne field '" + field.name()
                                + "' uses join column '" + joinColumnName
                                + "' but no matching foreign key was found in table '" + tableDefinition.fullName() + "'"
                );
                continue;
            }

            validateRelationTargetEntityWhenResolvable(
                    entityDefinition,
                    field,
                    foreignKeyDefinition,
                    schemaTables,
                    entityBySimpleName,
                    violations
            );
        }
    }

    /**
     * Validates a OneToMany field.
     *
     * @param entityDefinition   parsed entity definition
     * @param field              relation field
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateOneToManyField(
            JavaEntityDefinition entityDefinition,
            JavaFieldDefinition field,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        String mappedBy = field.annotationAttribute("OneToMany", "mappedBy");

        if (mappedBy == null || mappedBy.isBlank()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] @OneToMany field '" + field.name()
                            + "' must declare mappedBy"
            );
            return;
        }

        String targetType = resolveCollectionGenericType(field.type());
        if (targetType == null || targetType.isBlank()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Could not resolve target entity type for @OneToMany field '"
                            + field.name() + "'"
            );
            return;
        }

        JavaEntityDefinition targetEntityDefinition = entityBySimpleName.get(simpleTypeName(targetType));
        if (targetEntityDefinition == null) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] @OneToMany field '" + field.name()
                            + "' points to non-discovered entity '" + targetType + "'"
            );
            return;
        }

        JavaFieldDefinition mappedField = targetEntityDefinition.fields().stream()
                .filter(targetField -> targetField.name().equals(mappedBy))
                .findFirst()
                .orElse(null);

        if (mappedField == null) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] @OneToMany field '" + field.name()
                            + "' has invalid mappedBy='" + mappedBy
                            + "' on target entity '" + targetEntityDefinition.displayName() + "'"
            );
            return;
        }

        boolean validOwningAnnotation = mappedField.hasAnnotation("ManyToOne") || mappedField.hasAnnotation("OneToOne");
        if (!validOwningAnnotation) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] @OneToMany field '" + field.name()
                            + "' mappedBy='" + mappedBy
                            + "' points to field on target entity '" + targetEntityDefinition.displayName()
                            + "' that is not @ManyToOne/@OneToOne"
            );
        }
    }

    /**
     * Validates a ManyToMany field.
     *
     * @param entityDefinition parsed entity definition
     * @param field            relation field
     * @param schemaTables     parsed SQL tables
     * @param violations       collected violations
     */
    private void validateManyToManyField(
            JavaEntityDefinition entityDefinition,
            JavaFieldDefinition field,
            Map<String, TableDefinition> schemaTables,
            List<String> violations
    ) {
        String mappedBy = field.annotationAttribute("ManyToMany", "mappedBy");
        if (mappedBy != null && !mappedBy.isBlank()) {
            return;
        }

        String joinTableName = field.annotationAttribute("JoinTable", "name");
        String joinTableSchema = field.annotationAttribute("JoinTable", "schema");

        if (joinTableName == null || joinTableName.isBlank()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Owning @ManyToMany field '" + field.name()
                            + "' must declare @JoinTable(name=...)"
            );
            return;
        }

        String physicalJoinTableName = buildPhysicalTableName(joinTableSchema, joinTableName);
        TableDefinition joinTableDefinition = schemaTables.get(normalizeName(physicalJoinTableName));

        if (joinTableDefinition == null) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Join table not found in SQL schema for field '"
                            + field.name() + "': " + physicalJoinTableName
            );
            return;
        }

        for (String joinColumnName : extractJoinTableArrayNames(field, "joinColumns")) {
            if (!joinTableDefinition.columns().containsKey(normalizeName(joinColumnName))) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] JoinTable column '" + joinColumnName
                                + "' not found for field '" + field.name()
                                + "' in join table '" + physicalJoinTableName + "'"
                );
            }
        }

        for (String inverseJoinColumnName : extractJoinTableArrayNames(field, "inverseJoinColumns")) {
            if (!joinTableDefinition.columns().containsKey(normalizeName(inverseJoinColumnName))) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] JoinTable inverse column '" + inverseJoinColumnName
                                + "' not found for field '" + field.name()
                                + "' in join table '" + physicalJoinTableName + "'"
                );
            }
        }

        List<String> allJoinColumns = new ArrayList<>();
        allJoinColumns.addAll(extractJoinTableArrayNames(field, "joinColumns"));
        allJoinColumns.addAll(extractJoinTableArrayNames(field, "inverseJoinColumns"));

        for (String joinColumnName : allJoinColumns) {
            ForeignKeyDefinition foreignKeyDefinition = findForeignKeyBySourceColumn(joinTableDefinition, joinColumnName);
            if (foreignKeyDefinition == null) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] Join table '" + physicalJoinTableName
                                + "' column '" + joinColumnName + "' used by field '" + field.name()
                                + "' is not backed by a foreign key"
                );
            }
        }
    }

    /**
     * Validates that every local foreign key is represented by a relation field.
     *
     * @param entityDefinition parsed entity definition
     * @param tableDefinition parsed SQL table definition
     * @param schemaTables parsed schema tables
     * @param entityBySimpleName parsed entities by simple name
     * @param violations collected violations
     */
    private void validateForeignKeyCoverage(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        Set<String> relationJoinColumns = entityDefinition.fields().stream()
                .filter(field -> field.hasAnnotation("ManyToOne") || field.hasAnnotation("OneToOne"))
                .flatMap(field -> resolveJoinColumnNames(field).stream())
                .map(this::normalizeName)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for (ForeignKeyDefinition foreignKeyDefinition : tableDefinition.foreignKeys()) {
            String normalizedSourceColumn = normalizeName(foreignKeyDefinition.sourceColumn());

            if (relationJoinColumns.contains(normalizedSourceColumn)) {
                continue;
            }

            TableDefinition targetTableDefinition = resolveTableDefinitionByPhysicalOrUnqualifiedName(
                    schemaTables,
                    foreignKeyDefinition.targetTable()
            );

            if (targetTableDefinition == null) {
                continue;
            }

            boolean hasMatchingScalarField = entityDefinition.fields().stream()
                    .filter(field -> !field.hasAnnotation("Transient"))
                    .filter(field -> !field.hasAnnotation("ManyToOne"))
                    .filter(field -> !field.hasAnnotation("OneToOne"))
                    .filter(field -> !field.hasAnnotation("OneToMany"))
                    .filter(field -> !field.hasAnnotation("ManyToMany"))
                    .anyMatch(field -> normalizeName(resolveColumnName(field)).equals(normalizedSourceColumn));

            if (hasMatchingScalarField) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] Local foreign key column '"
                                + foreignKeyDefinition.sourceColumn()
                                + "' in table '" + tableDefinition.fullName()
                                + "' is mapped as scalar field instead of relation"
                );
            } else {
                violations.add(
                        "[" + entityDefinition.displayName() + "] Missing relation field for local foreign key column '"
                                + foreignKeyDefinition.sourceColumn()
                                + "' in table '" + tableDefinition.fullName() + "'"
                );
            }
        }
    }

    /**
     * Validates that an entity does not declare duplicate Java field names.
     *
     * @param entityDefinition parsed entity definition
     * @param violations collected violations
     */
    private void validateDuplicateFieldNames(
            JavaEntityDefinition entityDefinition,
            List<String> violations
    ) {
        Map<String, Long> fieldNameCounts = entityDefinition.fields().stream()
                .collect(Collectors.groupingBy(
                        JavaFieldDefinition::name,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        fieldNameCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> violations.add(
                        "[" + entityDefinition.displayName() + "] Duplicate Java field name detected: '"
                                + entry.getKey() + "'"
                ));
    }

    /**
     * Validates that table columns are covered by fields or join columns.
     *
     * @param entityDefinition   parsed entity definition
     * @param tableDefinition    parsed SQL table definition
     * @param entityBySimpleName parsed entities by simple name
     * @param violations         collected violations
     */
    private void validateMissingTableColumns(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        Set<String> mappedColumns = new TreeSet<>();

        for (JavaFieldDefinition field : entityDefinition.fields()) {
            if (field.hasAnnotation("Transient")) {
                continue;
            }

            if (field.hasAnnotation("EmbeddedId")) {
                JavaEntityDefinition embeddedIdDefinition = resolveEmbeddedIdDefinition(field, entityBySimpleName);

                if (embeddedIdDefinition != null) {
                    for (JavaFieldDefinition embeddedField : embeddedIdDefinition.fields()) {
                        if (embeddedField.hasAnnotation("Transient")) {
                            continue;
                        }

                        mappedColumns.add(normalizeName(resolveColumnName(embeddedField)));
                    }
                }

                continue;
            }

            if (field.hasAnnotation("Id")) {
                mappedColumns.add(normalizeName(resolveColumnName(field)));
                continue;
            }

            if (field.hasAnnotation("ManyToOne") || field.hasAnnotation("OneToOne")) {
                mappedColumns.addAll(
                        resolveJoinColumnNames(field).stream()
                                .map(this::normalizeName)
                                .collect(Collectors.toSet())
                );
                continue;
            }

            if (field.hasAnnotation("OneToMany") || field.hasAnnotation("ManyToMany")) {
                continue;
            }

            if (entityBySimpleName.containsKey(simpleTypeName(field.type()))) {
                continue;
            }

            mappedColumns.add(normalizeName(resolveColumnName(field)));
        }

        List<String> unmappedNonAuditColumns = tableDefinition.columns().keySet().stream()
                .filter(columnName -> !mappedColumns.contains(columnName))
                .filter(columnName -> !looksLikeAuditOnlyColumn(columnName))
                .sorted()
                .toList();

        if (!unmappedNonAuditColumns.isEmpty()) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Unmapped DB columns in table '"
                            + tableDefinition.fullName() + "': " + unmappedNonAuditColumns
            );
        }
    }

    /**
     * Parses the SQL schema and extracts CREATE TABLE blocks with columns and foreign keys.
     *
     * @param sql raw SQL schema text
     * @return parsed tables keyed by normalized full name
     */
    private Map<String, TableDefinition> parseSchema(String sql) {
        Map<String, TableDefinition> tables = new LinkedHashMap<>();

        Pattern createTablePattern = Pattern.compile(
                "(?is)CREATE\\s+TABLE\\s+([\\w.\"]+)\\s*\\((.*?)\\)\\s*;",
                Pattern.DOTALL
        );

        Matcher matcher = createTablePattern.matcher(sql);
        while (matcher.find()) {
            String rawTableName = sanitizeIdentifier(matcher.group(1));
            String tableBody = matcher.group(2);

            TableDefinition tableDefinition = parseTableBlock(rawTableName, tableBody);
            tables.put(normalizeName(rawTableName), tableDefinition);
        }

        return tables;
    }

    /**
     * Parses one CREATE TABLE block.
     *
     * @param tableName physical table name
     * @param tableBody table body
     * @return parsed table definition
     */
    private TableDefinition parseTableBlock(String tableName, String tableBody) {
        Map<String, ColumnDefinition> columns = new LinkedHashMap<>();
        List<ForeignKeyDefinition> foreignKeys = new ArrayList<>();
        List<String> segments = splitTopLevelSqlSegments(tableBody);

        for (String segment : segments) {
            String trimmed = segment.trim();
            if (trimmed.isBlank()) {
                continue;
            }

            if (isForeignKeyConstraint(trimmed)) {
                ForeignKeyDefinition foreignKeyDefinition = parseForeignKeyConstraint(tableName, trimmed);
                if (foreignKeyDefinition != null) {
                    foreignKeys.add(foreignKeyDefinition);
                }
                continue;
            }

            if (isTableConstraint(trimmed)) {
                continue;
            }

            ColumnDefinition columnDefinition = parseColumnDefinition(trimmed);
            if (columnDefinition != null) {
                columns.put(normalizeName(columnDefinition.name()), columnDefinition);

                ForeignKeyDefinition inlineForeignKeyDefinition =
                        parseInlineForeignKeyConstraint(tableName, columnDefinition.name(), trimmed);

                if (inlineForeignKeyDefinition != null) {
                    foreignKeys.add(inlineForeignKeyDefinition);
                }
            }
        }

        return new TableDefinition(tableName, columns, foreignKeys);
    }

    /**
     * Parses an inline column-level foreign key declaration.
     *
     * <p>Example:
     * {@code customer_id uuid NOT NULL REFERENCES pep_schema.customer(id)}
     *
     * @param sourceTableName source table name
     * @param sourceColumnName source column name
     * @param sqlSegment full column SQL segment
     * @return parsed foreign key definition or null
     */
    private ForeignKeyDefinition parseInlineForeignKeyConstraint(
            String sourceTableName,
            String sourceColumnName,
            String sqlSegment
    ) {
        Pattern pattern = Pattern.compile(
                "(?is)\\bREFERENCES\\s+([\\w.\"]+)\\s*\\(([^)]+)\\)"
        );

        Matcher matcher = pattern.matcher(sqlSegment);
        if (!matcher.find()) {
            return null;
        }

        String targetTableName = sanitizeIdentifier(matcher.group(1));
        String targetColumnName = sanitizeIdentifier(matcher.group(2));

        return new ForeignKeyDefinition(
                sanitizeIdentifier(sourceTableName),
                sanitizeIdentifier(sourceColumnName),
                targetTableName,
                targetColumnName
        );
    }


    private ColumnDefinition parseColumnDefinition(String sqlSegment) {
        Matcher matcher = Pattern.compile("^\\s*(\"[^\"]+\"|[a-zA-Z_][\\w$]*)\\s+(.+)$", Pattern.DOTALL).matcher(sqlSegment);

        if (!matcher.find()) {
            return null;
        }

        String rawColumnName = matcher.group(1);
        String definitionWithoutName = matcher.group(2).trim();

        String sqlType = extractSqlType(definitionWithoutName);

        return new ColumnDefinition(
                sanitizeIdentifier(rawColumnName),
                normalizeSqlType(sqlType),
                extractLength(sqlType),
                extractPrecision(sqlType),
                extractScale(sqlType),
                !containsNotNull(definitionWithoutName),
                containsPrimaryKey(definitionWithoutName),
                containsUnique(definitionWithoutName),
                extractDefaultValue(definitionWithoutName),
                extractCheckConstraint(definitionWithoutName)
        );
    }

    private String extractSqlType(String definitionWithoutName) {
        String value = definitionWithoutName.trim();
        String upper = value.toUpperCase(Locale.ROOT);

        int cutIndex = value.length();

        for (String marker : List.of(" NOT NULL", " NULL", " DEFAULT ", " CONSTRAINT ", " PRIMARY KEY", " UNIQUE", " CHECK ", " REFERENCES ")) {
            int currentIndex = upper.indexOf(marker);
            if (currentIndex >= 0 && currentIndex < cutIndex) {
                cutIndex = currentIndex;
            }
        }

        return value.substring(0, cutIndex).trim();
    }

    private String normalizeSqlType(String sqlType) {
        if (sqlType == null) {
            return null;
        }

        return sqlType.trim()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }

    private Integer extractLength(String sqlType) {
        if (sqlType == null) {
            return null;
        }

        Matcher matcher = Pattern.compile("(?i)^(?:character varying|varchar|char|character)\\((\\d+)\\)").matcher(sqlType);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
    }

    private Integer extractPrecision(String sqlType) {
        if (sqlType == null) {
            return null;
        }

        Matcher matcher = Pattern.compile("(?i)^(?:numeric|decimal)\\((\\d+)(?:\\s*,\\s*(\\d+))?\\)").matcher(sqlType);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : null;
    }

    private Integer extractScale(String sqlType) {
        if (sqlType == null) {
            return null;
        }

        Matcher matcher = Pattern.compile("(?i)^(?:numeric|decimal)\\((\\d+)\\s*,\\s*(\\d+)\\)").matcher(sqlType);
        return matcher.find() ? Integer.parseInt(matcher.group(2)) : null;
    }

    private boolean containsNotNull(String value) {
        return value.toUpperCase(Locale.ROOT).contains("NOT NULL");
    }

    private boolean containsPrimaryKey(String value) {
        return value.toUpperCase(Locale.ROOT).contains("PRIMARY KEY");
    }

    private boolean containsUnique(String value) {
        return value.toUpperCase(Locale.ROOT).contains("UNIQUE");
    }

    private String extractDefaultValue(String value) {
        Matcher matcher = Pattern.compile("(?i)\\bDEFAULT\\s+(.+?)(?=\\s+NOT\\s+NULL|\\s+NULL|\\s+CONSTRAINT|\\s+PRIMARY\\s+KEY|\\s+UNIQUE|\\s+CHECK|\\s+REFERENCES|$)").matcher(value);
        if (!matcher.find()) {
            return null;
        }

        return matcher.group(1).trim();
    }

    private String extractCheckConstraint(String value) {
        Matcher matcher = Pattern.compile("(?i)\\bCHECK\\s*\\((.*)\\)").matcher(value);
        if (!matcher.find()) {
            return null;
        }

        return "(" + matcher.group(1).trim() + ")";
    }



    /**
     * Returns true when the SQL segment is a foreign key constraint.
     *
     * @param sqlSegment SQL segment
     * @return true when foreign key constraint
     */
    private boolean isForeignKeyConstraint(String sqlSegment) {
        String normalized = sqlSegment.trim().toUpperCase(Locale.ROOT);

        return normalized.startsWith("FOREIGN KEY")
                || normalized.startsWith("CONSTRAINT ") && normalized.contains(" FOREIGN KEY ");
    }

    /**
     * Parses a foreign key table constraint.
     *
     * @param sourceTableName source table name
     * @param sqlSegment      SQL table-constraint segment
     * @return parsed foreign key definition or null
     */
    private ForeignKeyDefinition parseForeignKeyConstraint(String sourceTableName, String sqlSegment) {
        Pattern pattern = Pattern.compile(
                "(?is)(?:CONSTRAINT\\s+[^\\s]+\\s+)?FOREIGN\\s+KEY\\s*\\(([^)]+)\\)\\s+REFERENCES\\s+([\\w.\"]+)\\s*\\(([^)]+)\\)"
        );

        Matcher matcher = pattern.matcher(sqlSegment);
        if (!matcher.find()) {
            return null;
        }

        String sourceColumnsGroup = matcher.group(1);
        String targetTableGroup = sanitizeIdentifier(matcher.group(2));
        String targetColumnsGroup = matcher.group(3);

        List<String> sourceColumns = splitIdentifierList(sourceColumnsGroup);
        List<String> targetColumns = splitIdentifierList(targetColumnsGroup);

        if (sourceColumns.size() != 1 || targetColumns.size() != 1) {
            return null;
        }

        return new ForeignKeyDefinition(
                sanitizeIdentifier(sourceTableName),
                sanitizeIdentifier(sourceColumns.get(0)),
                sanitizeIdentifier(targetTableGroup),
                sanitizeIdentifier(targetColumns.get(0))
        );
    }

    /**
     * Splits a comma-separated identifier list.
     *
     * @param value raw identifier list
     * @return identifiers
     */
    private List<String> splitIdentifierList(String value) {
        return splitTopLevelByComma(value).stream()
                .map(String::trim)
                .map(this::sanitizeIdentifier)
                .filter(identifier -> identifier != null && !identifier.isBlank())
                .toList();
    }

    /**
     * Returns true when the SQL segment is a table-level constraint.
     *
     * @param sqlSegment SQL segment
     * @return true when table-level constraint
     */
    private boolean isTableConstraint(String sqlSegment) {
        String normalized = sqlSegment.trim().toUpperCase(Locale.ROOT);

        return normalized.startsWith("CONSTRAINT ")
                || normalized.startsWith("PRIMARY KEY")
                || normalized.startsWith("FOREIGN KEY")
                || normalized.startsWith("UNIQUE")
                || normalized.startsWith("CHECK")
                || normalized.startsWith("EXCLUDE ");
    }

    /**
     * Splits a CREATE TABLE body into top-level comma-separated segments.
     *
     * @param sqlBlock SQL block
     * @return top-level segments
     */
    private List<String> splitTopLevelSqlSegments(String sqlBlock) {
        List<String> segments = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int parenthesesDepth = 0;
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;

        for (int index = 0; index < sqlBlock.length(); index++) {
            char currentChar = sqlBlock.charAt(index);

            if (currentChar == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
            } else if (currentChar == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
            } else if (!inSingleQuote && !inDoubleQuote) {
                if (currentChar == '(') {
                    parenthesesDepth++;
                } else if (currentChar == ')') {
                    parenthesesDepth--;
                } else if (currentChar == ',' && parenthesesDepth == 0) {
                    segments.add(current.toString());
                    current.setLength(0);
                    continue;
                }
            }

            current.append(currentChar);
        }

        if (!current.isEmpty()) {
            segments.add(current.toString());
        }

        return segments;
    }

    /**
     * Parses Java fields from a source file.
     *
     * @param content Java source content
     * @return parsed field definitions
     */
    private List<JavaFieldDefinition> parseJavaFields(String content) {
        List<JavaFieldDefinition> fieldDefinitions = new ArrayList<>();

        int classBodyStart = content.indexOf('{');
        int classBodyEnd = content.lastIndexOf('}');
        if (classBodyStart < 0 || classBodyEnd <= classBodyStart) {
            return fieldDefinitions;
        }

        String classBody = content.substring(classBodyStart + 1, classBodyEnd);
        List<String> lines = classBody.lines().toList();

        List<String> pendingAnnotations = new ArrayList<>();
        StringBuilder currentAnnotation = null;
        int annotationParenthesesDepth = 0;

        for (String rawLine : lines) {
            String line = rawLine.trim();

            if (line.isBlank()) {
                continue;
            }

            if (currentAnnotation != null) {
                currentAnnotation.append(' ').append(line);
                annotationParenthesesDepth += countOccurrences(line, '(');
                annotationParenthesesDepth -= countOccurrences(line, ')');

                if (annotationParenthesesDepth <= 0) {
                    pendingAnnotations.add(currentAnnotation.toString().trim());
                    currentAnnotation = null;
                    annotationParenthesesDepth = 0;
                }

                continue;
            }

            if (line.startsWith("@")) {
                currentAnnotation = new StringBuilder(line);
                annotationParenthesesDepth = countOccurrences(line, '(') - countOccurrences(line, ')');

                if (annotationParenthesesDepth <= 0) {
                    pendingAnnotations.add(currentAnnotation.toString().trim());
                    currentAnnotation = null;
                    annotationParenthesesDepth = 0;
                }

                continue;
            }

            if (looksLikeFieldDeclaration(line)) {
                String fieldType = extractFieldType(line);
                String fieldName = extractFieldName(line);

                if (fieldType != null && fieldName != null) {
                    fieldDefinitions.add(new JavaFieldDefinition(
                            fieldName,
                            fieldType,
                            parseAnnotations(pendingAnnotations)
                    ));
                }

                pendingAnnotations = new ArrayList<>();
                continue;
            }

            if (line.contains("(")
                    || line.startsWith("public ")
                    || line.startsWith("protected ")
                    || line.startsWith("private ")) {
                pendingAnnotations = new ArrayList<>();
                currentAnnotation = null;
                annotationParenthesesDepth = 0;
            }
        }

        return fieldDefinitions;
    }

    /**
     * Counts how many times a character appears in a string.
     *
     * @param value  source text
     * @param target target character
     * @return occurrence count
     */
    private int countOccurrences(String value, char target) {
        int count = 0;

        for (int index = 0; index < value.length(); index++) {
            if (value.charAt(index) == target) {
                count++;
            }
        }

        return count;
    }

    /**
     * Parses annotation lines into annotation definitions.
     *
     * @param annotationLines raw annotation lines
     * @return parsed annotations
     */
    private List<AnnotationDefinition> parseAnnotations(List<String> annotationLines) {
        List<AnnotationDefinition> annotations = new ArrayList<>();

        for (String annotationLine : annotationLines) {
            String trimmed = annotationLine.trim();

            Matcher matcher = Pattern.compile("^@([A-Za-z0-9_]+)(\\((.*)\\))?$").matcher(trimmed);
            if (!matcher.find()) {
                continue;
            }

            String annotationName = matcher.group(1);
            String rawArguments = matcher.group(3);

            Map<String, String> attributes = parseAnnotationAttributes(rawArguments);

            annotations.add(new AnnotationDefinition(annotationName, attributes, rawArguments == null ? "" : rawArguments));
        }

        return annotations;
    }

    /**
     * Parses named annotation attributes.
     *
     * @param rawArguments raw annotation arguments
     * @return parsed attributes
     */
    private Map<String, String> parseAnnotationAttributes(String rawArguments) {
        Map<String, String> attributes = new LinkedHashMap<>();

        if (rawArguments == null || rawArguments.isBlank()) {
            return attributes;
        }

        List<String> segments = splitTopLevelByComma(rawArguments);

        for (String segment : segments) {
            String trimmed = segment.trim();

            int equalsIndex = findTopLevelEquals(trimmed);
            if (equalsIndex < 0) {
                attributes.put("value", unquote(trimmed));
                continue;
            }

            String name = trimmed.substring(0, equalsIndex).trim();
            String value = trimmed.substring(equalsIndex + 1).trim();
            attributes.put(name, unquote(value));
        }

        return attributes;
    }

    /**
     * Splits a string by top-level commas.
     *
     * @param value raw value
     * @return segments
     */
    private List<String> splitTopLevelByComma(String value) {
        List<String> segments = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int parenthesesDepth = 0;
        int bracesDepth = 0;
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;

        for (int index = 0; index < value.length(); index++) {
            char currentChar = value.charAt(index);

            if (currentChar == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
            } else if (currentChar == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
            } else if (!inSingleQuote && !inDoubleQuote) {
                if (currentChar == '(') {
                    parenthesesDepth++;
                } else if (currentChar == ')') {
                    parenthesesDepth--;
                } else if (currentChar == '{') {
                    bracesDepth++;
                } else if (currentChar == '}') {
                    bracesDepth--;
                } else if (currentChar == ',' && parenthesesDepth == 0 && bracesDepth == 0) {
                    segments.add(current.toString());
                    current.setLength(0);
                    continue;
                }
            }

            current.append(currentChar);
        }

        if (!current.isEmpty()) {
            segments.add(current.toString());
        }

        return segments;
    }

    /**
     * Finds the first top-level equals sign.
     *
     * @param value raw value
     * @return equals index or -1
     */
    private int findTopLevelEquals(String value) {
        int parenthesesDepth = 0;
        int bracesDepth = 0;
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;

        for (int index = 0; index < value.length(); index++) {
            char currentChar = value.charAt(index);

            if (currentChar == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
            } else if (currentChar == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
            } else if (!inSingleQuote && !inDoubleQuote) {
                if (currentChar == '(') {
                    parenthesesDepth++;
                } else if (currentChar == ')') {
                    parenthesesDepth--;
                } else if (currentChar == '{') {
                    bracesDepth++;
                } else if (currentChar == '}') {
                    bracesDepth--;
                } else if (currentChar == '=' && parenthesesDepth == 0 && bracesDepth == 0) {
                    return index;
                }
            }
        }

        return -1;
    }

    /**
     * Extracts the Java package name.
     *
     * @param content Java source content
     * @return package name or null
     */
    private String extractPackageName(String content) {
        Matcher matcher = Pattern.compile("\\bpackage\\s+([\\w.]+)\\s*;").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Extracts the Java class name.
     *
     * @param content Java source content
     * @return class name or null
     */
    private String extractClassName(String content) {
        Matcher matcher = Pattern.compile("\\bclass\\s+([A-Za-z_][A-Za-z0-9_]*)\\b").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Extracts the table name from @Table.
     *
     * @param content Java source content
     * @return table name or null
     */
    private String extractTableName(String content) {
        Matcher matcher = Pattern.compile("@Table\\s*\\((.*?)\\)", Pattern.DOTALL).matcher(content);
        if (!matcher.find()) {
            return null;
        }

        String arguments = matcher.group(1);
        Matcher nameMatcher = Pattern.compile("\\bname\\s*=\\s*\"([^\"]+)\"").matcher(arguments);
        return nameMatcher.find() ? nameMatcher.group(1) : null;
    }

    /**
     * Extracts the table schema from @Table.
     *
     * @param content Java source content
     * @return schema or null
     */
    private String extractTableSchema(String content) {
        Matcher matcher = Pattern.compile("@Table\\s*\\((.*?)\\)", Pattern.DOTALL).matcher(content);
        if (!matcher.find()) {
            return null;
        }

        String arguments = matcher.group(1);
        Matcher schemaMatcher = Pattern.compile("\\bschema\\s*=\\s*\"([^\"]+)\"").matcher(arguments);
        return schemaMatcher.find() ? schemaMatcher.group(1) : null;
    }

    /**
     * Returns true when a line looks like a Java field declaration.
     *
     * @param line raw line
     * @return true when field declaration
     */
    private boolean looksLikeFieldDeclaration(String line) {
        return line.endsWith(";")
                && (line.startsWith("private ") || line.startsWith("protected ") || line.startsWith("public "))
                && !line.contains("(");
    }

    /**
     * Extracts the type from a field declaration.
     *
     * @param line field declaration
     * @return field type or null
     */
    private String extractFieldType(String line) {
        String normalized = line.replace(";", "").trim();
        String[] parts = normalized.split("\\s+");

        if (parts.length < 3) {
            return null;
        }

        int index = 0;
        while (index < parts.length && isFieldModifier(parts[index])) {
            index++;
        }

        if (index >= parts.length - 1) {
            return null;
        }

        return parts[index];
    }

    /**
     * Extracts the field name from a field declaration.
     *
     * @param line field declaration
     * @return field name or null
     */
    private String extractFieldName(String line) {
        String normalized = line.replace(";", "").trim();
        String[] parts = normalized.split("\\s+");

        if (parts.length < 2) {
            return null;
        }

        return parts[parts.length - 1];
    }

    /**
     * Returns true when the token is a field modifier.
     *
     * @param token raw token
     * @return true when modifier
     */
    private boolean isFieldModifier(String token) {
        return token.equals("private")
                || token.equals("protected")
                || token.equals("public")
                || token.equals("final")
                || token.equals("static")
                || token.equals("transient")
                || token.equals("volatile");
    }

    /**
     * Resolves the expected SQL column name for a field.
     *
     * @param field parsed field
     * @return physical column name
     */
    private String resolveColumnName(JavaFieldDefinition field) {
        String declaredColumnName = field.annotationAttribute("Column", "name");

        if (declaredColumnName != null && !declaredColumnName.isBlank()) {
            return sanitizeIdentifier(declaredColumnName.replace("\"", "").trim());
        }

        return toSnakeCase(field.name());
    }

    /**
     * Resolves join column names from field annotations.
     *
     * @param field parsed field
     * @return join column names
     */
    private List<String> resolveJoinColumnNames(JavaFieldDefinition field) {
        List<String> names = new ArrayList<>();

        String directJoinColumnName = field.annotationAttribute("JoinColumn", "name");
        if (directJoinColumnName != null && !directJoinColumnName.isBlank()) {
            names.add(sanitizeIdentifier(directJoinColumnName));
        }

        String rawJoinColumns = field.annotationRawArguments("JoinColumns");
        if (rawJoinColumns != null && !rawJoinColumns.isBlank()) {
            Matcher matcher = Pattern.compile("name\\s*=\\s*\"([^\"]+)\"").matcher(rawJoinColumns);
            while (matcher.find()) {
                names.add(sanitizeIdentifier(matcher.group(1)));
            }
        }

        return names.stream().distinct().toList();
    }

    /**
     * Extracts join column names from JoinTable array attributes.
     *
     * @param field         parsed field
     * @param attributeName array attribute name
     * @return join column names
     */
    private List<String> extractJoinTableArrayNames(JavaFieldDefinition field, String attributeName) {
        String rawJoinTableArguments = field.annotationRawArguments("JoinTable");
        if (rawJoinTableArguments == null || rawJoinTableArguments.isBlank()) {
            return List.of();
        }

        String singleJoinColumnName = extractSingleJoinTableColumnName(rawJoinTableArguments, attributeName);
        if (singleJoinColumnName != null) {
            return List.of(singleJoinColumnName);
        }

        Matcher sectionMatcher = Pattern.compile(attributeName + "\\s*=\\s*\\{(.*?)\\}", Pattern.DOTALL).matcher(rawJoinTableArguments);
        if (!sectionMatcher.find()) {
            return List.of();
        }

        String section = sectionMatcher.group(1);
        Matcher nameMatcher = Pattern.compile("name\\s*=\\s*\"([^\"]+)\"").matcher(section);

        List<String> names = new ArrayList<>();
        while (nameMatcher.find()) {
            names.add(sanitizeIdentifier(nameMatcher.group(1)));
        }

        return names;
    }

    /**
     * Extracts a single JoinColumn reference from a JoinTable attribute.
     *
     * @param rawJoinTableArguments raw JoinTable arguments
     * @param attributeName         attribute name
     * @return join column name or null
     */
    private String extractSingleJoinTableColumnName(String rawJoinTableArguments, String attributeName) {
        Pattern pattern = Pattern.compile(attributeName + "\\s*=\\s*@JoinColumn\\s*\\((.*?)\\)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(rawJoinTableArguments);

        if (!matcher.find()) {
            return null;
        }

        String joinColumnArguments = matcher.group(1);
        Matcher nameMatcher = Pattern.compile("name\\s*=\\s*\"([^\"]+)\"").matcher(joinColumnArguments);

        if (!nameMatcher.find()) {
            return null;
        }

        return sanitizeIdentifier(nameMatcher.group(1));
    }

    /**
     * Returns true when a field should be skipped from simple column validation.
     *
     * @param field              parsed field
     * @param entityBySimpleName parsed entities by simple name
     * @return true when field should be skipped
     */
    private boolean shouldSkipSimpleColumnValidation(
            JavaFieldDefinition field,
            Map<String, JavaEntityDefinition> entityBySimpleName
    ) {
        return field.hasAnnotation("Transient")
                || field.hasAnnotation("OneToMany")
                || field.hasAnnotation("ManyToMany")
                || field.hasAnnotation("ManyToOne")
                || field.hasAnnotation("OneToOne")
                || entityBySimpleName.containsKey(simpleTypeName(field.type()));
    }

    /**
     * Resolves the generic type from a collection field type.
     *
     * @param fieldType raw field type
     * @return generic type or null
     */
    private String resolveCollectionGenericType(String fieldType) {
        Matcher matcher = Pattern.compile("<\\s*([A-Za-z_][A-Za-z0-9_$.]*)\\s*>").matcher(fieldType);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Returns the simple type name.
     *
     * @param fieldType raw field type
     * @return simple type name
     */
    private String simpleTypeName(String fieldType) {
        String normalized = fieldType.trim();

        if (normalized.contains("<")) {
            normalized = normalized.substring(0, normalized.indexOf('<'));
        }

        if (normalized.contains(".")) {
            return normalized.substring(normalized.lastIndexOf('.') + 1);
        }

        return normalized;
    }

    /**
     * Builds a physical table name from schema and table name.
     *
     * @param schema    schema name
     * @param tableName table name
     * @return full physical table name
     */
    private String buildPhysicalTableName(String schema, String tableName) {
        String sanitizedTableName = sanitizeIdentifier(tableName);

        if (schema == null || schema.isBlank()) {
            return sanitizedTableName;
        }

        return sanitizeIdentifier(schema) + "." + sanitizedTableName;
    }

    /**
     * Converts camelCase to snake_case.
     *
     * @param value Java field name
     * @return snake_case
     */
    private String toSnakeCase(String value) {
        return value
                .replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .toLowerCase(Locale.ROOT);
    }


    /**
     * Removes comments from Java source code.
     *
     * @param content raw Java source
     * @return source without comments
     */
    private String stripComments(String content) {
        String withoutBlockComments = content.replaceAll("(?s)/\\*.*?\\*/", "");
        return withoutBlockComments.replaceAll("(?m)//.*$", "");
    }

    /**
     * Removes surrounding quotes when present.
     *
     * @param value raw value
     * @return unquoted value
     */
    private String unquote(String value) {
        String trimmed = value.trim();

        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }

        return trimmed;
    }

    /**
     * Removes surrounding quotes from identifiers.
     *
     * @param identifier raw identifier
     * @return sanitized identifier
     */
    private String sanitizeIdentifier(String identifier) {
        if (identifier == null) {
            return null;
        }

        String trimmed = identifier.trim();
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }

        return trimmed;
    }

    /**
     * Normalizes names for tolerant comparisons.
     *
     * @param name raw identifier
     * @return normalized identifier
     */
    private String normalizeName(String name) {
        return sanitizeIdentifier(name).toLowerCase(Locale.ROOT);
    }

    /**
     * Returns true for common audit-only columns.
     *
     * @param columnName physical column name
     * @return true when column can be ignored
     */
    private boolean looksLikeAuditOnlyColumn(String columnName) {
        String normalized = normalizeName(columnName);
        return normalized.endsWith("_aud")
                || normalized.equals("rev")
                || normalized.equals("revtype");
    }

    /**
     * Finds a foreign key definition by source column in a table.
     *
     * @param tableDefinition table definition
     * @param sourceColumn    source column
     * @return foreign key definition or null
     */
    private ForeignKeyDefinition findForeignKeyBySourceColumn(TableDefinition tableDefinition, String sourceColumn) {
        String normalizedSourceColumn = normalizeName(sourceColumn);

        return tableDefinition.foreignKeys().stream()
                .filter(foreignKey -> normalizeName(foreignKey.sourceColumn()).equals(normalizedSourceColumn))
                .findFirst()
                .orElse(null);
    }

    /**
     * Validates relation target entity when the foreign-key target table can be resolved locally.
     * External references to tables outside the parsed schema are intentionally ignored.
     *
     * @param entityDefinition     owning entity definition
     * @param field                relation field
     * @param foreignKeyDefinition foreign key definition
     * @param schemaTables         parsed schema tables
     * @param entityBySimpleName   parsed entities by simple name
     * @param violations           collected violations
     */
    private void validateRelationTargetEntityWhenResolvable(
            JavaEntityDefinition entityDefinition,
            JavaFieldDefinition field,
            ForeignKeyDefinition foreignKeyDefinition,
            Map<String, TableDefinition> schemaTables,
            Map<String, JavaEntityDefinition> entityBySimpleName,
            List<String> violations
    ) {
        TableDefinition targetTableDefinition = resolveTableDefinitionByPhysicalOrUnqualifiedName(
                schemaTables,
                foreignKeyDefinition.targetTable()
        );

        if (targetTableDefinition == null) {
            return;
        }

        String expectedEntitySimpleName = toPascalCase(extractUnqualifiedTableName(targetTableDefinition.fullName()));
        String actualFieldTypeSimpleName = simpleTypeName(field.type());

        if (!actualFieldTypeSimpleName.equals(expectedEntitySimpleName)) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Relation field '" + field.name()
                            + "' points to entity type '" + actualFieldTypeSimpleName
                            + "' but foreign key targets table '" + targetTableDefinition.fullName()
                            + "' which resolves to expected entity '" + expectedEntitySimpleName + "'"
            );
        }

        String referencedColumnName = field.annotationAttribute("JoinColumn", "referencedColumnName");
        if (referencedColumnName != null && !referencedColumnName.isBlank()) {
            if (!normalizeName(referencedColumnName).equals(normalizeName(foreignKeyDefinition.targetColumn()))) {
                violations.add(
                        "[" + entityDefinition.displayName() + "] Relation field '" + field.name()
                                + "' declares referencedColumnName='" + referencedColumnName
                                + "' but foreign key points to '" + foreignKeyDefinition.targetColumn() + "'"
                );
            }
        }

        JavaEntityDefinition targetEntityDefinition = entityBySimpleName.get(expectedEntitySimpleName);
        if (targetEntityDefinition == null) {
            return;
        }

        boolean targetHasIdentifierCoverage = targetEntityDefinition.fields().stream()
                .anyMatch(targetField ->
                        targetField.hasAnnotation("Id")
                                || targetField.hasAnnotation("EmbeddedId")
                                || normalizeName(resolveColumnName(targetField)).equals(normalizeName(foreignKeyDefinition.targetColumn()))
                );

        if (!targetHasIdentifierCoverage) {
            violations.add(
                    "[" + entityDefinition.displayName() + "] Relation field '" + field.name()
                            + "' points to entity '" + targetEntityDefinition.displayName()
                            + "' but target column '" + foreignKeyDefinition.targetColumn()
                            + "' could not be matched on the target entity"
            );
        }
    }

    /**
     * Resolves a table definition using a physical table name or unqualified name.
     *
     * @param schemaTables                 parsed schema tables
     * @param physicalOrQualifiedTableName target table name
     * @return matching table definition or null
     */
    private TableDefinition resolveTableDefinitionByPhysicalOrUnqualifiedName(
            Map<String, TableDefinition> schemaTables,
            String physicalOrQualifiedTableName
    ) {
        if (physicalOrQualifiedTableName == null || physicalOrQualifiedTableName.isBlank()) {
            return null;
        }

        TableDefinition exactMatch = schemaTables.get(normalizeName(physicalOrQualifiedTableName));
        if (exactMatch != null) {
            return exactMatch;
        }

        String unqualifiedTableName = extractUnqualifiedTableName(physicalOrQualifiedTableName);

        List<TableDefinition> matches = schemaTables.values().stream()
                .filter(table -> normalizeName(extractUnqualifiedTableName(table.fullName()))
                        .equals(normalizeName(unqualifiedTableName)))
                .toList();

        if (matches.size() == 1) {
            return matches.get(0);
        }

        return null;
    }

    /**
     * Converts a physical table name to a PascalCase entity name.
     *
     * @param value physical table name
     * @return PascalCase name
     */
    private String toPascalCase(String value) {
        String normalized = sanitizeIdentifier(value);
        if (normalized == null || normalized.isBlank()) {
            return normalized;
        }

        String[] parts = normalized.split("[_\\s]+");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (part == null || part.isBlank()) {
                continue;
            }

            String lower = part.toLowerCase(Locale.ROOT);
            builder.append(Character.toUpperCase(lower.charAt(0)));

            if (lower.length() > 1) {
                builder.append(lower.substring(1));
            }
        }

        return builder.toString();
    }

    /**
     * Builds the final validation error report.
     *
     * @param violations collected violations
     * @return formatted report
     */
    private String buildViolationReport(List<String> violations) {
        StringBuilder builder = new StringBuilder();

        builder.append(System.lineSeparator());
        builder.append("==================================================").append(System.lineSeparator());
        builder.append("ENTITY / SCHEMA VALIDATION ERRORS").append(System.lineSeparator());
        builder.append("==================================================").append(System.lineSeparator());

        for (int index = 0; index < violations.size(); index++) {
            builder.append(index + 1)
                    .append(". ")
                    .append(violations.get(index))
                    .append(System.lineSeparator());
        }

        builder.append("==================================================").append(System.lineSeparator());
        builder.append("Total errors: ").append(violations.size()).append(System.lineSeparator());
        builder.append("==================================================");

        return builder.toString();
    }

    /**
     * Prints generated entity classes that contain TODO comments.
     *
     * @param entityDefinitions parsed entity definitions
     */
    private void printEntitiesWithTodoComments(List<JavaEntityDefinition> entityDefinitions) {
        List<JavaEntityDefinition> todoEntities = entityDefinitions.stream()
                .filter(JavaEntityDefinition::hasTodoComment)
                .sorted(Comparator.comparing(JavaEntityDefinition::displayName))
                .toList();

        if (todoEntities.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("GENERATED ENTITY CLASSES WITH TODO COMMENTS");
        System.out.println("==================================================");

        for (int index = 0; index < todoEntities.size(); index++) {
            System.out.printf("%3d. %s%n", index + 1, todoEntities.get(index).displayName());
        }

        System.out.println("==================================================");
        System.out.println("Total classes with TODO: " + todoEntities.size());
        System.out.println("==================================================");
        System.out.println();
    }

    /**
     * Parsed SQL table definition.
     *
     * @param fullName    physical table name
     * @param columns     parsed columns
     * @param foreignKeys parsed foreign keys
     */
    private record TableDefinition(
            String fullName,
            Map<String, ColumnDefinition> columns,
            List<ForeignKeyDefinition> foreignKeys
    ) {
    }

    /**
     * Parsed SQL column definition.
     *
     * @param name physical column name
     */
    private record ColumnDefinition(
            String name,
            String sqlType,
            Integer length,
            Integer precision,
            Integer scale,
            boolean nullable,
            boolean primaryKey,
            boolean unique,
            String defaultValue,
            String checkConstraint
    ) {
    }

    /**
     * Parsed SQL foreign key definition.
     *
     * @param sourceTable  source table
     * @param sourceColumn source column
     * @param targetTable  target table
     * @param targetColumn target column
     */
    private record ForeignKeyDefinition(
            String sourceTable,
            String sourceColumn,
            String targetTable,
            String targetColumn
    ) {
    }

    /**
     * Parsed Java entity definition from source code.
     *
     * @param sourceFile         source file path
     * @param packageName        package name
     * @param simpleName         class simple name
     * @param isEntity           whether @Entity exists
     * @param isMappedSuperclass whether @MappedSuperclass exists
     * @param isEmbeddable       whether @Embeddable exists
     * @param hasTodoComment     whether the source file contains TODO
     * @param tableSchema        @Table schema
     * @param tableName          @Table name
     * @param fields             parsed fields
     */
    private record JavaEntityDefinition(
            Path sourceFile,
            String packageName,
            String simpleName,
            boolean isEntity,
            boolean isMappedSuperclass,
            boolean isEmbeddable,
            boolean hasTodoComment,
            String tableSchema,
            String tableName,
            List<JavaFieldDefinition> fields
    ) {
        /**
         * Returns a human-readable entity display name.
         *
         * @return display name
         */
        private String displayName() {
            return packageName == null || packageName.isBlank()
                    ? simpleName
                    : packageName + "." + simpleName;
        }
    }

    /**
     * Prints the validation checks performed by this test.
     */
    private void printValidationChecklist() {
        List<String> checks = List.of(
                "Checks that the schema SQL file exists",
                "Checks that the generated Java root exists",
                "Checks that generated entity files can be parsed without errors",
                "Checks that at least one generated entity source file exists",
                "Scans generated entity source files under the entity package",
                "Parses @Entity, @Embeddable, @Table, fields, and annotations from source files",
                "Prints generated entities that still contain TODO comments",

                "Checks that each entity table exists in the SQL schema",
                "Checks that each entity has @Table(name=...)",
                "Checks that each entity has @Id or @EmbeddedId",

                "Checks for duplicate Java field names in generated entities",

                "Checks simple field-to-column mappings against SQL table columns",
                "Checks embedded id fields against SQL columns",
                "Checks for missing DB columns for mapped fields",
                "Checks for unmapped non-audit SQL columns",

                "Checks Java field types against SQL column types using TypeMapper",

                "Checks SQL NOT NULL against Java (@NotNull / nullable=false / primitives)",
                "Checks SQL UNIQUE against @Column(unique=true)",
                "Checks SQL length against @Column(length)",
                "Checks SQL precision against @Column(precision)",
                "Checks SQL scale against @Column(scale)",
                "Checks @Size(max=...) against SQL varchar/char length",
                "Checks @Digits(integer=..., fraction=...) against SQL numeric precision/scale",

                "Checks presence of SQL default metadata on boolean-like columns",
                "Checks presence of SQL check constraints on string-like columns",

                "Checks @ManyToOne fields for @JoinColumn/@JoinColumns presence",
                "Checks @ManyToOne join columns exist in the owning SQL table",
                "Checks @ManyToOne join columns are backed by a foreign key",

                "Checks @OneToOne owning-side fields for @JoinColumn/@JoinColumns presence",
                "Checks @OneToOne join columns exist in the owning SQL table",
                "Checks @OneToOne join columns are backed by a foreign key",

                "Checks @OneToMany fields define mappedBy",
                "Checks @OneToMany mappedBy points to a real field on the target entity",
                "Checks @OneToMany mappedBy points to a @ManyToOne or @OneToOne owning field",

                "Checks owning @ManyToMany fields define @JoinTable(name=...)",
                "Checks @ManyToMany join table exists in the parsed SQL schema",
                "Checks @ManyToMany joinColumns and inverseJoinColumns exist in the join table",
                "Checks @ManyToMany join table columns are backed by foreign keys",

                "Checks relation target entity type matches foreign-key target table",
                "Checks referencedColumnName matches foreign-key target column",

                "Checks local foreign keys are not mapped as scalar fields",
                "Checks local foreign keys are not missing relation fields",

                "Ignores foreign keys pointing to external tables not present in the parsed schema"
        );

        System.out.println();
        System.out.println("==================================================");
        System.out.println("ENTITY / SCHEMA VALIDATION CHECKLIST");
        System.out.println("==================================================");

        for (int index = 0; index < checks.size(); index++) {
            System.out.printf("%3d. %s%n", index + 1, checks.get(index));
        }

        System.out.println("==================================================");
        System.out.println("Total checks: " + checks.size());
        System.out.println("==================================================");
        System.out.println();
    }


    /**
     * Parsed Java field definition from source code.
     *
     * @param name field name
     * @param type field type
     * @param annotations field annotations
     */
    private record JavaFieldDefinition(
            String name,
            String type,
            List<AnnotationDefinition> annotations
    ) {
        /**
         * Returns true when the field has the given annotation.
         *
         * @param annotationName simple annotation name
         * @return true when present
         */
        private boolean hasAnnotation(String annotationName) {
            return annotations.stream().anyMatch(annotation -> annotation.name().equals(annotationName));
        }

        /**
         * Returns one named annotation attribute when present.
         *
         * @param annotationName annotation name
         * @param attributeName attribute name
         * @return attribute value or null
         */
        private String annotationAttribute(String annotationName, String attributeName) {
            return annotations.stream()
                    .filter(annotation -> annotation.name().equals(annotationName))
                    .map(annotation -> annotation.attributes().get(attributeName))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
        }

        /**
         * Returns the raw annotation arguments when present.
         *
         * @param annotationName annotation name
         * @return raw arguments or null
         */
        private String annotationRawArguments(String annotationName) {
            return annotations.stream()
                    .filter(annotation -> annotation.name().equals(annotationName))
                    .map(AnnotationDefinition::rawArguments)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * Parsed annotation definition from source code.
     *
     * @param name annotation simple name
     * @param attributes parsed attributes
     * @param rawArguments raw arguments
     */
    private record AnnotationDefinition(
            String name,
            Map<String, String> attributes,
            String rawArguments
    ) {
    }





    private JavaFieldDefinition findJavaFieldForColumn(
            JavaEntityDefinition javaEntityDefinition,
            ColumnDefinition columnDefinition
    ) {
        String normalizedColumnName = normalizeName(columnDefinition.name());

        return javaEntityDefinition.fields().stream()
                .filter(field -> !field.hasAnnotation("ManyToOne"))
                .filter(field -> !field.hasAnnotation("OneToOne"))
                .filter(field -> !field.hasAnnotation("OneToMany"))
                .filter(field -> !field.hasAnnotation("ManyToMany"))
                .filter(field -> !field.hasAnnotation("Transient"))
                .filter(field -> normalizeName(resolveColumnName(field)).equals(normalizedColumnName))
                .findFirst()
                .orElse(null);
    }

    private Column toModelColumn(ColumnDefinition columnDefinition) {
        Column column = new Column();
        column.setName(columnDefinition.name());
        column.setSqlType(columnDefinition.sqlType());
        column.setNullable(columnDefinition.nullable());
        column.setPrimaryKey(columnDefinition.primaryKey());
        column.setUnique(columnDefinition.unique());

        if (columnDefinition.length() != null) {
            column.setLength(columnDefinition.length());
        }

        if (columnDefinition.precision() != null) {
            column.setPrecision(columnDefinition.precision());
        }

        if (columnDefinition.scale() != null) {
            column.setScale(columnDefinition.scale());
        }

        return column;
    }

    private String simpleType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            return typeName;
        }

        String trimmedTypeName = typeName.trim();

        if (trimmedTypeName.contains("<")) {
            trimmedTypeName = trimmedTypeName.substring(0, trimmedTypeName.indexOf('<'));
        }

        if (trimmedTypeName.contains(".")) {
            return trimmedTypeName.substring(trimmedTypeName.lastIndexOf('.') + 1);
        }

        return trimmedTypeName;
    }


    /**
     * Validates column-level constraints declared in SQL against the generated Java field.
     *
     * @param entityDefinition parsed entity definition
     * @param tableDefinition parsed SQL table definition
     * @param violations collected violations
     */
    private void validateColumnConstraints(
            JavaEntityDefinition entityDefinition,
            TableDefinition tableDefinition,
            List<String> violations
    ) {
        for (ColumnDefinition columnDefinition : tableDefinition.columns().values()) {
            JavaFieldDefinition javaFieldDefinition = findJavaFieldForColumn(entityDefinition, columnDefinition);

            if (javaFieldDefinition == null) {
                continue;
            }

            if (javaFieldDefinition.hasAnnotation("ManyToOne")
                    || javaFieldDefinition.hasAnnotation("OneToOne")
                    || javaFieldDefinition.hasAnnotation("OneToMany")
                    || javaFieldDefinition.hasAnnotation("ManyToMany")
                    || javaFieldDefinition.hasAnnotation("Transient")) {
                continue;
            }

            validateNullabilityConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);
            validateUniqueConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);
            validateLengthConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);
            validatePrecisionAndScaleConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);
            validateSizeAnnotationConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);
            validateDigitsAnnotationConstraint(entityDefinition, columnDefinition, javaFieldDefinition, violations);

            // 🔥 THIS WAS MISSING
            validateDefaultAndCheckMetadataPresence(entityDefinition, columnDefinition, javaFieldDefinition, violations);
        }
    }

    /**
     * Validates @Size(max) against SQL length.
     */
    private void validateSizeAnnotationConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        if (columnDefinition.length() == null) return;

        Integer sizeMax = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Size", "max")
        );

        if (sizeMax != null && !columnDefinition.length().equals(sizeMax)) {
            violations.add("[%s] Column '%s' @Size mismatch (SQL=%d, Size=%d)"
                    .formatted(entityDefinition.displayName(), columnDefinition.name(),
                            columnDefinition.length(), sizeMax));
        }
    }

    /**
     * Validates @Digits against precision/scale.
     */
    private void validateDigitsAnnotationConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        if (columnDefinition.precision() == null || columnDefinition.scale() == null) return;

        Integer integerPart = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Digits", "integer")
        );

        Integer fractionPart = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Digits", "fraction")
        );

        if (integerPart == null || fractionPart == null) return;

        int expectedInteger = columnDefinition.precision() - columnDefinition.scale();
        int expectedFraction = columnDefinition.scale();

        if (integerPart != expectedInteger || fractionPart != expectedFraction) {
            violations.add("[%s] Column '%s' @Digits mismatch"
                    .formatted(entityDefinition.displayName(), columnDefinition.name()));
        }
    }

    private void validateDefaultAndCheckMetadataPresence(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        if (columnDefinition.defaultValue() != null
                && !columnDefinition.defaultValue().isBlank()
                && isBooleanLikeColumn(columnDefinition)
                && !javaFieldDefinition.hasAnnotation("Column")) {
            violations.add("""
                [%s] Column '%s' has SQL default metadata but Java field has no @Column annotation:
                  SQL default: %s
                """.formatted(
                    entityDefinition.displayName(),
                    columnDefinition.name(),
                    columnDefinition.defaultValue()
            ));
        }

        if (columnDefinition.checkConstraint() != null
                && !columnDefinition.checkConstraint().isBlank()
                && isStringLikeColumn(columnDefinition)
                && !javaFieldDefinition.hasAnnotation("Column")
                && !javaFieldDefinition.hasAnnotation("Size")) {
            violations.add("""
                [%s] Column '%s' has SQL check metadata but Java field exposes no obvious column/size constraint:
                  SQL check: %s
                """.formatted(
                    entityDefinition.displayName(),
                    columnDefinition.name(),
                    columnDefinition.checkConstraint()
            ));
        }
    }

    private boolean isBooleanLikeColumn(ColumnDefinition columnDefinition) {
        if (columnDefinition.sqlType() == null) {
            return false;
        }

        String sqlType = columnDefinition.sqlType().toLowerCase(Locale.ROOT);
        return sqlType.equals("bool")
                || sqlType.equals("boolean")
                || sqlType.equals("bit");
    }

    private boolean isStringLikeColumn(ColumnDefinition columnDefinition) {
        if (columnDefinition.sqlType() == null) {
            return false;
        }

        String sqlType = columnDefinition.sqlType().toLowerCase(Locale.ROOT);
        return sqlType.startsWith("varchar")
                || sqlType.startsWith("character varying")
                || sqlType.startsWith("char")
                || sqlType.startsWith("character")
                || sqlType.equals("text");
    }



    /**
     * Validates SQL nullability against Java field constraints.
     */
    private void validateNullabilityConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        boolean fieldDeclaresNullableFalse = "false".equalsIgnoreCase(
                javaFieldDefinition.annotationAttribute("Column", "nullable")
        );

        boolean fieldHasNotNull = javaFieldDefinition.hasAnnotation("NotNull");
        boolean primitiveTypeImpliesNotNull = isPrimitiveJavaType(javaFieldDefinition.type());

        if (!columnDefinition.nullable()
                && !fieldDeclaresNullableFalse
                && !fieldHasNotNull
                && !primitiveTypeImpliesNotNull) {

            violations.add("[%s] Column '%s' is NOT NULL in SQL but not enforced in Java"
                    .formatted(entityDefinition.displayName(), columnDefinition.name()));
        }
    }

    /**
     * Checks if Java type is primitive.
     */
    private boolean isPrimitiveJavaType(String typeName) {
        String simpleType = simpleType(typeName);

        return "int".equals(simpleType)
                || "long".equals(simpleType)
                || "double".equals(simpleType)
                || "float".equals(simpleType)
                || "boolean".equals(simpleType)
                || "short".equals(simpleType)
                || "byte".equals(simpleType)
                || "char".equals(simpleType);
    }



    /**
     * Validates SQL unique constraint.
     */
    private void validateUniqueConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        boolean fieldDeclaresUnique = "true".equalsIgnoreCase(
                javaFieldDefinition.annotationAttribute("Column", "unique")
        );

        if (columnDefinition.unique() && !fieldDeclaresUnique) {
            violations.add("[%s] Column '%s' is UNIQUE in SQL but not in Java"
                    .formatted(entityDefinition.displayName(), columnDefinition.name()));
        }
    }

    /**
     * Validates SQL length against @Column(length).
     */
    private void validateLengthConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        if (columnDefinition.length() == null) return;

        Integer actualLength = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Column", "length")
        );

        if (actualLength != null && !columnDefinition.length().equals(actualLength)) {
            violations.add("[%s] Column '%s' length mismatch (SQL=%d, Java=%d)"
                    .formatted(entityDefinition.displayName(), columnDefinition.name(),
                            columnDefinition.length(), actualLength));
        }
    }

    /**
     * Validates precision and scale.
     */
    private void validatePrecisionAndScaleConstraint(
            JavaEntityDefinition entityDefinition,
            ColumnDefinition columnDefinition,
            JavaFieldDefinition javaFieldDefinition,
            List<String> violations
    ) {
        Integer precision = columnDefinition.precision();
        Integer scale = columnDefinition.scale();

        if (precision == null && scale == null) return;

        Integer actualPrecision = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Column", "precision")
        );

        Integer actualScale = tryParseInteger(
                javaFieldDefinition.annotationAttribute("Column", "scale")
        );

        if (precision != null && actualPrecision != null && !precision.equals(actualPrecision)) {
            violations.add("[%s] Column '%s' precision mismatch (SQL=%d, Java=%d)"
                    .formatted(entityDefinition.displayName(), columnDefinition.name(), precision, actualPrecision));
        }

        if (scale != null && actualScale != null && !scale.equals(actualScale)) {
            violations.add("[%s] Column '%s' scale mismatch (SQL=%d, Java=%d)"
                    .formatted(entityDefinition.displayName(), columnDefinition.name(), scale, actualScale));
        }
    }


    private Integer tryParseInteger(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            return null;
        }
    }

}