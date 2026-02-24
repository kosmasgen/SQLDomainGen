package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nPK;
import java.util.List;

/**
 * Service contract for {@code ChAppUserContactI18n} domain operations.
 */
public interface ChAppUserContactI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChAppUserContactI18nDto}
     */
    List<ChAppUserContactI18nDto> getAllChAppUserContactI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto getChAppUserContactI18nById(ChAppUserContactI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto createChAppUserContactI18n(ChAppUserContactI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChAppUserContactI18nDto}
     */
    ChAppUserContactI18nDto updateChAppUserContactI18n(ChAppUserContactI18nPK id, ChAppUserContactI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteChAppUserContactI18n(ChAppUserContactI18nPK id);
}
