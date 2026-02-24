package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesPK;
import java.util.List;

/**
 * Service contract for {@code CompanyStatusViewRules} domain operations.
 */
public interface CompanyStatusViewRulesService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusViewRulesDto}
     */
    List<CompanyStatusViewRulesDto> getAllCompanyStatusViewRules();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto getCompanyStatusViewRulesById(CompanyStatusViewRulesPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto createCompanyStatusViewRules(CompanyStatusViewRulesDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusViewRulesDto}
     */
    CompanyStatusViewRulesDto updateCompanyStatusViewRules(CompanyStatusViewRulesPK id, CompanyStatusViewRulesDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyStatusViewRules(CompanyStatusViewRulesPK id);
}
