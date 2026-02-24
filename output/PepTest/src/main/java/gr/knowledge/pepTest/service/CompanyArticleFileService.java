package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyArticleFile} domain operations.
 */
public interface CompanyArticleFileService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyArticleFileDto}
     */
    List<CompanyArticleFileDto> getAllCompanyArticleFile();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyArticleFileDto}
     */
    CompanyArticleFileDto getCompanyArticleFileById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyArticleFileDto}
     */
    CompanyArticleFileDto createCompanyArticleFile(CompanyArticleFileDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyArticleFileDto}
     */
    CompanyArticleFileDto updateCompanyArticleFile(UUID id, CompanyArticleFileDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyArticleFile(UUID id);
}
