package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import java.util.List;

/**
 * Service contract for {@code ProfessionFriendlyCategory} domain operations.
 */
public interface ProfessionFriendlyCategoryService {

    /**
     * Retrieves all profession friendly categories.
     * @return non-null list of {@link ProfessionFriendlyCategoryDto}
     */
    List<ProfessionFriendlyCategoryDto> getAllProfessionFriendlyCategories();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto getProfessionFriendlyCategoryById(String id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto createProfessionFriendlyCategory(ProfessionFriendlyCategoryDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionFriendlyCategoryDto}
     */
    ProfessionFriendlyCategoryDto updateProfessionFriendlyCategory(String id, ProfessionFriendlyCategoryDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteProfessionFriendlyCategory(String id);
}
