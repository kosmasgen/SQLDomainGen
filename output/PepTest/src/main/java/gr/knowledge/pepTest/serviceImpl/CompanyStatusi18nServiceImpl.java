package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.mapper.CompanyStatusi18nMapper;
import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import gr.knowledge.pepTest.repository.CompanyStatusi18nRepository;
import gr.knowledge.pepTest.service.CompanyStatusi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CompanyStatusi18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Statusi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusi18nServiceImpl implements CompanyStatusi18nService {

    private final CompanyStatusi18nRepository companyStatusi18nRepository;
    private final CompanyStatusi18nMapper companyStatusi18nMapper;

    /**
     * Retrieves all company statusi18ns records.
     * @return list of CompanyStatusi18nDto
     */
    @Override
    public List<CompanyStatusi18nDto> getAllCompanyStatusi18ns() {
        log.info("Fetching all company statusi18ns records.");
        return companyStatusi18nMapper.toDTOList(companyStatusi18nRepository.findAll());
    }

    /**
     * Retrieves a company statusi18n record by id.
     * @param companyStatusId the companyStatusId value
     * @param languageId the languageId value
     * @return CompanyStatusi18nDto
     */
    @Override
    public CompanyStatusi18nDto getCompanyStatusi18nById(UUID companyStatusId, UUID languageId) {

        String compositeId = buildCompositeId(companyStatusId, languageId);
        log.info("Fetching company statusi18n with composite id: {}", compositeId);

        CompanyStatusi18n existingEntity = findCompanyStatusi18nByIdOrThrow(companyStatusId, languageId);
        return companyStatusi18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company statusi18n record.
     * @param dto input payload
     * @return created {@link CompanyStatusi18nDto}
     */
    @Override
    public CompanyStatusi18nDto createCompanyStatusi18n(CompanyStatusi18nDto dto) {
        log.info("Creating company statusi18n.");

        validateCompanyStatusi18nDoesNotExist(dto);

        validateCompanyStatusi18nCreateUniqueConstraints(dto);

        CompanyStatusi18n entity = companyStatusi18nMapper.toEntity(dto);
        CompanyStatusi18n savedEntity = companyStatusi18nRepository.save(entity);

        return companyStatusi18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company statusi18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param companyStatusId the companyStatusId value
     * @param languageId the languageId value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusi18nDto}
     */
    @Override
    public CompanyStatusi18nDto updateCompanyStatusi18n(UUID companyStatusId, UUID languageId, CompanyStatusi18nDto dto) {
        String compositeId = buildCompositeId(companyStatusId, languageId);

        log.info("Updating company statusi18n with composite id: {}", compositeId);

        CompanyStatusi18n existingEntity = findCompanyStatusi18nByIdOrThrow(companyStatusId, languageId);
        companyStatusi18nMapper.partialUpdate(existingEntity, dto);
        CompanyStatusi18n savedEntity = companyStatusi18nRepository.save(existingEntity);

        return companyStatusi18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company statusi18n record by id.
     * @param companyStatusId the company_status_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteCompanyStatusi18n(UUID companyStatusId, UUID languageId) {
        String compositeId = buildCompositeId(companyStatusId, languageId);
        log.info("Deleting company statusi18n with composite id: {}", compositeId);

        findCompanyStatusi18nByIdOrThrow(companyStatusId, languageId);
        companyStatusi18nRepository.deleteById(buildKey(companyStatusId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyStatusi18nCreateUniqueConstraints(CompanyStatusi18nDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getId() != null ? dto.getId().getCompanyStatusId() : null) != null && dto.getChamberI18nId() != null && companyStatusi18nRepository.existsByIdCompanyStatusIdAndChamberI18nId((dto.getId() != null ? dto.getId().getCompanyStatusId() : null), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyStatusi18n")
                    .message("CompanyStatusi18n already exists with " + "companyStatusId=" + (dto.getId() != null ? dto.getId().getCompanyStatusId() : null) + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing company statusi18n record by id or throws an exception.
     * @param companyStatusId the companyStatusId value
     * @param languageId the languageId value
     * @return existing CompanyStatusi18n entity
     */
    private CompanyStatusi18n findCompanyStatusi18nByIdOrThrow(UUID companyStatusId, UUID languageId) {
        return companyStatusi18nRepository.findById(buildKey(companyStatusId, languageId))
                .orElseThrow(() -> createCompanyStatusi18nNotFoundException(companyStatusId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the company statusi18n entity.
     @param companyStatusId the company_status_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createCompanyStatusi18nNotFoundException(UUID companyStatusId, UUID languageId) {
        String compositeId = buildCompositeId(companyStatusId, languageId);
        log.warn("CompanyStatusi18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatusi18n")
                .message("CompanyStatusi18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a company statusi18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyStatusi18nDoesNotExist(CompanyStatusi18nDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCompanyStatusId() : null) == null || (dto.getId() != null ? dto.getId().getLanguageId() : null) == null) {
            return;
        }

        CompanyStatusi18nKey key = buildKey((dto.getId() != null ? dto.getId().getCompanyStatusId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));

        if (companyStatusi18nRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCompanyStatusId() : null), (dto.getId() != null ? dto.getId().getLanguageId() : null));
            log.warn("CompanyStatusi18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyStatusi18n")
                    .message("CompanyStatusi18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyStatusId the companyStatusId value
     * @param languageId the languageId value
     * @return populated {@link CompanyStatusi18nKey}
     */
    private CompanyStatusi18nKey buildKey(UUID companyStatusId, UUID languageId) {
        CompanyStatusi18nKey key = new CompanyStatusi18nKey();
        key.setCompanyStatusId(companyStatusId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyStatusId the companyStatusId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyStatusId, UUID languageId) {
        return "companyStatusId=" + companyStatusId + ", " + "languageId=" + languageId;
    }

}
