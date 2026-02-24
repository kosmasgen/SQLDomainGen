package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import java.util.List;

/**
 * Service contract for {@code ProfessionFriendlyCategory} domain operations.
 */
public interface ProfessionFriendlyCategoryService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionFriendlyCategoryDto}
     */
    List<ProfessionFriendlyCategoryDto> getAllProfessionFriendlyCategory();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto getProfessionFriendlyCategoryById(String id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto createProfessionFriendlyCategory(ProfessionFriendlyCategoryDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto updateProfessionFriendlyCategory(String id, ProfessionFriendlyCategoryDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfessionFriendlyCategory(String id);
}
