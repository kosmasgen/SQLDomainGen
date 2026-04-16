package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import java.util.List;
import java.math.BigInteger;

/**
 * Service contract for {@code TemporaryCompanyi18n} domain operations.
 */
public interface TemporaryCompanyi18nService {

    /**
     * Retrieves all temporary companyi18ns.
     * @return non-null list of {@link TemporaryCompanyi18nDto}
     */
    List<TemporaryCompanyi18nDto> getAllTemporaryCompanyi18ns();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto getTemporaryCompanyi18nById(BigInteger id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto createTemporaryCompanyi18n(TemporaryCompanyi18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyi18nDto}
     */
    TemporaryCompanyi18nDto updateTemporaryCompanyi18n(BigInteger id, TemporaryCompanyi18nDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteTemporaryCompanyi18n(BigInteger id);
}
