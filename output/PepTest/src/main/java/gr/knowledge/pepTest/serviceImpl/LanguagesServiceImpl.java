package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.mapper.LanguagesMapper;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.repository.LanguagesRepository;
import gr.knowledge.pepTest.service.LanguagesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Languages} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LanguagesServiceImpl implements LanguagesService {

    private final LanguagesRepository languagesRepository;
    private final LanguagesMapper languagesMapper;

    /**
     * Retrieves all languageses records.
     * @return list of LanguagesDto
     */
    @Override
    public List<LanguagesDto> getAllLanguageses() {
        log.info("Fetching all languageses records.");
        return languagesMapper.toDTOList(languagesRepository.findAll());
    }

    /**
     * Retrieves a languages record by id.
     * @param id the languages id
     * @return LanguagesDto
     */
    @Override
    public LanguagesDto getLanguagesById(UUID id) {
        log.info("Fetching languages with id: {}", id);

        Languages existingEntity = findLanguagesByIdOrThrow(id);
        return languagesMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new languages record.
     * @param dto input payload
     * @return created {@link LanguagesDto}
     */
    @Override
    public LanguagesDto createLanguages(LanguagesDto dto) {
        log.info("Creating languages.");

        validateLanguagesCreateUniqueConstraints(dto);

        Languages entity = languagesMapper.toEntity(dto);
        Languages savedEntity = languagesRepository.save(entity);

        return languagesMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing languages record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the languages id
     * @param dto input payload with partial fields
     * @return updated {@link LanguagesDto}
     */
    @Override
    public LanguagesDto updateLanguages(UUID id, LanguagesDto dto) {
        log.info("Updating languages with id: {}", id);

        Languages existingEntity = findLanguagesByIdOrThrow(id);
        languagesMapper.partialUpdate(existingEntity, dto);
        Languages savedEntity = languagesRepository.save(existingEntity);

        return languagesMapper.toDTO(savedEntity);
    }

    /**
     * Delete a languages record by id.
     * @param id the languages id
     */
    @Override
    public void deleteLanguages(UUID id) {
        log.info("Deleting languages with id: {}", id);

        findLanguagesByIdOrThrow(id);
        languagesRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateLanguagesCreateUniqueConstraints(LanguagesDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getCd() != null && languagesRepository.existsByCd(dto.getCd())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Languages")
                    .message("Languages already exists with cd: " + dto.getCd())
                    .build();
        }
        if (dto.getChamberLanguageId() != null && languagesRepository.existsByChamberLanguageId(dto.getChamberLanguageId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Languages")
                    .message("Languages already exists with chamberLanguageId: " + dto.getChamberLanguageId())
                    .build();
        }
    }

    /**
     * Finds an existing languages record by id or throws an exception.
     * @param id the languages id
     * @return existing Languages entity
     */
    private Languages findLanguagesByIdOrThrow(UUID id) {
        return languagesRepository.findById(id)
                .orElseThrow(() -> createLanguagesNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the languages entity.
     @param id the languages id
     @return runtime exception
     */
    private RuntimeException createLanguagesNotFoundException(UUID id) {
        log.warn("Languages not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Languages")
                .message("Languages not found with id: " + id)
                .build();
    }

}
