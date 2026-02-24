package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import java.util.List;

/**
 * Service contract for {@code TemporaryCompanyi18n} domain operations.
 */
public interface TemporaryCompanyi18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyi18nDto}
     */
    List<TemporaryCompanyi18nDto> getAllTemporaryCompanyi18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto getTemporaryCompanyi18nById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto createTemporaryCompanyi18n(TemporaryCompanyi18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto updateTemporaryCompanyi18n(Long id, TemporaryCompanyi18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTemporaryCompanyi18n(Long id);
}
