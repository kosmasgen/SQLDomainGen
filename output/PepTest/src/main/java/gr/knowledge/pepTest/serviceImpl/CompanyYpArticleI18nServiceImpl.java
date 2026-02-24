package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.mapper.CompanyYpArticleI18nMapper;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import gr.knowledge.pepTest.repository.CompanyYpArticleI18nRepository;
import gr.knowledge.pepTest.service.CompanyYpArticleI18nService;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyYpArticleI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpArticleI18nServiceImpl implements CompanyYpArticleI18nService {

    private final CompanyYpArticleI18nRepository companyYpArticleI18nRepository;
    private final CompanyYpArticleI18nMapper companyYpArticleI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpArticleI18nDto}
     */
    @Override
    public List<CompanyYpArticleI18nDto> getAllCompanyYpArticleI18n() {
        log.info("Fetching all company-yp-article-i18n.");
        return companyYpArticleI18nMapper.toDTO(companyYpArticleI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpArticleI18nDto}
     */
    @Override
    public CompanyYpArticleI18nDto getCompanyYpArticleI18nById(CompanyYpArticleI18nPK id) {
        log.info("Fetching company-yp-article-i18n with id: {}", id);
        CompanyYpArticleI18n entity = companyYpArticleI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticleI18n not found with id: " + id));
        return companyYpArticleI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpArticleI18nDto}
     */
    @Override
    public CompanyYpArticleI18nDto createCompanyYpArticleI18n(CompanyYpArticleI18nDto dto) {
        log.info("Creating company-yp-article-i18n.");
        CompanyYpArticleI18n entity = companyYpArticleI18nMapper.toEntity(dto);
        CompanyYpArticleI18n saved = companyYpArticleI18nRepository.save(entity);
        return companyYpArticleI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpArticleI18nDto}
     */
    @Override
    public CompanyYpArticleI18nDto updateCompanyYpArticleI18n(CompanyYpArticleI18nPK id, CompanyYpArticleI18nDto dto) {
        log.info("Updating company-yp-article-i18n with id: {}", id);
        companyYpArticleI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticleI18n not found with id: " + id));
        CompanyYpArticleI18n entity = companyYpArticleI18nMapper.toEntity(dto);
        entity.setId(id);
        CompanyYpArticleI18n saved = companyYpArticleI18nRepository.save(entity);
        return companyYpArticleI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyYpArticleI18n(CompanyYpArticleI18nPK id) {
        log.info("Deleting company-yp-article-i18n with id: {}", id);
        companyYpArticleI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticleI18n not found with id: " + id));
        companyYpArticleI18nRepository.deleteById(id);
    }
}
