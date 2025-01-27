package com.sqldomaingen;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Relationship.RelationshipType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RelationshipTest {

    @Test
    void testRelationshipSettersAndGetters() {
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

        assertEquals("orders", relationship.getSourceTable(), "Source table should be 'orders'.");
        assertEquals("customer_id", relationship.getSourceColumn(), "Source column should be 'customer_id'.");
        assertEquals("customers", relationship.getTargetTable(), "Target table should be 'customers'.");
        assertEquals("id", relationship.getTargetColumn(), "Target column should be 'id'.");
        assertEquals(RelationshipType.MANYTOONE, relationship.getRelationshipType(), "Relationship type should be MANYTOONE.");
        assertEquals("CASCADE", relationship.getOnUpdate(), "OnUpdate action should be 'CASCADE'.");
        assertEquals("SET NULL", relationship.getOnDelete(), "OnDelete action should be 'SET NULL'.");
        assertEquals("order_customers", relationship.getJoinTableName(), "Join table name should be 'order_customers'.");
        assertEquals("customer_id", relationship.getInverseJoinColumn(), "Inverse join column should be 'customer_id'.");
    }

    @Test
    void testRelationshipToString() {
        Relationship relationship = new Relationship();
        relationship.setSourceTable("orders");
        relationship.setSourceColumn("customer_id");
        relationship.setTargetTable("customers");
        relationship.setTargetColumn("id");
        relationship.setRelationshipType(RelationshipType.ONETOMANY);
        relationship.setOnUpdate("RESTRICT");
        relationship.setOnDelete("CASCADE");

        String expected = "Relationship{sourceTable='orders', sourceColumn='customer_id', targetTable='customers', " +
                "targetColumn='id', relationshipType='ONETOMANY', onUpdate='RESTRICT', onDelete='CASCADE'}";
        assertEquals(expected, relationship.toString(), "Relationship toString should match the expected format.");
    }

    @Test
    void testDefaultValues() {
        Relationship relationship = new Relationship();

        assertNull(relationship.getSourceTable(), "Default source table should be null.");
        assertNull(relationship.getSourceColumn(), "Default source column should be null.");
        assertNull(relationship.getTargetTable(), "Default target table should be null.");
        assertNull(relationship.getTargetColumn(), "Default target column should be null.");
        assertNull(relationship.getRelationshipType(), "Default relationship type should be null.");
        assertNull(relationship.getOnUpdate(), "Default onUpdate should be null.");
        assertNull(relationship.getOnDelete(), "Default onDelete should be null.");
        assertNull(relationship.getJoinTableName(), "Default join table name should be null.");
        assertNull(relationship.getInverseJoinColumn(), "Default inverse join column should be null.");
    }
}
