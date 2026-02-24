package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code IncomeGemiPayment} domain operations.
 */
public interface IncomeGemiPaymentService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeGemiPaymentDto}
     */
    List<IncomeGemiPaymentDto> getAllIncomeGemiPayment();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto getIncomeGemiPaymentById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto createIncomeGemiPayment(IncomeGemiPaymentDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeGemiPaymentDto}
     */
    IncomeGemiPaymentDto updateIncomeGemiPayment(UUID id, IncomeGemiPaymentDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteIncomeGemiPayment(UUID id);
}
