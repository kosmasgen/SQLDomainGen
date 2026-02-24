package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "stats_expense")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsExpense {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "account_sum_id", nullable = false)
    private BigDecimal accountSumId;

    @Column(name = "cd_use", length = 255, nullable = false)
    private String cdUse;

    @Column(name = "group_descr", length = 255)
    private String groupDescr;

    @Column(name = "mm", length = 255, nullable = false)
    private String mm;

    @Column(name = "amount")
    private BigDecimal amount;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "rec_deleted")
    private BigDecimal recDeleted;

}
