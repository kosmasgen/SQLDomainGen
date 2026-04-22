package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitlei18nMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitlei18nRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.math.BigInteger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Temporary Company Titlei18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyTitlei18nServiceImpl implements TemporaryCompanyTitlei18nService {

    private final TemporaryCompanyTitlei18nRepository temporaryCompanyTitlei18nRepository;
    private final TemporaryCompanyTitlei18nMapper temporaryCompanyTitlei18nMapper;

    /**
     * Retrieves all temporary company titlei18ns records.
     * @return list of TemporaryCompanyTitlei18nDto
     */
    @Override
    public List<TemporaryCompanyTitlei18nDto> getAllTemporaryCompanyTitlei18ns() {
        log.info("Fetching all temporary company titlei18ns records.");
        return temporaryCompanyTitlei18nMapper.toDTOList(temporaryCompanyTitlei18nRepository.findAll());
    }

    /**
     * Retrieves a temporary company titlei18n record by id.
     * @param id the temporary company titlei18n id
     * @return TemporaryCompanyTitlei18nDto
     */
    @Override
    public TemporaryCompanyTitlei18nDto getTemporaryCompanyTitlei18nById(BigInteger id) {
        log.info("Fetching temporary company titlei18n with id: {}", id);

        TemporaryCompanyTitlei18n existingEntity = findTemporaryCompanyTitlei18nByIdOrThrow(id);
        return temporaryCompanyTitlei18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new temporary company titlei18n record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public TemporaryCompanyTitlei18nDto createTemporaryCompanyTitlei18n(TemporaryCompanyTitlei18nDto dto) {
        log.info("Creating temporary company titlei18n.");

        TemporaryCompanyTitlei18n entity = temporaryCompanyTitlei18nMapper.toEntity(dto);
        TemporaryCompanyTitlei18n savedEntity = temporaryCompanyTitlei18nRepository.save(entity);

        return temporaryCompanyTitlei18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing temporary company titlei18n record.
     *
     * @param id the temporary company titlei18n id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public TemporaryCompanyTitlei18nDto updateTemporaryCompanyTitlei18n(BigInteger id, TemporaryCompanyTitlei18nDto dto) {
        log.info("Updating temporary company titlei18n with id: {}", id);

        TemporaryCompanyTitlei18n existingEntity = findTemporaryCompanyTitlei18nByIdOrThrow(id);
        temporaryCompanyTitlei18nMapper.partialUpdate(existingEntity, dto);
        TemporaryCompanyTitlei18n savedEntity = temporaryCompanyTitlei18nRepository.save(existingEntity);

        return temporaryCompanyTitlei18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a temporary company titlei18n record by id.
     * @param id the temporary company titlei18n id
     */
    @Override
    public void deleteTemporaryCompanyTitlei18n(BigInteger id) {
        log.info("Deleting temporary company titlei18n with id: {}", id);

        findTemporaryCompanyTitlei18nByIdOrThrow(id);
        temporaryCompanyTitlei18nRepository.deleteById(id);
    }

    /**
     * Finds an existing temporary company titlei18n record by id or throws an exception.
     * @param id the temporary company titlei18n id
     * @return existing TemporaryCompanyTitlei18n entity
     */
    private TemporaryCompanyTitlei18n findTemporaryCompanyTitlei18nByIdOrThrow(BigInteger id) {
        return temporaryCompanyTitlei18nRepository.findById(id)
                .orElseThrow(() -> createTemporaryCompanyTitlei18nNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the temporary company titlei18n entity.
     @param id the temporary company titlei18n id
     @return runtime exception
     */
    private RuntimeException createTemporaryCompanyTitlei18nNotFoundException(BigInteger id) {
        log.warn("TemporaryCompanyTitlei18n not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyTitlei18n")
                .message("TemporaryCompanyTitlei18n not found with id: " + id)
                .build();
    }

}
