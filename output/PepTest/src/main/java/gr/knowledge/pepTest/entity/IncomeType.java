package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "income_type", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_type_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_type_id", nullable = false)
    private Integer chamberTypeId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted")
    private Boolean recdeleted;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

}
