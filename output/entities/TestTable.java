package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TestTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestTable {

    @Column(name = "id")
    private null id;

    @Column(name = "name")
    private null name;

}
