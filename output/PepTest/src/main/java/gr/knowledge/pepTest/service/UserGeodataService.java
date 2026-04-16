package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code UserGeodata} domain operations.
 */
public interface UserGeodataService {

    /**
     * Retrieves all user geodatas.
     * @return non-null list of {@link UserGeodataDto}
     */
    List<UserGeodataDto> getAllUserGeodatas();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link UserGeodataDto}
     */
    UserGeodataDto getUserGeodataById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link UserGeodataDto}
     */
    UserGeodataDto createUserGeodata(UserGeodataDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link UserGeodataDto}
     */
    UserGeodataDto updateUserGeodata(UUID id, UserGeodataDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteUserGeodata(UUID id);
}
