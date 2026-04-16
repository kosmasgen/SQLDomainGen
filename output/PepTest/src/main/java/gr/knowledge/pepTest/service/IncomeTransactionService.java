package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code IncomeTransaction} domain operations.
 */
public interface IncomeTransactionService {

    /**
     * Retrieves all income transactions.
     * @return non-null list of {@link IncomeTransactionDto}
     */
    List<IncomeTransactionDto> getAllIncomeTransactions();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link IncomeTransactionDto}
     */
    IncomeTransactionDto getIncomeTransactionById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link IncomeTransactionDto}
     */
    IncomeTransactionDto createIncomeTransaction(IncomeTransactionDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link IncomeTransactionDto}
     */
    IncomeTransactionDto updateIncomeTransaction(UUID id, IncomeTransactionDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteIncomeTransaction(UUID id);
}
