package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.mapper.CompanyViewRulesMapper;
import gr.knowledge.pepTest.entity.CompanyViewRules;
import gr.knowledge.pepTest.repository.CompanyViewRulesRepository;
import gr.knowledge.pepTest.service.CompanyViewRulesService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyViewRules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyViewRulesServiceImpl implements CompanyViewRulesService {

    private final CompanyViewRulesRepository companyViewRulesRepository;
    private final CompanyViewRulesMapper companyViewRulesMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyViewRulesDto}
     */
    @Override
    public List<CompanyViewRulesDto> getAllCompanyViewRules() {
        log.info("Fetching all company-view-rules.");
        return companyViewRulesMapper.toDTO(companyViewRulesRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyViewRulesDto}
     */
    @Override
    public CompanyViewRulesDto getCompanyViewRulesById(UUID id) {
        log.info("Fetching company-view-rules with id: {}", id);
        CompanyViewRules entity = companyViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyViewRules not found with id: " + id));
        return companyViewRulesMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyViewRulesDto}
     */
    @Override
    public CompanyViewRulesDto createCompanyViewRules(CompanyViewRulesDto dto) {
        log.info("Creating company-view-rules.");
        CompanyViewRules entity = companyViewRulesMapper.toEntity(dto);
        CompanyViewRules saved = companyViewRulesRepository.save(entity);
        return companyViewRulesMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyViewRulesDto}
     */
    @Override
    public CompanyViewRulesDto updateCompanyViewRules(UUID id, CompanyViewRulesDto dto) {
        log.info("Updating company-view-rules with id: {}", id);
        companyViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyViewRules not found with id: " + id));
        CompanyViewRules entity = companyViewRulesMapper.toEntity(dto);
        entity.setId(id);
        CompanyViewRules saved = companyViewRulesRepository.save(entity);
        return companyViewRulesMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyViewRules(UUID id) {
        log.info("Deleting company-view-rules with id: {}", id);
        companyViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyViewRules not found with id: " + id));
        companyViewRulesRepository.deleteById(id);
    }
}
