package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import java.util.List;

/**
 * Service contract for {@code SyncWatermarks} domain operations.
 */
public interface SyncWatermarksService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncWatermarksDto}
     */
    List<SyncWatermarksDto> getAllSyncWatermarks();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncWatermarksDto}
     */
    SyncWatermarksDto getSyncWatermarksById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncWatermarksDto}
     */
    SyncWatermarksDto createSyncWatermarks(SyncWatermarksDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncWatermarksDto}
     */
    SyncWatermarksDto updateSyncWatermarks(Long id, SyncWatermarksDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteSyncWatermarks(Long id);
}
