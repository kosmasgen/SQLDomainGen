package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code BusinessLocation} domain operations.
 */
public interface BusinessLocationService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BusinessLocationDto}
     */
    List<BusinessLocationDto> getAllBusinessLocation();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BusinessLocationDto}
     */
    BusinessLocationDto getBusinessLocationById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BusinessLocationDto}
     */
    BusinessLocationDto createBusinessLocation(BusinessLocationDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BusinessLocationDto}
     */
    BusinessLocationDto updateBusinessLocation(UUID id, BusinessLocationDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteBusinessLocation(UUID id);
}
