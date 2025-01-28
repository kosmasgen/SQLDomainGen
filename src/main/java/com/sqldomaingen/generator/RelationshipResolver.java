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
        log.info("🔵 Resolving relationships for table: {}", sourceTable.getName());

        List<Relationship> resolvedRelationships = new ArrayList<>();

        for (Column column : sourceTable.getColumns()) {
            log.info("🔍 Processing column: {}", column);

            if (validateColumnForRelationship(column)) {
                log.info("✅ Column '{}' is a valid foreign key.", column.getName());
                log.info("🔍 Looking for referenced table '{}'", column.getReferencedTable());

                Relationship relationship = createRelationship(column, sourceTable, allTables);

                if (relationship != null) {
                    resolvedRelationships.add(relationship);
                    log.info("✅ Successfully created relationship: {}", relationship);
                } else {
                    log.warn("⚠️ Relationship could not be created for column: {}", column.getName());
                }
            } else {
                log.warn("⚠️ Column '{}' does not define a valid relationship.", column.getName());
            }
        }

        log.info("🏁 Finished resolving relationships for table '{}'. Total relationships found: {}",
                sourceTable.getName(), resolvedRelationships.size());

        return resolvedRelationships;
    }

    public boolean validateColumnForRelationship(Column column) {
        return Boolean.TRUE.equals(column.isForeignKey()) && column.getRelationshipType() != null;
    }


    public Relationship createRelationship(Column column, Table sourceTable, Map<String, Table> allTables) {
        log.info("Creating relationship for column: {}", column.getName());

        // Αν η στήλη δεν είναι foreign key, επιστρέφουμε null
        if (!Boolean.TRUE.equals(column.isForeignKey())) {
            log.warn("Column '{}' is not a foreign key. Skipping relationship creation.", column.getName());
            return null;
        }

        // Εύρεση του πίνακα-στόχου (target table)
        Table targetTable = findTargetTable(column.getReferencedTable(), allTables);
        if (targetTable == null) {
            log.warn("Target table '{}' not found for column '{}'", column.getReferencedTable(), column.getName());
            return null;
        }

        // Εύρεση της στήλης-στόχου στον target table
        Column targetColumn = findTargetColumn(targetTable, column.getReferencedColumn());
        if (targetColumn == null) {
            log.warn("Target column '{}' not found in table '{}'", column.getReferencedColumn(), targetTable.getName());
            return null;
        }

        // Δημιουργία της σχέσης
        Relationship relationship = new Relationship();
        relationship.setSourceTable(sourceTable.getName());
        relationship.setSourceColumn(column.getName());
        relationship.setTargetTable(targetTable.getName());
        relationship.setTargetColumn(targetColumn.getName());

        // 🔍 Logging για να δούμε τι τιμή έχει το relationshipType πριν γίνει το mapping
        log.debug("📌 Column '{}' relationship type before mapping: {}", column.getName(), column.getRelationshipType());

        Relationship.RelationshipType relationshipType;
        try {
            relationshipType = mapToRelationshipType(column.getRelationshipType());
            log.debug("✅ Mapped relationship type: {}", relationshipType);
        } catch (IllegalArgumentException e) {
            log.error("❌ Invalid relationship type for column '{}': {}", column.getName(), column.getRelationshipType(), e);
            return null;
        }

        relationship.setRelationshipType(relationshipType);

        // Προσθήκη των OnDelete & OnUpdate αν υπάρχουν
        if (column.getOnDelete() != null) {
            relationship.setOnDelete(column.getOnDelete());
            log.debug("OnDelete action set to: {}", column.getOnDelete());
        }

        if (column.getOnUpdate() != null) {
            relationship.setOnUpdate(column.getOnUpdate());
            log.debug("OnUpdate action set to: {}", column.getOnUpdate());
        }

        // Διαχείριση Many-to-Many σχέσεων
        if ("MANYTOMANY".equalsIgnoreCase(column.getRelationshipType())) {
            resolveManyToManyDetails(column, relationship);
        }

        log.info("Created relationship: {}", relationship);
        return relationship;
    }


    private Column findTargetColumn(Table targetTable, String targetColumnName) {
        if (targetColumnName == null) {
            log.warn("Target column name is null.");
            return null;
        }

        return targetTable.getColumns().stream()
                .filter(col -> col.getName().equals(targetColumnName))
                .findFirst()
                .orElse(null);
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
