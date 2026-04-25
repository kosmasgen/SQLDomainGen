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

        log.info("Table map initialized with tables: {}", tableMap.keySet());
        resolver = new RelationshipResolver(tableMap);
    }


    private Table createTable(String name) {
        Table table = new Table();
        table.setName(name);
        return table;
    }


    private Column createColumn(String name, String sqlType, boolean isPrimaryKey, boolean isForeignKey, String referencedTable, String referencedColumn, boolean unique, String mappedBy) {
        Column column = new Column();
        column.setName(name);
        column.setSqlType(sqlType);
        column.setPrimaryKey(isPrimaryKey);
        column.setForeignKey(isForeignKey);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        column.setUnique(unique);
        column.setMappedBy(mappedBy);


        log.info("Created Column: name={}, sqlType={}, isPrimaryKey={}, isForeignKey={}, referencedTable={}, referencedColumn={}, unique={}, mappedBy={}",
                name, sqlType, isPrimaryKey, isForeignKey, referencedTable, referencedColumn, unique, mappedBy);

        return column;
    }

    @Test
    void testResolveOneToOneRelationship() {
        log.info("Running test: testResolveOneToOneRelationship");

        Table profilesTable = tableMap.get("Profiles");
        Table usersTable = tableMap.get("Users");

        log.info("Profiles Table: {}", profilesTable);
        log.info("Users Table: {}", usersTable);

        List<Relationship> relationships = resolver.resolveRelationships(profilesTable);
        log.info("Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "OneToOne should create one relationship.");

        Relationship relationship = relationships.getFirst();
        log.info("OneToOne relationship found: {}", relationship);

        assertEquals("Profiles", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOONE, relationship.getRelationshipType());


        assertNull(relationship.getMappedBy(), "mappedBy should be NULL on owning side of OneToOne");


        List<Relationship> inverseRelationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOONE)
                .toList();

        assertEquals(1, inverseRelationships.size(), "Expected one OneToOne relationship in Users table.");
        Relationship inverseRelationship = inverseRelationships.getFirst();

        assertEquals("Users", inverseRelationship.getSourceTable());
        assertEquals("id", inverseRelationship.getSourceColumn());
        assertEquals("Profiles", inverseRelationship.getTargetTable());
        assertEquals("user_id", inverseRelationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOONE, inverseRelationship.getRelationshipType());


        assertEquals("user", inverseRelationship.getMappedBy(), "mappedBy should be 'user' on inverse side of OneToOne");

        log.info("OneToOne relationship resolved correctly!");
    }

    @Test
    void testResolveManyToOneRelationship() {
        log.info("Running test: testResolveManyToOneRelationship");

        Table ordersTable = tableMap.get("Orders");
        List<Relationship> relationships = resolver.resolveRelationships(ordersTable);

        log.info("Found Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "ManyToOne should create one relationship.");

        Relationship relationship = relationships.getFirst();
        log.info("ManyToOne relationship found: {}", relationship);
        assertEquals("Orders", relationship.getSourceTable());
        assertEquals("user_id", relationship.getSourceColumn());
        assertEquals("Users", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());

        log.info("ManyToOne relationship resolved correctly!");
    }

    @Test
    void testTableMapInitialization() {
        log.info("Running test: testTableMapInitialization");

        assertNotNull(tableMap, "tableMap must not be null.");
        assertEquals(5, tableMap.size(), "Unexpected number of tables.");

        // Validate expected tables exist
        assertTrue(tableMap.containsKey("Users"), "Missing table 'Users'.");
        assertTrue(tableMap.containsKey("Profiles"), "Missing table 'Profiles'.");
        assertTrue(tableMap.containsKey("Orders"), "Missing table 'Orders'.");
        assertTrue(tableMap.containsKey("Products"), "Missing table 'Products'.");
        assertTrue(tableMap.containsKey("OrderProducts"), "Missing table 'OrderProducts'.");

        // Validate Users table columns
        Table usersTable = tableMap.get("Users");
        assertEquals(1, usersTable.getColumns().size(), "Unexpected number of columns in 'Users'.");
        assertTrue(
                usersTable.getColumns().stream()
                        .anyMatch(column -> column.getName().equals("id") && column.isPrimaryKey()),
                "Missing primary key 'id' in 'Users'."
        );

        // Validate Profiles table foreign key
        Table profilesTable = tableMap.get("Profiles");
        assertTrue(
                profilesTable.getColumns().stream()
                        .anyMatch(column -> column.getName().equals("user_id") && column.isForeignKey()),
                "Missing foreign key 'user_id' in 'Profiles'."
        );

        // Validate Orders table foreign key
        Table ordersTable = tableMap.get("Orders");
        assertTrue(
                ordersTable.getColumns().stream()
                        .anyMatch(column -> column.getName().equals("user_id") && column.isForeignKey()),
                "Invalid or missing foreign key 'user_id' in 'Orders'."
        );

        log.info("tableMap is correctly initialized with all tables and columns.");
    }


    @Test
    void testResolveOneToManyRelationship() {
        log.info("Running test: testResolveOneToManyRelationship");

        Table usersTable = tableMap.get("Users");


        tableMap.values().forEach(table -> resolver.resolveRelationships(table));


        List<Relationship> relationships = usersTable.getRelationships().stream()
                .filter(rel -> rel.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();

        log.info("Found OneToMany Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "Expected one OneToMany relationship.");

        Relationship relationship = relationships.getFirst();
        assertEquals("Users", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("Orders", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, relationship.getRelationshipType());


        assertEquals("user", relationship.getMappedBy(), "mappedBy should be 'user' for Users -> Orders");

        log.info("OneToMany relationship resolved correctly!");
    }



    @Test
    void testSelfReferencingForeignKeyDetected() {
        // Setup: Department table with self-referencing FK (parent_dept_id)
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

        // Assertion: 1 self-referencing ManyToOne
        assertEquals(1, relationships.size(), "Expected one self-referencing relationship");

        Relationship relationship = relationships.getFirst();
        assertEquals("Department", relationship.getSourceTable());
        assertEquals("parent_dept_id", relationship.getSourceColumn());
        assertEquals("Department", relationship.getTargetTable());
        assertEquals("department_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());
    }

    @Test
    void testSelfReferencingSupervisorRelationshipDetected() {
        log.info("Running test: testSelfReferencingSupervisorRelationshipDetected");

        // User με self-referencing foreign key: supervisor_id → user_id
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
        supervisorId.setUnique(false); // ManyToOne
        userTable.addColumn(supervisorId);

        Map<String, Table> map = new HashMap<>();
        map.put("User", userTable);

        RelationshipResolver resolver = new RelationshipResolver(map);
        List<Relationship> relationships = resolver.resolveRelationships(userTable);

        log.info("Relationships: {}", relationships);

        assertEquals(1, relationships.size(), "Expected one self-referencing relationship");

        Relationship relationship = relationships.getFirst();
        assertEquals("User", relationship.getSourceTable());
        assertEquals("supervisor_id", relationship.getSourceColumn());
        assertEquals("User", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(Relationship.RelationshipType.MANYTOONE, relationship.getRelationshipType());


        List<Relationship> inverseRels = userTable.getRelationships().stream()
                .filter(r -> r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY)
                .toList();

        assertEquals(1, inverseRels.size(), "Expected one inverse ONETOMANY relationship");

        Relationship inverse = inverseRels.getFirst();
        assertEquals("User", inverse.getSourceTable());
        assertEquals("user_id", inverse.getSourceColumn());
        assertEquals("User", inverse.getTargetTable());
        assertEquals("supervisor_id", inverse.getTargetColumn());
        assertEquals(Relationship.RelationshipType.ONETOMANY, inverse.getRelationshipType());


        assertEquals("supervisor", inverse.getMappedBy(), "mappedBy should be 'supervisor'");

        log.info("Self-referencing supervisor relationship resolved successfully!");
    }

    @Test
    void testPseudoManyToManyConstraintResolvesCorrectly() {
        log.info("Running test: testPseudoManyToManyConstraintResolvesCorrectly");

        Table studentTable = new Table();
        studentTable.setName("Student");
        Column studentId = new Column();
        studentId.setName("id");
        studentId.setPrimaryKey(true);
        studentId.setSqlType("BIGINT");
        studentId.setUnique(true);
        studentTable.addColumn(studentId);

        // Table: Course
        Table courseTable = new Table();
        courseTable.setName("Course");
        Column courseId = new Column();
        courseId.setName("id");
        courseId.setPrimaryKey(true);
        courseId.setSqlType("BIGINT");
        courseId.setUnique(true);
        courseTable.addColumn(courseId);

        // Table: Enrollment (pseudo-ManyToMany via constraint)
        Table enrollmentTable = new Table();
        enrollmentTable.setName("Enrollment");

        Column studentRef = new Column();
        studentRef.setName("student_id");
        studentRef.setSqlType("BIGINT");
        studentRef.setForeignKey(true);
        studentRef.setReferencedTable("Student");
        studentRef.setReferencedColumn("id");
        studentRef.setManyToMany(true); //  pseudo-constraint!
        enrollmentTable.addColumn(studentRef);

        Column courseRef = new Column();
        courseRef.setName("course_id");
        courseRef.setSqlType("BIGINT");
        courseRef.setForeignKey(true);
        courseRef.setReferencedTable("Course");
        courseRef.setReferencedColumn("id");
        courseRef.setManyToMany(true); // pseudo-constraint!
        enrollmentTable.addColumn(courseRef);

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Student", studentTable);
        tableMap.put("Course", courseTable);
        tableMap.put("Enrollment", enrollmentTable);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);

        //  Execute
        resolver.resolveRelationshipsForAllTables();

        List<Relationship> relationships = resolver.getRelationships();


        assertEquals(2, relationships.size(), "Expected 2 pseudo-ManyToMany relationships");

        for (Relationship rel : relationships) {
            assertEquals(Relationship.RelationshipType.MANYTOMANY, rel.getRelationshipType(), "Expected MANYTOMANY type");
            assertNull(rel.getMappedBy(), "MappedBy must be null for pseudo-ManyToMany");
        }

        log.info("Pseudo-ManyToMany relationships resolved correctly.");
    }



    @Test
    void testResolveRelationships_CompanyTable_CreatesFkToStatusAndSelfReference() {
        Table companyStatus = new Table();
        companyStatus.setName("pep_schema.company_status");

        Column companyStatusId = new Column();
        companyStatusId.setName("id");
        companyStatusId.setSqlType("uuid");
        companyStatusId.setJavaType("java.util.UUID");
        companyStatusId.setPrimaryKey(true);
        companyStatusId.setNullable(false);
        companyStatus.setColumns(new ArrayList<>(List.of(companyStatusId)));

        Table company = new Table();
        company.setName("pep_schema.company");

        Column companyId = new Column();
        companyId.setName("id");
        companyId.setSqlType("uuid");
        companyId.setJavaType("java.util.UUID");
        companyId.setPrimaryKey(true);
        companyId.setNullable(false);

        Column companyStatusFk = new Column();
        companyStatusFk.setName("company_status_id");
        companyStatusFk.setSqlType("uuid");
        companyStatusFk.setJavaType("java.util.UUID");
        companyStatusFk.setForeignKey(true);
        companyStatusFk.setNullable(true);
        companyStatusFk.setReferencedTable("pep_schema.company_status");
        companyStatusFk.setReferencedColumn("id");

        Column parentCompanyFk = new Column();
        parentCompanyFk.setName("parent_company_id");
        parentCompanyFk.setSqlType("uuid");
        parentCompanyFk.setJavaType("java.util.UUID");
        parentCompanyFk.setForeignKey(true);
        parentCompanyFk.setNullable(true);
        parentCompanyFk.setReferencedTable("pep_schema.company");
        parentCompanyFk.setReferencedColumn("id");

        company.setColumns(new ArrayList<>(List.of(
                companyId,
                companyStatusFk,
                parentCompanyFk
        )));

        resolver = new RelationshipResolver(Map.of(
                companyStatus.getName(), companyStatus,
                company.getName(), company
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(company);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company".equals(r.getSourceTable()) &&
                                "company_status_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_status".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company.company_status_id -> company_status.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company".equals(r.getSourceTable()) &&
                                "parent_company_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected self-referencing MANYTOONE relationship company.parent_company_id -> company.id");

        assertTrue(companyStatus.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_status".equals(r.getSourceTable()) &&
                                "pep_schema.company".equals(r.getTargetTable()) &&
                                "companyStatus".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_status mappedBy='companyStatus'");

        assertTrue(company.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company".equals(r.getSourceTable()) &&
                                "pep_schema.company".equals(r.getTargetTable()) &&
                                "parentCompany".equals(r.getMappedBy())
                ),
                "Expected inverse self ONETOMANY on company mappedBy='parentCompany'");
    }


    @Test
    void testResolveRelationships_CompanyProfileI18nTable_CreatesTwoManyToOneAndInverseOneToMany() {
        Table companyProfile = new Table();
        companyProfile.setName("pep_schema.company_profile");

        Column companyProfileId = new Column();
        companyProfileId.setName("id");
        companyProfileId.setSqlType("uuid");
        companyProfileId.setJavaType("java.util.UUID");
        companyProfileId.setPrimaryKey(true);
        companyProfileId.setNullable(false);
        companyProfile.setColumns(new ArrayList<>(List.of(companyProfileId)));

        Table language = new Table();
        language.setName("pep_schema.languages");

        Column languageId = new Column();
        languageId.setName("id");
        languageId.setSqlType("uuid");
        languageId.setJavaType("java.util.UUID");
        languageId.setPrimaryKey(true);
        languageId.setNullable(false);
        language.setColumns(new ArrayList<>(List.of(languageId)));

        Table companyProfileI18n = new Table();
        companyProfileI18n.setName("pep_schema.company_profile_i18n");

        Column companyProfileFk = new Column();
        companyProfileFk.setName("company_profile_id");
        companyProfileFk.setSqlType("uuid");
        companyProfileFk.setJavaType("java.util.UUID");
        companyProfileFk.setPrimaryKey(true);
        companyProfileFk.setForeignKey(true);
        companyProfileFk.setNullable(false);
        companyProfileFk.setReferencedTable("pep_schema.company_profile");
        companyProfileFk.setReferencedColumn("id");

        Column languageFk = new Column();
        languageFk.setName("language_id");
        languageFk.setSqlType("uuid");
        languageFk.setJavaType("java.util.UUID");
        languageFk.setPrimaryKey(true);
        languageFk.setForeignKey(true);
        languageFk.setNullable(false);
        languageFk.setReferencedTable("pep_schema.languages");
        languageFk.setReferencedColumn("id");

        companyProfileI18n.setColumns(new ArrayList<>(List.of(companyProfileFk, languageFk)));

        resolver = new RelationshipResolver(Map.of(
                companyProfile.getName(), companyProfile,
                language.getName(), language,
                companyProfileI18n.getName(), companyProfileI18n
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyProfileI18n);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_profile_i18n.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profile_i18n".equals(r.getSourceTable()) &&
                                "company_profile_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_profile".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profile_i18n.company_profile_id -> company_profile.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profile_i18n".equals(r.getSourceTable()) &&
                                "language_id".equals(r.getSourceColumn()) &&
                                "pep_schema.languages".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profile_i18n.language_id -> languages.id");

        assertTrue(companyProfile.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_profile".equals(r.getSourceTable()) &&
                                "pep_schema.company_profile_i18n".equals(r.getTargetTable()) &&
                                "companyProfile".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_profile mappedBy='companyProfile'");

        assertTrue(language.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.languages".equals(r.getSourceTable()) &&
                                "pep_schema.company_profile_i18n".equals(r.getTargetTable()) &&
                                "language".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on languages mappedBy='language'");
    }

    @Test
    void testResolveRelationships_CompanyProfessionTable_CreatesTwoManyToOneAndInverseOneToMany() {
        Table company = new Table();
        company.setName("pep_schema.company");

        Column companyId = new Column();
        companyId.setName("id");
        companyId.setSqlType("uuid");
        companyId.setJavaType("java.util.UUID");
        companyId.setPrimaryKey(true);
        companyId.setNullable(false);
        company.setColumns(new ArrayList<>(List.of(companyId)));

        Table profession = new Table();
        profession.setName("pep_schema.profession");

        Column professionId = new Column();
        professionId.setName("id");
        professionId.setSqlType("uuid");
        professionId.setJavaType("java.util.UUID");
        professionId.setPrimaryKey(true);
        professionId.setNullable(false);
        profession.setColumns(new ArrayList<>(List.of(professionId)));

        Table companyProfession = new Table();
        companyProfession.setName("pep_schema.company_profession");

        Column id = new Column();
        id.setName("id");
        id.setSqlType("uuid");
        id.setJavaType("java.util.UUID");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column companyFk = new Column();
        companyFk.setName("company_id");
        companyFk.setSqlType("uuid");
        companyFk.setJavaType("java.util.UUID");
        companyFk.setForeignKey(true);
        companyFk.setNullable(false);
        companyFk.setReferencedTable("pep_schema.company");
        companyFk.setReferencedColumn("id");

        Column professionFk = new Column();
        professionFk.setName("profession_id");
        professionFk.setSqlType("uuid");
        professionFk.setJavaType("java.util.UUID");
        professionFk.setForeignKey(true);
        professionFk.setNullable(false);
        professionFk.setReferencedTable("pep_schema.profession");
        professionFk.setReferencedColumn("id");

        Column fromDate = new Column();
        fromDate.setName("from_date");
        fromDate.setSqlType("timestamp");
        fromDate.setJavaType("java.time.LocalDateTime");
        fromDate.setNullable(true);

        Column toDate = new Column();
        toDate.setName("to_date");
        toDate.setSqlType("timestamp");
        toDate.setJavaType("java.time.LocalDateTime");
        toDate.setNullable(true);

        Column notes = new Column();
        notes.setName("notes");
        notes.setSqlType("varchar");
        notes.setJavaType("String");
        notes.setNullable(true);

        companyProfession.setColumns(new ArrayList<>(List.of(
                id,
                companyFk,
                professionFk,
                fromDate,
                toDate,
                notes
        )));

        resolver = new RelationshipResolver(Map.of(
                company.getName(), company,
                profession.getName(), profession,
                companyProfession.getName(), companyProfession
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyProfession);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_profession.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profession".equals(r.getSourceTable()) &&
                                "company_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profession.company_id -> company.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profession".equals(r.getSourceTable()) &&
                                "profession_id".equals(r.getSourceColumn()) &&
                                "pep_schema.profession".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profession.profession_id -> profession.id");

        assertTrue(company.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company".equals(r.getSourceTable()) &&
                                "pep_schema.company_profession".equals(r.getTargetTable()) &&
                                "company".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company mappedBy='company'");

        assertTrue(profession.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.profession".equals(r.getSourceTable()) &&
                                "pep_schema.company_profession".equals(r.getTargetTable()) &&
                                "profession".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on profession mappedBy='profession'");

        assertFalse(localRelationships.stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY
                ),
                "company_profession should remain a join entity with 2 MANYTOONE relations, not MANYTOMANY.");
    }

    @Test
    void testResolveRelationships_CompanyStatusViewRulesTable_CreatesTwoManyToOneAndInverseOneToMany() {
        Table companyStatus = new Table();
        companyStatus.setName("pep_schema.company_status");

        Column companyStatusId = new Column();
        companyStatusId.setName("id");
        companyStatusId.setSqlType("uuid");
        companyStatusId.setJavaType("java.util.UUID");
        companyStatusId.setPrimaryKey(true);
        companyStatusId.setNullable(false);
        companyStatus.setColumns(new ArrayList<>(List.of(companyStatusId)));

        Table companyViewRules = new Table();
        companyViewRules.setName("pep_schema.company_view_rules");

        Column companyViewRulesId = new Column();
        companyViewRulesId.setName("id");
        companyViewRulesId.setSqlType("uuid");
        companyViewRulesId.setJavaType("java.util.UUID");
        companyViewRulesId.setPrimaryKey(true);
        companyViewRulesId.setNullable(false);
        companyViewRules.setColumns(new ArrayList<>(List.of(companyViewRulesId)));

        Table companyStatusViewRules = new Table();
        companyStatusViewRules.setName("pep_schema.company_status_view_rules");

        Column companyStatusFk = new Column();
        companyStatusFk.setName("company_status_id");
        companyStatusFk.setSqlType("uuid");
        companyStatusFk.setJavaType("java.util.UUID");
        companyStatusFk.setPrimaryKey(true);
        companyStatusFk.setForeignKey(true);
        companyStatusFk.setNullable(false);
        companyStatusFk.setReferencedTable("pep_schema.company_status");
        companyStatusFk.setReferencedColumn("id");

        Column companyViewRulesFk = new Column();
        companyViewRulesFk.setName("company_view_rules_id");
        companyViewRulesFk.setSqlType("uuid");
        companyViewRulesFk.setJavaType("java.util.UUID");
        companyViewRulesFk.setPrimaryKey(true);
        companyViewRulesFk.setForeignKey(true);
        companyViewRulesFk.setNullable(false);
        companyViewRulesFk.setReferencedTable("pep_schema.company_view_rules");
        companyViewRulesFk.setReferencedColumn("id");

        Column excludeCompanies = new Column();
        excludeCompanies.setName("exclude_companies");
        excludeCompanies.setSqlType("bool");
        excludeCompanies.setJavaType("Boolean");
        excludeCompanies.setNullable(true);

        companyStatusViewRules.setColumns(new ArrayList<>(List.of(
                companyStatusFk,
                companyViewRulesFk,
                excludeCompanies
        )));

        resolver = new RelationshipResolver(Map.of(
                companyStatus.getName(), companyStatus,
                companyViewRules.getName(), companyViewRules,
                companyStatusViewRules.getName(), companyStatusViewRules
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyStatusViewRules);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_status_view_rules.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_status_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_status".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_status_id -> company_status.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_view_rules_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_view_rules".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_view_rules_id -> company_view_rules.id");

        assertTrue(companyStatus.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_status".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyStatus".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_status mappedBy='companyStatus'");

        assertTrue(companyViewRules.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_view_rules".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyViewRules".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_view_rules mappedBy='companyViewRules'");
    }


    @Test
    void testResolveRelationships_CompanyStatusViewRulesTable_CreatesTwoManyToOneAndInverseOneToMany2() {
        Table companyStatus = new Table();
        companyStatus.setName("pep_schema.company_status");

        Column companyStatusId = new Column();
        companyStatusId.setName("id");
        companyStatusId.setSqlType("uuid");
        companyStatusId.setJavaType("java.util.UUID");
        companyStatusId.setPrimaryKey(true);
        companyStatusId.setNullable(false);
        companyStatus.setColumns(new ArrayList<>(List.of(companyStatusId)));

        Table companyViewRules = new Table();
        companyViewRules.setName("pep_schema.company_view_rules");

        Column companyViewRulesId = new Column();
        companyViewRulesId.setName("id");
        companyViewRulesId.setSqlType("uuid");
        companyViewRulesId.setJavaType("java.util.UUID");
        companyViewRulesId.setPrimaryKey(true);
        companyViewRulesId.setNullable(false);
        companyViewRules.setColumns(new ArrayList<>(List.of(companyViewRulesId)));

        Table companyStatusViewRules = new Table();
        companyStatusViewRules.setName("pep_schema.company_status_view_rules");

        Column companyStatusFk = new Column();
        companyStatusFk.setName("company_status_id");
        companyStatusFk.setSqlType("uuid");
        companyStatusFk.setJavaType("java.util.UUID");
        companyStatusFk.setPrimaryKey(true);
        companyStatusFk.setForeignKey(true);
        companyStatusFk.setNullable(false);
        companyStatusFk.setReferencedTable("pep_schema.company_status");
        companyStatusFk.setReferencedColumn("id");

        Column companyViewRulesFk = new Column();
        companyViewRulesFk.setName("company_view_rules_id");
        companyViewRulesFk.setSqlType("uuid");
        companyViewRulesFk.setJavaType("java.util.UUID");
        companyViewRulesFk.setPrimaryKey(true);
        companyViewRulesFk.setForeignKey(true);
        companyViewRulesFk.setNullable(false);
        companyViewRulesFk.setReferencedTable("pep_schema.company_view_rules");
        companyViewRulesFk.setReferencedColumn("id");

        Column excludeCompanies = new Column();
        excludeCompanies.setName("exclude_companies");
        excludeCompanies.setSqlType("bool");
        excludeCompanies.setJavaType("Boolean");
        excludeCompanies.setNullable(true);

        companyStatusViewRules.setColumns(new ArrayList<>(List.of(
                companyStatusFk,
                companyViewRulesFk,
                excludeCompanies
        )));

        resolver = new RelationshipResolver(Map.of(
                companyStatus.getName(), companyStatus,
                companyViewRules.getName(), companyViewRules,
                companyStatusViewRules.getName(), companyStatusViewRules
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyStatusViewRules);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_status_view_rules.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_status_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_status".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_status_id -> company_status.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_view_rules_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_view_rules".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_view_rules_id -> company_view_rules.id");

        assertTrue(companyStatus.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_status".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyStatus".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_status mappedBy='companyStatus'");

        assertTrue(companyViewRules.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_view_rules".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyViewRules".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_view_rules mappedBy='companyViewRules'");
    }

    @Test
    void testResolveRelationships_CompanyStatusViewRulesTable_CreatesTwoManyToOneAndInverseOneToMany3() {
        Table companyStatus = new Table();
        companyStatus.setName("pep_schema.company_status");

        Column companyStatusId = new Column();
        companyStatusId.setName("id");
        companyStatusId.setSqlType("uuid");
        companyStatusId.setJavaType("java.util.UUID");
        companyStatusId.setPrimaryKey(true);
        companyStatusId.setNullable(false);
        companyStatus.setColumns(new ArrayList<>(List.of(companyStatusId)));

        Table companyViewRules = new Table();
        companyViewRules.setName("pep_schema.company_view_rules");

        Column companyViewRulesId = new Column();
        companyViewRulesId.setName("id");
        companyViewRulesId.setSqlType("uuid");
        companyViewRulesId.setJavaType("java.util.UUID");
        companyViewRulesId.setPrimaryKey(true);
        companyViewRulesId.setNullable(false);
        companyViewRules.setColumns(new ArrayList<>(List.of(companyViewRulesId)));

        Table companyStatusViewRules = new Table();
        companyStatusViewRules.setName("pep_schema.company_status_view_rules");

        Column companyStatusFk = new Column();
        companyStatusFk.setName("company_status_id");
        companyStatusFk.setSqlType("uuid");
        companyStatusFk.setJavaType("java.util.UUID");
        companyStatusFk.setPrimaryKey(true);
        companyStatusFk.setForeignKey(true);
        companyStatusFk.setNullable(false);
        companyStatusFk.setReferencedTable("pep_schema.company_status");
        companyStatusFk.setReferencedColumn("id");

        Column companyViewRulesFk = new Column();
        companyViewRulesFk.setName("company_view_rules_id");
        companyViewRulesFk.setSqlType("uuid");
        companyViewRulesFk.setJavaType("java.util.UUID");
        companyViewRulesFk.setPrimaryKey(true);
        companyViewRulesFk.setForeignKey(true);
        companyViewRulesFk.setNullable(false);
        companyViewRulesFk.setReferencedTable("pep_schema.company_view_rules");
        companyViewRulesFk.setReferencedColumn("id");

        Column excludeCompanies = new Column();
        excludeCompanies.setName("exclude_companies");
        excludeCompanies.setSqlType("bool");
        excludeCompanies.setJavaType("Boolean");
        excludeCompanies.setNullable(true);

        companyStatusViewRules.setColumns(new ArrayList<>(List.of(
                companyStatusFk,
                companyViewRulesFk,
                excludeCompanies
        )));

        resolver = new RelationshipResolver(Map.of(
                companyStatus.getName(), companyStatus,
                companyViewRules.getName(), companyViewRules,
                companyStatusViewRules.getName(), companyStatusViewRules
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyStatusViewRules);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_status_view_rules.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_status_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_status".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_status_id -> company_status.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_status_view_rules".equals(r.getSourceTable()) &&
                                "company_view_rules_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company_view_rules".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_status_view_rules.company_view_rules_id -> company_view_rules.id");

        assertTrue(companyStatus.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_status".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyStatus".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_status mappedBy='companyStatus'");

        assertTrue(companyViewRules.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company_view_rules".equals(r.getSourceTable()) &&
                                "pep_schema.company_status_view_rules".equals(r.getTargetTable()) &&
                                "companyViewRules".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company_view_rules mappedBy='companyViewRules'");
    }

    @Test
    void testResolveRelationships_CompanyProfessionSystemLinkTable_CreatesTwoManyToOneAndInverseOneToMany() {
        Table company = new Table();
        company.setName("pep_schema.company");

        Column companyId = new Column();
        companyId.setName("id");
        companyId.setSqlType("uuid");
        companyId.setJavaType("java.util.UUID");
        companyId.setPrimaryKey(true);
        companyId.setNullable(false);
        company.setColumns(new ArrayList<>(List.of(companyId)));

        Table professionSystem = new Table();
        professionSystem.setName("pep_schema.profession_system");

        Column professionSystemId = new Column();
        professionSystemId.setName("id");
        professionSystemId.setSqlType("uuid");
        professionSystemId.setJavaType("java.util.UUID");
        professionSystemId.setPrimaryKey(true);
        professionSystemId.setNullable(false);
        professionSystem.setColumns(new ArrayList<>(List.of(professionSystemId)));

        Table companyProfessionSystemLink = new Table();
        companyProfessionSystemLink.setName("pep_schema.company_profession_system_link");

        Column companyFk = new Column();
        companyFk.setName("company_id");
        companyFk.setSqlType("uuid");
        companyFk.setJavaType("java.util.UUID");
        companyFk.setPrimaryKey(true);
        companyFk.setForeignKey(true);
        companyFk.setNullable(false);
        companyFk.setReferencedTable("pep_schema.company");
        companyFk.setReferencedColumn("id");

        Column professionSystemFk = new Column();
        professionSystemFk.setName("profession_system_id");
        professionSystemFk.setSqlType("uuid");
        professionSystemFk.setJavaType("java.util.UUID");
        professionSystemFk.setPrimaryKey(true);
        professionSystemFk.setForeignKey(true);
        professionSystemFk.setNullable(false);
        professionSystemFk.setReferencedTable("pep_schema.profession_system");
        professionSystemFk.setReferencedColumn("id");

        companyProfessionSystemLink.setColumns(new ArrayList<>(List.of(companyFk, professionSystemFk)));

        resolver = new RelationshipResolver(Map.of(
                company.getName(), company,
                professionSystem.getName(), professionSystem,
                companyProfessionSystemLink.getName(), companyProfessionSystemLink
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(companyProfessionSystemLink);

        assertNotNull(localRelationships);
        assertEquals(2, localRelationships.size(),
                "Expected exactly 2 owning MANYTOONE relationships for company_profession_system_link.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profession_system_link".equals(r.getSourceTable()) &&
                                "company_id".equals(r.getSourceColumn()) &&
                                "pep_schema.company".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profession_system_link.company_id -> company.id");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "pep_schema.company_profession_system_link".equals(r.getSourceTable()) &&
                                "profession_system_id".equals(r.getSourceColumn()) &&
                                "pep_schema.profession_system".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE
                ),
                "Expected MANYTOONE relationship company_profession_system_link.profession_system_id -> profession_system.id");

        assertTrue(company.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.company".equals(r.getSourceTable()) &&
                                "pep_schema.company_profession_system_link".equals(r.getTargetTable()) &&
                                "company".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on company mappedBy='company'");

        assertTrue(professionSystem.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOMANY &&
                                "pep_schema.profession_system".equals(r.getSourceTable()) &&
                                "pep_schema.company_profession_system_link".equals(r.getTargetTable()) &&
                                "professionSystem".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOMANY on profession_system mappedBy='professionSystem'");
    }


    @Test
    void testResolveRelationships_ProfileTable_CreatesOneToOneAndInverseOneToOne() {
        Table users = new Table();
        users.setName("Users");

        Column userId = new Column();
        userId.setName("id");
        userId.setSqlType("BIGINT");
        userId.setJavaType("java.lang.Long");
        userId.setPrimaryKey(true);
        userId.setNullable(false);
        users.setColumns(new ArrayList<>(List.of(userId)));

        Table profiles = new Table();
        profiles.setName("Profiles");

        Column profileId = new Column();
        profileId.setName("id");
        profileId.setSqlType("BIGINT");
        profileId.setJavaType("java.lang.Long");
        profileId.setPrimaryKey(true);
        profileId.setNullable(false);

        Column userFk = new Column();
        userFk.setName("user_id");
        userFk.setSqlType("BIGINT");
        userFk.setJavaType("java.lang.Long");
        userFk.setForeignKey(true);
        userFk.setNullable(false);
        userFk.setUnique(true);
        userFk.setReferencedTable("Users");
        userFk.setReferencedColumn("id");

        profiles.setColumns(new ArrayList<>(List.of(profileId, userFk)));

        resolver = new RelationshipResolver(Map.of(
                users.getName(), users,
                profiles.getName(), profiles
        ));

        List<Relationship> localRelationships = resolver.resolveRelationships(profiles);

        assertNotNull(localRelationships);
        assertEquals(1, localRelationships.size(),
                "Expected exactly 1 owning ONETOONE relationship for Profiles.");

        assertTrue(localRelationships.stream().anyMatch(r ->
                        "Profiles".equals(r.getSourceTable()) &&
                                "user_id".equals(r.getSourceColumn()) &&
                                "Users".equals(r.getTargetTable()) &&
                                "id".equals(r.getTargetColumn()) &&
                                r.getRelationshipType() == Relationship.RelationshipType.ONETOONE
                ),
                "Expected ONETOONE relationship Profiles.user_id -> Users.id");

        assertTrue(users.getRelationships().stream().anyMatch(r ->
                        r.getRelationshipType() == Relationship.RelationshipType.ONETOONE &&
                                "Users".equals(r.getSourceTable()) &&
                                "Profiles".equals(r.getTargetTable()) &&
                                "user".equals(r.getMappedBy())
                ),
                "Expected inverse ONETOONE on Users mappedBy='user'");
    }


}