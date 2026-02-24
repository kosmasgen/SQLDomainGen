package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyFile} domain operations.
 */
public interface CompanyFileService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyFileDto}
     */
    List<CompanyFileDto> getAllCompanyFile();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyFileDto}
     */
    CompanyFileDto getCompanyFileById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyFileDto}
     */
    CompanyFileDto createCompanyFile(CompanyFileDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyFileDto}
     */
    CompanyFileDto updateCompanyFile(UUID id, CompanyFileDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyFile(UUID id);
}
