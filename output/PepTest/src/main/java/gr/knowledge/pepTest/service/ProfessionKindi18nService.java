package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.entity.ProfessionKindi18nPK;
import java.util.List;

/**
 * Service contract for {@code ProfessionKindi18n} domain operations.
 */
public interface ProfessionKindi18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionKindi18nDto}
     */
    List<ProfessionKindi18nDto> getAllProfessionKindi18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto getProfessionKindi18nById(ProfessionKindi18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto createProfessionKindi18n(ProfessionKindi18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionKindi18nDto}
     */
    ProfessionKindi18nDto updateProfessionKindi18n(ProfessionKindi18nPK id, ProfessionKindi18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfessionKindi18n(ProfessionKindi18nPK id);
}
