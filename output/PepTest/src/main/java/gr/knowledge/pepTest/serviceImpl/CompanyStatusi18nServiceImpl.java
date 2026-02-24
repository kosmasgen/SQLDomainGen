package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.mapper.CompanyStatusi18nMapper;
import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import gr.knowledge.pepTest.repository.CompanyStatusi18nRepository;
import gr.knowledge.pepTest.service.CompanyStatusi18nService;
import gr.knowledge.pepTest.entity.CompanyStatusi18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyStatusi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusi18nServiceImpl implements CompanyStatusi18nService {

    private final CompanyStatusi18nRepository companyStatusi18nRepository;
    private final CompanyStatusi18nMapper companyStatusi18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusi18nDto}
     */
    @Override
    public List<CompanyStatusi18nDto> getAllCompanyStatusi18n() {
        log.info("Fetching all company-statusi18n.");
        return companyStatusi18nMapper.toDTO(companyStatusi18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusi18nDto}
     */
    @Override
    public CompanyStatusi18nDto getCompanyStatusi18nById(CompanyStatusi18nPK id) {
        log.info("Fetching company-statusi18n with id: {}", id);
        CompanyStatusi18n entity = companyStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusi18n not found with id: " + id));
        return companyStatusi18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusi18nDto}
     */
    @Override
    public CompanyStatusi18nDto createCompanyStatusi18n(CompanyStatusi18nDto dto) {
        log.info("Creating company-statusi18n.");
        CompanyStatusi18n entity = companyStatusi18nMapper.toEntity(dto);
        CompanyStatusi18n saved = companyStatusi18nRepository.save(entity);
        return companyStatusi18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusi18nDto}
     */
    @Override
    public CompanyStatusi18nDto updateCompanyStatusi18n(CompanyStatusi18nPK id, CompanyStatusi18nDto dto) {
        log.info("Updating company-statusi18n with id: {}", id);
        companyStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusi18n not found with id: " + id));
        CompanyStatusi18n entity = companyStatusi18nMapper.toEntity(dto);
        entity.setId(id);
        CompanyStatusi18n saved = companyStatusi18nRepository.save(entity);
        return companyStatusi18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyStatusi18n(CompanyStatusi18nPK id) {
        log.info("Deleting company-statusi18n with id: {}", id);
        companyStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatusi18n not found with id: " + id));
        companyStatusi18nRepository.deleteById(id);
    }
}
