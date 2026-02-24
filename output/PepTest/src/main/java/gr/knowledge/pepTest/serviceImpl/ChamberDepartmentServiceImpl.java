package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.mapper.ChamberDepartmentMapper;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.repository.ChamberDepartmentRepository;
import gr.knowledge.pepTest.service.ChamberDepartmentService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ChamberDepartment} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberDepartmentServiceImpl implements ChamberDepartmentService {

    private final ChamberDepartmentRepository chamberDepartmentRepository;
    private final ChamberDepartmentMapper chamberDepartmentMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberDepartmentDto}
     */
    @Override
    public List<ChamberDepartmentDto> getAllChamberDepartment() {
        log.info("Fetching all chamber-department.");
        return chamberDepartmentMapper.toDTO(chamberDepartmentRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberDepartmentDto}
     */
    @Override
    public ChamberDepartmentDto getChamberDepartmentById(UUID id) {
        log.info("Fetching chamber-department with id: {}", id);
        ChamberDepartment entity = chamberDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartment not found with id: " + id));
        return chamberDepartmentMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberDepartmentDto}
     */
    @Override
    public ChamberDepartmentDto createChamberDepartment(ChamberDepartmentDto dto) {
        log.info("Creating chamber-department.");
        ChamberDepartment entity = chamberDepartmentMapper.toEntity(dto);
        ChamberDepartment saved = chamberDepartmentRepository.save(entity);
        return chamberDepartmentMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberDepartmentDto}
     */
    @Override
    public ChamberDepartmentDto updateChamberDepartment(UUID id, ChamberDepartmentDto dto) {
        log.info("Updating chamber-department with id: {}", id);
        chamberDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartment not found with id: " + id));
        ChamberDepartment entity = chamberDepartmentMapper.toEntity(dto);
        entity.setId(id);
        ChamberDepartment saved = chamberDepartmentRepository.save(entity);
        return chamberDepartmentMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteChamberDepartment(UUID id) {
        log.info("Deleting chamber-department with id: {}", id);
        chamberDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartment not found with id: " + id));
        chamberDepartmentRepository.deleteById(id);
    }
}
