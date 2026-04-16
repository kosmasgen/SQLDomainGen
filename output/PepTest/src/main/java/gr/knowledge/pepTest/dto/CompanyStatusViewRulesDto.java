package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for CompanyStatusViewRules.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyStatusViewRulesDto {

    private CompanyStatusViewRulesKey id;

    @NotNull
    private CompanyStatusDto companyStatus;

    @NotNull
    private CompanyViewRulesDto companyViewRules;

    private Boolean excludeCompanies;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

}
