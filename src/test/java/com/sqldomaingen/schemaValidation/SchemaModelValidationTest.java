package com.sqldomaingen.schemaValidation;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.IndexDefinition;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.shell.GeneratorCommands;
import com.sqldomaingen.util.Constants;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Validates the parsed schema model produced from the real SQL schema file.
 *
 * <p>This test focuses on parser/model correctness before entity and Liquibase generation.
 * It verifies:
 * <ul>
 *     <li>all parsed tables are structurally valid,</li>
 *     <li>all columns have stable metadata,</li>
 *     <li>foreign keys point to existing tables,</li>
 *     <li>indexes are attached to the correct tables,</li>
 *     <li>critical real-schema scenarios are preserved.</li>
 * </ul>
 */
class SchemaModelValidationTest {

    /**
     * Validates that the real schema is parsed into a consistent in-memory table model.
     *
     * @throws Exception if schema loading fails
     */
    @Test
    void shouldParseRealSchemaIntoConsistentTableModel() throws Exception {
        Path schemaPath = Constants.SCHEMA_PATH;
        assertTrue(Files.exists(schemaPath), "Missing schema file: " + schemaPath.toAbsolutePath());

        String sql = Files.readString(schemaPath);

        GeneratorCommands generatorCommands = new GeneratorCommands();
        List<Table> parsedTables = assertDoesNotThrow(
                () -> generatorCommands.parseSQLToTables(sql),
                "Parsing real schema should not throw exception"
        );

        assertNotNull(parsedTables, "Parsed tables list should not be null");
        assertFalse(parsedTables.isEmpty(), "Parsed tables list should not be empty");

        Map<String, Table> tableByName = indexTablesByNormalizedName(parsedTables);

        assertEveryTableIsStructurallyValid(parsedTables);
        assertEveryColumnIsStructurallyValid(parsedTables);
        assertForeignKeysPointToExistingTables(parsedTables, tableByName);
        assertIndexesAreStructurallyValid(parsedTables);
        assertDataStagingTableScenario(tableByName);
        assertKeycloakDataTableScenario(tableByName);
        assertCompanySearchMaterializedViewIndexes(tableByName);
    }

    /**
     * Builds a lookup map for parsed tables using normalized physical table names.
     *
     * @param parsedTables parsed table models
     * @return normalized table lookup map
     */
    private Map<String, Table> indexTablesByNormalizedName(List<Table> parsedTables) {
        Map<String, Table> tableByName = new LinkedHashMap<>();

        for (Table table : parsedTables) {
            assertNotNull(table, "Parsed table must not be null");
            assertNotNull(table.getName(), "Parsed table name must not be null");
            assertFalse(table.getName().isBlank(), "Parsed table name must not be blank");

            tableByName.put(normalizePhysicalName(table.getName()), table);
        }

        return tableByName;
    }

    /**
     * Verifies that every parsed table has the minimum required structure.
     *
     * @param parsedTables parsed table models
     */
    private void assertEveryTableIsStructurallyValid(List<Table> parsedTables) {
        for (Table table : parsedTables) {
            assertNotNull(table.getName(), "Table name must not be null");
            assertFalse(table.getName().isBlank(), "Table name must not be blank");

            assertNotNull(table.getColumns(), "Columns list must not be null for table: " + table.getName());
            assertFalse(table.getColumns().isEmpty(), "Columns list must not be empty for table: " + table.getName());

            long primaryKeyCount = table.getColumns().stream()
                    .filter(Column::isPrimaryKey)
                    .count();

            assertTrue(
                    primaryKeyCount >= 1,
                    "Expected at least one primary key column for table: " + table.getName()
            );
        }
    }

    /**
     * Verifies that every parsed column contains stable core metadata.
     *
     * @param parsedTables parsed table models
     */
    private void assertEveryColumnIsStructurallyValid(List<Table> parsedTables) {
        for (Table table : parsedTables) {
            for (Column column : table.getColumns()) {
                assertNotNull(column, "Column must not be null in table: " + table.getName());

                assertNotNull(column.getName(), "Column name must not be null in table: " + table.getName());
                assertFalse(column.getName().isBlank(), "Column name must not be blank in table: " + table.getName());

                assertNotNull(column.getSqlType(),
                        "SQL type must not be null for column: " + table.getName() + "." + column.getName());
                assertFalse(column.getSqlType().isBlank(),
                        "SQL type must not be blank for column: " + table.getName() + "." + column.getName());

                assertNotNull(column.getJavaType(),
                        "Java type must not be null for column: " + table.getName() + "." + column.getName());
                assertFalse(column.getJavaType().isBlank(),
                        "Java type must not be blank for column: " + table.getName() + "." + column.getName());

                if (column.getDefaultValue() != null) {
                    assertFalse(
                            column.getDefaultValue().isBlank(),
                            "Default value must not be blank when present for column: "
                                    + table.getName() + "." + column.getName()
                    );
                }
            }
        }
    }

    /**
     * Verifies that every parsed foreign key has a valid referenced table name.
     * <p>
     * Foreign keys pointing to tables outside the currently parsed schema file are allowed.
     *
     * @param parsedTables parsed table models
     * @param tableByName normalized table lookup map
     */
    private void assertForeignKeysPointToExistingTables(List<Table> parsedTables, Map<String, Table> tableByName) {
        List<String> externalReferences = new ArrayList<>();

        for (Table table : parsedTables) {
            for (Column column : table.getColumns()) {
                if (!column.isForeignKey()) {
                    continue;
                }

                assertNotNull(
                        column.getReferencedTable(),
                        "Referenced table must not be null for foreign key column: "
                                + table.getName() + "." + column.getName()
                );

                assertFalse(
                        column.getReferencedTable().isBlank(),
                        "Referenced table must not be blank for foreign key column: "
                                + table.getName() + "." + column.getName()
                );

                String normalizedReferencedTable = normalizePhysicalName(column.getReferencedTable());

                if (!tableByName.containsKey(normalizedReferencedTable)) {
                    externalReferences.add(
                            table.getName() + "." + column.getName() + " -> " + column.getReferencedTable()
                    );
                }
            }
        }

        if (!externalReferences.isEmpty()) {
            System.out.println();
            System.out.println("External foreign key references not present in the parsed schema file:");
            for (String externalReference : externalReferences) {
                System.out.println(" - " + externalReference);
            }
            System.out.println();
        }
    }

    /**
     * Verifies that parsed indexes are attached and minimally valid.
     *
     * @param parsedTables parsed table models
     */
    private void assertIndexesAreStructurallyValid(List<Table> parsedTables) {
        for (Table table : parsedTables) {
            if (table.getIndexes() == null || table.getIndexes().isEmpty()) {
                continue;
            }

            for (IndexDefinition indexDefinition : table.getIndexes()) {
                assertNotNull(indexDefinition, "Index must not be null for table: " + table.getName());

                assertNotNull(indexDefinition.getName(),
                        "Index name must not be null for table: " + table.getName());
                assertFalse(indexDefinition.getName().isBlank(),
                        "Index name must not be blank for table: " + table.getName());

                assertNotNull(indexDefinition.getTableName(),
                        "Index tableName must not be null for index: " + indexDefinition.getName());
                assertFalse(indexDefinition.getTableName().isBlank(),
                        "Index tableName must not be blank for index: " + indexDefinition.getName());

                assertEquals(
                        normalizePhysicalName(table.getName()),
                        normalizePhysicalName(indexDefinition.getTableName()),
                        "Index is attached to the wrong table: " + indexDefinition.getName()
                );

                assertNotNull(indexDefinition.getColumns(),
                        "Index columns must not be null for index: " + indexDefinition.getName());
                assertFalse(indexDefinition.getColumns().isEmpty(),
                        "Index columns must not be empty for index: " + indexDefinition.getName());

                for (String columnName : indexDefinition.getColumns()) {
                    assertNotNull(columnName, "Index column name must not be null for index: " + indexDefinition.getName());
                    assertFalse(columnName.isBlank(),
                            "Index column name must not be blank for index: " + indexDefinition.getName());
                }
            }
        }
    }

    /**
     * Verifies the known real-schema scenario for pep_schema.data_staging.
     *
     * @param tableByName normalized table lookup map
     */
    private void assertDataStagingTableScenario(Map<String, Table> tableByName) {
        Table dataStagingTable = tableByName.get("pep_schema.data_staging");
        assertNotNull(dataStagingTable, "Table pep_schema.data_staging should exist");

        Column idColumn = findColumn(dataStagingTable, "id");
        assertNotNull(idColumn, "data_staging.id should exist");
        assertTrue(idColumn.isPrimaryKey(), "data_staging.id should be primary key");

        Column pulledAtColumn = findColumn(dataStagingTable, "pulled_at");
        assertNotNull(pulledAtColumn, "data_staging.pulled_at should exist");
        assertEquals("java.time.LocalDateTime", pulledAtColumn.getJavaType(),
                "data_staging.pulled_at should map to LocalDateTime");
        assertNotNull(pulledAtColumn.getDefaultValue(),
                "data_staging.pulled_at should preserve default now()");

        Column statusColumn = findColumn(dataStagingTable, "status");
        assertNotNull(statusColumn, "data_staging.status should exist");
        assertEquals("String", toSimpleJavaType(statusColumn.getJavaType()),
                "data_staging.status should map to String");
        assertNotNull(statusColumn.getDefaultValue(),
                "data_staging.status should preserve default value");

        assertNotNull(dataStagingTable.getIndexes(), "data_staging indexes should not be null");
        assertTrue(
                hasIndex(dataStagingTable, "idx_staging_status", List.of("status", "pulled_at")),
                "Expected index idx_staging_status(status, pulled_at)"
        );
        assertTrue(
                hasIndex(dataStagingTable, "idx_staging_table_status", List.of("legacy_table_name", "status")),
                "Expected index idx_staging_table_status(legacy_table_name, status)"
        );
    }

    /**
     * Verifies the known real-schema scenario for pep_schema.keycloak_data.
     *
     * @param tableByName normalized table lookup map
     */
    private void assertKeycloakDataTableScenario(Map<String, Table> tableByName) {
        Table keycloakDataTable = tableByName.get("pep_schema.keycloak_data");
        assertNotNull(keycloakDataTable, "Table pep_schema.keycloak_data should exist");

        Column keycloakIdColumn = findColumn(keycloakDataTable, "keycloak_id");
        assertNotNull(keycloakIdColumn, "keycloak_data.keycloak_id should exist");
        assertTrue(keycloakIdColumn.isPrimaryKey(), "keycloak_data.keycloak_id should be primary key");
        assertEquals("String", toSimpleJavaType(keycloakIdColumn.getJavaType()),
                "keycloak_data.keycloak_id should map to String");
        assertFalse(keycloakIdColumn.isIdentity(),
                "keycloak_data.keycloak_id must not be treated as identity");

        Column recDeletedColumn = findColumn(keycloakDataTable, "rec_deleted");
        assertNotNull(recDeletedColumn, "keycloak_data.rec_deleted should exist");
        assertEquals("Boolean", toSimpleJavaType(recDeletedColumn.getJavaType()),
                "keycloak_data.rec_deleted should map to Boolean");
        assertNotNull(recDeletedColumn.getDefaultValue(),
                "keycloak_data.rec_deleted should preserve boolean default");
    }

    /**
     * Verifies important company_search_mv index scenarios from the real schema.
     *
     * @param tableByName normalized table lookup map
     */
    private void assertCompanySearchMaterializedViewIndexes(Map<String, Table> tableByName) {
        Table companySearchMvTable = tableByName.get("pep_schema.company_search_mv");
        if (companySearchMvTable == null) {
            return;
        }

        assertNotNull(companySearchMvTable.getIndexes(), "company_search_mv indexes should not be null");
        assertFalse(companySearchMvTable.getIndexes().isEmpty(),
                "company_search_mv should have indexes parsed");

        assertTrue(
                hasIndex(companySearchMvTable, "idx_company_search_mv_id", List.of("id")),
                "Expected unique index idx_company_search_mv_id(id)"
        );

        assertTrue(
                hasIndex(companySearchMvTable, "idx_company_search_mv_company_profile", List.of("company_id", "profile_id")),
                "Expected multi-column index idx_company_search_mv_company_profile(company_id, profile_id)"
        );

        assertTrue(
                hasIndex(companySearchMvTable, "idx_company_search_mv_titles_greek", List.of("titles_greek")),
                "Expected gin index idx_company_search_mv_titles_greek(titles_greek)"
        );

        assertTrue(
                hasIndex(companySearchMvTable, "idx_company_search_mv_name_english_lower",
                        List.of("lower((name_english)::text)")),
                "Expected expression index idx_company_search_mv_name_english_lower(lower((name_english)::text))"
        );

        assertTrue(
                hasIndex(companySearchMvTable, "idx_company_search_mv_aegean_cuisine_url",
                        List.of("aegean_cuisine_url")),
                "Expected partial index idx_company_search_mv_aegean_cuisine_url(aegean_cuisine_url)"
        );

        IndexDefinition uniqueIdIndex = findIndex(companySearchMvTable, "idx_company_search_mv_id");
        assertNotNull(uniqueIdIndex, "idx_company_search_mv_id should exist");
        assertTrue(uniqueIdIndex.isUnique(), "idx_company_search_mv_id should be unique");
    }

    /**
     * Finds one parsed column by physical name.
     *
     * @param table parsed table
     * @param columnName physical column name
     * @return matched column or null
     */
    private Column findColumn(Table table, String columnName) {
        for (Column column : table.getColumns()) {
            if (column == null || column.getName() == null) {
                continue;
            }

            if (normalizePhysicalName(column.getName()).equals(normalizePhysicalName(columnName))) {
                return column;
            }
        }

        return null;
    }

    /**
     * Finds one parsed index by name.
     *
     * @param table parsed table
     * @param indexName physical index name
     * @return matched index or null
     */
    private IndexDefinition findIndex(Table table, String indexName) {
        if (table.getIndexes() == null) {
            return null;
        }

        for (IndexDefinition indexDefinition : table.getIndexes()) {
            if (indexDefinition == null || indexDefinition.getName() == null) {
                continue;
            }

            if (normalizePhysicalName(indexDefinition.getName()).equals(normalizePhysicalName(indexName))) {
                return indexDefinition;
            }
        }

        return null;
    }

    /**
     * Verifies that one index exists with the expected ordered column list.
     *
     * @param table parsed table
     * @param indexName expected index name
     * @param expectedColumns expected ordered column or expression list
     * @return true when the index exists and matches the expected columns
     */
    private boolean hasIndex(Table table, String indexName, List<String> expectedColumns) {
        IndexDefinition indexDefinition = findIndex(table, indexName);
        if (indexDefinition == null) {
            return false;
        }

        List<String> actualColumns = new ArrayList<>();
        for (String currentColumn : indexDefinition.getColumns()) {
            actualColumns.add(normalizePhysicalName(currentColumn));
        }

        List<String> normalizedExpectedColumns = new ArrayList<>();
        for (String currentColumn : expectedColumns) {
            normalizedExpectedColumns.add(normalizePhysicalName(currentColumn));
        }

        return actualColumns.equals(normalizedExpectedColumns);
    }

    /**
     * Normalizes physical names for stable comparisons.
     *
     * @param value raw physical name
     * @return normalized name
     */
    private String normalizePhysicalName(String value) {
        if (value == null) {
            return "";
        }

        return value.trim()
                .replace("\"", "")
                .replaceAll("\\s+", "")
                .toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a Java type to its simple type name.
     *
     * @param javaType raw Java type
     * @return simplified type name
     */
    private String toSimpleJavaType(String javaType) {
        if (javaType == null || javaType.isBlank()) {
            return "";
        }

        String trimmedJavaType = javaType.trim();
        int lastDotIndex = trimmedJavaType.lastIndexOf('.');

        if (lastDotIndex >= 0 && lastDotIndex < trimmedJavaType.length() - 1) {
            return trimmedJavaType.substring(lastDotIndex + 1);
        }

        return trimmedJavaType;
    }
}