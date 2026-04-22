package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyProfessionMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import gr.knowledge.pepTest.repository.TemporaryCompanyProfessionRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.math.BigInteger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Temporary Company Profession} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyProfessionServiceImpl implements TemporaryCompanyProfessionService {

    private final TemporaryCompanyProfessionRepository temporaryCompanyProfessionRepository;
    private final TemporaryCompanyProfessionMapper temporaryCompanyProfessionMapper;

    /**
     * Retrieves all temporary company professions records.
     * @return list of TemporaryCompanyProfessionDto
     */
    @Override
    public List<TemporaryCompanyProfessionDto> getAllTemporaryCompanyProfessions() {
        log.info("Fetching all temporary company professions records.");
        return temporaryCompanyProfessionMapper.toDTOList(temporaryCompanyProfessionRepository.findAll());
    }

    /**
     * Retrieves a temporary company profession record by id.
     * @param id the temporary company profession id
     * @return TemporaryCompanyProfessionDto
     */
    @Override
    public TemporaryCompanyProfessionDto getTemporaryCompanyProfessionById(BigInteger id) {
        log.info("Fetching temporary company profession with id: {}", id);

        TemporaryCompanyProfession existingEntity = findTemporaryCompanyProfessionByIdOrThrow(id);
        return temporaryCompanyProfessionMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new temporary company profession record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public TemporaryCompanyProfessionDto createTemporaryCompanyProfession(TemporaryCompanyProfessionDto dto) {
        log.info("Creating temporary company profession.");

        TemporaryCompanyProfession entity = temporaryCompanyProfessionMapper.toEntity(dto);
        TemporaryCompanyProfession savedEntity = temporaryCompanyProfessionRepository.save(entity);

        return temporaryCompanyProfessionMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing temporary company profession record.
     *
     * @param id the temporary company profession id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public TemporaryCompanyProfessionDto updateTemporaryCompanyProfession(BigInteger id, TemporaryCompanyProfessionDto dto) {
        log.info("Updating temporary company profession with id: {}", id);

        TemporaryCompanyProfession existingEntity = findTemporaryCompanyProfessionByIdOrThrow(id);
        temporaryCompanyProfessionMapper.partialUpdate(existingEntity, dto);
        TemporaryCompanyProfession savedEntity = temporaryCompanyProfessionRepository.save(existingEntity);

        return temporaryCompanyProfessionMapper.toDTO(savedEntity);
    }

    /**
     * Delete a temporary company profession record by id.
     * @param id the temporary company profession id
     */
    @Override
    public void deleteTemporaryCompanyProfession(BigInteger id) {
        log.info("Deleting temporary company profession with id: {}", id);

        findTemporaryCompanyProfessionByIdOrThrow(id);
        temporaryCompanyProfessionRepository.deleteById(id);
    }

    /**
     * Finds an existing temporary company profession record by id or throws an exception.
     * @param id the temporary company profession id
     * @return existing TemporaryCompanyProfession entity
     */
    private TemporaryCompanyProfession findTemporaryCompanyProfessionByIdOrThrow(BigInteger id) {
        return temporaryCompanyProfessionRepository.findById(id)
                .orElseThrow(() -> createTemporaryCompanyProfessionNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the temporary company profession entity.
     @param id the temporary company profession id
     @return runtime exception
     */
    private RuntimeException createTemporaryCompanyProfessionNotFoundException(BigInteger id) {
        log.warn("TemporaryCompanyProfession not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyProfession")
                .message("TemporaryCompanyProfession not found with id: " + id)
                .build();
    }

}
