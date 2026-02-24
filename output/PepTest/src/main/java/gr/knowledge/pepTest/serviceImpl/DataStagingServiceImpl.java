package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.mapper.DataStagingMapper;
import gr.knowledge.pepTest.entity.DataStaging;
import gr.knowledge.pepTest.repository.DataStagingRepository;
import gr.knowledge.pepTest.service.DataStagingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code DataStaging} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class DataStagingServiceImpl implements DataStagingService {

    private final DataStagingRepository dataStagingRepository;
    private final DataStagingMapper dataStagingMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link DataStagingDto}
     */
    @Override
    public List<DataStagingDto> getAllDataStaging() {
        log.info("Fetching all data-staging.");
        return dataStagingMapper.toDTO(dataStagingRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link DataStagingDto}
     */
    @Override
    public DataStagingDto getDataStagingById(Long id) {
        log.info("Fetching data-staging with id: {}", id);
        DataStaging entity = dataStagingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DataStaging not found with id: " + id));
        return dataStagingMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link DataStagingDto}
     */
    @Override
    public DataStagingDto createDataStaging(DataStagingDto dto) {
        log.info("Creating data-staging.");
        DataStaging entity = dataStagingMapper.toEntity(dto);
        DataStaging saved = dataStagingRepository.save(entity);
        return dataStagingMapper.toDTO(saved);
    }

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
    @Override
    public DataStagingDto updateDataStaging(Long id, DataStagingDto dto) {
        log.info("Updating data-staging with id: {}", id);
        dataStagingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DataStaging not found with id: " + id));
        DataStaging entity = dataStagingMapper.toEntity(dto);
        entity.setId(id);
        DataStaging saved = dataStagingRepository.save(entity);
        return dataStagingMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteDataStaging(Long id) {
        log.info("Deleting data-staging with id: {}", id);
        dataStagingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DataStaging not found with id: " + id));
        dataStagingRepository.deleteById(id);
    }
}
