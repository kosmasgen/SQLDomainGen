package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code UserGeodata} domain operations.
 */
public interface UserGeodataService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link UserGeodataDto}
     */
    List<UserGeodataDto> getAllUserGeodata();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link UserGeodataDto}
     */
    UserGeodataDto getUserGeodataById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link UserGeodataDto}
     */
    UserGeodataDto createUserGeodata(UserGeodataDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link UserGeodataDto}
     */
    UserGeodataDto updateUserGeodata(UUID id, UserGeodataDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteUserGeodata(UUID id);
}
