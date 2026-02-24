package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.SyncrunsErrorLogDto;
import gr.knowledge.pepTest.mapper.SyncrunsErrorLogMapper;
import gr.knowledge.pepTest.entity.SyncrunsErrorLog;
import gr.knowledge.pepTest.repository.SyncrunsErrorLogRepository;
import gr.knowledge.pepTest.service.SyncrunsErrorLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code SyncrunsErrorLog} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SyncrunsErrorLogServiceImpl implements SyncrunsErrorLogService {

    private final SyncrunsErrorLogRepository syncrunsErrorLogRepository;
    private final SyncrunsErrorLogMapper syncrunsErrorLogMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncrunsErrorLogDto}
     */
    @Override
    public List<SyncrunsErrorLogDto> getAllSyncrunsErrorLog() {
        log.info("Fetching all syncruns-error-log.");
        return syncrunsErrorLogMapper.toDTO(syncrunsErrorLogRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncrunsErrorLogDto}
     */
    @Override
    public SyncrunsErrorLogDto getSyncrunsErrorLogById(String id) {
        log.info("Fetching syncruns-error-log with id: {}", id);
        SyncrunsErrorLog entity = syncrunsErrorLogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncrunsErrorLog not found with id: " + id));
        return syncrunsErrorLogMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncrunsErrorLogDto}
     */
    @Override
    public SyncrunsErrorLogDto createSyncrunsErrorLog(SyncrunsErrorLogDto dto) {
        log.info("Creating syncruns-error-log.");
        SyncrunsErrorLog entity = syncrunsErrorLogMapper.toEntity(dto);
        SyncrunsErrorLog saved = syncrunsErrorLogRepository.save(entity);
        return syncrunsErrorLogMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncrunsErrorLogDto}
     */
    @Override
    public SyncrunsErrorLogDto updateSyncrunsErrorLog(String id, SyncrunsErrorLogDto dto) {
        log.info("Updating syncruns-error-log with id: {}", id);
        syncrunsErrorLogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncrunsErrorLog not found with id: " + id));
        SyncrunsErrorLog entity = syncrunsErrorLogMapper.toEntity(dto);
        entity.setId(id);
        SyncrunsErrorLog saved = syncrunsErrorLogRepository.save(entity);
        return syncrunsErrorLogMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteSyncrunsErrorLog(String id) {
        log.info("Deleting syncruns-error-log with id: {}", id);
        syncrunsErrorLogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SyncrunsErrorLog not found with id: " + id));
        syncrunsErrorLogRepository.deleteById(id);
    }
}
