package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ChAppUserContact} domain operations.
 */
public interface ChAppUserContactService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChAppUserContactDto}
     */
    List<ChAppUserContactDto> getAllChAppUserContact();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChAppUserContactDto}
     */
    ChAppUserContactDto getChAppUserContactById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChAppUserContactDto}
     */
    ChAppUserContactDto createChAppUserContact(ChAppUserContactDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChAppUserContactDto}
     */
    ChAppUserContactDto updateChAppUserContact(UUID id, ChAppUserContactDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteChAppUserContact(UUID id);
}
