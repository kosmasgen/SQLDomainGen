package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyProfileI18n} domain operations.
 */
public interface CompanyProfileI18nService {

    /**
     * Retrieves all company profile i18ns.
     * @return non-null list of {@link CompanyProfileI18nDto}
     */
    List<CompanyProfileI18nDto> getAllCompanyProfileI18ns();

    /**
     * Retrieves a record by id.
     * @param companyProfileId the company_profile_id value
     * @param languageId the language_id value
     * @return the matching {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto getCompanyProfileI18nById(UUID companyProfileId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto createCompanyProfileI18n(CompanyProfileI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyProfileId the company_profile_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyProfileI18nDto}
     */
    CompanyProfileI18nDto updateCompanyProfileI18n(UUID companyProfileId, UUID languageId, CompanyProfileI18nDto dto);

    /**
     * Deletes a record by id.
     * @param companyProfileId the company_profile_id value
     * @param languageId the language_id value
     */
    void deleteCompanyProfileI18n(UUID companyProfileId, UUID languageId);
}
