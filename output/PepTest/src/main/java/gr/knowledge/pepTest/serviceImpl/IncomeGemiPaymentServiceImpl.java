package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.mapper.IncomeGemiPaymentMapper;
import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import gr.knowledge.pepTest.repository.IncomeGemiPaymentRepository;
import gr.knowledge.pepTest.service.IncomeGemiPaymentService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Income Gemi Payment} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeGemiPaymentServiceImpl implements IncomeGemiPaymentService {

    private final IncomeGemiPaymentRepository incomeGemiPaymentRepository;
    private final IncomeGemiPaymentMapper incomeGemiPaymentMapper;

    /**
     * Retrieves all income gemi payments records.
     * @return list of IncomeGemiPaymentDto
     */
    @Override
    public List<IncomeGemiPaymentDto> getAllIncomeGemiPayments() {
        log.info("Fetching all income gemi payments records.");
        return incomeGemiPaymentMapper.toDTOList(incomeGemiPaymentRepository.findAll());
    }

    /**
     * Retrieves a income gemi payment record by id.
     * @param id the income gemi payment id
     * @return IncomeGemiPaymentDto
     */
    @Override
    public IncomeGemiPaymentDto getIncomeGemiPaymentById(UUID id) {
        log.info("Fetching income gemi payment with id: {}", id);

        IncomeGemiPayment existingEntity = findIncomeGemiPaymentByIdOrThrow(id);
        return incomeGemiPaymentMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new income gemi payment record.
     * @param dto input payload
     * @return created {@link IncomeGemiPaymentDto}
     */
    @Override
    public IncomeGemiPaymentDto createIncomeGemiPayment(IncomeGemiPaymentDto dto) {
        log.info("Creating income gemi payment.");

        validateIncomeGemiPaymentCreateUniqueConstraints(dto);

        IncomeGemiPayment entity = incomeGemiPaymentMapper.toEntity(dto);
        IncomeGemiPayment savedEntity = incomeGemiPaymentRepository.save(entity);

        return incomeGemiPaymentMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing income gemi payment record.
     *
     * @param id the income gemi payment id
     * @param dto input payload
     * @return updated {@link IncomeGemiPaymentDto}
     */
    @Override
    public IncomeGemiPaymentDto updateIncomeGemiPayment(UUID id, IncomeGemiPaymentDto dto) {
        log.info("Updating income gemi payment with id: {}", id);

        IncomeGemiPayment existingEntity = findIncomeGemiPaymentByIdOrThrow(id);
        incomeGemiPaymentMapper.partialUpdate(existingEntity, dto);
        IncomeGemiPayment savedEntity = incomeGemiPaymentRepository.save(existingEntity);

        return incomeGemiPaymentMapper.toDTO(savedEntity);
    }

    /**
     * Delete a income gemi payment record by id.
     * @param id the income gemi payment id
     */
    @Override
    public void deleteIncomeGemiPayment(UUID id) {
        log.info("Deleting income gemi payment with id: {}", id);

        findIncomeGemiPaymentByIdOrThrow(id);
        incomeGemiPaymentRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateIncomeGemiPaymentCreateUniqueConstraints(IncomeGemiPaymentDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getGemiPaymentId() != null && dto.getPaymentType() != null && dto.getCancelFlag() != null && incomeGemiPaymentRepository.existsByChamberIdAndGemiPaymentIdAndPaymentTypeAndCancelFlag(dto.getChamberId(), dto.getGemiPaymentId(), dto.getPaymentType(), dto.getCancelFlag())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("IncomeGemiPayment")
                    .message("IncomeGemiPayment already exists with " + "chamberId=" + dto.getChamberId() + ", " + "gemiPaymentId=" + dto.getGemiPaymentId() + ", " + "paymentType=" + dto.getPaymentType() + ", " + "cancelFlag=" + dto.getCancelFlag())
                    .build();
        }
    }

    /**
     * Finds an existing income gemi payment record by id or throws an exception.
     * @param id the income gemi payment id
     * @return existing IncomeGemiPayment entity
     */
    private IncomeGemiPayment findIncomeGemiPaymentByIdOrThrow(UUID id) {
        return incomeGemiPaymentRepository.findById(id)
                .orElseThrow(() -> createIncomeGemiPaymentNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the income gemi payment entity.
     @param id the income gemi payment id
     @return runtime exception
     */
    private RuntimeException createIncomeGemiPaymentNotFoundException(UUID id) {
        log.warn("IncomeGemiPayment not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeGemiPayment")
                .message("IncomeGemiPayment not found with id: " + id)
                .build();
    }

}
