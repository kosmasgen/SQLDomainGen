package com.sqldomaingen.model;

import lombok.Data;
import java.util.List;

@Data
public class EntityModel {
    private String name;
    private List<Field> fields;
}
