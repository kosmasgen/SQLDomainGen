package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyProfile} domain operations.
 */
public interface CompanyProfileService {

    /**
     * Retrieves all company profiles.
     * @return non-null list of {@link CompanyProfileDto}
     */
    List<CompanyProfileDto> getAllCompanyProfiles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyProfileDto}
     */
    CompanyProfileDto getCompanyProfileById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyProfileDto}
     */
    CompanyProfileDto createCompanyProfile(CompanyProfileDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyProfileDto}
     */
    CompanyProfileDto updateCompanyProfile(UUID id, CompanyProfileDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyProfile(UUID id);
}
