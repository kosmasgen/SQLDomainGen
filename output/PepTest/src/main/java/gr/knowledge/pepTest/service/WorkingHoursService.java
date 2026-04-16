package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import java.util.List;

/**
 * Service contract for {@code WorkingHours} domain operations.
 */
public interface WorkingHoursService {

    /**
     * Retrieves all working hourses.
     * @return non-null list of {@link WorkingHoursDto}
     */
    List<WorkingHoursDto> getAllWorkingHourses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link WorkingHoursDto}
     */
    WorkingHoursDto getWorkingHoursById(Long id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link WorkingHoursDto}
     */
    WorkingHoursDto createWorkingHours(WorkingHoursDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link WorkingHoursDto}
     */
    WorkingHoursDto updateWorkingHours(Long id, WorkingHoursDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteWorkingHours(Long id);
}
