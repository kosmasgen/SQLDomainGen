package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.entity.CompanyStatusi18nPK;
import java.util.List;

/**
 * Service contract for {@code CompanyStatusi18n} domain operations.
 */
public interface CompanyStatusi18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusi18nDto}
     */
    List<CompanyStatusi18nDto> getAllCompanyStatusi18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto getCompanyStatusi18nById(CompanyStatusi18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto createCompanyStatusi18n(CompanyStatusi18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto updateCompanyStatusi18n(CompanyStatusi18nPK id, CompanyStatusi18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyStatusi18n(CompanyStatusi18nPK id);
}
