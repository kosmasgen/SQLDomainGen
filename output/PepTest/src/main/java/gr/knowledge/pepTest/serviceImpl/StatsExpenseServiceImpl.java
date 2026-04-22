package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.mapper.StatsExpenseMapper;
import gr.knowledge.pepTest.entity.StatsExpense;
import gr.knowledge.pepTest.repository.StatsExpenseRepository;
import gr.knowledge.pepTest.service.StatsExpenseService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Stats Expense} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class StatsExpenseServiceImpl implements StatsExpenseService {

    private final StatsExpenseRepository statsExpenseRepository;
    private final StatsExpenseMapper statsExpenseMapper;

    /**
     * Retrieves all stats expenses records.
     * @return list of StatsExpenseDto
     */
    @Override
    public List<StatsExpenseDto> getAllStatsExpenses() {
        log.info("Fetching all stats expenses records.");
        return statsExpenseMapper.toDTOList(statsExpenseRepository.findAll());
    }

    /**
     * Retrieves a stats expense record by id.
     * @param id the stats expense id
     * @return StatsExpenseDto
     */
    @Override
    public StatsExpenseDto getStatsExpenseById(UUID id) {
        log.info("Fetching stats expense with id: {}", id);

        StatsExpense existingEntity = findStatsExpenseByIdOrThrow(id);
        return statsExpenseMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new stats expense record.
     * @param dto input payload
     * @return created {@link StatsExpenseDto}
     */
    @Override
    public StatsExpenseDto createStatsExpense(StatsExpenseDto dto) {
        log.info("Creating stats expense.");

        validateStatsExpenseCreateUniqueConstraints(dto);

        StatsExpense entity = statsExpenseMapper.toEntity(dto);
        StatsExpense savedEntity = statsExpenseRepository.save(entity);

        return statsExpenseMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing stats expense record.
     *
     * @param id the stats expense id
     * @param dto input payload
     * @return updated {@link StatsExpenseDto}
     */
    @Override
    public StatsExpenseDto updateStatsExpense(UUID id, StatsExpenseDto dto) {
        log.info("Updating stats expense with id: {}", id);

        StatsExpense existingEntity = findStatsExpenseByIdOrThrow(id);
        statsExpenseMapper.partialUpdate(existingEntity, dto);
        StatsExpense savedEntity = statsExpenseRepository.save(existingEntity);

        return statsExpenseMapper.toDTO(savedEntity);
    }

    /**
     * Delete a stats expense record by id.
     * @param id the stats expense id
     */
    @Override
    public void deleteStatsExpense(UUID id) {
        log.info("Deleting stats expense with id: {}", id);

        findStatsExpenseByIdOrThrow(id);
        statsExpenseRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateStatsExpenseCreateUniqueConstraints(StatsExpenseDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getAccountSumId() != null && statsExpenseRepository.existsByChamberIdAndAccountSumId(dto.getChamberId(), dto.getAccountSumId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("StatsExpense")
                    .message("StatsExpense already exists with " + "chamberId=" + dto.getChamberId() + ", " + "accountSumId=" + dto.getAccountSumId())
                    .build();
        }
    }

    /**
     * Finds an existing stats expense record by id or throws an exception.
     * @param id the stats expense id
     * @return existing StatsExpense entity
     */
    private StatsExpense findStatsExpenseByIdOrThrow(UUID id) {
        return statsExpenseRepository.findById(id)
                .orElseThrow(() -> createStatsExpenseNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the stats expense entity.
     @param id the stats expense id
     @return runtime exception
     */
    private RuntimeException createStatsExpenseNotFoundException(UUID id) {
        log.warn("StatsExpense not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("StatsExpense")
                .message("StatsExpense not found with id: " + id)
                .build();
    }

}
