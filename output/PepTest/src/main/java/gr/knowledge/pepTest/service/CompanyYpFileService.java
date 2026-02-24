package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyYpFile} domain operations.
 */
public interface CompanyYpFileService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpFileDto}
     */
    List<CompanyYpFileDto> getAllCompanyYpFile();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpFileDto}
     */
    CompanyYpFileDto getCompanyYpFileById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpFileDto}
     */
    CompanyYpFileDto createCompanyYpFile(CompanyYpFileDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpFileDto}
     */
    CompanyYpFileDto updateCompanyYpFile(UUID id, CompanyYpFileDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyYpFile(UUID id);
}
