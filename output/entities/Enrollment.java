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

}
