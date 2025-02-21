package com.sqldomaingen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private boolean unique;
    private boolean nullable = true;
    private Integer length;
    private String columnName;
    private String referencedColumn;
    private String referencedEntity;
    private String mappedBy;
    private String cascade;
    private boolean orphanRemoval;

    public boolean isRelationship() {
        return isForeignKey && referencedEntity != null && !referencedEntity.isBlank();
    }
}
