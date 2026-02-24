package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyYpArticle} domain operations.
 */
public interface CompanyYpArticleService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpArticleDto}
     */
    List<CompanyYpArticleDto> getAllCompanyYpArticle();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpArticleDto}
     */
    CompanyYpArticleDto getCompanyYpArticleById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpArticleDto}
     */
    CompanyYpArticleDto createCompanyYpArticle(CompanyYpArticleDto dto);

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
    CompanyYpArticleDto updateCompanyYpArticle(UUID id, CompanyYpArticleDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyYpArticle(UUID id);
}
