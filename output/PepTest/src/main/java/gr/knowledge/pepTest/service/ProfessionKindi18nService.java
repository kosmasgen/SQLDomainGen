package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ProfessionKindi18n} domain operations.
 */
public interface ProfessionKindi18nService {

    /**
     * Retrieves all profession kindi18ns.
     * @return non-null list of {@link ProfessionKindi18nDto}
     */
    List<ProfessionKindi18nDto> getAllProfessionKindi18ns();

    /**
     * Retrieves a record by id.
     * @param professionKindId the profession_kind_id value
     * @param languageId the language_id value
     * @return the matching {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto getProfessionKindi18nById(UUID professionKindId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto createProfessionKindi18n(ProfessionKindi18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param professionKindId the profession_kind_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto updateProfessionKindi18n(UUID professionKindId, UUID languageId, ProfessionKindi18nDto dto);

    /**
     * Deletes a record by id.
     * @param professionKindId the profession_kind_id value
     * @param languageId the language_id value
     */
    void deleteProfessionKindi18n(UUID professionKindId, UUID languageId);
}
