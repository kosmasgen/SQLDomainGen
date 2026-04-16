package com.sqldomaingen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parsed database table together with its columns, constraints,
 * resolved relationships, and synthetic many-to-many metadata.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Table {

    /**
     * The physical table name as parsed from SQL.
     */
    private String name;

    /**
     * The columns that belong to this table.
     */
    private List<Column> columns = new ArrayList<>();

    /**
     * Table-level constraints collected from the SQL definition.
     */
    private List<String> constraints = new ArrayList<>();

    /**
     * Resolved standard relationships derived from foreign keys.
     */
    private List<Relationship> relationships = new ArrayList<>();

    /**
     * Synthetic many-to-many relationships generated from pure join tables.
     */
    private List<ManyToManyRelation> manyToManyRelations = new ArrayList<>();

    private CompositeKeyDefinition compositeKey;

    private List<IndexDefinition> indexes = new ArrayList<>();

    /**
     * Indicates whether this table is a pure join table that should not be
     * generated as a standalone entity.
     */
    private boolean pureJoinTable;

    /**
     * Adds table-level constraints to this table.
     *
     * @param constraints the constraints to append
     */
    public void addConstraints(List<String> constraints) {
        if (constraints != null) {
            this.constraints.addAll(constraints);
        }
    }

    /**
     * Adds a column to this table.
     *
     * @param column the column to add
     */
    public void addColumn(Column column) {
        if (column != null) {
            this.columns.add(column);
        }
    }

    /**
     * Adds a resolved relationship to this table.
     *
     * @param relationship the relationship to add
     */
    public void addRelationship(Relationship relationship) {
        if (relationship == null) {
            return;
        }

        if (this.relationships == null) {
            this.relationships = new ArrayList<>();
        }

        this.relationships.add(relationship);

        log.debug("Relationship added: {}.{} -> {}.{}",
                relationship.getSourceTable(),
                relationship.getSourceColumn(),
                relationship.getTargetTable(),
                relationship.getTargetColumn());

        log.debug("Table '{}' relationship count: {}", this.name, this.relationships.size());
    }

    private List<UniqueConstraint> uniqueConstraints = new ArrayList<>();

    /**
     * Adds synthetic many-to-many metadata to this table.
     *
     * @param manyToManyRelation the many-to-many metadata to add
     */
    public void addManyToManyRelation(ManyToManyRelation manyToManyRelation) {
        if (manyToManyRelation == null) {
            return;
        }

        if (this.manyToManyRelations == null) {
            this.manyToManyRelations = new ArrayList<>();
        }

        this.manyToManyRelations.add(manyToManyRelation);

        log.debug("Many-to-many relation added on table '{}': field='{}', target='{}', owningSide={}",
                this.name,
                manyToManyRelation.getFieldName(),
                manyToManyRelation.getTargetEntityName(),
                manyToManyRelation.isOwningSide());
    }

    /**
     * Returns a readable representation of the table model for debugging.
     *
     * @return the table debug representation
     */
    @Override
    public String toString() {
        return "Table{name='" + name + '\'' +
                ", columns=" + columns +
                ", constraints=" + constraints +
                ", relationships=" + relationships +
                ", manyToManyRelations=" + manyToManyRelations +
                ", pureJoinTable=" + pureJoinTable +
                ", indexes=" + indexes +
                '}';
    }
}