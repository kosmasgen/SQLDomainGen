package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for CorporateStatusViewRules.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorporateStatusViewRulesDto {

    private CorporateStatusViewRulesKey id;

    @NotNull
    private CorporateStatusDto corporateStatus;

    @NotNull
    private CompanyViewRulesDto companyViewRules;

    private Boolean excludeCompanies;

    private Boolean showContactInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

}
