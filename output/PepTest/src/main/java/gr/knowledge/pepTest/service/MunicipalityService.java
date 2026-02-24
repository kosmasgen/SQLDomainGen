package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Municipality} domain operations.
 */
public interface MunicipalityService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link MunicipalityDto}
     */
    List<MunicipalityDto> getAllMunicipality();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link MunicipalityDto}
     */
    MunicipalityDto getMunicipalityById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link MunicipalityDto}
     */
    MunicipalityDto createMunicipality(MunicipalityDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link MunicipalityDto}
     */
    MunicipalityDto updateMunicipality(UUID id, MunicipalityDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteMunicipality(UUID id);
}
