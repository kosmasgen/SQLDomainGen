package com.sqldomaingen;

import com.sqldomaingen.generator.RelationshipResolver;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipResolverTest {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipResolverTest.class);

    @Test
    void testResolveRelationships() {
        logger.info("Starting test for resolveRelationships");

        // Δημιουργία πινάκων
        Table sourceTable = new Table();
        sourceTable.setName("SourceTable");

        Table targetTableOneToOne = new Table();
        targetTableOneToOne.setName("TargetTableOneToOne");

        Table targetTableOneToMany = new Table();
        targetTableOneToMany.setName("TargetTableOneToMany");

        Table targetTableManyToOne = new Table();
        targetTableManyToOne.setName("TargetTableManyToOne");

        Table targetTableManyToMany = new Table();
        targetTableManyToMany.setName("TargetTableManyToMany");

        // Δημιουργία χάρτη με όλους τους πίνακες
        Map<String, Table> allTables = new HashMap<>();
        allTables.put("SourceTable", sourceTable);
        allTables.put("TargetTableOneToOne", targetTableOneToOne);
        allTables.put("TargetTableOneToMany", targetTableOneToMany);
        allTables.put("TargetTableManyToOne", targetTableManyToOne);
        allTables.put("TargetTableManyToMany", targetTableManyToMany);

        // Δημιουργία στηλών στον source πίνακα
        Column oneToOneColumn = new Column();
        oneToOneColumn.setName("one_to_one_id");
        oneToOneColumn.setForeignKey(true);
        oneToOneColumn.setReferencedTable("TargetTableOneToOne");
        oneToOneColumn.setRelationshipType("ONETOONE");

        Column oneToManyColumn = new Column();
        oneToManyColumn.setName("one_to_many_id");
        oneToManyColumn.setForeignKey(true);
        oneToManyColumn.setReferencedTable("TargetTableOneToMany");
        oneToManyColumn.setRelationshipType("ONETOMANY");

        Column manyToOneColumn = new Column();
        manyToOneColumn.setName("many_to_one_id");
        manyToOneColumn.setForeignKey(true);
        manyToOneColumn.setReferencedTable("TargetTableManyToOne");
        manyToOneColumn.setRelationshipType("MANYTOONE");

        Column manyToManyColumn = new Column();
        manyToManyColumn.setName("many_to_many_id");
        manyToManyColumn.setForeignKey(true);
        manyToManyColumn.setReferencedTable("TargetTableManyToMany");
        manyToManyColumn.setRelationshipType("MANYTOMANY");
        manyToManyColumn.setJoinTableName("JoinTableManyToMany");
        manyToManyColumn.setInverseJoinColumn("target_id");

        sourceTable.setColumns(Arrays.asList(oneToOneColumn, oneToManyColumn, manyToOneColumn, manyToManyColumn));

        // Εκτέλεση του RelationshipResolver
        RelationshipResolver resolver = new RelationshipResolver();
        List<Relationship> relationships = resolver.resolveRelationships(sourceTable, allTables);

        logger.info("Resolved relationships: {}", relationships);

        // Έλεγχος των σχέσεων
        assertEquals(4, relationships.size(), "Should have 4 relationships");

        // Έλεγχος της σχέσης OneToOne
        Relationship oneToOne = relationships.get(0);
        assertEquals("SourceTable", oneToOne.getSourceTable());
        assertEquals("one_to_one_id", oneToOne.getSourceColumn());
        assertEquals("TargetTableOneToOne", oneToOne.getTargetTable());
        assertEquals(Relationship.RelationshipType.ONETOONE, oneToOne.getRelationshipType());
        logger.info("Verified OneToOne relationship: {}", oneToOne);

        // Έλεγχος της σχέσης OneToMany
        Relationship oneToMany = relationships.get(1);
        assertEquals("SourceTable", oneToMany.getSourceTable());
        assertEquals("one_to_many_id", oneToMany.getSourceColumn());
        assertEquals("TargetTableOneToMany", oneToMany.getTargetTable());
        assertEquals(Relationship.RelationshipType.ONETOMANY, oneToMany.getRelationshipType());
        logger.info("Verified OneToMany relationship: {}", oneToMany);

        // Έλεγχος της σχέσης ManyToOne
        Relationship manyToOne = relationships.get(2);
        assertEquals("SourceTable", manyToOne.getSourceTable());
        assertEquals("many_to_one_id", manyToOne.getSourceColumn());
        assertEquals("TargetTableManyToOne", manyToOne.getTargetTable());
        assertEquals(Relationship.RelationshipType.MANYTOONE, manyToOne.getRelationshipType());
        logger.info("Verified ManyToOne relationship: {}", manyToOne);

        // Έλεγχος της σχέσης ManyToMany
        Relationship manyToMany = relationships.get(3);
        assertEquals("SourceTable", manyToMany.getSourceTable());
        assertEquals("many_to_many_id", manyToMany.getSourceColumn());
        assertEquals("TargetTableManyToMany", manyToMany.getTargetTable());
        assertEquals(Relationship.RelationshipType.MANYTOMANY, manyToMany.getRelationshipType());
        assertEquals("JoinTableManyToMany", manyToMany.getJoinTableName());
        assertEquals("target_id", manyToMany.getInverseJoinColumn());
        logger.info("Verified ManyToMany relationship: {}", manyToMany);
    }
}
