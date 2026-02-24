package com.sqldomaingen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Table {

    private String name;
    private List<Column> columns = new ArrayList<>();
    private List<String> constraints = new ArrayList<>();
    private List<Relationship> relationships = new ArrayList<>();

    /**
     * Adds table-level constraints (if provided) to this table.
     *
     * @param constraints constraints to append (ignored if null)
     */
    public void addConstraints(List<String> constraints) {
        if (constraints != null) {
            this.constraints.addAll(constraints);
        }
    }

    /**
     * Adds a column to this table.
     *
     * @param column column to add (ignored if null)
     */
    public void addColumn(Column column) {
        if (column != null) {
            this.columns.add(column);
        }
    }

    /**
     * Adds a relationship (typically derived from a FOREIGN KEY) to this table.
     * Ensures the relationships list is initialized before insertion.
     *
     * @param relationship relationship to add (ignored if null)
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
                relationship.getSourceTable(), relationship.getSourceColumn(),
                relationship.getTargetTable(), relationship.getTargetColumn());

        log.debug("Table '{}' relationship count: {}", this.name, this.relationships.size());
    }

    /**
     * Returns a readable representation of the table model for debugging.
     */
    @Override
    public String toString() {
        return "Table{name='" + name + '\'' +
                ", columns=" + columns +
                ", constraints=" + constraints +
                ", relationships=" + relationships +
                '}';
    }
}
