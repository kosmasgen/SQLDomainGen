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
    void testGenerateEntityWithUUIDPrimaryKey() throws IOException {
        log.info("🟢 Running testGenerateEntityWithUUIDPrimaryKey...");

        Table logTable = new Table();
        logTable.setName("AuditLog");

        Column logId = new Column();
        logId.setName("log_id");
        logId.setSqlType("UUID");
        logId.setJavaType("UUID"); // πρέπει να γίνει mapping στο generator σε java.util.UUID
        logId.setPrimaryKey(true);
        logId.setNullable(false);

        logTable.setColumns(List.of(logId));

        entityGenerator.generate(List.of(logTable), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("AuditLog.java");
        assertTrue(Files.exists(generatedFile), "✅ AuditLog.java should be generated");

        String content = Files.readString(generatedFile);
        log.debug("📄 Generated content: \n{}", content);

        System.out.println("---- AuditLog.java ----");
        System.out.println(content);
        System.out.println("------------------------");

        assertTrue(content.contains("private UUID logId"), "⚠️ Το UUID πεδίο δεν εμφανίζεται σωστά");
        assertTrue(content.contains("@Id"), "⚠️ Δεν βρέθηκε το annotation @Id");
        assertTrue(content.contains("@Column(name = \"log_id\", nullable = false)"), "⚠️ Το @Column annotation δεν είναι σωστό");
        assertTrue(content.contains("import java.util.UUID;"), "⚠️ Το import για UUID λείπει");
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
