package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.mapper.SyncWatermarksMapper;
import gr.knowledge.pepTest.entity.SyncWatermarks;
import gr.knowledge.pepTest.repository.SyncWatermarksRepository;
import gr.knowledge.pepTest.service.SyncWatermarksService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code SyncWatermarks} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SyncWatermarksServiceImpl implements SyncWatermarksService {

    private final SyncWatermarksRepository syncWatermarksRepository;
    private final SyncWatermarksMapper syncWatermarksMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncWatermarksDto}
     */
    @Override
    public List<SyncWatermarksDto> getAllSyncWatermarks() {
        log.info("Fetching all sync-watermarks.");
        return syncWatermarksMapper.toDTO(syncWatermarksRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncWatermarksDto}
     */
    @Override
    public SyncWatermarksDto getSyncWatermarksById(Long id) {
        log.info("Fetching sync-watermarks with id: {}", id);
        SyncWatermarks entity = syncWatermarksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncWatermarks not found with id: " + id));
        return syncWatermarksMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncWatermarksDto}
     */
    @Override
    public SyncWatermarksDto createSyncWatermarks(SyncWatermarksDto dto) {
        log.info("Creating sync-watermarks.");
        SyncWatermarks entity = syncWatermarksMapper.toEntity(dto);
        SyncWatermarks saved = syncWatermarksRepository.save(entity);
        return syncWatermarksMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncWatermarksDto}
     */
    @Override
    public SyncWatermarksDto updateSyncWatermarks(Long id, SyncWatermarksDto dto) {
        log.info("Updating sync-watermarks with id: {}", id);
        syncWatermarksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncWatermarks not found with id: " + id));
        SyncWatermarks entity = syncWatermarksMapper.toEntity(dto);
        entity.setId(id);
        SyncWatermarks saved = syncWatermarksRepository.save(entity);
        return syncWatermarksMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteSyncWatermarks(Long id) {
        log.info("Deleting sync-watermarks with id: {}", id);
        syncWatermarksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncWatermarks not found with id: " + id));
        syncWatermarksRepository.deleteById(id);
    }
}
