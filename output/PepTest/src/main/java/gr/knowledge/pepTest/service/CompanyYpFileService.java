package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyYpFile} domain operations.
 */
public interface CompanyYpFileService {

    /**
     * Retrieves all company yp files.
     * @return non-null list of {@link CompanyYpFileDto}
     */
    List<CompanyYpFileDto> getAllCompanyYpFiles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyYpFileDto}
     */
    CompanyYpFileDto getCompanyYpFileById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyYpFileDto}
     */
    CompanyYpFileDto createCompanyYpFile(CompanyYpFileDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpFileDto}
     */
    CompanyYpFileDto updateCompanyYpFile(UUID id, CompanyYpFileDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyYpFile(UUID id);
}
