package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.mapper.StatusHistoryMapper;
import gr.knowledge.pepTest.entity.StatusHistory;
import gr.knowledge.pepTest.repository.StatusHistoryRepository;
import gr.knowledge.pepTest.service.StatusHistoryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Status History} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;
    private final StatusHistoryMapper statusHistoryMapper;

    /**
     * Retrieves all status histories records.
     * @return list of StatusHistoryDto
     */
    @Override
    public List<StatusHistoryDto> getAllStatusHistories() {
        log.info("Fetching all status histories records.");
        return statusHistoryMapper.toDTOList(statusHistoryRepository.findAll());
    }

    /**
     * Retrieves a status history record by id.
     * @param id the status history id
     * @return StatusHistoryDto
     */
    @Override
    public StatusHistoryDto getStatusHistoryById(UUID id) {
        log.info("Fetching status history with id: {}", id);

        StatusHistory existingEntity = findStatusHistoryByIdOrThrow(id);
        return statusHistoryMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new status history record.
     * @param dto input payload
     * @return created {@link StatusHistoryDto}
     */
    @Override
    public StatusHistoryDto createStatusHistory(StatusHistoryDto dto) {
        log.info("Creating status history.");

        validateStatusHistoryCreateUniqueConstraints(dto);

        StatusHistory entity = statusHistoryMapper.toEntity(dto);
        StatusHistory savedEntity = statusHistoryRepository.save(entity);

        return statusHistoryMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing status history record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the status history id
     * @param dto input payload with partial fields
     * @return updated {@link StatusHistoryDto}
     */
    @Override
    public StatusHistoryDto updateStatusHistory(UUID id, StatusHistoryDto dto) {
        log.info("Updating status history with id: {}", id);

        StatusHistory existingEntity = findStatusHistoryByIdOrThrow(id);
        statusHistoryMapper.partialUpdate(existingEntity, dto);
        StatusHistory savedEntity = statusHistoryRepository.save(existingEntity);

        return statusHistoryMapper.toDTO(savedEntity);
    }

    /**
     * Delete a status history record by id.
     * @param id the status history id
     */
    @Override
    public void deleteStatusHistory(UUID id) {
        log.info("Deleting status history with id: {}", id);

        findStatusHistoryByIdOrThrow(id);
        statusHistoryRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateStatusHistoryCreateUniqueConstraints(StatusHistoryDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberStatusHistoryId() != null && statusHistoryRepository.existsByChamberIdAndChamberStatusHistoryId(dto.getChamberId(), dto.getChamberStatusHistoryId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("StatusHistory")
                    .message("StatusHistory already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberStatusHistoryId=" + dto.getChamberStatusHistoryId())
                    .build();
        }
    }

    /**
     * Finds an existing status history record by id or throws an exception.
     * @param id the status history id
     * @return existing StatusHistory entity
     */
    private StatusHistory findStatusHistoryByIdOrThrow(UUID id) {
        return statusHistoryRepository.findById(id)
                .orElseThrow(() -> createStatusHistoryNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the status history entity.
     @param id the status history id
     @return runtime exception
     */
    private RuntimeException createStatusHistoryNotFoundException(UUID id) {
        log.warn("StatusHistory not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("StatusHistory")
                .message("StatusHistory not found with id: " + id)
                .build();
    }

}
