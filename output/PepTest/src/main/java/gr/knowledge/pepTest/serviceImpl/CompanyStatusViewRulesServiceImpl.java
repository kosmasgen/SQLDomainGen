package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.mapper.CompanyStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import gr.knowledge.pepTest.repository.CompanyStatusViewRulesRepository;
import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyStatusViewRules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusViewRulesServiceImpl implements CompanyStatusViewRulesService {

    private final CompanyStatusViewRulesRepository companyStatusViewRulesRepository;
    private final CompanyStatusViewRulesMapper companyStatusViewRulesMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusViewRulesDto}
     */
    @Override
    public List<CompanyStatusViewRulesDto> getAllCompanyStatusViewRules() {
        log.info("Fetching all company-status-view-rules.");
        return companyStatusViewRulesMapper.toDTO(companyStatusViewRulesRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusViewRulesDto}
     */
    @Override
    public CompanyStatusViewRulesDto getCompanyStatusViewRulesById(CompanyStatusViewRulesPK id) {
        log.info("Fetching company-status-view-rules with id: {}", id);
        CompanyStatusViewRules entity = companyStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusViewRules not found with id: " + id));
        return companyStatusViewRulesMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusViewRulesDto}
     */
    @Override
    public CompanyStatusViewRulesDto createCompanyStatusViewRules(CompanyStatusViewRulesDto dto) {
        log.info("Creating company-status-view-rules.");
        CompanyStatusViewRules entity = companyStatusViewRulesMapper.toEntity(dto);
        CompanyStatusViewRules saved = companyStatusViewRulesRepository.save(entity);
        return companyStatusViewRulesMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusViewRulesDto}
     */
    @Override
    public CompanyStatusViewRulesDto updateCompanyStatusViewRules(CompanyStatusViewRulesPK id, CompanyStatusViewRulesDto dto) {
        log.info("Updating company-status-view-rules with id: {}", id);
        companyStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusViewRules not found with id: " + id));
        CompanyStatusViewRules entity = companyStatusViewRulesMapper.toEntity(dto);
        entity.setId(id);
        CompanyStatusViewRules saved = companyStatusViewRulesRepository.save(entity);
        return companyStatusViewRulesMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyStatusViewRules(CompanyStatusViewRulesPK id) {
        log.info("Deleting company-status-view-rules with id: {}", id);
        companyStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusViewRules not found with id: " + id));
        companyStatusViewRulesRepository.deleteById(id);
    }
}
