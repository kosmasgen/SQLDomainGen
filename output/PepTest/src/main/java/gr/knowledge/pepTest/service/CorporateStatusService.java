package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CorporateStatus} domain operations.
 */
public interface CorporateStatusService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusDto}
     */
    List<CorporateStatusDto> getAllCorporateStatus();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusDto}
     */
    CorporateStatusDto getCorporateStatusById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusDto}
     */
    CorporateStatusDto createCorporateStatus(CorporateStatusDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusDto}
     */
    CorporateStatusDto updateCorporateStatus(UUID id, CorporateStatusDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCorporateStatus(UUID id);
}
