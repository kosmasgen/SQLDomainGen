package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Profession} domain operations.
 */
public interface ProfessionService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionDto}
     */
    List<ProfessionDto> getAllProfession();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionDto}
     */
    ProfessionDto getProfessionById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionDto}
     */
    ProfessionDto createProfession(ProfessionDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionDto}
     */
    ProfessionDto updateProfession(UUID id, ProfessionDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfession(UUID id);
}
