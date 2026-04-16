package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ChAppUserContactI18n} domain operations.
 */
public interface ChAppUserContactI18nService {

    /**
     * Retrieves all ch app user contact i18ns.
     * @return non-null list of {@link ChAppUserContactI18nDto}
     */
    List<ChAppUserContactI18nDto> getAllChAppUserContactI18ns();

    /**
     * Retrieves a record by id.
     * @param chAppUserContactId the ch_app_user_contact_id value
     * @param languageId the language_id value
     * @return the matching {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto getChAppUserContactI18nById(UUID chAppUserContactId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto createChAppUserContactI18n(ChAppUserContactI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param chAppUserContactId the ch_app_user_contact_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto updateChAppUserContactI18n(UUID chAppUserContactId, UUID languageId, ChAppUserContactI18nDto dto);

    /**
     * Deletes a record by id.
     * @param chAppUserContactId the ch_app_user_contact_id value
     * @param languageId the language_id value
     */
    void deleteChAppUserContactI18n(UUID chAppUserContactId, UUID languageId);
}
