package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.mapper.CompanyProfileI18nMapper;
import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import gr.knowledge.pepTest.repository.CompanyProfileI18nRepository;
import gr.knowledge.pepTest.service.CompanyProfileI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Profile I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfileI18nServiceImpl implements CompanyProfileI18nService {

    private final CompanyProfileI18nRepository companyProfileI18nRepository;
    private final CompanyProfileI18nMapper companyProfileI18nMapper;

    /**
     * Retrieves all company profile i18ns records.
     * @return list of CompanyProfileI18nDto
     */
    @Override
    public List<CompanyProfileI18nDto> getAllCompanyProfileI18ns() {
        log.info("Fetching all company profile i18ns records.");
        return companyProfileI18nMapper.toDTOList(companyProfileI18nRepository.findAll());
    }

    /**
     * Retrieves a company profile i18n record by id.
     * @param companyProfileId the companyProfileId value
     * @param languageId the languageId value
     * @return CompanyProfileI18nDto
     */
    @Override
    public CompanyProfileI18nDto getCompanyProfileI18nById(UUID companyProfileId, UUID languageId) {

        String compositeId = buildCompositeId(companyProfileId, languageId);
        log.info("Fetching company profile i18n with composite id: {}", compositeId);

        CompanyProfileI18n existingEntity = findCompanyProfileI18nByIdOrThrow(companyProfileId, languageId);
        return companyProfileI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company profile i18n record.
     * @param dto input payload
     * @return created {@link CompanyProfileI18nDto}
     */
    @Override
    public CompanyProfileI18nDto createCompanyProfileI18n(CompanyProfileI18nDto dto) {
        log.info("Creating company profile i18n.");

        validateCompanyProfileI18nDoesNotExist(dto);

        CompanyProfileI18n entity = companyProfileI18nMapper.toEntity(dto);
        CompanyProfileI18n savedEntity = companyProfileI18nRepository.save(entity);

        return companyProfileI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company profile i18n record.
     *
     * @param companyProfileId the companyProfileId value
     * @param languageId the languageId value
     * @param dto input payload
     * @return updated {@link CompanyProfileI18nDto}
     */
    @Override
    public CompanyProfileI18nDto updateCompanyProfileI18n(UUID companyProfileId, UUID languageId, CompanyProfileI18nDto dto) {
        String compositeId = buildCompositeId(companyProfileId, languageId);

        log.info("Updating company profile i18n with composite id: {}", compositeId);

        CompanyProfileI18n existingEntity = findCompanyProfileI18nByIdOrThrow(companyProfileId, languageId);
        companyProfileI18nMapper.partialUpdate(existingEntity, dto);
        CompanyProfileI18n savedEntity = companyProfileI18nRepository.save(existingEntity);

        return companyProfileI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company profile i18n record by id.
     * @param companyProfileId the company_profile_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteCompanyProfileI18n(UUID companyProfileId, UUID languageId) {
        String compositeId = buildCompositeId(companyProfileId, languageId);
        log.info("Deleting company profile i18n with composite id: {}", compositeId);

        findCompanyProfileI18nByIdOrThrow(companyProfileId, languageId);
        companyProfileI18nRepository.deleteById(buildKey(companyProfileId, languageId));
    }

    /**
     * Finds an existing company profile i18n record by id or throws an exception.
     * @param companyProfileId the companyProfileId value
     * @param languageId the languageId value
     * @return existing CompanyProfileI18n entity
     */
    private CompanyProfileI18n findCompanyProfileI18nByIdOrThrow(UUID companyProfileId, UUID languageId) {
        return companyProfileI18nRepository.findById(buildKey(companyProfileId, languageId))
                .orElseThrow(() -> createCompanyProfileI18nNotFoundException(companyProfileId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the company profile i18n entity.
     @param companyProfileId the company_profile_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createCompanyProfileI18nNotFoundException(UUID companyProfileId, UUID languageId) {
        String compositeId = buildCompositeId(companyProfileId, languageId);
        log.warn("CompanyProfileI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyProfileI18n")
                .message("CompanyProfileI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a company profile i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyProfileI18nDoesNotExist(CompanyProfileI18nDto dto) {
        if (dto == null || dto.getId().getCompanyProfileId() == null || dto.getId().getLanguageId() == null) {
            return;
        }

        CompanyProfileI18nKey key = buildKey(dto.getId().getCompanyProfileId(), dto.getId().getLanguageId());

        if (companyProfileI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getCompanyProfileId(), dto.getId().getLanguageId());
            log.warn("CompanyProfileI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyProfileI18n")
                    .message("CompanyProfileI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyProfileId the companyProfileId value
     * @param languageId the languageId value
     * @return populated {@link CompanyProfileI18nKey}
     */
    private CompanyProfileI18nKey buildKey(UUID companyProfileId, UUID languageId) {
        CompanyProfileI18nKey key = new CompanyProfileI18nKey();
        key.setCompanyProfileId(companyProfileId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyProfileId the companyProfileId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyProfileId, UUID languageId) {
        return "companyProfileId=" + companyProfileId + ", " + "languageId=" + languageId;
    }

}
