package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Companyi18n} domain operations.
 */
public interface Companyi18nService {

    /**
     * Retrieves all companyi18ns.
     * @return non-null list of {@link Companyi18nDto}
     */
    List<Companyi18nDto> getAllCompanyi18ns();

    /**
     * Retrieves a record by id.
     * @param companyId the company_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     * @return the matching {@link Companyi18nDto}
     */
    Companyi18nDto getCompanyi18nById(UUID companyId, UUID languageId, Integer chamberI18nId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link Companyi18nDto}
     */
    Companyi18nDto createCompanyi18n(Companyi18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param companyId the company_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     * @param dto input payload with partial fields
     * @return updated {@link Companyi18nDto}
     */
    Companyi18nDto updateCompanyi18n(UUID companyId, UUID languageId, Integer chamberI18nId, Companyi18nDto dto);

    /**
     * Deletes a record by id.
     * @param companyId the company_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     */
    void deleteCompanyi18n(UUID companyId, UUID languageId, Integer chamberI18nId);
}
