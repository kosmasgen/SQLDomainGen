package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code BgPoiI18n} domain operations.
 */
public interface BgPoiI18nService {

    /**
     * Retrieves all bg poi i18ns.
     * @return non-null list of {@link BgPoiI18nDto}
     */
    List<BgPoiI18nDto> getAllBgPoiI18ns();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link BgPoiI18nDto}
     */
    BgPoiI18nDto getBgPoiI18nById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link BgPoiI18nDto}
     */
    BgPoiI18nDto createBgPoiI18n(BgPoiI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link BgPoiI18nDto}
     */
    BgPoiI18nDto updateBgPoiI18n(UUID id, BgPoiI18nDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteBgPoiI18n(UUID id);
}
