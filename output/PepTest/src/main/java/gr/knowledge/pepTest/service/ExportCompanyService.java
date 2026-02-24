package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ExportCompany} domain operations.
 */
public interface ExportCompanyService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ExportCompanyDto}
     */
    List<ExportCompanyDto> getAllExportCompany();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ExportCompanyDto}
     */
    ExportCompanyDto getExportCompanyById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ExportCompanyDto}
     */
    ExportCompanyDto createExportCompany(ExportCompanyDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ExportCompanyDto}
     */
    ExportCompanyDto updateExportCompany(UUID id, ExportCompanyDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteExportCompany(UUID id);
}
