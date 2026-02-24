package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import java.util.List;

/**
 * Service contract for {@code WorkingHours} domain operations.
 */
public interface WorkingHoursService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link WorkingHoursDto}
     */
    List<WorkingHoursDto> getAllWorkingHours();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link WorkingHoursDto}
     */
    WorkingHoursDto getWorkingHoursById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link WorkingHoursDto}
     */
    WorkingHoursDto createWorkingHours(WorkingHoursDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link WorkingHoursDto}
     */
    WorkingHoursDto updateWorkingHours(Long id, WorkingHoursDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteWorkingHours(Long id);
}
