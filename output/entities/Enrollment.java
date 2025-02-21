package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

}
