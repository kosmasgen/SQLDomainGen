package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.mapper.ChamberDepartmentMapper;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.repository.ChamberDepartmentRepository;
import gr.knowledge.pepTest.service.ChamberDepartmentService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Chamber Department} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberDepartmentServiceImpl implements ChamberDepartmentService {

    private final ChamberDepartmentRepository chamberDepartmentRepository;
    private final ChamberDepartmentMapper chamberDepartmentMapper;

    /**
     * Retrieves all chamber departments records.
     * @return list of ChamberDepartmentDto
     */
    @Override
    public List<ChamberDepartmentDto> getAllChamberDepartments() {
        log.info("Fetching all chamber departments records.");
        return chamberDepartmentMapper.toDTOList(chamberDepartmentRepository.findAll());
    }

    /**
     * Retrieves a chamber department record by id.
     * @param id the chamber department id
     * @return ChamberDepartmentDto
     */
    @Override
    public ChamberDepartmentDto getChamberDepartmentById(UUID id) {
        log.info("Fetching chamber department with id: {}", id);

        ChamberDepartment existingEntity = findChamberDepartmentByIdOrThrow(id);
        return chamberDepartmentMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new chamber department record.
     * @param dto input payload
     * @return created {@link ChamberDepartmentDto}
     */
    @Override
    public ChamberDepartmentDto createChamberDepartment(ChamberDepartmentDto dto) {
        log.info("Creating chamber department.");

        validateChamberDepartmentCreateUniqueConstraints(dto);

        ChamberDepartment entity = chamberDepartmentMapper.toEntity(dto);
        ChamberDepartment savedEntity = chamberDepartmentRepository.save(entity);

        return chamberDepartmentMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing chamber department record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the chamber department id
     * @param dto input payload with partial fields
     * @return updated {@link ChamberDepartmentDto}
     */
    @Override
    public ChamberDepartmentDto updateChamberDepartment(UUID id, ChamberDepartmentDto dto) {
        log.info("Updating chamber department with id: {}", id);

        ChamberDepartment existingEntity = findChamberDepartmentByIdOrThrow(id);
        chamberDepartmentMapper.partialUpdate(existingEntity, dto);
        ChamberDepartment savedEntity = chamberDepartmentRepository.save(existingEntity);

        return chamberDepartmentMapper.toDTO(savedEntity);
    }

    /**
     * Delete a chamber department record by id.
     * @param id the chamber department id
     */
    @Override
    public void deleteChamberDepartment(UUID id) {
        log.info("Deleting chamber department with id: {}", id);

        findChamberDepartmentByIdOrThrow(id);
        chamberDepartmentRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateChamberDepartmentCreateUniqueConstraints(ChamberDepartmentDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberDepartmentId() != null && chamberDepartmentRepository.existsByChamberIdAndChamberDepartmentId(dto.getChamberId(), dto.getChamberDepartmentId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ChamberDepartment")
                    .message("ChamberDepartment already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberDepartmentId=" + dto.getChamberDepartmentId())
                    .build();
        }
    }

    /**
     * Finds an existing chamber department record by id or throws an exception.
     * @param id the chamber department id
     * @return existing ChamberDepartment entity
     */
    private ChamberDepartment findChamberDepartmentByIdOrThrow(UUID id) {
        return chamberDepartmentRepository.findById(id)
                .orElseThrow(() -> createChamberDepartmentNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the chamber department entity.
     @param id the chamber department id
     @return runtime exception
     */
    private RuntimeException createChamberDepartmentNotFoundException(UUID id) {
        log.warn("ChamberDepartment not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberDepartment")
                .message("ChamberDepartment not found with id: " + id)
                .build();
    }

}
