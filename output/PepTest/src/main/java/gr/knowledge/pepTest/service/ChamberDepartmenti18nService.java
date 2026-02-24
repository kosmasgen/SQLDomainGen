package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nPK;
import java.util.List;

/**
 * Service contract for {@code ChamberDepartmenti18n} domain operations.
 */
public interface ChamberDepartmenti18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberDepartmenti18nDto}
     */
    List<ChamberDepartmenti18nDto> getAllChamberDepartmenti18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto getChamberDepartmenti18nById(ChamberDepartmenti18nPK id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto createChamberDepartmenti18n(ChamberDepartmenti18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto updateChamberDepartmenti18n(ChamberDepartmenti18nPK id, ChamberDepartmenti18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteChamberDepartmenti18n(ChamberDepartmenti18nPK id);
}
