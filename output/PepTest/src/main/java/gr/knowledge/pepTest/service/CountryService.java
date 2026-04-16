package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CountryDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Country} domain operations.
 */
public interface CountryService {

    /**
     * Retrieves all countries.
     * @return non-null list of {@link CountryDto}
     */
    List<CountryDto> getAllCountries();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CountryDto}
     */
    CountryDto getCountryById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CountryDto}
     */
    CountryDto createCountry(CountryDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CountryDto}
     */
    CountryDto updateCountry(UUID id, CountryDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCountry(UUID id);
}
