package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.mapper.CompanyYpArticleMapper;
import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.repository.CompanyYpArticleRepository;
import gr.knowledge.pepTest.service.CompanyYpArticleService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyYpArticle} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpArticleServiceImpl implements CompanyYpArticleService {

    private final CompanyYpArticleRepository companyYpArticleRepository;
    private final CompanyYpArticleMapper companyYpArticleMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpArticleDto}
     */
    @Override
    public List<CompanyYpArticleDto> getAllCompanyYpArticle() {
        log.info("Fetching all company-yp-article.");
        return companyYpArticleMapper.toDTO(companyYpArticleRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpArticleDto}
     */
    @Override
    public CompanyYpArticleDto getCompanyYpArticleById(UUID id) {
        log.info("Fetching company-yp-article with id: {}", id);
        CompanyYpArticle entity = companyYpArticleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticle not found with id: " + id));
        return companyYpArticleMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpArticleDto}
     */
    @Override
    public CompanyYpArticleDto createCompanyYpArticle(CompanyYpArticleDto dto) {
        log.info("Creating company-yp-article.");
        CompanyYpArticle entity = companyYpArticleMapper.toEntity(dto);
        CompanyYpArticle saved = companyYpArticleRepository.save(entity);
        return companyYpArticleMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpArticleDto}
     */
    @Override
    public CompanyYpArticleDto updateCompanyYpArticle(UUID id, CompanyYpArticleDto dto) {
        log.info("Updating company-yp-article with id: {}", id);
        companyYpArticleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticle not found with id: " + id));
        CompanyYpArticle entity = companyYpArticleMapper.toEntity(dto);
        entity.setId(id);
        CompanyYpArticle saved = companyYpArticleRepository.save(entity);
        return companyYpArticleMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyYpArticle(UUID id) {
        log.info("Deleting company-yp-article with id: {}", id);
        companyYpArticleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpArticle not found with id: " + id));
        companyYpArticleRepository.deleteById(id);
    }
}
