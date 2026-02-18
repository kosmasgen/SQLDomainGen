package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.dto.SchoolDto;
import java.util.List;

/**
 * Service contract for {@code School} domain operations.
 */
public interface SchoolService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SchoolDto}
     */
    List<SchoolDto> getAllSchool();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SchoolDto}
     */
    SchoolDto getSchoolById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SchoolDto}
     */
    SchoolDto createSchool(SchoolDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SchoolDto}
     */
    SchoolDto updateSchool(Long id, SchoolDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteSchool(Long id);
}
