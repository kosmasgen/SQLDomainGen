package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.LanguagesDto;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for CompanyProfileI18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfileI18nDto {

    private CompanyProfileI18nKey id;

    @NotNull
    private CompanyProfileDto companyProfile;

    @NotNull
    private LanguagesDto language;

    @NotNull
    private Boolean recDeleted;

    @Size(max = 1000)
    private String name;

    @Size(max = 50)
    private String addressCity;

    @Size(max = 50)
    private String addressRegion;

    @Size(max = 100)
    private String addressStreet;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private String objective;

}
