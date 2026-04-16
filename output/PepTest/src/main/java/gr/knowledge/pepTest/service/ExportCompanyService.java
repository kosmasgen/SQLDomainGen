package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ExportCompany} domain operations.
 */
public interface ExportCompanyService {

    /**
     * Retrieves all export companies.
     * @return non-null list of {@link ExportCompanyDto}
     */
    List<ExportCompanyDto> getAllExportCompanies();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ExportCompanyDto}
     */
    ExportCompanyDto getExportCompanyById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ExportCompanyDto}
     */
    ExportCompanyDto createExportCompany(ExportCompanyDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ExportCompanyDto}
     */
    ExportCompanyDto updateExportCompany(UUID id, ExportCompanyDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteExportCompany(UUID id);
}
