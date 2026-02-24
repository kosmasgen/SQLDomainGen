package com.sqldomaingen;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sqldomaingen.generator.RelationshipResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class RelationshipResolverTest {


    private RelationshipResolver resolver;
    private Map<String, Table> tableMap;

    @Test
    void testResolveRelationships_BusinessLocationI18nCompositePkJoinTable_CreatesTwoManyToOneAndInverseSides() {
        // Target table: pep_schema.business_location
        Table businessLocation = new Table();
        businessLocation.setName("pep_schema.business_location");

        Column businessLocationPk = new Column();
        businessLocationPk.setName("id");
        businessLocationPk.setSqlType("uuid");
        businessLocationPk.setJavaType("java.util.UUID");
        businessLocationPk.setPrimaryKey(true);
        businessLocationPk.setNullable(false);

        businessLocation.setColumns(new ArrayList<>(List.of(businessLocationPk)));

        // Target table: pep_schema.languages
        Table languages = new Table();
        languages.setName("pep_schema.languages");

        Column languagesPk = new Column();
        languagesPk.setName("id");
        languagesPk.setSqlType("uuid");
        languagesPk.setJavaType("java.util.UUID");
        languagesPk.setPrimaryKey(true);
        languagesPk.setNullable(false);

        languages.setColumns(new ArrayList<>(List.of(languagesPk)));

        // Join entity table: pep_schema.business_location_i18n
        Table businessLocationI18n = new Table();
        businessLocationI18n.setName("pep_schema.business_location_i18n");

        Column description = new Column();
        description.setName("description");
        description.setSqlType("varchar");
        description.setJavaType("java.lang.String");
        description.setNullable(false);
        description.setLength(255);

        Column code = new Column();
        code.setName("code");
        code.setSqlType("varchar");
        code.setJavaType("java.lang.String");
        code.setNullable(false);
        code.setLength(255);

        Column businessLocationId = new Column();
        businessLocationId.setName("business_location_id");
        businessLocationId.setSqlType("uuid");
        businessLocationId.setJavaType("java.util.UUID");
        businessLocationId.setPrimaryKey(true);
        businessLocationId.setForeignKey(true);
        businessLocationId.setNullable(false);
        businessLocationId.setReferencedTable("pep_schema.business_location");
        businessLocationId.setReferencedColumn("id");

        Column languageId = new Column();
        languageId.setName("language_id");
        languageId.setSqlType("uuid");
        languageId.setJavaType("java.util.UUID");
        languageId.setPrimaryKey(true);
        languageId.setForeignKey(true);
        languageId.setNullable(false);
        languageId.setReferencedTable("pep_schema.languages");
        languageId.setReferencedColumn("id");

        businessLocationI18n.setColumns(new ArrayList<>(List.of(
                description, code, businessLocationId, languageId
        )));

        resolver = new RelationshipResolver(Map.of(
                businessLocation.getName(), businessLocation,
                languages.getName(), languages,
                businessLocationI18n.getName(), businessLocationI18n
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(businessLocationI18n);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(), "Join table should create exactly 2 owning relationships.");

        // owning side checks
        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.business_location_i18n".equals(r.getSourceTable()) &&
                                "business_location_id".equals(r.getSourceColumn()) &&
                                "pep_schema.business_location".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship business_location_i18n.business_location_id -> business_location.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.business_location_i18n".equals(r.getSourceTable()) &&
                                "language_id".equals(r.getSourceColumn()) &&
                                "pep_schema.languages".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship business_location_i18n.language_id -> languages.id");

        // inverse side checks on target tables
        assertTrue(businessLocation.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.business_location".equals(r.getSourceTable()) &&
                                "pep_schema.business_location_i18n".equals(r.getTargetTable()) &&
                                "businessLocation".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on business_location mappedBy='businessLocation'");

        assertTrue(languages.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.languages".equals(r.getSourceTable()) &&
                                "pep_schema.business_location_i18n".equals(r.getTargetTable()) &&
                                "language".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on languages mappedBy='language'");
    }

    @BeforeEach
    void setUp() {
        log.info("🔵 Setting up tables for RelationshipResolver test...");
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
        orderProductsTable.addColumn(createColumn("order_id", "BIGINT", true, true, "Orders", "id", false, null));
        orderProductsTable.addColumn(createColumn("product_id", "BIGINT", true, true, "Products", "id", false, null));

        // Add tables to map
        tableMap.put("Users", usersTable);
        tableMap.put("Profiles", profilesTable);
        tableMap.put("Orders", ordersTable);
        tableMap.put("Products", productsTable);
        tableMap.put("OrderProducts", orderProductsTable);

        log.info("✅ Table map initialized with tables: {}", tableMap.keySet());
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
        log.info("📊 Created Column: name={}, sqlType={}, isPrimaryKey={}, isForeignKey={}, referencedTable={}, referencedColumn={}, unique={}, mappedBy={}",
                name, sqlType, isPrimaryKey, isForeignKey, referencedTable, referencedColumn, unique, mappedBy);

        return column;
    }

    @Test
    void testResolveOneToOneRelationship() {
        log.info("🔵 Running test: testResolveOneToOneRelationship");

        Table profilesTable = tableMap.get("Profiles");
        Table usersTable = tableMap.get("Users");

        log.info("📌 Profiles Table: {}", profilesTable);
        log.info("📌 Users Table: {}", usersTable);

        List<Relationship> relationships = resolver.resolveRelationships(profilesTable);
        log.info("🔍 Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "OneToOne should create one relationship.");

        Relationship relationship = relationships.get(0);
        log.info("✅ OneToOne relationship found: {}", relationship);

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

        log.info("🎉 OneToOne relationship resolved correctly!");
    }

    @Test
    void testResolveManyToOneRelationship() {
        log.info("🔵 Running test: testResolveManyToOneRelationship");

        Table ordersTable = tableMap.get("Orders");
        List<Relationship> relationships = resolver.resolveRelationships(ordersTable);

        log.info("🔍 Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "ManyToOne should create one relationship.");

        Relationship relationship = relationships.get(0);
        log.info("✅ ManyToOne relationship found: {}", relationship);
        assertEquals("Orders", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());

        log.info("🎉 ManyToOne relationship resolved correctly!");
    }

    @Test
    void testTableMapInitialization() {
        log.info("🔍 Running test: testTableMapInitialization");

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

        log.info("🎯 Το tableMap έχει ρυθμιστεί σωστά με όλους τους πίνακες και τις στήλες!");
    }


    @Test
    void testResolveOneToManyRelationship() {
        log.info("🔵 Running test: testResolveOneToManyRelationship");

        Table usersTable = tableMap.get("Users");

        // 🔄 Περνάμε όλα τα tables για να λυθούν πρώτα οι σχέσεις!
        tableMap.values().forEach(table -> resolver.resolveRelationships(table));

        // ✅ Διαβάζουμε τις μοναδικές σχέσεις από το Users table
        List<Relationship> relationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();

        log.info("🔍 Found OneToMany Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "Expected one OneToMany relationship.");

        Relationship relationship = relationships.get(0);
        assertEquals("Users", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("Orders", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, relationship.getRelationshipType());

        // ✅ Εδώ προσθέτεις έλεγχο για το mappedBy
        assertEquals("user", relationship.getMappedBy(), "mappedBy should be 'user' for Users -> Orders");

        log.info("🎉 OneToMany relationship resolved correctly!");
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
        log.info("🔵 Running test: testSelfReferencingSupervisorRelationshipDetected");

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

        log.info("🔍 Relationships: {}", relationships);

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

        log.info("🎯 Self-referencing supervisor relationship resolved successfully!");
    }

    @Test
    void testPseudoManyToManyConstraintResolvesCorrectly() {
        log.info("🔵 Running test: testPseudoManyToManyConstraintResolvesCorrectly");

        // 📦 Table: Student
        Table studentTable = new Table();
        studentTable.setName("Student");
        Column studentId = new Column();
        studentId.setName("id");
        studentId.setPrimaryKey(true);
        studentId.setSqlType("BIGINT");
        studentId.setUnique(true);
        studentTable.addColumn(studentId);

        // 📦 Table: Course
        Table courseTable = new Table();
        courseTable.setName("Course");
        Column courseId = new Column();
        courseId.setName("id");
        courseId.setPrimaryKey(true);
        courseId.setSqlType("BIGINT");
        courseId.setUnique(true);
        courseTable.addColumn(courseId);

        // 📦 Table: Enrollment (pseudo-ManyToMany via constraint)
        Table enrollmentTable = new Table();
        enrollmentTable.setName("Enrollment");

        Column studentRef = new Column();
        studentRef.setName("student_id");
        studentRef.setSqlType("BIGINT");
        studentRef.setForeignKey(true);
        studentRef.setReferencedTable("Student");
        studentRef.setReferencedColumn("id");
        studentRef.setManyToMany(true); // ⛳ pseudo-constraint!
        enrollmentTable.addColumn(studentRef);

        Column courseRef = new Column();
        courseRef.setName("course_id");
        courseRef.setSqlType("BIGINT");
        courseRef.setForeignKey(true);
        courseRef.setReferencedTable("Course");
        courseRef.setReferencedColumn("id");
        courseRef.setManyToMany(true); // ⛳ pseudo-constraint!
        enrollmentTable.addColumn(courseRef);

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Student", studentTable);
        tableMap.put("Course", courseTable);
        tableMap.put("Enrollment", enrollmentTable);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);

        // 🔍 Execute
        resolver.resolveRelationshipsForAllTables();

        List<Relationship> relationships = resolver.getRelationships();

        // ✅ Έλεγχος: Πρέπει να έχουμε 2 σχέσεις τύπου MANYTOMANY
        assertEquals(2, relationships.size(), "Expected 2 pseudo-ManyToMany relationships");

        for (Relationship rel : relationships) {
            assertEquals(Relationship.RelationshipType.MANYTOMANY, rel.getRelationshipType(), "Expected MANYTOMANY type");
            assertNull(rel.getMappedBy(), "MappedBy must be null for pseudo-ManyToMany");
        }

        log.info("🎯 Pseudo-ManyToMany relationships resolved correctly.");
    }


}