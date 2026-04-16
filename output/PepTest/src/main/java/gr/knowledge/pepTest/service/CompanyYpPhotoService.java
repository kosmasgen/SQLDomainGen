package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyYpPhoto} domain operations.
 */
public interface CompanyYpPhotoService {

    /**
     * Retrieves all company yp photos.
     * @return non-null list of {@link CompanyYpPhotoDto}
     */
    List<CompanyYpPhotoDto> getAllCompanyYpPhotos();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto getCompanyYpPhotoById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto createCompanyYpPhoto(CompanyYpPhotoDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpPhotoDto}
     */
    CompanyYpPhotoDto updateCompanyYpPhoto(UUID id, CompanyYpPhotoDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyYpPhoto(UUID id);
}
