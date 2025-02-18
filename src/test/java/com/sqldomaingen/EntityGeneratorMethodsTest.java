package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.generator.RelationshipResolver;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.sqldomaingen.model.Relationship.RelationshipType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntityGeneratorMethodsTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityGeneratorMethodsTest.class);

    @Test
    void testCreateEntityContent() {
        logger.info("🟢 Running testCreateEntityContent...");

        // Δημιουργία πινάκων
        Table orders = new Table();
        orders.setName("Orders");

        Table products = new Table();
        products.setName("Products");

        Table orderProducts = new Table();
        orderProducts.setName("OrderProducts");

        // Δημιουργία στηλών για Orders
        Column ordersId = new Column();
        ordersId.setName("id");
        ordersId.setSqlType("BIGINT");
        ordersId.setJavaType("Long");
        ordersId.setPrimaryKey(true);

        orders.setColumns(new ArrayList<>(List.of(ordersId)));

        // Δημιουργία στηλών για Products
        Column productsId = new Column();
        productsId.setName("id");
        productsId.setSqlType("BIGINT");
        productsId.setJavaType("Long");
        productsId.setPrimaryKey(true);

        products.setColumns(new ArrayList<>(List.of(productsId)));

        Column id = new Column();
        id.setName("id");
        id.setSqlType("BIGINT");
        id.setJavaType("Long");
        id.setPrimaryKey(true);

        Column orderId = new Column();
        orderId.setName("order_id");
        orderId.setSqlType("BIGINT");
        orderId.setJavaType("Long");
        orderId.setForeignKey(true);
        orderId.setReferencedTable("Orders");
        orderId.setReferencedColumn("id");

        Column productId = new Column();
        productId.setName("product_id");
        productId.setSqlType("BIGINT");
        productId.setJavaType("Long");
        productId.setForeignKey(true);
        productId.setReferencedTable("Products");
        productId.setReferencedColumn("id");

        orderProducts.setColumns(new ArrayList<>(List.of(orderId, productId)));

        // Δημιουργία του table map για τον RelationshipResolver
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Orders", orders);
        tableMap.put("Products", products);
        tableMap.put("OrderProducts", orderProducts);

        // Ανάλυση των σχέσεων
        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();
        List<Relationship> relationships = resolver.getRelationships();

        logger.info("🔗 Resolved Relationships:");
        relationships.forEach(rel -> logger.info(rel.toString()));

        // Δημιουργία περιεχομένου για τα entities
        EntityGenerator generator = new EntityGenerator();

        String ordersContent = generator.createEntityContent(orders, "com.example", false);
        String productsContent = generator.createEntityContent(products, "com.example", false);
        String orderProductsContent = generator.createEntityContent(orderProducts, "com.example", false);

        logger.debug("📄 Generated content of Orders.java:\n{}", ordersContent);
        logger.debug("📄 Generated content of Products.java:\n{}", productsContent);
        logger.debug("📄 Generated content of OrderProducts.java:\n{}", orderProductsContent);

        System.out.println("📄 Generated content of Orders.java:");
        System.out.println(ordersContent);
        System.out.println("----------------------------------------------------");

        System.out.println("📄 Generated content of Products.java:");
        System.out.println(productsContent);
        System.out.println("----------------------------------------------------");

        System.out.println("📄 Generated content of OrderProducts.java:");
        System.out.println(orderProductsContent);
        System.out.println("----------------------------------------------------");

        assertNotNull(ordersContent);
        assertNotNull(productsContent);
        assertNotNull(orderProductsContent);
    }


    @Test
    void testGenerate() {
        logger.info("🟢 Running testGenerate...");

        // Δημιουργία πινάκων
        Table orders = new Table();
        orders.setName("Orders");

        Table products = new Table();
        products.setName("Products");

        Table orderProducts = new Table();
        orderProducts.setName("OrderProducts");

        // Δημιουργία στηλών
        Column orderId = new Column();
        orderId.setName("order_id");
        orderId.setSqlType("BIGINT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);
        orderId.setForeignKey(true);
        orderId.setReferencedTable("Orders");
        orderId.setReferencedColumn("id");

        Column productId = new Column();
        productId.setName("product_id");
        productId.setSqlType("BIGINT");
        productId.setJavaType("Long");
        productId.setPrimaryKey(true);
        productId.setForeignKey(true);
        productId.setReferencedTable("Products");
        productId.setReferencedColumn("id");

        orderProducts.setColumns(new ArrayList<>(List.of(orderId, productId)));

        // Δημιουργία του table map για τον RelationshipResolver
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Orders", orders);
        tableMap.put("Products", products);
        tableMap.put("OrderProducts", orderProducts);

        // Ανάλυση των σχέσεων
        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();
        List<Relationship> relationships = resolver.getRelationships();

        logger.info("🔗 Resolved Relationships:");
        relationships.forEach(rel -> logger.info(rel.toString()));

        // Δημιουργία entities
        EntityGenerator generator = new EntityGenerator();
        generator.generate(new ArrayList<>(tableMap.values()), "com.example", "./GeneratedEntities", false, false);

        logger.info("✅ testGenerate completed successfully!");
    }

    @Test
    void testAddRelationshipField_ManyToOne() {
        logger.info("🟢 Starting testAddRelationshipField_ManyToOne...");

        StringBuilder builder = new StringBuilder();
        Column column = new Column();
        column.setName("order_id");
        column.setSqlType("INT");
        column.setForeignKey(true);  // Σημαντικό!
        column.setPrimaryKey(false);
        column.setUnique(false);
        column.setDefaultValue(null);

        Table table = new Table();
        table.setName("OrderProducts");
        table.getColumns().add(column); //  ξεχνάς και αυτό

        Relationship relationship = new Relationship(
                "order_id", "id", "OrderProducts", "Orders",
                null, null, null, null, "order",
                RelationshipType.MANYTOONE
        );

        // ΣΩΣΤΟ: Βάλε το relationship μέσα στο ίδιο το table
        table.getRelationships().add(relationship);

        EntityGenerator generator = new EntityGenerator();
        generator.addRelationshipField(builder, column, table);

        String result = builder.toString();
        logger.debug("Generated field content:\n{}", result);

        System.out.println("Generated ManyToOne field content:\n" + result);
        assertTrue(result.contains("@ManyToOne"), "Expected @ManyToOne annotation");
        assertTrue(result.contains("private Orders order;"), "Expected private Orders order; field");

        logger.info("✅ testAddRelationshipField_ManyToOne completed successfully!");
    }



    @Test
    void testAddInverseRelationshipField_OneToMany() {
        logger.info("🟢 Starting testAddInverseRelationshipField_OneToMany...");

        StringBuilder builder = new StringBuilder();
        Relationship relationship = new Relationship("order_id", "id", "OrderProducts", "Orders", null, null, null, null, "orderProducts", RelationshipType.ONETOMANY);

        EntityGenerator generator = new EntityGenerator();
        generator.addInverseRelationshipField(builder, relationship);

        String result = builder.toString();
        System.out.println("Generated OneToMany inverse field content:\n" + result);

        logger.debug("Generated inverse relationship field content:\n{}", result);

        assertTrue(result.contains("@OneToMany"), "Expected @OneToMany annotation");
        assertTrue(result.contains("private List<OrderProducts> orderProducts = new ArrayList<>();"), "Expected private List<OrderProducts> orderProducts = new ArrayList<>(); field");

        logger.info("✅ testAddInverseRelationshipField_OneToMany completed successfully!");
    }


    @Test
    void testOneToOneRelationshipGeneration() {
        logger.info("🟢 Starting testOneToOneRelationshipGeneration...");

        // Δημιουργία των πινάκων
        Table ordersTable = new Table();
        ordersTable.setName("Orders");

        Table usersTable = new Table();
        usersTable.setName("Users");

        // Δημιουργία στηλών
        Column userIdColumn = new Column();
        userIdColumn.setName("user_id");
        userIdColumn.setSqlType("BIGINT");
        userIdColumn.setJavaType("Long");
        userIdColumn.setForeignKey(true);
        userIdColumn.setReferencedTable("Users");
        userIdColumn.setReferencedColumn("id");
        userIdColumn.setUnique(true); // ΣΗΜΑΝΤΙΚΟ για OneToOne

        Column userPrimaryKey = new Column();
        userPrimaryKey.setName("id");
        userPrimaryKey.setSqlType("BIGINT");
        userPrimaryKey.setJavaType("Long");
        userPrimaryKey.setPrimaryKey(true);

        ordersTable.getColumns().add(userIdColumn);
        usersTable.getColumns().add(userPrimaryKey);

        // Βάζουμε τους πίνακες στο map
        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Orders", ordersTable);
        tableMap.put("Users", usersTable);

        // Λύνουμε τις σχέσεις
        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        // Παίρνουμε τις σχέσεις
        List<Relationship> relationships = resolver.getRelationships();
        relationships.forEach(rel -> logger.info(rel.toString()));

        // Δημιουργία του EntityGenerator
        EntityGenerator generator = new EntityGenerator();

        String ordersContent = generator.createEntityContent(ordersTable, "com.example", false);
        String usersContent = generator.createEntityContent(usersTable, "com.example", false);

        logger.info("Generated Orders.java content:\n{}", ordersContent);
        logger.info("Generated Users.java content:\n{}", usersContent);

        System.out.println("Generated Orders.java content:\n" + ordersContent);
        System.out.println("Generated Users.java content:\n" + usersContent);

        assertTrue(ordersContent.contains("@OneToOne"), "Expected @OneToOne annotation in Orders");
        assertTrue(ordersContent.contains("@JoinColumn(name = \"user_id\""), "Expected @JoinColumn in Orders");

        assertTrue(usersContent.contains("@OneToOne"), "Expected @OneToOne annotation in Users");
        assertTrue(usersContent.contains("mappedBy = \"user\""), "Expected mappedBy in Users");

        logger.info("✅ testOneToOneRelationshipGeneration completed successfully!");
    }


    @Test
    void testManyToManyWithoutJoinTableGeneration() {
        Table students = new Table();
        students.setName("Students");

        Table courses = new Table();
        courses.setName("Courses");

        Column studentId = new Column();
        studentId.setName("id");
        studentId.setSqlType("BIGINT");
        studentId.setJavaType("Long");
        studentId.setPrimaryKey(true);

        Column courseId = new Column();
        courseId.setName("id");
        courseId.setSqlType("BIGINT");
        courseId.setJavaType("Long");
        courseId.setPrimaryKey(true);

        students.getColumns().add(studentId);
        courses.getColumns().add(courseId);

        // Δημιουργούμε τη ManyToMany σχέση από την πλευρά των Students
        Relationship relationship = new Relationship();
        relationship.setSourceTable("Students");
        relationship.setTargetTable("Courses");
        relationship.setSourceColumn("id");
        relationship.setTargetColumn("id");
        relationship.setJoinTableName("students_courses");
        relationship.setInverseJoinColumn("id");
        relationship.setRelationshipType(Relationship.RelationshipType.MANYTOMANY);

        students.getRelationships().add(relationship);

        // Αντίστροφη σχέση για Courses (inverse side)
        Relationship inverseRelationship = new Relationship();
        inverseRelationship.setSourceTable("Courses");
        inverseRelationship.setTargetTable("Students");
        inverseRelationship.setRelationshipType(Relationship.RelationshipType.MANYTOMANY);
        inverseRelationship.setMappedBy("courses");

        courses.getRelationships().add(inverseRelationship);

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Students", students);
        tableMap.put("Courses", courses);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();
        students.getRelationships().forEach(rel -> System.out.println("Students relationship -> " + rel));
        courses.getRelationships().forEach(rel -> System.out.println("Courses relationship -> " + rel));


        EntityGenerator generator = new EntityGenerator();
        String studentsContent = generator.createEntityContent(students, "com.example", false);
        String coursesContent = generator.createEntityContent(courses, "com.example", false);

        System.out.println("Students:\n" + studentsContent);
        System.out.println("Courses:\n" + coursesContent);

        assertTrue(studentsContent.contains("@ManyToMany"), "Expected @ManyToMany in Students");
        assertTrue(studentsContent.contains("@JoinTable"), "Expected @JoinTable in Students");
        assertTrue(coursesContent.contains("@ManyToMany"), "Expected @ManyToMany in Courses");
        assertTrue(coursesContent.contains("mappedBy"), "Expected mappedBy in Courses");
    }




    @Test
        void testManyToManyWithJoinTableGeneration() {
            logger.info("🟢 Running testManyToManyWithJoinTableGeneration...");

            // 👉 Δημιουργούμε πίνακα Orders
            Table orders = new Table();
            orders.setName("Orders");

            Column ordersId = new Column();
            ordersId.setName("id");
            ordersId.setSqlType("BIGINT");
            ordersId.setJavaType("Long");
            ordersId.setPrimaryKey(true);
            orders.addColumn(ordersId);

            // 👉 Δημιουργούμε πίνακα Products
            Table products = new Table();
            products.setName("Products");

            Column productsId = new Column();
            productsId.setName("id");
            productsId.setSqlType("BIGINT");
            productsId.setJavaType("Long");
            productsId.setPrimaryKey(true);
            products.addColumn(productsId);

            // 👉 Δημιουργούμε πίνακα OrderProducts (Join Table)
            Table orderProducts = new Table();
            orderProducts.setName("OrderProducts");

            Column orderId = new Column();
            orderId.setName("order_id");
            orderId.setSqlType("BIGINT");
            orderId.setJavaType("Long");
            orderId.setPrimaryKey(true);
            orderId.setForeignKey(true);
            orderId.setReferencedTable("Orders");
            orderId.setReferencedColumn("id");

            Column productId = new Column();
            productId.setName("product_id");
            productId.setSqlType("BIGINT");
            productId.setJavaType("Long");
            productId.setPrimaryKey(true);
            productId.setForeignKey(true);
            productId.setReferencedTable("Products");
            productId.setReferencedColumn("id");

            orderProducts.addColumn(orderId);
            orderProducts.addColumn(productId);

            // 👉 Φτιάχνουμε το tableMap
            Map<String, Table> tableMap = new HashMap<>();
            tableMap.put("Orders", orders);
            tableMap.put("Products", products);
            tableMap.put("OrderProducts", orderProducts);

            // 👉 RelationshipResolver
            RelationshipResolver resolver = new RelationshipResolver(tableMap);
            resolver.resolveRelationshipsForAllTables();

            // 👉 Φέρνουμε τις σχέσεις
            List<Relationship> relationships = resolver.getRelationships();
            relationships.forEach(rel -> logger.info(rel.toString()));

            // 👉 EntityGenerator
            EntityGenerator generator = new EntityGenerator();

            String ordersContent = generator.createEntityContent(orders, "com.example", false);
            String productsContent = generator.createEntityContent(products, "com.example", false);
            String orderProductsContent = generator.createEntityContent(orderProducts, "com.example", false);

            logger.debug("Orders entity content:\n{}", ordersContent);
            logger.debug("Products entity content:\n{}", productsContent);
            logger.debug("OrderProducts entity content:\n{}", orderProductsContent);

            // 👉 Assertions (Βασικοί Έλεγχοι)
            assertTrue(ordersContent.contains("@ManyToMany"), "Expected @ManyToMany in Orders");
            assertTrue(ordersContent.contains("private List<Products>"), "Expected List<Products> field in Orders");

            assertTrue(productsContent.contains("@ManyToMany"), "Expected @ManyToMany in Products");
            assertTrue(productsContent.contains("private List<Orders>"), "Expected List<Orders> field in Products");

            assertTrue(orderProductsContent.contains("@ManyToOne"), "Expected @ManyToOne in OrderProducts");
            assertTrue(orderProductsContent.contains("private Orders order"), "Expected private Orders order in OrderProducts");
            assertTrue(orderProductsContent.contains("private Products product"), "Expected private Products product in OrderProducts");

            logger.info("✅ testManyToManyWithJoinTableGeneration completed successfully!");
        }
}




