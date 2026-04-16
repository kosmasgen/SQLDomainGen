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
@Table(name = "company_profession", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_company_profession_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_company_profession_id")
    private Integer chamberCompanyProfessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_kind_id")
    private ProfessionKind professionKind;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private CompanyProfile profile;

    @Column(name = "gemi_id")
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDateTime gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDateTime gemiLastUpdated;

}
