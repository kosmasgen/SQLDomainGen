package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "professor")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "p_name", length = 100)
    private String pName;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

}
