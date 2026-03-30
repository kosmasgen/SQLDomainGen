package com.sqldomaingen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Stores metadata for a generated pure many-to-many relationship between two entities.
 * <p>
 * This model is used by the resolver/generator flow to describe both the owning
 * and inverse side of a many-to-many association created from a pure join table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManyToManyRelation {

    /**
     * The collection field name that will be generated in the entity.
     */
    private String fieldName;

    /**
     * The target entity class name of the relationship.
     */
    private String targetEntityName;

    /**
     * The physical join table name used in the generated {@code @JoinTable}.
     */
    private String joinTableName;

    /**
     * The join column name for the owning side.
     */
    private String joinColumnName;

    /**
     * The inverse join column name for the owning side.
     */
    private String inverseJoinColumnName;

    /**
     * The mappedBy field name used on the inverse side.
     */
    private String mappedBy;

    /**
     * Indicates whether this relation represents the owning side.
     */
    private boolean owningSide;
}