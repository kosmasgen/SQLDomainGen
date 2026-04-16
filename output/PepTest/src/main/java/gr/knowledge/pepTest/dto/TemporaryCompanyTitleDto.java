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
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Data transfer object for TemporaryCompanyTitle.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyTitleDto {

    private BigInteger id;

    @NotNull
    private BigInteger version;

    private BigInteger companyId;

    private BigInteger companyPreregistrationId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private BigInteger orderSeq;

    @NotNull
    private BigInteger recdeleted;

    @Size(max = 1000)
    private String title;

    @Size(max = 255)
    private String titleLatin;

    @Size(max = 1000)
    private String titleNrm;

    private BigInteger titleStatusId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

}
