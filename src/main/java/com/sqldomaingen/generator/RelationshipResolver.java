package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.NamingConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@NoArgsConstructor
@Component
@Log4j2
public class RelationshipResolver {

    private final Map<String, Table> tableMap = new HashMap<>();
    private final List<Relationship> relationships = new ArrayList<>();

    public RelationshipResolver(Map<String, Table> tableMap) {
        this.tableMap.putAll(tableMap);
    }

    public List<Relationship> resolveRelationships(Table sourceTable) {
        log.info("🔵 Resolving relationships for table: {}", sourceTable.getName());

        return handleForeignKeyRelationships(sourceTable);
    }

    private void addInverseRelationship(Relationship relationship, Column column) {
        if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOMANY) {
            log.info("⛔ Skipping inverse for pseudo-ManyToMany on '{}.{}'", relationship.getSourceTable(), relationship.getSourceColumn());
            return;
        }

        Table targetTable = tableMap.get(relationship.getTargetTable());
        if (targetTable == null) {
            log.warn("⚠️ Target table '{}' not found while adding inverse relationship for '{}'", relationship.getTargetTable(), column.getName());
            return;
        }

        Relationship.RelationshipType inverseType;
        boolean isUnique = column.isUnique();

        if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOONE) {
            inverseType = isUnique ? Relationship.RelationshipType.ONETOONE : Relationship.RelationshipType.ONETOMANY;
        } else if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE) {
            inverseType = Relationship.RelationshipType.ONETOONE;
        } else {
            inverseType = Relationship.RelationshipType.ONETOMANY;
        }

        Relationship inverseRelationship = new Relationship();
        inverseRelationship.setSourceColumn(relationship.getTargetColumn());
        inverseRelationship.setTargetColumn(relationship.getSourceColumn());
        inverseRelationship.setSourceTable(relationship.getTargetTable());
        inverseRelationship.setTargetTable(relationship.getSourceTable());
        inverseRelationship.setRelationshipType(inverseType);


        String mappedByValue = getMappedByFieldName(column);
        inverseRelationship.setMappedBy(mappedByValue);
        log.info("🔧 Setting mappedBy='{}' for inverse relationship {} -> {}",
                mappedByValue,
                inverseRelationship.getSourceTable(),
                inverseRelationship.getTargetTable());

        if (!targetTable.getRelationships().contains(inverseRelationship)) {
            targetTable.addRelationship(inverseRelationship);
            log.info("🔄 Inverse relationship added: {} -> {} ({})",
                    inverseRelationship.getSourceTable(), inverseRelationship.getTargetTable(), inverseRelationship.getRelationshipType());
        } else {
            log.debug("⚠️ Inverse relationship already exists in '{}'. Skipping: {}", targetTable.getName(), inverseRelationship);
        }
    }


    private String getMappedByFieldName(Column column) {
        String rawName = column.getName();

        // Αν η στήλη τελειώνει σε _id, αφαιρούμε το _id
        if (rawName.toLowerCase().endsWith("_id")) {
            rawName = rawName.substring(0, rawName.length() - 3);
        }

        return NamingConverter.toCamelCase(rawName);
    }


    private List<Relationship> handleForeignKeyRelationships(Table sourceTable) {
        log.info("🔄 Handling foreign key relationships for table: {}", sourceTable.getName());

        List<Relationship> localRelationships = new ArrayList<>();
        List<Column> foreignKeys = getForeignKeys(sourceTable);

        for (Column column : foreignKeys) {
            log.debug("🔗 Analyzing foreign key: {}", column.getName());

            Relationship relationship = createRelationship(column, sourceTable);
            if (relationship != null) {
                localRelationships.add(relationship);
                sourceTable.addRelationship(relationship);
                this.relationships.add(relationship);
                log.info("✅ Relationship created: {} -> {} ({})",
                        relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType());

                addInverseRelationship(relationship, column);
            } else {
                log.warn("❌ No relationship created for foreign key '{}'", column.getName());
            }
        }

        log.info("🏁 Finished handling foreign key relationships for table '{}'. Relationships created: {}",
                sourceTable.getName(), localRelationships.size());

        return localRelationships;
    }


    /**
     * Επιστρέφει όλες τις foreign key στήλες του πίνακα.
     */
    private List<Column> getForeignKeys(Table table) {
        return table.getColumns().stream()
                .filter(Column::isForeignKey)
                .toList();
    }


    /**
     * Καθορίζει αν η σχέση είναι `OneToMany`, `ManyToOne`, `OneToOne` ή `ManyToMany`.
     */
    public Relationship createRelationship(Column column, Table sourceTable) {
        log.info("🔄 Creating relationship for column '{}' in table '{}', referencing '{}.{}'",
                column.getName(), sourceTable.getName(), column.getReferencedTable(), column.getReferencedColumn());

        // Use raw referenced table name; do not PascalCase here.
        Table targetTable = findTargetTable(column.getReferencedTable());

        if (targetTable == null) {
            log.warn("⚠️ Target table '{}' not found for column '{}'", column.getReferencedTable(), column.getName());
            return null;
        }

        Column targetColumn = findTargetColumn(targetTable, column.getReferencedColumn());
        if (targetColumn == null) {
            log.warn("⚠️ Target column '{}' not found in table '{}'", column.getReferencedColumn(), targetTable.getName());
            return null;
        }

        Relationship.RelationshipType relationshipType = determineType(column, sourceTable, targetTable);
        if (relationshipType == null) {
            log.warn("⚠️ Unable to determine relationship type for column '{}' in table '{}'", column.getName(), sourceTable.getName());
            return null;
        }

        Relationship relationship = new Relationship();
        relationship.setSourceTable(sourceTable.getName());
        relationship.setSourceColumn(column.getName());
        relationship.setTargetTable(targetTable.getName());
        relationship.setTargetColumn(targetColumn.getName());
        relationship.setRelationshipType(relationshipType);
        relationship.setOnDelete(column.getOnDelete());
        relationship.setOnUpdate(column.getOnUpdate());

        log.info("✅ Created relationship: {}", relationship);
        return relationship;
    }


    /**
     * Ελέγχει αν υπάρχει σχέση `OneToMany` μετρώντας αναφορές στον πίνακα προορισμού.
     */
    private boolean isOneToMany(Table targetTable, Table sourceTable) {
        long referenceCount = sourceTable.getColumns().stream()
                .filter(col -> col.getReferencedTable() != null && col.getReferencedTable().equalsIgnoreCase(targetTable.getName()))
                .count();
        return referenceCount > 1;
    }

    /**
     * Επιστρέφει `true` αν ο πίνακας-στόχος έχει μόνο μία αναφορά.
     */
    private boolean hasSingleReference(Table sourceTable, Table targetTable) {
        log.debug("🔍 Entering hasSingleReference: Checking '{}' -> '{}'", sourceTable.getName(), targetTable.getName());

        long referenceCount = sourceTable.getColumns().stream()
                .filter(col -> col.getReferencedTable() != null && col.getReferencedTable().equalsIgnoreCase(targetTable.getName()))
                .count();

        log.debug("📌 Found {} references from '{}' to '{}'", referenceCount, sourceTable.getName(), targetTable.getName());

        boolean result = referenceCount == 1;
        log.info("🔍 Checking if '{}' has a single reference to '{}'. Found {} references. Result: {}",
                sourceTable.getName(), targetTable.getName(), referenceCount, result);

        return result;
    }

    /**
     * Βρίσκει τον πίνακα-στόχο.
     */
    private Table findTargetTable(String referencedTableRaw) {
        String raw = referencedTableRaw == null ? "" : referencedTableRaw.trim();
        if (raw.isBlank()) {
            log.error("❌ Referenced table name is null or blank.");
            return null;
        }

        // 1) Direct hit (exact key)
        Table direct = tableMap.get(raw);
        if (direct != null) return direct;

        // 2) Try without schema
        String noSchema = normalizeTableName(raw);
        Table noSchemaHit = tableMap.get(noSchema);
        if (noSchemaHit != null) return noSchemaHit;

        // 3) Case-insensitive match on normalized names
        for (Map.Entry<String, Table> e : tableMap.entrySet()) {
            String keyNorm = normalizeTableName(e.getKey());
            if (keyNorm.equalsIgnoreCase(noSchema)) {
                return e.getValue();
            }
        }

        log.error("❌ Table '{}' not found in tableMap keys: {}", raw, tableMap.keySet());
        return null;
    }

    private static String normalizeTableName(String raw) {
        if (raw == null) return "";
        String s = raw.trim();
        int dot = s.lastIndexOf('.');
        if (dot >= 0 && dot < s.length() - 1) {
            s = s.substring(dot + 1);
        }
        return s;
    }

    private Relationship.RelationshipType determineType(
            Column column,
            Table sourceTable,
            Table targetTable
    ) {

        // Explicit pseudo-constraint for ManyToMany
        if (column.isManyToMany()) {
            log.info("🎯 Column '{}' has explicit MANYTOMANY pseudo-constraint. Assigning MANYTOMANY.", column.getName());
            return Relationship.RelationshipType.MANYTOMANY;
        }

        // Owning side of a foreign key:
        // FK + UNIQUE => OneToOne
        if (column.isUnique()) {
            log.info("🔥 Column '{}' is unique. Assigning ONETOONE.", column.getName());
            return Relationship.RelationshipType.ONETOONE;
        }

        // Default for FK columns
        log.info("🔥 Column '{}' is not unique. Assigning MANYTOONE.", column.getName());
        return Relationship.RelationshipType.MANYTOONE;
    }


    /**
     * Βρίσκει τη στήλη-στόχο στον πίνακα προορισμού.
     */
    private Column findTargetColumn(Table targetTable, String targetColumnName) {
        if (targetTable == null || targetColumnName == null || targetColumnName.isBlank()) {
            log.warn("⚠️ Invalid target column lookup. Table: '{}', Column: '{}'",
                    targetTable != null ? targetTable.getName() : "null", targetColumnName);
            return null;
        }

        // Προσθήκη print για τις στήλες του πίνακα-στόχου
        log.info("🔍 Checking columns in table '{}'. Looking for '{}'. Available columns: {}",
                targetTable.getName(), targetColumnName,
                targetTable.getColumns().stream()
                        .map(Column::getName)
                        .toList()
        );

        Column targetColumn = targetTable.getColumns().stream()
                .filter(col -> col.getName().equalsIgnoreCase(targetColumnName))
                .findFirst()
                .orElse(null);

        if (targetColumn == null) {
            log.warn("⚠️ Column '{}' not found in table '{}'", targetColumnName, targetTable.getName());
        }

        return targetColumn;
    }

    public void resolveRelationshipsForAllTables() {
        log.info("🔍 Starting to resolve relationships for all {} tables...", tableMap.size());

        // Εκτύπωση του περιεχομένου του tableMap
        log.debug("🗺️ TableMap contents (detailed):");
        tableMap.forEach((key, table) -> {
            log.debug("🔑 Key: '{}', Table Name: '{}'", key, table.getName());
            table.getColumns().forEach(column -> {
                log.debug("   🧱 Column: '{}', FK: {}, PK: {}, RefTable: '{}', RefColumn: '{}'",
                        column.getName(),
                        column.isForeignKey(),
                        column.isPrimaryKey(),
                        column.getReferencedTable(),
                        column.getReferencedColumn());
            });
        });

        for (Table table : tableMap.values()) {
            log.info("📋 Resolving relationships for table: {}", table.getName());

            for (Column column : table.getColumns()) {
                log.info("🔎 Column: {} | Type: {} | PK: {} | FK: {} | RefTable: {} | RefColumn: {}",
                        column.getName(),
                        column.getSqlType(),
                        column.isPrimaryKey(),
                        column.isForeignKey(),
                        column.getReferencedTable(),
                        column.getReferencedColumn());
            }

            List<Relationship> localRelationships = resolveRelationships(table);

            for (Relationship relationship : localRelationships) {
                log.info("🔗 Relationship created: {} -> {} | Type: {}",
                        relationship.getSourceTable(),
                        relationship.getTargetTable(),
                        relationship.getRelationshipType());
            }
        }

        log.info("✅ Relationships resolved for all tables.");
    }

}