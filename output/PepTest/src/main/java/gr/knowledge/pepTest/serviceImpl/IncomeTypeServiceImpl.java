package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.mapper.IncomeTypeMapper;
import gr.knowledge.pepTest.entity.IncomeType;
import gr.knowledge.pepTest.repository.IncomeTypeRepository;
import gr.knowledge.pepTest.service.IncomeTypeService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code IncomeType} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class IncomeTypeServiceImpl implements IncomeTypeService {

    private final IncomeTypeRepository incomeTypeRepository;
    private final IncomeTypeMapper incomeTypeMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link IncomeTypeDto}
     */
    @Override
    public List<IncomeTypeDto> getAllIncomeType() {
        log.info("Fetching all income-type.");
        return incomeTypeMapper.toDTO(incomeTypeRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link IncomeTypeDto}
     */
    @Override
    public IncomeTypeDto getIncomeTypeById(UUID id) {
        log.info("Fetching income-type with id: {}", id);
        IncomeType entity = incomeTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeType not found with id: " + id));
        return incomeTypeMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link IncomeTypeDto}
     */
    @Override
    public IncomeTypeDto createIncomeType(IncomeTypeDto dto) {
        log.info("Creating income-type.");
        IncomeType entity = incomeTypeMapper.toEntity(dto);
        IncomeType saved = incomeTypeRepository.save(entity);
        return incomeTypeMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link IncomeTypeDto}
     */
    @Override
    public IncomeTypeDto updateIncomeType(UUID id, IncomeTypeDto dto) {
        log.info("Updating income-type with id: {}", id);
        incomeTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeType not found with id: " + id));
        IncomeType entity = incomeTypeMapper.toEntity(dto);
        entity.setId(id);
        IncomeType saved = incomeTypeRepository.save(entity);
        return incomeTypeMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteIncomeType(UUID id) {
        log.info("Deleting income-type with id: {}", id);
        incomeTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "IncomeType not found with id: " + id));
        incomeTypeRepository.deleteById(id);
    }
}
