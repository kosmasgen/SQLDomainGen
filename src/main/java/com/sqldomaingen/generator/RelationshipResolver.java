package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.NamingConverter;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Slf4j
public class RelationshipResolver {

    /**
     * Αναγνωρίζει και δημιουργεί σχέσεις για έναν συγκεκριμένο πίνακα.
     *
     * @param sourceTable Ο πίνακας προς ανάλυση
     * @param allTables   Χάρτης με όλους τους πίνακες
     * @return Λίστα με τις σχέσεις που βρέθηκαν
     */
    public List<Relationship> resolveRelationships(Table sourceTable, Map<String, Table> allTables) {
        log.info("🔵 Resolving relationships for table: {}", sourceTable.getName());

        List<Relationship> relationships = new ArrayList<>();
        List<Column> foreignKeys = getForeignKeys(sourceTable);

        for (Column column : foreignKeys) {
            Relationship relationship = createRelationship(column, sourceTable, allTables);
            relationships.add(relationship);

            Table targetTable = allTables.get(column.getReferencedTable());
            if (targetTable != null) {
                Relationship.RelationshipType inverseType;

                // 🛠️ Διόρθωση: Αν η foreign key είναι UNIQUE, τότε είναι OneToOne, αλλιώς OneToMany
                boolean isUnique = column.isUnique();

                if (relationship.getRelationshipType() == Relationship.RelationshipType.MANYTOONE) {
                    inverseType = isUnique ? Relationship.RelationshipType.ONETOONE : Relationship.RelationshipType.ONETOMANY;
                } else if (relationship.getRelationshipType() == Relationship.RelationshipType.ONETOONE) {
                    inverseType = Relationship.RelationshipType.ONETOONE;
                } else {
                    inverseType = Relationship.RelationshipType.ONETOMANY;
                }


                Relationship inverseRelationship = new Relationship();
                inverseRelationship.setSourceColumn(column.getReferencedColumn());
                inverseRelationship.setTargetColumn(column.getName());
                inverseRelationship.setSourceTable(targetTable.getName());
                inverseRelationship.setTargetTable(sourceTable.getName());
                inverseRelationship.setRelationshipType(inverseType);

                // 🛠️ Εξασφάλιση ότι δεν προσθέτουμε OneToMany αν είναι πραγματικά OneToOne
                if (!targetTable.getRelationships().contains(inverseRelationship)) {
                    targetTable.addRelationship(inverseRelationship);
                    log.info("🔄 Inverse {} added to '{}': {}", inverseType, targetTable.getName(), inverseRelationship);
                }
            }
        }

        log.info("🏁 Finished resolving relationships for table '{}'. Total relationships found: {}",
                sourceTable.getName(), relationships.size());

        return relationships;
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
     * Δημιουργεί ένα `Relationship` αντικείμενο, καθορίζοντας τον τύπο του.
     */
    public Relationship createRelationship(Column column, Table sourceTable, Map<String, Table> allTables) {
        log.info("🔄 Creating relationship for column '{}' in table '{}', referencing '{}.{}'",
                column.getName(), sourceTable.getName(), column.getReferencedTable(), column.getReferencedColumn());

        Table targetTable = findTargetTable(NamingConverter.toPascalCase(column.getReferencedTable()), allTables);

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

        // ✅ Δημιουργία relationship
        Relationship relationship = new Relationship();
        relationship.setSourceTable(sourceTable.getName());
        relationship.setSourceColumn(column.getName());
        relationship.setTargetTable(targetTable.getName());
        relationship.setTargetColumn(targetColumn.getName());
        relationship.setRelationshipType(relationshipType);
        relationship.setOnDelete(column.getOnDelete());
        relationship.setOnUpdate(column.getOnUpdate());

        // ✅ Αν η σχέση είναι MANYTOMANY, προσθέτουμε joinTableName
        if (relationshipType == Relationship.RelationshipType.MANYTOMANY) {
            setJoinTableInfo(relationship, sourceTable, column, targetTable );
        }


        log.info("✅ Created relationship: {}", relationship);

        return relationship;
    }

    private void setJoinTableInfo(Relationship relationship, Table sourceTable, Column column, Table targetTable) {
        relationship.setJoinTableName(sourceTable.getName());
        log.info("🔗 Setting join table name for ManyToMany: {}", sourceTable.getName());

        // ✅ Εύρεση της άλλης foreign key
        String inverseColumn = findInverseJoinColumn(sourceTable, column);

        if (inverseColumn != null) {
            relationship.setInverseJoinColumn(inverseColumn); // 🔥 Ενημέρωση του αρχικού relationship
            log.info("🔗 Setting inverse join column for ManyToMany: {}", inverseColumn);
        } else {
            log.warn("⚠️ Inverse join column not found for table '{}'", sourceTable.getName());
        }
    }


    private String findInverseJoinColumn(Table joinTable, Column column) {
        for (Column col : joinTable.getColumns()) {
            if (!col.getName().equals(column.getName()) && col.isForeignKey()) {
                log.info("🔎 Found inverse join column: {}", col.getName());
                return col.getName(); // **Επιστρέφει την άλλη foreign key**
            }
        }
        log.warn("⚠️ No inverse join column found for table '{}'", joinTable.getName());
        return null;
    }


    /**
     * Καθορίζει αν η σχέση είναι `OneToMany`, `ManyToOne`, `OneToOne` ή `ManyToMany`.
     */
    private Relationship.RelationshipType determineType(Column column, Table sourceTable, Table targetTable) {
        if (isJoinTable(sourceTable)) {
            log.info("🔥 Table '{}' detected as a join table. Assigning MANYTOMANY.", sourceTable.getName());
            return Relationship.RelationshipType.MANYTOMANY;
        }

        // ✅ Αν η foreign key ΔΕΝ είναι unique, είναι σίγουρα MANYTOONE
        if (!column.isUnique()) {
            log.info("🔥 Column '{}' is NOT unique. Assigning MANYTOONE.", column.getName());
            return Relationship.RelationshipType.MANYTOONE;
        }

        // ✅ Αν υπάρχει μόνο μία αναφορά στον targetTable, είναι OneToOne
        if (hasSingleReference(sourceTable, targetTable)) {
            log.info("🔥 Column '{}' is unique AND '{}' has only one reference. Assigning ONETOONE.", column.getName(), targetTable.getName());
            return Relationship.RelationshipType.ONETOONE;
        }

        // ✅ Αν ο targetTable έχει πολλές αναφορές από το sourceTable, είναι OneToMany
        if (isOneToMany(targetTable, sourceTable)) {
            log.info("🔥 Table '{}' is referenced multiple times from '{}'. Assigning ONETOMANY.", targetTable.getName(), sourceTable.getName());
            return Relationship.RelationshipType.ONETOMANY;
        }

        // ✅ Default περίπτωση (δε θα πρέπει να φτάσει εδώ)
        return Relationship.RelationshipType.MANYTOONE;
    }



    /**
     * Ελέγχει αν ένας πίνακας είναι join table (δηλαδή έχει δύο foreign keys).
     */
    private boolean isJoinTable(Table table) {
        return table.getColumns().stream().filter(Column::isForeignKey).count() == 2;
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
        return sourceTable.getColumns().stream()
                .filter(col -> col.getReferencedTable() != null && col.getReferencedTable().equalsIgnoreCase(targetTable.getName()))
                .count() == 1;
    }


    /**
     * Βρίσκει τον πίνακα-στόχο.
     */
    private Table findTargetTable(String targetTableName, Map<String, Table> allTables) {
        if (targetTableName == null || targetTableName.isBlank()) {
            log.error("❌ Target table name is null or blank.");
            return null;
        }

        Table targetTable = allTables.get(targetTableName);
        if (targetTable == null) {
            log.error("❌ Table '{}' not found.", targetTableName);
        }
        return targetTable;
    }

    /**
     * Βρίσκει τη στήλη-στόχο.
     */
    private Column findTargetColumn(Table targetTable, String targetColumnName) {
        if (targetTable == null || targetColumnName == null || targetColumnName.isBlank()) {
            log.warn("⚠️ Invalid target column lookup.");
            return null;
        }

        return targetTable.getColumns().stream()
                .filter(col -> col.getName().equalsIgnoreCase(targetColumnName))
                .findFirst()
                .orElse(null);
    }
}
