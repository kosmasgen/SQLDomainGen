package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "syncruns")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Syncruns {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_run")
    private LocalDateTime lastRun;

    @Column(name = "trades_last_run")
    private LocalDateTime tradesLastRun;

    @Column(name = "is_running")
    private Boolean isRunning;

}
