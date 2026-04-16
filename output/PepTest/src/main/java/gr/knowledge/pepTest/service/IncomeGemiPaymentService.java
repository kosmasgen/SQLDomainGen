package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code IncomeGemiPayment} domain operations.
 */
public interface IncomeGemiPaymentService {

    /**
     * Retrieves all income gemi payments.
     * @return non-null list of {@link IncomeGemiPaymentDto}
     */
    List<IncomeGemiPaymentDto> getAllIncomeGemiPayments();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto getIncomeGemiPaymentById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto createIncomeGemiPayment(IncomeGemiPaymentDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto updateIncomeGemiPayment(UUID id, IncomeGemiPaymentDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteIncomeGemiPayment(UUID id);
}
