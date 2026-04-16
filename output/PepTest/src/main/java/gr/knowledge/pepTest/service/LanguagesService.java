package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.LanguagesDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Languages} domain operations.
 */
public interface LanguagesService {

    /**
     * Retrieves all languageses.
     * @return non-null list of {@link LanguagesDto}
     */
    List<LanguagesDto> getAllLanguageses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link LanguagesDto}
     */
    LanguagesDto getLanguagesById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link LanguagesDto}
     */
    LanguagesDto createLanguages(LanguagesDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link LanguagesDto}
     */
    LanguagesDto updateLanguages(UUID id, LanguagesDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteLanguages(UUID id);
}
