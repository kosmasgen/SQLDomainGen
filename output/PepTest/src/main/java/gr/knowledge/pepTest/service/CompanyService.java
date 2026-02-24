package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Company} domain operations.
 */
public interface CompanyService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyDto}
     */
    List<CompanyDto> getAllCompany();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyDto}
     */
    CompanyDto getCompanyById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyDto}
     */
    CompanyDto createCompany(CompanyDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyDto}
     */
    CompanyDto updateCompany(UUID id, CompanyDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompany(UUID id);
}
