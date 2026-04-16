package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CorporateStatusi18nKey;
import java.util.UUID;

/**
 * Repository for {@link CorporateStatusi18n} entity.
 * Provides database access methods for CorporateStatusi18n.
 */
@Repository
public interface CorporateStatusi18nRepository extends JpaRepository<CorporateStatusi18n, CorporateStatusi18nKey> {

    /**
     * Checks if an entity exists with the given corporateStatusId and chamberI18nId.
     *
     * @param corporateStatusId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdCorporateStatusIdAndChamberI18nId(UUID corporateStatusId, Integer chamberI18nId);
}
