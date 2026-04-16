package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.mapper.SyncrunsMapper;
import gr.knowledge.pepTest.entity.Syncruns;
import gr.knowledge.pepTest.repository.SyncrunsRepository;
import gr.knowledge.pepTest.service.SyncrunsService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Syncruns} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class SyncrunsServiceImpl implements SyncrunsService {

    private final SyncrunsRepository syncrunsRepository;
    private final SyncrunsMapper syncrunsMapper;

    /**
     * Retrieves all syncrunses records.
     * @return list of SyncrunsDto
     */
    @Override
    public List<SyncrunsDto> getAllSyncrunses() {
        log.info("Fetching all syncrunses records.");
        return syncrunsMapper.toDTOList(syncrunsRepository.findAll());
    }

    /**
     * Retrieves a syncruns record by id.
     * @param id the syncruns id
     * @return SyncrunsDto
     */
    @Override
    public SyncrunsDto getSyncrunsById(Long id) {
        log.info("Fetching syncruns with id: {}", id);

        Syncruns existingEntity = findSyncrunsByIdOrThrow(id);
        return syncrunsMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new syncruns record.
     * @param dto input payload
     * @return created {@link SyncrunsDto}
     */
    @Override
    public SyncrunsDto createSyncruns(SyncrunsDto dto) {
        log.info("Creating syncruns.");

        Syncruns entity = syncrunsMapper.toEntity(dto);
        Syncruns savedEntity = syncrunsRepository.save(entity);

        return syncrunsMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing syncruns record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the syncruns id
     * @param dto input payload with partial fields
     * @return updated {@link SyncrunsDto}
     */
    @Override
    public SyncrunsDto updateSyncruns(Long id, SyncrunsDto dto) {
        log.info("Updating syncruns with id: {}", id);

        Syncruns existingEntity = findSyncrunsByIdOrThrow(id);
        syncrunsMapper.partialUpdate(existingEntity, dto);
        Syncruns savedEntity = syncrunsRepository.save(existingEntity);

        return syncrunsMapper.toDTO(savedEntity);
    }

    /**
     * Delete a syncruns record by id.
     * @param id the syncruns id
     */
    @Override
    public void deleteSyncruns(Long id) {
        log.info("Deleting syncruns with id: {}", id);

        findSyncrunsByIdOrThrow(id);
        syncrunsRepository.deleteById(id);
    }

    /**
     * Finds an existing syncruns record by id or throws an exception.
     * @param id the syncruns id
     * @return existing Syncruns entity
     */
    private Syncruns findSyncrunsByIdOrThrow(Long id) {
        return syncrunsRepository.findById(id)
                .orElseThrow(() -> createSyncrunsNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the syncruns entity.
     @param id the syncruns id
     @return runtime exception
     */
    private RuntimeException createSyncrunsNotFoundException(Long id) {
        log.warn("Syncruns not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Syncruns")
                .message("Syncruns not found with id: " + id)
                .build();
    }

}
