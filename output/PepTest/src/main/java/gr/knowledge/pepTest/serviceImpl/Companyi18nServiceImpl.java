package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.mapper.Companyi18nMapper;
import gr.knowledge.pepTest.entity.Companyi18n;
import gr.knowledge.pepTest.repository.Companyi18nRepository;
import gr.knowledge.pepTest.service.Companyi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.Companyi18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Companyi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Companyi18nServiceImpl implements Companyi18nService {

    private final Companyi18nRepository companyi18nRepository;
    private final Companyi18nMapper companyi18nMapper;

    /**
     * Retrieves all companyi18ns records.
     * @return list of Companyi18nDto
     */
    @Override
    public List<Companyi18nDto> getAllCompanyi18ns() {
        log.info("Fetching all companyi18ns records.");
        return companyi18nMapper.toDTOList(companyi18nRepository.findAll());
    }

    /**
     * Retrieves a companyi18n record by id.
     * @param companyId the companyId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return Companyi18nDto
     */
    @Override
    public Companyi18nDto getCompanyi18nById(UUID companyId, UUID languageId, Integer chamberI18nId) {

        String compositeId = buildCompositeId(companyId, languageId, chamberI18nId);
        log.info("Fetching companyi18n with composite id: {}", compositeId);

        Companyi18n existingEntity = findCompanyi18nByIdOrThrow(companyId, languageId, chamberI18nId);
        return companyi18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new companyi18n record.
     * @param dto input payload
     * @return created {@link Companyi18nDto}
     */
    @Override
    public Companyi18nDto createCompanyi18n(Companyi18nDto dto) {
        log.info("Creating companyi18n.");

        validateCompanyi18nDoesNotExist(dto);

        validateCompanyi18nCreateUniqueConstraints(dto);

        Companyi18n entity = companyi18nMapper.toEntity(dto);
        Companyi18n savedEntity = companyi18nRepository.save(entity);

        return companyi18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing companyi18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param companyId the companyId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @param dto input payload with partial fields
     * @return updated {@link Companyi18nDto}
     */
    @Override
    public Companyi18nDto updateCompanyi18n(UUID companyId, UUID languageId, Integer chamberI18nId, Companyi18nDto dto) {
        String compositeId = buildCompositeId(companyId, languageId, chamberI18nId);

        log.info("Updating companyi18n with composite id: {}", compositeId);

        Companyi18n existingEntity = findCompanyi18nByIdOrThrow(companyId, languageId, chamberI18nId);
        companyi18nMapper.partialUpdate(existingEntity, dto);
        Companyi18n savedEntity = companyi18nRepository.save(existingEntity);

        return companyi18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a companyi18n record by id.
     * @param companyId the company_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     */
    @Override
    public void deleteCompanyi18n(UUID companyId, UUID languageId, Integer chamberI18nId) {
        String compositeId = buildCompositeId(companyId, languageId, chamberI18nId);
        log.info("Deleting companyi18n with composite id: {}", compositeId);

        findCompanyi18nByIdOrThrow(companyId, languageId, chamberI18nId);
        companyi18nRepository.deleteById(buildKey(companyId, languageId, chamberI18nId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyi18nCreateUniqueConstraints(Companyi18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getCompanyId() : null) != null && (dto.getId() != null ? dto.getId().getChamberI18nId() : null) != null && companyi18nRepository.existsByIdCompanyIdAndIdChamberI18nId((dto.getId() != null ? dto.getId().getCompanyId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null))) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Companyi18n")
                    .message("Companyi18n already exists with " + "companyId=" + (dto.getId() != null ? dto.getId().getCompanyId() : null) + ", " + "chamberI18nId=" + (dto.getId() != null ? dto.getId().getChamberI18nId() : null))
                    .build();
        }
    }

    /**
     * Finds an existing companyi18n record by id or throws an exception.
     * @param companyId the companyId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return existing Companyi18n entity
     */
    private Companyi18n findCompanyi18nByIdOrThrow(UUID companyId, UUID languageId, Integer chamberI18nId) {
        return companyi18nRepository.findById(buildKey(companyId, languageId, chamberI18nId))
                .orElseThrow(() -> createCompanyi18nNotFoundException(companyId, languageId, chamberI18nId));
    }

    /**
     Creates a NOT FOUND exception for the companyi18n entity.
     @param companyId the company_id value
     @param languageId the language_id value
     @param chamberI18nId the chamber_i18n_id value
     @return runtime exception
     */
    private RuntimeException createCompanyi18nNotFoundException(UUID companyId, UUID languageId, Integer chamberI18nId) {
        String compositeId = buildCompositeId(companyId, languageId, chamberI18nId);
        log.warn("Companyi18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Companyi18n")
                .message("Companyi18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a companyi18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyi18nDoesNotExist(Companyi18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCompanyId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null || (dto.getId() != null ? dto.getId().getChamberI18nId() : null) == null) {
            return;
        }

        Companyi18nKey key = buildKey((dto.getId() != null ? dto.getId().getCompanyId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null));

        if (companyi18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCompanyId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null));
            log.warn("Companyi18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Companyi18n")
                    .message("Companyi18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyId the companyId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return populated {@link Companyi18nKey}
     */
    private Companyi18nKey buildKey(UUID companyId, UUID languageId, Integer chamberI18nId) {
        Companyi18nKey key = new Companyi18nKey();
        key.setCompanyId(companyId);
        key.setLanguageId(languageId);
        key.setChamberI18nId(chamberI18nId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyId the companyId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyId, UUID languageId, Integer chamberI18nId) {
        return "companyId=" + companyId + ", " + "languageId=" + languageId + ", " + "chamberI18nId=" + chamberI18nId;
    }

}
