package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CountryDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Country} domain operations.
 */
public interface CountryService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CountryDto}
     */
    List<CountryDto> getAllCountry();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CountryDto}
     */
    CountryDto getCountryById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CountryDto}
     */
    CountryDto createCountry(CountryDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CountryDto}
     */
    CountryDto updateCountry(UUID id, CountryDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCountry(UUID id);
}
