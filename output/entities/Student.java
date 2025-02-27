package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "s_name", length = 100)
    private String sName;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

}
