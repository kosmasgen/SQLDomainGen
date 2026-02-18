package gr.knowledge.schoolmanagement.serviceImpl;

import gr.knowledge.schoolmanagement.dto.CourseDto;
import gr.knowledge.schoolmanagement.mapper.CourseMapper;
import gr.knowledge.schoolmanagement.entity.Course;
import gr.knowledge.schoolmanagement.repository.CourseRepository;
import gr.knowledge.schoolmanagement.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Course} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CourseDto}
     */
    @Override
    public List<CourseDto> getAllCourse() {
        log.info("Fetching all course.");
        return courseMapper.toDTO(courseRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CourseDto}
     */
    @Override
    public CourseDto getCourseById(Long id) {
        log.info("Fetching course with id: {}", id);
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
        return courseMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CourseDto}
     */
    @Override
    public CourseDto createCourse(CourseDto dto) {
        log.info("Creating course.");
        Course entity = courseMapper.toEntity(dto);
        Course saved = courseRepository.save(entity);
        return courseMapper.toDTO(saved);
    }

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
    @Override
    public CourseDto updateCourse(Long id, CourseDto dto) {
        log.info("Updating course with id: {}", id);
        courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
        Course entity = courseMapper.toEntity(dto);
        entity.setId(id);
        Course saved = courseRepository.save(entity);
        return courseMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting course with id: {}", id);
        courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
        courseRepository.deleteById(id);
    }
}
