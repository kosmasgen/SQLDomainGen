package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.dto.CourseStudentDto;
import java.util.List;

/**
 * Service contract for {@code CourseStudent} domain operations.
 */
public interface CourseStudentService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CourseStudentDto}
     */
    List<CourseStudentDto> getAllCourseStudent();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CourseStudentDto}
     */
    CourseStudentDto getCourseStudentById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CourseStudentDto}
     */
    CourseStudentDto createCourseStudent(CourseStudentDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CourseStudentDto}
     */
    CourseStudentDto updateCourseStudent(Long id, CourseStudentDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCourseStudent(Long id);
}
