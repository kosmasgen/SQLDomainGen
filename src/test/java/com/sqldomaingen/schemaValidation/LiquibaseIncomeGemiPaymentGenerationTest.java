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
 * Verifies Liquibase generation for the income_gemi_payment table using the real schema file.
 */
class LiquibaseIncomeGemiPaymentGenerationTest {

    private static final String TARGET_TABLE_NAME = "pep_schema.income_gemi_payment";
    private static final String TARGET_CHANGELOG_FILE = "incomeGemiPayment.xml";

    @TempDir
    Path tempDir;

    /**
     * Verifies that the generated Liquibase changelog for income_gemi_payment preserves
     * the expected PostgreSQL semantics for primary key, varchar lengths, numeric precision,
     * unique constraint, indexes, and audit table generation.
     *
     * @throws Exception if parsing, generation, or file reading fails
     */
    @Test
    void shouldGenerateCorrectLiquibaseForIncomeGemiPaymentTableFromRealSchema() throws Exception {
        Path schemaPath = Constants.SCHEMA_PATH;
        assertTrue(Files.exists(schemaPath), "Missing schema file: " + schemaPath.toAbsolutePath());

        String sql = Files.readString(schemaPath);
        Table incomeGemiPaymentTable = parseTargetTable(sql);
        assertNotNull(incomeGemiPaymentTable, "Table not found in parsed schema: " + TARGET_TABLE_NAME);

        LiquibaseGenerator liquibaseGenerator = new LiquibaseGenerator();
        liquibaseGenerator.generateLiquibaseFiles(
                tempDir.toString(),
                List.of(incomeGemiPaymentTable),
                true,
                "tester@knowledge.gr"
        );

        Path changelogPath = tempDir.resolve(
                "src/main/resources/db/migration/changelogs/v0.1.0/" + TARGET_CHANGELOG_FILE
        );

        assertTrue(Files.exists(changelogPath), "Expected generated changelog file was not found");

        String xml = Files.readString(changelogPath);

        assertContains(xml, "<createTable tableName=\"income_gemi_payment\" schemaName=\"pep_schema\">");
        assertContains(xml, "<column name=\"id\" type=\"UUID\" defaultValueComputed=\"gen_random_uuid()\">");
        assertContains(xml, "<column name=\"chamber_id\" type=\"INTEGER\">");
        assertContains(xml, "<column name=\"payment_type\" type=\"VARCHAR(255)\">");
        assertContains(xml, "<column name=\"sale_ts\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"chamber_amount\" type=\"NUMERIC(19,2)\">");
        assertContains(xml, "<column name=\"chamber_amount_for_certs\" type=\"NUMERIC(19,2)\">");
        assertContains(xml, "<column name=\"chamber_amount_for_postal\" type=\"NUMERIC(19,2)\">");
        assertContains(xml, "<column name=\"total_amount_paid\" type=\"NUMERIC(19,2)\">");
        assertContains(xml, "<column name=\"descr\" type=\"VARCHAR(500)\">");
        assertContains(xml, "<column name=\"payer\" type=\"VARCHAR(500)\">");
        assertContains(xml, "<column name=\"gemi_payment_id\" type=\"NUMERIC\">");
        assertContains(xml, "<column name=\"company_gemi_id\" type=\"NUMERIC\">");
        assertContains(xml, "<column name=\"co_name\" type=\"VARCHAR(1000)\">");
        assertContains(xml, "<column name=\"company_chamber_id\" type=\"NUMERIC\">");
        assertContains(xml, "<column name=\"payment_method\" type=\"VARCHAR(255)\">");
        assertContains(xml, "<column name=\"ri3\" type=\"VARCHAR(25)\">");
        assertContains(xml, "<column name=\"subscription_start_date\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"subscription_end_date\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"cancel_flag\" type=\"NUMERIC\">");
        assertContains(xml, "<column name=\"refund_ts\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"remittance_dt\" type=\"TIMESTAMP\">");
        assertContains(xml, "<column name=\"remittance_amount\" type=\"NUMERIC(19,2)\">");
        assertContains(xml, "<column name=\"remittance_reference\" type=\"VARCHAR(20)\">");
        assertContains(xml, "<column name=\"last_updated\" type=\"TIMESTAMP\">");

        assertContains(
                xml,
                "<addUniqueConstraint tableName=\"income_gemi_payment\" schemaName=\"pep_schema\" " +
                        "columnNames=\"chamber_id, gemi_payment_id, payment_type, cancel_flag\" " +
                        "constraintName=\"uk_income_gemi_payment\"/>"
        );

        assertContains(
                xml,
                "<createIndex tableName=\"income_gemi_payment\" schemaName=\"pep_schema\" indexName=\"idx_gemi_pay_chamber\">"
        );
        assertContains(xml, "<column name=\"chamber_id\"/>");

        assertContains(
                xml,
                "<createIndex tableName=\"income_gemi_payment\" schemaName=\"pep_schema\" indexName=\"idx_gemi_pay_comp\">"
        );
        assertContains(xml, "<column name=\"company_gemi_id\"/>");

        assertContains(
                xml,
                "<createIndex tableName=\"income_gemi_payment\" schemaName=\"pep_schema\" indexName=\"idx_gemi_pay_dt\">"
        );
        assertContains(xml, "<column name=\"sale_ts\"/>");

        assertContains(xml, "<createTable tableName=\"income_gemi_payment_aud\" schemaName=\"audit\">");
        assertContains(xml, "<column name=\"payment_method\" type=\"VARCHAR(255)\"/>");
        assertContains(xml, "<addPrimaryKey tableName=\"income_gemi_payment_aud\"");
        assertContains(xml, "columnNames=\"id, rev\"");
        assertContains(xml, "constraintName=\"pk_income_gemi_payment_aud\"");
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