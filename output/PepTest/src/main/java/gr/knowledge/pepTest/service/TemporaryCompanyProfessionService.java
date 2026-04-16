package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import java.util.List;
import java.math.BigInteger;

/**
 * Service contract for {@code TemporaryCompanyProfession} domain operations.
 */
public interface TemporaryCompanyProfessionService {

    /**
     * Retrieves all temporary company professions.
     * @return non-null list of {@link TemporaryCompanyProfessionDto}
     */
    List<TemporaryCompanyProfessionDto> getAllTemporaryCompanyProfessions();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto getTemporaryCompanyProfessionById(BigInteger id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto createTemporaryCompanyProfession(TemporaryCompanyProfessionDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyProfessionDto}
     */
    TemporaryCompanyProfessionDto updateTemporaryCompanyProfession(BigInteger id, TemporaryCompanyProfessionDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteTemporaryCompanyProfession(BigInteger id);
}
