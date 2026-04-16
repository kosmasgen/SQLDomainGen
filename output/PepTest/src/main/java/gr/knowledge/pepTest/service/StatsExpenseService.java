package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code StatsExpense} domain operations.
 */
public interface StatsExpenseService {

    /**
     * Retrieves all stats expenses.
     * @return non-null list of {@link StatsExpenseDto}
     */
    List<StatsExpenseDto> getAllStatsExpenses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link StatsExpenseDto}
     */
    StatsExpenseDto getStatsExpenseById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link StatsExpenseDto}
     */
    StatsExpenseDto createStatsExpense(StatsExpenseDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link StatsExpenseDto}
     */
    StatsExpenseDto updateStatsExpense(UUID id, StatsExpenseDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteStatsExpense(UUID id);
}
