package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyStatus} domain operations.
 */
public interface CompanyStatusService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusDto}
     */
    List<CompanyStatusDto> getAllCompanyStatus();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusDto}
     */
    CompanyStatusDto getCompanyStatusById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusDto}
     */
    CompanyStatusDto createCompanyStatus(CompanyStatusDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusDto}
     */
    CompanyStatusDto updateCompanyStatus(UUID id, CompanyStatusDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyStatus(UUID id);
}
