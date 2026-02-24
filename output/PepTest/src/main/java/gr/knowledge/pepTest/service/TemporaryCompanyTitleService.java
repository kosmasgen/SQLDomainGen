package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import java.util.List;

/**
 * Service contract for {@code TemporaryCompanyTitle} domain operations.
 */
public interface TemporaryCompanyTitleService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyTitleDto}
     */
    List<TemporaryCompanyTitleDto> getAllTemporaryCompanyTitle();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto getTemporaryCompanyTitleById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto createTemporaryCompanyTitle(TemporaryCompanyTitleDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto updateTemporaryCompanyTitle(Long id, TemporaryCompanyTitleDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTemporaryCompanyTitle(Long id);
}
