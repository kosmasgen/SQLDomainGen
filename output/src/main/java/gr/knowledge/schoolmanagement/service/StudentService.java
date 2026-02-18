package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.dto.StudentDto;
import java.util.List;

/**
 * Service contract for {@code Student} domain operations.
 */
public interface StudentService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StudentDto}
     */
    List<StudentDto> getAllStudent();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StudentDto}
     */
    StudentDto getStudentById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StudentDto}
     */
    StudentDto createStudent(StudentDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link StudentDto}
     */
    StudentDto updateStudent(Long id, StudentDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteStudent(Long id);
}
