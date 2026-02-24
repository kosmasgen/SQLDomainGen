package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import java.util.List;

/**
 * Service contract for {@code TemporaryCompanyTitlei18n} domain operations.
 */
public interface TemporaryCompanyTitlei18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyTitlei18nDto}
     */
    List<TemporaryCompanyTitlei18nDto> getAllTemporaryCompanyTitlei18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto getTemporaryCompanyTitlei18nById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto createTemporaryCompanyTitlei18n(TemporaryCompanyTitlei18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto updateTemporaryCompanyTitlei18n(Long id, TemporaryCompanyTitlei18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTemporaryCompanyTitlei18n(Long id);
}
