package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code CompanyYpArticle} domain operations.
 */
public interface CompanyYpArticleService {

    /**
     * Retrieves all company yp articles.
     * @return non-null list of {@link CompanyYpArticleDto}
     */
    List<CompanyYpArticleDto> getAllCompanyYpArticles();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link CompanyYpArticleDto}
     */
    CompanyYpArticleDto getCompanyYpArticleById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link CompanyYpArticleDto}
     */
    CompanyYpArticleDto createCompanyYpArticle(CompanyYpArticleDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpArticleDto}
     */
    CompanyYpArticleDto updateCompanyYpArticle(UUID id, CompanyYpArticleDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteCompanyYpArticle(UUID id);
}
