package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.mapper.SyncrunsMapper;
import gr.knowledge.pepTest.entity.Syncruns;
import gr.knowledge.pepTest.repository.SyncrunsRepository;
import gr.knowledge.pepTest.service.SyncrunsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
     * Retrieves all records.
     *
     * @return non-null list of {@link SyncrunsDto}
     */
    @Override
    public List<SyncrunsDto> getAllSyncruns() {
        log.info("Fetching all syncruns.");
        return syncrunsMapper.toDTO(syncrunsRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link SyncrunsDto}
     */
    @Override
    public SyncrunsDto getSyncrunsById(Long id) {
        log.info("Fetching syncruns with id: {}", id);
        Syncruns entity = syncrunsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Syncruns not found with id: " + id));
        return syncrunsMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link SyncrunsDto}
     */
    @Override
    public SyncrunsDto createSyncruns(SyncrunsDto dto) {
        log.info("Creating syncruns.");
        Syncruns entity = syncrunsMapper.toEntity(dto);
        Syncruns saved = syncrunsRepository.save(entity);
        return syncrunsMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link SyncrunsDto}
     */
    @Override
    public SyncrunsDto updateSyncruns(Long id, SyncrunsDto dto) {
        log.info("Updating syncruns with id: {}", id);
        syncrunsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Syncruns not found with id: " + id));
        Syncruns entity = syncrunsMapper.toEntity(dto);
        entity.setId(id);
        Syncruns saved = syncrunsRepository.save(entity);
        return syncrunsMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteSyncruns(Long id) {
        log.info("Deleting syncruns with id: {}", id);
        syncrunsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Syncruns not found with id: " + id));
        syncrunsRepository.deleteById(id);
    }
}
