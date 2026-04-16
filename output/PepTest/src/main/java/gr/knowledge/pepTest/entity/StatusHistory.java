package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "status_history", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_status_history_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_status_history_id", nullable = false)
    private BigInteger chamberStatusHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_status_id", nullable = false)
    private CompanyStatus companyStatus;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "notes", length = 256)
    private String notes;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "start_dt", nullable = false)
    private LocalDateTime startDt;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "gemi_id")
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDateTime gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDateTime gemiLastUpdated;

    @Column(name = "action_no", length = 50)
    private String actionNo;

}
