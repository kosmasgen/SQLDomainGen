package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code StatusHistory} domain operations.
 */
public interface StatusHistoryService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StatusHistoryDto}
     */
    List<StatusHistoryDto> getAllStatusHistory();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StatusHistoryDto}
     */
    StatusHistoryDto getStatusHistoryById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StatusHistoryDto}
     */
    StatusHistoryDto createStatusHistory(StatusHistoryDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link StatusHistoryDto}
     */
    StatusHistoryDto updateStatusHistory(UUID id, StatusHistoryDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteStatusHistory(UUID id);
}
