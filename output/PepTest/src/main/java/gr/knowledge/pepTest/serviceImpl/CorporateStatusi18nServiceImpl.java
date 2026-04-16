package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.mapper.CorporateStatusi18nMapper;
import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import gr.knowledge.pepTest.repository.CorporateStatusi18nRepository;
import gr.knowledge.pepTest.service.CorporateStatusi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CorporateStatusi18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Corporate Statusi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusi18nServiceImpl implements CorporateStatusi18nService {

    private final CorporateStatusi18nRepository corporateStatusi18nRepository;
    private final CorporateStatusi18nMapper corporateStatusi18nMapper;

    /**
     * Retrieves all corporate statusi18ns records.
     * @return list of CorporateStatusi18nDto
     */
    @Override
    public List<CorporateStatusi18nDto> getAllCorporateStatusi18ns() {
        log.info("Fetching all corporate statusi18ns records.");
        return corporateStatusi18nMapper.toDTOList(corporateStatusi18nRepository.findAll());
    }

    /**
     * Retrieves a corporate statusi18n record by id.
     * @param corporateStatusId the corporateStatusId value
     * @param languageId the languageId value
     * @return CorporateStatusi18nDto
     */
    @Override
    public CorporateStatusi18nDto getCorporateStatusi18nById(UUID corporateStatusId, UUID languageId) {

        String compositeId = buildCompositeId(corporateStatusId, languageId);
        log.info("Fetching corporate statusi18n with composite id: {}", compositeId);

        CorporateStatusi18n existingEntity = findCorporateStatusi18nByIdOrThrow(corporateStatusId, languageId);
        return corporateStatusi18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new corporate statusi18n record.
     * @param dto input payload
     * @return created {@link CorporateStatusi18nDto}
     */
    @Override
    public CorporateStatusi18nDto createCorporateStatusi18n(CorporateStatusi18nDto dto) {
        log.info("Creating corporate statusi18n.");

        validateCorporateStatusi18nDoesNotExist(dto);

        validateCorporateStatusi18nCreateUniqueConstraints(dto);

        CorporateStatusi18n entity = corporateStatusi18nMapper.toEntity(dto);
        CorporateStatusi18n savedEntity = corporateStatusi18nRepository.save(entity);

        return corporateStatusi18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing corporate statusi18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param corporateStatusId the corporateStatusId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusi18nDto}
     */
    @Override
    public CorporateStatusi18nDto updateCorporateStatusi18n(UUID corporateStatusId, UUID languageId, CorporateStatusi18nDto dto) {
        String compositeId = buildCompositeId(corporateStatusId, languageId);

        log.info("Updating corporate statusi18n with composite id: {}", compositeId);

        CorporateStatusi18n existingEntity = findCorporateStatusi18nByIdOrThrow(corporateStatusId, languageId);
        corporateStatusi18nMapper.partialUpdate(existingEntity, dto);
        CorporateStatusi18n savedEntity = corporateStatusi18nRepository.save(existingEntity);

        return corporateStatusi18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a corporate statusi18n record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteCorporateStatusi18n(UUID corporateStatusId, UUID languageId) {
        String compositeId = buildCompositeId(corporateStatusId, languageId);
        log.info("Deleting corporate statusi18n with composite id: {}", compositeId);

        findCorporateStatusi18nByIdOrThrow(corporateStatusId, languageId);
        corporateStatusi18nRepository.deleteById(buildKey(corporateStatusId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCorporateStatusi18nCreateUniqueConstraints(CorporateStatusi18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getCorporateStatusId() : null) != null && dto.getChamberI18nId() != null && corporateStatusi18nRepository.existsByIdCorporateStatusIdAndChamberI18nId((dto.getId() != null ? dto.getId().getCorporateStatusId() : null), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CorporateStatusi18n")
                    .message("CorporateStatusi18n already exists with " + "corporateStatusId=" + (dto.getId() != null ? dto.getId().getCorporateStatusId() : null) + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing corporate statusi18n record by id or throws an exception.
     * @param corporateStatusId the corporateStatusId value
     * @param languageId the languageId value
     * @return existing CorporateStatusi18n entity
     */
    private CorporateStatusi18n findCorporateStatusi18nByIdOrThrow(UUID corporateStatusId, UUID languageId) {
        return corporateStatusi18nRepository.findById(buildKey(corporateStatusId, languageId))
                .orElseThrow(() -> createCorporateStatusi18nNotFoundException(corporateStatusId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the corporate statusi18n entity.
     @param corporateStatusId the corporate_status_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createCorporateStatusi18nNotFoundException(UUID corporateStatusId, UUID languageId) {
        String compositeId = buildCompositeId(corporateStatusId, languageId);
        log.warn("CorporateStatusi18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatusi18n")
                .message("CorporateStatusi18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a corporate statusi18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCorporateStatusi18nDoesNotExist(CorporateStatusi18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCorporateStatusId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        CorporateStatusi18nKey key = buildKey((dto.getId() != null ? dto.getId().getCorporateStatusId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (corporateStatusi18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCorporateStatusId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("CorporateStatusi18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CorporateStatusi18n")
                    .message("CorporateStatusi18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param corporateStatusId the corporateStatusId value
     * @param languageId the languageId value
     * @return populated {@link CorporateStatusi18nKey}
     */
    private CorporateStatusi18nKey buildKey(UUID corporateStatusId, UUID languageId) {
        CorporateStatusi18nKey key = new CorporateStatusi18nKey();
        key.setCorporateStatusId(corporateStatusId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param corporateStatusId the corporateStatusId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID corporateStatusId, UUID languageId) {
        return "corporateStatusId=" + corporateStatusId + ", " + "languageId=" + languageId;
    }

}
