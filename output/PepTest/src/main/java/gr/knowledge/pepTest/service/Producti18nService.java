package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.entity.Producti18nPK;
import java.util.List;

/**
 * Service contract for {@code Producti18n} domain operations.
 */
public interface Producti18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Producti18nDto}
     */
    List<Producti18nDto> getAllProducti18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Producti18nDto}
     */
    Producti18nDto getProducti18nById(Producti18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Producti18nDto}
     */
    Producti18nDto createProducti18n(Producti18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Producti18nDto}
     */
    Producti18nDto updateProducti18n(Producti18nPK id, Producti18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProducti18n(Producti18nPK id);
}
