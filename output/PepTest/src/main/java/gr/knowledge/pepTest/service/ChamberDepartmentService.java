package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ChamberDepartment} domain operations.
 */
public interface ChamberDepartmentService {

    /**
     * Retrieves all chamber departments.
     * @return non-null list of {@link ChamberDepartmentDto}
     */
    List<ChamberDepartmentDto> getAllChamberDepartments();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto getChamberDepartmentById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto createChamberDepartment(ChamberDepartmentDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ChamberDepartmentDto}
     */
    ChamberDepartmentDto updateChamberDepartment(UUID id, ChamberDepartmentDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteChamberDepartment(UUID id);
}
