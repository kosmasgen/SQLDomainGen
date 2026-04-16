package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.mapper.IncomeTransactionMapper;
import gr.knowledge.pepTest.entity.IncomeTransaction;
import gr.knowledge.pepTest.repository.IncomeTransactionRepository;
import gr.knowledge.pepTest.service.IncomeTransactionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Income Transaction} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeTransactionServiceImpl implements IncomeTransactionService {

    private final IncomeTransactionRepository incomeTransactionRepository;
    private final IncomeTransactionMapper incomeTransactionMapper;

    /**
     * Retrieves all income transactions records.
     * @return list of IncomeTransactionDto
     */
    @Override
    public List<IncomeTransactionDto> getAllIncomeTransactions() {
        log.info("Fetching all income transactions records.");
        return incomeTransactionMapper.toDTOList(incomeTransactionRepository.findAll());
    }

    /**
     * Retrieves a income transaction record by id.
     * @param id the income transaction id
     * @return IncomeTransactionDto
     */
    @Override
    public IncomeTransactionDto getIncomeTransactionById(UUID id) {
        log.info("Fetching income transaction with id: {}", id);

        IncomeTransaction existingEntity = findIncomeTransactionByIdOrThrow(id);
        return incomeTransactionMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new income transaction record.
     * @param dto input payload
     * @return created {@link IncomeTransactionDto}
     */
    @Override
    public IncomeTransactionDto createIncomeTransaction(IncomeTransactionDto dto) {
        log.info("Creating income transaction.");

        validateIncomeTransactionCreateUniqueConstraints(dto);

        IncomeTransaction entity = incomeTransactionMapper.toEntity(dto);
        IncomeTransaction savedEntity = incomeTransactionRepository.save(entity);

        return incomeTransactionMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing income transaction record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the income transaction id
     * @param dto input payload with partial fields
     * @return updated {@link IncomeTransactionDto}
     */
    @Override
    public IncomeTransactionDto updateIncomeTransaction(UUID id, IncomeTransactionDto dto) {
        log.info("Updating income transaction with id: {}", id);

        IncomeTransaction existingEntity = findIncomeTransactionByIdOrThrow(id);
        incomeTransactionMapper.partialUpdate(existingEntity, dto);
        IncomeTransaction savedEntity = incomeTransactionRepository.save(existingEntity);

        return incomeTransactionMapper.toDTO(savedEntity);
    }

    /**
     * Delete a income transaction record by id.
     * @param id the income transaction id
     */
    @Override
    public void deleteIncomeTransaction(UUID id) {
        log.info("Deleting income transaction with id: {}", id);

        findIncomeTransactionByIdOrThrow(id);
        incomeTransactionRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateIncomeTransactionCreateUniqueConstraints(IncomeTransactionDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberInTransdId() != null && dto.getIsKratisi() != null && incomeTransactionRepository.existsByChamberIdAndChamberInTransdIdAndIsKratisi(dto.getChamberId(), dto.getChamberInTransdId(), dto.getIsKratisi())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("IncomeTransaction")
                    .message("IncomeTransaction already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberInTransdId=" + dto.getChamberInTransdId() + ", " + "isKratisi=" + dto.getIsKratisi())
                    .build();
        }
    }

    /**
     * Finds an existing income transaction record by id or throws an exception.
     * @param id the income transaction id
     * @return existing IncomeTransaction entity
     */
    private IncomeTransaction findIncomeTransactionByIdOrThrow(UUID id) {
        return incomeTransactionRepository.findById(id)
                .orElseThrow(() -> createIncomeTransactionNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the income transaction entity.
     @param id the income transaction id
     @return runtime exception
     */
    private RuntimeException createIncomeTransactionNotFoundException(UUID id) {
        log.warn("IncomeTransaction not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeTransaction")
                .message("IncomeTransaction not found with id: " + id)
                .build();
    }

}
