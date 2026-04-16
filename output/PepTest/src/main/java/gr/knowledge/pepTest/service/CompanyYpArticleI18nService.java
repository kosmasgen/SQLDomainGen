package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyYpArticleI18n} domain operations.
 */
public interface CompanyYpArticleI18nService {

    /**
     * Retrieves all company yp article i18ns.
     * @return non-null list of {@link CompanyYpArticleI18nDto}
     */
    List<CompanyYpArticleI18nDto> getAllCompanyYpArticleI18ns();

    /**
     * Retrieves a record by id.
     * @param companyArticleId the company_article_id value
     * @param languageId the language_id value
     * @return the matching {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto getCompanyYpArticleI18nById(UUID companyArticleId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto createCompanyYpArticleI18n(CompanyYpArticleI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyArticleId the company_article_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpArticleI18nDto}
     */
    CompanyYpArticleI18nDto updateCompanyYpArticleI18n(UUID companyArticleId, UUID languageId, CompanyYpArticleI18nDto dto);

    /**
     * Deletes a record by id.
     * @param companyArticleId the company_article_id value
     * @param languageId the language_id value
     */
    void deleteCompanyYpArticleI18n(UUID companyArticleId, UUID languageId);
}
