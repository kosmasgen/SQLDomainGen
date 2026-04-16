package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "municipality", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_municipality_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id")
    private Long chamberId;

    @Column(name = "chamber_municipality_id")
    private Long chamberMunicipalityId;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted")
    private Boolean recdeleted;

    @Column(name = "cd", nullable = false)
    private String cd;

    @Column(name = "is_proteas_data")
    private Boolean isProteasData;

}
