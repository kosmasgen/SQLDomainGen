package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.mapper.IncomeTypeMapper;
import gr.knowledge.pepTest.entity.IncomeType;
import gr.knowledge.pepTest.repository.IncomeTypeRepository;
import gr.knowledge.pepTest.service.IncomeTypeService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Income Type} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeTypeServiceImpl implements IncomeTypeService {

    private final IncomeTypeRepository incomeTypeRepository;
    private final IncomeTypeMapper incomeTypeMapper;

    /**
     * Retrieves all income types records.
     * @return list of IncomeTypeDto
     */
    @Override
    public List<IncomeTypeDto> getAllIncomeTypes() {
        log.info("Fetching all income types records.");
        return incomeTypeMapper.toDTOList(incomeTypeRepository.findAll());
    }

    /**
     * Retrieves a income type record by id.
     * @param id the income type id
     * @return IncomeTypeDto
     */
    @Override
    public IncomeTypeDto getIncomeTypeById(UUID id) {
        log.info("Fetching income type with id: {}", id);

        IncomeType existingEntity = findIncomeTypeByIdOrThrow(id);
        return incomeTypeMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new income type record.
     * @param dto input payload
     * @return created {@link IncomeTypeDto}
     */
    @Override
    public IncomeTypeDto createIncomeType(IncomeTypeDto dto) {
        log.info("Creating income type.");

        validateIncomeTypeCreateUniqueConstraints(dto);

        IncomeType entity = incomeTypeMapper.toEntity(dto);
        IncomeType savedEntity = incomeTypeRepository.save(entity);

        return incomeTypeMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing income type record.
     *
     * @param id the income type id
     * @param dto input payload
     * @return updated {@link IncomeTypeDto}
     */
    @Override
    public IncomeTypeDto updateIncomeType(UUID id, IncomeTypeDto dto) {
        log.info("Updating income type with id: {}", id);

        IncomeType existingEntity = findIncomeTypeByIdOrThrow(id);
        incomeTypeMapper.partialUpdate(existingEntity, dto);
        IncomeType savedEntity = incomeTypeRepository.save(existingEntity);

        return incomeTypeMapper.toDTO(savedEntity);
    }

    /**
     * Delete a income type record by id.
     * @param id the income type id
     */
    @Override
    public void deleteIncomeType(UUID id) {
        log.info("Deleting income type with id: {}", id);

        findIncomeTypeByIdOrThrow(id);
        incomeTypeRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateIncomeTypeCreateUniqueConstraints(IncomeTypeDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberTypeId() != null && incomeTypeRepository.existsByChamberIdAndChamberTypeId(dto.getChamberId(), dto.getChamberTypeId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("IncomeType")
                    .message("IncomeType already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberTypeId=" + dto.getChamberTypeId())
                    .build();
        }
    }

    /**
     * Finds an existing income type record by id or throws an exception.
     * @param id the income type id
     * @return existing IncomeType entity
     */
    private IncomeType findIncomeTypeByIdOrThrow(UUID id) {
        return incomeTypeRepository.findById(id)
                .orElseThrow(() -> createIncomeTypeNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the income type entity.
     @param id the income type id
     @return runtime exception
     */
    private RuntimeException createIncomeTypeNotFoundException(UUID id) {
        log.warn("IncomeType not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeType")
                .message("IncomeType not found with id: " + id)
                .build();
    }

}
