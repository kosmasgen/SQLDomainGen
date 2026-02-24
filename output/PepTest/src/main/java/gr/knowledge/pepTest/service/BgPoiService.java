package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BgPoiDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code BgPoi} domain operations.
 */
public interface BgPoiService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BgPoiDto}
     */
    List<BgPoiDto> getAllBgPoi();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BgPoiDto}
     */
    BgPoiDto getBgPoiById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BgPoiDto}
     */
    BgPoiDto createBgPoi(BgPoiDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BgPoiDto}
     */
    BgPoiDto updateBgPoi(UUID id, BgPoiDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteBgPoi(UUID id);
}
