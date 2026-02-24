package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import java.util.List;

/**
 * Service contract for {@code Syncruns} domain operations.
 */
public interface SyncrunsService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncrunsDto}
     */
    List<SyncrunsDto> getAllSyncruns();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncrunsDto}
     */
    SyncrunsDto getSyncrunsById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncrunsDto}
     */
    SyncrunsDto createSyncruns(SyncrunsDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncrunsDto}
     */
    SyncrunsDto updateSyncruns(Long id, SyncrunsDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteSyncruns(Long id);
}
