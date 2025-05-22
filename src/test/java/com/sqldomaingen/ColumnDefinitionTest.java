package com.sqldomaingen;

import com.sqldomaingen.parser.ColumnDefinition;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.parser.PostgreSQLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

@Log4j2
class ColumnDefinitionTest {


    private PostgreSQLParser.ColumnDefContext getColumnDefContext(String sql) {
        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString("CREATE TABLE test (" + sql + ");"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PostgreSQLParser parser = new PostgreSQLParser(tokens);
        ParseTree tree = parser.createTableStatement();
        return ((PostgreSQLParser.CreateTableStatementContext) tree).columnDef(0);
    }

    private void logColumnDetails(ColumnDefinition column, String testName) {
        log.info("Starting test: {}", testName);
        log.info("Column Details - Name: {}, SQL Type: {}, Base SQL Type: {}, Length: {}, Precision: {}, Scale: {}, Primary Key: {}, Foreign Key: {}, Nullable: {}, Unique: {}, Default: {}, Check Constraint: {}",
                column.getColumnName(), column.getSqlType(), column.getBaseSqlType(), column.getLength(),
                column.getPrecision(), column.getScale(), // ✅ Προσθέσαμε Precision & Scale
                column.isPrimaryKey(), column.isForeignKey(), column.isNullable(), column.isUnique(),
                column.getDefaultValue(), column.getCheckConstraint());
    }

    @Test
    void testFromContext_SimpleColumn() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("id INTEGER");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_SimpleColumn");

        assertEquals("id", column.getColumnName());
        assertEquals("INTEGER", column.getSqlType());
        assertFalse(column.isPrimaryKey());
        assertTrue(column.isNullable());
        assertFalse(column.isUnique());
        assertNull(column.getDefaultValue());
    }

    @Test
    void testFromContext_WithPrimaryKey() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("id SERIAL PRIMARY KEY");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithPrimaryKey");

        assertEquals("id", column.getColumnName());
        assertEquals("SERIAL", column.getSqlType());
        assertTrue(column.isPrimaryKey());
        assertFalse(column.isNullable());
    }

    @Test
    void testFromContext_WithNotNull() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("username VARCHAR(50) NOT NULL");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithNotNull");

        assertEquals("username", column.getColumnName());
        assertEquals("VARCHAR", column.getBaseSqlType());
        assertEquals(50, column.getLength());
        assertFalse(column.isNullable());
    }

    @Test
    void testFromContext_WithUnique() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("email VARCHAR(100) UNIQUE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithUnique");

        assertEquals("email", column.getColumnName());
        assertEquals("VARCHAR", column.getBaseSqlType());
        assertTrue(column.isUnique());
    }

    @Test
    void testFromContext_WithDefault() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("status VARCHAR(20) DEFAULT 'active'");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithDefault");

        assertEquals("status", column.getColumnName());
        assertEquals("VARCHAR", column.getBaseSqlType());
        assertEquals("active", column.getDefaultValue());
    }

    @Test
    void testFromContext_WithCheckConstraint() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("age INTEGER CHECK (age >= 18)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithCheckConstraint");

        assertEquals("age", column.getColumnName());
        assertEquals("INTEGER", column.getSqlType());
        assertEquals("(age >= 18)", column.getCheckConstraint());
    }

    @Test
    void testFromContext_WithForeignKey() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("user_id INTEGER REFERENCES users(id)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey");

        assertEquals("user_id", column.getColumnName());
        assertEquals("INTEGER", column.getSqlType());
        assertTrue(column.isForeignKey());
        assertEquals("users", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithCompositeKey() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT PRIMARY KEY, customer_id INT PRIMARY KEY");
        ColumnDefinition column1 = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column1, "testFromContext_WithCompositeKey - Order ID");

        assertEquals("order_id", column1.getColumnName());
        assertEquals("INT", column1.getSqlType());
        assertTrue(column1.isPrimaryKey());

        PostgreSQLParser.ColumnDefContext ctx2 = getColumnDefContext("customer_id INT PRIMARY KEY");
        ColumnDefinition column2 = ColumnDefinition.fromContext(ctx2);
        logColumnDetails(column2, "testFromContext_WithCompositeKey - Customer ID");

        assertEquals("customer_id", column2.getColumnName());
        assertEquals("INT", column2.getSqlType());
        assertTrue(column2.isPrimaryKey());
    }

    @Test
    void testFromContext_WithMultipleConstraints() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("amount DECIMAL(10,2) NOT NULL CHECK (amount > 0) DEFAULT 100.00");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithMultipleConstraints");

        assertEquals("amount", column.getColumnName());
        assertEquals("DECIMAL", column.getBaseSqlType());

        // ✅ Έλεγχος Precision και Scale
        assertEquals(10, column.getPrecision(), "Precision should be 10");
        assertEquals(2, column.getScale(), "Scale should be 2");

        assertFalse(column.isNullable());
        assertEquals("(amount > 0)", column.getCheckConstraint());
        assertEquals("100.00", column.getDefaultValue());
    }


    @Test
    void testFromContext_PrimaryKey_WithoutSerial() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("customer_id BIGINT PRIMARY KEY");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_PrimaryKey_WithoutSerial");

        assertEquals("customer_id", column.getColumnName());
        assertEquals("BIGINT", column.getSqlType());
        assertTrue(column.isPrimaryKey());
    }

    @Test
    void testFromContext_WithForeignKey_And_Cascade() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON DELETE CASCADE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_And_Cascade");

        assertEquals("order_id", column.getColumnName());
        assertEquals("INT", column.getSqlType());
        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }


    @Test
    void testFromContext_WithPrimaryKeyAndForeignKey() {
        log.info("Starting test: testFromContext_WithPrimaryKeyAndForeignKey");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT PRIMARY KEY REFERENCES orders(id)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("order_id", column.getColumnName());
        assertEquals("INT", column.getSqlType());
        assertTrue(column.isPrimaryKey());
        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithCheckConstraintBetween() {
        log.info("Starting test: testFromContext_WithCheckConstraintBetween");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("age INTEGER CHECK (age BETWEEN 18 AND 65)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("(age BETWEEN 18 AND 65)", column.getCheckConstraint());
    }

    @Test
    void testFromContext_WithCheckConstraintRange() {
        log.info("Starting test: testFromContext_WithCheckConstraintRange");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("salary DECIMAL(10,2) CHECK (salary > 30000 AND salary < 100000)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("(salary > 30000 AND salary < 100000)", column.getCheckConstraint());
    }

    @Test
    void testFromContext_WithCheckConstraintPercentage() {
        log.info("Starting test: testFromContext_WithCheckConstraintPercentage");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("discount_percentage INT CHECK (discount_percentage >= 0 AND discount_percentage <= 50)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("(discount_percentage >= 0 AND discount_percentage <= 50)", column.getCheckConstraint());
    }

    @Test
    void testFromContext_WithNullableFalse() {
        log.info("Starting test: testFromContext_WithNullableFalse");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("email VARCHAR(100) NOT NULL");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertFalse(column.isNullable());
    }

    @Test
    void testFromContext_WithUniqueConstraint() {
        log.info("Starting test: testFromContext_WithUniqueConstraint");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("phone_number VARCHAR(15) UNIQUE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertTrue(column.isUnique());
    }

    @Test
    void testFromContext_WithDefaultNumericValue() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("score DECIMAL(5,2) DEFAULT 99.99");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithDefaultNumericValue");

        assertEquals("score", column.getColumnName());
        assertEquals("DECIMAL", column.getBaseSqlType());

        // ✅ Έλεγχος Precision και Scale
        assertEquals(5, column.getPrecision(), "Precision should be 5");
        assertEquals(2, column.getScale(), "Scale should be 2");

        assertEquals("99.99", column.getDefaultValue());
    }


    @Test
    void testFromContext_WithForeignKeyCascade() {
        log.info("Starting test: testFromContext_WithForeignKeyCascade");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON DELETE CASCADE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithDecimalNoScale() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("weight DECIMAL(8)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithDecimalNoScale");

        assertEquals("weight", column.getColumnName());
        assertEquals("DECIMAL", column.getBaseSqlType());

        // ✅ Έλεγχος Precision και Default Scale
        assertEquals(8, column.getPrecision(), "Precision should be 8");
        assertEquals(0, column.getScale(), "Scale should default to 0");
    }


    @Test
    void testToColumn() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("id BIGINT PRIMARY KEY");
        ColumnDefinition columnDefinition = ColumnDefinition.fromContext(ctx);
        logColumnDetails(columnDefinition, "testToColumn");
        com.sqldomaingen.model.Column column = columnDefinition.toColumn();

        assertEquals("id", column.getName());
        assertEquals("BIGINT", column.getSqlType());
        assertTrue(column.isPrimaryKey());
        assertFalse(column.isNullable());
    }

    @Test
    void testFromContext_WithDateType() {
        log.info("Starting test: testFromContext_WithDateType");
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("birth_date DATE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);

        assertEquals("birth_date", column.getColumnName());
        assertEquals("DATE", column.getSqlType());
    }

    @Test
    void testFromContext_WithForeignKey_OnUpdateCascade() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("customer_id INT REFERENCES customers(id) ON UPDATE CASCADE");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnUpdateCascade");

        assertTrue(column.isForeignKey());
        assertEquals("customers", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnDeleteSetNull() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON DELETE SET NULL");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnDeleteSetNull");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnUpdateSetNull() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON UPDATE SET NULL");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnUpdateSetNull");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnDeleteRestrict() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON DELETE RESTRICT");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnDeleteRestrict");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnUpdateRestrict() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON UPDATE RESTRICT");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnUpdateRestrict");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnDeleteNoAction() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON DELETE NO ACTION");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnDeleteNoAction");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithForeignKey_OnUpdateNoAction() {
        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("order_id INT REFERENCES orders(id) ON UPDATE NO ACTION");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithForeignKey_OnUpdateNoAction");

        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    void testFromContext_WithTextAndTimestampDefault() {
        log.info("Starting test: testFromContext_WithTextAndTimestampDefault");

        // Column: title TEXT NOT NULL
        PostgreSQLParser.ColumnDefContext ctx1 = getColumnDefContext("title TEXT NOT NULL");
        ColumnDefinition col1 = ColumnDefinition.fromContext(ctx1);
        logColumnDetails(col1, "Text NOT NULL");

        assertEquals("title", col1.getColumnName());
        assertEquals("TEXT", col1.getSqlType());
        assertFalse(col1.isNullable());

        // Column: content TEXT
        PostgreSQLParser.ColumnDefContext ctx2 = getColumnDefContext("content TEXT");
        ColumnDefinition col2 = ColumnDefinition.fromContext(ctx2);
        logColumnDetails(col2, "Text nullable");

        assertEquals("content", col2.getColumnName());
        assertEquals("TEXT", col2.getSqlType());
        assertTrue(col2.isNullable());

        // Column: created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        PostgreSQLParser.ColumnDefContext ctx3 = getColumnDefContext("created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP");
        ColumnDefinition col3 = ColumnDefinition.fromContext(ctx3);
        logColumnDetails(col3, "Timestamp with default");

        assertEquals("created_at", col3.getColumnName());
        assertEquals("TIMESTAMP", col3.getSqlType());
        assertEquals("CURRENT_TIMESTAMP", col3.getDefaultValue());
    }

    @Test
    void testFromContext_JoinTableColumnsWithPKAndFK() {
        log.info("Starting test: testFromContext_JoinTableColumnsWithPKAndFK");

        // employee_id INT NOT NULL PRIMARY KEY REFERENCES employee(id)
        PostgreSQLParser.ColumnDefContext ctx1 = getColumnDefContext("employee_id INT NOT NULL PRIMARY KEY REFERENCES employee(id)");
        ColumnDefinition col1 = ColumnDefinition.fromContext(ctx1);
        logColumnDetails(col1, "employee_id");

        assertEquals("employee_id", col1.getColumnName());
        assertEquals("INT", col1.getSqlType());
        assertTrue(col1.isPrimaryKey());
        assertTrue(col1.isForeignKey());
        assertEquals("employee", col1.getReferencedTable());
        assertEquals("id", col1.getReferencedColumn());

        // department_id INT NOT NULL PRIMARY KEY REFERENCES department(id)
        PostgreSQLParser.ColumnDefContext ctx2 = getColumnDefContext("department_id INT NOT NULL PRIMARY KEY REFERENCES department(id)");
        ColumnDefinition col2 = ColumnDefinition.fromContext(ctx2);
        logColumnDetails(col2, "department_id");

        assertEquals("department_id", col2.getColumnName());
        assertEquals("INT", col2.getSqlType());
        assertTrue(col2.isPrimaryKey());
        assertTrue(col2.isForeignKey());
        assertEquals("department", col2.getReferencedTable());
        assertEquals("id", col2.getReferencedColumn());
    }

    @Test
    void testFromContext_WithManyToManyPseudoConstraint() {
        log.info("Starting test: testFromContext_WithManyToManyPseudoConstraint");

        PostgreSQLParser.ColumnDefContext ctx = getColumnDefContext("user_id INT MANYTOMANY REFERENCES user(user_id)");
        ColumnDefinition column = ColumnDefinition.fromContext(ctx);
        logColumnDetails(column, "testFromContext_WithManyToManyPseudoConstraint");

        assertEquals("user_id", column.getColumnName());
        assertEquals("INT", column.getSqlType());
        assertTrue(column.isManyToMany(), "Column should be marked as ManyToMany");
        assertTrue(column.isForeignKey(), "Column should be marked as ForeignKey");
        assertEquals("user", column.getReferencedTable());
        assertEquals("user_id", column.getReferencedColumn());
    }


}
