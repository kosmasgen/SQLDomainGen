package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyTitle} domain operations.
 */
public interface CompanyTitleService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyTitleDto}
     */
    List<CompanyTitleDto> getAllCompanyTitle();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyTitleDto}
     */
    CompanyTitleDto getCompanyTitleById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyTitleDto}
     */
    CompanyTitleDto createCompanyTitle(CompanyTitleDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyTitleDto}
     */
    CompanyTitleDto updateCompanyTitle(UUID id, CompanyTitleDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyTitle(UUID id);
}
