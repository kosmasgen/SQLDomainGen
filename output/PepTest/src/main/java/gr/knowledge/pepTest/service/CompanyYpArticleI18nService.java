package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nPK;
import java.util.List;

/**
 * Service contract for {@code CompanyYpArticleI18n} domain operations.
 */
public interface CompanyYpArticleI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpArticleI18nDto}
     */
    List<CompanyYpArticleI18nDto> getAllCompanyYpArticleI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto getCompanyYpArticleI18nById(CompanyYpArticleI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto createCompanyYpArticleI18n(CompanyYpArticleI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto updateCompanyYpArticleI18n(CompanyYpArticleI18nPK id, CompanyYpArticleI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyYpArticleI18n(CompanyYpArticleI18nPK id);
}
