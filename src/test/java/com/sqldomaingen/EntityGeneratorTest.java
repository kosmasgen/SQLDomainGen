package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Relationship;
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

        // Προσθέτουμε το primary key στον πίνακα Customers
        Column customerId = new Column();
        customerId.setName("id");
        customerId.setSqlType("INT");
        customerId.setJavaType("Long");
        customerId.setPrimaryKey(true);
        customerId.setNullable(false);
        customerId.setForeignKey(false);
        customers.setColumns(new ArrayList<>(List.of(customerId)));

        // Προσθέτουμε το primary key στον πίνακα Orders
        Column orderId = new Column();
        orderId.setName("id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);
        orderId.setNullable(false);
        orderId.setForeignKey(false);

        // Προσθέτουμε τη foreign key στον πίνακα Orders
        Column customerIdColumn = new Column();
        customerIdColumn.setName("customer_id");
        customerIdColumn.setSqlType("INT");
        customerIdColumn.setJavaType("Long");
        customerIdColumn.setPrimaryKey(false);
        customerIdColumn.setNullable(false);
        customerIdColumn.setForeignKey(true);
        customerIdColumn.setReferencedTable("Customers");
        customerIdColumn.setReferencedColumn("id");

        orders.setColumns(new ArrayList<>(List.of(orderId, customerIdColumn)));

        Relationship relationship = new Relationship();
        relationship.setSourceColumn("customer_id");
        relationship.setTargetColumn("id");
        relationship.setSourceTable("Orders");
        relationship.setTargetTable("Customers");
        relationship.setRelationshipType(Relationship.RelationshipType.MANYTOONE);

        orders.setRelationships(List.of(relationship));

        entityGenerator.generate(List.of(orders, customers), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("Orders.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        assertTrue(content.contains("@ManyToOne"));
        assertTrue(content.contains("@JoinColumn(name = \"customer_id\", referencedColumnName = \"id\")"));
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

        // Προσθέτουμε primary key στους Orders
        Column ordersId = new Column();
        ordersId.setName("id");
        ordersId.setSqlType("INT");
        ordersId.setJavaType("Long");
        ordersId.setPrimaryKey(true);
        ordersId.setNullable(false);
        ordersId.setForeignKey(false);
        orders.getColumns().add(ordersId);

        // Προσθέτουμε primary key στους Products
        Column productsId = new Column();
        productsId.setName("id");
        productsId.setSqlType("INT");
        productsId.setJavaType("Long");
        productsId.setPrimaryKey(true);
        productsId.setNullable(false);
        productsId.setForeignKey(false);
        products.getColumns().add(productsId);

        // Προσθέτουμε foreign keys στο OrderProducts
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

        Relationship manyToMany = new Relationship();
        manyToMany.setSourceColumn("order_id");
        manyToMany.setTargetColumn("id");
        manyToMany.setSourceTable(orderProducts.getName());
        manyToMany.setTargetTable(orders.getName());
        manyToMany.setRelationshipType(Relationship.RelationshipType.MANYTOMANY);

        orderProducts.setRelationships(List.of(manyToMany));

        entityGenerator.generate(List.of(orderProducts, orders, products), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve(orderProducts.getName() + ".java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        assertTrue(content.contains("@ManyToMany"));
        assertTrue(content.contains("@JoinTable"));
    }

    @Test
    void testGenerateEntityWithOneToOne() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithOneToOne...");

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
        userIdFk.setNullable(false); // Είναι υποχρεωτικό
        userIdFk.setUnique(true);    // 🚩 Απαραίτητο για OneToOne
        userIdFk.setReferencedTable("Users");
        userIdFk.setReferencedColumn("id");
        userDetails.getColumns().add(userIdFk);

        Relationship oneToOne = new Relationship();
        oneToOne.setSourceColumn("user_id");
        oneToOne.setTargetColumn("id");
        oneToOne.setSourceTable("UserDetails");
        oneToOne.setTargetTable("Users");
        oneToOne.setRelationshipType(Relationship.RelationshipType.ONETOONE);

        userDetails.setRelationships(List.of(oneToOne));

        entityGenerator.generate(List.of(users, userDetails), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("UserDetails.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        assertTrue(content.contains("@OneToOne"), "⚠️ Δεν βρέθηκε η σχέση OneToOne");
        assertTrue(content.contains("@JoinColumn(name = \"user_id\", referencedColumnName = \"id\")"), "⚠️ Το JoinColumn δεν δημιουργήθηκε σωστά");
    }




    @Test
    void testGenerateEntityWithOneToMany() throws IOException {
        logger.info("🟢 Running testGenerateEntityWithOneToMany...");

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

        Relationship oneToMany = new Relationship();
        oneToMany.setSourceColumn("customer_id");
        oneToMany.setTargetColumn("id");
        oneToMany.setSourceTable("Orders");
        oneToMany.setTargetTable("Customers");
        oneToMany.setRelationshipType(Relationship.RelationshipType.ONETOMANY);

        customers.setRelationships(List.of(oneToMany));

        entityGenerator.generate(List.of(customers, orders), tempDir.toString(), "com.example.entities", true, false);

        Path generatedFile = tempDir.resolve("Customers.java");
        assertTrue(Files.exists(generatedFile), "✅ Generated entity file should exist");

        String content = Files.readString(generatedFile);
        logger.debug("📄 Generated content: \n{}", content);

        assertTrue(content.contains("@OneToMany"), "⚠️ Δεν βρέθηκε η σχέση OneToMany");
        assertTrue(content.contains("mappedBy = \"customer\""), "⚠️ Το mappedBy δεν δημιουργήθηκε σωστά");
    }



}
