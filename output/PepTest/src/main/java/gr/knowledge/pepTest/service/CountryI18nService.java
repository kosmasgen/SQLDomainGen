package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.entity.CountryI18nPK;
import java.util.List;

/**
 * Service contract for {@code CountryI18n} domain operations.
 */
public interface CountryI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CountryI18nDto}
     */
    List<CountryI18nDto> getAllCountryI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CountryI18nDto}
     */
    CountryI18nDto getCountryI18nById(CountryI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CountryI18nDto}
     */
    CountryI18nDto createCountryI18n(CountryI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CountryI18nDto}
     */
    CountryI18nDto updateCountryI18n(CountryI18nPK id, CountryI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCountryI18n(CountryI18nPK id);
}
