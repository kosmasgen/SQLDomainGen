package com.sqldomaingen;

import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.SQLParser;
import com.sqldomaingen.parser.ColumnDefinition;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.model.Table;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateTableDefinitionTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateTableDefinitionTest.class);

    /**
     * 🛠️ Βοηθητική μέθοδος για τη δημιουργία SQLParser & ParseTree
     */
    private PostgreSQLParser.CreateTableStatementContext getCreateTableContext(String sql) {
        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql);
        ParseTree parseTree = sqlParser.parseTreeFromSQL();
        assertNotNull(parseTree, "Το ParseTree είναι null!");

        PostgreSQLParser.CreateTableStatementContext ctx = null;
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (parseTree.getChild(i) instanceof PostgreSQLParser.CreateTableStatementContext) {
                ctx = (PostgreSQLParser.CreateTableStatementContext) parseTree.getChild(i);
                break;
            }
        }
        assertNotNull(ctx, "Δεν βρέθηκε CREATE TABLE statement!");
        return ctx;
    }

    @Test
    void testExtractTableName() {
        String sql = "CREATE TABLE orders (id INT PRIMARY KEY, name VARCHAR(100));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        tableDefinition.extractTableName(ctx);

        String tableName = tableDefinition.getTableName();
        logger.info("Extracted Table Name: {}", tableName);

        assertNotNull(tableName, "Το όνομα του πίνακα είναι null!");
        assertEquals("orders", tableName, "Το όνομα του πίνακα δεν είναι σωστό!");
    }

    @Test
    void testExtractColumns() {
        String sql = "CREATE TABLE customers (id SERIAL, name VARCHAR(255), age INT NOT NULL);";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx); // ✅ Χρήση της σωστής μεθόδου

        assertNotNull(columns, "Οι στήλες δεν εξήχθησαν σωστά!");
        assertEquals(3, columns.size(), "Λάθος αριθμός στηλών!");

        ColumnDefinition ageColumn = columns.stream()
                .filter(col -> col.getColumnName().equals("age"))
                .findFirst()
                .orElse(null);
        assertNotNull(ageColumn, "Η στήλη 'age' δεν βρέθηκε!");
        assertFalse(ageColumn.isNullable(), "Η στήλη 'age' πρέπει να έχει NOT NULL constraint!");
    }

    @Test
    void testExtractPrimaryKeys() {
        String sql = "CREATE TABLE employees (id INT PRIMARY KEY, email VARCHAR(100), name VARCHAR(50));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx); // ✅ Χρήση της σωστής μεθόδου

        List<String> primaryKeys = columns.stream()
                .filter(ColumnDefinition::isPrimaryKey)
                .map(ColumnDefinition::getColumnName)
                .toList(); // ✅ Ανάκτηση primary keys μέσω των ColumnDefinition

        assertNotNull(primaryKeys, "Το primary key δεν εξήχθη!");
        assertEquals(1, primaryKeys.size(), "Λάθος αριθμός primary keys!");
        assertEquals("id", primaryKeys.get(0), "Το primary key δεν εξήχθη σωστά!");
    }

    @Test
    void testExtractConstraints() {
        String sql = "CREATE TABLE products (id INT PRIMARY KEY, code VARCHAR(50) UNIQUE, price DECIMAL(10,2) CHECK (price > 0));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        List<ColumnDefinition> columns = tableDefinition.extractColumnDefinitions(ctx); // ✅ Χρήση της σωστής μεθόδου

        // Εξαγωγή constraints
        boolean hasPrimaryKey = columns.stream().anyMatch(ColumnDefinition::isPrimaryKey);
        boolean hasUnique = columns.stream().anyMatch(ColumnDefinition::isUnique);
        boolean hasCheck = columns.stream().anyMatch(col -> col.getCheckConstraint() != null);

        assertTrue(hasPrimaryKey, "Δεν βρέθηκε το PRIMARY KEY!");
        assertTrue(hasUnique, "Δεν βρέθηκε το UNIQUE!");
        assertTrue(hasCheck, "Δεν βρέθηκε το CHECK constraint!");
    }

    @Test
    void testExtractForeignKeys() {
        String sql = "CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT REFERENCES customers(id));";
        PostgreSQLParser.CreateTableStatementContext ctx = getCreateTableContext(sql);

        CreateTableDefinition tableDefinition = new CreateTableDefinition();

        // ✅ Χρήση processCreateTable() για να εξασφαλίσουμε ότι όλα τα δεδομένα αποθηκεύονται σωστά
        Table table = tableDefinition.processCreateTable(ctx);

        // ✅ Έλεγχος ότι εξήχθησαν οι στήλες
        assertNotNull(tableDefinition.getColumnDefinitions(), "Οι στήλες δεν εξήχθησαν!");
        assertEquals(2, tableDefinition.getColumnDefinitions().size(), "Λάθος αριθμός στηλών!");


        // ✅ Έλεγχος αν η foreign key υπάρχει
        List<String> foreignKeys = tableDefinition.getColumnDefinitions().stream()
                .filter(ColumnDefinition::isForeignKey)
                .map(col -> col.getColumnName() + " -> " + col.getReferencedTable() + "(" + col.getReferencedColumn() + ")")
                .toList();

        assertEquals(1, foreignKeys.size(), "Λάθος αριθμός foreign keys!");
        assertTrue(foreignKeys.contains("customer_id -> customers(id)"), "Η foreign key δεν εξήχθη σωστά!");
    }

}
