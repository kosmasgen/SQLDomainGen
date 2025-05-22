package com.sqldomaingen;

import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.SQLParser;
import com.sqldomaingen.parser.ColumnDefinition;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.model.Table;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

@Log4j2
class CreateTableDefinitionTest {

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
        log.info("Extracted Table Name: {}", tableName);

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

        assertNotNull(tableMap, "Ο tableMap δεν πρέπει να είναι null!");
        assertEquals(2, tableMap.size(), "Δεν εξήχθησαν σωστά οι πίνακες!");

        assertTrue(tableMap.containsKey("Orders"), "Ο πίνακας 'Orders' λείπει!");
        assertTrue(tableMap.containsKey("Customers"), "Ο πίνακας 'Customers' λείπει!");


        assertEquals(2, tableMap.get("Orders").getColumns().size(), "Λάθος αριθμός στηλών στον πίνακα 'Orders'!");
        assertEquals(2, tableMap.get("Customers").getColumns().size(), "Λάθος αριθμός στηλών στον πίνακα 'Customers'!");

    }

    @Test
    void testParseAllTables2() {
        log.info("🚀 Ξεκινάει το test: testParseAllTables");

        String sql1 = "CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT REFERENCES customers(id));";
        String sql2 = "CREATE TABLE customers (id SERIAL PRIMARY KEY, name VARCHAR(255));";

        log.info("📄 SQL Scripts:\n1️⃣ {}\n2️⃣ {}", sql1, sql2);

        // Ανάλυση SQL
        SQLParser sqlParser = new SQLParser();
        sqlParser.setSqlContent(sql1 + " " + sql2);
        ParseTree parseTree = sqlParser.parseTreeFromSQL();

        log.info("✅ ParseTree δημιουργήθηκε με {} κόμβους.", parseTree.getChildCount());

        List<PostgreSQLParser.CreateTableStatementContext> createTableStatements = new ArrayList<>();
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (parseTree.getChild(i) instanceof PostgreSQLParser.CreateTableStatementContext) {
                createTableStatements.add((PostgreSQLParser.CreateTableStatementContext) parseTree.getChild(i));
                log.info("📥 Βρέθηκε CREATE TABLE: {}", parseTree.getChild(i).getText());
            }
        }

        log.info("✅ Συνολικά βρέθηκαν {} εντολές CREATE TABLE.", createTableStatements.size());

        // Ανάλυση Πινάκων
        CreateTableDefinition tableDefinition = new CreateTableDefinition();
        Map<String, Table> tableMap = tableDefinition.parseAllTables(createTableStatements);

        // Έλεγχοι Αποτελεσμάτων
        assertNotNull(tableMap, () -> {
            log.error("❌ Ο tableMap είναι null!");
            return "Ο tableMap δεν πρέπει να είναι null!";
        });
        log.info("✅ Ο tableMap δεν είναι null.");

        assertEquals(2, tableMap.size(), () -> {
            log.error("❌ Λάθος αριθμός πινάκων! Αναμενόμενοι: 2, Βρέθηκαν: {}", tableMap.size());
            return "Δεν εξήχθησαν σωστά οι πίνακες!";
        });
        log.info("✅ Ο αριθμός πινάκων είναι σωστός: {}", tableMap.size());

        assertTrue(tableMap.containsKey("Orders"), () -> {
            log.error("❌ Ο πίνακας 'Orders' λείπει!");
            return "Ο πίνακας 'Orders' λείπει!";
        });
        log.info("✅ Ο πίνακας 'Orders' βρέθηκε.");

        assertTrue(tableMap.containsKey("Customers"), () -> {
            log.error("❌ Ο πίνακας 'Customers' λείπει!");
            return "Ο πίνακας 'Customers' λείπει!";
        });
        log.info("✅ Ο πίνακας 'Customers' βρέθηκε.");

        // Έλεγχος Στηλών στον Πίνακα Orders
        assertEquals(2, tableMap.get("Orders").getColumns().size(), () -> {
            log.error("❌ Λάθος αριθμός στηλών στον πίνακα 'Orders'. Αναμενόμενος: 2, Βρέθηκαν: {}", tableMap.get("Orders").getColumns().size());
            return "Λάθος αριθμός στηλών στον πίνακα 'Orders'!";
        });
        log.info("✅ Ο αριθμός στηλών στον πίνακα 'Orders' είναι σωστός.");

        // Έλεγχος Στηλών στον Πίνακα Customers
        assertEquals(2, tableMap.get("Customers").getColumns().size(), () -> {
            log.error("❌ Λάθος αριθμός στηλών στον πίνακα 'Customers'. Αναμενόμενος: 2, Βρέθηκαν: {}", tableMap.get("Customers").getColumns().size());
            return "Λάθος αριθμός στηλών στον πίνακα 'Customers'!";
        });
        log.info("✅ Ο αριθμός στηλών στον πίνακα 'Customers' είναι σωστός.");

        log.info("🎯 Το test ολοκληρώθηκε με επιτυχία!");
    }

}
