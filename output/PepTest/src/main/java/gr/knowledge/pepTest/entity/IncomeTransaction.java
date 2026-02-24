package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "income_transaction")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTransaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_in_transd_id", nullable = false)
    private BigDecimal chamberInTransdId;

    @Column(name = "cd_use", length = 255, nullable = false)
    private String cdUse;

    @Column(name = "dt", nullable = false)
    private LocalDateTime dt;

    @Column(name = "is_member")
    private Integer isMember;

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "account_cd", length = 255)
    private String accountCd;

    @Column(name = "income_type_id")
    private UUID incomeTypeId;

    @Column(name = "amount")
    private BigDecimal amount;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "rec_deleted", nullable = false)
    private BigDecimal recDeleted;

    @Column(name = "income_pay_method_id")
    private UUID incomePayMethodId;

    @Column(name = "is_echamber")
    private Integer isEchamber;

    @Column(name = "block_ser", length = 255)
    private String blockSer;

    @Column(name = "is_kratisi")
    private BigDecimal isKratisi;

    @Column(name = "chamber_comp_id")
    private BigDecimal chamberCompId;

    @Column(name = "chamber_method")
    private BigDecimal chamberMethod;

    @Column(name = "chamber_type")
    private BigDecimal chamberType;

}
