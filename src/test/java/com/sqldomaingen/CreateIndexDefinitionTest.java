package com.sqldomaingen;

import com.sqldomaingen.model.IndexDefinition;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.parser.CreateIndexDefinition;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.SQLParser;
import com.sqldomaingen.shell.GeneratorCommands;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class CreateIndexDefinitionTest {

    @Test
    void testProcessCreateIndex_WithSimpleMultiColumnIndex() {
        String sql = """
                CREATE INDEX idx_staging_status
                ON pep_schema.data_staging USING btree (status, pulled_at);
                """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_staging_status", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.data_staging", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(2, indexDefinition.getColumns().size(), "Expected two indexed columns.");
            assertEquals("status", indexDefinition.getColumns().get(0), "Unexpected first index column.");
            assertEquals("pulled_at", indexDefinition.getColumns().get(1), "Unexpected second index column.");

            assertFalse(indexDefinition.isUnique(), "Index should not be unique.");
        });
    }

    @Test
    void testProcessCreateIndex_WithSecondMultiColumnIndex() {
        String sql = """
                CREATE INDEX idx_staging_table_status
                ON pep_schema.data_staging USING btree (legacy_table_name, status);
                """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_staging_table_status", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.data_staging", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(2, indexDefinition.getColumns().size(), "Expected two indexed columns.");
            assertEquals("legacy_table_name", indexDefinition.getColumns().get(0), "Unexpected first index column.");
            assertEquals("status", indexDefinition.getColumns().get(1), "Unexpected second index column.");

            assertFalse(indexDefinition.isUnique(), "Index should not be unique.");
        });
    }

    @Test
    void testProcessCreateIndex_WithUniqueIndex() {
        String sql = """
                CREATE UNIQUE INDEX idx_company_email
                ON pep_schema.company USING btree (contact_email);
                """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_company_email", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.company", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(1, indexDefinition.getColumns().size(), "Expected one indexed column.");
            assertEquals("contact_email", indexDefinition.getColumns().getFirst(), "Unexpected indexed column.");

            assertTrue(indexDefinition.isUnique(), "Index should be unique.");
        });
    }


    @Test
    void testProcessCreateIndex_WithGinIndex() {
        String sql = """
            CREATE INDEX idx_company_search_mv_titles_greek
            ON pep_schema.company_search_mv USING gin (titles_greek);
            """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_company_search_mv_titles_greek", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.company_search_mv", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(1, indexDefinition.getColumns().size(), "Expected one indexed column.");
            assertEquals("titles_greek", indexDefinition.getColumns().getFirst(), "Unexpected indexed column.");

            assertFalse(indexDefinition.isUnique(), "Index should not be unique.");
        });
    }


    @Test
    void testProcessCreateIndex_WithPartialIndex() {
        String sql = """
            CREATE INDEX idx_company_search_mv_aegean_cuisine_url
            ON pep_schema.company_search_mv USING btree (aegean_cuisine_url)
            WHERE (aegean_cuisine_url IS NOT NULL);
            """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_company_search_mv_aegean_cuisine_url", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.company_search_mv", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(1, indexDefinition.getColumns().size(), "Expected one indexed column.");
            assertEquals("aegean_cuisine_url", indexDefinition.getColumns().getFirst(), "Unexpected indexed column.");

            assertFalse(indexDefinition.isUnique(), "Index should not be unique.");
            assertEquals("btree", indexDefinition.getUsingMethod(), "Unexpected index method.");
            assertEquals("(aegean_cuisine_url IS NOT NULL)", indexDefinition.getWhereClause(), "Unexpected WHERE clause.");
        });
    }


    @Test
    void testProcessCreateIndex_WithExpressionIndex() {
        String sql = """
            CREATE INDEX idx_company_search_mv_name_english_lower
            ON pep_schema.company_search_mv USING btree (lower((name_english)::text));
            """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        assertDoesNotThrow(() -> {
            IndexDefinition indexDefinition = createIndexDefinition.processCreateIndex(indexContext);

            assertNotNull(indexDefinition, "IndexDefinition should not be null.");
            assertEquals("idx_company_search_mv_name_english_lower", indexDefinition.getName(), "Unexpected index name.");
            assertEquals("pep_schema.company_search_mv", indexDefinition.getTableName(), "Unexpected table name.");

            assertNotNull(indexDefinition.getColumns(), "Index columns should not be null.");
            assertEquals(1, indexDefinition.getColumns().size(), "Expected one index element.");
            assertEquals("lower((name_english)::text)", indexDefinition.getColumns().getFirst(), "Unexpected expression index element.");

            assertFalse(indexDefinition.isUnique(), "Index should not be unique.");
        });
    }

    @Test
    void testProcessCreateIndex_WithMultipleIndexesInSameScript() {
        String sql = """
            CREATE INDEX idx_company_search_mv_afm
            ON pep_schema.company_search_mv USING btree (afm);

            CREATE UNIQUE INDEX idx_company_search_mv_id
            ON pep_schema.company_search_mv USING btree (id);

            CREATE INDEX idx_company_search_mv_titles_greek
            ON pep_schema.company_search_mv USING gin (titles_greek);

            CREATE INDEX idx_company_search_mv_aegean_cuisine_url
            ON pep_schema.company_search_mv USING btree (aegean_cuisine_url)
            WHERE (aegean_cuisine_url IS NOT NULL);
            """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(4, context.createIndexStatement().size(), "Expected exactly four CREATE INDEX statements.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        IndexDefinition firstIndex = createIndexDefinition.processCreateIndex(context.createIndexStatement().get(0));
        IndexDefinition secondIndex = createIndexDefinition.processCreateIndex(context.createIndexStatement().get(1));
        IndexDefinition thirdIndex = createIndexDefinition.processCreateIndex(context.createIndexStatement().get(2));
        IndexDefinition fourthIndex = createIndexDefinition.processCreateIndex(context.createIndexStatement().get(3));

        assertEquals("idx_company_search_mv_afm", firstIndex.getName());
        assertEquals("afm", firstIndex.getColumns().getFirst());
        assertFalse(firstIndex.isUnique());

        assertEquals("idx_company_search_mv_id", secondIndex.getName());
        assertEquals("id", secondIndex.getColumns().getFirst());
        assertTrue(secondIndex.isUnique());

        assertEquals("idx_company_search_mv_titles_greek", thirdIndex.getName());
        assertEquals("titles_greek", thirdIndex.getColumns().getFirst());
        assertFalse(thirdIndex.isUnique());

        assertEquals("idx_company_search_mv_aegean_cuisine_url", fourthIndex.getName());
        assertEquals("aegean_cuisine_url", fourthIndex.getColumns().getFirst());
        assertFalse(fourthIndex.isUnique());
    }


    @Test
    void testParseSQLToTables_WithIndexes_ShouldAttachIndexesToCorrectTable() {
        log.info("🔵 Running test: testParseSQLToTables_WithIndexes_ShouldAttachIndexesToCorrectTable");

        String sql = """
            CREATE TABLE pep_schema.data_staging (
                id bigserial NOT NULL,
                status varchar(20),
                pulled_at timestamp,
                legacy_table_name varchar(100),
                PRIMARY KEY (id)
            );

            CREATE INDEX idx_staging_status
            ON pep_schema.data_staging USING btree (status, pulled_at);

            CREATE INDEX idx_staging_table_status
            ON pep_schema.data_staging USING btree (legacy_table_name, status);
            """;

        GeneratorCommands generatorCommands = new GeneratorCommands();

        List<Table> tables = assertDoesNotThrow(() ->
                        generatorCommands.parseSQLToTables(sql),
                "Parsing SQL should not throw exception"
        );

        assertNotNull(tables);
        assertEquals(1, tables.size(), "Should parse exactly one table");

        Table table = tables.getFirst();

        assertEquals("pep_schema.data_staging", table.getName());

        assertNotNull(table.getIndexes(), "Indexes list should not be null");
        assertEquals(2, table.getIndexes().size(), "Table should have 2 indexes");

        IndexDefinition index1 = table.getIndexes().stream()
                .filter(index -> index.getName().equals("idx_staging_status"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("idx_staging_status not found"));

        IndexDefinition index2 = table.getIndexes().stream()
                .filter(index -> index.getName().equals("idx_staging_table_status"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("idx_staging_table_status not found"));

        assertEquals(List.of("status", "pulled_at"), index1.getColumns());
        assertEquals(List.of("legacy_table_name", "status"), index2.getColumns());

        log.info(" Table '{}' has correct indexes: {}", table.getName(), table.getIndexes());

    }

    @Test
    void testProcessCreateIndex_WithUniquePartialExpressionIndex() {
        String sql = """
        CREATE UNIQUE INDEX uq_test_email_active
        ON public.index_expression_test USING btree (lower((email)::text))
        WHERE deleted_at IS NULL;
        """;

        SQLParser sqlParser = new SQLParser(sql);
        PostgreSQLParser parser = sqlParser.createParser();
        PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

        assertNotNull(context, "SqlScriptContext should not be null.");
        assertEquals(1, context.createIndexStatement().size(), "Expected exactly one CREATE INDEX statement.");

        PostgreSQLParser.CreateIndexStatementContext indexContext = context.createIndexStatement().getFirst();
        assertNotNull(indexContext, "CreateIndexStatementContext should not be null.");

        CreateIndexDefinition createIndexDefinition = new CreateIndexDefinition();

        IndexDefinition indexDefinition = assertDoesNotThrow(
                () -> createIndexDefinition.processCreateIndex(indexContext)
        );

        assertNotNull(indexDefinition, "IndexDefinition should not be null.");
        assertEquals("uq_test_email_active", indexDefinition.getName(), "Unexpected index name.");
        assertEquals("public.index_expression_test", indexDefinition.getTableName(), "Unexpected table name.");
        assertEquals("btree", indexDefinition.getUsingMethod(), "Unexpected index method.");
        assertTrue(indexDefinition.isUnique(), "Index should be unique.");
        assertEquals(List.of("lower((email)::text)"), indexDefinition.getColumns(), "Unexpected index columns.");
        assertEquals("deleted_at IS NULL", indexDefinition.getWhereClause(), "Unexpected WHERE clause.");
    }

}