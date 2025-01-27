package com.sqldomaingen;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.parser.ColumnDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class ColumnTest {

    private static final Logger logger = LoggerFactory.getLogger(ColumnTest.class);

    @Test
    public void testToColumnMethod() {
        // Δημιουργία ενός ColumnDefinition αντικειμένου
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("test_column");
        columnDefinition.setJavaType("String");
        columnDefinition.setLength(255);
        columnDefinition.setPrimaryKey(true);
        columnDefinition.setNullable(false);
        columnDefinition.setDefaultValue("default_value");
        columnDefinition.setUnique(true);

        // Μετατροπή σε Column
        Column column = columnDefinition.toColumn();

        // Logging για επαλήθευση των τιμών
        logger.info("Testing 'testToColumnMethod'");
        logger.info("Column name: {}", column.getName());
        logger.info("Column type: {}", column.getType());
        logger.info("Column length: {}", column.getLength());
        logger.info("Column is primary key: {}", column.isPrimaryKey());
        logger.info("Column is nullable: {}", column.isNullable());
        logger.info("Column default value: {}", column.getDefaultValue());
        logger.info("Column is unique: {}", column.isUnique());

        // Επαληθεύσεις μέσω Assertions
        Assertions.assertEquals("test_column", column.getName());
        Assertions.assertEquals("String", column.getType());
        Assertions.assertEquals(255, column.getLength());
        Assertions.assertTrue(column.isPrimaryKey());
        Assertions.assertFalse(column.isNullable());
        Assertions.assertEquals("default_value", column.getDefaultValue());
        Assertions.assertTrue(column.isUnique());
    }

    @Test
    public void testToColumnMethodWithDifferentDataTypes() {
        logger.info("Testing 'testToColumnMethodWithDifferentDataTypes'");

        // Σενάριο 1: id - Long, Primary Key
        ColumnDefinition idColumnDef = new ColumnDefinition();
        idColumnDef.setColumnName("id");
        idColumnDef.setJavaType("Long");
        idColumnDef.setPrimaryKey(true);
        idColumnDef.setNullable(false);
        idColumnDef.setDefaultValue(null);
        idColumnDef.setUnique(true);

        Column idColumn = idColumnDef.toColumn();

        logger.info("Scenario 1 - idColumn: {}", idColumn);
        Assertions.assertEquals("id", idColumn.getName());
        Assertions.assertEquals("Long", idColumn.getType());
        Assertions.assertTrue(idColumn.isPrimaryKey());
        Assertions.assertFalse(idColumn.isNullable());
        Assertions.assertNull(idColumn.getDefaultValue());
        Assertions.assertTrue(idColumn.isUnique());

        // Σενάριο 2: name - String, Unique
        ColumnDefinition nameColumnDef = new ColumnDefinition();
        nameColumnDef.setColumnName("name");
        nameColumnDef.setJavaType("String");
        nameColumnDef.setLength(100);
        nameColumnDef.setPrimaryKey(false);
        nameColumnDef.setNullable(false);
        nameColumnDef.setDefaultValue("default_name");
        nameColumnDef.setUnique(true);

        Column nameColumn = nameColumnDef.toColumn();

        logger.info("Scenario 2 - nameColumn: {}", nameColumn);
        Assertions.assertEquals("name", nameColumn.getName());
        Assertions.assertEquals("String", nameColumn.getType());
        Assertions.assertEquals(100, nameColumn.getLength());
        Assertions.assertFalse(nameColumn.isPrimaryKey());
        Assertions.assertFalse(nameColumn.isNullable());
        Assertions.assertEquals("default_name", nameColumn.getDefaultValue());
        Assertions.assertTrue(nameColumn.isUnique());

        // Σενάριο 3: price - Double, Nullable
        ColumnDefinition priceColumnDef = new ColumnDefinition();
        priceColumnDef.setColumnName("price");
        priceColumnDef.setJavaType("Double");
        priceColumnDef.setPrimaryKey(false);
        priceColumnDef.setNullable(true);
        priceColumnDef.setDefaultValue("0.0");
        priceColumnDef.setUnique(false);

        Column priceColumn = priceColumnDef.toColumn();

        logger.info("Scenario 3 - priceColumn: {}", priceColumn);
        Assertions.assertEquals("price", priceColumn.getName());
        Assertions.assertEquals("Double", priceColumn.getType());
        Assertions.assertFalse(priceColumn.isPrimaryKey());
        Assertions.assertTrue(priceColumn.isNullable());
        Assertions.assertEquals("0.0", priceColumn.getDefaultValue());
        Assertions.assertFalse(priceColumn.isUnique());

        // Σενάριο 4: created_at - Timestamp, Nullable
        ColumnDefinition createdAtColumnDef = new ColumnDefinition();
        createdAtColumnDef.setColumnName("created_at");
        createdAtColumnDef.setJavaType("Timestamp");
        createdAtColumnDef.setPrimaryKey(false);
        createdAtColumnDef.setNullable(true);
        createdAtColumnDef.setDefaultValue(null);
        createdAtColumnDef.setUnique(false);

        Column createdAtColumn = createdAtColumnDef.toColumn();

        logger.info("Scenario 4 - createdAtColumn: {}", createdAtColumn);
        Assertions.assertEquals("created_at", createdAtColumn.getName());
        Assertions.assertEquals("Timestamp", createdAtColumn.getType());
        Assertions.assertFalse(createdAtColumn.isPrimaryKey());
        Assertions.assertTrue(createdAtColumn.isNullable());
        Assertions.assertNull(createdAtColumn.getDefaultValue());
        Assertions.assertFalse(createdAtColumn.isUnique());
    }
    @Test
    public void testToColumnMethods() {
        // Δημιουργία ColumnDefinition
        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("test_column");
        columnDefinition.setSqlType("VARCHAR");
        columnDefinition.setLength(255);
        columnDefinition.setPrimaryKey(true);
        columnDefinition.setNullable(false);
        columnDefinition.setDefaultValue("default_value");
        columnDefinition.setUnique(true);

        // Εκτύπωση των τιμών του ColumnDefinition πριν τη μετατροπή
        System.out.println("ColumnDefinition πριν τη μετατροπή:");
        System.out.println("Column Name: " + columnDefinition.getColumnName());
        System.out.println("SQL Type: " + columnDefinition.getSqlType());
        System.out.println("Length: " + columnDefinition.getLength());
        System.out.println("Primary Key: " + columnDefinition.isPrimaryKey());
        System.out.println("Nullable: " + columnDefinition.isNullable());
        System.out.println("Default Value: " + columnDefinition.getDefaultValue());
        System.out.println("Unique: " + columnDefinition.isUnique());

        // Μετατροπή σε Column
        Column column = columnDefinition.toColumn();

        // Εκτύπωση των τιμών του Column μετά τη μετατροπή
        System.out.println("Column μετά τη μετατροπή:");
        System.out.println("Column Name: " + column.getName());
        System.out.println("Java Type: " + column.getType());
        System.out.println("Length: " + column.getLength());
        System.out.println("Primary Key: " + column.isPrimaryKey());
        System.out.println("Nullable: " + column.isNullable());
        System.out.println("Default Value: " + column.getDefaultValue());
        System.out.println("Unique: " + column.isUnique());

        // Έλεγχοι
        assertEquals("test_column", column.getName());
        assertEquals("String", column.getType()); // Εδώ ελέγχεται το mapping από SQL σε Java type
        assertEquals(255, column.getLength());
        assertTrue(column.isPrimaryKey());
        assertFalse(column.isNullable());
        assertEquals("default_value", column.getDefaultValue());
        assertTrue(column.isUnique());
    }

}
