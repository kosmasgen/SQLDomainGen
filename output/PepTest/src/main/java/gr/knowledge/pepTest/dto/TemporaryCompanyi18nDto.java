package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Data transfer object for TemporaryCompanyi18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyi18nDto {

    private BigInteger id;

    @NotNull
    private BigInteger version;

    @Size(max = 50)
    private String city;

    @Size(max = 1000)
    private String coName;

    @NotNull
    private TemporaryCompanyDto company;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull
    private LanguagesDto language;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @Size(max = 60)
    private String mailName;

    private String objective;

    @NotNull
    private BigInteger recdeleted;

    @Size(max = 60)
    private String street;

    private String comments;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

    @Size(max = 24)
    private String gemiCity;

    private String article;

}
