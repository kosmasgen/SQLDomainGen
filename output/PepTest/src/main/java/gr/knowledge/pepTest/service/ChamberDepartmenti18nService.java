package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code ChamberDepartmenti18n} domain operations.
 */
public interface ChamberDepartmenti18nService {

    /**
     * Retrieves all chamber departmenti18ns.
     * @return non-null list of {@link ChamberDepartmenti18nDto}
     */
    List<ChamberDepartmenti18nDto> getAllChamberDepartmenti18ns();

    /**
     * Retrieves a record by id.
     * @param departmentId the department_id value
     * @param languageId the language_id value
     * @return the matching {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto getChamberDepartmenti18nById(UUID departmentId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto createChamberDepartmenti18n(ChamberDepartmenti18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param departmentId the department_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link ChamberDepartmenti18nDto}
     */
    ChamberDepartmenti18nDto updateChamberDepartmenti18n(UUID departmentId, UUID languageId, ChamberDepartmenti18nDto dto);

    /**
     * Deletes a record by id.
     * @param departmentId the department_id value
     * @param languageId the language_id value
     */
    void deleteChamberDepartmenti18n(UUID departmentId, UUID languageId);
}
