package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.mapper.AuditTrailMapper;
import gr.knowledge.pepTest.entity.AuditTrail;
import gr.knowledge.pepTest.repository.AuditTrailRepository;
import gr.knowledge.pepTest.service.AuditTrailService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code AuditTrail} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRepository auditTrailRepository;
    private final AuditTrailMapper auditTrailMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link AuditTrailDto}
     */
    @Override
    public List<AuditTrailDto> getAllAuditTrail() {
        log.info("Fetching all audit-trail.");
        return auditTrailMapper.toDTO(auditTrailRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link AuditTrailDto}
     */
    @Override
    public AuditTrailDto getAuditTrailById(UUID id) {
        log.info("Fetching audit-trail with id: {}", id);
        AuditTrail entity = auditTrailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AuditTrail not found with id: " + id));
        return auditTrailMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link AuditTrailDto}
     */
    @Override
    public AuditTrailDto createAuditTrail(AuditTrailDto dto) {
        log.info("Creating audit-trail.");
        AuditTrail entity = auditTrailMapper.toEntity(dto);
        AuditTrail saved = auditTrailRepository.save(entity);
        return auditTrailMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link AuditTrailDto}
     */
    @Override
    public AuditTrailDto updateAuditTrail(UUID id, AuditTrailDto dto) {
        log.info("Updating audit-trail with id: {}", id);
        auditTrailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AuditTrail not found with id: " + id));
        AuditTrail entity = auditTrailMapper.toEntity(dto);
        entity.setId(id);
        AuditTrail saved = auditTrailRepository.save(entity);
        return auditTrailMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteAuditTrail(UUID id) {
        log.info("Deleting audit-trail with id: {}", id);
        auditTrailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AuditTrail not found with id: " + id));
        auditTrailRepository.deleteById(id);
    }
}
