package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code StatsExpense} domain operations.
 */
public interface StatsExpenseService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StatsExpenseDto}
     */
    List<StatsExpenseDto> getAllStatsExpense();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StatsExpenseDto}
     */
    StatsExpenseDto getStatsExpenseById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StatsExpenseDto}
     */
    StatsExpenseDto createStatsExpense(StatsExpenseDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link StatsExpenseDto}
     */
    StatsExpenseDto updateStatsExpense(UUID id, StatsExpenseDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteStatsExpense(UUID id);
}
