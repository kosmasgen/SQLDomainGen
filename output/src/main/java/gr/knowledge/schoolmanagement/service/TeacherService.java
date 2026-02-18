package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.dto.TeacherDto;
import java.util.List;

/**
 * Service contract for {@code Teacher} domain operations.
 */
public interface TeacherService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TeacherDto}
     */
    List<TeacherDto> getAllTeacher();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TeacherDto}
     */
    TeacherDto getTeacherById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TeacherDto}
     */
    TeacherDto createTeacher(TeacherDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TeacherDto}
     */
    TeacherDto updateTeacher(Long id, TeacherDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTeacher(Long id);
}
