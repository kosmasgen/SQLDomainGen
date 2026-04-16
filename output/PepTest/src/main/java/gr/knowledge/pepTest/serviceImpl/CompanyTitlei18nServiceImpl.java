package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.mapper.CompanyTitlei18nMapper;
import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import gr.knowledge.pepTest.repository.CompanyTitlei18nRepository;
import gr.knowledge.pepTest.service.CompanyTitlei18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CompanyTitlei18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Titlei18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyTitlei18nServiceImpl implements CompanyTitlei18nService {

    private final CompanyTitlei18nRepository companyTitlei18nRepository;
    private final CompanyTitlei18nMapper companyTitlei18nMapper;

    /**
     * Retrieves all company titlei18ns records.
     * @return list of CompanyTitlei18nDto
     */
    @Override
    public List<CompanyTitlei18nDto> getAllCompanyTitlei18ns() {
        log.info("Fetching all company titlei18ns records.");
        return companyTitlei18nMapper.toDTOList(companyTitlei18nRepository.findAll());
    }

    /**
     * Retrieves a company titlei18n record by id.
     * @param companyTitleId the companyTitleId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return CompanyTitlei18nDto
     */
    @Override
    public CompanyTitlei18nDto getCompanyTitlei18nById(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {

        String compositeId = buildCompositeId(companyTitleId, languageId, chamberI18nId);
        log.info("Fetching company titlei18n with composite id: {}", compositeId);

        CompanyTitlei18n existingEntity = findCompanyTitlei18nByIdOrThrow(companyTitleId, languageId, chamberI18nId);
        return companyTitlei18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company titlei18n record.
     * @param dto input payload
     * @return created {@link CompanyTitlei18nDto}
     */
    @Override
    public CompanyTitlei18nDto createCompanyTitlei18n(CompanyTitlei18nDto dto) {
        log.info("Creating company titlei18n.");

        validateCompanyTitlei18nDoesNotExist(dto);

        validateCompanyTitlei18nCreateUniqueConstraints(dto);

        CompanyTitlei18n entity = companyTitlei18nMapper.toEntity(dto);
        CompanyTitlei18n savedEntity = companyTitlei18nRepository.save(entity);

        return companyTitlei18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company titlei18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param companyTitleId the companyTitleId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyTitlei18nDto}
     */
    @Override
    public CompanyTitlei18nDto updateCompanyTitlei18n(UUID companyTitleId, UUID languageId, Integer chamberI18nId, CompanyTitlei18nDto dto) {
        String compositeId = buildCompositeId(companyTitleId, languageId, chamberI18nId);

        log.info("Updating company titlei18n with composite id: {}", compositeId);

        CompanyTitlei18n existingEntity = findCompanyTitlei18nByIdOrThrow(companyTitleId, languageId, chamberI18nId);
        companyTitlei18nMapper.partialUpdate(existingEntity, dto);
        CompanyTitlei18n savedEntity = companyTitlei18nRepository.save(existingEntity);

        return companyTitlei18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company titlei18n record by id.
     * @param companyTitleId the company_title_id value
     * @param languageId the language_id value
     * @param chamberI18nId the chamber_i18n_id value
     */
    @Override
    public void deleteCompanyTitlei18n(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        String compositeId = buildCompositeId(companyTitleId, languageId, chamberI18nId);
        log.info("Deleting company titlei18n with composite id: {}", compositeId);

        findCompanyTitlei18nByIdOrThrow(companyTitleId, languageId, chamberI18nId);
        companyTitlei18nRepository.deleteById(buildKey(companyTitleId, languageId, chamberI18nId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyTitlei18nCreateUniqueConstraints(CompanyTitlei18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getCompanyTitleId() : null) != null && (dto.getId() != null ? dto.getId().getChamberI18nId() : null) != null && companyTitlei18nRepository.existsByIdCompanyTitleIdAndIdChamberI18nId((dto.getId() != null ? dto.getId().getCompanyTitleId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null))) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyTitlei18n")
                    .message("CompanyTitlei18n already exists with " + "companyTitleId=" + (dto.getId() != null ? dto.getId().getCompanyTitleId() : null) + ", " + "chamberI18nId=" + (dto.getId() != null ? dto.getId().getChamberI18nId() : null))
                    .build();
        }
    }

    /**
     * Finds an existing company titlei18n record by id or throws an exception.
     * @param companyTitleId the companyTitleId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return existing CompanyTitlei18n entity
     */
    private CompanyTitlei18n findCompanyTitlei18nByIdOrThrow(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        return companyTitlei18nRepository.findById(buildKey(companyTitleId, languageId, chamberI18nId))
                .orElseThrow(() -> createCompanyTitlei18nNotFoundException(companyTitleId, languageId, chamberI18nId));
    }

    /**
     Creates a NOT FOUND exception for the company titlei18n entity.
     @param companyTitleId the company_title_id value
     @param languageId the language_id value
     @param chamberI18nId the chamber_i18n_id value
     @return runtime exception
     */
    private RuntimeException createCompanyTitlei18nNotFoundException(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        String compositeId = buildCompositeId(companyTitleId, languageId, chamberI18nId);
        log.warn("CompanyTitlei18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyTitlei18n")
                .message("CompanyTitlei18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a company titlei18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyTitlei18nDoesNotExist(CompanyTitlei18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCompanyTitleId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null || (dto.getId() != null ? dto.getId().getChamberI18nId() : null) == null) {
            return;
        }

        CompanyTitlei18nKey key = buildKey((dto.getId() != null ? dto.getId().getCompanyTitleId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null));

        if (companyTitlei18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCompanyTitleId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null), (dto.getId() != null ? dto.getId().getChamberI18nId() : null));
            log.warn("CompanyTitlei18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyTitlei18n")
                    .message("CompanyTitlei18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyTitleId the companyTitleId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return populated {@link CompanyTitlei18nKey}
     */
    private CompanyTitlei18nKey buildKey(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        CompanyTitlei18nKey key = new CompanyTitlei18nKey();
        key.setCompanyTitleId(companyTitleId);
        key.setLanguageId(languageId);
        key.setChamberI18nId(chamberI18nId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyTitleId the companyTitleId value
     * @param languageId the languageId value
     * @param chamberI18nId the chamberI18nId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        return "companyTitleId=" + companyTitleId + ", " + "languageId=" + languageId + ", " + "chamberI18nId=" + chamberI18nId;
    }

}
