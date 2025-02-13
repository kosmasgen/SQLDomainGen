package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityGeneratorTest.class);

    private EntityGenerator entityGenerator;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        entityGenerator = new EntityGenerator();
        logger.info("🔧 Setting up EntityGeneratorTest...");
    }

    @Test
    void testGenerateEntityWithManyToOne() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithManyToOne...");

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

        // ✅ Δε χρειάζεται να δημιουργήσουμε τη σχέση χειροκίνητα
        entityGenerator.generate(List.of(orders, customers), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("Orders.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        assertTrue(content.contains("@ManyToOne"), "⚠️ Δεν βρέθηκε η σχέση ManyToOne");
        assertTrue(content.contains("@JoinColumn(name = \"customer_id\", referencedColumnName = \"id\")"), "⚠️ Το JoinColumn δεν δημιουργήθηκε σωστά");
    }

    @Test
    void testGenerateEntityWithManyToMany() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithManyToMany...");

        Table orderProducts = new Table();
        orderProducts.setName(NamingConverter.toPascalCase("order_products"));
        orderProducts.setColumns(new ArrayList<>());

        Table orders = new Table();
        orders.setName(NamingConverter.toPascalCase("orders"));
        orders.setColumns(new ArrayList<>());

        Table products = new Table();
        products.setName(NamingConverter.toPascalCase("products"));
        products.setColumns(new ArrayList<>());

        // ✅ Προσθέτουμε primary key στους Orders
        Column ordersId = new Column();
        ordersId.setName("id");
        ordersId.setSqlType("INT");
        ordersId.setJavaType("Long");
        ordersId.setPrimaryKey(true);
        ordersId.setNullable(false);
        orders.getColumns().add(ordersId);

        // ✅ Προσθέτουμε primary key στους Products
        Column productsId = new Column();
        productsId.setName("id");
        productsId.setSqlType("INT");
        productsId.setJavaType("Long");
        productsId.setPrimaryKey(true);
        productsId.setNullable(false);
        products.getColumns().add(productsId);

        // ✅ Προσθέτουμε foreign keys στο OrderProducts
        Column orderId = new Column();
        orderId.setName("order_id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);
        orderId.setForeignKey(true);
        orderId.setReferencedTable(orders.getName());
        orderId.setReferencedColumn("id");
        orderProducts.getColumns().add(orderId);

        Column productId = new Column();
        productId.setName("product_id");
        productId.setSqlType("INT");
        productId.setJavaType("Long");
        productId.setPrimaryKey(true);
        productId.setForeignKey(true);
        productId.setReferencedTable(products.getName());
        productId.setReferencedColumn("id");
        orderProducts.getColumns().add(productId);

        // ✅ Δε δημιουργούμε χειροκίνητα το Relationship
        entityGenerator.generate(List.of(orderProducts, orders, products), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve(orderProducts.getName() + ".java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        // ✅ Ελέγχουμε για τις αναμενόμενες annotations
        assertTrue(content.contains("@ManyToMany"), "⚠️ Δεν βρέθηκε η σχέση ManyToMany");
        assertTrue(content.contains("@JoinTable"), "⚠️ Το JoinTable δεν δημιουργήθηκε σωστά");
        assertTrue(content.contains("@JoinColumn(name = \"order_id\""), "⚠️ Το JoinColumn για την παραγγελία δεν είναι σωστό");
        assertTrue(content.contains("inverseJoinColumns = @JoinColumn(name = \"product_id\""), "⚠️ Το inverseJoinColumn για το προϊόν δεν είναι σωστό");
    }



    @Test
    void testGenerateEntityWithOneToOne() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithOneToOne...");

        Table users = new Table();
        users.setName("Users");

        Table userDetails = new Table();
        userDetails.setName("UserDetails");

        // ✅ Προσθήκη Primary Key στους Users
        Column userId = new Column();
        userId.setName("id");
        userId.setSqlType("INT");
        userId.setJavaType("Long");
        userId.setPrimaryKey(true);
        users.setColumns(new ArrayList<>(List.of(userId)));

        // ✅ Προσθήκη Primary Key στους UserDetails
        Column detailsId = new Column();
        detailsId.setName("id");
        detailsId.setSqlType("INT");
        detailsId.setJavaType("Long");
        detailsId.setPrimaryKey(true);
        userDetails.setColumns(new ArrayList<>(List.of(detailsId)));

        // ✅ Προσθήκη Foreign Key στους UserDetails (One-to-One)
        Column userIdFk = new Column();
        userIdFk.setName("user_id");
        userIdFk.setSqlType("INT");
        userIdFk.setJavaType("Long");
        userIdFk.setPrimaryKey(false);
        userIdFk.setForeignKey(true);
        userIdFk.setNullable(false); // Υποχρεωτικό
        userIdFk.setUnique(true);    // 🚩 Απαραίτητο για OneToOne
        userIdFk.setReferencedTable("Users");
        userIdFk.setReferencedColumn("id");
        userDetails.getColumns().add(userIdFk);

        // ✅ Δε χρειάζεται πλέον να ορίζουμε χειροκίνητα τη σχέση
        entityGenerator.generate(List.of(users, userDetails), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("UserDetails.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        // ✅ Ελέγχουμε για τις αναμενόμενες annotations
        assertTrue(content.contains("@OneToOne"), "⚠️ Δεν βρέθηκε η σχέση OneToOne");
        assertTrue(content.contains("@JoinColumn(name = \"user_id\", referencedColumnName = \"id\")"), "⚠️ Το JoinColumn δεν δημιουργήθηκε σωστά");
        assertTrue(content.contains("unique = true"), "⚠️ Το unique constraint δεν προστέθηκε στο foreign key για το OneToOne");
    }

    @Test
    void testGenerateEntityWithOneToMany() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithOneToMany...");

        Table customers = new Table();
        customers.setName("Customers");

        Table orders = new Table();
        orders.setName("Orders");

        // ✅ Προσθήκη Primary Key στους Customers
        Column customerId = new Column();
        customerId.setName("id");
        customerId.setSqlType("INT");
        customerId.setJavaType("Long");
        customerId.setPrimaryKey(true);
        customers.setColumns(new ArrayList<>(List.of(customerId)));

        // ✅ Προσθήκη Primary Key στους Orders
        Column orderId = new Column();
        orderId.setName("id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);

        // ✅ Προσθήκη Foreign Key στους Orders (Many-to-One)
        Column customerIdFk = new Column();
        customerIdFk.setName("customer_id");
        customerIdFk.setSqlType("INT");
        customerIdFk.setJavaType("Long");
        customerIdFk.setPrimaryKey(false);
        customerIdFk.setForeignKey(true);
        customerIdFk.setNullable(false); // Υποχρεωτικό
        customerIdFk.setReferencedTable("Customers");
        customerIdFk.setReferencedColumn("id");

        orders.setColumns(new ArrayList<>(List.of(orderId, customerIdFk)));

        // ✅ Δε χρειάζεται πλέον να ορίζουμε χειροκίνητα τη σχέση
        entityGenerator.generate(List.of(customers, orders), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("Customers.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        // ✅ Ελέγχουμε για τις αναμενόμενες annotations
        assertTrue(content.contains("@OneToMany"), "⚠️ Δεν βρέθηκε η σχέση OneToMany");
        assertTrue(content.contains("mappedBy = \"customer\"") || content.contains("mappedBy = \"customerId\""),
                "⚠️ Το mappedBy δεν δημιουργήθηκε σωστά");
    }

}
