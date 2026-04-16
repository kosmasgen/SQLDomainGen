package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Data transfer object for TemporaryCompanyProfession.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyProfessionDto {

    private BigInteger id;

    @NotNull
    private BigInteger version;

    @NotNull
    private BigInteger companyId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private BigInteger professionId;

    private BigInteger professionKindId;

    @NotNull
    private BigInteger recdeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

}
