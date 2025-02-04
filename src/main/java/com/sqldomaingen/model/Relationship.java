package com.sqldomaingen.model;

import lombok.*;

/**
 * Represents a database relationship between two tables.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Relationship {

    private String sourceColumn;
    private String targetColumn;
    private String sourceTable;
    private String targetTable;
    private String onUpdate;
    private String onDelete;
    private String joinTableName = "";
    private String inverseJoinColumn = "";
    private RelationshipType relationshipType;


    public enum RelationshipType {
        ONETOONE,
        MANYTOONE,
        ONETOMANY,
        MANYTOMANY
    }
}
