package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.entity.CorporateStatusi18nPK;
import java.util.List;

/**
 * Service contract for {@code CorporateStatusi18n} domain operations.
 */
public interface CorporateStatusi18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusi18nDto}
     */
    List<CorporateStatusi18nDto> getAllCorporateStatusi18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto getCorporateStatusi18nById(CorporateStatusi18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto createCorporateStatusi18n(CorporateStatusi18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusi18nDto}
     */
    CorporateStatusi18nDto updateCorporateStatusi18n(CorporateStatusi18nPK id, CorporateStatusi18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCorporateStatusi18n(CorporateStatusi18nPK id);
}
