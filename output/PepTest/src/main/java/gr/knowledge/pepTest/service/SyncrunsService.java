package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import java.util.List;

/**
 * Service contract for {@code Syncruns} domain operations.
 */
public interface SyncrunsService {

    /**
     * Retrieves all syncrunses.
     * @return non-null list of {@link SyncrunsDto}
     */
    List<SyncrunsDto> getAllSyncrunses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link SyncrunsDto}
     */
    SyncrunsDto getSyncrunsById(Long id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link SyncrunsDto}
     */
    SyncrunsDto createSyncruns(SyncrunsDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link SyncrunsDto}
     */
    SyncrunsDto updateSyncruns(Long id, SyncrunsDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteSyncruns(Long id);
}
