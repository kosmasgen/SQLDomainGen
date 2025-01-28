package com.sqldomaingen.model;

import com.sqldomaingen.parser.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Table {
    private static final Logger logger = LoggerFactory.getLogger(Table.class);

    private String name;
    private List<Column> columns = new ArrayList<>();
    private List<String> constraints = new ArrayList<>();
    private List<Relationship> relationships = new ArrayList<>();

    /**
     * Προσθέτει constraints στον πίνακα.
     *
     * @param constraints η λίστα των constraints που θα προστεθούν.
     */
    public void addConstraints(List<String> constraints) {
        if (constraints != null) {
            this.constraints.addAll(constraints);
        }
    }

    /**
     * Προσθέτει μία στήλη στον πίνακα.
     *
     * @param column το αντικείμενο Column που θα προστεθεί.
     */
    public void addColumn(Column column) {
        if (column != null) {
            this.columns.add(column);
        }
    }

    /**
     * Προσθέτει μία σχέση Foreign Key στον πίνακα.
     *
     * @param relationship το αντικείμενο Relationship που θα προστεθεί.
     */
    public void addRelationship(Relationship relationship) {
        if (this.relationships == null) {
            this.relationships = new ArrayList<>(); // 🔥 Εξασφαλίζουμε ότι η λίστα είναι αρχικοποιημένη
        }
        this.relationships.add(relationship);

        // 🔍 Debug log για επιβεβαίωση
        logger.debug("Added relationship: {}.{} -> {}.{}",
                relationship.getSourceTable(), relationship.getSourceColumn(),
                relationship.getTargetTable(), relationship.getTargetColumn());

        logger.debug("Current relationship count in Table {}: {}", this.name, this.relationships.size());
    }


    /**
     * Επιστρέφει μια περιγραφή του πίνακα με τις στήλες, τα constraints και τις σχέσεις.
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
