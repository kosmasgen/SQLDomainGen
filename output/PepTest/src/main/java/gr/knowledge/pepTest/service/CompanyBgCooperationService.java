package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyBgCooperation} domain operations.
 */
public interface CompanyBgCooperationService {

    /**
     * Retrieves all company bg cooperations.
     * @return non-null list of {@link CompanyBgCooperationDto}
     */
    List<CompanyBgCooperationDto> getAllCompanyBgCooperations();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto getCompanyBgCooperationById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto createCompanyBgCooperation(CompanyBgCooperationDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto updateCompanyBgCooperation(UUID id, CompanyBgCooperationDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyBgCooperation(UUID id);
}
