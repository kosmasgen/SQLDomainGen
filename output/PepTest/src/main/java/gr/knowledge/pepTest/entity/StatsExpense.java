package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "stats_expense", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "account_sum_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "account_sum_id", nullable = false)
    private BigInteger accountSumId;

    @Column(name = "cd_use", length = 4, nullable = false)
    private String cdUse;

    @Column(name = "group_descr", length = 300)
    private String groupDescr;

    @Column(name = "mm", length = 2, nullable = false)
    private String mm;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted")
    private BigInteger recdeleted;

}
