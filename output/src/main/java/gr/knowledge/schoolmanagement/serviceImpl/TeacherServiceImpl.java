package gr.knowledge.schoolmanagement.serviceImpl;

import gr.knowledge.schoolmanagement.dto.TeacherDto;
import gr.knowledge.schoolmanagement.mapper.TeacherMapper;
import gr.knowledge.schoolmanagement.entity.Teacher;
import gr.knowledge.schoolmanagement.repository.TeacherRepository;
import gr.knowledge.schoolmanagement.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Teacher} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TeacherDto}
     */
    @Override
    public List<TeacherDto> getAllTeacher() {
        log.info("Fetching all teacher.");
        return teacherMapper.toDTO(teacherRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TeacherDto}
     */
    @Override
    public TeacherDto getTeacherById(Long id) {
        log.info("Fetching teacher with id: {}", id);
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found with id: " + id));
        return teacherMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TeacherDto}
     */
    @Override
    public TeacherDto createTeacher(TeacherDto dto) {
        log.info("Creating teacher.");
        Teacher entity = teacherMapper.toEntity(dto);
        Teacher saved = teacherRepository.save(entity);
        return teacherMapper.toDTO(saved);
    }

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
    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto dto) {
        log.info("Updating teacher with id: {}", id);
        teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found with id: " + id));
        Teacher entity = teacherMapper.toEntity(dto);
        entity.setId(id);
        Teacher saved = teacherRepository.save(entity);
        return teacherMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTeacher(Long id) {
        log.info("Deleting teacher with id: {}", id);
        teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found with id: " + id));
        teacherRepository.deleteById(id);
    }
}
