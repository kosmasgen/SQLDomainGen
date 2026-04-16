package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code AuditTrail} domain operations.
 */
public interface AuditTrailService {

    /**
     * Retrieves all audit trails.
     * @return non-null list of {@link AuditTrailDto}
     */
    List<AuditTrailDto> getAllAuditTrails();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link AuditTrailDto}
     */
    AuditTrailDto getAuditTrailById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link AuditTrailDto}
     */
    AuditTrailDto createAuditTrail(AuditTrailDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link AuditTrailDto}
     */
    AuditTrailDto updateAuditTrail(UUID id, AuditTrailDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteAuditTrail(UUID id);
}
