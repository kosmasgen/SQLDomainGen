package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data transfer object for TemporaryCompanyi18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyi18nDto {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal version;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String coName;

    @NotNull
    private BigDecimal companyId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull
    private BigDecimal languageId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @Size(max = 255)
    private String mailName;

    @Size(max = 255)
    private String objective;

    @NotNull
    private BigDecimal recDeleted;

    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String comments;

    private BigDecimal gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

    @Size(max = 255)
    private String gemiCity;

    @Size(max = 255)
    private String article;

}
