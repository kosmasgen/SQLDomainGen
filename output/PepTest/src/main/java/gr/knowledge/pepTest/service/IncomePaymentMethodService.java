package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code IncomePaymentMethod} domain operations.
 */
public interface IncomePaymentMethodService {

    /**
     * Retrieves all income payment methods.
     * @return non-null list of {@link IncomePaymentMethodDto}
     */
    List<IncomePaymentMethodDto> getAllIncomePaymentMethods();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto getIncomePaymentMethodById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto createIncomePaymentMethod(IncomePaymentMethodDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link IncomePaymentMethodDto}
     */
    IncomePaymentMethodDto updateIncomePaymentMethod(UUID id, IncomePaymentMethodDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteIncomePaymentMethod(UUID id);
}
