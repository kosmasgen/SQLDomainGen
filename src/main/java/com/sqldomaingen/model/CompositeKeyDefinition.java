package com.sqldomaingen.model;

import lombok.Data;

import java.util.List;

/**
 * Represents a composite primary key definition.
 */
@Data
public class CompositeKeyDefinition {

    private String name;
    private List<Column> columns;
}
