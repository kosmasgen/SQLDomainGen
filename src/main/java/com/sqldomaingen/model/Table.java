package com.sqldomaingen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Table {
    private String name;
    private List<Column> columns = new ArrayList<>();
    private List<String> constraints = new ArrayList<>();

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
     * Επιστρέφει μια περιγραφή του πίνακα με τις στήλες και τα constraints.
     */
    @Override
    public String toString() {
        return "Table{name='" + name + '\'' +
                ", columns=" + columns +
                ", constraints=" + constraints +
                '}';
    }
}
