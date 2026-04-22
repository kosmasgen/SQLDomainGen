package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.mapper.CompanyViewRulesMapper;
import gr.knowledge.pepTest.entity.CompanyViewRules;
import gr.knowledge.pepTest.repository.CompanyViewRulesRepository;
import gr.knowledge.pepTest.service.CompanyViewRulesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company View Rules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyViewRulesServiceImpl implements CompanyViewRulesService {

    private final CompanyViewRulesRepository companyViewRulesRepository;
    private final CompanyViewRulesMapper companyViewRulesMapper;

    /**
     * Retrieves all company view ruleses records.
     * @return list of CompanyViewRulesDto
     */
    @Override
    public List<CompanyViewRulesDto> getAllCompanyViewRuleses() {
        log.info("Fetching all company view ruleses records.");
        return companyViewRulesMapper.toDTOList(companyViewRulesRepository.findAll());
    }

    /**
     * Retrieves a company view rules record by id.
     * @param id the company view rules id
     * @return CompanyViewRulesDto
     */
    @Override
    public CompanyViewRulesDto getCompanyViewRulesById(UUID id) {
        log.info("Fetching company view rules with id: {}", id);

        CompanyViewRules existingEntity = findCompanyViewRulesByIdOrThrow(id);
        return companyViewRulesMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company view rules record.
     * @param dto input payload
     * @return created {@link CompanyViewRulesDto}
     */
    @Override
    public CompanyViewRulesDto createCompanyViewRules(CompanyViewRulesDto dto) {
        log.info("Creating company view rules.");

        CompanyViewRules entity = companyViewRulesMapper.toEntity(dto);
        CompanyViewRules savedEntity = companyViewRulesRepository.save(entity);

        return companyViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company view rules record.
     *
     * @param id the company view rules id
     * @param dto input payload
     * @return updated {@link CompanyViewRulesDto}
     */
    @Override
    public CompanyViewRulesDto updateCompanyViewRules(UUID id, CompanyViewRulesDto dto) {
        log.info("Updating company view rules with id: {}", id);

        CompanyViewRules existingEntity = findCompanyViewRulesByIdOrThrow(id);
        companyViewRulesMapper.partialUpdate(existingEntity, dto);
        CompanyViewRules savedEntity = companyViewRulesRepository.save(existingEntity);

        return companyViewRulesMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company view rules record by id.
     * @param id the company view rules id
     */
    @Override
    public void deleteCompanyViewRules(UUID id) {
        log.info("Deleting company view rules with id: {}", id);

        findCompanyViewRulesByIdOrThrow(id);
        companyViewRulesRepository.deleteById(id);
    }

    /**
     * Finds an existing company view rules record by id or throws an exception.
     * @param id the company view rules id
     * @return existing CompanyViewRules entity
     */
    private CompanyViewRules findCompanyViewRulesByIdOrThrow(UUID id) {
        return companyViewRulesRepository.findById(id)
                .orElseThrow(() -> createCompanyViewRulesNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company view rules entity.
     @param id the company view rules id
     @return runtime exception
     */
    private RuntimeException createCompanyViewRulesNotFoundException(UUID id) {
        log.warn("CompanyViewRules not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyViewRules")
                .message("CompanyViewRules not found with id: " + id)
                .build();
    }

}
