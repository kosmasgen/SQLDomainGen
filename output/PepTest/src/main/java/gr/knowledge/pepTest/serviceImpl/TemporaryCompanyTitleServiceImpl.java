package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitleMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitleRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyTitleService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.math.BigInteger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Temporary Company Title} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyTitleServiceImpl implements TemporaryCompanyTitleService {

    private final TemporaryCompanyTitleRepository temporaryCompanyTitleRepository;
    private final TemporaryCompanyTitleMapper temporaryCompanyTitleMapper;

    /**
     * Retrieves all temporary company titles records.
     * @return list of TemporaryCompanyTitleDto
     */
    @Override
    public List<TemporaryCompanyTitleDto> getAllTemporaryCompanyTitles() {
        log.info("Fetching all temporary company titles records.");
        return temporaryCompanyTitleMapper.toDTOList(temporaryCompanyTitleRepository.findAll());
    }

    /**
     * Retrieves a temporary company title record by id.
     * @param id the temporary company title id
     * @return TemporaryCompanyTitleDto
     */
    @Override
    public TemporaryCompanyTitleDto getTemporaryCompanyTitleById(BigInteger id) {
        log.info("Fetching temporary company title with id: {}", id);

        TemporaryCompanyTitle existingEntity = findTemporaryCompanyTitleByIdOrThrow(id);
        return temporaryCompanyTitleMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new temporary company title record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitleDto}
     */
    @Override
    public TemporaryCompanyTitleDto createTemporaryCompanyTitle(TemporaryCompanyTitleDto dto) {
        log.info("Creating temporary company title.");

        TemporaryCompanyTitle entity = temporaryCompanyTitleMapper.toEntity(dto);
        TemporaryCompanyTitle savedEntity = temporaryCompanyTitleRepository.save(entity);

        return temporaryCompanyTitleMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing temporary company title record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the temporary company title id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyTitleDto}
     */
    @Override
    public TemporaryCompanyTitleDto updateTemporaryCompanyTitle(BigInteger id, TemporaryCompanyTitleDto dto) {
        log.info("Updating temporary company title with id: {}", id);

        TemporaryCompanyTitle existingEntity = findTemporaryCompanyTitleByIdOrThrow(id);
        temporaryCompanyTitleMapper.partialUpdate(existingEntity, dto);
        TemporaryCompanyTitle savedEntity = temporaryCompanyTitleRepository.save(existingEntity);

        return temporaryCompanyTitleMapper.toDTO(savedEntity);
    }

    /**
     * Delete a temporary company title record by id.
     * @param id the temporary company title id
     */
    @Override
    public void deleteTemporaryCompanyTitle(BigInteger id) {
        log.info("Deleting temporary company title with id: {}", id);

        findTemporaryCompanyTitleByIdOrThrow(id);
        temporaryCompanyTitleRepository.deleteById(id);
    }

    /**
     * Finds an existing temporary company title record by id or throws an exception.
     * @param id the temporary company title id
     * @return existing TemporaryCompanyTitle entity
     */
    private TemporaryCompanyTitle findTemporaryCompanyTitleByIdOrThrow(BigInteger id) {
        return temporaryCompanyTitleRepository.findById(id)
                .orElseThrow(() -> createTemporaryCompanyTitleNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the temporary company title entity.
     @param id the temporary company title id
     @return runtime exception
     */
    private RuntimeException createTemporaryCompanyTitleNotFoundException(BigInteger id) {
        log.warn("TemporaryCompanyTitle not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyTitle")
                .message("TemporaryCompanyTitle not found with id: " + id)
                .build();
    }

}
