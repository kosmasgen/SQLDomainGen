package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.mapper.CorporateStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import gr.knowledge.pepTest.repository.CorporateStatusViewRulesRepository;
import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CorporateStatusViewRules} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusViewRulesServiceImpl implements CorporateStatusViewRulesService {

    private final CorporateStatusViewRulesRepository corporateStatusViewRulesRepository;
    private final CorporateStatusViewRulesMapper corporateStatusViewRulesMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusViewRulesDto}
     */
    @Override
    public List<CorporateStatusViewRulesDto> getAllCorporateStatusViewRules() {
        log.info("Fetching all corporate-status-view-rules.");
        return corporateStatusViewRulesMapper.toDTO(corporateStatusViewRulesRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusViewRulesDto}
     */
    @Override
    public CorporateStatusViewRulesDto getCorporateStatusViewRulesById(CorporateStatusViewRulesPK id) {
        log.info("Fetching corporate-status-view-rules with id: {}", id);
        CorporateStatusViewRules entity = corporateStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusViewRules not found with id: " + id));
        return corporateStatusViewRulesMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusViewRulesDto}
     */
    @Override
    public CorporateStatusViewRulesDto createCorporateStatusViewRules(CorporateStatusViewRulesDto dto) {
        log.info("Creating corporate-status-view-rules.");
        CorporateStatusViewRules entity = corporateStatusViewRulesMapper.toEntity(dto);
        CorporateStatusViewRules saved = corporateStatusViewRulesRepository.save(entity);
        return corporateStatusViewRulesMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusViewRulesDto}
     */
    @Override
    public CorporateStatusViewRulesDto updateCorporateStatusViewRules(CorporateStatusViewRulesPK id, CorporateStatusViewRulesDto dto) {
        log.info("Updating corporate-status-view-rules with id: {}", id);
        corporateStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusViewRules not found with id: " + id));
        CorporateStatusViewRules entity = corporateStatusViewRulesMapper.toEntity(dto);
        entity.setId(id);
        CorporateStatusViewRules saved = corporateStatusViewRulesRepository.save(entity);
        return corporateStatusViewRulesMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCorporateStatusViewRules(CorporateStatusViewRulesPK id) {
        log.info("Deleting corporate-status-view-rules with id: {}", id);
        corporateStatusViewRulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusViewRules not found with id: " + id));
        corporateStatusViewRulesRepository.deleteById(id);
    }
}
