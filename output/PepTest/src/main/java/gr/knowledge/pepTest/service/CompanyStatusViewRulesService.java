package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyStatusViewRules} domain operations.
 */
public interface CompanyStatusViewRulesService {

    /**
     * Retrieves all company status view ruleses.
     * @return non-null list of {@link CompanyStatusViewRulesDto}
     */
    List<CompanyStatusViewRulesDto> getAllCompanyStatusViewRuleses();

    /**
     * Retrieves a record by id.
     * @param companyStatusId the company_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     * @return the matching {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto getCompanyStatusViewRulesById(UUID companyStatusId, UUID companyViewRulesId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto createCompanyStatusViewRules(CompanyStatusViewRulesDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyStatusId the company_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto updateCompanyStatusViewRules(UUID companyStatusId, UUID companyViewRulesId, CompanyStatusViewRulesDto dto);

    /**
     * Deletes a record by id.
     * @param companyStatusId the company_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     */
    void deleteCompanyStatusViewRules(UUID companyStatusId, UUID companyViewRulesId);
}
