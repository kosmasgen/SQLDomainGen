package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "syncruns")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Syncruns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lastrun")
    private LocalDateTime lastrun;

    @Column(name = "trades_lastrun")
    private LocalDateTime tradesLastrun;

    @Column(name = "is_running")
    private Boolean isRunning;

}
