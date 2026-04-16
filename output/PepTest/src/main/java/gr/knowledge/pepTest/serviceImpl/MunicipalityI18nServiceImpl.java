package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.mapper.MunicipalityI18nMapper;
import gr.knowledge.pepTest.entity.MunicipalityI18n;
import gr.knowledge.pepTest.repository.MunicipalityI18nRepository;
import gr.knowledge.pepTest.service.MunicipalityI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Municipality I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MunicipalityI18nServiceImpl implements MunicipalityI18nService {

    private final MunicipalityI18nRepository municipalityI18nRepository;
    private final MunicipalityI18nMapper municipalityI18nMapper;

    /**
     * Retrieves all municipality i18ns records.
     * @return list of MunicipalityI18nDto
     */
    @Override
    public List<MunicipalityI18nDto> getAllMunicipalityI18ns() {
        log.info("Fetching all municipality i18ns records.");
        return municipalityI18nMapper.toDTOList(municipalityI18nRepository.findAll());
    }

    /**
     * Retrieves a municipality i18n record by id.
     * @param municipalityId the municipalityId value
     * @param languageId the languageId value
     * @return MunicipalityI18nDto
     */
    @Override
    public MunicipalityI18nDto getMunicipalityI18nById(UUID municipalityId, UUID languageId) {

        String compositeId = buildCompositeId(municipalityId, languageId);
        log.info("Fetching municipality i18n with composite id: {}", compositeId);

        MunicipalityI18n existingEntity = findMunicipalityI18nByIdOrThrow(municipalityId, languageId);
        return municipalityI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new municipality i18n record.
     * @param dto input payload
     * @return created {@link MunicipalityI18nDto}
     */
    @Override
    public MunicipalityI18nDto createMunicipalityI18n(MunicipalityI18nDto dto) {
        log.info("Creating municipality i18n.");

        validateMunicipalityI18nDoesNotExist(dto);

        validateMunicipalityI18nCreateUniqueConstraints(dto);

        MunicipalityI18n entity = municipalityI18nMapper.toEntity(dto);
        MunicipalityI18n savedEntity = municipalityI18nRepository.save(entity);

        return municipalityI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing municipality i18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param municipalityId the municipalityId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link MunicipalityI18nDto}
     */
    @Override
    public MunicipalityI18nDto updateMunicipalityI18n(UUID municipalityId, UUID languageId, MunicipalityI18nDto dto) {
        String compositeId = buildCompositeId(municipalityId, languageId);

        log.info("Updating municipality i18n with composite id: {}", compositeId);

        MunicipalityI18n existingEntity = findMunicipalityI18nByIdOrThrow(municipalityId, languageId);
        municipalityI18nMapper.partialUpdate(existingEntity, dto);
        MunicipalityI18n savedEntity = municipalityI18nRepository.save(existingEntity);

        return municipalityI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a municipality i18n record by id.
     * @param municipalityId the municipality_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteMunicipalityI18n(UUID municipalityId, UUID languageId) {
        String compositeId = buildCompositeId(municipalityId, languageId);
        log.info("Deleting municipality i18n with composite id: {}", compositeId);

        findMunicipalityI18nByIdOrThrow(municipalityId, languageId);
        municipalityI18nRepository.deleteById(buildKey(municipalityId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateMunicipalityI18nCreateUniqueConstraints(MunicipalityI18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getMunicipalityId() : null) != null && dto.getChamberI18nId() != null && municipalityI18nRepository.existsByIdMunicipalityIdAndChamberI18nId((dto.getId() != null ? dto.getId().getMunicipalityId() : null), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("MunicipalityI18n")
                    .message("MunicipalityI18n already exists with " + "municipalityId=" + (dto.getId() != null ? dto.getId().getMunicipalityId() : null) + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing municipality i18n record by id or throws an exception.
     * @param municipalityId the municipalityId value
     * @param languageId the languageId value
     * @return existing MunicipalityI18n entity
     */
    private MunicipalityI18n findMunicipalityI18nByIdOrThrow(UUID municipalityId, UUID languageId) {
        return municipalityI18nRepository.findById(buildKey(municipalityId, languageId))
                .orElseThrow(() -> createMunicipalityI18nNotFoundException(municipalityId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the municipality i18n entity.
     @param municipalityId the municipality_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createMunicipalityI18nNotFoundException(UUID municipalityId, UUID languageId) {
        String compositeId = buildCompositeId(municipalityId, languageId);
        log.warn("MunicipalityI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("MunicipalityI18n")
                .message("MunicipalityI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a municipality i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateMunicipalityI18nDoesNotExist(MunicipalityI18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getMunicipalityId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        MunicipalityI18nKey key = buildKey((dto.getId() != null ? dto.getId().getMunicipalityId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (municipalityI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getMunicipalityId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("MunicipalityI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("MunicipalityI18n")
                    .message("MunicipalityI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param municipalityId the municipalityId value
     * @param languageId the languageId value
     * @return populated {@link MunicipalityI18nKey}
     */
    private MunicipalityI18nKey buildKey(UUID municipalityId, UUID languageId) {
        MunicipalityI18nKey key = new MunicipalityI18nKey();
        key.setMunicipalityId(municipalityId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param municipalityId the municipalityId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID municipalityId, UUID languageId) {
        return "municipalityId=" + municipalityId + ", " + "languageId=" + languageId;
    }

}
