package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.mapper.CompanyYpArticleI18nMapper;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import gr.knowledge.pepTest.repository.CompanyYpArticleI18nRepository;
import gr.knowledge.pepTest.service.CompanyYpArticleI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Yp Article I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpArticleI18nServiceImpl implements CompanyYpArticleI18nService {

    private final CompanyYpArticleI18nRepository companyYpArticleI18nRepository;
    private final CompanyYpArticleI18nMapper companyYpArticleI18nMapper;

    /**
     * Retrieves all company yp article i18ns records.
     * @return list of CompanyYpArticleI18nDto
     */
    @Override
    public List<CompanyYpArticleI18nDto> getAllCompanyYpArticleI18ns() {
        log.info("Fetching all company yp article i18ns records.");
        return companyYpArticleI18nMapper.toDTOList(companyYpArticleI18nRepository.findAll());
    }

    /**
     * Retrieves a company yp article i18n record by id.
     * @param companyArticleId the companyArticleId value
     * @param languageId the languageId value
     * @return CompanyYpArticleI18nDto
     */
    @Override
    public CompanyYpArticleI18nDto getCompanyYpArticleI18nById(UUID companyArticleId, UUID languageId) {

        String compositeId = buildCompositeId(companyArticleId, languageId);
        log.info("Fetching company yp article i18n with composite id: {}", compositeId);

        CompanyYpArticleI18n existingEntity = findCompanyYpArticleI18nByIdOrThrow(companyArticleId, languageId);
        return companyYpArticleI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company yp article i18n record.
     * @param dto input payload
     * @return created {@link CompanyYpArticleI18nDto}
     */
    @Override
    public CompanyYpArticleI18nDto createCompanyYpArticleI18n(CompanyYpArticleI18nDto dto) {
        log.info("Creating company yp article i18n.");

        validateCompanyYpArticleI18nDoesNotExist(dto);

        CompanyYpArticleI18n entity = companyYpArticleI18nMapper.toEntity(dto);
        CompanyYpArticleI18n savedEntity = companyYpArticleI18nRepository.save(entity);

        return companyYpArticleI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company yp article i18n record.
     *
     * @param companyArticleId the companyArticleId value
     * @param languageId the languageId value
     * @param dto input payload
     * @return updated {@link CompanyYpArticleI18nDto}
     */
    @Override
    public CompanyYpArticleI18nDto updateCompanyYpArticleI18n(UUID companyArticleId, UUID languageId, CompanyYpArticleI18nDto dto) {
        String compositeId = buildCompositeId(companyArticleId, languageId);

        log.info("Updating company yp article i18n with composite id: {}", compositeId);

        CompanyYpArticleI18n existingEntity = findCompanyYpArticleI18nByIdOrThrow(companyArticleId, languageId);
        companyYpArticleI18nMapper.partialUpdate(existingEntity, dto);
        CompanyYpArticleI18n savedEntity = companyYpArticleI18nRepository.save(existingEntity);

        return companyYpArticleI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company yp article i18n record by id.
     * @param companyArticleId the company_article_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteCompanyYpArticleI18n(UUID companyArticleId, UUID languageId) {
        String compositeId = buildCompositeId(companyArticleId, languageId);
        log.info("Deleting company yp article i18n with composite id: {}", compositeId);

        findCompanyYpArticleI18nByIdOrThrow(companyArticleId, languageId);
        companyYpArticleI18nRepository.deleteById(buildKey(companyArticleId, languageId));
    }

    /**
     * Finds an existing company yp article i18n record by id or throws an exception.
     * @param companyArticleId the companyArticleId value
     * @param languageId the languageId value
     * @return existing CompanyYpArticleI18n entity
     */
    private CompanyYpArticleI18n findCompanyYpArticleI18nByIdOrThrow(UUID companyArticleId, UUID languageId) {
        return companyYpArticleI18nRepository.findById(buildKey(companyArticleId, languageId))
                .orElseThrow(() -> createCompanyYpArticleI18nNotFoundException(companyArticleId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the company yp article i18n entity.
     @param companyArticleId the company_article_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createCompanyYpArticleI18nNotFoundException(UUID companyArticleId, UUID languageId) {
        String compositeId = buildCompositeId(companyArticleId, languageId);
        log.warn("CompanyYpArticleI18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpArticleI18n")
                .message("CompanyYpArticleI18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a company yp article i18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyYpArticleI18nDoesNotExist(CompanyYpArticleI18nDto dto) {
        if (dto == null || dto.getId().getCompanyArticleId() == null || dto.getId().getLanguageId() == null) {
            return;
        }

        CompanyYpArticleI18nKey key = buildKey(dto.getId().getCompanyArticleId(), dto.getId().getLanguageId());

        if (companyYpArticleI18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getCompanyArticleId(), dto.getId().getLanguageId());
            log.warn("CompanyYpArticleI18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyYpArticleI18n")
                    .message("CompanyYpArticleI18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyArticleId the companyArticleId value
     * @param languageId the languageId value
     * @return populated {@link CompanyYpArticleI18nKey}
     */
    private CompanyYpArticleI18nKey buildKey(UUID companyArticleId, UUID languageId) {
        CompanyYpArticleI18nKey key = new CompanyYpArticleI18nKey();
        key.setCompanyArticleId(companyArticleId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyArticleId the companyArticleId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyArticleId, UUID languageId) {
        return "companyArticleId=" + companyArticleId + ", " + "languageId=" + languageId;
    }

}
