package gr.knowledge.schoolmanagement.serviceImpl;

import gr.knowledge.schoolmanagement.dto.StudentDto;
import gr.knowledge.schoolmanagement.mapper.StudentMapper;
import gr.knowledge.schoolmanagement.entity.Student;
import gr.knowledge.schoolmanagement.repository.StudentRepository;
import gr.knowledge.schoolmanagement.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Student} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StudentDto}
     */
    @Override
    public List<StudentDto> getAllStudent() {
        log.info("Fetching all student.");
        return studentMapper.toDTO(studentRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StudentDto}
     */
    @Override
    public StudentDto getStudentById(Long id) {
        log.info("Fetching student with id: {}", id);
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
        return studentMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StudentDto}
     */
    @Override
    public StudentDto createStudent(StudentDto dto) {
        log.info("Creating student.");
        Student entity = studentMapper.toEntity(dto);
        Student saved = studentRepository.save(entity);
        return studentMapper.toDTO(saved);
    }

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
    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) {
        log.info("Updating student with id: {}", id);
        studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
        Student entity = studentMapper.toEntity(dto);
        entity.setId(id);
        Student saved = studentRepository.save(entity);
        return studentMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with id: {}", id);
        studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
        studentRepository.deleteById(id);
    }
}
