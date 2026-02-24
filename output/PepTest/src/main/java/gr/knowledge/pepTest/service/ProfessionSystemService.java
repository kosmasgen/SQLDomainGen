package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ProfessionSystem} domain operations.
 */
public interface ProfessionSystemService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionSystemDto}
     */
    List<ProfessionSystemDto> getAllProfessionSystem();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionSystemDto}
     */
    ProfessionSystemDto getProfessionSystemById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionSystemDto}
     */
    ProfessionSystemDto createProfessionSystem(ProfessionSystemDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionSystemDto}
     */
    ProfessionSystemDto updateProfessionSystem(UUID id, ProfessionSystemDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfessionSystem(UUID id);
}
