package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyProfile} domain operations.
 */
public interface CompanyProfileService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfileDto}
     */
    List<CompanyProfileDto> getAllCompanyProfile();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfileDto}
     */
    CompanyProfileDto getCompanyProfileById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfileDto}
     */
    CompanyProfileDto createCompanyProfile(CompanyProfileDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfileDto}
     */
    CompanyProfileDto updateCompanyProfile(UUID id, CompanyProfileDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyProfile(UUID id);
}
