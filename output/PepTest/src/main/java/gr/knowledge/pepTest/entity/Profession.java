package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "profession", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_profession_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_profession_id")
    private Integer chamberProfessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_profession_id")
    private Profession parentProfession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_system_id", nullable = false)
    private ProfessionSystem professionSystem;

    @Column(name = "code", nullable = false)
    private String code;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "proteas_id", precision = 19)
    private BigInteger proteasId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendly_cat_id")
    private ProfessionFriendlyCategory friendlyCat;

}
