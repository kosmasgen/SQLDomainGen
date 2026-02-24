package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.entity.BusinessLocationI18nPK;
import java.util.List;

/**
 * Service contract for {@code BusinessLocationI18n} domain operations.
 */
public interface BusinessLocationI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BusinessLocationI18nDto}
     */
    List<BusinessLocationI18nDto> getAllBusinessLocationI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto getBusinessLocationI18nById(BusinessLocationI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto createBusinessLocationI18n(BusinessLocationI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BusinessLocationI18nDto}
     */
    BusinessLocationI18nDto updateBusinessLocationI18n(BusinessLocationI18nPK id, BusinessLocationI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteBusinessLocationI18n(BusinessLocationI18nPK id);
}
