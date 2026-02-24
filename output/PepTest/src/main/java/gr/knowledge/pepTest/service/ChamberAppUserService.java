package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ChamberAppUser} domain operations.
 */
public interface ChamberAppUserService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberAppUserDto}
     */
    List<ChamberAppUserDto> getAllChamberAppUser();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberAppUserDto}
     */
    ChamberAppUserDto getChamberAppUserById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberAppUserDto}
     */
    ChamberAppUserDto createChamberAppUser(ChamberAppUserDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberAppUserDto}
     */
    ChamberAppUserDto updateChamberAppUser(UUID id, ChamberAppUserDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteChamberAppUser(UUID id);
}
