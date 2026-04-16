package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.mapper.Professioni18nMapper;
import gr.knowledge.pepTest.entity.Professioni18n;
import gr.knowledge.pepTest.repository.Professioni18nRepository;
import gr.knowledge.pepTest.service.Professioni18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.Professioni18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Professioni18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Professioni18nServiceImpl implements Professioni18nService {

    private final Professioni18nRepository professioni18nRepository;
    private final Professioni18nMapper professioni18nMapper;

    /**
     * Retrieves all professioni18ns records.
     * @return list of Professioni18nDto
     */
    @Override
    public List<Professioni18nDto> getAllProfessioni18ns() {
        log.info("Fetching all professioni18ns records.");
        return professioni18nMapper.toDTOList(professioni18nRepository.findAll());
    }

    /**
     * Retrieves a professioni18n record by id.
     * @param professionId the professionId value
     * @param languageId the languageId value
     * @return Professioni18nDto
     */
    @Override
    public Professioni18nDto getProfessioni18nById(UUID professionId, UUID languageId) {

        String compositeId = buildCompositeId(professionId, languageId);
        log.info("Fetching professioni18n with composite id: {}", compositeId);

        Professioni18n existingEntity = findProfessioni18nByIdOrThrow(professionId, languageId);
        return professioni18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new professioni18n record.
     * @param dto input payload
     * @return created {@link Professioni18nDto}
     */
    @Override
    public Professioni18nDto createProfessioni18n(Professioni18nDto dto) {
        log.info("Creating professioni18n.");

        validateProfessioni18nDoesNotExist(dto);

        validateProfessioni18nCreateUniqueConstraints(dto);

        Professioni18n entity = professioni18nMapper.toEntity(dto);
        Professioni18n savedEntity = professioni18nRepository.save(entity);

        return professioni18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing professioni18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param professionId the professionId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link Professioni18nDto}
     */
    @Override
    public Professioni18nDto updateProfessioni18n(UUID professionId, UUID languageId, Professioni18nDto dto) {
        String compositeId = buildCompositeId(professionId, languageId);

        log.info("Updating professioni18n with composite id: {}", compositeId);

        Professioni18n existingEntity = findProfessioni18nByIdOrThrow(professionId, languageId);
        professioni18nMapper.partialUpdate(existingEntity, dto);
        Professioni18n savedEntity = professioni18nRepository.save(existingEntity);

        return professioni18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a professioni18n record by id.
     * @param professionId the profession_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteProfessioni18n(UUID professionId, UUID languageId) {
        String compositeId = buildCompositeId(professionId, languageId);
        log.info("Deleting professioni18n with composite id: {}", compositeId);

        findProfessioni18nByIdOrThrow(professionId, languageId);
        professioni18nRepository.deleteById(buildKey(professionId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateProfessioni18nCreateUniqueConstraints(Professioni18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getProfessionId() : null) != null && dto.getChamberI18nId() != null && professioni18nRepository.existsByIdProfessionIdAndChamberI18nId((dto.getId() != null ? dto.getId().getProfessionId() : null), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Professioni18n")
                    .message("Professioni18n already exists with " + "professionId=" + (dto.getId() != null ? dto.getId().getProfessionId() : null) + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing professioni18n record by id or throws an exception.
     * @param professionId the professionId value
     * @param languageId the languageId value
     * @return existing Professioni18n entity
     */
    private Professioni18n findProfessioni18nByIdOrThrow(UUID professionId, UUID languageId) {
        return professioni18nRepository.findById(buildKey(professionId, languageId))
                .orElseThrow(() -> createProfessioni18nNotFoundException(professionId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the professioni18n entity.
     @param professionId the profession_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createProfessioni18nNotFoundException(UUID professionId, UUID languageId) {
        String compositeId = buildCompositeId(professionId, languageId);
        log.warn("Professioni18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Professioni18n")
                .message("Professioni18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a professioni18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateProfessioni18nDoesNotExist(Professioni18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getProfessionId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        Professioni18nKey key = buildKey((dto.getId() != null ? dto.getId().getProfessionId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (professioni18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getProfessionId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("Professioni18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Professioni18n")
                    .message("Professioni18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param professionId the professionId value
     * @param languageId the languageId value
     * @return populated {@link Professioni18nKey}
     */
    private Professioni18nKey buildKey(UUID professionId, UUID languageId) {
        Professioni18nKey key = new Professioni18nKey();
        key.setProfessionId(professionId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param professionId the professionId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID professionId, UUID languageId) {
        return "professionId=" + professionId + ", " + "languageId=" + languageId;
    }

}
