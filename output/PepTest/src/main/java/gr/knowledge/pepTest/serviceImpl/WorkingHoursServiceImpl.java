package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.mapper.WorkingHoursMapper;
import gr.knowledge.pepTest.entity.WorkingHours;
import gr.knowledge.pepTest.repository.WorkingHoursRepository;
import gr.knowledge.pepTest.service.WorkingHoursService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Working Hours} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;
    private final WorkingHoursMapper workingHoursMapper;

    /**
     * Retrieves all working hourses records.
     * @return list of WorkingHoursDto
     */
    @Override
    public List<WorkingHoursDto> getAllWorkingHourses() {
        log.info("Fetching all working hourses records.");
        return workingHoursMapper.toDTOList(workingHoursRepository.findAll());
    }

    /**
     * Retrieves a working hours record by id.
     * @param id the working hours id
     * @return WorkingHoursDto
     */
    @Override
    public WorkingHoursDto getWorkingHoursById(Long id) {
        log.info("Fetching working hours with id: {}", id);

        WorkingHours existingEntity = findWorkingHoursByIdOrThrow(id);
        return workingHoursMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new working hours record.
     * @param dto input payload
     * @return created {@link WorkingHoursDto}
     */
    @Override
    public WorkingHoursDto createWorkingHours(WorkingHoursDto dto) {
        log.info("Creating working hours.");

        WorkingHours entity = workingHoursMapper.toEntity(dto);
        WorkingHours savedEntity = workingHoursRepository.save(entity);

        return workingHoursMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing working hours record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the working hours id
     * @param dto input payload with partial fields
     * @return updated {@link WorkingHoursDto}
     */
    @Override
    public WorkingHoursDto updateWorkingHours(Long id, WorkingHoursDto dto) {
        log.info("Updating working hours with id: {}", id);

        WorkingHours existingEntity = findWorkingHoursByIdOrThrow(id);
        workingHoursMapper.partialUpdate(existingEntity, dto);
        WorkingHours savedEntity = workingHoursRepository.save(existingEntity);

        return workingHoursMapper.toDTO(savedEntity);
    }

    /**
     * Delete a working hours record by id.
     * @param id the working hours id
     */
    @Override
    public void deleteWorkingHours(Long id) {
        log.info("Deleting working hours with id: {}", id);

        findWorkingHoursByIdOrThrow(id);
        workingHoursRepository.deleteById(id);
    }

    /**
     * Finds an existing working hours record by id or throws an exception.
     * @param id the working hours id
     * @return existing WorkingHours entity
     */
    private WorkingHours findWorkingHoursByIdOrThrow(Long id) {
        return workingHoursRepository.findById(id)
                .orElseThrow(() -> createWorkingHoursNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the working hours entity.
     @param id the working hours id
     @return runtime exception
     */
    private RuntimeException createWorkingHoursNotFoundException(Long id) {
        log.warn("WorkingHours not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("WorkingHours")
                .message("WorkingHours not found with id: " + id)
                .build();
    }

}
