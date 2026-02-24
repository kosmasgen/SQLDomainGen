package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.mapper.WorkingHoursMapper;
import gr.knowledge.pepTest.entity.WorkingHours;
import gr.knowledge.pepTest.repository.WorkingHoursRepository;
import gr.knowledge.pepTest.service.WorkingHoursService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code WorkingHours} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;
    private final WorkingHoursMapper workingHoursMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link WorkingHoursDto}
     */
    @Override
    public List<WorkingHoursDto> getAllWorkingHours() {
        log.info("Fetching all working-hours.");
        return workingHoursMapper.toDTO(workingHoursRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link WorkingHoursDto}
     */
    @Override
    public WorkingHoursDto getWorkingHoursById(Long id) {
        log.info("Fetching working-hours with id: {}", id);
        WorkingHours entity = workingHoursRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WorkingHours not found with id: " + id));
        return workingHoursMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link WorkingHoursDto}
     */
    @Override
    public WorkingHoursDto createWorkingHours(WorkingHoursDto dto) {
        log.info("Creating working-hours.");
        WorkingHours entity = workingHoursMapper.toEntity(dto);
        WorkingHours saved = workingHoursRepository.save(entity);
        return workingHoursMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link WorkingHoursDto}
     */
    @Override
    public WorkingHoursDto updateWorkingHours(Long id, WorkingHoursDto dto) {
        log.info("Updating working-hours with id: {}", id);
        workingHoursRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WorkingHours not found with id: " + id));
        WorkingHours entity = workingHoursMapper.toEntity(dto);
        entity.setId(id);
        WorkingHours saved = workingHoursRepository.save(entity);
        return workingHoursMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteWorkingHours(Long id) {
        log.info("Deleting working-hours with id: {}", id);
        workingHoursRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WorkingHours not found with id: " + id));
        workingHoursRepository.deleteById(id);
    }
}
