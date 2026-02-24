package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import java.util.List;

/**
 * Service contract for {@code TemporaryCompany} domain operations.
 */
public interface TemporaryCompanyService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyDto}
     */
    List<TemporaryCompanyDto> getAllTemporaryCompany();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto getTemporaryCompanyById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto createTemporaryCompany(TemporaryCompanyDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto updateTemporaryCompany(Long id, TemporaryCompanyDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTemporaryCompany(Long id);
}
