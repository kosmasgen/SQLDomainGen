package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import java.util.List;

/**
 * Service contract for {@code TemporaryCompanyProfession} domain operations.
 */
public interface TemporaryCompanyProfessionService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyProfessionDto}
     */
    List<TemporaryCompanyProfessionDto> getAllTemporaryCompanyProfession();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto getTemporaryCompanyProfessionById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto createTemporaryCompanyProfession(TemporaryCompanyProfessionDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto updateTemporaryCompanyProfession(Long id, TemporaryCompanyProfessionDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteTemporaryCompanyProfession(Long id);
}
