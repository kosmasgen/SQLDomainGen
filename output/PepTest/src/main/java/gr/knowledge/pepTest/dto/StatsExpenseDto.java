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
 * Data transfer object for StatsExpense.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsExpenseDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private BigDecimal accountSumId;

    @NotNull
    @Size(max = 255)
    private String cdUse;

    @Size(max = 255)
    private String groupDescr;

    @NotNull
    @Size(max = 255)
    private String mm;

    @Positive
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private BigDecimal recDeleted;

}
