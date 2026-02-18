package gr.knowledge.schoolmanagement.serviceImpl;

import gr.knowledge.schoolmanagement.dto.CourseStudentDto;
import gr.knowledge.schoolmanagement.mapper.CourseStudentMapper;
import gr.knowledge.schoolmanagement.entity.CourseStudent;
import gr.knowledge.schoolmanagement.repository.CourseStudentRepository;
import gr.knowledge.schoolmanagement.service.CourseStudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CourseStudent} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CourseStudentServiceImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final CourseStudentMapper courseStudentMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CourseStudentDto}
     */
    @Override
    public List<CourseStudentDto> getAllCourseStudent() {
        log.info("Fetching all course-student.");
        return courseStudentMapper.toDTO(courseStudentRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CourseStudentDto}
     */
    @Override
    public CourseStudentDto getCourseStudentById(Long id) {
        log.info("Fetching course-student with id: {}", id);
        CourseStudent entity = courseStudentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseStudent not found with id: " + id));
        return courseStudentMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CourseStudentDto}
     */
    @Override
    public CourseStudentDto createCourseStudent(CourseStudentDto dto) {
        log.info("Creating course-student.");
        CourseStudent entity = courseStudentMapper.toEntity(dto);
        CourseStudent saved = courseStudentRepository.save(entity);
        return courseStudentMapper.toDTO(saved);
    }

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
    @Override
    public CourseStudentDto updateCourseStudent(Long id, CourseStudentDto dto) {
        log.info("Updating course-student with id: {}", id);
        courseStudentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseStudent not found with id: " + id));
        CourseStudent entity = courseStudentMapper.toEntity(dto);
        entity.setId(id);
        CourseStudent saved = courseStudentRepository.save(entity);
        return courseStudentMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCourseStudent(Long id) {
        log.info("Deleting course-student with id: {}", id);
        courseStudentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseStudent not found with id: " + id));
        courseStudentRepository.deleteById(id);
    }
}
