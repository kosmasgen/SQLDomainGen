package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code UserContactinfo} domain operations.
 */
public interface UserContactinfoService {

    /**
     * Retrieves all user contactinfos.
     * @return non-null list of {@link UserContactinfoDto}
     */
    List<UserContactinfoDto> getAllUserContactinfos();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link UserContactinfoDto}
     */
    UserContactinfoDto getUserContactinfoById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link UserContactinfoDto}
     */
    UserContactinfoDto createUserContactinfo(UserContactinfoDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link UserContactinfoDto}
     */
    UserContactinfoDto updateUserContactinfo(UUID id, UserContactinfoDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteUserContactinfo(UUID id);
}
