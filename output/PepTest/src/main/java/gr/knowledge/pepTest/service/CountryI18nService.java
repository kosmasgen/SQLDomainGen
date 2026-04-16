package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CountryI18n} domain operations.
 */
public interface CountryI18nService {

    /**
     * Retrieves all country i18ns.
     * @return non-null list of {@link CountryI18nDto}
     */
    List<CountryI18nDto> getAllCountryI18ns();

    /**
     * Retrieves a record by id.
     * @param countryId the country_id value
     * @param languageId the language_id value
     * @return the matching {@link CountryI18nDto}
     */
    CountryI18nDto getCountryI18nById(UUID countryId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CountryI18nDto}
     */
    CountryI18nDto createCountryI18n(CountryI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param countryId the country_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link CountryI18nDto}
     */
    CountryI18nDto updateCountryI18n(UUID countryId, UUID languageId, CountryI18nDto dto);

    /**
     * Deletes a record by id.
     * @param countryId the country_id value
     * @param languageId the language_id value
     */
    void deleteCountryI18n(UUID countryId, UUID languageId);
}
