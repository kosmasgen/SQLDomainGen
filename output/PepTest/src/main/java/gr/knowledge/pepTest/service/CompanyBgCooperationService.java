package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyBgCooperation} domain operations.
 */
public interface CompanyBgCooperationService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyBgCooperationDto}
     */
    List<CompanyBgCooperationDto> getAllCompanyBgCooperation();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto getCompanyBgCooperationById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto createCompanyBgCooperation(CompanyBgCooperationDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyBgCooperationDto}
     */
    CompanyBgCooperationDto updateCompanyBgCooperation(UUID id, CompanyBgCooperationDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyBgCooperation(UUID id);
}
