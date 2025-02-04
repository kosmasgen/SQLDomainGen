package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Profiles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "created_at", columnDefinition = "DEFAULT 'CURRENT_TIMESTAMP'")
    private java.time.LocalDateTime createdAt;

}
