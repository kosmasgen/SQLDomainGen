package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import java.util.List;
import java.math.BigInteger;

/**
 * Service contract for {@code TemporaryCompanyTitle} domain operations.
 */
public interface TemporaryCompanyTitleService {

    /**
     * Retrieves all temporary company titles.
     * @return non-null list of {@link TemporaryCompanyTitleDto}
     */
    List<TemporaryCompanyTitleDto> getAllTemporaryCompanyTitles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto getTemporaryCompanyTitleById(BigInteger id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto createTemporaryCompanyTitle(TemporaryCompanyTitleDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyTitleDto}
     */
    TemporaryCompanyTitleDto updateTemporaryCompanyTitle(BigInteger id, TemporaryCompanyTitleDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteTemporaryCompanyTitle(BigInteger id);
}
