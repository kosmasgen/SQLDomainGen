package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CorporateStatus} domain operations.
 */
public interface CorporateStatusService {

    /**
     * Retrieves all corporate statuses.
     * @return non-null list of {@link CorporateStatusDto}
     */
    List<CorporateStatusDto> getAllCorporateStatuses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CorporateStatusDto}
     */
    CorporateStatusDto getCorporateStatusById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CorporateStatusDto}
     */
    CorporateStatusDto createCorporateStatus(CorporateStatusDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusDto}
     */
    CorporateStatusDto updateCorporateStatus(UUID id, CorporateStatusDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCorporateStatus(UUID id);
}
