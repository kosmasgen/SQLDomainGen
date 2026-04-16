package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyFavourites} domain operations.
 */
public interface CompanyFavouritesService {

    /**
     * Retrieves all company favouriteses.
     * @return non-null list of {@link CompanyFavouritesDto}
     */
    List<CompanyFavouritesDto> getAllCompanyFavouriteses();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto getCompanyFavouritesById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto createCompanyFavourites(CompanyFavouritesDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto updateCompanyFavourites(UUID id, CompanyFavouritesDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyFavourites(UUID id);
}
