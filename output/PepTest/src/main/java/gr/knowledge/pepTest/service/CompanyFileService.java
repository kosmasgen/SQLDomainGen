package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyFile} domain operations.
 */
public interface CompanyFileService {

    /**
     * Retrieves all company files.
     * @return non-null list of {@link CompanyFileDto}
     */
    List<CompanyFileDto> getAllCompanyFiles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyFileDto}
     */
    CompanyFileDto getCompanyFileById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyFileDto}
     */
    CompanyFileDto createCompanyFile(CompanyFileDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyFileDto}
     */
    CompanyFileDto updateCompanyFile(UUID id, CompanyFileDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyFile(UUID id);
}
