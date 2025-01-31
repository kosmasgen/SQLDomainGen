package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cu_name", length = 100, nullable = false)
    private String cuName;

    @Column(name = "email", length = 150, unique = true, nullable = false)
    private String email;

    @Column(name = "birth_date")
    private java.time.LocalDate birthDate;

    @Column(name = "created_at", columnDefinition = "DEFAULT 'CURRENT_TIMESTAMP'")
    private java.time.LocalDateTime createdAt;

}
