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
 * Data transfer object for TemporaryCompanyTitlei18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyTitlei18nDto {

    private BigInteger id;

    @NotNull
    private BigInteger version;

    @NotNull
    private TemporaryCompanyTitleDto companyTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull
    private LanguagesDto language;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private BigInteger recdeleted;

    @Size(max = 1000)
    private String title;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

}
