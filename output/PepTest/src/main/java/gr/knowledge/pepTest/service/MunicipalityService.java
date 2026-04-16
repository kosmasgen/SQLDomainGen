package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Municipality} domain operations.
 */
public interface MunicipalityService {

    /**
     * Retrieves all municipalities.
     * @return non-null list of {@link MunicipalityDto}
     */
    List<MunicipalityDto> getAllMunicipalities();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link MunicipalityDto}
     */
    MunicipalityDto getMunicipalityById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link MunicipalityDto}
     */
    MunicipalityDto createMunicipality(MunicipalityDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link MunicipalityDto}
     */
    MunicipalityDto updateMunicipality(UUID id, MunicipalityDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteMunicipality(UUID id);
}
