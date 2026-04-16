package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyContactMessage} domain operations.
 */
public interface CompanyContactMessageService {

    /**
     * Retrieves all company contact messages.
     * @return non-null list of {@link CompanyContactMessageDto}
     */
    List<CompanyContactMessageDto> getAllCompanyContactMessages();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto getCompanyContactMessageById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto createCompanyContactMessage(CompanyContactMessageDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyContactMessageDto}
     */
    CompanyContactMessageDto updateCompanyContactMessage(UUID id, CompanyContactMessageDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyContactMessage(UUID id);
}
