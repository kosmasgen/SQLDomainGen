package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ProfessionKind} domain operations.
 */
public interface ProfessionKindService {

    /**
     * Retrieves all profession kinds.
     * @return non-null list of {@link ProfessionKindDto}
     */
    List<ProfessionKindDto> getAllProfessionKinds();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ProfessionKindDto}
     */
    ProfessionKindDto getProfessionKindById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProfessionKindDto}
     */
    ProfessionKindDto createProfessionKind(ProfessionKindDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionKindDto}
     */
    ProfessionKindDto updateProfessionKind(UUID id, ProfessionKindDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteProfessionKind(UUID id);
}
