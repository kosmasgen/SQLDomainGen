package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyContactMessage} domain operations.
 */
public interface CompanyContactMessageService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyContactMessageDto}
     */
    List<CompanyContactMessageDto> getAllCompanyContactMessage();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto getCompanyContactMessageById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto createCompanyContactMessage(CompanyContactMessageDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto updateCompanyContactMessage(UUID id, CompanyContactMessageDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyContactMessage(UUID id);
}
