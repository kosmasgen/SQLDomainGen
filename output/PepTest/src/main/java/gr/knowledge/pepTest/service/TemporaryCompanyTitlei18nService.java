package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import java.util.List;
import java.math.BigInteger;

/**
 * Service contract for {@code TemporaryCompanyTitlei18n} domain operations.
 */
public interface TemporaryCompanyTitlei18nService {

    /**
     * Retrieves all temporary company titlei18ns.
     * @return non-null list of {@link TemporaryCompanyTitlei18nDto}
     */
    List<TemporaryCompanyTitlei18nDto> getAllTemporaryCompanyTitlei18ns();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto getTemporaryCompanyTitlei18nById(BigInteger id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto createTemporaryCompanyTitlei18n(TemporaryCompanyTitlei18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyTitlei18nDto}
     */
    TemporaryCompanyTitlei18nDto updateTemporaryCompanyTitlei18n(BigInteger id, TemporaryCompanyTitlei18nDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteTemporaryCompanyTitlei18n(BigInteger id);
}
