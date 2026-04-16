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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for StatsExpense.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsExpenseDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private BigInteger accountSumId;

    @NotNull
    @Size(max = 4)
    private String cdUse;

    @Size(max = 300)
    private String groupDescr;

    @NotNull
    @Size(max = 2)
    private String mm;

    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private BigInteger recdeleted;

}
