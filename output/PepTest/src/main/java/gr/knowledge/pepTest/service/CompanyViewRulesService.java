package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyViewRules} domain operations.
 */
public interface CompanyViewRulesService {

    /**
     * Retrieves all company view ruleses.
     * @return non-null list of {@link CompanyViewRulesDto}
     */
    List<CompanyViewRulesDto> getAllCompanyViewRuleses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto getCompanyViewRulesById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto createCompanyViewRules(CompanyViewRulesDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto updateCompanyViewRules(UUID id, CompanyViewRulesDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyViewRules(UUID id);
}
