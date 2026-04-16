package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.DataStagingDto;
import java.util.List;

/**
 * Service contract for {@code DataStaging} domain operations.
 */
public interface DataStagingService {

    /**
     * Retrieves all data stagings.
     * @return non-null list of {@link DataStagingDto}
     */
    List<DataStagingDto> getAllDataStagings();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link DataStagingDto}
     */
    DataStagingDto getDataStagingById(Long id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link DataStagingDto}
     */
    DataStagingDto createDataStaging(DataStagingDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link DataStagingDto}
     */
    DataStagingDto updateDataStaging(Long id, DataStagingDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteDataStaging(Long id);
}
