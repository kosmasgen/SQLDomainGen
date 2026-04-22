package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.mapper.SyncWatermarksMapper;
import gr.knowledge.pepTest.entity.SyncWatermarks;
import gr.knowledge.pepTest.repository.SyncWatermarksRepository;
import gr.knowledge.pepTest.service.SyncWatermarksService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Sync Watermarks} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SyncWatermarksServiceImpl implements SyncWatermarksService {

    private final SyncWatermarksRepository syncWatermarksRepository;
    private final SyncWatermarksMapper syncWatermarksMapper;

    /**
     * Retrieves all sync watermarkses records.
     * @return list of SyncWatermarksDto
     */
    @Override
    public List<SyncWatermarksDto> getAllSyncWatermarkses() {
        log.info("Fetching all sync watermarkses records.");
        return syncWatermarksMapper.toDTOList(syncWatermarksRepository.findAll());
    }

    /**
     * Retrieves a sync watermarks record by id.
     * @param id the sync watermarks id
     * @return SyncWatermarksDto
     */
    @Override
    public SyncWatermarksDto getSyncWatermarksById(Long id) {
        log.info("Fetching sync watermarks with id: {}", id);

        SyncWatermarks existingEntity = findSyncWatermarksByIdOrThrow(id);
        return syncWatermarksMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new sync watermarks record.
     * @param dto input payload
     * @return created {@link SyncWatermarksDto}
     */
    @Override
    public SyncWatermarksDto createSyncWatermarks(SyncWatermarksDto dto) {
        log.info("Creating sync watermarks.");

        validateSyncWatermarksCreateUniqueConstraints(dto);

        SyncWatermarks entity = syncWatermarksMapper.toEntity(dto);
        SyncWatermarks savedEntity = syncWatermarksRepository.save(entity);

        return syncWatermarksMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing sync watermarks record.
     *
     * @param id the sync watermarks id
     * @param dto input payload
     * @return updated {@link SyncWatermarksDto}
     */
    @Override
    public SyncWatermarksDto updateSyncWatermarks(Long id, SyncWatermarksDto dto) {
        log.info("Updating sync watermarks with id: {}", id);

        SyncWatermarks existingEntity = findSyncWatermarksByIdOrThrow(id);
        syncWatermarksMapper.partialUpdate(existingEntity, dto);
        SyncWatermarks savedEntity = syncWatermarksRepository.save(existingEntity);

        return syncWatermarksMapper.toDTO(savedEntity);
    }

    /**
     * Delete a sync watermarks record by id.
     * @param id the sync watermarks id
     */
    @Override
    public void deleteSyncWatermarks(Long id) {
        log.info("Deleting sync watermarks with id: {}", id);

        findSyncWatermarksByIdOrThrow(id);
        syncWatermarksRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateSyncWatermarksCreateUniqueConstraints(SyncWatermarksDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getTableName() != null && syncWatermarksRepository.existsByTableName(dto.getTableName())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("SyncWatermarks")
                    .message("SyncWatermarks already exists with tableName: " + dto.getTableName())
                    .build();
        }
    }

    /**
     * Finds an existing sync watermarks record by id or throws an exception.
     * @param id the sync watermarks id
     * @return existing SyncWatermarks entity
     */
    private SyncWatermarks findSyncWatermarksByIdOrThrow(Long id) {
        return syncWatermarksRepository.findById(id)
                .orElseThrow(() -> createSyncWatermarksNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the sync watermarks entity.
     @param id the sync watermarks id
     @return runtime exception
     */
    private RuntimeException createSyncWatermarksNotFoundException(Long id) {
        log.warn("SyncWatermarks not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("SyncWatermarks")
                .message("SyncWatermarks not found with id: " + id)
                .build();
    }

}
