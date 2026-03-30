package com.sqldomaingen;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.parser.ColumnDefinition;
import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.SQLParser;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class CreateTableDefinitionTest {

    /**
     * Helper method that builds SQLParser and returns the first CREATE TABLE context.
     */
    private PostgreSQLParser.CreateTableStatementContext getCreateTableContext(String sql) {
        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql);

        ParseTree parseTree = sqlParser.parseTreeFromSQL();
        assertNotNull(parseTree, "ParseTree must not be null.");

        PostgreSQLParser.CreateTableStatementContext ctx = null;
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (parseTree.getChild(i) instanceof PostgreSQLParser.CreateTableStatementContext) {
                ctx = (PostgreSQLParser.CreateTableStatementContext) parseTree.getChild(i);
                break;
            }
        }

        assertNotNull(ctx, "CREATE TABLE statement was not found.");
        return ctx;
    }

    /**
     * Helper method that returns a column from the generated Table model by name.
     */
    private Column findColumn(Table table, String columnName) {
        assertNotNull(table, "Table must not be null.");
        assertNotNull(table.getColumns(), "Table columns must not be null.");

        return table.getColumns().stream()
                .filter(c -> c != null && columnName.equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Column not found: " + columnName));
    }

    /**
     * Helper method to normalize SQL type strings for stable assertions.
     */
    private String normalizeSqlType(String sqlType) {
        if (sqlType == null) {
            return "";
        }
        return sqlType.trim().replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);
    }

    /**
     * Helper method to normalize default values for stable assertions.
     */
    private String normalizeDefaultValue(String defaultValue) {
        if (defaultValue == null) {
            return "";
        }

        return defaultValue
                .trim()
                .toLowerCase(Locale.ROOT)
                .replace("(", "")
                .replace(")", "")
                .replace("::boolean", "")
                .replace("::bool", "")
                .replace("'", "")
                .replaceAll("\\s+", " ");
    }

    @Test
    void testExtractTableName() {
        String sql = "CREATE TABLE orders (id INT PRIMARY KEY, name VARCHAR(100));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        tableDefinition.extractTableName(ctx);

        String tableName = tableDefinition.getTableName();
        log.info("Extracted table name: {}", tableName);

        assertNotNull(tableName, "Table name must not be null.");
        assertEquals("orders", tableName, "Unexpected table name.");
    }

    @Test
    void testExtractColumns() {
        String sql = "CREATE TABLE customers (id SERIAL, name VARCHAR(255), age INT NOT NULL);";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx);

        assertNotNull(columns, "Columns were not extracted.");
        assertEquals(3, columns.size(), "Unexpected number of columns.");

        ColumnDefinition ageColumn = columns.stream()
                .filter(col -> "age".equals(col.getColumnName()))
                .findFirst()
                .orElse(null);

        assertNotNull(ageColumn, "Column 'age' was not found.");
        assertFalse(ageColumn.isNullable(), "Column 'age' must be NOT NULL.");
    }

    @Test
    void testExtractPrimaryKeys() {
        String sql = "CREATE TABLE employees (id INT PRIMARY KEY, email VARCHAR(100), name VARCHAR(50));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx);

        List<String> primaryKeys = columns.stream()
                .filter(ColumnDefinition::isPrimaryKey)
                .map(ColumnDefinition::getColumnName)
                .toList();

        assertNotNull(primaryKeys, "Primary key list must not be null.");
        assertEquals(1, primaryKeys.size(), "Unexpected number of primary keys.");
        assertEquals("id", primaryKeys.get(0), "Primary key column was not extracted correctly.");
    }

    @Test
    void testExtractConstraints() {
        String sql = "CREATE TABLE products (id INT PRIMARY KEY, code VARCHAR(50) UNIQUE, price DECIMAL(10,2) CHECK (price > 0));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx);

        boolean hasPrimaryKey = columns.stream().anyMatch(ColumnDefinition::isPrimaryKey);
        boolean hasUnique = columns.stream().anyMatch(ColumnDefinition::isUnique);
        boolean hasCheck = columns.stream().anyMatch(col -> col.getCheckConstraint() != null);

        assertTrue(hasPrimaryKey, "PRIMARY KEY was not extracted.");
        assertTrue(hasUnique, "UNIQUE was not extracted.");
        assertTrue(hasCheck, "CHECK constraint was not extracted.");
    }

    @Test
    void testExtractForeignKeys() {
        String sql = "CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT REFERENCES customers(id));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Table table = tableDefinition.processCreateTable(ctx);

        assertNotNull(table, "Table must not be null.");

        assertNotNull(tableDefinition.getColumnDefinitions(), "Column definitions were not extracted.");
        assertEquals(2, tableDefinition.getColumnDefinitions().size(), "Unexpected number of columns.");

        List<String> foreignKeys = tableDefinition.getColumnDefinitions().stream()
                .filter(ColumnDefinition::isForeignKey)
                .map(col -> col.getColumnName() + " -> " + col.getReferencedTable() + "(" + col.getReferencedColumn() + ")")
                .toList();

        assertEquals(1, foreignKeys.size(), "Unexpected number of foreign keys.");
        assertTrue(foreignKeys.contains("customer_id -> customers(id)"), "Foreign key was not extracted correctly.");
    }

    @Test
    void testParseAllTables() {
        String sql1 = "CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT REFERENCES customers(id));";
        String sql2 = "CREATE TABLE customers (id SERIAL PRIMARY KEY, name VARCHAR(255));";

        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql1 + " " + sql2);
        ParseTree parseTree = sqlParser.parseTreeFromSQL();

        List<PostgreSQLParser.CreateTableStatementContext> createTableStatements = new ArrayList<>();
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (parseTree.getChild(i) instanceof PostgreSQLParser.CreateTableStatementContext) {
                createTableStatements.add((PostgreSQLParser.CreateTableStatementContext) parseTree.getChild(i));
            }
        }

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Map<String, Table> tableMap = tableDefinition.parseAllTables(createTableStatements);

        assertNotNull(tableMap, "Table map must not be null.");
        assertEquals(2, tableMap.size(), "Tables were not extracted correctly.");

        assertTrue(tableMap.containsKey("orders"), "Table 'Orders' is missing.");
        assertTrue(tableMap.containsKey("customers"), "Table 'Customers' is missing.");

        assertEquals(2, tableMap.get("orders").getColumns().size(), "Unexpected column count for 'Orders'.");
        assertEquals(2, tableMap.get("customers").getColumns().size(), "Unexpected column count for 'Customers'.");
    }

    @Test
    void testParseAllTablesWithVerboseLogging() {
        log.info("Starting test: testParseAllTablesWithVerboseLogging");

        String sql1 = "CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT REFERENCES customers(id));";
        String sql2 = "CREATE TABLE customers (id SERIAL PRIMARY KEY, name VARCHAR(255));";

        log.info("SQL scripts:\n1) {}\n2) {}", sql1, sql2);

        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql1 + " " + sql2);
        ParseTree parseTree = sqlParser.parseTreeFromSQL();

        log.info("ParseTree created with {} children.", parseTree.getChildCount());

        List<PostgreSQLParser.CreateTableStatementContext> createTableStatements = new ArrayList<>();
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (parseTree.getChild(i) instanceof PostgreSQLParser.CreateTableStatementContext) {
                createTableStatements.add((PostgreSQLParser.CreateTableStatementContext) parseTree.getChild(i));
                log.info("Found CREATE TABLE node: {}", parseTree.getChild(i).getText());
            }
        }

        log.info("Total CREATE TABLE statements found: {}", createTableStatements.size());

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Map<String, Table> tableMap = tableDefinition.parseAllTables(createTableStatements);

        assertNotNull(tableMap, () -> {
            log.error("Table map is null.");
            return "Table map must not be null.";
        });
        log.info("Table map is not null.");

        assertEquals(2, tableMap.size(), () -> {
            log.error("Unexpected table count. Expected: 2, Found: {}", tableMap.size());
            return "Tables were not extracted correctly.";
        });
        log.info("Table count is correct: {}", tableMap.size());

        assertTrue(tableMap.containsKey("orders"), () -> {
            log.error("Table 'Orders' is missing.");
            return "Table 'Orders' is missing.";
        });
        log.info("Table 'Orders' found.");

        assertTrue(tableMap.containsKey("customers"), () -> {
            log.error("Table 'Customers' is missing.");
            return "Table 'Customers' is missing.";
        });
        log.info("Table 'Customers' found.");

        assertEquals(2, tableMap.get("orders").getColumns().size(), () -> {
            log.error("Unexpected column count for 'Orders'. Expected: 2, Found: {}",
                    tableMap.get("Orders").getColumns().size());
            return "Unexpected column count for 'Orders'.";
        });
        log.info("Column count for 'Orders' is correct.");

        assertEquals(2, tableMap.get("customers").getColumns().size(), () -> {
            log.error("Unexpected column count for 'Customers'. Expected: 2, Found: {}",
                    tableMap.get("Customers").getColumns().size());
            return "Unexpected column count for 'Customers'.";
        });
        log.info("Column count for 'Customers' is correct.");

        log.info("Test completed successfully.");
    }

    @Test
    void testProcessCreateTable_companyBgCooperationI18n_shouldPreserveExactMetadata() {
        String sql = """
                CREATE TABLE pep_schema.company_bg_cooperation_i18n (
                    id uuid NOT NULL,
                    description VARCHAR(1000) NULL,
                    date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                    last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                    rec_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                    cooperation_id uuid NOT NULL,
                    language_id uuid NOT NULL,
                    CONSTRAINT company_bg_cooperation_i18n_pkey PRIMARY KEY (id)
                );
                """;

        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Table table = tableDefinition.processCreateTable(ctx);

        assertNotNull(table, "Processed table must not be null.");
        assertNotNull(table.getColumns(), "Processed table columns must not be null.");

        assertEquals(7, table.getColumns().size(), "Unexpected column count.");

        List<String> actualColumnNames = table.getColumns().stream()
                .map(Column::getName)
                .toList();

        List<String> expectedColumnNames = List.of(
                "id",
                "description",
                "date_created",
                "last_updated",
                "rec_deleted",
                "cooperation_id",
                "language_id"
        );

        assertEquals(expectedColumnNames, actualColumnNames, "Column names/order mismatch.");

        Column id = findColumn(table, "id");
        assertTrue(id.isPrimaryKey(), "Column 'id' must be primary key.");
        assertFalse(id.isNullable(), "Column 'id' must be NOT NULL.");

        Column description = findColumn(table, "description");
        assertTrue(description.isNullable(), "Column 'description' must be nullable.");
        assertEquals(1000, description.getLength(), "Column 'description' length must be 1000.");
        assertTrue(normalizeSqlType(description.getSqlType()).contains("VARCHAR"),
                "Column 'description' SQL type must be VARCHAR.");
        assertTrue(normalizeSqlType(description.getSqlType()).contains("1000"),
                "Column 'description' SQL type must preserve size 1000.");

        Column dateCreated = findColumn(table, "date_created");
        assertFalse(dateCreated.isNullable(), "Column 'date_created' must be NOT NULL.");
        assertTrue(normalizeSqlType(dateCreated.getSqlType()).startsWith("TIMESTAMP"),
                "Column 'date_created' SQL type must be TIMESTAMP.");
        assertTrue(normalizeDefaultValue(dateCreated.getDefaultValue()).contains("current_timestamp"),
                "Column 'date_created' default must be CURRENT_TIMESTAMP.");

        Column lastUpdated = findColumn(table, "last_updated");
        assertFalse(lastUpdated.isNullable(), "Column 'last_updated' must be NOT NULL.");
        assertTrue(normalizeSqlType(lastUpdated.getSqlType()).startsWith("TIMESTAMP"),
                "Column 'last_updated' SQL type must be TIMESTAMP.");
        assertTrue(normalizeDefaultValue(lastUpdated.getDefaultValue()).contains("current_timestamp"),
                "Column 'last_updated' default must be CURRENT_TIMESTAMP.");

        Column recDeleted = findColumn(table, "rec_deleted");
        assertFalse(recDeleted.isNullable(), "Column 'rec_deleted' must be NOT NULL.");
        String recDeletedType = normalizeSqlType(recDeleted.getSqlType());
        assertTrue(recDeletedType.startsWith("BOOL") || recDeletedType.startsWith("BOOLEAN"),
                "Column 'rec_deleted' SQL type must be BOOL/BOOLEAN.");
        String recDeletedDefault = normalizeDefaultValue(recDeleted.getDefaultValue());
        assertTrue(recDeletedDefault.equals("false") || recDeletedDefault.equals("0"),
                "Column 'rec_deleted' default must be FALSE.");

        Column cooperationId = findColumn(table, "cooperation_id");
        assertFalse(cooperationId.isNullable(), "Column 'cooperation_id' must be NOT NULL.");

        Column languageId = findColumn(table, "language_id");
        assertFalse(languageId.isNullable(), "Column 'language_id' must be NOT NULL.");

        List<String> pkColumns = table.getColumns().stream()
                .filter(Column::isPrimaryKey)
                .map(Column::getName)
                .toList();

        assertEquals(List.of("id"), pkColumns, "Primary key columns mismatch.");
    }

    @Test
    void testExtractColumns_VarcharLengthUppercase_ShouldPreserveLength1000() {
        String sql = """
        CREATE TABLE company_bg_cooperation_i18n (
            id uuid NOT NULL,
            description VARCHAR(1000) NULL,
            language_id uuid NOT NULL
        );
        """;

        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Table table = tableDefinition.processCreateTable(ctx);

        assertNotNull(table);
        assertNotNull(table.getColumns());
        assertEquals(3, table.getColumns().size(), "Expected exactly 3 columns");

        Column description = table.getColumns().stream()
                .filter(c -> "description".equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Column 'description' not found"));

        assertEquals("VARCHAR(1000)", description.getSqlType(), "SQL type should be preserved/normalized");
        assertEquals(1000, description.getLength(), "Length for description must be 1000");
        assertTrue(description.isNullable(), "description should be nullable");
    }

    @Test
    void testExtractColumns_VarcharLengthLowercase_ShouldPreserveLength1000() {
        String sql = """
        CREATE TABLE company_bg_cooperation_i18n (
            id uuid NOT NULL,
            description varchar(1000) NULL,
            language_id uuid NOT NULL
        );
        """;

        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Table table = tableDefinition.processCreateTable(ctx);

        assertNotNull(table);
        assertNotNull(table.getColumns());
        assertEquals(3, table.getColumns().size(), "Expected exactly 3 columns");

        Column description = table.getColumns().stream()
                .filter(c -> "description".equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Column 'description' not found"));

        assertEquals("VARCHAR(1000)", description.getSqlType(), "SQL type should be normalized to uppercase");
        assertEquals(1000, description.getLength(), "Length for lowercase varchar must still be 1000");
        assertTrue(description.isNullable(), "description should be nullable");
    }


    @Test
    void testExtractColumns_NumericPrecisionAndScale_ShouldBePreservedInColumnModel() {
        String sql = """
        CREATE TABLE income_gemi_payment (
            chamber_amount NUMERIC(19, 2) NULL,
            total_amount_paid NUMERIC(19, 2) NULL
        );
        """;

        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Table table = tableDefinition.processCreateTable(ctx);

        assertNotNull(table);
        assertNotNull(table.getColumns());
        assertEquals(2, table.getColumns().size(), "Expected exactly 2 columns");

        Column chamberAmount = findColumn(table, "chamber_amount");

        assertEquals("NUMERIC(19, 2)", chamberAmount.getSqlType(), "SQL type must preserve precision/scale");
        assertEquals(19, chamberAmount.getPrecision(), "Precision must be 19");
        assertEquals(2, chamberAmount.getScale(), "Scale must be 2");

        Column totalAmount = findColumn(table, "total_amount_paid");

        assertEquals("NUMERIC(19, 2)", totalAmount.getSqlType(), "SQL type must preserve precision/scale");
        assertEquals(19, totalAmount.getPrecision(), "Precision must be 19");
        assertEquals(2, totalAmount.getScale(), "Scale must be 2");
    }
}