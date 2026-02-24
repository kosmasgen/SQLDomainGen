package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code IncomeType} domain operations.
 */
public interface IncomeTypeService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeTypeDto}
     */
    List<IncomeTypeDto> getAllIncomeType();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeTypeDto}
     */
    IncomeTypeDto getIncomeTypeById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeTypeDto}
     */
    IncomeTypeDto createIncomeType(IncomeTypeDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeTypeDto}
     */
    IncomeTypeDto updateIncomeType(UUID id, IncomeTypeDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteIncomeType(UUID id);
}
