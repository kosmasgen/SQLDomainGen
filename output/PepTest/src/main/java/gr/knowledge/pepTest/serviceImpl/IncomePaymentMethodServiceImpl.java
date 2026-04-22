package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.mapper.IncomePaymentMethodMapper;
import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import gr.knowledge.pepTest.repository.IncomePaymentMethodRepository;
import gr.knowledge.pepTest.service.IncomePaymentMethodService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Income Payment Method} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomePaymentMethodServiceImpl implements IncomePaymentMethodService {

    private final IncomePaymentMethodRepository incomePaymentMethodRepository;
    private final IncomePaymentMethodMapper incomePaymentMethodMapper;

    /**
     * Retrieves all income payment methods records.
     * @return list of IncomePaymentMethodDto
     */
    @Override
    public List<IncomePaymentMethodDto> getAllIncomePaymentMethods() {
        log.info("Fetching all income payment methods records.");
        return incomePaymentMethodMapper.toDTOList(incomePaymentMethodRepository.findAll());
    }

    /**
     * Retrieves a income payment method record by id.
     * @param id the income payment method id
     * @return IncomePaymentMethodDto
     */
    @Override
    public IncomePaymentMethodDto getIncomePaymentMethodById(UUID id) {
        log.info("Fetching income payment method with id: {}", id);

        IncomePaymentMethod existingEntity = findIncomePaymentMethodByIdOrThrow(id);
        return incomePaymentMethodMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new income payment method record.
     * @param dto input payload
     * @return created {@link IncomePaymentMethodDto}
     */
    @Override
    public IncomePaymentMethodDto createIncomePaymentMethod(IncomePaymentMethodDto dto) {
        log.info("Creating income payment method.");

        validateIncomePaymentMethodCreateUniqueConstraints(dto);

        IncomePaymentMethod entity = incomePaymentMethodMapper.toEntity(dto);
        IncomePaymentMethod savedEntity = incomePaymentMethodRepository.save(entity);

        return incomePaymentMethodMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing income payment method record.
     *
     * @param id the income payment method id
     * @param dto input payload
     * @return updated {@link IncomePaymentMethodDto}
     */
    @Override
    public IncomePaymentMethodDto updateIncomePaymentMethod(UUID id, IncomePaymentMethodDto dto) {
        log.info("Updating income payment method with id: {}", id);

        IncomePaymentMethod existingEntity = findIncomePaymentMethodByIdOrThrow(id);
        incomePaymentMethodMapper.partialUpdate(existingEntity, dto);
        IncomePaymentMethod savedEntity = incomePaymentMethodRepository.save(existingEntity);

        return incomePaymentMethodMapper.toDTO(savedEntity);
    }

    /**
     * Delete a income payment method record by id.
     * @param id the income payment method id
     */
    @Override
    public void deleteIncomePaymentMethod(UUID id) {
        log.info("Deleting income payment method with id: {}", id);

        findIncomePaymentMethodByIdOrThrow(id);
        incomePaymentMethodRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateIncomePaymentMethodCreateUniqueConstraints(IncomePaymentMethodDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberPayMethodId() != null && incomePaymentMethodRepository.existsByChamberIdAndChamberPayMethodId(dto.getChamberId(), dto.getChamberPayMethodId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("IncomePaymentMethod")
                    .message("IncomePaymentMethod already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberPayMethodId=" + dto.getChamberPayMethodId())
                    .build();
        }
    }

    /**
     * Finds an existing income payment method record by id or throws an exception.
     * @param id the income payment method id
     * @return existing IncomePaymentMethod entity
     */
    private IncomePaymentMethod findIncomePaymentMethodByIdOrThrow(UUID id) {
        return incomePaymentMethodRepository.findById(id)
                .orElseThrow(() -> createIncomePaymentMethodNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the income payment method entity.
     @param id the income payment method id
     @return runtime exception
     */
    private RuntimeException createIncomePaymentMethodNotFoundException(UUID id) {
        log.warn("IncomePaymentMethod not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomePaymentMethod")
                .message("IncomePaymentMethod not found with id: " + id)
                .build();
    }

}
