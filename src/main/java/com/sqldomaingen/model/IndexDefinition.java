package com.sqldomaingen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexDefinition {

    private String name;
    private String tableName;
    private String whereClause;
    private String usingMethod;
    private List<String> columns = new ArrayList<>();
    private boolean unique;
}