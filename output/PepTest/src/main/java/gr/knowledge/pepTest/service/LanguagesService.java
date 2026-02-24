package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.LanguagesDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Languages} domain operations.
 */
public interface LanguagesService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link LanguagesDto}
     */
    List<LanguagesDto> getAllLanguages();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link LanguagesDto}
     */
    LanguagesDto getLanguagesById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link LanguagesDto}
     */
    LanguagesDto createLanguages(LanguagesDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link LanguagesDto}
     */
    LanguagesDto updateLanguages(UUID id, LanguagesDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteLanguages(UUID id);
}
