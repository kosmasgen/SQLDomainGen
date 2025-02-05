package com.sqldomaingen;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqldomaingen.generator.RelationshipResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipResolverTest {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipResolverTest.class);
    private RelationshipResolver resolver;
    private Map<String, Table> tableMap;

    @BeforeEach
    void setUp() {
        logger.info("🔵 Setting up tables for RelationshipResolver test...");
        tableMap = new HashMap<>();

        // Users Table
        Table usersTable = createTable("Users");
        usersTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true)); // ✅ Primary key

        // Profiles Table (OneToOne with Users, user_id must be unique)
        Table profilesTable = createTable("Profiles");
        profilesTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true)); // ✅ Primary key
        profilesTable.addColumn(createColumn("user_id", "BIGINT", false, true, "Users", "id", true)); // ✅ Unique Foreign Key

        // Orders Table (ManyToOne with Users, user_id is NOT unique)
        Table ordersTable = createTable("Orders");
        ordersTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true)); // ✅ Primary key
        ordersTable.addColumn(createColumn("user_id", "BIGINT", false, true, "Users", "id", false)); // ❌ NOT unique, ManyToOne

        // Products Table
        Table productsTable = createTable("Products");
        productsTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true)); // ✅ Primary key

        // OrderProducts Table (ManyToMany between Orders and Products)
        Table orderProductsTable = createTable("OrderProducts");
        orderProductsTable.addColumn(createColumn("order_id", "BIGINT", false, true, "Orders", "id", false));
        orderProductsTable.addColumn(createColumn("product_id", "BIGINT", false, true, "Products", "id", false));

        // Add tables to map
        tableMap.put("Users", usersTable);
        tableMap.put("Profiles", profilesTable);
        tableMap.put("Orders", ordersTable);
        tableMap.put("Products", productsTable);
        tableMap.put("OrderProducts", orderProductsTable);

        logger.info("✅ Table map initialized with tables: {}", tableMap.keySet());
        resolver = new RelationshipResolver();
    }

    @Test
    void testResolveOneToOneRelationship() {
        logger.info("🔵 Running test: testResolveOneToOneRelationship");

        Table profilesTable = tableMap.get("Profiles");
        List<Relationship> relationships = resolver.resolveRelationships(profilesTable, tableMap);

        logger.info("🔍 Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "OneToOne should create one relationship.");

        Relationship relationship = relationships.get(0);
        logger.info("✅ OneToOne relationship found: {}", relationship);
        assertEquals("Profiles", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOONE, relationship.getRelationshipType());

        logger.info("🎉 OneToOne relationship resolved correctly!");
    }

    @Test
    void testResolveManyToOneRelationship() {
        logger.info("🔵 Running test: testResolveManyToOneRelationship");

        Table ordersTable = tableMap.get("Orders");
        List<Relationship> relationships = resolver.resolveRelationships(ordersTable, tableMap);

        logger.info("🔍 Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "ManyToOne should create one relationship.");

        Relationship relationship = relationships.get(0);
        logger.info("✅ ManyToOne relationship found: {}", relationship);
        assertEquals("Orders", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());

        logger.info("🎉 ManyToOne relationship resolved correctly!");
    }


    @Test
    void testResolveOneToManyRelationship() {
        logger.info("🔵 Running test: testResolveOneToManyRelationship");

        Table usersTable = tableMap.get("Users");

        // 🔄 Περνάμε όλα τα tables για να λυθούν πρώτα οι σχέσεις!
        tableMap.values().forEach(table -> resolver.resolveRelationships(table, tableMap));

        // ✅ Διαβάζουμε τις μοναδικές σχέσεις από το Users table
        List<Relationship> relationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();  // ✅ Βελτιωμένη χρήση!


        logger.info("🔍 Found OneToMany Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "Expected one OneToMany relationship.");

        Relationship relationship = relationships.get(0);
        assertEquals("Users", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("Orders", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, relationship.getRelationshipType());

        logger.info("🎉 OneToMany relationship resolved correctly!");
    }

    @Test
    void testResolveManyToManyRelationship() {
        logger.info("🔵 Running test: testResolveManyToManyRelationship");

        Table orderProductsTable = tableMap.get("OrderProducts");
        List<Relationship> relationships = resolver.resolveRelationships(orderProductsTable, tableMap);

        logger.info("🔍 Found Relationships: {}", relationships);

        assertEquals(2, relationships.size(), "ManyToMany should create two relationships.");

        Relationship firstRelationship = relationships.get(0);
        logger.info("✅ First ManyToMany relationship found: {}", firstRelationship);
        assertEquals("OrderProducts", firstRelationship.getSourceTable());
        assertEquals("order_id", firstRelationship.getSourceColumn());
        assertEquals("Orders", firstRelationship.getTargetTable());
        assertEquals("id", firstRelationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOMANY, firstRelationship.getRelationshipType());

        // 🔴 Προσθέτουμε έλεγχο για το Join Table και τα Join Columns
        assertNotNull(firstRelationship.getJoinTableName(), "Join table name should not be null");
        assertEquals("OrderProducts", firstRelationship.getJoinTableName(), "Join table name should be correct");
        logger.info("🔍 Checking inverse join column for first relationship: {}", firstRelationship.getInverseJoinColumn());
        assertNotNull(firstRelationship.getInverseJoinColumn(), "Inverse join column should not be null");
        assertEquals("product_id", firstRelationship.getInverseJoinColumn(), "Inverse join column should be correct");

        Relationship secondRelationship = relationships.get(1);
        logger.info("✅ Second ManyToMany relationship found: {}", secondRelationship);
        assertEquals("OrderProducts", secondRelationship.getSourceTable());
        assertEquals("product_id", secondRelationship.getSourceColumn());
        assertEquals("Products", secondRelationship.getTargetTable());
        assertEquals("id", secondRelationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOMANY, secondRelationship.getRelationshipType());

        // 🔴 Προσθέτουμε έλεγχο για το Join Table και τα Join Columns
        assertNotNull(secondRelationship.getJoinTableName(), "Join table name should not be null");
        assertEquals("OrderProducts", secondRelationship.getJoinTableName(), "Join table name should be correct");
        logger.info("🔍 Checking inverse join column for second relationship: {}", secondRelationship.getInverseJoinColumn());
        assertNotNull(secondRelationship.getInverseJoinColumn(), "Inverse join column should not be null");
        assertEquals("order_id", secondRelationship.getInverseJoinColumn(), "Inverse join column should be correct");

        logger.info("🎉 ManyToMany relationships resolved correctly!");
    }


    // Βοηθητική Μέθοδος για Δημιουργία Table
    private Table createTable(String name) {
        Table table = new Table();
        table.setName(name);
        return table;
    }

    // Βοηθητική Μέθοδος για Δημιουργία Column
    private Column createColumn(String name, String sqlType, boolean isPrimaryKey, boolean isForeignKey, String referencedTable, String referencedColumn, boolean unique) {
        Column column = new Column();
        column.setName(name);
        column.setSqlType(sqlType);
        column.setPrimaryKey(isPrimaryKey);
        column.setForeignKey(isForeignKey);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        column.setUnique(unique);
        return column;
    }
}
