package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Data transfer object for IncomeGemiPayment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeGemiPaymentDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @Size(max = 255)
    private String paymentType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTs;

    private BigDecimal chamberAmount;

    private BigDecimal chamberAmountForCerts;

    private BigDecimal chamberAmountForPostal;

    private BigDecimal totalAmountPaid;

    @Size(max = 500)
    private String descr;

    @Size(max = 500)
    private String payer;

    @NotNull
    private BigInteger gemiPaymentId;

    private BigInteger companyGemiId;

    @Size(max = 1000)
    private String coName;

    private BigInteger companyChamberId;

    @NotNull
    @Size(max = 255)
    private String paymentMethod;

    @Size(max = 25)
    private String ri3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionEndDate;

    private BigInteger cancelFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime remittanceDt;

    private BigDecimal remittanceAmount;

    @Size(max = 20)
    private String remittanceReference;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

}
