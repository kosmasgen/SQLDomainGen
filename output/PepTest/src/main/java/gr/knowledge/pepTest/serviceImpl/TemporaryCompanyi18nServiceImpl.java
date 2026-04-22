package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyi18nMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import gr.knowledge.pepTest.repository.TemporaryCompanyi18nRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.math.BigInteger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Temporary Companyi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyi18nServiceImpl implements TemporaryCompanyi18nService {

    private final TemporaryCompanyi18nRepository temporaryCompanyi18nRepository;
    private final TemporaryCompanyi18nMapper temporaryCompanyi18nMapper;

    /**
     * Retrieves all temporary companyi18ns records.
     * @return list of TemporaryCompanyi18nDto
     */
    @Override
    public List<TemporaryCompanyi18nDto> getAllTemporaryCompanyi18ns() {
        log.info("Fetching all temporary companyi18ns records.");
        return temporaryCompanyi18nMapper.toDTOList(temporaryCompanyi18nRepository.findAll());
    }

    /**
     * Retrieves a temporary companyi18n record by id.
     * @param id the temporary companyi18n id
     * @return TemporaryCompanyi18nDto
     */
    @Override
    public TemporaryCompanyi18nDto getTemporaryCompanyi18nById(BigInteger id) {
        log.info("Fetching temporary companyi18n with id: {}", id);

        TemporaryCompanyi18n existingEntity = findTemporaryCompanyi18nByIdOrThrow(id);
        return temporaryCompanyi18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new temporary companyi18n record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyi18nDto}
     */
    @Override
    public TemporaryCompanyi18nDto createTemporaryCompanyi18n(TemporaryCompanyi18nDto dto) {
        log.info("Creating temporary companyi18n.");

        TemporaryCompanyi18n entity = temporaryCompanyi18nMapper.toEntity(dto);
        TemporaryCompanyi18n savedEntity = temporaryCompanyi18nRepository.save(entity);

        return temporaryCompanyi18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing temporary companyi18n record.
     *
     * @param id the temporary companyi18n id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyi18nDto}
     */
    @Override
    public TemporaryCompanyi18nDto updateTemporaryCompanyi18n(BigInteger id, TemporaryCompanyi18nDto dto) {
        log.info("Updating temporary companyi18n with id: {}", id);

        TemporaryCompanyi18n existingEntity = findTemporaryCompanyi18nByIdOrThrow(id);
        temporaryCompanyi18nMapper.partialUpdate(existingEntity, dto);
        TemporaryCompanyi18n savedEntity = temporaryCompanyi18nRepository.save(existingEntity);

        return temporaryCompanyi18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a temporary companyi18n record by id.
     * @param id the temporary companyi18n id
     */
    @Override
    public void deleteTemporaryCompanyi18n(BigInteger id) {
        log.info("Deleting temporary companyi18n with id: {}", id);

        findTemporaryCompanyi18nByIdOrThrow(id);
        temporaryCompanyi18nRepository.deleteById(id);
    }

    /**
     * Finds an existing temporary companyi18n record by id or throws an exception.
     * @param id the temporary companyi18n id
     * @return existing TemporaryCompanyi18n entity
     */
    private TemporaryCompanyi18n findTemporaryCompanyi18nByIdOrThrow(BigInteger id) {
        return temporaryCompanyi18nRepository.findById(id)
                .orElseThrow(() -> createTemporaryCompanyi18nNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the temporary companyi18n entity.
     @param id the temporary companyi18n id
     @return runtime exception
     */
    private RuntimeException createTemporaryCompanyi18nNotFoundException(BigInteger id) {
        log.warn("TemporaryCompanyi18n not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyi18n")
                .message("TemporaryCompanyi18n not found with id: " + id)
                .build();
    }

}
