package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.entity.Professioni18nPK;
import java.util.List;

/**
 * Service contract for {@code Professioni18n} domain operations.
 */
public interface Professioni18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Professioni18nDto}
     */
    List<Professioni18nDto> getAllProfessioni18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Professioni18nDto}
     */
    Professioni18nDto getProfessioni18nById(Professioni18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Professioni18nDto}
     */
    Professioni18nDto createProfessioni18n(Professioni18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Professioni18nDto}
     */
    Professioni18nDto updateProfessioni18n(Professioni18nPK id, Professioni18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProfessioni18n(Professioni18nPK id);
}
