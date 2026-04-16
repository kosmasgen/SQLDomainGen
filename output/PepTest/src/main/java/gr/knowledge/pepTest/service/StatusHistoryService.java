package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code StatusHistory} domain operations.
 */
public interface StatusHistoryService {

    /**
     * Retrieves all status histories.
     * @return non-null list of {@link StatusHistoryDto}
     */
    List<StatusHistoryDto> getAllStatusHistories();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link StatusHistoryDto}
     */
    StatusHistoryDto getStatusHistoryById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link StatusHistoryDto}
     */
    StatusHistoryDto createStatusHistory(StatusHistoryDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link StatusHistoryDto}
     */
    StatusHistoryDto updateStatusHistory(UUID id, StatusHistoryDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteStatusHistory(UUID id);
}
