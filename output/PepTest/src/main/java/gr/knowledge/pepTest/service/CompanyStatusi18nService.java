package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyStatusi18n} domain operations.
 */
public interface CompanyStatusi18nService {

    /**
     * Retrieves all company statusi18ns.
     * @return non-null list of {@link CompanyStatusi18nDto}
     */
    List<CompanyStatusi18nDto> getAllCompanyStatusi18ns();

    /**
     * Retrieves a record by id.
     * @param companyStatusId the company_status_id value
     * @param languageId the language_id value
     * @return the matching {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto getCompanyStatusi18nById(UUID companyStatusId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto createCompanyStatusi18n(CompanyStatusi18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyStatusId the company_status_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusi18nDto}
     */
    CompanyStatusi18nDto updateCompanyStatusi18n(UUID companyStatusId, UUID languageId, CompanyStatusi18nDto dto);

    /**
     * Deletes a record by id.
     * @param companyStatusId the company_status_id value
     * @param languageId the language_id value
     */
    void deleteCompanyStatusi18n(UUID companyStatusId, UUID languageId);
}
