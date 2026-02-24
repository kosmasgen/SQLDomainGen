package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.mapper.StatusHistoryMapper;
import gr.knowledge.pepTest.entity.StatusHistory;
import gr.knowledge.pepTest.repository.StatusHistoryRepository;
import gr.knowledge.pepTest.service.StatusHistoryService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code StatusHistory} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;
    private final StatusHistoryMapper statusHistoryMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StatusHistoryDto}
     */
    @Override
    public List<StatusHistoryDto> getAllStatusHistory() {
        log.info("Fetching all status-history.");
        return statusHistoryMapper.toDTO(statusHistoryRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StatusHistoryDto}
     */
    @Override
    public StatusHistoryDto getStatusHistoryById(UUID id) {
        log.info("Fetching status-history with id: {}", id);
        StatusHistory entity = statusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory not found with id: " + id));
        return statusHistoryMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StatusHistoryDto}
     */
    @Override
    public StatusHistoryDto createStatusHistory(StatusHistoryDto dto) {
        log.info("Creating status-history.");
        StatusHistory entity = statusHistoryMapper.toEntity(dto);
        StatusHistory saved = statusHistoryRepository.save(entity);
        return statusHistoryMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link StatusHistoryDto}
     */
    @Override
    public StatusHistoryDto updateStatusHistory(UUID id, StatusHistoryDto dto) {
        log.info("Updating status-history with id: {}", id);
        statusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory not found with id: " + id));
        StatusHistory entity = statusHistoryMapper.toEntity(dto);
        entity.setId(id);
        StatusHistory saved = statusHistoryRepository.save(entity);
        return statusHistoryMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteStatusHistory(UUID id) {
        log.info("Deleting status-history with id: {}", id);
        statusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory not found with id: " + id));
        statusHistoryRepository.deleteById(id);
    }
}
