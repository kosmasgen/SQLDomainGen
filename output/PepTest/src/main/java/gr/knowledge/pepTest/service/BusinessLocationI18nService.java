package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code BusinessLocationI18n} domain operations.
 */
public interface BusinessLocationI18nService {

    /**
     * Retrieves all business location i18ns.
     * @return non-null list of {@link BusinessLocationI18nDto}
     */
    List<BusinessLocationI18nDto> getAllBusinessLocationI18ns();

    /**
     * Retrieves a record by id.
     * @param businessLocationId the business_location_id value
     * @param languageId the language_id value
     * @return the matching {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto getBusinessLocationI18nById(UUID businessLocationId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto createBusinessLocationI18n(BusinessLocationI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param businessLocationId the business_location_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto updateBusinessLocationI18n(UUID businessLocationId, UUID languageId, BusinessLocationI18nDto dto);

    /**
     * Deletes a record by id.
     * @param businessLocationId the business_location_id value
     * @param languageId the language_id value
     */
    void deleteBusinessLocationI18n(UUID businessLocationId, UUID languageId);
}
