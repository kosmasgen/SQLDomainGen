package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.mapper.IncomePaymentMethodMapper;
import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import gr.knowledge.pepTest.repository.IncomePaymentMethodRepository;
import gr.knowledge.pepTest.service.IncomePaymentMethodService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code IncomePaymentMethod} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomePaymentMethodServiceImpl implements IncomePaymentMethodService {

    private final IncomePaymentMethodRepository incomePaymentMethodRepository;
    private final IncomePaymentMethodMapper incomePaymentMethodMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomePaymentMethodDto}
     */
    @Override
    public List<IncomePaymentMethodDto> getAllIncomePaymentMethod() {
        log.info("Fetching all income-payment-method.");
        return incomePaymentMethodMapper.toDTO(incomePaymentMethodRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomePaymentMethodDto}
     */
    @Override
    public IncomePaymentMethodDto getIncomePaymentMethodById(UUID id) {
        log.info("Fetching income-payment-method with id: {}", id);
        IncomePaymentMethod entity = incomePaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomePaymentMethod not found with id: " + id));
        return incomePaymentMethodMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomePaymentMethodDto}
     */
    @Override
    public IncomePaymentMethodDto createIncomePaymentMethod(IncomePaymentMethodDto dto) {
        log.info("Creating income-payment-method.");
        IncomePaymentMethod entity = incomePaymentMethodMapper.toEntity(dto);
        IncomePaymentMethod saved = incomePaymentMethodRepository.save(entity);
        return incomePaymentMethodMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomePaymentMethodDto}
     */
    @Override
    public IncomePaymentMethodDto updateIncomePaymentMethod(UUID id, IncomePaymentMethodDto dto) {
        log.info("Updating income-payment-method with id: {}", id);
        incomePaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomePaymentMethod not found with id: " + id));
        IncomePaymentMethod entity = incomePaymentMethodMapper.toEntity(dto);
        entity.setId(id);
        IncomePaymentMethod saved = incomePaymentMethodRepository.save(entity);
        return incomePaymentMethodMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteIncomePaymentMethod(UUID id) {
        log.info("Deleting income-payment-method with id: {}", id);
        incomePaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomePaymentMethod not found with id: " + id));
        incomePaymentMethodRepository.deleteById(id);
    }
}
