package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.entity.MunicipalityI18nPK;
import java.util.List;

/**
 * Service contract for {@code MunicipalityI18n} domain operations.
 */
public interface MunicipalityI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link MunicipalityI18nDto}
     */
    List<MunicipalityI18nDto> getAllMunicipalityI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto getMunicipalityI18nById(MunicipalityI18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto createMunicipalityI18n(MunicipalityI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto updateMunicipalityI18n(MunicipalityI18nPK id, MunicipalityI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteMunicipalityI18n(MunicipalityI18nPK id);
}
