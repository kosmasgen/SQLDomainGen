package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.generator.RelationshipResolver;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class EntityGeneratorTest {

    private EntityGenerator entityGenerator;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        entityGenerator = new EntityGenerator();
        log.info("🔧 Setting up EntityGeneratorTest...");
    }

    @Test
    void testCreateEntityContent_ForCompositePkJoinTable_GeneratesEmbeddedIdAndMapsId() {
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

        // Join table: pep_schema.business_location_i18n
        Table joinTable = new Table();
        joinTable.setName("pep_schema.business_location_i18n");

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

        Column dateCreated = new Column();
        dateCreated.setName("date_created");
        dateCreated.setSqlType("timestamp");
        dateCreated.setJavaType("java.time.LocalDateTime");
        dateCreated.setNullable(false);

        Column lastUpdated = new Column();
        lastUpdated.setName("last_updated");
        lastUpdated.setSqlType("timestamp");
        lastUpdated.setJavaType("java.time.LocalDateTime");
        lastUpdated.setNullable(false);

        Column recdeleted = new Column();
        recdeleted.setName("recdeleted");
        recdeleted.setSqlType("bool");
        recdeleted.setJavaType("java.lang.Boolean");
        recdeleted.setNullable(false);
        recdeleted.setDefaultValue("false");

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

        joinTable.setColumns(new ArrayList<>(List.of(
                description, code, dateCreated, lastUpdated, recdeleted, businessLocationId, languageId
        )));

        // Resolve relationships first (same flow as production generator)
        RelationshipResolver relationshipResolver = new RelationshipResolver(Map.of(
                businessLocation.getName(), businessLocation,
                languages.getName(), languages,
                joinTable.getName(), joinTable
        ));
        relationshipResolver.resolveRelationships(joinTable);

        String content = entityGenerator.createEntityContent(joinTable, "gr.knowledge.pepTest.entity", true);

        assertNotNull(content);

        // Class and table
        assertTrue(content.contains("public class BusinessLocationI18n"), "Expected entity class name BusinessLocationI18n.");
        assertTrue(content.contains("@Table(name = \"business_location_i18n\")"), "Expected correct @Table annotation.");

        // Composite PK join entity pattern
        assertTrue(content.contains("@EmbeddedId"), "Expected @EmbeddedId for composite PK join entity.");
        assertTrue(content.contains("private Id id;"), "Expected embedded id field 'id'.");

        assertTrue(content.contains("@MapsId(\"businessLocationId\")"),
                "Expected @MapsId for business_location_id.");
        assertTrue(content.contains("@MapsId(\"languageId\")"),
                "Expected @MapsId for language_id.");

        assertTrue(content.contains("private BusinessLocation businessLocation;"),
                "Expected @ManyToOne relation field to BusinessLocation.");
        assertTrue(content.contains("private Languages language;"),
                "Expected @ManyToOne relation field to Languages.");

        assertTrue(content.contains("@Embeddable"), "Expected nested @Embeddable Id class.");
        assertTrue(content.contains("public static class Id implements java.io.Serializable"),
                "Expected nested serializable Id class.");
        assertTrue(content.contains("private UUID businessLocationId;"),
                "Expected businessLocationId inside embedded Id.");
        assertTrue(content.contains("private UUID languageId;"),
                "Expected languageId inside embedded Id.");

        // Regular columns must still exist
        assertTrue(content.contains("private String description;"), "Expected normal column field: description.");
        assertTrue(content.contains("private String code;"), "Expected normal column field: code.");
        assertTrue(content.contains("private Boolean recdeleted = false;"), "Expected boolean default handling.");

        // Must NOT generate legacy wrong style for composite FK PK columns
        assertFalse(content.contains("@GeneratedValue(generator = \"UUID\")"),
                "Composite PK join entity FK columns must not be generated as standalone UUID IDs.");
        assertFalse(content.contains("@Id\n    @Id"),
                "Should not generate duplicated standalone @Id fields.");

        // Composite FK primitive fields should appear only once each (inside embedded Id)
        String businessLocationIdField = "private UUID businessLocationId;";
        assertEquals(content.indexOf(businessLocationIdField), content.lastIndexOf(businessLocationIdField),
                "businessLocationId primitive field should exist only once (inside EmbeddedId).");

        String languageIdField = "private UUID languageId;";
        assertEquals(content.indexOf(languageIdField), content.lastIndexOf(languageIdField),
                "languageId primitive field should exist only once (inside EmbeddedId).");

        // Join entity should not generate collection navigation fields on itself
        assertFalse(content.contains("@OneToMany("),
                "Composite join entity should not generate inverse @OneToMany collections.");
        assertFalse(content.contains("@ManyToMany("),
                "Composite join entity should not generate @ManyToMany collections.");
    }

    @Test
    void testGenerateEntityWithManyToOne() throws IOException {
        log.info("🟢 Running testGenerateEntityWithManyToOne...");

        Table orders = new Table();
        orders.setName("Orders");

        Table customers = new Table();
        customers.setName("Customers");

        Column customerId = new Column();
        customerId.setName("id");
        customerId.setSqlType("INT");
        customerId.setJavaType("Long");
        customerId.setPrimaryKey(true);
        customerId.setNullable(false);
        customers.setColumns(List.of(customerId));

        Column orderId = new Column();
        orderId.setName("id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);
        orderId.setNullable(false);

        Column customerIdColumn = new Column();
        customerIdColumn.setName("customer_id");
        customerIdColumn.setSqlType("INT");
        customerIdColumn.setJavaType("Long");
        customerIdColumn.setNullable(false);
        customerIdColumn.setForeignKey(true);
        customerIdColumn.setReferencedTable("Customers");
        customerIdColumn.setReferencedColumn("id");

        orders.setColumns(List.of(orderId, customerIdColumn));

        entityGenerator.generate(List.of(orders, customers), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("Orders.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        log.debug("📄 Generated content: \n{}", content);

        System.out.println("---- Orders.java (ManyToOne) ----");
        System.out.println(content);
        System.out.println("-------------------------------");


        assertTrue(content.contains("@ManyToOne"), "⚠️ Δεν βρέθηκε η σχέση ManyToOne");
        assertTrue(content.contains("@JoinColumn(name = \"customer_id\", referencedColumnName = \"id\")"), "⚠️ Το JoinColumn δεν δημιουργήθηκε σωστά");

        // ✅ Έλεγχος για το όνομα του field (customer)
        assertTrue(content.contains("private Customers customer"), "⚠️ Το όνομα του field για το ManyToOne δεν είναι σωστό");
    }

    @Test
    void testGenerateEntityWithOneToOne() throws IOException {
        log.info("🟢 Running testGenerateEntityWithOneToOne...");

        // Ορίζουμε τον σταθερό φάκελο εξόδου (π.χ. Επιφάνεια Εργασίας)
        Path outputDir = Paths.get(System.getProperty("user.home"), "Desktop", "GeneratedEntities");
        Files.createDirectories(outputDir); // Δημιουργούμε τον φάκελο αν δεν υπάρχει

        // Δημιουργούμε τα tables και τις στήλες
        Table users = new Table();
        users.setName("Users");

        Table userDetails = new Table();
        userDetails.setName("UserDetails");

        Column userId = new Column();
        userId.setName("id");
        userId.setSqlType("INT");
        userId.setJavaType("Long");
        userId.setPrimaryKey(true);
        users.setColumns(new ArrayList<>(List.of(userId)));

        Column detailsId = new Column();
        detailsId.setName("id");
        detailsId.setSqlType("INT");
        detailsId.setJavaType("Long");
        detailsId.setPrimaryKey(true);
        userDetails.setColumns(new ArrayList<>(List.of(detailsId)));

        Column userIdFk = new Column();
        userIdFk.setName("user_id");
        userIdFk.setSqlType("INT");
        userIdFk.setJavaType("Long");
        userIdFk.setPrimaryKey(false);
        userIdFk.setForeignKey(true);
        userIdFk.setNullable(false);
        userIdFk.setUnique(true);
        userIdFk.setReferencedTable("Users");
        userIdFk.setReferencedColumn("id");
        userDetails.getColumns().add(userIdFk);

        // Καλούμε το generate περνώντας το outputDir
        entityGenerator.generate(List.of(users, userDetails), outputDir.toString(), "com.example.entities", true, false);

        // Ελέγχουμε αν δημιουργήθηκε το αρχείο UserDetails.java
        Path generatedUserDetailsFile = outputDir.resolve("UserDetails.java");
        assertTrue(Files.exists(generatedUserDetailsFile), "✅ Generated UserDetails.java file should exist");

        String userDetailsContent = Files.readString(generatedUserDetailsFile);
        log.debug("📄 Generated UserDetails.java content: \n{}", userDetailsContent);
        System.out.println("---- UserDetails.java ----");
        System.out.println(userDetailsContent);
        System.out.println("--------------------------");

        // Ελέγχουμε αν δημιουργήθηκε το αρχείο Users.java
        Path generatedUsersFile = outputDir.resolve("Users.java");
        if (Files.exists(generatedUsersFile)) {
            String usersContent = Files.readString(generatedUsersFile);
            log.debug("📄 Generated Users.java content: \n{}", usersContent);
            System.out.println("---- Users.java ----");
            System.out.println(usersContent);
            System.out.println("--------------------");
        } else {
            log.warn("❌ Users.java file was not generated.");
        }

        // 🔍 Έλεγχοι περιεχομένου αρχείου UserDetails
        assertTrue(userDetailsContent.contains("@OneToOne"), "⚠️ Δεν βρέθηκε η σχέση OneToOne");
        assertTrue(userDetailsContent.contains("@JoinColumn(name = \"user_id\""), "⚠️ Δεν βρέθηκε το JoinColumn για user_id");
        assertTrue(userDetailsContent.contains("private Users user"), "⚠️ Το όνομα του field (user) δεν είναι σωστό");

        // ❌ Δεν πρέπει να υπάρχει mappedBy εδώ
        assertFalse(userDetailsContent.contains("mappedBy"), "❌ Το mappedBy δεν πρέπει να υπάρχει στο UserDetails (owning πλευρά)");
    }


    @Test
    void testGenerateEntityWithOneToMany() {
        log.info("🟢 Running testGenerateEntityWithOneToMany...");

        Table customers = new Table();
        customers.setName("Customers");

        Table orders = new Table();
        orders.setName("Orders");

        Column customerId = new Column();
        customerId.setName("id");
        customerId.setSqlType("INT");
        customerId.setJavaType("Long");
        customerId.setPrimaryKey(true);
        customers.setColumns(new ArrayList<>(List.of(customerId)));

        Column orderId = new Column();
        orderId.setName("id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);

        Column customerIdFk = new Column();
        customerIdFk.setName("customer_id");
        customerIdFk.setSqlType("INT");
        customerIdFk.setJavaType("Long");
        customerIdFk.setPrimaryKey(false);
        customerIdFk.setForeignKey(true);
        customerIdFk.setNullable(false);
        customerIdFk.setReferencedTable("Customers");
        customerIdFk.setReferencedColumn("id");

        orders.setColumns(new ArrayList<>(List.of(orderId, customerIdFk)));

        // Δημιουργούμε το map που περιμένει ο RelationshipResolver
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Customers", customers);
        tableMap.put("Orders", orders);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        EntityGenerator generator = new EntityGenerator();
        String content = generator.createEntityContent(customers, "com.example.entities", false);

        log.debug("📄 Generated content of Customers.java:\n{}", content);

        boolean hasOneToMany = content.contains("@OneToMany");
        boolean hasCorrectMappedBy = content.contains("mappedBy = \"customer\"");
        boolean hasCorrectListField = content.contains("private List<Orders> orders");

        if (!hasOneToMany) {
            log.error("❌ @OneToMany annotation is missing!");
        }

        if (!hasCorrectMappedBy) {
            log.error("❌ mappedBy = \"customer\" is missing or incorrect!");
        }

        if (!hasCorrectListField) {
            log.error("❌ Field 'private List<Orders> orders' is missing or incorrect!");
        }

        System.out.println("---- Customers.java (OneToMany) ----");
        System.out.println(content);
        System.out.println("-------------------------------");


        assertTrue(hasOneToMany, "⚠️ Δεν βρέθηκε η σχέση OneToMany");
        assertTrue(hasCorrectMappedBy, "⚠️ Το mappedBy δεν είναι σωστό");
        assertTrue(hasCorrectListField, "⚠️ Το όνομα της λίστας δεν είναι σωστό");
    }

    @Test
    void testGenerateDepartmentEntity_WithUuidPk_AndFields() throws IOException {
        log.info("🟢 Running testGenerateDepartmentEntity_WithUuidPk_AndFields...");

        String packageName = "com.example.entities";

        Table table = new Table();
        table.setName("department");

        Column departmentUuid = new Column();
        departmentUuid.setName("department_uuid");
        departmentUuid.setSqlType("UUID");
        departmentUuid.setJavaType("java.util.UUID");
        departmentUuid.setPrimaryKey(true);
        departmentUuid.setNullable(false);

        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setSqlType("SERIAL");
        departmentId.setJavaType("Integer");
        departmentId.setPrimaryKey(false);
        departmentId.setNullable(true);

        Column name = new Column();
        name.setName("name");
        name.setSqlType("VARCHAR(100)");
        name.setJavaType("String");
        name.setNullable(false);

        Column description = new Column();
        description.setName("description");
        description.setSqlType("TEXT");
        description.setJavaType("String");
        description.setNullable(true);

        Column parentDeptId = new Column();
        parentDeptId.setName("parent_dept_id");
        parentDeptId.setSqlType("INT");
        parentDeptId.setJavaType("Integer");
        parentDeptId.setNullable(true);

        Column date = new Column();
        date.setName("date");
        date.setSqlType("DATE");
        date.setJavaType("java.time.LocalDate");
        date.setNullable(true);

        Column createdAt = new Column();
        createdAt.setName("created_at");
        createdAt.setSqlType("TIMESTAMP");
        createdAt.setJavaType("java.time.LocalDateTime");
        createdAt.setNullable(true);
        createdAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column updatedAt = new Column();
        updatedAt.setName("updated_at");
        updatedAt.setSqlType("TIMESTAMP");
        updatedAt.setJavaType("java.time.LocalDateTime");
        updatedAt.setNullable(true);
        updatedAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column isActive = new Column();
        isActive.setName("is_active");
        isActive.setSqlType("BOOLEAN");
        isActive.setJavaType("Boolean");
        isActive.setNullable(false);
        isActive.setDefaultValue("TRUE");

        Column budget = new Column();
        budget.setName("budget");
        budget.setSqlType("NUMERIC(12,2)");
        budget.setJavaType("java.math.BigDecimal");
        budget.setNullable(true);

        Column headcount = new Column();
        headcount.setName("headcount");
        headcount.setSqlType("SMALLINT");
        headcount.setJavaType("Short");
        headcount.setNullable(true);

        Column phone = new Column();
        phone.setName("phone");
        phone.setSqlType("VARCHAR(20)");
        phone.setJavaType("String");
        phone.setNullable(true);

        Column websiteUrl = new Column();
        websiteUrl.setName("website_url");
        websiteUrl.setSqlType("TEXT");
        websiteUrl.setJavaType("String");
        websiteUrl.setNullable(true);

        Column attachment = new Column();
        attachment.setName("attachment");
        attachment.setSqlType("BYTEA");
        attachment.setJavaType("byte[]");
        attachment.setNullable(true);

        Column shiftStart = new Column();
        shiftStart.setName("shift_start");
        shiftStart.setSqlType("TIME");
        shiftStart.setJavaType("java.time.LocalTime");
        shiftStart.setNullable(true);

        table.setColumns(List.of(
                departmentUuid,
                departmentId,
                name,
                description,
                parentDeptId,
                date,
                createdAt,
                updatedAt,
                isActive,
                budget,
                headcount,
                phone,
                websiteUrl,
                attachment,
                shiftStart
        ));

        entityGenerator.generate(List.of(table), tempDir.toString(), packageName, true, false);

        // Expected output path: <tempDir>/com/example/entities/Department.java
        Path expectedFile = tempDir
                .resolve(packageName.replace('.', java.io.File.separatorChar))
                .resolve("Department.java");

        // More robust: search for Department.java anywhere under tempDir
        Path generatedFile;
        try (java.util.stream.Stream<Path> walk = Files.walk(tempDir)) {
            generatedFile = walk
                    .filter(p -> p.getFileName().toString().equals("Department.java"))
                    .findFirst()
                    .orElse(expectedFile);
        }

        if (!Files.exists(generatedFile)) {
            // Dump generated files for debugging
            try (java.util.stream.Stream<Path> walk = Files.walk(tempDir)) {
                String filesDump = walk
                        .filter(Files::isRegularFile)
                        .map(p -> tempDir.relativize(p).toString())
                        .sorted()
                        .reduce("", (a, b) -> a + "\n" + b);
                log.error("❌ Department.java was not generated. Files under tempDir:{}", filesDump);
            }
        }

        assertTrue(Files.exists(generatedFile),
                "Department.java should be generated under the package folder. Expected: " + expectedFile);

        String content = Files.readString(generatedFile);
        log.debug("Generated Department.java:\n{}", content);

        // Minimal sanity checks (expand as you like)
        assertTrue(content.contains("public class Department"), "Class name not generated correctly");
        assertTrue(content.contains("@Entity"), "@Entity annotation missing");
        assertTrue(content.contains("private UUID departmentUuid"), "UUID PK field not generated correctly");

        // Your generator rules
        assertFalse(content.contains("@Builder.Default"), "Generator must not add @Builder.Default");
        assertFalse(content.contains("optional = false"), "Generator must not add optional=false");
        assertFalse(content.contains("referencedColumnName"), "Generator must not add referencedColumnName=\"id\"");
    }




    @Test
    void testNoDuplicateRelationshipFields() throws IOException {
        log.info("🟢 Running testNoDuplicateRelationshipFields...");

        Table department = new Table();
        department.setName("Department");

        Column id = new Column();
        id.setName("id");
        id.setSqlType("INT");
        id.setJavaType("Long");
        id.setPrimaryKey(true);
        department.setColumns(new ArrayList<>(List.of(id)));

        Column parentId = new Column();
        parentId.setName("parent_id");
        parentId.setSqlType("INT");
        parentId.setJavaType("Long");
        parentId.setForeignKey(true);
        parentId.setReferencedTable("Department");
        parentId.setReferencedColumn("id");
        department.getColumns().add(parentId);

        // Δημιουργούμε το map που περιμένει ο RelationshipResolver
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Department", department);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        EntityGenerator generator = new EntityGenerator();
        String content = generator.createEntityContent(department, "com.example.entities", false);

        System.out.println("---- Department.java ----");
        System.out.println(content);
        System.out.println("-------------------------");

        // ✅ Πρέπει να υπάρχει ΜΟΝΟ ΜΙΑ private Department parent σχέση
        long fieldCount = content.lines()
                .filter(line -> line.contains("private Department parent"))
                .count();

        assertEquals(1, fieldCount, "❌ Πρέπει να υπάρχει μόνο ένα πεδίο 'parent' για self-referencing σχέση.");
    }
}
