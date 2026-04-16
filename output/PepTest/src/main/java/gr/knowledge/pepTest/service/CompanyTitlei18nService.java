package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyTitlei18n} domain operations.
 */
public interface CompanyTitlei18nService {

    /**
     * Retrieves all company titlei18ns.
     * @return non-null list of {@link CompanyTitlei18nDto}
     */
    List<CompanyTitlei18nDto> getAllCompanyTitlei18ns();

    /**
     * Retrieves a record by id.
     * @param companyTitleId the company_title_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     * @return the matching {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto getCompanyTitlei18nById(UUID companyTitleId, UUID languageId, Integer chamberI18nId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto createCompanyTitlei18n(CompanyTitlei18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyTitleId the company_title_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyTitlei18nDto}
     */
    CompanyTitlei18nDto updateCompanyTitlei18n(UUID companyTitleId, UUID languageId, Integer chamberI18nId, CompanyTitlei18nDto dto);

    /**
     * Deletes a record by id.
     * @param companyTitleId the company_title_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     */
    void deleteCompanyTitlei18n(UUID companyTitleId, UUID languageId, Integer chamberI18nId);
}
