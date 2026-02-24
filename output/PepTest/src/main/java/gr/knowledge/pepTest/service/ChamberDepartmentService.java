package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ChamberDepartment} domain operations.
 */
public interface ChamberDepartmentService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberDepartmentDto}
     */
    List<ChamberDepartmentDto> getAllChamberDepartment();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto getChamberDepartmentById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto createChamberDepartment(ChamberDepartmentDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto updateChamberDepartment(UUID id, ChamberDepartmentDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteChamberDepartment(UUID id);
}
