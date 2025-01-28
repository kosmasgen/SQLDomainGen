package com.sqldomaingen;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.parser.ColumnDefinition;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class ColumnTest {

    private static final Logger logger = LoggerFactory.getLogger(ColumnTest.class);

    @Test
    public void testBasicColumnProperties() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("username");
        columnDefinition.setSqlType("VARCHAR");
        columnDefinition.setJavaType("String");
        columnDefinition.setLength(100);
        columnDefinition.setPrimaryKey(false);
        columnDefinition.setNullable(false);
        columnDefinition.setUnique(true);
        columnDefinition.setDefaultValue("guest");

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testBasicColumnProperties'");
        assertEquals("username", column.getName());
        assertEquals("VARCHAR", column.getSqlType());
        assertEquals("String", column.getJavaType());
        assertEquals(100, column.getLength());
        assertFalse(column.isPrimaryKey());
        assertFalse(column.isNullable());
        assertTrue(column.isUnique());
        assertEquals("guest", column.getDefaultValue());
    }

    @Test
    public void testPrimaryKeyColumn() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("id");
        columnDefinition.setSqlType("BIGINT");
        columnDefinition.setJavaType("Long");
        columnDefinition.setPrimaryKey(true);
        columnDefinition.setNullable(false);

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testPrimaryKeyColumn'");
        assertEquals("id", column.getName());
        assertEquals("BIGINT", column.getSqlType());
        assertEquals("Long", column.getJavaType());
        assertTrue(column.isPrimaryKey());
        assertFalse(column.isNullable());
    }

    @Test
    public void testForeignKeyColumn() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("order_id");
        columnDefinition.setSqlType("INT");
        columnDefinition.setJavaType("Integer");
        columnDefinition.setForeignKey(true);
        columnDefinition.setReferencedTable("orders");
        columnDefinition.setReferencedColumn("id");

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testForeignKeyColumn'");
        assertEquals("order_id", column.getName());
        assertEquals("INT", column.getSqlType());
        assertEquals("Integer", column.getJavaType());
        assertTrue(column.isForeignKey());
        assertEquals("orders", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
    }

    @Test
    public void testForeignKeyWithCascade() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("customer_id");
        columnDefinition.setSqlType("INT");
        columnDefinition.setJavaType("Integer");
        columnDefinition.setForeignKey(true);
        columnDefinition.setReferencedTable("customers");
        columnDefinition.setReferencedColumn("id");
        columnDefinition.setOnDelete("CASCADE");
        columnDefinition.setOnUpdate("SET NULL");

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testForeignKeyWithCascade'");
        assertEquals("customer_id", column.getName());
        assertTrue(column.isForeignKey());
        assertEquals("customers", column.getReferencedTable());
        assertEquals("id", column.getReferencedColumn());
        assertEquals("CASCADE", column.getOnDelete());
        assertEquals("SET NULL", column.getOnUpdate());
    }

    @Test
    public void testCheckConstraint() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("age");
        columnDefinition.setSqlType("INTEGER");
        columnDefinition.setJavaType("Integer");
        columnDefinition.setCheckConstraint("age >= 18");

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testCheckConstraint'");
        assertEquals("age", column.getName());
        assertEquals("INTEGER", column.getSqlType());
        assertEquals("Integer", column.getJavaType());
        assertEquals("age >= 18", column.getCheckConstraint());
    }

    @Test
    public void testDecimalColumn() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("price");
        columnDefinition.setSqlType("DECIMAL(10,2)");
        columnDefinition.setJavaType("BigDecimal");
        columnDefinition.setPrecision(10);
        columnDefinition.setScale(2);

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testDecimalColumn'");
        assertEquals("price", column.getName());
        assertEquals("DECIMAL(10,2)", column.getSqlType());
        assertEquals("BigDecimal", column.getJavaType());
        assertEquals(10, column.getPrecision());
        assertEquals(2, column.getScale());
    }

    @Test
    public void testUniqueAndNotNullColumn() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("email");
        columnDefinition.setSqlType("VARCHAR");
        columnDefinition.setJavaType("String");
        columnDefinition.setUnique(true);
        columnDefinition.setNullable(false);

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testUniqueAndNotNullColumn'");
        assertEquals("email", column.getName());
        assertTrue(column.isUnique());
        assertFalse(column.isNullable());
    }

    @Test
    public void testDefaultValues() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("status");
        columnDefinition.setSqlType("VARCHAR");
        columnDefinition.setJavaType("String");
        columnDefinition.setDefaultValue("ACTIVE");

        Column column = columnDefinition.toColumn();

        logger.info("Testing 'testDefaultValues'");
        assertEquals("status", column.getName());
        assertEquals("ACTIVE", column.getDefaultValue());
    }

    @Test
    public void testForeignKeyWithAllActions() {
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("order_id");
        columnDefinition.setSqlType("INT");
        columnDefinition.setJavaType("Integer");
        columnDefinition.setForeignKey(true);
        columnDefinition.setReferencedTable("orders");
        columnDefinition.setReferencedColumn("id");

        // ✅ Δοκιμάζουμε όλες τις περιπτώσεις
        String[] onDeleteActions = {"CASCADE", "SET NULL", "SET DEFAULT", "RESTRICT", "NO ACTION"};
        String[] onUpdateActions = {"CASCADE", "SET NULL", "SET DEFAULT", "RESTRICT", "NO ACTION"};

        for (String onDelete : onDeleteActions) {
            for (String onUpdate : onUpdateActions) {
                columnDefinition.setOnDelete(onDelete);
                columnDefinition.setOnUpdate(onUpdate);
                Column column = columnDefinition.toColumn();

                logger.info("Testing 'testForeignKeyWithAllActions' - ON DELETE: {}, ON UPDATE: {}", onDelete, onUpdate);
                assertEquals("order_id", column.getName());
                assertEquals("orders", column.getReferencedTable());
                assertEquals("id", column.getReferencedColumn());
                assertEquals(onDelete, column.getOnDelete());
                assertEquals(onUpdate, column.getOnUpdate());
            }
        }
    }


}
