package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.NamingConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.*;

@Getter
@NoArgsConstructor
@Component
public class RelationshipResolver {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipResolver.class);

    private final Map<String, Table> tableMap = new HashMap<>();
    private final List<Relationship> relationships = new ArrayList<>();

    public RelationshipResolver(Map<String, Table> tableMap) {
        this.tableMap.putAll(tableMap);
    }

    public List<Relationship> resolveRelationships(Table sourceTable) {
        logger.info("🔵 Resolving relationships for table: {}", sourceTable.getName());

        if (isJoinTable(sourceTable)) {
            return handleManyToManyJoinTable(sourceTable);
        }

        return handleForeignKeyRelationships(sourceTable);
    }


    private List<Relationship> handleManyToManyJoinTable(Table sourceTable) {
        logger.info("🔥 Detected join table '{}' with composite primary key. Treating as ManyToMany.", sourceTable.getName());

        List<Column> foreignKeys = getForeignKeys(sourceTable);
        if (foreignKeys.size() < 2) {
            logger.warn("⚠️ Join table '{}' does not have at least 2 foreign keys. Skipping ManyToMany handling.", sourceTable.getName());
            return Collections.emptyList();
        }

        Column fk1 = foreignKeys.get(0);
        Column fk2 = foreignKeys.get(1);

        String fk1ReferencedTablePascal = NamingConverter.toPascalCase(fk1.getReferencedTable());
        String fk2ReferencedTablePascal = NamingConverter.toPascalCase(fk2.getReferencedTable());

        Table table1 = findTargetTable(fk1ReferencedTablePascal);
        Table table2 = findTargetTable(fk2ReferencedTablePascal);

        if (table1 == null || table2 == null) {
            logger.warn("⚠️ One or both referenced tables not found for join table '{}': {} -> {}, {} -> {}",
                    sourceTable.getName(),
                    fk1.getName(), fk1ReferencedTablePascal,
                    fk2.getName(), fk2ReferencedTablePascal);
            return Collections.emptyList();
        }

        // 🚨 Αφαιρούμε τυχόν προηγούμενες σχέσεις ManyToOne που μπορεί να υπήρχαν
        sourceTable.getRelationships().removeIf(r -> r.getRelationshipType() == Relationship.RelationshipType.MANYTOONE);

        // ✅ Δημιουργούμε ΜΟΝΟ **μια** ManyToMany σχέση
        Relationship manyToMany = new Relationship(
                fk1.getReferencedColumn(),
                fk2.getReferencedColumn(),
                fk1ReferencedTablePascal,
                fk2ReferencedTablePascal,
                null, null,
                sourceTable.getName(),
                fk2.getName(),
                NamingConverter.toCamelCasePlural(fk2ReferencedTablePascal),
                Relationship.RelationshipType.MANYTOMANY
        );

        // Owning Side -> JoinTable
        manyToMany.setJoinTableName(sourceTable.getName());
        manyToMany.setSourceColumn(fk1.getName());
        manyToMany.setInverseJoinColumn(fk2.getName());

        table1.addRelationship(manyToMany);

        logger.info("🔄 ManyToMany relationship added: {} -> {} via {}",
                table1.getName(), table2.getName(), sourceTable.getName());

        // ✅ Προσθέτουμε **ΜΟΝΟ μία** σχέση ManyToMany
        this.relationships.add(manyToMany);

        logger.info("✅ ManyToMany relationship created for join table '{}'", sourceTable.getName());

        return List.of(manyToMany);
    }


    private void addInverseRelationship(Relationship relationship, Column column) {
        Table targetTable = tableMap.get(relationship.getTargetTable());
        if (targetTable == null) {
            logger.warn("⚠️ Target table '{}' not found while adding inverse relationship for '{}'", relationship.getTargetTable(), column.getName());
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

        // Εδώ θέλουμε mappedBy μόνο αν ΔΕΝ είναι ManyToMany
        if (relationship.getRelationshipType() != Relationship.RelationshipType.MANYTOMANY) {
            String mappedByValue = getMappedByFieldName(relationship, column);
            inverseRelationship.setMappedBy(mappedByValue);
            logger.info("🔧 Setting mappedBy='{}' for inverse relationship {} -> {}",
                    mappedByValue,
                    inverseRelationship.getSourceTable(),
                    inverseRelationship.getTargetTable());

        }

        if (!targetTable.getRelationships().contains(inverseRelationship)) {
            targetTable.addRelationship(inverseRelationship);
            logger.info("🔄 Inverse relationship added: {} -> {} ({})",
                    inverseRelationship.getSourceTable(), inverseRelationship.getTargetTable(), inverseRelationship.getRelationshipType());
        } else {
            logger.debug("⚠️ Inverse relationship already exists in '{}'. Skipping: {}", targetTable.getName(), inverseRelationship);
        }
    }

    private String getMappedByFieldName(Relationship relationship, Column column) {
        String rawName = column.getName();

        // Αν η στήλη τελειώνει σε _id, αφαιρούμε το _id
        if (rawName.toLowerCase().endsWith("_id")) {
            rawName = rawName.substring(0, rawName.length() - 3);
        }

        return NamingConverter.toCamelCase(rawName);
    }





    private List<Relationship> handleForeignKeyRelationships(Table sourceTable) {
        logger.info("🔄 Handling foreign key relationships for table: {}", sourceTable.getName());

        List<Relationship> localRelationships = new ArrayList<>();
        List<Column> foreignKeys = getForeignKeys(sourceTable);

        for (Column column : foreignKeys) {
            logger.debug("🔗 Analyzing foreign key: {}", column.getName());

            Relationship relationship = createRelationship(column, sourceTable);
            if (relationship != null) {
                localRelationships.add(relationship);
                sourceTable.addRelationship(relationship);
                this.relationships.add(relationship);
                logger.info("✅ Relationship created: {} -> {} ({})",
                        relationship.getSourceTable(), relationship.getTargetTable(), relationship.getRelationshipType());

                addInverseRelationship(relationship, column);
            } else {
                logger.warn("❌ No relationship created for foreign key '{}'", column.getName());
            }
        }

        logger.info("🏁 Finished handling foreign key relationships for table '{}'. Relationships created: {}",
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
     * Δημιουργεί ένα `Relationship` αντικείμενο, καθορίζοντας τον τύπο του.
     */
    public Relationship createRelationship(Column column, Table sourceTable) {
        logger.info("🔄 Creating relationship for column '{}' in table '{}', referencing '{}.{}'",
                column.getName(), sourceTable.getName(), column.getReferencedTable(), column.getReferencedColumn());

        Table targetTable = findTargetTable(NamingConverter.toPascalCase(column.getReferencedTable()));

        if (targetTable == null) {
            logger.warn("⚠️ Target table '{}' not found for column '{}'", column.getReferencedTable(), column.getName());
            return null;
        }

        Column targetColumn = findTargetColumn(targetTable, column.getReferencedColumn());
        if (targetColumn == null) {
            logger.warn("⚠️ Target column '{}' not found in table '{}'", column.getReferencedColumn(), targetTable.getName());
            return null;
        }

        Relationship.RelationshipType relationshipType = determineType(column, sourceTable, targetTable);
        if (relationshipType == null) {
            logger.warn("⚠️ Unable to determine relationship type for column '{}' in table '{}'", column.getName(), sourceTable.getName());
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

        if (relationshipType == Relationship.RelationshipType.MANYTOMANY) {
            setJoinTableInfo(relationship, sourceTable, column);
        }
        logger.info("✅ Created relationship: {}", relationship);

        return relationship;
    }



    private void setJoinTableInfo(Relationship relationship, Table sourceTable, Column column) {
        relationship.setJoinTableName(sourceTable.getName());
        logger.info("🔗 Setting join table name for ManyToMany: {}", sourceTable.getName());

        // ✅ Εύρεση της άλλης foreign key
        String inverseColumn = findInverseJoinColumn(sourceTable, column);

        if (inverseColumn != null) {
            relationship.setInverseJoinColumn(inverseColumn); // 🔥 Ενημέρωση του αρχικού relationship
            logger.info("🔗 Setting inverse join column for ManyToMany: {}", inverseColumn);
        } else {
            logger.warn("⚠️ Inverse join column not found for table '{}'", sourceTable.getName());
        }
    }

    private String findInverseJoinColumn(Table joinTable, Column column) {
        for (Column col : joinTable.getColumns()) {
            if (!col.getName().equals(column.getName()) && col.isForeignKey()) {
                logger.info("🔎 Found inverse join column: {}", col.getName());
                return col.getName(); // **Επιστρέφει την άλλη foreign key**
            }
        }
        logger.warn("⚠️ No inverse join column found for table '{}'", joinTable.getName());
        return null;
    }

    /**
     * Καθορίζει αν η σχέση είναι `OneToMany`, `ManyToOne`, `OneToOne` ή `ManyToMany`.
     */
    private Relationship.RelationshipType determineType(Column column, Table sourceTable, Table targetTable) {
        // ✅ Αν είναι join table και έχει δύο foreign keys, είναι ManyToMany
        if (isJoinTable(sourceTable)) {
            // Αν ο πίνακας είναι join table, αλλά ελέγχουμε τη μοναδικότητα των foreign keys
            logger.info("🔥 Table '{}' detected as a join table. Checking uniqueness of foreign keys.", sourceTable.getName());

            // Αν η στήλη δεν είναι μοναδική, θεωρούμε ότι πρόκειται για MANYTOONE
            if (!column.isUnique()) {
                logger.info("🔥 Column '{}' is NOT unique. Assigning MANYTOONE.", column.getName());
                return Relationship.RelationshipType.MANYTOONE;
            } else {
                // Αν είναι μοναδική, είναι αρκετό για να θεωρηθεί πολλαπλή σχέση ManyToMany
                logger.info("🔥 Column '{}' is unique in join table. Assigning MANYTOMANY.", column.getName());
                return Relationship.RelationshipType.MANYTOMANY;
            }
        }

        // ✅ Αν η σχέση είναι OneToMany (υπάρχουν πολλές αναφορές στον ίδιο πίνακα)
        if (isOneToMany(targetTable, sourceTable)) {
            logger.info("🔥 Table '{}' is referenced multiple times from '{}'. Assigning ONETOMANY.", targetTable.getName(), sourceTable.getName());
            return Relationship.RelationshipType.ONETOMANY;
        }

        // ✅ Αν η foreign key ΔΕΝ είναι unique, είναι σίγουρα MANYTOONE
        if (!column.isUnique()) {
            logger.info("🔥 Column '{}' is NOT unique. Assigning MANYTOONE.", column.getName());
            return Relationship.RelationshipType.MANYTOONE;
        }

        // ✅ Αν υπάρχει μόνο μία αναφορά στον targetTable, είναι OneToOne
        if (hasSingleReference(sourceTable, targetTable)) {
            logger.info("🔥 Column '{}' is unique AND '{}' has only one reference. Assigning ONETOONE.", column.getName(), targetTable.getName());
            return Relationship.RelationshipType.ONETOONE;
        }

        // ✅ Default περίπτωση (αν δεν ταιριάζει τίποτα άλλο)
        logger.info("🔥 Default case for '{}'. Assigning MANYTOONE.", column.getName());
        return Relationship.RelationshipType.MANYTOONE;
    }


    public boolean isJoinTable(Table table) {
        List<Column> pkColumns = table.getColumns().stream()
                .filter(Column::isPrimaryKey)
                .toList();

        List<Column> fkColumns = table.getColumns().stream()
                .filter(Column::isForeignKey)
                .toList();

        // Πρέπει να υπάρχουν ακριβώς 2 foreign keys
        if (fkColumns.size() != 2) return false;

        // Πρέπει να είναι σύνθετο primary key με 2 στήλες
        boolean pkIsComposite = pkColumns.size() == 2;

        // Πρέπει το primary key να περιέχει και τα δύο foreign keys
        boolean pkIncludesAllFks = new HashSet<>(pkColumns).containsAll(fkColumns);

        // Αν είναι composite PK με 2 FKs, τότε έστω και με extra στήλες, είναι join table με metadata
        return pkIsComposite && pkIncludesAllFks;
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
        logger.debug("🔍 Entering hasSingleReference: Checking '{}' -> '{}'", sourceTable.getName(), targetTable.getName());

        long referenceCount = sourceTable.getColumns().stream()
                .filter(col -> col.getReferencedTable() != null && col.getReferencedTable().equalsIgnoreCase(targetTable.getName()))
                .count();

        logger.debug("📌 Found {} references from '{}' to '{}'", referenceCount, sourceTable.getName(), targetTable.getName());

        boolean result = referenceCount == 1;
        logger.info("🔍 Checking if '{}' has a single reference to '{}'. Found {} references. Result: {}",
                sourceTable.getName(), targetTable.getName(), referenceCount, result);

        return result;
    }

    /**
     * Βρίσκει τον πίνακα-στόχο.
     */
    private Table findTargetTable(String targetTableName) {
        if (targetTableName == null || targetTableName.isBlank()) {
            logger.error("❌ Target table name is null or blank.");
            return null;
        }

        Table targetTable = tableMap.get(targetTableName); // ✅ Ανακτούμε τον πίνακα από το tableMap
        if (targetTable == null) {
            logger.error("❌ Table '{}' not found.", targetTableName);
        }
        return targetTable;
    }

    /**
     * Βρίσκει τη στήλη-στόχο στον πίνακα προορισμού.
     */
    private Column findTargetColumn(Table targetTable, String targetColumnName) {
        if (targetTable == null || targetColumnName == null || targetColumnName.isBlank()) {
            logger.warn("⚠️ Invalid target column lookup. Table: '{}', Column: '{}'",
                    targetTable != null ? targetTable.getName() : "null", targetColumnName);
            return null;
        }

        // Προσθήκη print για τις στήλες του πίνακα-στόχου
        logger.info("🔍 Checking columns in table '{}'. Looking for '{}'. Available columns: {}",
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
            logger.warn("⚠️ Column '{}' not found in table '{}'", targetColumnName, targetTable.getName());
        }

        return targetColumn;
    }

    public void resolveRelationshipsForAllTables() {
        logger.info("🔍 Starting to resolve relationships for all {} tables...", tableMap.size());

        // Εκτύπωση του περιεχομένου του tableMap
        logger.debug("🗺️ TableMap contents (detailed):");
        tableMap.forEach((key, table) -> {
            logger.debug("🔑 Key: '{}', Table Name: '{}'", key, table.getName());
            table.getColumns().forEach(column -> {
                logger.debug("   🧱 Column: '{}', FK: {}, PK: {}, RefTable: '{}', RefColumn: '{}'",
                        column.getName(),
                        column.isForeignKey(),
                        column.isPrimaryKey(),
                        column.getReferencedTable(),
                        column.getReferencedColumn());
            });
        });

        for (Table table : tableMap.values()) {
            logger.info("📋 Resolving relationships for table: {}", table.getName());

            for (Column column : table.getColumns()) {
                logger.info("🔎 Column: {} | Type: {} | PK: {} | FK: {} | RefTable: {} | RefColumn: {}",
                        column.getName(),
                        column.getSqlType(),
                        column.isPrimaryKey(),
                        column.isForeignKey(),
                        column.getReferencedTable(),
                        column.getReferencedColumn());
            }

            List<Relationship> localRelationships = resolveRelationships(table);

            for (Relationship relationship : localRelationships) {
                logger.info("🔗 Relationship created: {} -> {} | Type: {}",
                        relationship.getSourceTable(),
                        relationship.getTargetTable(),
                        relationship.getRelationshipType());
            }
        }

        logger.info("✅ Relationships resolved for all tables.");
    }

    private boolean tableHasForeignKey(Table sourceTable, Table targetTable) {
        return sourceTable.getColumns().stream()
                .anyMatch(col -> col.isForeignKey() && col.getReferencedTable().equalsIgnoreCase(targetTable.getName()));
    }
}