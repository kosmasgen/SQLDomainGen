package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.mapper.CountryI18nMapper;
import gr.knowledge.pepTest.entity.CountryI18n;
import gr.knowledge.pepTest.repository.CountryI18nRepository;
import gr.knowledge.pepTest.service.CountryI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CountryI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Country I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CountryI18nServiceImpl implements CountryI18nService {

    private final CountryI18nRepository countryI18nRepository;
    private final CountryI18nMapper countryI18nMapper;

    /**
     * Retrieves all country i18ns records.
     * @return list of CountryI18nDto
     */
    @Override
    public List<CountryI18nDto> getAllCountryI18ns() {
        log.info("Fetching all country i18ns records.");
        return countryI18nMapper.toDTOList(countryI18nRepository.findAll());
    }

    /**
     * Retrieves a country i18n record by id.
     * @param countryId the countryId value
     * @param languageId the languageId value
     * @return CountryI18nDto
     */
    @Override
    public CountryI18nDto getCountryI18nById(UUID countryId, UUID languageId) {

        String compositeId = buildCompositeId(countryId, languageId);
        log.info("Fetching country i18n with composite id: {}", compositeId);

        CountryI18n existingEntity = findCountryI18nByIdOrThrow(countryId, languageId);
        return countryI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new country i18n record.
     * @param dto input payload
     * @return created {@link CountryI18nDto}
     */
    @Override
    public CountryI18nDto createCountryI18n(CountryI18nDto dto) {
        log.info("Creating country i18n.");

        validateCountryI18nDoesNotExist(dto);

        validateCountryI18nCreateUniqueConstraints(dto);

        CountryI18n entity = countryI18nMapper.toEntity(dto);
        CountryI18n savedEntity = countryI18nRepository.save(entity);

        return countryI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing country i18n record.
     *
     * @param countryId the countryId value
     * @param languageId the languageId value
     * @param dto input payload
     * @return updated {@link CountryI18nDto}
     */
    @Override
    public CountryI18nDto updateCountryI18n(UUID countryId, UUID languageId, CountryI18nDto dto) {
        String compositeId = buildCompositeId(countryId, languageId);

        log.info("Updating country i18n with composite id: {}", compositeId);

        CountryI18n existingEntity = findCountryI18nByIdOrThrow(countryId, languageId);
        countryI18nMapper.partialUpdate(existingEntity, dto);
        CountryI18n savedEntity = countryI18nRepository.save(existingEntity);

        return countryI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a country i18n record by id.
     * @param countryId the country_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteCountryI18n(UUID countryId, UUID languageId) {
        String compositeId = buildCompositeId(countryId, languageId);
        log.info("Deleting country i18n with composite id: {}", compositeId);

        findCountryI18nByIdOrThrow(countryId, languageId);
        countryI18nRepository.deleteById(buildKey(countryId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCountryI18nCreateUniqueConstraints(CountryI18nDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getId() != null && dto.getId().getCountryId() != null && dto.getChamberCountryI18nId() != null && countryI18nRepository.existsByIdCountryIdAndChamberCountryI18nId(dto.getId().getCountryId(), dto.getChamberCountryI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CountryI18n")
                    .message("CountryI18n already exists with " + "countryId=" + dto.getId().getCountryId() + ", " + "chamberCountryI18nId=" + dto.getChamberCountryI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing country i18n record by id or throws an exception.
     * @param countryId the countryId value
     * @param languageId the languageId value
     * @return existing CountryI18n entity
     */
    private CountryI18n findCountryI18nByIdOrThrow(UUID countryId, UUID languageId) {
        return countryI18nRepository.findById(buildKey(countryId, languageId))
                .orElseThrow(() -> createCountryI18nNotFoundException(countryId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the country i18n entity.
     @param countryId the country_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createCountryI18nNotFoundException(UUID countryId, UUID languageId) {
        String compositeId = buildCompositeId(countryId, languageId);
        log.warn("CountryI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CountryI18n")
                .message("CountryI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a country i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCountryI18nDoesNotExist(CountryI18nDto dto) {
        if (dto == null || dto.getId().getCountryId() == null || dto.getId().getLanguageId() == null) {
            return;
        }

        CountryI18nKey key = buildKey(dto.getId().getCountryId(), dto.getId().getLanguageId());

        if (countryI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getCountryId(), dto.getId().getLanguageId());
            log.warn("CountryI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CountryI18n")
                    .message("CountryI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param countryId the countryId value
     * @param languageId the languageId value
     * @return populated {@link CountryI18nKey}
     */
    private CountryI18nKey buildKey(UUID countryId, UUID languageId) {
        CountryI18nKey key = new CountryI18nKey();
        key.setCountryId(countryId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param countryId the countryId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID countryId, UUID languageId) {
        return "countryId=" + countryId + ", " + "languageId=" + languageId;
    }

}
