package com.sqldomaingen.schemaValidation;

import com.sqldomaingen.generator.LiquibaseGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.UniqueConstraint;
import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.PostgreSQLBaseListener;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.SQLParser;
import com.sqldomaingen.util.Constants;
import lombok.Getter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LiquibaseSchemaParityTest {

    private static final Pattern TYPE_PATTERN =
            Pattern.compile("^\\s*([A-Z0-9 ]+?)\\s*(?:\\((\\d+)(?:\\s*,\\s*(\\d+))?\\))?\\s*(\\[\\])?\\s*$");

    @TempDir
    Path tempDir;

    /**
     * Verifies that the generated Liquibase changelog files preserve key SQL semantics
     * from the real schema file.
     *
     * @throws Exception if parsing, generation, or file reading fails
     */
    @Test
    void shouldGenerateLiquibaseFilesThatMatchParsedSqlSchema() throws Exception {
        Path schemaPath = Constants.SCHEMA_PATH;
        assertTrue(Files.exists(schemaPath), "Missing schema file: " + schemaPath.toAbsolutePath());

        String sql = Files.readString(schemaPath);
        List<Table> parsedTables = parseTables(sql);

        LiquibaseGenerator liquibaseGenerator = new LiquibaseGenerator();
        liquibaseGenerator.generateLiquibaseFiles(
                tempDir.toString(),
                parsedTables,
                true,
                "tester@knowledge.gr"
        );

        Path versionDir = tempDir.resolve("src/main/resources/db/migration/changelogs/v0.1.0");
        Path auditXmlPath = versionDir.resolve("audit.xml");
        Path mainXmlPath = versionDir.resolve("main.xml");

        assertTrue(Files.exists(auditXmlPath), "Generated audit.xml was not found");
        assertTrue(Files.exists(mainXmlPath), "Generated main.xml was not found");

        String auditXml = Files.readString(auditXmlPath);
        String mainXml = Files.readString(mainXmlPath);

        assertAuditBootstrap(auditXml);
        assertMainIncludesAllTables(mainXml, parsedTables);

        for (Table table : parsedTables) {
            assertGeneratedTableChangelogMatchesParsedTable(versionDir, table);
        }
    }

    /**
     * Parses CREATE TABLE statements from the real schema SQL.
     *
     * @param sql raw schema SQL
     * @return parsed tables
     */
    private List<Table> parseTables(String sql) {
        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql);

        ParseTree parseTree = sqlParser.parseTreeFromSQL();

        CreateTableStatementCollector collector = new CreateTableStatementCollector();
        ParseTreeWalker.DEFAULT.walk(collector, parseTree);

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        Map<String, Table> tableMap = createTableDefinition.parseAllTables(collector.getCreateTableStatements());

        return new ArrayList<>(tableMap.values());
    }

    /**
     * Verifies audit bootstrap XML.
     *
     * @param auditXml generated audit.xml content
     */
    private void assertAuditBootstrap(String auditXml) {
        assertContains(auditXml, "<changeSet id=\"create_audit_schema\" author=\"tester@knowledge.gr\">");
        assertContains(auditXml, "CREATE SCHEMA IF NOT EXISTS audit;");
        assertContains(auditXml, "<createTable tableName=\"REVINFO\" schemaName=\"audit\">");
        assertContains(auditXml, "<createSequence sequenceName=\"revinfo_seq\"");
    }

    /**
     * Verifies that main.xml includes audit.xml and all expected table changelog files.
     *
     * @param mainXml generated main.xml content
     * @param parsedTables parsed tables
     */
    private void assertMainIncludesAllTables(String mainXml, List<Table> parsedTables) {
        assertContains(mainXml, "<include file=\"audit.xml\" relativeToChangelogFile=\"true\" />");

        for (Table table : parsedTables) {
            String expectedFileName = toIncludeFileName(table.getName());
            assertContains(
                    mainXml,
                    "<include file=\"" + expectedFileName + "\" relativeToChangelogFile=\"true\" />"
            );
        }
    }

    /**
     * Verifies that one generated table changelog preserves the parsed SQL semantics.
     *
     * @param versionDir generated Liquibase version directory
     * @param table parsed table
     * @throws Exception if reading fails
     */
    private void assertGeneratedTableChangelogMatchesParsedTable(Path versionDir, Table table) throws Exception {
        String fileName = toIncludeFileName(table.getName());
        Path tableXmlPath = versionDir.resolve(fileName);

        assertTrue(Files.exists(tableXmlPath), "Generated changelog file was not found: " + fileName);

        String xml = Files.readString(tableXmlPath);

        String normalizedTableName = stripSchema(table.getName());
        String schemaName = extractSchema(table.getName());
        String auditTableName = normalizedTableName + "_aud";
        String expectedMainCreateTableTag = buildExpectedMainCreateTableTag(normalizedTableName, schemaName);

        assertContains(xml, expectedMainCreateTableTag);
        assertContains(xml, "<createTable tableName=\"" + auditTableName + "\" schemaName=\"audit\">");
        assertContains(xml, "<addPrimaryKey tableName=\"" + auditTableName + "\"");
        assertContains(xml, "constraintName=\"pk_" + auditTableName + "\"");
        assertContains(xml, "<addForeignKeyConstraint baseTableName=\"" + auditTableName + "\"");
        assertContains(xml, "constraintName=\"fk_" + normalizedTableName + "_aud_revinfo\"");

        String mainTableBlock = extractBlock(
                xml,
                expectedMainCreateTableTag,
                "</createTable>"
        );
        String auditTableBlock = extractBlock(
                xml,
                "<createTable tableName=\"" + auditTableName + "\" schemaName=\"audit\">",
                "</createTable>"
        );

        for (Column column : table.getColumns()) {
            assertMainColumnMapping(mainTableBlock, column);
            assertAuditColumnMapping(auditTableBlock, column);
        }

        assertMainTableColumnCount(mainTableBlock, table);
        assertAuditTableColumnCount(auditTableBlock, table);
        assertMainTableColumnOrder(mainTableBlock, table);
        assertAuditTableColumnOrder(auditTableBlock, table);

        assertForeignKeys(xml, table);
        assertForeignKeyCount(xml, table);

        assertCompositeUniqueConstraints(xml, table);
        assertCompositeUniqueConstraintCount(xml, table);

        assertCheckConstraints(xml, table);
        assertCheckConstraintCount(xml, table);
    }

    /**
     * Builds the expected createTable tag for the main table.
     *
     * @param tableName schema-free table name
     * @param schemaName schema name
     * @return expected createTable tag
     */
    private String buildExpectedMainCreateTableTag(String tableName, String schemaName) {
        if (schemaName == null || schemaName.isBlank()) {
            return "<createTable tableName=\"" + tableName + "\">";
        }

        return "<createTable tableName=\"" + tableName + "\" schemaName=\"" + schemaName + "\">";
    }

    /**
     * Verifies one main-table column mapping in the generated XML block.
     *
     * @param mainTableBlock generated main-table block
     * @param column parsed column
     */
    private void assertMainColumnMapping(String mainTableBlock, Column column) {
        String columnBlock = extractColumnBlock(mainTableBlock, normalizeIdentifier(column.getName()));

        assertColumnType(columnBlock, column);
        assertPrimaryKeyMapping(columnBlock, column);
        assertNullableMapping(columnBlock, column);
        assertUniqueMapping(columnBlock, column);
        assertDefaultValueMapping(columnBlock, column);
        assertAutoIncrementMapping(columnBlock, column);
    }

    /**
     * Verifies one audit-table column mapping in the generated XML block.
     *
     * @param auditTableBlock generated audit-table block
     * @param column parsed column
     */
    private void assertAuditColumnMapping(String auditTableBlock, Column column) {
        String columnBlock = extractColumnBlock(auditTableBlock, normalizeIdentifier(column.getName()));
        assertColumnType(columnBlock, column);
    }

    /**
     * Verifies the generated SQL type semantics for one column block.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertColumnType(String columnBlock, Column column) {
        String actualType = extractAttributeValue(columnBlock, "type");
        String expectedType = normalizeType(column.getSqlType());

        TypeDescriptor expectedDescriptor = parseTypeDescriptor(expectedType);
        TypeDescriptor actualDescriptor = parseTypeDescriptor(actualType);

        assertEquals(
                expectedDescriptor.baseType(),
                actualDescriptor.baseType(),
                "Unexpected base type for column: " + normalizeIdentifier(column.getName())
        );

        assertEquals(
                expectedDescriptor.lengthOrPrecision(),
                actualDescriptor.lengthOrPrecision(),
                "Unexpected length/precision for column: " + normalizeIdentifier(column.getName())
        );

        assertEquals(
                expectedDescriptor.scale(),
                actualDescriptor.scale(),
                "Unexpected scale for column: " + normalizeIdentifier(column.getName())
        );

        assertEquals(
                expectedDescriptor.array(),
                actualDescriptor.array(),
                "Unexpected array flag for column: " + normalizeIdentifier(column.getName())
        );
    }

    /**
     * Verifies primary key mapping for one column block.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertPrimaryKeyMapping(String columnBlock, Column column) {
        boolean actualPrimaryKey = columnBlock.contains("primaryKey=\"true\"");
        assertEquals(
                column.isPrimaryKey(),
                actualPrimaryKey,
                "Unexpected primary key mapping for column: " + normalizeIdentifier(column.getName())
        );
    }

    /**
     * Verifies nullable mapping for one column block.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertNullableMapping(String columnBlock, Column column) {
        boolean actualNullableFalse = columnBlock.contains("nullable=\"false\"");
        assertEquals(
                !column.isNullable(),
                actualNullableFalse,
                "Unexpected nullable mapping for column: " + normalizeIdentifier(column.getName())
        );
    }

    /**
     * Verifies unique mapping for one column block.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertUniqueMapping(String columnBlock, Column column) {
        boolean actualUnique = columnBlock.contains("unique=\"true\"");
        assertEquals(
                column.isUnique(),
                actualUnique,
                "Unexpected unique mapping for column: " + normalizeIdentifier(column.getName())
        );
    }

    /**
     * Verifies default value mapping for one parsed column.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertDefaultValueMapping(String columnBlock, Column column) {
        String defaultValue = column.getDefaultValue();
        if (defaultValue == null || defaultValue.isBlank()) {
            assertTrue(
                    !columnBlock.contains("defaultValue=")
                            && !columnBlock.contains("defaultValueBoolean=")
                            && !columnBlock.contains("defaultValueNumeric=")
                            && !columnBlock.contains("defaultValueComputed="),
                    "Unexpected default value mapping for column: " + normalizeIdentifier(column.getName())
            );
            return;
        }

        String trimmedDefaultValue = defaultValue.trim();
        String normalizedDefaultValue = trimmedDefaultValue.toLowerCase(Locale.ROOT);

        if ("true".equals(normalizedDefaultValue) || "false".equals(normalizedDefaultValue)) {
            assertContains(columnBlock, "defaultValueBoolean=\"" + normalizedDefaultValue + "\"");
            return;
        }

        if (normalizedDefaultValue.matches("-?\\d+(\\.\\d+)?")) {
            assertContains(columnBlock, "defaultValueNumeric=\"" + normalizedDefaultValue + "\"");
            return;
        }

        if (normalizedDefaultValue.matches("^'?[^']+'?::(charactervarying|character varying|varchar|text)$")) {
            String literalValue = trimmedDefaultValue
                    .replaceFirst("(?i)::(charactervarying|character varying|varchar|text)$", "")
                    .trim();

            if (literalValue.startsWith("'") && literalValue.endsWith("'") && literalValue.length() >= 2) {
                literalValue = literalValue.substring(1, literalValue.length() - 1);
            }

            assertContains(columnBlock, "defaultValue=\"" + literalValue + "\"");
            return;
        }

        if (trimmedDefaultValue.matches("^'.*'$")) {
            String literalValue = trimmedDefaultValue.substring(1, trimmedDefaultValue.length() - 1);
            assertContains(columnBlock, "defaultValue=\"" + literalValue + "\"");
            return;
        }

        if (normalizedDefaultValue.contains("(")
                || "current_timestamp".equals(normalizedDefaultValue)
                || "current_date".equals(normalizedDefaultValue)
                || "current_time".equals(normalizedDefaultValue)
                || "localtimestamp".equals(normalizedDefaultValue)
                || "localtime".equals(normalizedDefaultValue)
                || column.isDefaultExpression()) {
            assertContains(columnBlock, "defaultValueComputed=\"" + trimmedDefaultValue + "\"");
            return;
        }

        assertContains(columnBlock, "defaultValue=\"" + trimmedDefaultValue + "\"");
    }

    /**
     * Verifies auto-increment mapping for one column block.
     *
     * @param columnBlock generated column block
     * @param column parsed column
     */
    private void assertAutoIncrementMapping(String columnBlock, Column column) {
        boolean actualAutoIncrement = columnBlock.contains("autoIncrement=\"true\"");
        assertEquals(
                column.isIdentity(),
                actualAutoIncrement,
                "Unexpected auto-increment mapping for column: " + normalizeIdentifier(column.getName())
        );
    }

    /**
     * Verifies generated foreign keys for the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertForeignKeys(String xml, Table table) {
        for (Column column : table.getColumns()) {
            if (!column.isForeignKey()) {
                continue;
            }

            assertContains(xml, "<addForeignKeyConstraint baseTableName=\"" + stripSchema(table.getName()) + "\"");
            assertContains(xml, "baseColumnNames=\"" + normalizeIdentifier(column.getName()) + "\"");

            String referencedTable = stripSchema(column.getReferencedTable());
            assertContains(xml, "referencedTableName=\"" + normalizeIdentifier(referencedTable) + "\"");

            String referencedColumn = column.getReferencedColumn() != null && !column.getReferencedColumn().isBlank()
                    ? normalizeIdentifier(column.getReferencedColumn())
                    : "id";
            assertContains(xml, "referencedColumnNames=\"" + referencedColumn + "\"");

            String referencedSchema = extractSchema(column.getReferencedTable());
            if (referencedSchema != null && !referencedSchema.isBlank()) {
                assertContains(xml, "referencedTableSchemaName=\"" + referencedSchema + "\"");
            }

            if (column.getOnDelete() != null && !column.getOnDelete().isBlank()) {
                assertContains(xml, "onDelete=\"" + column.getOnDelete() + "\"");
            }

            if (column.getOnUpdate() != null && !column.getOnUpdate().isBlank()) {
                assertContains(xml, "onUpdate=\"" + column.getOnUpdate() + "\"");
            }
        }
    }

    /**
     * Verifies that the number of generated main-table foreign keys matches the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertForeignKeyCount(String xml, Table table) {
        long expectedForeignKeyCount = table.getColumns().stream()
                .filter(Column::isForeignKey)
                .count();

        String normalizedTableName = stripSchema(table.getName());

        int actualForeignKeyCount = countOccurrences(
                xml,
                "<addForeignKeyConstraint baseTableName=\"" + normalizedTableName + "\""
        );

        assertEquals(
                expectedForeignKeyCount,
                actualForeignKeyCount,
                "Unexpected foreign key count for table: " + normalizedTableName
        );
    }

    /**
     * Verifies generated composite unique constraints for the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertCompositeUniqueConstraints(String xml, Table table) {
        if (table.getUniqueConstraints() == null || table.getUniqueConstraints().isEmpty()) {
            return;
        }

        for (UniqueConstraint uniqueConstraint : table.getUniqueConstraints()) {
            List<String> columns = uniqueConstraint.getColumns();
            if (columns == null || columns.isEmpty()) {
                continue;
            }

            String joinedColumns = columns.stream()
                    .map(this::normalizeIdentifier)
                    .collect(Collectors.joining(", "));

            assertContains(xml, "<addUniqueConstraint tableName=\"" + stripSchema(table.getName()) + "\"");
            assertContains(xml, "columnNames=\"" + joinedColumns + "\"");

            if (uniqueConstraint.getName() != null && !uniqueConstraint.getName().isBlank()) {
                assertContains(xml, "constraintName=\"" + uniqueConstraint.getName() + "\"");
            }
        }
    }

    /**
     * Verifies that the number of generated composite unique constraints matches the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertCompositeUniqueConstraintCount(String xml, Table table) {
        long expectedUniqueConstraintCount = table.getUniqueConstraints() == null
                ? 0
                : table.getUniqueConstraints().stream()
                .filter(uniqueConstraint -> uniqueConstraint.getColumns() != null
                        && uniqueConstraint.getColumns().size() > 1)
                .count();

        String normalizedTableName = stripSchema(table.getName());

        int actualUniqueConstraintCount = countOccurrences(
                xml,
                "<addUniqueConstraint tableName=\"" + normalizedTableName + "\""
        );

        assertEquals(
                expectedUniqueConstraintCount,
                actualUniqueConstraintCount,
                "Unexpected composite unique constraint count for table: " + normalizedTableName
        );
    }

    /**
     * Verifies generated check constraints for the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertCheckConstraints(String xml, Table table) {
        if (table.getConstraints() == null || table.getConstraints().isEmpty()) {
            return;
        }

        for (String constraint : table.getConstraints()) {
            if (constraint == null || constraint.isBlank()) {
                continue;
            }

            String normalizedConstraint = normalizeCheckConstraint(constraint);
            if (normalizedConstraint.isBlank()) {
                continue;
            }

            assertContains(
                    normalizeXmlForConstraintComparison(xml),
                    normalizedConstraint
            );
        }
    }

    /**
     * Verifies that the number of generated check constraints matches the parsed table.
     *
     * @param xml generated table changelog XML
     * @param table parsed table
     */
    private void assertCheckConstraintCount(String xml, Table table) {
        long expectedCheckConstraintCount = table.getConstraints() == null
                ? 0
                : table.getConstraints().stream()
                .filter(Objects::nonNull)
                .map(this::normalizeCheckConstraint)
                .filter(constraint -> !constraint.isBlank())
                .count();

        int actualCheckConstraintCount = countOccurrences(xml, "<addCheckConstraint ");

        assertEquals(
                expectedCheckConstraintCount,
                actualCheckConstraintCount,
                "Unexpected check constraint count for table: " + stripSchema(table.getName())
        );
    }

    /**
     * Verifies that the generated XML contains exactly the expected number of main-table columns.
     *
     * @param mainTableBlock generated main-table block
     * @param table parsed table
     */
    private void assertMainTableColumnCount(String mainTableBlock, Table table) {
        int actualColumnCount = countOccurrences(mainTableBlock, "<column name=\"");
        int expectedColumnCount = table.getColumns().size();

        assertEquals(
                expectedColumnCount,
                actualColumnCount,
                "Unexpected main-table column count for table: " + stripSchema(table.getName())
        );
    }

    /**
     * Verifies that the generated XML contains exactly the expected number of audit-table columns.
     *
     * @param auditTableBlock generated audit-table block
     * @param table parsed table
     */
    private void assertAuditTableColumnCount(String auditTableBlock, Table table) {
        int actualColumnCount = countOccurrences(auditTableBlock, "<column name=\"");
        int expectedColumnCount = table.getColumns().size() + 2;

        assertEquals(
                expectedColumnCount,
                actualColumnCount,
                "Unexpected audit-table column count for table: " + stripSchema(table.getName())
        );
    }

    /**
     * Verifies that the main-table columns preserve the parsed SQL order.
     *
     * @param mainTableBlock generated main-table block
     * @param table parsed table
     */
    private void assertMainTableColumnOrder(String mainTableBlock, Table table) {
        List<String> actualColumnNames = extractColumnNamesInOrder(mainTableBlock);
        List<String> expectedColumnNames = table.getColumns().stream()
                .map(Column::getName)
                .map(this::normalizeIdentifier)
                .toList();

        assertEquals(
                expectedColumnNames,
                actualColumnNames,
                "Unexpected main-table column order for table: " + stripSchema(table.getName())
        );
    }

    /**
     * Verifies that the audit-table columns preserve the parsed SQL order plus revision columns.
     *
     * @param auditTableBlock generated audit-table block
     * @param table parsed table
     */
    private void assertAuditTableColumnOrder(String auditTableBlock, Table table) {
        List<String> actualColumnNames = extractColumnNamesInOrder(auditTableBlock);

        List<String> expectedColumnNames = new ArrayList<>(
                table.getColumns().stream()
                        .map(Column::getName)
                        .map(this::normalizeIdentifier)
                        .toList()
        );
        expectedColumnNames.add("rev");
        expectedColumnNames.add("revtype");

        assertEquals(
                expectedColumnNames,
                actualColumnNames,
                "Unexpected audit-table column order for table: " + stripSchema(table.getName())
        );
    }

    /**
     * Extracts a table or section block between two markers.
     *
     * @param text source text
     * @param startMarker block start marker
     * @param endMarker block end marker
     * @return extracted block
     */
    private String extractBlock(String text, String startMarker, String endMarker) {
        int startIndex = text.indexOf(startMarker);
        assertTrue(startIndex >= 0, "Start marker not found: " + startMarker);

        int endIndex = text.indexOf(endMarker, startIndex);
        assertTrue(endIndex >= 0, "End marker not found: " + endMarker);

        endIndex += endMarker.length();
        return text.substring(startIndex, endIndex);
    }

    /**
     * Extracts one exact column block from a createTable block.
     *
     * @param createTableBlock table block
     * @param columnName normalized column name
     * @return extracted column block
     */
    private String extractColumnBlock(String createTableBlock, String columnName) {
        String marker = "<column name=\"" + columnName + "\"";
        int startIndex = createTableBlock.indexOf(marker);

        assertTrue(startIndex >= 0, "Column block not found for column: " + columnName);

        int selfClosingEndIndex = createTableBlock.indexOf("/>", startIndex);
        int regularEndIndex = createTableBlock.indexOf("</column>", startIndex);

        if (selfClosingEndIndex >= 0 && (regularEndIndex < 0 || selfClosingEndIndex < regularEndIndex)) {
            return createTableBlock.substring(startIndex, selfClosingEndIndex + 2);
        }

        assertTrue(regularEndIndex >= 0, "Column closing tag not found for column: " + columnName);
        return createTableBlock.substring(startIndex, regularEndIndex + "</column>".length());
    }

    /**
     * Extracts all column names in encounter order from a createTable block.
     *
     * @param createTableBlock table block
     * @return ordered column names
     */
    private List<String> extractColumnNamesInOrder(String createTableBlock) {
        Pattern columnPattern = Pattern.compile("<column\\s+name=\"([^\"]+)\"");
        Matcher matcher = columnPattern.matcher(createTableBlock);

        List<String> columnNames = new ArrayList<>();
        while (matcher.find()) {
            columnNames.add(matcher.group(1));
        }

        return columnNames;
    }

    /**
     * Extracts an XML attribute value from a fragment.
     *
     * @param xmlFragment source XML fragment
     * @param attributeName attribute name
     * @return attribute value
     */
    private String extractAttributeValue(String xmlFragment, String attributeName) {
        Pattern attributePattern = Pattern.compile(attributeName + "=\"([^\"]+)\"");
        Matcher matcher = attributePattern.matcher(xmlFragment);

        assertTrue(matcher.find(), "Attribute not found: " + attributeName + " in fragment:\n" + xmlFragment);
        return matcher.group(1);
    }

    /**
     * Counts the occurrences of a token inside a text.
     *
     * @param text source text
     * @param token token to count
     * @return number of occurrences
     */
    private int countOccurrences(String text, String token) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(token, index)) >= 0) {
            count++;
            index += token.length();
        }

        return count;
    }

    /**
     * Normalizes a SQL identifier by removing surrounding double quotes.
     *
     * @param identifier raw SQL identifier
     * @return normalized identifier
     */
    private String normalizeIdentifier(String identifier) {
        if (identifier == null) {
            return "";
        }

        String normalizedIdentifier = identifier.trim();

        if (normalizedIdentifier.length() >= 2
                && normalizedIdentifier.startsWith("\"")
                && normalizedIdentifier.endsWith("\"")) {
            normalizedIdentifier = normalizedIdentifier.substring(1, normalizedIdentifier.length() - 1);
        }

        return normalizedIdentifier.trim();
    }

    /**
     * Normalizes a SQL type for stable comparison.
     *
     * @param sqlType raw SQL type
     * @return normalized SQL type
     */
    private String normalizeType(String sqlType) {
        if (sqlType == null || sqlType.isBlank()) {
            return "";
        }

        String normalizedType = sqlType.trim()
                .replaceAll("\\s+", " ")
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*,\\s*", ",")
                .replaceAll("\\s*\\)", ")")
                .replaceAll("\\s*\\[\\s*\\]", "[]")
                .toUpperCase(Locale.ROOT);

        if ("BIGSERIAL".equals(normalizedType)) {
            return "BIGINT";
        }

        if ("SERIAL".equals(normalizedType)) {
            return "INTEGER";
        }

        if ("SMALLSERIAL".equals(normalizedType)) {
            return "SMALLINT";
        }

        if ("INT8".equals(normalizedType)) {
            return "BIGINT";
        }

        if ("INT4".equals(normalizedType)) {
            return "INTEGER";
        }

        if ("INT2".equals(normalizedType)) {
            return "SMALLINT";
        }

        if ("FLOAT8".equals(normalizedType)) {
            return "DOUBLE";
        }

        if ("FLOAT4".equals(normalizedType)) {
            return "REAL";
        }

        if ("BOOL".equals(normalizedType)) {
            return "BOOLEAN";
        }

        if ("BPCHAR".equals(normalizedType)) {
            return "CHAR";
        }

        if (normalizedType.startsWith("BPCHAR(")) {
            return normalizedType.replaceFirst("BPCHAR", "CHAR");
        }

        if (normalizedType.startsWith("CHARACTER VARYING")) {
            return normalizedType.replaceFirst("CHARACTER VARYING", "VARCHAR");
        }

        if (normalizedType.startsWith("CHARACTERVARYING")) {
            return normalizedType.replaceFirst("CHARACTERVARYING", "VARCHAR");
        }

        if (normalizedType.startsWith("TIMESTAMP")) {
            return "TIMESTAMP";
        }

        if (normalizedType.startsWith("TIME(")) {
            return "TIME";
        }

        return normalizedType;
    }

    /**
     * Parses a SQL type into structured components.
     *
     * @param sqlType normalized or raw SQL type
     * @return parsed type descriptor
     */
    private TypeDescriptor parseTypeDescriptor(String sqlType) {
        String normalizedType = normalizeType(sqlType);
        Matcher matcher = TYPE_PATTERN.matcher(normalizedType);

        assertTrue(matcher.matches(), "Could not parse SQL type: " + sqlType);

        String baseType = matcher.group(1) == null ? "" : matcher.group(1).trim();
        Integer lengthOrPrecision = matcher.group(2) == null ? null : Integer.valueOf(matcher.group(2));
        Integer scale = matcher.group(3) == null ? null : Integer.valueOf(matcher.group(3));
        boolean array = matcher.group(4) != null && !matcher.group(4).isBlank();

        return new TypeDescriptor(baseType, lengthOrPrecision, scale, array);
    }

    /**
     * Normalizes a parsed check constraint for XML comparison.
     *
     * @param constraint raw parsed constraint
     * @return normalized constraint expression
     */
    private String normalizeCheckConstraint(String constraint) {
        if (constraint == null || constraint.isBlank()) {
            return "";
        }

        String normalizedConstraint = constraint.trim()
                .replace("\"", "")
                .replaceAll("\\s+", " ")
                .trim();

        String upperConstraint = normalizedConstraint.toUpperCase(Locale.ROOT);
        int checkIndex = upperConstraint.indexOf("CHECK");
        if (checkIndex >= 0) {
            normalizedConstraint = normalizedConstraint.substring(checkIndex);
        }

        return normalizedConstraint
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*\\)\\s*", ")")
                .replaceAll("\\s*=\\s*", "=")
                .replaceAll("\\s*<>\\s*", "<>")
                .replaceAll("\\s*!=\\s*", "!=")
                .replaceAll("\\s*<=\\s*", "<=")
                .replaceAll("\\s*>=\\s*", ">=")
                .replaceAll("\\s*<\\s*", "<")
                .replaceAll("\\s*>\\s*", ">")
                .replaceAll("\\s*,\\s*", ",")
                .replaceAll("\\s+", " ")
                .trim();
    }

    /**
     * Normalizes generated XML for check-constraint comparison.
     *
     * @param xml generated XML
     * @return normalized XML text
     */
    private String normalizeXmlForConstraintComparison(String xml) {
        if (xml == null) {
            return "";
        }

        return xml.replace("\"", "")
                .replaceAll("\\s+", " ")
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*\\)\\s*", ")")
                .replaceAll("\\s*=\\s*", "=")
                .replaceAll("\\s*<>\\s*", "<>")
                .replaceAll("\\s*!=\\s*", "!=")
                .replaceAll("\\s*<=\\s*", "<=")
                .replaceAll("\\s*>=\\s*", ">=")
                .replaceAll("\\s*<\\s*", "<")
                .replaceAll("\\s*>\\s*", ">")
                .replaceAll("\\s*,\\s*", ",")
                .trim();
    }

    /**
     * Converts a physical table name to the expected Liquibase changelog filename.
     *
     * @param physicalTableName physical table name
     * @return changelog filename
     */
    private String toIncludeFileName(String physicalTableName) {
        String schemaFreeName = stripSchema(physicalTableName);
        String[] parts = schemaFreeName.split("_");

        StringBuilder builder = new StringBuilder(parts[0]);
        for (int index = 1; index < parts.length; index++) {
            builder.append(capitalize(parts[index]));
        }

        return builder + ".xml";
    }

    /**
     * Removes schema prefix from a physical table name.
     *
     * @param tableName physical table name
     * @return schema-free table name
     */
    private String stripSchema(String tableName) {
        if (tableName == null || tableName.isBlank()) {
            return "";
        }

        int dotIndex = tableName.indexOf('.');
        return dotIndex >= 0 ? tableName.substring(dotIndex + 1) : tableName;
    }

    /**
     * Extracts schema name from a schema-qualified table reference.
     *
     * @param tableName schema-qualified or plain table name
     * @return schema name or null
     */
    private String extractSchema(String tableName) {
        if (tableName == null || tableName.isBlank()) {
            return null;
        }

        int dotIndex = tableName.indexOf('.');
        return dotIndex > 0 ? tableName.substring(0, dotIndex) : null;
    }

    /**
     * Capitalizes the first character of the given text.
     *
     * @param value input text
     * @return capitalized text
     */
    private String capitalize(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }

        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    /**
     * Asserts that the given fragment exists in the generated XML.
     *
     * @param actualXml actual generated XML
     * @param expectedFragment expected fragment
     */
    private void assertContains(String actualXml, String expectedFragment) {
        assertTrue(
                actualXml.contains(expectedFragment),
                () -> "Expected XML fragment not found:\n" + expectedFragment + "\n\nActual XML:\n" + actualXml
        );
    }

    /**
     * Collects CREATE TABLE statement contexts from the parsed SQL tree.
     */
    @Getter
    private static class CreateTableStatementCollector extends PostgreSQLBaseListener {

        /**
         * Collected CREATE TABLE statement contexts in encounter order.
         */
        private final List<PostgreSQLParser.CreateTableStatementContext> createTableStatements = new ArrayList<>();

        /**
         * Stores each encountered CREATE TABLE statement context.
         *
         * @param context the current CREATE TABLE statement context
         */
        @Override
        public void enterCreateTableStatement(PostgreSQLParser.CreateTableStatementContext context) {
            createTableStatements.add(Objects.requireNonNull(context));
        }
    }

    /**
     * Structured SQL type descriptor.
     *
     * @param baseType base SQL type
     * @param lengthOrPrecision varchar length or numeric precision
     * @param scale numeric scale
     * @param array whether the type is an array
     */
    private record TypeDescriptor(String baseType, Integer lengthOrPrecision, Integer scale, boolean array) {
    }
}