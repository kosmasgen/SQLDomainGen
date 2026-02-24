package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.DataStagingDto;
import java.util.List;

/**
 * Service contract for {@code DataStaging} domain operations.
 */
public interface DataStagingService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link DataStagingDto}
     */
    List<DataStagingDto> getAllDataStaging();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link DataStagingDto}
     */
    DataStagingDto getDataStagingById(Long id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link DataStagingDto}
     */
    DataStagingDto createDataStaging(DataStagingDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link DataStagingDto}
     */
    DataStagingDto updateDataStaging(Long id, DataStagingDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteDataStaging(Long id);
}
