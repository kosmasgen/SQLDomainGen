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
public class Entity {
    private String name;
    private CompositeKeyDefinition compositeKey;
    private List<Field> fields = new ArrayList<>();
}
