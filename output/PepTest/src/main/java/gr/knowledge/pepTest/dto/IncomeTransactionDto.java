package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;

/**
 * Data transfer object for IncomeTransaction.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeTransactionDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private BigInteger chamberInTransdId;

    @NotNull
    @Size(max = 4)
    private String cdUse;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;

    private Integer isMember;

    private UUID companyId;

    @Size(max = 255)
    private String accountCd;

    private IncomeTypeDto incomeType;

    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private BigInteger recdeleted;

    private IncomePaymentMethodDto incomePayMethod;

    private Integer isEchamber;

    @Size(max = 3)
    private String blockSer;

    private BigInteger isKratisi;

    private BigInteger chamberCompId;

    private BigInteger chamberMethod;

    private BigInteger chamberType;

}
