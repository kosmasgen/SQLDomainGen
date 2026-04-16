package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CorporateStatusi18n} domain operations.
 */
public interface CorporateStatusi18nService {

    /**
     * Retrieves all corporate statusi18ns.
     * @return non-null list of {@link CorporateStatusi18nDto}
     */
    List<CorporateStatusi18nDto> getAllCorporateStatusi18ns();

    /**
     * Retrieves a record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param languageId the language_id value
     * @return the matching {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto getCorporateStatusi18nById(UUID corporateStatusId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto createCorporateStatusi18n(CorporateStatusi18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param corporateStatusId the corporate_status_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto updateCorporateStatusi18n(UUID corporateStatusId, UUID languageId, CorporateStatusi18nDto dto);

    /**
     * Deletes a record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param languageId the language_id value
     */
    void deleteCorporateStatusi18n(UUID corporateStatusId, UUID languageId);
}
