package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code IncomePaymentMethod} domain operations.
 */
public interface IncomePaymentMethodService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomePaymentMethodDto}
     */
    List<IncomePaymentMethodDto> getAllIncomePaymentMethod();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto getIncomePaymentMethodById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto createIncomePaymentMethod(IncomePaymentMethodDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto updateIncomePaymentMethod(UUID id, IncomePaymentMethodDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteIncomePaymentMethod(UUID id);
}
