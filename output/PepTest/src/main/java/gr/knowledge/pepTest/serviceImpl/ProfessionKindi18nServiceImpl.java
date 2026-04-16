package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.mapper.ProfessionKindi18nMapper;
import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import gr.knowledge.pepTest.repository.ProfessionKindi18nRepository;
import gr.knowledge.pepTest.service.ProfessionKindi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Profession Kindi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionKindi18nServiceImpl implements ProfessionKindi18nService {

    private final ProfessionKindi18nRepository professionKindi18nRepository;
    private final ProfessionKindi18nMapper professionKindi18nMapper;

    /**
     * Retrieves all profession kindi18ns records.
     * @return list of ProfessionKindi18nDto
     */
    @Override
    public List<ProfessionKindi18nDto> getAllProfessionKindi18ns() {
        log.info("Fetching all profession kindi18ns records.");
        return professionKindi18nMapper.toDTOList(professionKindi18nRepository.findAll());
    }

    /**
     * Retrieves a profession kindi18n record by id.
     * @param professionKindId the professionKindId value
     * @param languageId the languageId value
     * @return ProfessionKindi18nDto
     */
    @Override
    public ProfessionKindi18nDto getProfessionKindi18nById(UUID professionKindId, UUID languageId) {

        String compositeId = buildCompositeId(professionKindId, languageId);
        log.info("Fetching profession kindi18n with composite id: {}", compositeId);

        ProfessionKindi18n existingEntity = findProfessionKindi18nByIdOrThrow(professionKindId, languageId);
        return professionKindi18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new profession kindi18n record.
     * @param dto input payload
     * @return created {@link ProfessionKindi18nDto}
     */
    @Override
    public ProfessionKindi18nDto createProfessionKindi18n(ProfessionKindi18nDto dto) {
        log.info("Creating profession kindi18n.");

        validateProfessionKindi18nDoesNotExist(dto);

        validateProfessionKindi18nCreateUniqueConstraints(dto);

        ProfessionKindi18n entity = professionKindi18nMapper.toEntity(dto);
        ProfessionKindi18n savedEntity = professionKindi18nRepository.save(entity);

        return professionKindi18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing profession kindi18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param professionKindId the professionKindId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionKindi18nDto}
     */
    @Override
    public ProfessionKindi18nDto updateProfessionKindi18n(UUID professionKindId, UUID languageId, ProfessionKindi18nDto dto) {
        String compositeId = buildCompositeId(professionKindId, languageId);

        log.info("Updating profession kindi18n with composite id: {}", compositeId);

        ProfessionKindi18n existingEntity = findProfessionKindi18nByIdOrThrow(professionKindId, languageId);
        professionKindi18nMapper.partialUpdate(existingEntity, dto);
        ProfessionKindi18n savedEntity = professionKindi18nRepository.save(existingEntity);

        return professionKindi18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a profession kindi18n record by id.
     * @param professionKindId the profession_kind_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteProfessionKindi18n(UUID professionKindId, UUID languageId) {
        String compositeId = buildCompositeId(professionKindId, languageId);
        log.info("Deleting profession kindi18n with composite id: {}", compositeId);

        findProfessionKindi18nByIdOrThrow(professionKindId, languageId);
        professionKindi18nRepository.deleteById(buildKey(professionKindId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateProfessionKindi18nCreateUniqueConstraints(ProfessionKindi18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getProfessionKindId() : null) != null && dto.getChamberI18nId() != null && professionKindi18nRepository.existsByIdProfessionKindIdAndChamberI18nId((dto.getId() != null ? dto.getId().getProfessionKindId() : null), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ProfessionKindi18n")
                    .message("ProfessionKindi18n already exists with " + "professionKindId=" + (dto.getId() != null ? dto.getId().getProfessionKindId() : null) + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing profession kindi18n record by id or throws an exception.
     * @param professionKindId the professionKindId value
     * @param languageId the languageId value
     * @return existing ProfessionKindi18n entity
     */
    private ProfessionKindi18n findProfessionKindi18nByIdOrThrow(UUID professionKindId, UUID languageId) {
        return professionKindi18nRepository.findById(buildKey(professionKindId, languageId))
                .orElseThrow(() -> createProfessionKindi18nNotFoundException(professionKindId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the profession kindi18n entity.
     @param professionKindId the profession_kind_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createProfessionKindi18nNotFoundException(UUID professionKindId, UUID languageId) {
        String compositeId = buildCompositeId(professionKindId, languageId);
        log.warn("ProfessionKindi18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionKindi18n")
                .message("ProfessionKindi18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a profession kindi18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateProfessionKindi18nDoesNotExist(ProfessionKindi18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getProfessionKindId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        ProfessionKindi18nKey key = buildKey((dto.getId() != null ? dto.getId().getProfessionKindId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (professionKindi18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getProfessionKindId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("ProfessionKindi18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ProfessionKindi18n")
                    .message("ProfessionKindi18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param professionKindId the professionKindId value
     * @param languageId the languageId value
     * @return populated {@link ProfessionKindi18nKey}
     */
    private ProfessionKindi18nKey buildKey(UUID professionKindId, UUID languageId) {
        ProfessionKindi18nKey key = new ProfessionKindi18nKey();
        key.setProfessionKindId(professionKindId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param professionKindId the professionKindId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID professionKindId, UUID languageId) {
        return "professionKindId=" + professionKindId + ", " + "languageId=" + languageId;
    }

}
