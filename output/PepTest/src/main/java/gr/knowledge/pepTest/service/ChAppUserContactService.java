package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ChAppUserContact} domain operations.
 */
public interface ChAppUserContactService {

    /**
     * Retrieves all ch app user contacts.
     * @return non-null list of {@link ChAppUserContactDto}
     */
    List<ChAppUserContactDto> getAllChAppUserContacts();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ChAppUserContactDto}
     */
    ChAppUserContactDto getChAppUserContactById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ChAppUserContactDto}
     */
    ChAppUserContactDto createChAppUserContact(ChAppUserContactDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ChAppUserContactDto}
     */
    ChAppUserContactDto updateChAppUserContact(UUID id, ChAppUserContactDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteChAppUserContact(UUID id);
}
