package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.mapper.AuditTrailMapper;
import gr.knowledge.pepTest.entity.AuditTrail;
import gr.knowledge.pepTest.repository.AuditTrailRepository;
import gr.knowledge.pepTest.service.AuditTrailService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Audit Trail} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRepository auditTrailRepository;
    private final AuditTrailMapper auditTrailMapper;

    /**
     * Retrieves all audit trails records.
     * @return list of AuditTrailDto
     */
    @Override
    public List<AuditTrailDto> getAllAuditTrails() {
        log.info("Fetching all audit trails records.");
        return auditTrailMapper.toDTOList(auditTrailRepository.findAll());
    }

    /**
     * Retrieves a audit trail record by id.
     * @param id the audit trail id
     * @return AuditTrailDto
     */
    @Override
    public AuditTrailDto getAuditTrailById(UUID id) {
        log.info("Fetching audit trail with id: {}", id);

        AuditTrail existingEntity = findAuditTrailByIdOrThrow(id);
        return auditTrailMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new audit trail record.
     * @param dto input payload
     * @return created {@link AuditTrailDto}
     */
    @Override
    public AuditTrailDto createAuditTrail(AuditTrailDto dto) {
        log.info("Creating audit trail.");

        AuditTrail entity = auditTrailMapper.toEntity(dto);
        AuditTrail savedEntity = auditTrailRepository.save(entity);

        return auditTrailMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing audit trail record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the audit trail id
     * @param dto input payload with partial fields
     * @return updated {@link AuditTrailDto}
     */
    @Override
    public AuditTrailDto updateAuditTrail(UUID id, AuditTrailDto dto) {
        log.info("Updating audit trail with id: {}", id);

        AuditTrail existingEntity = findAuditTrailByIdOrThrow(id);
        auditTrailMapper.partialUpdate(existingEntity, dto);
        AuditTrail savedEntity = auditTrailRepository.save(existingEntity);

        return auditTrailMapper.toDTO(savedEntity);
    }

    /**
     * Delete a audit trail record by id.
     * @param id the audit trail id
     */
    @Override
    public void deleteAuditTrail(UUID id) {
        log.info("Deleting audit trail with id: {}", id);

        findAuditTrailByIdOrThrow(id);
        auditTrailRepository.deleteById(id);
    }

    /**
     * Finds an existing audit trail record by id or throws an exception.
     * @param id the audit trail id
     * @return existing AuditTrail entity
     */
    private AuditTrail findAuditTrailByIdOrThrow(UUID id) {
        return auditTrailRepository.findById(id)
                .orElseThrow(() -> createAuditTrailNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the audit trail entity.
     @param id the audit trail id
     @return runtime exception
     */
    private RuntimeException createAuditTrailNotFoundException(UUID id) {
        log.warn("AuditTrail not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("AuditTrail")
                .message("AuditTrail not found with id: " + id)
                .build();
    }

}
