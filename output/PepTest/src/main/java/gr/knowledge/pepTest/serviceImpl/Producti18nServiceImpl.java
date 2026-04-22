package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.mapper.Producti18nMapper;
import gr.knowledge.pepTest.entity.Producti18n;
import gr.knowledge.pepTest.repository.Producti18nRepository;
import gr.knowledge.pepTest.service.Producti18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.Producti18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Producti18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Producti18nServiceImpl implements Producti18nService {

    private final Producti18nRepository producti18nRepository;
    private final Producti18nMapper producti18nMapper;

    /**
     * Retrieves all producti18ns records.
     * @return list of Producti18nDto
     */
    @Override
    public List<Producti18nDto> getAllProducti18ns() {
        log.info("Fetching all producti18ns records.");
        return producti18nMapper.toDTOList(producti18nRepository.findAll());
    }

    /**
     * Retrieves a producti18n record by id.
     * @param languageId the languageId value
     * @param productId the productId value
     * @return Producti18nDto
     */
    @Override
    public Producti18nDto getProducti18nById(UUID languageId, UUID productId) {

        String compositeId = buildCompositeId(languageId, productId);
        log.info("Fetching producti18n with composite id: {}", compositeId);

        Producti18n existingEntity = findProducti18nByIdOrThrow(languageId, productId);
        return producti18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new producti18n record.
     * @param dto input payload
     * @return created {@link Producti18nDto}
     */
    @Override
    public Producti18nDto createProducti18n(Producti18nDto dto) {
        log.info("Creating producti18n.");

        validateProducti18nDoesNotExist(dto);

        Producti18n entity = producti18nMapper.toEntity(dto);
        Producti18n savedEntity = producti18nRepository.save(entity);

        return producti18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing producti18n record.
     *
     * @param languageId the languageId value
     * @param productId the productId value
     * @param dto input payload
     * @return updated {@link Producti18nDto}
     */
    @Override
    public Producti18nDto updateProducti18n(UUID languageId, UUID productId, Producti18nDto dto) {
        String compositeId = buildCompositeId(languageId, productId);

        log.info("Updating producti18n with composite id: {}", compositeId);

        Producti18n existingEntity = findProducti18nByIdOrThrow(languageId, productId);
        producti18nMapper.partialUpdate(existingEntity, dto);
        Producti18n savedEntity = producti18nRepository.save(existingEntity);

        return producti18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a producti18n record by id.
     * @param languageId the language_id value
     * @param productId the product_id value
     */
    @Override
    public void deleteProducti18n(UUID languageId, UUID productId) {
        String compositeId = buildCompositeId(languageId, productId);
        log.info("Deleting producti18n with composite id: {}", compositeId);

        findProducti18nByIdOrThrow(languageId, productId);
        producti18nRepository.deleteById(buildKey(languageId, productId));
    }

    /**
     * Finds an existing producti18n record by id or throws an exception.
     * @param languageId the languageId value
     * @param productId the productId value
     * @return existing Producti18n entity
     */
    private Producti18n findProducti18nByIdOrThrow(UUID languageId, UUID productId) {
        return producti18nRepository.findById(buildKey(languageId, productId))
                .orElseThrow(() -> createProducti18nNotFoundException(languageId, productId));
    }

    /**
     Creates a NOT FOUND exception for the producti18n entity.
     @param languageId the language_id value
     @param productId the product_id value
     @return runtime exception
     */
    private RuntimeException createProducti18nNotFoundException(UUID languageId, UUID productId) {
        String compositeId = buildCompositeId(languageId, productId);
        log.warn("Producti18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Producti18n")
                .message("Producti18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a producti18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateProducti18nDoesNotExist(Producti18nDto dto) {
        if (dto == null || dto.getId().getLanguageId() == null || dto.getId().getProductId() == null) {
            return;
        }

        Producti18nKey key = buildKey(dto.getId().getLanguageId(), dto.getId().getProductId());

        if (producti18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getLanguageId(), dto.getId().getProductId());
            log.warn("Producti18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Producti18n")
                    .message("Producti18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param languageId the languageId value
     * @param productId the productId value
     * @return populated {@link Producti18nKey}
     */
    private Producti18nKey buildKey(UUID languageId, UUID productId) {
        Producti18nKey key = new Producti18nKey();
        key.setLanguageId(languageId);
        key.setProductId(productId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param languageId the languageId value
     * @param productId the productId value
     * @return composite id string
     */
    private String buildCompositeId(UUID languageId, UUID productId) {
        return "languageId=" + languageId + ", " + "productId=" + productId;
    }

}
