package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BgPoiDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code BgPoi} domain operations.
 */
public interface BgPoiService {

    /**
     * Retrieves all bg pois.
     * @return non-null list of {@link BgPoiDto}
     */
    List<BgPoiDto> getAllBgPois();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link BgPoiDto}
     */
    BgPoiDto getBgPoiById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link BgPoiDto}
     */
    BgPoiDto createBgPoi(BgPoiDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link BgPoiDto}
     */
    BgPoiDto updateBgPoi(UUID id, BgPoiDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteBgPoi(UUID id);
}
