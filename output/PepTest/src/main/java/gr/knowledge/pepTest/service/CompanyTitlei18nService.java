package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.entity.CompanyTitlei18nPK;
import java.util.List;

/**
 * Service contract for {@code CompanyTitlei18n} domain operations.
 */
public interface CompanyTitlei18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyTitlei18nDto}
     */
    List<CompanyTitlei18nDto> getAllCompanyTitlei18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto getCompanyTitlei18nById(CompanyTitlei18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto createCompanyTitlei18n(CompanyTitlei18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto updateCompanyTitlei18n(CompanyTitlei18nPK id, CompanyTitlei18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyTitlei18n(CompanyTitlei18nPK id);
}
