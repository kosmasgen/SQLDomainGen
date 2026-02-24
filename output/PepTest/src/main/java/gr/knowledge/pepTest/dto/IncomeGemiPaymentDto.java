package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object for IncomeGemiPayment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeGemiPaymentDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer chamberId;

    @Size(max = 255)
    private String paymentType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTs;

    @Positive
    private BigDecimal chamberAmount;

    @Positive
    private BigDecimal chamberAmountForCerts;

    @Positive
    private BigDecimal chamberAmountForPostal;

    @Positive
    private BigDecimal totalAmountPaid;

    @Size(max = 255)
    private String descr;

    @Size(max = 255)
    private String payer;

    @NotNull
    private BigDecimal gemiPaymentId;

    private BigDecimal companyGemiId;

    @Size(max = 255)
    private String coName;

    private BigDecimal companyChamberId;

    @NotNull
    @Size(max = 255)
    private String paymentMethod;

    @Size(max = 255)
    private String ri3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionEndDate;

    private BigDecimal cancelFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime remittanceDt;

    @Positive
    private BigDecimal remittanceAmount;

    @Size(max = 255)
    private String remittanceReference;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

}
