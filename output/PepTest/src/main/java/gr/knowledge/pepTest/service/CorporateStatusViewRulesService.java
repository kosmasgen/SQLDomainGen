package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesPK;
import java.util.List;

/**
 * Service contract for {@code CorporateStatusViewRules} domain operations.
 */
public interface CorporateStatusViewRulesService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusViewRulesDto}
     */
    List<CorporateStatusViewRulesDto> getAllCorporateStatusViewRules();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto getCorporateStatusViewRulesById(CorporateStatusViewRulesPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto createCorporateStatusViewRules(CorporateStatusViewRulesDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusViewRulesDto}
     */
    CorporateStatusViewRulesDto updateCorporateStatusViewRules(CorporateStatusViewRulesPK id, CorporateStatusViewRulesDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCorporateStatusViewRules(CorporateStatusViewRulesPK id);
}
