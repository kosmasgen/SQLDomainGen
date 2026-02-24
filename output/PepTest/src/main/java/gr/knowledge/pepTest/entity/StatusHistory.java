package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "status_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_status_history_id", nullable = false)
    private BigDecimal chamberStatusHistoryId;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "company_status_id", nullable = false)
    private UUID companyStatusId;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "start_dt", nullable = false)
    private LocalDateTime startDt;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "gemi_date_created")
    private LocalDateTime gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDateTime gemiLastUpdated;

    @Column(name = "action_no", length = 255)
    private String actionNo;

}
