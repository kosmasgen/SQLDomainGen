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

    @Test
    void testResolveOneToOneRelationship() {
        logger.info("🔵 Running test: testResolveOneToOneRelationship");

        Table profilesTable = tableMap.get("Profiles");
        Table usersTable = tableMap.get("Users");

        logger.info("📌 Profiles Table: {}", profilesTable);
        logger.info("📌 Users Table: {}", usersTable);

        List<Relationship> relationships = resolver.resolveRelationships(profilesTable);
        logger.info("🔍 Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "OneToOne should create one relationship.");

        Relationship relationship = relationships.get(0);
        logger.info("✅ OneToOne relationship found: {}", relationship);

        // ➤ Βασικά δεδομένα
        assertEquals("Profiles", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOONE, relationship.getRelationshipType());

        // ➤ mappedBy πρέπει να είναι null στην owning πλευρά
        assertNull(relationship.getMappedBy(), "mappedBy should be NULL on owning side of OneToOne");

        // ➤ Έλεγχος της inverse σχέσης (Users -> Profiles)
        List<Relationship> inverseRelationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOONE)
                .toList();

        assertEquals(1, inverseRelationships.size(), "Expected one OneToOne relationship in Users table.");
        Relationship inverseRelationship = inverseRelationships.get(0);

        assertEquals("Users", inverseRelationship.getSourceTable());
        assertEquals("id", inverseRelationship.getSourceColumn());
        assertEquals("Profiles", inverseRelationship.getTargetTable());
        assertEquals("user_id", inverseRelationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOONE, inverseRelationship.getRelationshipType());

        // ➤ mappedBy πρέπει να είναι "user" στην inverse πλευρά
        assertEquals("user", inverseRelationship.getMappedBy(), "mappedBy should be 'user' on inverse side of OneToOne");

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
    void testResolveOneToManyRelationship() {
        logger.info("🔵 Running test: testResolveOneToManyRelationship");

        Table usersTable = tableMap.get("Users");

        // 🔄 Περνάμε όλα τα tables για να λυθούν πρώτα οι σχέσεις!
        tableMap.values().forEach(table -> resolver.resolveRelationships(table));

        // ✅ Διαβάζουμε τις μοναδικές σχέσεις από το Users table
        List<Relationship> relationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();

        logger.info("🔍 Found OneToMany Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "Expected one OneToMany relationship.");

        Relationship relationship = relationships.get(0);
        assertEquals("Users", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("Orders", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, relationship.getRelationshipType());

        // ✅ Εδώ προσθέτεις έλεγχο για το mappedBy
        assertEquals("user", relationship.getMappedBy(), "mappedBy should be 'user' for Users -> Orders");

        logger.info("🎉 OneToMany relationship resolved correctly!");
    }

    @Test
    void testJoinTableWithExtraColumnIsManyToMany() {
        logger.info("🔵 Running test: testJoinTableWithExtraColumnIsManyToMany");

        Table order = new Table();
        order.setName("Orders");
        Column orderPrimaryKey = new Column();
        orderPrimaryKey.setName("id");
        orderPrimaryKey.setSqlType("BIGINT");
        orderPrimaryKey.setPrimaryKey(true);
        orderPrimaryKey.setUnique(true);
        order.addColumn(orderPrimaryKey);

        Table product = new Table();
        product.setName("Products");
        Column productPrimaryKey = new Column();
        productPrimaryKey.setName("id");
        productPrimaryKey.setSqlType("BIGINT");
        productPrimaryKey.setPrimaryKey(true);
        productPrimaryKey.setUnique(true);
        product.addColumn(productPrimaryKey);

        Table orderProduct = new Table();
        orderProduct.setName("OrderProducts");

        Column orderId = new Column();
        orderId.setName("order_id");
        orderId.setSqlType("INT");
        orderId.setForeignKey(true);
        orderId.setReferencedTable("Orders");
        orderId.setReferencedColumn("id");
        orderId.setPrimaryKey(true);

        Column productId = new Column();
        productId.setName("product_id");
        productId.setSqlType("INT");
        productId.setForeignKey(true);
        productId.setReferencedTable("Products");
        productId.setReferencedColumn("id");
        productId.setPrimaryKey(true);

        Column extraColumn = new Column();
        extraColumn.setName("quantity");
        extraColumn.setSqlType("INT");

        orderProduct.addColumn(orderId);
        orderProduct.addColumn(productId);
        orderProduct.addColumn(extraColumn);

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Orders", order);
        tableMap.put("Products", product);
        tableMap.put("OrderProducts", orderProduct);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        List<Relationship> relationships = resolver.getRelationships();

        System.out.println("🔍 Relationships found: " + relationships.size());
        relationships.forEach(rel -> System.out.println(
                "🔗 Relationship: " + rel.getSourceTable() + " -> " + rel.getTargetTable() + " | Type: " + rel.getRelationshipType()
        ));

        assertEquals(1, relationships.size(), "Should create only one ManyToMany relationship");
        assertTrue(relationships.stream().allMatch(rel -> rel.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY),
                "The relationship should be ManyToMany");

        logger.info("✅ Test testJoinTableWithExtraColumnIsManyToMany completed successfully.");
    }

    @Test
    void testIsJoinTableWithOnlyTwoFKCompositePK() {
        logger.info("🔵 Running test: testIsJoinTableWithOnlyTwoFKCompositePK");

        Table joinTable = new Table();
        joinTable.setName("employee_department");

        Column employeeId = new Column();
        employeeId.setName("employee_id");
        employeeId.setSqlType("INT");
        employeeId.setPrimaryKey(true);
        employeeId.setForeignKey(true);
        employeeId.setReferencedTable("employee");
        employeeId.setReferencedColumn("id");

        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setSqlType("INT");
        departmentId.setPrimaryKey(true);
        departmentId.setForeignKey(true);
        departmentId.setReferencedTable("department");
        departmentId.setReferencedColumn("id");

        joinTable.addColumn(employeeId);
        joinTable.addColumn(departmentId);

        // ✅ Χρήση instance
        RelationshipResolver resolver = new RelationshipResolver(Map.of());
        boolean result = resolver.isJoinTable(joinTable);

        logger.info("📎 isJoinTable(employee_department) = {}", result);
        assertTrue(result, "Table with 2 composite FKs should be considered join table");
    }

    @Test
    void testResolveRelationships_ManyToManyJoinTable() {
        logger.info("🔵 Running test: testResolveRelationships_ManyToManyJoinTable");

        // Πίνακας Employee
        Table employee = new Table();
        employee.setName("employee");
        Column employeeId = new Column();
        employeeId.setName("id");
        employeeId.setSqlType("INT");
        employeeId.setPrimaryKey(true);
        employee.addColumn(employeeId);

        // Πίνακας Department
        Table department = new Table();
        department.setName("department");
        Column departmentId = new Column();
        departmentId.setName("id");
        departmentId.setSqlType("INT");
        departmentId.setPrimaryKey(true);
        department.addColumn(departmentId);

        // Join Table: employee_department
        Table employeeDepartment = new Table();
        employeeDepartment.setName("employee_department");

        Column empFk = new Column();
        empFk.setName("employee_id");
        empFk.setSqlType("INT");
        empFk.setPrimaryKey(true);
        empFk.setForeignKey(true);
        empFk.setReferencedTable("employee");
        empFk.setReferencedColumn("id");

        Column deptFk = new Column();
        deptFk.setName("department_id");
        deptFk.setSqlType("INT");
        deptFk.setPrimaryKey(true);
        deptFk.setForeignKey(true);
        deptFk.setReferencedTable("department");
        deptFk.setReferencedColumn("id");

        employeeDepartment.addColumn(empFk);
        employeeDepartment.addColumn(deptFk);

        // 📦 Map με όλα τα tables
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Employee", employee);
        tableMap.put("Department", department);
        tableMap.put("EmployeeDepartment", employeeDepartment); // PascalCase key

        // 🚀 Κλήση resolver
        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        List<Relationship> relationships = resolver.resolveRelationships(employeeDepartment);

        // ✅ Έλεγχοι
        assertEquals(1, relationships.size(), "Expected one ManyToMany relationship");

        Relationship rel = relationships.get(0);
        assertEquals("Employee", rel.getSourceTable());
        assertEquals("Department", rel.getTargetTable());
        assertEquals(Relationship.RelationshipType.MANYTOMANY, rel.getRelationshipType());
        assertEquals("employee_department", rel.getJoinTableName());
        assertEquals("employee_id", rel.getSourceColumn());
        assertEquals("department_id", rel.getInverseJoinColumn());

        logger.info("✅ ManyToMany relationship verified: {}", rel);
    }

    @Test
    void testSelfReferencingForeignKeyDetected() {
        // 🏗️ Setup: Department table with self-referencing FK (parent_dept_id)
        Table departmentTable = new Table();
        departmentTable.setName("Department");

        Column deptId = new Column();
        deptId.setName("department_id");
        deptId.setSqlType("INT");
        deptId.setPrimaryKey(true);
        deptId.setUnique(true);

        Column parentDeptId = new Column();
        parentDeptId.setName("parent_dept_id");
        parentDeptId.setSqlType("INT");
        parentDeptId.setForeignKey(true);
        parentDeptId.setReferencedTable("department");
        parentDeptId.setReferencedColumn("department_id");

        departmentTable.addColumn(deptId);
        departmentTable.addColumn(parentDeptId);

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Department", departmentTable);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        List<Relationship> relationships = resolver.resolveRelationships(departmentTable);

        // ✅ Assertion: Πρέπει να υπάρχει 1 self-referencing ManyToOne σχέση
        assertEquals(1, relationships.size(), "Expected one self-referencing relationship");

        Relationship relationship = relationships.get(0);
        assertEquals("Department", relationship.getSourceTable());
        assertEquals("parent_dept_id", relationship.getSourceColumn());
        assertEquals("Department", relationship.getTargetTable());
        assertEquals("department_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());
    }

    @Test
    void testSelfReferencingSupervisorRelationshipDetected() {
        logger.info("🔵 Running test: testSelfReferencingSupervisorRelationshipDetected");

        // Δημιουργία πίνακα User με self-referencing foreign key: supervisor_id → user_id
        Table userTable = new Table();
        userTable.setName("User");

        Column userId = new Column();
        userId.setName("user_id");
        userId.setSqlType("INT");
        userId.setPrimaryKey(true);
        userId.setUnique(true);
        userTable.addColumn(userId);

        Column supervisorId = new Column();
        supervisorId.setName("supervisor_id");
        supervisorId.setSqlType("INT");
        supervisorId.setForeignKey(true);
        supervisorId.setReferencedTable("User");
        supervisorId.setReferencedColumn("user_id");
        supervisorId.setPrimaryKey(false);
        supervisorId.setUnique(false); // ManyToOne σχέση
        userTable.addColumn(supervisorId);

        Map<String, Table> map = new HashMap<>();
        map.put("User", userTable);

        RelationshipResolver resolver = new RelationshipResolver(map);
        List<Relationship> relationships = resolver.resolveRelationships(userTable);

        logger.info("🔍 Relationships: {}", relationships);

        // ➤ Αναμένουμε μία ManyToOne σχέση (User → Supervisor)
        assertEquals(1, relationships.size(), "Expected one self-referencing relationship");

        Relationship relationship = relationships.get(0);
        assertEquals("User", relationship.getSourceTable());
        assertEquals("supervisor_id", relationship.getSourceColumn());
        assertEquals("User", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());

        // ➤ Εύρεση αντίστροφης σχέσης ONETOMANY (Supervisor → Employees)
        List<Relationship> inverseRels = userTable.getRelationships().stream()
                .filter(r -> r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();

        assertEquals(1, inverseRels.size(), "Expected one inverse ONETOMANY relationship");

        Relationship inverse = inverseRels.get(0);
        assertEquals("User", inverse.getSourceTable());
        assertEquals("user_id", inverse.getSourceColumn());
        assertEquals("User", inverse.getTargetTable());
        assertEquals("supervisor_id", inverse.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, inverse.getRelationshipType());

        // ➤ mappedBy πρέπει να είναι "supervisor"
        assertEquals("supervisor", inverse.getMappedBy(), "mappedBy should be 'supervisor'");

        logger.info("🎯 Self-referencing supervisor relationship resolved successfully!");
    }

}