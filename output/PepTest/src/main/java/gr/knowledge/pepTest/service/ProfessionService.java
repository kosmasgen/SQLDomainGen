package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Profession} domain operations.
 */
public interface ProfessionService {

    /**
     * Retrieves all professions.
     * @return non-null list of {@link ProfessionDto}
     */
    List<ProfessionDto> getAllProfessions();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ProfessionDto}
     */
    ProfessionDto getProfessionById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProfessionDto}
     */
    ProfessionDto createProfession(ProfessionDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionDto}
     */
    ProfessionDto updateProfession(UUID id, ProfessionDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteProfession(UUID id);
}
