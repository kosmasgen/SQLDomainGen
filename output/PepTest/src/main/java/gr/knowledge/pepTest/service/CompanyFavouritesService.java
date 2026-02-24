package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyFavourites} domain operations.
 */
public interface CompanyFavouritesService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyFavouritesDto}
     */
    List<CompanyFavouritesDto> getAllCompanyFavourites();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto getCompanyFavouritesById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto createCompanyFavourites(CompanyFavouritesDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyFavouritesDto}
     */
    CompanyFavouritesDto updateCompanyFavourites(UUID id, CompanyFavouritesDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyFavourites(UUID id);
}
