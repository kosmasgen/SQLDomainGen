package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.mapper.IncomeGemiPaymentMapper;
import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import gr.knowledge.pepTest.repository.IncomeGemiPaymentRepository;
import gr.knowledge.pepTest.service.IncomeGemiPaymentService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code IncomeGemiPayment} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeGemiPaymentServiceImpl implements IncomeGemiPaymentService {

    private final IncomeGemiPaymentRepository incomeGemiPaymentRepository;
    private final IncomeGemiPaymentMapper incomeGemiPaymentMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeGemiPaymentDto}
     */
    @Override
    public List<IncomeGemiPaymentDto> getAllIncomeGemiPayment() {
        log.info("Fetching all income-gemi-payment.");
        return incomeGemiPaymentMapper.toDTO(incomeGemiPaymentRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeGemiPaymentDto}
     */
    @Override
    public IncomeGemiPaymentDto getIncomeGemiPaymentById(UUID id) {
        log.info("Fetching income-gemi-payment with id: {}", id);
        IncomeGemiPayment entity = incomeGemiPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeGemiPayment not found with id: " + id));
        return incomeGemiPaymentMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeGemiPaymentDto}
     */
    @Override
    public IncomeGemiPaymentDto createIncomeGemiPayment(IncomeGemiPaymentDto dto) {
        log.info("Creating income-gemi-payment.");
        IncomeGemiPayment entity = incomeGemiPaymentMapper.toEntity(dto);
        IncomeGemiPayment saved = incomeGemiPaymentRepository.save(entity);
        return incomeGemiPaymentMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeGemiPaymentDto}
     */
    @Override
    public IncomeGemiPaymentDto updateIncomeGemiPayment(UUID id, IncomeGemiPaymentDto dto) {
        log.info("Updating income-gemi-payment with id: {}", id);
        incomeGemiPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeGemiPayment not found with id: " + id));
        IncomeGemiPayment entity = incomeGemiPaymentMapper.toEntity(dto);
        entity.setId(id);
        IncomeGemiPayment saved = incomeGemiPaymentRepository.save(entity);
        return incomeGemiPaymentMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteIncomeGemiPayment(UUID id) {
        log.info("Deleting income-gemi-payment with id: {}", id);
        incomeGemiPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeGemiPayment not found with id: " + id));
        incomeGemiPaymentRepository.deleteById(id);
    }
}
