package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyStatusi18nKey;
import java.util.UUID;

/**
 * Repository for {@link CompanyStatusi18n} entity.
 * Provides database access methods for CompanyStatusi18n.
 */
@Repository
public interface CompanyStatusi18nRepository extends JpaRepository<CompanyStatusi18n, CompanyStatusi18nKey> {

    /**
     * Checks if an entity exists with the given companyStatusId and chamberI18nId.
     *
     * @param companyStatusId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdCompanyStatusIdAndChamberI18nId(UUID companyStatusId, Integer chamberI18nId);
}
