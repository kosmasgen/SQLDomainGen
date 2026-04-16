package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.mapper.ChAppUserContactI18nMapper;
import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import gr.knowledge.pepTest.repository.ChAppUserContactI18nRepository;
import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Ch App User Contact I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChAppUserContactI18nServiceImpl implements ChAppUserContactI18nService {

    private final ChAppUserContactI18nRepository chAppUserContactI18nRepository;
    private final ChAppUserContactI18nMapper chAppUserContactI18nMapper;

    /**
     * Retrieves all ch app user contact i18ns records.
     * @return list of ChAppUserContactI18nDto
     */
    @Override
    public List<ChAppUserContactI18nDto> getAllChAppUserContactI18ns() {
        log.info("Fetching all ch app user contact i18ns records.");
        return chAppUserContactI18nMapper.toDTOList(chAppUserContactI18nRepository.findAll());
    }

    /**
     * Retrieves a ch app user contact i18n record by id.
     * @param chAppUserContactId the chAppUserContactId value
     * @param languageId the languageId value
     * @return ChAppUserContactI18nDto
     */
    @Override
    public ChAppUserContactI18nDto getChAppUserContactI18nById(UUID chAppUserContactId, UUID languageId) {

        String compositeId = buildCompositeId(chAppUserContactId, languageId);
        log.info("Fetching ch app user contact i18n with composite id: {}", compositeId);

        ChAppUserContactI18n existingEntity = findChAppUserContactI18nByIdOrThrow(chAppUserContactId, languageId);
        return chAppUserContactI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new ch app user contact i18n record.
     * @param dto input payload
     * @return created {@link ChAppUserContactI18nDto}
     */
    @Override
    public ChAppUserContactI18nDto createChAppUserContactI18n(ChAppUserContactI18nDto dto) {
        log.info("Creating ch app user contact i18n.");

        validateChAppUserContactI18nDoesNotExist(dto);

        ChAppUserContactI18n entity = chAppUserContactI18nMapper.toEntity(dto);
        ChAppUserContactI18n savedEntity = chAppUserContactI18nRepository.save(entity);

        return chAppUserContactI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing ch app user contact i18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param chAppUserContactId the chAppUserContactId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link ChAppUserContactI18nDto}
     */
    @Override
    public ChAppUserContactI18nDto updateChAppUserContactI18n(UUID chAppUserContactId, UUID languageId, ChAppUserContactI18nDto dto) {
        String compositeId = buildCompositeId(chAppUserContactId, languageId);

        log.info("Updating ch app user contact i18n with composite id: {}", compositeId);

        ChAppUserContactI18n existingEntity = findChAppUserContactI18nByIdOrThrow(chAppUserContactId, languageId);
        chAppUserContactI18nMapper.partialUpdate(existingEntity, dto);
        ChAppUserContactI18n savedEntity = chAppUserContactI18nRepository.save(existingEntity);

        return chAppUserContactI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a ch app user contact i18n record by id.
     * @param chAppUserContactId the ch_app_user_contact_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteChAppUserContactI18n(UUID chAppUserContactId, UUID languageId) {
        String compositeId = buildCompositeId(chAppUserContactId, languageId);
        log.info("Deleting ch app user contact i18n with composite id: {}", compositeId);

        findChAppUserContactI18nByIdOrThrow(chAppUserContactId, languageId);
        chAppUserContactI18nRepository.deleteById(buildKey(chAppUserContactId, languageId));
    }

    /**
     * Finds an existing ch app user contact i18n record by id or throws an exception.
     * @param chAppUserContactId the chAppUserContactId value
     * @param languageId the languageId value
     * @return existing ChAppUserContactI18n entity
     */
    private ChAppUserContactI18n findChAppUserContactI18nByIdOrThrow(UUID chAppUserContactId, UUID languageId) {
        return chAppUserContactI18nRepository.findById(buildKey(chAppUserContactId, languageId))
                .orElseThrow(() -> createChAppUserContactI18nNotFoundException(chAppUserContactId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the ch app user contact i18n entity.
     @param chAppUserContactId the ch_app_user_contact_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createChAppUserContactI18nNotFoundException(UUID chAppUserContactId, UUID languageId) {
        String compositeId = buildCompositeId(chAppUserContactId, languageId);
        log.warn("ChAppUserContactI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChAppUserContactI18n")
                .message("ChAppUserContactI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a ch app user contact i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateChAppUserContactI18nDoesNotExist(ChAppUserContactI18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getChAppUserContactId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        ChAppUserContactI18nKey key = buildKey((dto.getId() != null ? dto.getId().getChAppUserContactId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (chAppUserContactI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getChAppUserContactId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("ChAppUserContactI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ChAppUserContactI18n")
                    .message("ChAppUserContactI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param chAppUserContactId the chAppUserContactId value
     * @param languageId the languageId value
     * @return populated {@link ChAppUserContactI18nKey}
     */
    private ChAppUserContactI18nKey buildKey(UUID chAppUserContactId, UUID languageId) {
        ChAppUserContactI18nKey key = new ChAppUserContactI18nKey();
        key.setChAppUserContactId(chAppUserContactId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param chAppUserContactId the chAppUserContactId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID chAppUserContactId, UUID languageId) {
        return "chAppUserContactId=" + chAppUserContactId + ", " + "languageId=" + languageId;
    }

}
