package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.NamingConverter;
import lombok.Getter;  // Εισαγωγή του Lombok @Getter
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import java.util.*;

@Getter  // Προσθήκη του Lombok @Getter στην κλάση
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

        if (isJoinTable(sourceTable)) { // <--- ΑΛΛΑΓΗ ΕΔΩ
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

        // MANYTOONE relationships στο ίδιο το join table
        Relationship manyToOne1 = new Relationship(
                fk1.getName(),
                fk1.getReferencedColumn(),
                sourceTable.getName(),
                fk1.getReferencedTable(),
                null, null,
                null, null, null,
                Relationship.RelationshipType.MANYTOONE
        );

        Relationship manyToOne2 = new Relationship(
                fk2.getName(),
                fk2.getReferencedColumn(),
                sourceTable.getName(),
                fk2.getReferencedTable(),
                null, null,
                null, null, null,
                Relationship.RelationshipType.MANYTOONE
        );

        sourceTable.addRelationship(manyToOne1);
        sourceTable.addRelationship(manyToOne2);

        addInverseRelationship(manyToOne1, fk1, sourceTable);
        addInverseRelationship(manyToOne2, fk2, sourceTable);

        // MANYTOMANY relationships στις συνδεδεμένες οντότητες
        Relationship manyToMany1 = new Relationship(
                fk1.getReferencedColumn(),
                fk2.getReferencedColumn(),
                fk1.getReferencedTable(),
                fk2.getReferencedTable(),
                null, null,
                sourceTable.getName(),
                fk2.getName(),
                NamingConverter.toCamelCasePlural(fk2.getReferencedTable()),
                Relationship.RelationshipType.MANYTOMANY
        );

        // Δεν βάζουμε mappedBy στην owning side
        Relationship manyToMany2 = new Relationship(
                fk2.getReferencedColumn(),
                fk1.getReferencedColumn(),
                fk2.getReferencedTable(),
                fk1.getReferencedTable(),
                null, null,
                sourceTable.getName(),
                fk1.getName(),
                NamingConverter.toCamelCasePlural(fk1.getReferencedTable()),
                Relationship.RelationshipType.MANYTOMANY
        );

        // Μόνο στην πρώτη σχέση προσθέτουμε το mappedBy
        manyToMany1.setMappedBy(NamingConverter.toCamelCasePlural(fk2.getReferencedTable()));

        Table table1 = tableMap.get(fk1.getReferencedTable());
        Table table2 = tableMap.get(fk2.getReferencedTable());

        if (table1 != null) {
            table1.addRelationship(manyToMany1);
            logger.info("🔄 ManyToMany relationship added: {} -> {} via {}",
                    table1.getName(), table2.getName(), sourceTable.getName());
        }

        if (table2 != null) {
            table2.addRelationship(manyToMany2);
            logger.info("🔄 ManyToMany relationship added: {} -> {} via {}",
                    table2.getName(), table1.getName(), sourceTable.getName());
        }

        // Ενημερώνουμε και το RelationshipResolver
        this.relationships.add(manyToOne1);
        this.relationships.add(manyToOne2);
        this.relationships.add(manyToMany1);
        this.relationships.add(manyToMany2);

        logger.info("✅ ManyToMany and ManyToOne relationships created for join table '{}'", sourceTable.getName());

        return List.of(manyToOne1, manyToOne2, manyToMany1, manyToMany2);
    }


    private void addInverseRelationship(Relationship relationship, Column column, Table sourceTable) {
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
            String mappedByValue = NamingConverter.toCamelCase(column.getName().replace("_id", ""));
            inverseRelationship.setMappedBy(mappedByValue);
            relationship.setMappedBy(mappedByValue);
        }

        if (!targetTable.getRelationships().contains(inverseRelationship)) {
            targetTable.addRelationship(inverseRelationship);
            logger.info("🔄 Inverse relationship added: {} -> {} ({})",
                    inverseRelationship.getSourceTable(), inverseRelationship.getTargetTable(), inverseRelationship.getRelationshipType());
        } else {
            logger.debug("⚠️ Inverse relationship already exists in '{}'. Skipping: {}", targetTable.getName(), inverseRelationship);
        }
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

                addInverseRelationship(relationship, column, sourceTable);
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




    private boolean isJoinTable(Table table) {
        List<Column> columns = table.getColumns();

        long fkCount = columns.stream().filter(Column::isForeignKey).count();
        long pkCount = columns.stream().filter(Column::isPrimaryKey).count();

        boolean allPKsAreFKs = columns.stream()
                .filter(Column::isPrimaryKey)
                .allMatch(Column::isForeignKey);

        boolean hasExtraNonKeyColumn = columns.stream()
                .anyMatch(c -> !c.isForeignKey() && !c.isPrimaryKey());

        logger.debug("🔍 Checking if '{}' is join table. FK Count: {}, PK Count: {}, All PKs are FKs: {}, Has Extra Column: {}, Columns: {}",
                table.getName(), fkCount, pkCount, allPKsAreFKs, hasExtraNonKeyColumn, columns);

        // Join Table θεωρείται ΜΟΝΟ αν έχει 2 FK, 2 PK και ΚΑΜΙΑ extra non-key column, και τα PK είναι FK
        return fkCount == 2 && pkCount == 2 && allPKsAreFKs && !hasExtraNonKeyColumn;
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

}