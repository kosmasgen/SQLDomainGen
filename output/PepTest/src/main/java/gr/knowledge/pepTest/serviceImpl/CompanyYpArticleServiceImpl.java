package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.mapper.CompanyYpArticleMapper;
import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.repository.CompanyYpArticleRepository;
import gr.knowledge.pepTest.service.CompanyYpArticleService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Yp Article} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpArticleServiceImpl implements CompanyYpArticleService {

    private final CompanyYpArticleRepository companyYpArticleRepository;
    private final CompanyYpArticleMapper companyYpArticleMapper;

    /**
     * Retrieves all company yp articles records.
     * @return list of CompanyYpArticleDto
     */
    @Override
    public List<CompanyYpArticleDto> getAllCompanyYpArticles() {
        log.info("Fetching all company yp articles records.");
        return companyYpArticleMapper.toDTOList(companyYpArticleRepository.findAll());
    }

    /**
     * Retrieves a company yp article record by id.
     * @param id the company yp article id
     * @return CompanyYpArticleDto
     */
    @Override
    public CompanyYpArticleDto getCompanyYpArticleById(UUID id) {
        log.info("Fetching company yp article with id: {}", id);

        CompanyYpArticle existingEntity = findCompanyYpArticleByIdOrThrow(id);
        return companyYpArticleMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company yp article record.
     * @param dto input payload
     * @return created {@link CompanyYpArticleDto}
     */
    @Override
    public CompanyYpArticleDto createCompanyYpArticle(CompanyYpArticleDto dto) {
        log.info("Creating company yp article.");

        validateCompanyYpArticleCreateUniqueConstraints(dto);

        CompanyYpArticle entity = companyYpArticleMapper.toEntity(dto);
        CompanyYpArticle savedEntity = companyYpArticleRepository.save(entity);

        return companyYpArticleMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company yp article record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company yp article id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpArticleDto}
     */
    @Override
    public CompanyYpArticleDto updateCompanyYpArticle(UUID id, CompanyYpArticleDto dto) {
        log.info("Updating company yp article with id: {}", id);

        CompanyYpArticle existingEntity = findCompanyYpArticleByIdOrThrow(id);
        companyYpArticleMapper.partialUpdate(existingEntity, dto);
        CompanyYpArticle savedEntity = companyYpArticleRepository.save(existingEntity);

        return companyYpArticleMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company yp article record by id.
     * @param id the company yp article id
     */
    @Override
    public void deleteCompanyYpArticle(UUID id) {
        log.info("Deleting company yp article with id: {}", id);

        findCompanyYpArticleByIdOrThrow(id);
        companyYpArticleRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyYpArticleCreateUniqueConstraints(CompanyYpArticleDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getCompany() != null ? dto.getCompany().getId() : null) != null && dto.getTitle() != null && companyYpArticleRepository.existsByCompanyIdAndTitle((dto.getCompany() != null ? dto.getCompany().getId() : null), dto.getTitle())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyYpArticle")
                    .message("CompanyYpArticle already exists with " + "companyId=" + (dto.getCompany() != null ? dto.getCompany().getId() : null) + ", " + "title=" + dto.getTitle())
                    .build();
        }
    }

    /**
     * Finds an existing company yp article record by id or throws an exception.
     * @param id the company yp article id
     * @return existing CompanyYpArticle entity
     */
    private CompanyYpArticle findCompanyYpArticleByIdOrThrow(UUID id) {
        return companyYpArticleRepository.findById(id)
                .orElseThrow(() -> createCompanyYpArticleNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company yp article entity.
     @param id the company yp article id
     @return runtime exception
     */
    private RuntimeException createCompanyYpArticleNotFoundException(UUID id) {
        log.warn("CompanyYpArticle not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpArticle")
                .message("CompanyYpArticle not found with id: " + id)
                .build();
    }

}
