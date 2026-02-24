package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyViewRules} domain operations.
 */
public interface CompanyViewRulesService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyViewRulesDto}
     */
    List<CompanyViewRulesDto> getAllCompanyViewRules();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto getCompanyViewRulesById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto createCompanyViewRules(CompanyViewRulesDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyViewRulesDto}
     */
    CompanyViewRulesDto updateCompanyViewRules(UUID id, CompanyViewRulesDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyViewRules(UUID id);
}
