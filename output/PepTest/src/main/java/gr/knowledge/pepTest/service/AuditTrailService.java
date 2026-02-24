package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code AuditTrail} domain operations.
 */
public interface AuditTrailService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link AuditTrailDto}
     */
    List<AuditTrailDto> getAllAuditTrail();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link AuditTrailDto}
     */
    AuditTrailDto getAuditTrailById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link AuditTrailDto}
     */
    AuditTrailDto createAuditTrail(AuditTrailDto dto);

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
    AuditTrailDto updateAuditTrail(UUID id, AuditTrailDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteAuditTrail(UUID id);
}
