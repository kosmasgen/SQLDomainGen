package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code UserContactinfo} domain operations.
 */
public interface UserContactinfoService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link UserContactinfoDto}
     */
    List<UserContactinfoDto> getAllUserContactinfo();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link UserContactinfoDto}
     */
    UserContactinfoDto getUserContactinfoById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link UserContactinfoDto}
     */
    UserContactinfoDto createUserContactinfo(UserContactinfoDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link UserContactinfoDto}
     */
    UserContactinfoDto updateUserContactinfo(UUID id, UserContactinfoDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteUserContactinfo(UUID id);
}
