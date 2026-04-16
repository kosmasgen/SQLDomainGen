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
@Table(name = "income_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_in_transd_id", "is_kratisi"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_in_transd_id", nullable = false)
    private BigInteger chamberInTransdId;

    @Column(name = "cd_use", length = 4, nullable = false)
    private String cdUse;

    @Column(name = "dt", nullable = false)
    private LocalDateTime dt;

    @Column(name = "is_member")
    private Integer isMember;

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "account_cd")
    private String accountCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_type_id")
    private IncomeType incomeType;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private BigInteger recdeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_pay_method_id")
    private IncomePaymentMethod incomePayMethod;

    @Column(name = "is_echamber")
    private Integer isEchamber;

    @Column(name = "block_ser", length = 3)
    private String blockSer;

    @Column(name = "is_kratisi")
    private BigInteger isKratisi;

    @Column(name = "chamber_comp_id")
    private BigInteger chamberCompId;

    @Column(name = "chamber_method")
    private BigInteger chamberMethod;

    @Column(name = "chamber_type")
    private BigInteger chamberType;

}
