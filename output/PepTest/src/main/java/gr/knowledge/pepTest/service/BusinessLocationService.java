package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code BusinessLocation} domain operations.
 */
public interface BusinessLocationService {

    /**
     * Retrieves all business locations.
     * @return non-null list of {@link BusinessLocationDto}
     */
    List<BusinessLocationDto> getAllBusinessLocations();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link BusinessLocationDto}
     */
    BusinessLocationDto getBusinessLocationById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link BusinessLocationDto}
     */
    BusinessLocationDto createBusinessLocation(BusinessLocationDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link BusinessLocationDto}
     */
    BusinessLocationDto updateBusinessLocation(UUID id, BusinessLocationDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteBusinessLocation(UUID id);
}
