package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyYpArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyYpArticle} entity.
 * Provides database access methods for CompanyYpArticle.
 */
@Repository
public interface CompanyYpArticleRepository extends JpaRepository<CompanyYpArticle, UUID> {

    /**
     * Checks if an entity exists with the given companyId and title.
     *
     * @param companyId value to check
     * @param title value to check
     * @return true if exists, false otherwise
     */
    boolean existsByCompanyIdAndTitle(UUID companyId, String title);
}
