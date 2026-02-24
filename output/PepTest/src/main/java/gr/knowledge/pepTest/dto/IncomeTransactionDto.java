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
 * Data transfer object for IncomeTransaction.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeTransactionDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private BigDecimal chamberInTransdId;

    @NotNull
    @Size(max = 255)
    private String cdUse;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;

    private Integer isMember;

    private UUID companyId;

    @Size(max = 255)
    private String accountCd;

    private UUID incomeTypeId;

    @Positive
    private BigDecimal amount;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private BigDecimal recDeleted;

    private UUID incomePayMethodId;

    private Integer isEchamber;

    @Size(max = 255)
    private String blockSer;

    private BigDecimal isKratisi;

    private BigDecimal chamberCompId;

    private BigDecimal chamberMethod;

    private BigDecimal chamberType;

}
