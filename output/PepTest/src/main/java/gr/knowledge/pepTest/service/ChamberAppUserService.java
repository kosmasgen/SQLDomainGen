package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ChamberAppUser} domain operations.
 */
public interface ChamberAppUserService {

    /**
     * Retrieves all chamber app users.
     * @return non-null list of {@link ChamberAppUserDto}
     */
    List<ChamberAppUserDto> getAllChamberAppUsers();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ChamberAppUserDto}
     */
    ChamberAppUserDto getChamberAppUserById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ChamberAppUserDto}
     */
    ChamberAppUserDto createChamberAppUser(ChamberAppUserDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ChamberAppUserDto}
     */
    ChamberAppUserDto updateChamberAppUser(UUID id, ChamberAppUserDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteChamberAppUser(UUID id);
}
