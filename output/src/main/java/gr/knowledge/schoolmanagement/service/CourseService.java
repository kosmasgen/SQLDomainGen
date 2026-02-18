package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.dto.CourseDto;
import java.util.List;

/**
 * Service contract for {@code Course} domain operations.
 */
public interface CourseService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CourseDto}
     */
    List<CourseDto> getAllCourse();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CourseDto}
     */
    CourseDto getCourseById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CourseDto}
     */
    CourseDto createCourse(CourseDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CourseDto}
     */
    CourseDto updateCourse(Long id, CourseDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCourse(Long id);
}
