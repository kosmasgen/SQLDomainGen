package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.entity.CompanyProfileI18nPK;
import java.util.List;

/**
 * Service contract for {@code CompanyProfileI18n} domain operations.
 */
public interface CompanyProfileI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfileI18nDto}
     */
    List<CompanyProfileI18nDto> getAllCompanyProfileI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto getCompanyProfileI18nById(CompanyProfileI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto createCompanyProfileI18n(CompanyProfileI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto updateCompanyProfileI18n(CompanyProfileI18nPK id, CompanyProfileI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyProfileI18n(CompanyProfileI18nPK id);
}
