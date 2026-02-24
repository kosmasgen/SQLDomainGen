package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.mapper.CompanyTitlei18nMapper;
import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import gr.knowledge.pepTest.repository.CompanyTitlei18nRepository;
import gr.knowledge.pepTest.service.CompanyTitlei18nService;
import gr.knowledge.pepTest.entity.CompanyTitlei18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyTitlei18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyTitlei18nServiceImpl implements CompanyTitlei18nService {

    private final CompanyTitlei18nRepository companyTitlei18nRepository;
    private final CompanyTitlei18nMapper companyTitlei18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyTitlei18nDto}
     */
    @Override
    public List<CompanyTitlei18nDto> getAllCompanyTitlei18n() {
        log.info("Fetching all company-titlei18n.");
        return companyTitlei18nMapper.toDTO(companyTitlei18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyTitlei18nDto}
     */
    @Override
    public CompanyTitlei18nDto getCompanyTitlei18nById(CompanyTitlei18nPK id) {
        log.info("Fetching company-titlei18n with id: {}", id);
        CompanyTitlei18n entity = companyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitlei18n not found with id: " + id));
        return companyTitlei18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyTitlei18nDto}
     */
    @Override
    public CompanyTitlei18nDto createCompanyTitlei18n(CompanyTitlei18nDto dto) {
        log.info("Creating company-titlei18n.");
        CompanyTitlei18n entity = companyTitlei18nMapper.toEntity(dto);
        CompanyTitlei18n saved = companyTitlei18nRepository.save(entity);
        return companyTitlei18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyTitlei18nDto}
     */
    @Override
    public CompanyTitlei18nDto updateCompanyTitlei18n(CompanyTitlei18nPK id, CompanyTitlei18nDto dto) {
        log.info("Updating company-titlei18n with id: {}", id);
        companyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitlei18n not found with id: " + id));
        CompanyTitlei18n entity = companyTitlei18nMapper.toEntity(dto);
        entity.setId(id);
        CompanyTitlei18n saved = companyTitlei18nRepository.save(entity);
        return companyTitlei18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyTitlei18n(CompanyTitlei18nPK id) {
        log.info("Deleting company-titlei18n with id: {}", id);
        companyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitlei18n not found with id: " + id));
        companyTitlei18nRepository.deleteById(id);
    }
}
