package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.SyncrunsErrorLogDto;
import java.util.List;

/**
 * Service contract for {@code SyncrunsErrorLog} domain operations.
 */
public interface SyncrunsErrorLogService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncrunsErrorLogDto}
     */
    List<SyncrunsErrorLogDto> getAllSyncrunsErrorLog();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncrunsErrorLogDto}
     */
    SyncrunsErrorLogDto getSyncrunsErrorLogById(String id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncrunsErrorLogDto}
     */
    SyncrunsErrorLogDto createSyncrunsErrorLog(SyncrunsErrorLogDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncrunsErrorLogDto}
     */
    SyncrunsErrorLogDto updateSyncrunsErrorLog(String id, SyncrunsErrorLogDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteSyncrunsErrorLog(String id);
}
