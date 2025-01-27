package com.sqldomaingen.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relationship {

    private String sourceColumn;  // Στήλη από την οποία ξεκινά η σχέση (π.χ., ξένη κλείδα)
    private String targetColumn;  // Στήλη στον πίνακα-στόχο
    private String sourceTable;   // Πίνακας που περιέχει τη στήλη από την οποία γίνεται η αναφορά
    private String targetTable;   // Πίνακας που αναφέρεται από την ξένη κλείδα
    private String onUpdate;      // Ενέργεια κατά την ενημέρωση
    private String onDelete;      // Ενέργεια κατά τη διαγραφή
    private String joinTableName; // Όνομα πίνακα σύνδεσης (για ManyToMany)
    private String inverseJoinColumn; // Στήλη στον αντίστροφο πίνακα για ManyToMany
    private RelationshipType relationshipType;

    public enum RelationshipType {
        ONETOONE,
        MANYTOONE,
        ONETOMANY,
        MANYTOMANY
    }

    // Αντιπροσωπεύει τη σχέση ως κείμενο για logging ή debugging
    @Override
    public String toString() {
        return "Relationship{" +
                "sourceTable='" + sourceTable + '\'' +
                ", sourceColumn='" + sourceColumn + '\'' +
                ", targetTable='" + targetTable + '\'' +
                ", targetColumn='" + targetColumn + '\'' +
                ", relationshipType='" + relationshipType + '\'' +
                ", onUpdate='" + onUpdate + '\'' +
                ", onDelete='" + onDelete + '\'' +
                '}';
    }
}
