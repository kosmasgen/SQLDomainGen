package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "profession_system", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_prof_system_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_prof_system_id")
    private Integer chamberProfSystemId;

    @Column(name = "cd", nullable = false)
    private String cd;

    @Column(name = "description", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

}
