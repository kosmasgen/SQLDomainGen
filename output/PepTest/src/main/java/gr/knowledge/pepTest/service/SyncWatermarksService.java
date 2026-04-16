package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import java.util.List;

/**
 * Service contract for {@code SyncWatermarks} domain operations.
 */
public interface SyncWatermarksService {

    /**
     * Retrieves all sync watermarkses.
     * @return non-null list of {@link SyncWatermarksDto}
     */
    List<SyncWatermarksDto> getAllSyncWatermarkses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link SyncWatermarksDto}
     */
    SyncWatermarksDto getSyncWatermarksById(Long id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link SyncWatermarksDto}
     */
    SyncWatermarksDto createSyncWatermarks(SyncWatermarksDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link SyncWatermarksDto}
     */
    SyncWatermarksDto updateSyncWatermarks(Long id, SyncWatermarksDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteSyncWatermarks(Long id);
}
