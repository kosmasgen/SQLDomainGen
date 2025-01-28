package com.sqldomaingen;

import com.sqldomaingen.generator.RelationshipResolver;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipResolverTest {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipResolverTest.class);

    @Test
    void testResolveRelationships() {
        logger.info("🔵 Starting test for resolveRelationships...");



        // Δημιουργία πινάκων
        Table sourceTable = new Table();
        sourceTable.setName("SourceTable");

        Table targetTableOneToOne = new Table();
        targetTableOneToOne.setName("TargetTableOneToOne");

        Table targetTableOneToMany = new Table();
        targetTableOneToMany.setName("TargetTableOneToMany");

        Table targetTableManyToOne = new Table();
        targetTableManyToOne.setName("TargetTableManyToOne");

        Table targetTableManyToMany = new Table();
        targetTableManyToMany.setName("TargetTableManyToMany");

        // 🔹 Δημιουργία primary key columns στους target tables
        Column targetColumn = new Column();
        targetColumn.setName("id");
        targetColumn.setPrimaryKey(true);
        targetColumn.setJavaType("Long");

        targetTableOneToOne.setColumns(List.of(targetColumn));
        targetTableOneToMany.setColumns(List.of(targetColumn));
        targetTableManyToOne.setColumns(List.of(targetColumn));
        targetTableManyToMany.setColumns(List.of(targetColumn));

        // 🔹 Δημιουργία χάρτη με όλους τους πίνακες
        Map<String, Table> allTables = new HashMap<>();
        allTables.put("SourceTable", sourceTable);
        allTables.put("TargetTableOneToOne", targetTableOneToOne);
        allTables.put("TargetTableOneToMany", targetTableOneToMany);
        allTables.put("TargetTableManyToOne", targetTableManyToOne);
        allTables.put("TargetTableManyToMany", targetTableManyToMany);

        // 🔹 Δημιουργία Foreign Key columns
        Column oneToOneColumn = createForeignKeyColumn("one_to_one_id", "TargetTableOneToOne", "id", "ONETOONE");
        Column oneToManyColumn = createForeignKeyColumn("one_to_many_id", "TargetTableOneToMany", "id", "ONETOMANY");
        Column manyToOneColumn = createForeignKeyColumn("many_to_one_id", "TargetTableManyToOne", "id", "MANYTOONE");
        Column manyToManyColumn = createForeignKeyColumn("many_to_many_id", "TargetTableManyToMany", "id", "MANYTOMANY");
        manyToManyColumn.setJoinTableName("JoinTableManyToMany");
        manyToManyColumn.setInverseJoinColumn("target_id");

        sourceTable.setColumns(Arrays.asList(oneToOneColumn, oneToManyColumn, manyToOneColumn, manyToManyColumn));

        // 🔹 Εκτέλεση RelationshipResolver
        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        logger.info("📌 Resolved relationships: {}", relationships);
        assertFalse(relationships.isEmpty(), "❌ No relationships were resolved!");
        assertEquals(4, relationships.size(), "❌ Should have 4 relationships");
    }

    @Test
    void testDifferentTargetColumn() {
        logger.info("🔵 Testing relationship with a different target column...");
        Table sourceTable = new Table();
        sourceTable.setName("Orders");

        Table targetTable = new Table();
        targetTable.setName("Customers");

        Column targetColumn = new Column();
        targetColumn.setName("customer_code");
        targetTable.addColumn(targetColumn);

        Column fkColumn = new Column();
        fkColumn.setName("customer_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedTable("Customers");
        fkColumn.setReferencedColumn("customer_code");
        fkColumn.setRelationshipType("MANYTOONE");

        sourceTable.addColumn(fkColumn);

        Map<String, Table> allTables = Map.of("Customers", targetTable);
        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        assertEquals(1, relationships.size(), "Expected one relationship.");
        assertEquals("customer_code", relationships.get(0).getTargetColumn(), "Target column should be 'customer_code'");
        logger.info("✅ Passed testDifferentTargetColumn");
    }

    @Test
    void testOnUpdateOnDeleteActions() {
        logger.info("🔵 Testing relationship with ON UPDATE and ON DELETE actions...");

        // Δημιουργία source και target tables
        Table sourceTable = new Table();
        sourceTable.setName("Orders");

        Table targetTable = new Table();
        targetTable.setName("Products");

        // Δημιουργία στήλης foreign key
        Column fkColumn = new Column();
        fkColumn.setName("product_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedTable("Products");
        fkColumn.setReferencedColumn("id");
        fkColumn.setOnDelete("CASCADE");
        fkColumn.setOnUpdate("SET NULL");
        fkColumn.setRelationshipType("MANYTOONE");

        sourceTable.addColumn(fkColumn);

        // Προσθήκη primary key στον target πίνακα (Products)
        Column targetColumn = new Column();
        targetColumn.setName("id");
        targetTable.addColumn(targetColumn);

        // Χάρτης με όλους τους πίνακες
        Map<String, Table> allTables = Map.of("Products", targetTable);

        logger.info("📌 Source Table: {}", sourceTable.getName());
        logger.info("📌 Target Table: {}", targetTable.getName());
        logger.info("📌 Target Table Columns: {}", targetTable.getColumns());

        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        logger.info("📌 Found {} relationships", relationships.size());
        for (Relationship relationship : relationships) {
            logger.info("📌 Resolved Relationship: {}", relationship);
        }

        // Έλεγχος αν δημιουργήθηκε η σχέση
        assertEquals(1, relationships.size(), "❌ Expected one relationship.");

        // Έλεγχος του ON DELETE & ON UPDATE
        Relationship relationship = relationships.get(0);
        assertEquals("CASCADE", relationship.getOnDelete(), "❌ Expected ON DELETE CASCADE");
        assertEquals("SET NULL", relationship.getOnUpdate(), "❌ Expected ON UPDATE SET NULL");

        logger.info("✅ Passed testOnUpdateOnDeleteActions");
    }

    @Test
    void testMissingTargetColumn() {
        logger.info("🔵 Testing missing target column scenario...");

        Table sourceTable = new Table();
        sourceTable.setName("Payments");

        Table targetTable = new Table();
        targetTable.setName("Users");

        Column fkColumn = new Column();
        fkColumn.setName("user_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedTable("Users");
        fkColumn.setReferencedColumn("non_existing_column"); // Column does not exist
        fkColumn.setRelationshipType("MANYTOONE");

        sourceTable.addColumn(fkColumn);

        Map<String, Table> allTables = Map.of("Users", targetTable);

        // 🔹 Log για τις διαθέσιμες στήλες στο target table
        logger.info("📌 Available columns in Users table: {}", targetTable.getColumns());

        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        assertEquals(0, relationships.size(), "No relationship should be created due to missing target column.");
        logger.info("✅ Passed testMissingTargetColumn");
    }




    @Test
    void testCompositeKeyRelationship() {
        logger.info("🔵 Testing relationship with composite key...");
        Table sourceTable = new Table();
        sourceTable.setName("OrderItems");

        Table targetTable = new Table();
        targetTable.setName("Orders");

        Column compositeKeyPart1 = new Column();
        compositeKeyPart1.setName("order_id");
        targetTable.addColumn(compositeKeyPart1);

        Column compositeKeyPart2 = new Column();
        compositeKeyPart2.setName("order_line");
        targetTable.addColumn(compositeKeyPart2);

        Column fkColumn = new Column();
        fkColumn.setName("order_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedTable("Orders");
        fkColumn.setReferencedColumn("order_id");
        fkColumn.setRelationshipType("MANYTOONE");

        sourceTable.addColumn(fkColumn);

        Map<String, Table> allTables = Map.of("Orders", targetTable);
        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        assertEquals(1, relationships.size(), "Expected one relationship.");
        assertEquals("order_id", relationships.get(0).getTargetColumn(), "Expected target column order_id");
        logger.info("✅ Passed testCompositeKeyRelationship");
    }

    @Test
    void testMissingReferencedTable() {
        logger.info("🔵 Testing missing referenced table scenario...");

        Table sourceTable = new Table();
        sourceTable.setName("Shipments");

        Column fkColumn = new Column();
        fkColumn.setName("order_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedTable("NonExistentTable");
        fkColumn.setReferencedColumn("id");
        fkColumn.setRelationshipType("MANYTOONE");

        sourceTable.addColumn(fkColumn);

        Map<String, Table> allTables = new HashMap<>(); // Δεν προσθέτουμε τον πίνακα σκοπίμως

        // 🔹 Προσθέτουμε log για τα διαθέσιμα tables πριν το resolving
        logger.info("📌 Available tables before resolving: {}", allTables.keySet());

        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        assertEquals(0, relationships.size(), "No relationship should be created due to missing referenced table.");
        logger.info("✅ Passed testMissingReferencedTable");
    }

    private Column createForeignKeyColumn(String columnName, String referencedTable, String referencedColumn, String relationshipType) {
        Column column = new Column();
        column.setName(columnName);
        column.setForeignKey(true);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        column.setRelationshipType(relationshipType);
        return column;
    }

}
