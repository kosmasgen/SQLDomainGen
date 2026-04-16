package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Company} domain operations.
 */
public interface CompanyService {

    /**
     * Retrieves all companies.
     * @return non-null list of {@link CompanyDto}
     */
    List<CompanyDto> getAllCompanies();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyDto}
     */
    CompanyDto getCompanyById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyDto}
     */
    CompanyDto createCompany(CompanyDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyDto}
     */
    CompanyDto updateCompany(UUID id, CompanyDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompany(UUID id);
}
