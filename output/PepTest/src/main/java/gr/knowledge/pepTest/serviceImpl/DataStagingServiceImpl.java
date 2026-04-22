package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.mapper.DataStagingMapper;
import gr.knowledge.pepTest.entity.DataStaging;
import gr.knowledge.pepTest.repository.DataStagingRepository;
import gr.knowledge.pepTest.service.DataStagingService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Data Staging} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class DataStagingServiceImpl implements DataStagingService {

    private final DataStagingRepository dataStagingRepository;
    private final DataStagingMapper dataStagingMapper;

    /**
     * Retrieves all data stagings records.
     * @return list of DataStagingDto
     */
    @Override
    public List<DataStagingDto> getAllDataStagings() {
        log.info("Fetching all data stagings records.");
        return dataStagingMapper.toDTOList(dataStagingRepository.findAll());
    }

    /**
     * Retrieves a data staging record by id.
     * @param id the data staging id
     * @return DataStagingDto
     */
    @Override
    public DataStagingDto getDataStagingById(Long id) {
        log.info("Fetching data staging with id: {}", id);

        DataStaging existingEntity = findDataStagingByIdOrThrow(id);
        return dataStagingMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new data staging record.
     * @param dto input payload
     * @return created {@link DataStagingDto}
     */
    @Override
    public DataStagingDto createDataStaging(DataStagingDto dto) {
        log.info("Creating data staging.");

        validateDataStagingCreateUniqueConstraints(dto);

        DataStaging entity = dataStagingMapper.toEntity(dto);
        DataStaging savedEntity = dataStagingRepository.save(entity);

        return dataStagingMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing data staging record.
     *
     * @param id the data staging id
     * @param dto input payload
     * @return updated {@link DataStagingDto}
     */
    @Override
    public DataStagingDto updateDataStaging(Long id, DataStagingDto dto) {
        log.info("Updating data staging with id: {}", id);

        DataStaging existingEntity = findDataStagingByIdOrThrow(id);
        dataStagingMapper.partialUpdate(existingEntity, dto);
        DataStaging savedEntity = dataStagingRepository.save(existingEntity);

        return dataStagingMapper.toDTO(savedEntity);
    }

    /**
     * Delete a data staging record by id.
     * @param id the data staging id
     */
    @Override
    public void deleteDataStaging(Long id) {
        log.info("Deleting data staging with id: {}", id);

        findDataStagingByIdOrThrow(id);
        dataStagingRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateDataStagingCreateUniqueConstraints(DataStagingDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getLegacyTableName() != null && dto.getLegacyRecordId() != null && dto.getLegacyUpdatedAt() != null && dataStagingRepository.existsByLegacyTableNameAndLegacyRecordIdAndLegacyUpdatedAt(dto.getLegacyTableName(), dto.getLegacyRecordId(), dto.getLegacyUpdatedAt())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("DataStaging")
                    .message("DataStaging already exists with " + "legacyTableName=" + dto.getLegacyTableName() + ", " + "legacyRecordId=" + dto.getLegacyRecordId() + ", " + "legacyUpdatedAt=" + dto.getLegacyUpdatedAt())
                    .build();
        }
    }

    /**
     * Finds an existing data staging record by id or throws an exception.
     * @param id the data staging id
     * @return existing DataStaging entity
     */
    private DataStaging findDataStagingByIdOrThrow(Long id) {
        return dataStagingRepository.findById(id)
                .orElseThrow(() -> createDataStagingNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the data staging entity.
     @param id the data staging id
     @return runtime exception
     */
    private RuntimeException createDataStagingNotFoundException(Long id) {
        log.warn("DataStaging not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("DataStaging")
                .message("DataStaging not found with id: " + id)
                .build();
    }

}
