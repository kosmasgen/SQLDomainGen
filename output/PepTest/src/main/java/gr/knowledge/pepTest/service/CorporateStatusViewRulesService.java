package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CorporateStatusViewRules} domain operations.
 */
public interface CorporateStatusViewRulesService {

    /**
     * Retrieves all corporate status view ruleses.
     * @return non-null list of {@link CorporateStatusViewRulesDto}
     */
    List<CorporateStatusViewRulesDto> getAllCorporateStatusViewRuleses();

    /**
     * Retrieves a record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     * @return the matching {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto getCorporateStatusViewRulesById(UUID corporateStatusId, UUID companyViewRulesId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto createCorporateStatusViewRules(CorporateStatusViewRulesDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param corporateStatusId the corporate_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto updateCorporateStatusViewRules(UUID corporateStatusId, UUID companyViewRulesId, CorporateStatusViewRulesDto dto);

    /**
     * Deletes a record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     */
    void deleteCorporateStatusViewRules(UUID corporateStatusId, UUID companyViewRulesId);
}
