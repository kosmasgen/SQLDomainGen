package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ProfessionSystem} domain operations.
 */
public interface ProfessionSystemService {

    /**
     * Retrieves all profession systems.
     * @return non-null list of {@link ProfessionSystemDto}
     */
    List<ProfessionSystemDto> getAllProfessionSystems();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ProfessionSystemDto}
     */
    ProfessionSystemDto getProfessionSystemById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProfessionSystemDto}
     */
    ProfessionSystemDto createProfessionSystem(ProfessionSystemDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionSystemDto}
     */
    ProfessionSystemDto updateProfessionSystem(UUID id, ProfessionSystemDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteProfessionSystem(UUID id);
}
