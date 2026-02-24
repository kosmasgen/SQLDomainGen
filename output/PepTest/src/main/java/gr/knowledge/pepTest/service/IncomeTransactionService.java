package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code IncomeTransaction} domain operations.
 */
public interface IncomeTransactionService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeTransactionDto}
     */
    List<IncomeTransactionDto> getAllIncomeTransaction();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeTransactionDto}
     */
    IncomeTransactionDto getIncomeTransactionById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeTransactionDto}
     */
    IncomeTransactionDto createIncomeTransaction(IncomeTransactionDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeTransactionDto}
     */
    IncomeTransactionDto updateIncomeTransaction(UUID id, IncomeTransactionDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteIncomeTransaction(UUID id);
}
