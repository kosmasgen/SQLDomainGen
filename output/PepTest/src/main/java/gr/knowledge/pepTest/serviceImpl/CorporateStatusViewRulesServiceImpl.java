package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.mapper.CorporateStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import gr.knowledge.pepTest.repository.CorporateStatusViewRulesRepository;
import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Corporate Status View Rules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusViewRulesServiceImpl implements CorporateStatusViewRulesService {

    private final CorporateStatusViewRulesRepository corporateStatusViewRulesRepository;
    private final CorporateStatusViewRulesMapper corporateStatusViewRulesMapper;

    /**
     * Retrieves all corporate status view ruleses records.
     * @return list of CorporateStatusViewRulesDto
     */
    @Override
    public List<CorporateStatusViewRulesDto> getAllCorporateStatusViewRuleses() {
        log.info("Fetching all corporate status view ruleses records.");
        return corporateStatusViewRulesMapper.toDTOList(corporateStatusViewRulesRepository.findAll());
    }

    /**
     * Retrieves a corporate status view rules record by id.
     * @param corporateStatusId the corporateStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return CorporateStatusViewRulesDto
     */
    @Override
    public CorporateStatusViewRulesDto getCorporateStatusViewRulesById(UUID corporateStatusId, UUID companyViewRulesId) {

        String compositeId = buildCompositeId(corporateStatusId, companyViewRulesId);
        log.info("Fetching corporate status view rules with composite id: {}", compositeId);

        CorporateStatusViewRules existingEntity = findCorporateStatusViewRulesByIdOrThrow(corporateStatusId, companyViewRulesId);
        return corporateStatusViewRulesMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new corporate status view rules record.
     * @param dto input payload
     * @return created {@link CorporateStatusViewRulesDto}
     */
    @Override
    public CorporateStatusViewRulesDto createCorporateStatusViewRules(CorporateStatusViewRulesDto dto) {
        log.info("Creating corporate status view rules.");

        validateCorporateStatusViewRulesDoesNotExist(dto);

        CorporateStatusViewRules entity = corporateStatusViewRulesMapper.toEntity(dto);
        CorporateStatusViewRules savedEntity = corporateStatusViewRulesRepository.save(entity);

        return corporateStatusViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing corporate status view rules record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param corporateStatusId the corporateStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusViewRulesDto}
     */
    @Override
    public CorporateStatusViewRulesDto updateCorporateStatusViewRules(UUID corporateStatusId, UUID companyViewRulesId, CorporateStatusViewRulesDto dto) {
        String compositeId = buildCompositeId(corporateStatusId, companyViewRulesId);

        log.info("Updating corporate status view rules with composite id: {}", compositeId);

        CorporateStatusViewRules existingEntity = findCorporateStatusViewRulesByIdOrThrow(corporateStatusId, companyViewRulesId);
        corporateStatusViewRulesMapper.partialUpdate(existingEntity, dto);
        CorporateStatusViewRules savedEntity = corporateStatusViewRulesRepository.save(existingEntity);

        return corporateStatusViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Delete a corporate status view rules record by id.
     * @param corporateStatusId the corporate_status_id value
     * @param companyViewRulesId the company_view_rules_id value
     */
    @Override
    public void deleteCorporateStatusViewRules(UUID corporateStatusId, UUID companyViewRulesId) {
        String compositeId = buildCompositeId(corporateStatusId, companyViewRulesId);
        log.info("Deleting corporate status view rules with composite id: {}", compositeId);

        findCorporateStatusViewRulesByIdOrThrow(corporateStatusId, companyViewRulesId);
        corporateStatusViewRulesRepository.deleteById(buildKey(corporateStatusId, companyViewRulesId));
    }

    /**
     * Finds an existing corporate status view rules record by id or throws an exception.
     * @param corporateStatusId the corporateStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return existing CorporateStatusViewRules entity
     */
    private CorporateStatusViewRules findCorporateStatusViewRulesByIdOrThrow(UUID corporateStatusId, UUID companyViewRulesId) {
        return corporateStatusViewRulesRepository.findById(buildKey(corporateStatusId, companyViewRulesId))
                .orElseThrow(() -> createCorporateStatusViewRulesNotFoundException(corporateStatusId, companyViewRulesId));
    }

    /**
     Creates a NOT FOUND exception for the corporate status view rules entity.
     @param corporateStatusId the corporate_status_id value
     @param companyViewRulesId the company_view_rules_id value
     @return runtime exception
     */
    private RuntimeException createCorporateStatusViewRulesNotFoundException(UUID corporateStatusId, UUID companyViewRulesId) {
        String compositeId = buildCompositeId(corporateStatusId, companyViewRulesId);
        log.warn("CorporateStatusViewRules not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatusViewRules")
                .message("CorporateStatusViewRules not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a corporate status view rules record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateCorporateStatusViewRulesDoesNotExist(CorporateStatusViewRulesDto dto) {
        if (dto == null || (dto.getId() != null ? dto.getId().getCorporateStatusId() : null) == null || (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null) == null) {
            return;
        }

        CorporateStatusViewRulesKey key = buildKey((dto.getId() != null ? dto.getId().getCorporateStatusId() : null), (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null));

        if (corporateStatusViewRulesRepository.existsById(key)) {
            String compositeId = buildCompositeId((dto.getId() != null ? dto.getId().getCorporateStatusId() : null), (dto.getId() != null ? dto.getId().getCompanyViewRulesId() : null));
            log.warn("CorporateStatusViewRules already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CorporateStatusViewRules")
                    .message("CorporateStatusViewRules already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param corporateStatusId the corporateStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return populated {@link CorporateStatusViewRulesKey}
     */
    private CorporateStatusViewRulesKey buildKey(UUID corporateStatusId, UUID companyViewRulesId) {
        CorporateStatusViewRulesKey key = new CorporateStatusViewRulesKey();
        key.setCorporateStatusId(corporateStatusId);
        key.setCompanyViewRulesId(companyViewRulesId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param corporateStatusId the corporateStatusId value
     * @param companyViewRulesId the companyViewRulesId value
     * @return composite id string
     */
    private String buildCompositeId(UUID corporateStatusId, UUID companyViewRulesId) {
        return "corporateStatusId=" + corporateStatusId + ", " + "companyViewRulesId=" + companyViewRulesId;
    }

}
