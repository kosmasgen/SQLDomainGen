package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyProfession} domain operations.
 */
public interface CompanyProfessionService {

    /**
     * Retrieves all company professions.
     * @return non-null list of {@link CompanyProfessionDto}
     */
    List<CompanyProfessionDto> getAllCompanyProfessions();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyProfessionDto}
     */
    CompanyProfessionDto getCompanyProfessionById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyProfessionDto}
     */
    CompanyProfessionDto createCompanyProfession(CompanyProfessionDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyProfessionDto}
     */
    CompanyProfessionDto updateCompanyProfession(UUID id, CompanyProfessionDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyProfession(UUID id);
}
