package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyProfession} domain operations.
 */
public interface CompanyProfessionService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfessionDto}
     */
    List<CompanyProfessionDto> getAllCompanyProfession();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfessionDto}
     */
    CompanyProfessionDto getCompanyProfessionById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfessionDto}
     */
    CompanyProfessionDto createCompanyProfession(CompanyProfessionDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfessionDto}
     */
    CompanyProfessionDto updateCompanyProfession(UUID id, CompanyProfessionDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyProfession(UUID id);
}
