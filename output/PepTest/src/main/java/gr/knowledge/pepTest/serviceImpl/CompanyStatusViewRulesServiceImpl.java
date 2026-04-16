package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.mapper.CompanyStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import gr.knowledge.pepTest.repository.CompanyStatusViewRulesRepository;
import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Status View Rules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusViewRulesServiceImpl implements CompanyStatusViewRulesService {

    private final CompanyStatusViewRulesRepository companyStatusViewRulesRepository;
    private final CompanyStatusViewRulesMapper companyStatusViewRulesMapper;

    /**
     * Retrieves all company status view ruleses records.
     * @return list of CompanyStatusViewRulesDto
     */
    @Override
    public List<CompanyStatusViewRulesDto> getAllCompanyStatusViewRuleses() {
        log.info("Fetching all company status view ruleses records.");
        return companyStatusViewRulesMapper.toDTOList(companyStatusViewRulesRepository.findAll());
    }

    /**
     * Retrieves a company status view rules record by id.
     * @param companyStatusId the companyStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return CompanyStatusViewRulesDto
     */
    @Override
    public CompanyStatusViewRulesDto getCompanyStatusViewRulesById(UUID companyStatusId, UUID companyViewRulesId) {

        String compositeId = buildCompositeId(companyStatusId, companyViewRulesId);
        log.info("Fetching company status view rules with composite id: {}", compositeId);

        CompanyStatusViewRules existingEntity = findCompanyStatusViewRulesByIdOrThrow(companyStatusId, companyViewRulesId);
        return companyStatusViewRulesMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company status view rules record.
     * @param dto input payload
     * @return created {@link CompanyStatusViewRulesDto}
     */
    @Override
    public CompanyStatusViewRulesDto createCompanyStatusViewRules(CompanyStatusViewRulesDto dto) {
        log.info("Creating company status view rules.");

        validateCompanyStatusViewRulesDoesNotExist(dto);

        CompanyStatusViewRules entity = companyStatusViewRulesMapper.toEntity(dto);
        CompanyStatusViewRules savedEntity = companyStatusViewRulesRepository.save(entity);

        return companyStatusViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company status view rules record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param companyStatusId the companyStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusViewRulesDto}
     */
    @Override
    public CompanyStatusViewRulesDto updateCompanyStatusViewRules(UUID companyStatusId, UUID companyViewRulesId, CompanyStatusViewRulesDto dto) {
        String compositeId = buildCompositeId(companyStatusId, companyViewRulesId);

        log.info("Updating company status view rules with composite id: {}", compositeId);

        CompanyStatusViewRules existingEntity = findCompanyStatusViewRulesByIdOrThrow(companyStatusId, companyViewRulesId);
        companyStatusViewRulesMapper.partialUpdate(existingEntity, dto);
        CompanyStatusViewRules savedEntity = companyStatusViewRulesRepository.save(existingEntity);

        return companyStatusViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company status view rules record by id.
     * @param companyStatusId the company_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     */
    @Override
    public void deleteCompanyStatusViewRules(UUID companyStatusId, UUID companyViewRulesId) {
        String compositeId = buildCompositeId(companyStatusId, companyViewRulesId);
        log.info("Deleting company status view rules with composite id: {}", compositeId);

        findCompanyStatusViewRulesByIdOrThrow(companyStatusId, companyViewRulesId);
        companyStatusViewRulesRepository.deleteById(buildKey(companyStatusId, companyViewRulesId));
    }

    /**
     * Finds an existing company status view rules record by id or throws an exception.
     * @param companyStatusId the companyStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return existing CompanyStatusViewRules entity
     */
    private CompanyStatusViewRules findCompanyStatusViewRulesByIdOrThrow(UUID companyStatusId, UUID companyViewRulesId) {
        return companyStatusViewRulesRepository.findById(buildKey(companyStatusId, companyViewRulesId))
                .orElseThrow(() -> createCompanyStatusViewRulesNotFoundException(companyStatusId, companyViewRulesId));
    }

    /**
     Creates a NOT FOUND exception for the company status view rules entity.
     @param companyStatusId the company_status_id value
     @param companyViewRulesId the company_view_rules_id value
     @return runtime exception
     */
    private RuntimeException createCompanyStatusViewRulesNotFoundException(UUID companyStatusId, UUID companyViewRulesId) {
        String compositeId = buildCompositeId(companyStatusId, companyViewRulesId);
        log.warn("CompanyStatusViewRules not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatusViewRules")
                .message("CompanyStatusViewRules not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a company status view rules record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCompanyStatusViewRulesDoesNotExist(CompanyStatusViewRulesDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCompanyStatusId() : null) == null || (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null) == null) {
            return;
        }

        CompanyStatusViewRulesKey key = buildKey((dto.getId() != null ? dto.getId().getCompanyStatusId() : null), (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null));

        if (companyStatusViewRulesRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCompanyStatusId() : null), (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null));
            log.warn("CompanyStatusViewRules already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyStatusViewRules")
                    .message("CompanyStatusViewRules already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param companyStatusId the companyStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return populated {@link CompanyStatusViewRulesKey}
     */
    private CompanyStatusViewRulesKey buildKey(UUID companyStatusId, UUID companyViewRulesId) {
        CompanyStatusViewRulesKey key = new CompanyStatusViewRulesKey();
        key.setCompanyStatusId(companyStatusId);
        key.setCompanyViewRulesId(companyViewRulesId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param companyStatusId the companyStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return composite id string
     */
    private String buildCompositeId(UUID companyStatusId, UUID companyViewRulesId) {
        return "companyStatusId=" + companyStatusId + ", " + "companyViewRulesId=" + companyViewRulesId;
    }

}
