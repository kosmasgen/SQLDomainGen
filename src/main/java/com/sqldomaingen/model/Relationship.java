package com.sqldomaingen.model;

import lombok.*;

/**
 * Represents a relationship between two tables (source -> target).
 * Used for generating JPA mappings and inverse relations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {
        "sourceTable",
        "sourceColumn",
        "targetTable",
        "targetColumn",
        "relationshipType",
        "joinTableName",
        "inverseJoinColumn",
        "mappedBy"
})
public class Relationship {

    private String sourceColumn;
    private String targetColumn;
    private String sourceTable;
    private String targetTable;

    private String onUpdate;
    private String onDelete;

    private String joinTableName;
    private String inverseJoinColumn;

    private String mappedBy;
    private RelationshipType relationshipType;

    public enum RelationshipType {
        ONETOONE,
        MANYTOONE,
        ONETOMANY,
        MANYTOMANY
    }
}
