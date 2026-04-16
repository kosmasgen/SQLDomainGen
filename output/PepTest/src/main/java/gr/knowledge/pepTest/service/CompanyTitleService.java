package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyTitle} domain operations.
 */
public interface CompanyTitleService {

    /**
     * Retrieves all company titles.
     * @return non-null list of {@link CompanyTitleDto}
     */
    List<CompanyTitleDto> getAllCompanyTitles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyTitleDto}
     */
    CompanyTitleDto getCompanyTitleById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyTitleDto}
     */
    CompanyTitleDto createCompanyTitle(CompanyTitleDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyTitleDto}
     */
    CompanyTitleDto updateCompanyTitle(UUID id, CompanyTitleDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyTitle(UUID id);
}
