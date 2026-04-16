package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code IncomeType} domain operations.
 */
public interface IncomeTypeService {

    /**
     * Retrieves all income types.
     * @return non-null list of {@link IncomeTypeDto}
     */
    List<IncomeTypeDto> getAllIncomeTypes();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link IncomeTypeDto}
     */
    IncomeTypeDto getIncomeTypeById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link IncomeTypeDto}
     */
    IncomeTypeDto createIncomeType(IncomeTypeDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link IncomeTypeDto}
     */
    IncomeTypeDto updateIncomeType(UUID id, IncomeTypeDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteIncomeType(UUID id);
}
