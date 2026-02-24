package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ProfessionKind} domain operations.
 */
public interface ProfessionKindService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionKindDto}
     */
    List<ProfessionKindDto> getAllProfessionKind();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionKindDto}
     */
    ProfessionKindDto getProfessionKindById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionKindDto}
     */
    ProfessionKindDto createProfessionKind(ProfessionKindDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionKindDto}
     */
    ProfessionKindDto updateProfessionKind(UUID id, ProfessionKindDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfessionKind(UUID id);
}
