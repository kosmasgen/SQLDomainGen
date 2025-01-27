package com.sqldomaingen.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@ToString
public class Column {

    private String name;
    private String type;
    private int length;
    private boolean isIdentity;
    private String identityGeneration;
    private String sequenceName;
    private List<String> constraints = new ArrayList<>();
    private String defaultValue;
    private boolean primaryKey;
    private boolean unique;
    private boolean nullable;
    private boolean isDefaultExpression;
    private String defaultExpression;
    private String checkConstraint;
    private String relationshipType;
    private String targetTable;
    private String joinTableName;
    private String inverseJoinColumn;
    private boolean isRelationship;
    private String onUpdate;
    private String onDelete;
    private String generatedAs;
    private String javaType;
    private boolean foreignKey;
    private String formattedName;

    // Νέα πεδία για foreign key
    private String referencedTable; // Όνομα πίνακα αναφοράς
    private String referencedColumn; // Όνομα στήλης αναφοράς


    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type='" + javaType + '\'' +
                ", length=" + length +
                ", primaryKey=" + primaryKey +
                ", nullable=" + nullable +
                ", defaultValue='" + defaultValue + '\'' +
                ", unique=" + unique +
                '}';
    }

}
