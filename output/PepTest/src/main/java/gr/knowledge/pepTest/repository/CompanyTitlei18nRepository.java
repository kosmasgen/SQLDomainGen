package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyTitlei18nKey;
import java.util.UUID;

/**
 * Repository for {@link CompanyTitlei18n} entity.
 * Provides database access methods for CompanyTitlei18n.
 */
@Repository
public interface CompanyTitlei18nRepository extends JpaRepository<CompanyTitlei18n, CompanyTitlei18nKey> {

    /**
     * Checks if an entity exists with the given companyTitleId and chamberI18nId.
     *
     * @param companyTitleId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdCompanyTitleIdAndIdChamberI18nId(UUID companyTitleId, Integer chamberI18nId);
}
