package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Column(name = "id")
    private null id;

    @Column(name = "name")
    private null name;

    @Column(name = "salary")
    private null salary;

}
