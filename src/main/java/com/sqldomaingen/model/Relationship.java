package com.sqldomaingen.model;

import lombok.*;

/**
 * Represents a database relationship between two tables.
 */
@Builder
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

    @Builder.Default
    private String joinTableName = "";

    @Builder.Default
    private String inverseJoinColumn = "";

    @Builder.Default
    private RelationshipType relationshipType = RelationshipType.MANYTOONE;

    /**
     * Custom constructor to allow direct instantiation with required fields.
     */
    public Relationship(String sourceTable, String sourceColumn, String targetTable, String targetColumn) {
        this.sourceTable = sourceTable;
        this.sourceColumn = sourceColumn;
        this.targetTable = targetTable;
        this.targetColumn = targetColumn;
        this.relationshipType = RelationshipType.MANYTOONE; // Προεπιλεγμένος τύπος
    }

    public enum RelationshipType {
        ONETOONE,
        MANYTOONE,
        ONETOMANY,
        MANYTOMANY
    }
}
