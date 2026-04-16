package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code MunicipalityI18n} domain operations.
 */
public interface MunicipalityI18nService {

    /**
     * Retrieves all municipality i18ns.
     * @return non-null list of {@link MunicipalityI18nDto}
     */
    List<MunicipalityI18nDto> getAllMunicipalityI18ns();

    /**
     * Retrieves a record by id.
     * @param municipalityId the municipality_id value
     * @param languageId the language_id value
     * @return the matching {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto getMunicipalityI18nById(UUID municipalityId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto createMunicipalityI18n(MunicipalityI18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param municipalityId the municipality_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link MunicipalityI18nDto}
     */
    MunicipalityI18nDto updateMunicipalityI18n(UUID municipalityId, UUID languageId, MunicipalityI18nDto dto);

    /**
     * Deletes a record by id.
     * @param municipalityId the municipality_id value
     * @param languageId the language_id value
     */
    void deleteMunicipalityI18n(UUID municipalityId, UUID languageId);
}
