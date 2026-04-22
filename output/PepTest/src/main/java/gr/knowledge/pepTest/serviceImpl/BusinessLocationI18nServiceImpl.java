package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.mapper.BusinessLocationI18nMapper;
import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import gr.knowledge.pepTest.repository.BusinessLocationI18nRepository;
import gr.knowledge.pepTest.service.BusinessLocationI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.BusinessLocationI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Business Location I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BusinessLocationI18nServiceImpl implements BusinessLocationI18nService {

    private final BusinessLocationI18nRepository businessLocationI18nRepository;
    private final BusinessLocationI18nMapper businessLocationI18nMapper;

    /**
     * Retrieves all business location i18ns records.
     * @return list of BusinessLocationI18nDto
     */
    @Override
    public List<BusinessLocationI18nDto> getAllBusinessLocationI18ns() {
        log.info("Fetching all business location i18ns records.");
        return businessLocationI18nMapper.toDTOList(businessLocationI18nRepository.findAll());
    }

    /**
     * Retrieves a business location i18n record by id.
     * @param businessLocationId the businessLocationId value
     * @param languageId the languageId value
     * @return BusinessLocationI18nDto
     */
    @Override
    public BusinessLocationI18nDto getBusinessLocationI18nById(UUID businessLocationId, UUID languageId) {

        String compositeId = buildCompositeId(businessLocationId, languageId);
        log.info("Fetching business location i18n with composite id: {}", compositeId);

        BusinessLocationI18n existingEntity = findBusinessLocationI18nByIdOrThrow(businessLocationId, languageId);
        return businessLocationI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new business location i18n record.
     * @param dto input payload
     * @return created {@link BusinessLocationI18nDto}
     */
    @Override
    public BusinessLocationI18nDto createBusinessLocationI18n(BusinessLocationI18nDto dto) {
        log.info("Creating business location i18n.");

        validateBusinessLocationI18nDoesNotExist(dto);

        BusinessLocationI18n entity = businessLocationI18nMapper.toEntity(dto);
        BusinessLocationI18n savedEntity = businessLocationI18nRepository.save(entity);

        return businessLocationI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing business location i18n record.
     *
     * @param businessLocationId the businessLocationId value
     * @param languageId the languageId value
     * @param dto input payload
     * @return updated {@link BusinessLocationI18nDto}
     */
    @Override
    public BusinessLocationI18nDto updateBusinessLocationI18n(UUID businessLocationId, UUID languageId, BusinessLocationI18nDto dto) {
        String compositeId = buildCompositeId(businessLocationId, languageId);

        log.info("Updating business location i18n with composite id: {}", compositeId);

        BusinessLocationI18n existingEntity = findBusinessLocationI18nByIdOrThrow(businessLocationId, languageId);
        businessLocationI18nMapper.partialUpdate(existingEntity, dto);
        BusinessLocationI18n savedEntity = businessLocationI18nRepository.save(existingEntity);

        return businessLocationI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a business location i18n record by id.
     * @param businessLocationId the business_location_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteBusinessLocationI18n(UUID businessLocationId, UUID languageId) {
        String compositeId = buildCompositeId(businessLocationId, languageId);
        log.info("Deleting business location i18n with composite id: {}", compositeId);

        findBusinessLocationI18nByIdOrThrow(businessLocationId, languageId);
        businessLocationI18nRepository.deleteById(buildKey(businessLocationId, languageId));
    }

    /**
     * Finds an existing business location i18n record by id or throws an exception.
     * @param businessLocationId the businessLocationId value
     * @param languageId the languageId value
     * @return existing BusinessLocationI18n entity
     */
    private BusinessLocationI18n findBusinessLocationI18nByIdOrThrow(UUID businessLocationId, UUID languageId) {
        return businessLocationI18nRepository.findById(buildKey(businessLocationId, languageId))
                .orElseThrow(() -> createBusinessLocationI18nNotFoundException(businessLocationId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the business location i18n entity.
     @param businessLocationId the business_location_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createBusinessLocationI18nNotFoundException(UUID businessLocationId, UUID languageId) {
        String compositeId = buildCompositeId(businessLocationId, languageId);
        log.warn("BusinessLocationI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BusinessLocationI18n")
                .message("BusinessLocationI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a business location i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateBusinessLocationI18nDoesNotExist(BusinessLocationI18nDto dto) {
        if (dto == null || dto.getId().getBusinessLocationId() == null || dto.getId().getLanguageId() == null) {
            return;
        }

        BusinessLocationI18nKey key = buildKey(dto.getId().getBusinessLocationId(), dto.getId().getLanguageId());

        if (businessLocationI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getBusinessLocationId(), dto.getId().getLanguageId());
            log.warn("BusinessLocationI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("BusinessLocationI18n")
                    .message("BusinessLocationI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param businessLocationId the businessLocationId value
     * @param languageId the languageId value
     * @return populated {@link BusinessLocationI18nKey}
     */
    private BusinessLocationI18nKey buildKey(UUID businessLocationId, UUID languageId) {
        BusinessLocationI18nKey key = new BusinessLocationI18nKey();
        key.setBusinessLocationId(businessLocationId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param businessLocationId the businessLocationId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID businessLocationId, UUID languageId) {
        return "businessLocationId=" + businessLocationId + ", " + "languageId=" + languageId;
    }

}
