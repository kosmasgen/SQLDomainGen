package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.entity.Companyi18nPK;
import java.util.List;

/**
 * Service contract for {@code Companyi18n} domain operations.
 */
public interface Companyi18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Companyi18nDto}
     */
    List<Companyi18nDto> getAllCompanyi18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Companyi18nDto}
     */
    Companyi18nDto getCompanyi18nById(Companyi18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Companyi18nDto}
     */
    Companyi18nDto createCompanyi18n(Companyi18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Companyi18nDto}
     */
    Companyi18nDto updateCompanyi18n(Companyi18nPK id, Companyi18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyi18n(Companyi18nPK id);
}
