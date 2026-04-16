package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import java.util.List;
import java.math.BigInteger;

/**
 * Service contract for {@code TemporaryCompany} domain operations.
 */
public interface TemporaryCompanyService {

    /**
     * Retrieves all temporary companies.
     * @return non-null list of {@link TemporaryCompanyDto}
     */
    List<TemporaryCompanyDto> getAllTemporaryCompanies();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto getTemporaryCompanyById(BigInteger id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto createTemporaryCompany(TemporaryCompanyDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyDto}
     */
    TemporaryCompanyDto updateTemporaryCompany(BigInteger id, TemporaryCompanyDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteTemporaryCompany(BigInteger id);
}
