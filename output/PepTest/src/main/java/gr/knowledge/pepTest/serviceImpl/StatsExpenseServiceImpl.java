package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.mapper.StatsExpenseMapper;
import gr.knowledge.pepTest.entity.StatsExpense;
import gr.knowledge.pepTest.repository.StatsExpenseRepository;
import gr.knowledge.pepTest.service.StatsExpenseService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code StatsExpense} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class StatsExpenseServiceImpl implements StatsExpenseService {

    private final StatsExpenseRepository statsExpenseRepository;
    private final StatsExpenseMapper statsExpenseMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link StatsExpenseDto}
     */
    @Override
    public List<StatsExpenseDto> getAllStatsExpense() {
        log.info("Fetching all stats-expense.");
        return statsExpenseMapper.toDTO(statsExpenseRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link StatsExpenseDto}
     */
    @Override
    public StatsExpenseDto getStatsExpenseById(UUID id) {
        log.info("Fetching stats-expense with id: {}", id);
        StatsExpense entity = statsExpenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatsExpense not found with id: " + id));
        return statsExpenseMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link StatsExpenseDto}
     */
    @Override
    public StatsExpenseDto createStatsExpense(StatsExpenseDto dto) {
        log.info("Creating stats-expense.");
        StatsExpense entity = statsExpenseMapper.toEntity(dto);
        StatsExpense saved = statsExpenseRepository.save(entity);
        return statsExpenseMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link StatsExpenseDto}
     */
    @Override
    public StatsExpenseDto updateStatsExpense(UUID id, StatsExpenseDto dto) {
        log.info("Updating stats-expense with id: {}", id);
        statsExpenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatsExpense not found with id: " + id));
        StatsExpense entity = statsExpenseMapper.toEntity(dto);
        entity.setId(id);
        StatsExpense saved = statsExpenseRepository.save(entity);
        return statsExpenseMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteStatsExpense(UUID id) {
        log.info("Deleting stats-expense with id: {}", id);
        statsExpenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatsExpense not found with id: " + id));
        statsExpenseRepository.deleteById(id);
    }
}
