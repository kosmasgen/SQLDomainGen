package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class RelationshipResolver {

    /**
     * Resolves relationships for a given table by analyzing its columns.
     *
     * @param sourceTable the table to analyze
     * @return a list of resolved relationships
     */
    public List<Relationship> resolveRelationships(Table sourceTable, Map<String, Table> allTables) {
        log.info("Resolving relationships for table: {}", sourceTable.getName());
        List<Relationship> resolvedRelationships = new ArrayList<>();

        for (Column column : sourceTable.getColumns()) {
            log.info("Processing column: {}", column);
            if (validateColumnForRelationship(column)) {
                Relationship relationship = createRelationship(column, sourceTable, allTables);
                if (relationship != null) {
                    resolvedRelationships.add(relationship);
                    log.info("Added relationship: {}", relationship);
                }
            } else {
                log.warn("Column does not define a valid relationship: {}", column);
            }
        }

        log.info("Finished resolving relationships: {}", resolvedRelationships);
        return resolvedRelationships;
    }

    public boolean validateColumnForRelationship(Column column) {
        return Boolean.TRUE.equals(column.isForeignKey()) && column.getRelationshipType() != null;
    }


    public Relationship createRelationship(Column column, Table sourceTable, Map<String, Table> allTables) {
        Relationship relationship = new Relationship();
        relationship.setSourceTable(sourceTable.getName());
        relationship.setSourceColumn(column.getName());

        // Έλεγχος αν η στήλη είναι foreign key
        if (!column.isForeignKey()) {
            log.warn("Column '{}' is not marked as a foreign key. Skipping relationship creation.", column.getName());
            return null; // Η στήλη δεν είναι foreign key
        }

        Table targetTable = findTargetTable(column.getReferencedTable(), allTables);
        if (targetTable == null) {
            log.warn("Target table '{}' not found for column '{}'", column.getReferencedTable(), column.getName());
            return null;
        }

        relationship.setTargetTable(targetTable.getName());
        relationship.setRelationshipType(mapToRelationshipType(column.getRelationshipType()));

        // Διαχείριση Many-to-Many σχέσεων
        if ("MANYTOMANY".equalsIgnoreCase(column.getRelationshipType())) {
            resolveManyToManyDetails(column, relationship);
        }

        return relationship;
    }


    private Table findTargetTable(String targetTableName, Map<String, Table> allTables) {
        if (targetTableName == null) {
            log.error("Target table name is null.");
            return null;
        }
        if (allTables == null) {
            log.error("All tables map is null.");
            return null;
        }
        if (!allTables.containsKey(targetTableName)) {
            log.error("Target table '{}' not found in the provided tables map. Available tables: {}",
                    targetTableName, allTables.keySet());
            return null;
        }
        log.debug("Target table '{}' found.", targetTableName);
        return allTables.get(targetTableName);
    }



    public void resolveManyToManyDetails(Column column, Relationship relationship) {
        relationship.setJoinTableName(column.getJoinTableName());
        relationship.setInverseJoinColumn(column.getInverseJoinColumn());
    }

    /**
     * Maps a string to a RelationshipType.
     *
     * @param type the string representation of the relationship type
     * @return the corresponding RelationshipType
     * @throws IllegalArgumentException if the type is invalid
     */
    public Relationship.RelationshipType mapToRelationshipType(String type) {
        try {
            return Relationship.RelationshipType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid relationship type: " + type, e);
        }
    }

}
