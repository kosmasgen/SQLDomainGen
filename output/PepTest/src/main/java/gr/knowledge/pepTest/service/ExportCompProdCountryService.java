package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ExportCompProdCountry} domain operations.
 */
public interface ExportCompProdCountryService {

    /**
     * Retrieves all export comp prod countries.
     * @return non-null list of {@link ExportCompProdCountryDto}
     */
    List<ExportCompProdCountryDto> getAllExportCompProdCountries();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto getExportCompProdCountryById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto createExportCompProdCountry(ExportCompProdCountryDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto updateExportCompProdCountry(UUID id, ExportCompProdCountryDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteExportCompProdCountry(UUID id);
}
