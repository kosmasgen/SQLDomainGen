package com.sqldomaingen.schemaValidation;

import com.sqldomaingen.generator.LiquibaseGenerator;
import com.sqldomaingen.model.IndexDefinition;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.parser.CreateIndexDefinition;
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
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies Liquibase generation for the data_staging table using the real schema file.
 */
class LiquibaseDataStagingGenerationTest {

    private static final String TARGET_TABLE_NAME = "pep_schema.data_staging";
    private static final String TARGET_CHANGELOG_FILE = "dataStaging.xml";

    @TempDir
    Path tempDir;

    /**
     * Verifies that the generated Liquibase changelog for data_staging preserves
     * the expected PostgreSQL semantics for identity, varchar length, defaults,
     * unique constraint, indexes, and audit table generation.
     *
     * @throws Exception if parsing, generation, or file reading fails
     */
    @Test
    void shouldGenerateCorrectLiquibaseForDataStagingTableFromRealSchema() throws Exception {
        Path schemaPath = Constants.SCHEMA_PATH;
        assertTrue(Files.exists(schemaPath), "Missing schema file: " + schemaPath.toAbsolutePath());

        String sql = Files.readString(schemaPath);
        Table dataStagingTable = parseTargetTable(sql);
        assertNotNull(dataStagingTable, "Table not found in parsed schema: " + TARGET_TABLE_NAME);

        LiquibaseGenerator liquibaseGenerator = new LiquibaseGenerator();
        liquibaseGenerator.generateLiquibaseFiles(
                tempDir.toString(),
                List.of(dataStagingTable),
                true,
                "tester@knowledge.gr"
        );

        Path changelogPath = tempDir.resolve(
                "src/main/resources/db/migration/changelogs/v0.1.0/" + TARGET_CHANGELOG_FILE
        );

        assertTrue(Files.exists(changelogPath), "Expected generated changelog file was not found");

        String xml = Files.readString(changelogPath);

        assertContains(xml, "<createTable tableName=\"data_staging\" schemaName=\"pep_schema\">");
        assertContains(xml, "<column name=\"id\" type=\"BIGINT\" autoIncrement=\"true\">");
        assertContains(xml, "<column name=\"legacy_table_name\" type=\"VARCHAR(100)\">");
        assertContains(xml, "<column name=\"legacy_record_id\" type=\"VARCHAR(255)\">");
        assertContains(xml, "<column name=\"raw_data\" type=\"JSONB\">");
        assertContains(xml, "<column name=\"legacy_updated_at\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"pulled_at\" type=\"TIMESTAMP\" defaultValueComputed=\"now()\">");
        assertContains(xml, "<column name=\"status\" type=\"VARCHAR(20)\" defaultValue=\"PENDING\">");

        assertContains(
                xml,
                "<addUniqueConstraint tableName=\"data_staging\" schemaName=\"pep_schema\" " +
                        "columnNames=\"legacy_table_name, legacy_record_id, legacy_updated_at\" " +
                        "constraintName=\"data_staging_legacy_table_name_legacy_record_id_legacy_upda_key\"/>"
        );

        assertContains(
                xml,
                "<createIndex tableName=\"data_staging\" schemaName=\"pep_schema\" indexName=\"idx_staging_status\">"
        );
        assertContains(
                xml,
                "<createIndex tableName=\"data_staging\" schemaName=\"pep_schema\" indexName=\"idx_staging_table_status\">"
        );

        assertContains(xml, "<createTable tableName=\"data_staging_aud\" schemaName=\"audit\">");
        assertContains(xml, "<column name=\"status\" type=\"VARCHAR(20)\"/>");
        assertContains(xml, "<addPrimaryKey tableName=\"data_staging_aud\"");
        assertContains(xml, "columnNames=\"id, rev\"");
        assertContains(xml, "constraintName=\"pk_data_staging_aud\"");
    }

    /**
     * Parses the real schema SQL, attaches indexes, and returns the target table definition.
     *
     * @param sql raw schema SQL
     * @return parsed target table or null when not found
     */
    private Table parseTargetTable(String sql) {
        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql);

        ParseTree parseTree = sqlParser.parseTreeFromSQL();

        CreateTableStatementCollector tableCollector = new CreateTableStatementCollector();
        ParseTreeWalker.DEFAULT.walk(tableCollector, parseTree);

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        Map<String, Table> tableMap = createTableDefinition.parseAllTables(tableCollector.getCreateTableStatements());

        CreateIndexStatementCollector indexCollector = new CreateIndexStatementCollector();
        ParseTreeWalker.DEFAULT.walk(indexCollector, parseTree);

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();
        indexCollector.getCreateIndexStatements().forEach(indexContext -> {
            try {
                IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);
                Table table = tableMap.get(indexDefinition.getTableName());
                if (table != null) {
                    if (table.getIndexes() == null) {
                        table.setIndexes(new ArrayList<>());
                    }
                    table.getIndexes().add(indexDefinition);
                }
            } catch (Exception exception) {
                throw new IllegalStateException(
                        "Failed to parse index statement: " + indexContext.getText(),
                        exception
                );
            }
        });

        return tableMap.get(TARGET_TABLE_NAME);
    }

    /**
     * Verifies that the generated XML contains the expected fragment.
     *
     * @param actualXml generated XML
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
     * Collects CREATE INDEX statement contexts from the parsed SQL tree.
     */
    @Getter
    private static class CreateIndexStatementCollector extends PostgreSQLBaseListener {

        /**
         * Collected CREATE INDEX statement contexts in encounter order.
         */
        private final List<PostgreSQLParser.CreateIndexStatementContext> createIndexStatements = new ArrayList<>();

        /**
         * Stores each encountered CREATE INDEX statement context.
         *
         * @param context the current CREATE INDEX statement context
         */
        @Override
        public void enterCreateIndexStatement(PostgreSQLParser.CreateIndexStatementContext context) {
            createIndexStatements.add(Objects.requireNonNull(context));
        }
    }
}