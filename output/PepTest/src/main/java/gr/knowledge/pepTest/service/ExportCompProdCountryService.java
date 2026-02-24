package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code ExportCompProdCountry} domain operations.
 */
public interface ExportCompProdCountryService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ExportCompProdCountryDto}
     */
    List<ExportCompProdCountryDto> getAllExportCompProdCountry();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto getExportCompProdCountryById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto createExportCompProdCountry(ExportCompProdCountryDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ExportCompProdCountryDto}
     */
    ExportCompProdCountryDto updateExportCompProdCountry(UUID id, ExportCompProdCountryDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteExportCompProdCountry(UUID id);
}
