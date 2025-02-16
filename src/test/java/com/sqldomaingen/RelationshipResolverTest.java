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
        usersTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true, null)); // ✅ Primary key

        // Profiles Table (OneToOne with Users, user_id must be unique
        Table profilesTable = createTable("Profiles");
        profilesTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true, null)); // ✅ Primary key
        profilesTable.addColumn(createColumn("user_id", "BIGINT", false, true, "Users", "id", true, null)); // ✅ Unique Foreign Key

        // Orders Table (ManyToOne with Users, user_id is NOT unique)
        Table ordersTable = createTable("Orders");
        ordersTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true, null)); // ✅ Primary key
        ordersTable.addColumn(createColumn("user_id", "BIGINT", false, true, "Users", "id", false, null)); // ❌ NOT unique, ManyToOne

        // Products Table
        Table productsTable = createTable("Products");
        productsTable.addColumn(createColumn("id", "BIGINT", true, false, null, null, true, null)); // ✅ Primary key

        // OrderProducts Table (ManyToMany between Orders and Products)
        Table orderProductsTable = createTable("OrderProducts");
        orderProductsTable.addColumn(createColumn("order_id", "BIGINT", true, true, "Orders", "id", false,null));
        orderProductsTable.addColumn(createColumn("product_id", "BIGINT", true, true, "Products", "id", false,null));

        // Add tables to map
        tableMap.put("Users", usersTable);
        tableMap.put("Profiles", profilesTable);
        tableMap.put("Orders", ordersTable);
        tableMap.put("Products", productsTable);
        tableMap.put("OrderProducts", orderProductsTable);

        logger.info("✅ Table map initialized with tables: {}", tableMap.keySet());
        resolver = new RelationshipResolver(tableMap);
    }

    @Test
    void testResolveOneToOneRelationship() {
        logger.info("🔵 Running test: testResolveOneToOneRelationship");

        Table profilesTable = tableMap.get("Profiles");
        List<Relationship> relationships = resolver.resolveRelationships(profilesTable);

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
        List<Relationship> relationships = resolver.resolveRelationships(ordersTable);

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
        tableMap.values().forEach(table -> resolver.resolveRelationships(table));

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

        // Step 1: Αναλύουμε όλες τις σχέσεις πριν τον έλεγχο.
        logger.info("📋 Resolving relationships for all tables...");
        resolver.resolveRelationshipsForAllTables();

        // Step 2: Ελέγχουμε τον πίνακα Orders.
        logger.info("📋 Checking ManyToMany relationship in Orders...");
        Table ordersTable = tableMap.get("Orders");
        List<Relationship> ordersRelationships = ordersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY)
                .toList();

        // Log the relationships found in Orders table
        ordersRelationships.forEach(relationship ->
                logger.info("🔗 Found Orders ManyToMany Relationship: {}", relationship));

        // Assertions για τον πίνακα Orders
        assertEquals(1, ordersRelationships.size(), "Orders table should have one ManyToMany relationship.");
        Relationship ordersRelationship = ordersRelationships.get(0);
        assertEquals("OrderProducts", ordersRelationship.getJoinTableName(),
                "The join table for Orders->Products ManyToMany relationship should be OrderProducts.");
        logger.info("✅ Orders table has correct ManyToMany relationship.");

        // Step 3: Ελέγχουμε τον πίνακα Products.
        logger.info("📋 Checking ManyToMany relationship in Products...");
        Table productsTable = tableMap.get("Products");
        List<Relationship> productsRelationships = productsTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY)
                .toList();

        // Log the relationships found in Products table
        productsRelationships.forEach(relationship ->
                logger.info("🔗 Found Products ManyToMany Relationship: {}", relationship));

        // Assertions για τον πίνακα Products
        assertEquals(1, productsRelationships.size(), "Products table should have one ManyToMany relationship.");
        Relationship productsRelationship = productsRelationships.get(0);
        assertEquals("OrderProducts", productsRelationship.getJoinTableName(),
                "The join table for Products->Orders ManyToMany relationship should be OrderProducts.");
        logger.info("✅ Products table has correct ManyToMany relationship.");

        // Συνολική επιβεβαίωση
        logger.info("🎉 ManyToMany relationships resolved correctly in Orders and Products!");
    }


    @Test
    void testTableMapInitialization() {
        logger.info("🔍 Running test: testTableMapInitialization");

        // ✅ Έλεγχος αν το tableMap δεν είναι null και έχει 5 πίνακες
        assertNotNull(tableMap, "❌ Το tableMap δεν πρέπει να είναι null!");
        assertEquals(5, tableMap.size(), "❌ Ο αριθμός των πινάκων δεν είναι σωστός!");

        // ✅ Έλεγχος αν υπάρχουν οι σωστοί πίνακες
        assertTrue(tableMap.containsKey("Users"), "❌ Ο πίνακας 'Users' λείπει!");
        assertTrue(tableMap.containsKey("Profiles"), "❌ Ο πίνακας 'Profiles' λείπει!");
        assertTrue(tableMap.containsKey("Orders"), "❌ Ο πίνακας 'Orders' λείπει!");
        assertTrue(tableMap.containsKey("Products"), "❌ Ο πίνακας 'Products' λείπει!");
        assertTrue(tableMap.containsKey("OrderProducts"), "❌ Ο πίνακας 'OrderProducts' λείπει!");

        // ✅ Έλεγχος στήλης για τον πίνακα Users
        Table usersTable = tableMap.get("Users");
        assertEquals(1, usersTable.getColumns().size(), "❌ Λάθος αριθμός στηλών στον πίνακα 'Users'!");
        assertTrue(usersTable.getColumns().stream().anyMatch(c -> c.getName().equals("id") && c.isPrimaryKey()), "❌ Λείπει το πρωτεύον κλειδί 'id' στον πίνακα 'Users'!");

        // ✅ Έλεγχος στήλης για τον πίνακα Profiles
        Table profilesTable = tableMap.get("Profiles");
        assertTrue(profilesTable.getColumns().stream().anyMatch(c -> c.getName().equals("user_id") && c.isForeignKey()), "❌ Λείπει το ξένο κλειδί 'user_id' στον πίνακα 'Profiles'!");

        // ✅ Έλεγχος Many-to-One σχέσης για Orders
        Table ordersTable = tableMap.get("Orders");
        assertTrue(ordersTable.getColumns().stream().anyMatch(c -> c.getName().equals("user_id") && c.isForeignKey()), "❌ Ο πίνακας 'Orders' δεν έχει σωστό foreign key για 'user_id'!");

        logger.info("🎯 Το tableMap έχει ρυθμιστεί σωστά με όλους τους πίνακες και τις στήλες!");
    }

    @Test
    void testResolveRelationshipsForAllTables() {
        logger.info("🔵 Running test: testResolveRelationshipsForAllTables");

        // Εκκίνηση της ανάλυσης σχέσεων για όλους τους πίνακες
        resolver.resolveRelationshipsForAllTables();

        // ✅ Έλεγχος σχέσεων για κάθε πίνακα
        tableMap.forEach((tableName, table) -> {
            logger.info("🔍 Checking relationships for table: {}", tableName);
            List<Relationship> relationships = table.getRelationships();

            // ✅ Εξασφάλιση ότι οι πίνακες έχουν αναγνωριστεί σωστά
            assertNotNull(relationships, "❌ Relationships list should not be null for table: " + tableName);

            // ✅ Εξατομικευμένοι έλεγχοι για συγκεκριμένους πίνακες
            if (tableName.equals("Profiles")) {
                assertEquals(1, relationships.size(), "❌ Profiles should have 1 OneToOne relationship.");
                assertEquals(Relationship.RelationshipType.ONETOONE, relationships.get(0).getRelationshipType(), "❌ Relationship should be OneToOne.");
            }

            if (tableName.equals("Orders")) {
                assertTrue(relationships.stream().anyMatch(r -> r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE),
                        "❌ Orders should have at least one ManyToOne relationship.");
            }

            if (tableName.equals("Users")) {
                assertTrue(relationships.stream().anyMatch(r -> r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY),
                        "❌ Users should have at least one OneToMany relationship.");
            }

            if (tableName.equals("OrderProducts")) {
                assertEquals(2, relationships.size(), "❌ OrderProducts should have 2 MANYTOONE relationships.");

                // ✅ Ορθός έλεγχος για ManyToOne σχέσεις
                assertTrue(relationships.stream().allMatch(r -> r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE),
                        "❌ All relationships in OrderProducts should be MANYTOONE.");

                // ✅ Logging για mappedBy και joinTable/inverseJoinColumn
                relationships.forEach(relationship -> {
                    logger.info("🔗 Relationship from '{}' to '{}', Type: {}, JoinTable: {}, InverseJoinColumn: {}, MappedBy: {}",
                            relationship.getSourceTable(),
                            relationship.getTargetTable(),
                            relationship.getRelationshipType(),
                            relationship.getJoinTableName() != null ? relationship.getJoinTableName() : "None",
                            relationship.getInverseJoinColumn() != null ? relationship.getInverseJoinColumn() : "None",
                            relationship.getMappedBy() != null ? relationship.getMappedBy() : "None"
                    );
                });
            }



            // ✅ Προσθήκη logging για το mappedBy
            relationships.forEach(rel -> {
                logger.info("🔗 Relationship from '{}' to '{}', Type: {}, MappedBy: {}",
                        rel.getSourceTable(), rel.getTargetTable(), rel.getRelationshipType(),
                        rel.getMappedBy() != null ? rel.getMappedBy() : "None");
            });

            logger.info("✅ Relationships verified for table: {}", tableName);
        });

        logger.info("🎉 All relationships resolved correctly for all tables!");
    }


    @Test
    void testRelationshipsResolvedCorrectly() {
        logger.info("🔵 Running test: testRelationshipsResolvedCorrectly");

        // Δημιουργία πινάκων με ονόματα PascalCase για να ταιριάζουν με τα υπόλοιπα τεστ
        Table order = new Table();
        order.setName("Orders");

        Table product = new Table();
        product.setName("Products");

        Table orderProduct = new Table();
        orderProduct.setName("OrderProducts");

        // Δημιουργία Foreign Key στηλών με PascalCase referencedTable
        Column orderId = new Column();
        orderId.setName("order_id");
        orderId.setSqlType("INT");
        orderId.setForeignKey(true);
        orderId.setReferencedTable("Orders");
        orderId.setReferencedColumn("id");

        Column productId = new Column();
        productId.setName("product_id");
        productId.setSqlType("INT");
        productId.setForeignKey(true);
        productId.setReferencedTable("Products");
        productId.setReferencedColumn("id");

        // Προσθήκη στηλών στον πίνακα OrderProducts
        orderProduct.addColumn(orderId);
        orderProduct.addColumn(productId);

        // Προσθήκη primary keys για να ανιχνευτεί ως join table
        orderId.setPrimaryKey(true);
        productId.setPrimaryKey(true);

        // Δημιουργία map με PascalCase ονόματα πινάκων (σύμφωνα με τα άλλα τεστ)
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Orders", order);
        tableMap.put("Products", product);
        tableMap.put("OrderProducts", orderProduct);

        // Εκτέλεση RelationshipResolver
        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        // Ανάκτηση των relationships
        List<Relationship> relationships = resolver.getRelationships();

        logger.info("🔍 Relationships found: {}", relationships.size());
        for (Relationship rel : relationships) {
            logger.info("🔗 Relationship: {} -> {} | Type: {} | SourceColumn: {} | TargetColumn: {} | mappedBy: {} | JoinTable: {}",
                    rel.getSourceTable(),
                    rel.getTargetTable(),
                    rel.getRelationshipType(),
                    rel.getSourceColumn(),
                    rel.getTargetColumn(),
                    rel.getMappedBy() != null ? rel.getMappedBy() : "None",
                    rel.getJoinTableName() != null ? rel.getJoinTableName() : "None"
            );
        }

        // Πρέπει να βρούμε 4 σχέσεις:
        // - 2 ManyToOne (order_product -> order, order_product -> product)
        // - 2 ManyToMany (order -> product, product -> order) μέσω join table
        assertEquals(4, relationships.size(), "Should create 4 relationships (2 ManyToOne + 2 ManyToMany)");

        logger.info("✅ Test testRelationshipsResolvedCorrectly completed successfully.");
    }



    // Βοηθητική Μέθοδος για Δημιουργία Table
    private Table createTable(String name) {
        Table table = new Table();
        table.setName(name);
        return table;
    }

    // Βοηθητική Μέθοδος για Δημιουργία Column
    private Column createColumn(String name, String sqlType, boolean isPrimaryKey, boolean isForeignKey, String referencedTable, String referencedColumn, boolean unique, String mappedBy) {
        Column column = new Column();
        column.setName(name);
        column.setSqlType(sqlType);
        column.setPrimaryKey(isPrimaryKey);
        column.setForeignKey(isForeignKey);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        column.setUnique(unique);
        column.setMappedBy(mappedBy); // ✅ Προσθήκη mappedBy

        // ✅ Καταγραφή για παρακολούθηση
        logger.info("📊 Created Column: name={}, sqlType={}, isPrimaryKey={}, isForeignKey={}, referencedTable={}, referencedColumn={}, unique={}, mappedBy={}",
                name, sqlType, isPrimaryKey, isForeignKey, referencedTable, referencedColumn, unique, mappedBy);

        return column;
    }


}