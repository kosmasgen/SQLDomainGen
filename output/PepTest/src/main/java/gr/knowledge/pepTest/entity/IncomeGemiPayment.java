package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "income_gemi_payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeGemiPayment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "payment_type", length = 255)
    private String paymentType;

    @Column(name = "sale_ts")
    private LocalDateTime saleTs;

    @Column(name = "chamber_amount")
    private BigDecimal chamberAmount;

    @Column(name = "chamber_amount_for_certs")
    private BigDecimal chamberAmountForCerts;

    @Column(name = "chamber_amount_for_postal")
    private BigDecimal chamberAmountForPostal;

    @Column(name = "total_amount_paid")
    private BigDecimal totalAmountPaid;

    @Column(name = "descr", length = 255)
    private String descr;

    @Column(name = "payer", length = 255)
    private String payer;

    @Column(name = "gemi_payment_id", nullable = false)
    private BigDecimal gemiPaymentId;

    @Column(name = "company_gemi_id")
    private BigDecimal companyGemiId;

    @Column(name = "co_name", length = 255)
    private String coName;

    @Column(name = "company_chamber_id")
    private BigDecimal companyChamberId;

    @Column(name = "payment_method", length = 255, nullable = false)
    private String paymentMethod;

    @Column(name = "ri3", length = 255)
    private String ri3;

    @Column(name = "subscription_start_date")
    private LocalDateTime subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private LocalDateTime subscriptionEndDate;

    @Column(name = "cancel_flag")
    private BigDecimal cancelFlag;

    @Column(name = "refund_ts")
    private LocalDateTime refundTs;

    @Column(name = "remittance_dt")
    private LocalDateTime remittanceDt;

    @Column(name = "remittance_amount")
    private BigDecimal remittanceAmount;

    @Column(name = "remittance_reference", length = 255)
    private String remittanceReference;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}
