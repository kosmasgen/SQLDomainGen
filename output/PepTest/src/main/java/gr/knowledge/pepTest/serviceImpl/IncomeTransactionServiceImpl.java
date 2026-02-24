package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.mapper.IncomeTransactionMapper;
import gr.knowledge.pepTest.entity.IncomeTransaction;
import gr.knowledge.pepTest.repository.IncomeTransactionRepository;
import gr.knowledge.pepTest.service.IncomeTransactionService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code IncomeTransaction} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeTransactionServiceImpl implements IncomeTransactionService {

    private final IncomeTransactionRepository incomeTransactionRepository;
    private final IncomeTransactionMapper incomeTransactionMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeTransactionDto}
     */
    @Override
    public List<IncomeTransactionDto> getAllIncomeTransaction() {
        log.info("Fetching all income-transaction.");
        return incomeTransactionMapper.toDTO(incomeTransactionRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeTransactionDto}
     */
    @Override
    public IncomeTransactionDto getIncomeTransactionById(UUID id) {
        log.info("Fetching income-transaction with id: {}", id);
        IncomeTransaction entity = incomeTransactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeTransaction not found with id: " + id));
        return incomeTransactionMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeTransactionDto}
     */
    @Override
    public IncomeTransactionDto createIncomeTransaction(IncomeTransactionDto dto) {
        log.info("Creating income-transaction.");
        IncomeTransaction entity = incomeTransactionMapper.toEntity(dto);
        IncomeTransaction saved = incomeTransactionRepository.save(entity);
        return incomeTransactionMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeTransactionDto}
     */
    @Override
    public IncomeTransactionDto updateIncomeTransaction(UUID id, IncomeTransactionDto dto) {
        log.info("Updating income-transaction with id: {}", id);
        incomeTransactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeTransaction not found with id: " + id));
        IncomeTransaction entity = incomeTransactionMapper.toEntity(dto);
        entity.setId(id);
        IncomeTransaction saved = incomeTransactionRepository.save(entity);
        return incomeTransactionMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteIncomeTransaction(UUID id) {
        log.info("Deleting income-transaction with id: {}", id);
        incomeTransactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeTransaction not found with id: " + id));
        incomeTransactionRepository.deleteById(id);
    }
}
