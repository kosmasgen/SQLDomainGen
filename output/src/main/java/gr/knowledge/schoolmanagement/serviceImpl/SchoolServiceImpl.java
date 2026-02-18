package gr.knowledge.schoolmanagement.serviceImpl;

import gr.knowledge.schoolmanagement.dto.SchoolDto;
import gr.knowledge.schoolmanagement.mapper.SchoolMapper;
import gr.knowledge.schoolmanagement.entity.School;
import gr.knowledge.schoolmanagement.repository.SchoolRepository;
import gr.knowledge.schoolmanagement.service.SchoolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code School} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SchoolDto}
     */
    @Override
    public List<SchoolDto> getAllSchool() {
        log.info("Fetching all school.");
        return schoolMapper.toDTO(schoolRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SchoolDto}
     */
    @Override
    public SchoolDto getSchoolById(Long id) {
        log.info("Fetching school with id: {}", id);
        School entity = schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found with id: " + id));
        return schoolMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SchoolDto}
     */
    @Override
    public SchoolDto createSchool(SchoolDto dto) {
        log.info("Creating school.");
        School entity = schoolMapper.toEntity(dto);
        School saved = schoolRepository.save(entity);
        return schoolMapper.toDTO(saved);
    }

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
    @Override
    public SchoolDto updateSchool(Long id, SchoolDto dto) {
        log.info("Updating school with id: {}", id);
        schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found with id: " + id));
        School entity = schoolMapper.toEntity(dto);
        entity.setId(id);
        School saved = schoolRepository.save(entity);
        return schoolMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteSchool(Long id) {
        log.info("Deleting school with id: {}", id);
        schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found with id: " + id));
        schoolRepository.deleteById(id);
    }
}
