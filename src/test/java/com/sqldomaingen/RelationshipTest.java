package com.sqldomaingen;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Relationship.RelationshipType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.jupiter.api.Assertions.*;

class RelationshipTest {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipTest.class);

    @Test
    void testRelationshipSettersAndGetters() {
        logger.info("🔵 Running test: testRelationshipSettersAndGetters");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("orders");
        relationship.setSourceColumn("customer_id");
        relationship.setTargetTable("customers");
        relationship.setTargetColumn("id");
        relationship.setRelationshipType(RelationshipType.MANYTOONE);
        relationship.setOnUpdate("CASCADE");
        relationship.setOnDelete("SET NULL");
        relationship.setJoinTableName("order_customers");
        relationship.setInverseJoinColumn("customer_id");

        logger.info("✅ Relationship initialized: {}", relationship);

        assertEquals("orders", relationship.getSourceTable());
        assertEquals("customer_id", relationship.getSourceColumn());
        assertEquals("customers", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(RelationshipType.MANYTOONE, relationship.getRelationshipType());
        assertEquals("CASCADE", relationship.getOnUpdate());
        assertEquals("SET NULL", relationship.getOnDelete());
        assertEquals("order_customers", relationship.getJoinTableName());
        assertEquals("customer_id", relationship.getInverseJoinColumn());
    }



    @Test
    void testRelationshipToString() {
        logger.info("🔵 Running test: testRelationshipToString");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("orders");
        relationship.setSourceColumn("customer_id");
        relationship.setTargetTable("customers");
        relationship.setTargetColumn("id");
        relationship.setRelationshipType(Relationship.RelationshipType.ONETOMANY);
        relationship.setOnUpdate("RESTRICT");
        relationship.setOnDelete("CASCADE");

        // Ανανέωση του expected για να ταιριάζει με το πραγματικό αποτέλεσμα (null αντί για κενό)
        String expected = "Relationship(sourceColumn=customer_id, targetColumn=id, sourceTable=orders, " +
                "targetTable=customers, onUpdate=RESTRICT, onDelete=CASCADE, joinTableName=null, inverseJoinColumn=null, " +
                "relationshipType=ONETOMANY)";

        logger.info("✅ Expected: {}", expected);
        logger.info("✅ Actual: {}", relationship.toString());

        assertEquals(expected, relationship.toString(), "Relationship toString should match the expected format.");
    }



    @Test
    void testDefaultValues() {
        logger.info("🔵 Running test: testDefaultValues");

        Relationship relationship = new Relationship();

        logger.info("🔍 Default Relationship object: {}", relationship);

        assertNull(relationship.getSourceTable(), "Source table should be null.");
        assertNull(relationship.getSourceColumn(), "Source column should be null.");
        assertNull(relationship.getTargetTable(), "Target table should be null.");
        assertNull(relationship.getTargetColumn(), "Target column should be null.");
        assertNull(relationship.getRelationshipType(), "Relationship type should be null.");
        assertNull(relationship.getOnUpdate(), "OnUpdate action should be null.");
        assertNull(relationship.getOnDelete(), "OnDelete action should be null.");


    }

    @Test
    void testOneToOneRelationship() {
        logger.info("🔵 Running test: testOneToOneRelationship");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("users");
        relationship.setSourceColumn("id");
        relationship.setTargetTable("profiles");
        relationship.setTargetColumn("user_id");
        relationship.setRelationshipType(RelationshipType.ONETOONE);
        relationship.setOnUpdate("CASCADE");
        relationship.setOnDelete("SET NULL");

        logger.info("✅ Created Relationship: {}", relationship);

        assertEquals("users", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("profiles", relationship.getTargetTable());
        assertEquals("user_id", relationship.getTargetColumn());
        assertEquals(RelationshipType.ONETOONE, relationship.getRelationshipType());
        assertEquals("CASCADE", relationship.getOnUpdate());
        assertEquals("SET NULL", relationship.getOnDelete());
    }

    @Test
    void testManyToOneRelationship() {
        logger.info("🔵 Running test: testManyToOneRelationship");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("orders");
        relationship.setSourceColumn("customer_id");
        relationship.setTargetTable("customers");
        relationship.setTargetColumn("id");
        relationship.setRelationshipType(RelationshipType.MANYTOONE);
        relationship.setOnUpdate("RESTRICT");
        relationship.setOnDelete("CASCADE");

        logger.info("✅ Created Relationship: {}", relationship);

        assertEquals("orders", relationship.getSourceTable());
        assertEquals("customer_id", relationship.getSourceColumn());
        assertEquals("customers", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(RelationshipType.MANYTOONE, relationship.getRelationshipType());
        assertEquals("RESTRICT", relationship.getOnUpdate());
        assertEquals("CASCADE", relationship.getOnDelete());
    }

    @Test
    void testOneToManyRelationship() {
        logger.info("🔵 Running test: testOneToManyRelationship");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("customers");
        relationship.setSourceColumn("id");
        relationship.setTargetTable("orders");
        relationship.setTargetColumn("customer_id");
        relationship.setRelationshipType(RelationshipType.ONETOMANY);
        relationship.setOnUpdate("NO ACTION");
        relationship.setOnDelete("SET DEFAULT");

        logger.info("✅ Created Relationship: {}", relationship);

        assertEquals("customers", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("orders", relationship.getTargetTable());
        assertEquals("customer_id", relationship.getTargetColumn());
        assertEquals(RelationshipType.ONETOMANY, relationship.getRelationshipType());
        assertEquals("NO ACTION", relationship.getOnUpdate());
        assertEquals("SET DEFAULT", relationship.getOnDelete());
    }

    @Test
    void testManyToManyRelationship() {
        logger.info("🔵 Running test: testManyToManyRelationship");

        Relationship relationship = new Relationship();
        relationship.setSourceTable("students");
        relationship.setSourceColumn("id");
        relationship.setTargetTable("courses");
        relationship.setTargetColumn("id");
        relationship.setRelationshipType(RelationshipType.MANYTOMANY);
        relationship.setJoinTableName("student_courses");
        relationship.setInverseJoinColumn("course_id");
        relationship.setOnUpdate("CASCADE");
        relationship.setOnDelete("CASCADE");

        logger.info("✅ Created Relationship: {}", relationship);

        assertEquals("students", relationship.getSourceTable());
        assertEquals("id", relationship.getSourceColumn());
        assertEquals("courses", relationship.getTargetTable());
        assertEquals("id", relationship.getTargetColumn());
        assertEquals(RelationshipType.MANYTOMANY, relationship.getRelationshipType());
        assertEquals("student_courses", relationship.getJoinTableName());
        assertEquals("course_id", relationship.getInverseJoinColumn());
        assertEquals("CASCADE", relationship.getOnUpdate());
        assertEquals("CASCADE", relationship.getOnDelete());
    }




}
