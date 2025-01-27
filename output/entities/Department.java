package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Column(name = "dept_id")
    private null deptId;

    @Column(name = "dept_name")
    private null deptName;

}
