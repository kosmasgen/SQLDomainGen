package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyStatus} domain operations.
 */
public interface CompanyStatusService {

    /**
     * Retrieves all company statuses.
     * @return non-null list of {@link CompanyStatusDto}
     */
    List<CompanyStatusDto> getAllCompanyStatuses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyStatusDto}
     */
    CompanyStatusDto getCompanyStatusById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyStatusDto}
     */
    CompanyStatusDto createCompanyStatus(CompanyStatusDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusDto}
     */
    CompanyStatusDto updateCompanyStatus(UUID id, CompanyStatusDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyStatus(UUID id);
}
