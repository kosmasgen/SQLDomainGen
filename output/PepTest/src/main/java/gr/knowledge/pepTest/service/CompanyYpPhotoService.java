package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyYpPhoto} domain operations.
 */
public interface CompanyYpPhotoService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpPhotoDto}
     */
    List<CompanyYpPhotoDto> getAllCompanyYpPhoto();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto getCompanyYpPhotoById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto createCompanyYpPhoto(CompanyYpPhotoDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto updateCompanyYpPhoto(UUID id, CompanyYpPhotoDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyYpPhoto(UUID id);
}
