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
@Table(name = "income_gemi_payment", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "gemi_payment_id", "payment_type", "cancel_flag"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeGemiPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "sale_ts")
    private LocalDateTime saleTs;

    @Column(name = "chamber_amount", precision = 19, scale = 2)
    private BigDecimal chamberAmount;

    @Column(name = "chamber_amount_for_certs", precision = 19, scale = 2)
    private BigDecimal chamberAmountForCerts;

    @Column(name = "chamber_amount_for_postal", precision = 19, scale = 2)
    private BigDecimal chamberAmountForPostal;

    @Column(name = "total_amount_paid", precision = 19, scale = 2)
    private BigDecimal totalAmountPaid;

    @Column(name = "descr", length = 500)
    private String descr;

    @Column(name = "payer", length = 500)
    private String payer;

    @Column(name = "gemi_payment_id", nullable = false)
    private BigInteger gemiPaymentId;

    @Column(name = "company_gemi_id")
    private BigInteger companyGemiId;

    @Column(name = "co_name", length = 1000)
    private String coName;

    @Column(name = "company_chamber_id")
    private BigInteger companyChamberId;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "ri3", length = 25)
    private String ri3;

    @Column(name = "subscription_start_date")
    private LocalDateTime subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private LocalDateTime subscriptionEndDate;

    @Column(name = "cancel_flag")
    private BigInteger cancelFlag;

    @Column(name = "refund_ts")
    private LocalDateTime refundTs;

    @Column(name = "remittance_dt")
    private LocalDateTime remittanceDt;

    @Column(name = "remittance_amount", precision = 19, scale = 2)
    private BigDecimal remittanceAmount;

    @Column(name = "remittance_reference", length = 20)
    private String remittanceReference;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}
