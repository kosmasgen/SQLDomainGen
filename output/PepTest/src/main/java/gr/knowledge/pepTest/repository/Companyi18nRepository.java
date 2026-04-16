package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Companyi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.Companyi18nKey;
import java.util.UUID;

/**
 * Repository for {@link Companyi18n} entity.
 * Provides database access methods for Companyi18n.
 */
@Repository
public interface Companyi18nRepository extends JpaRepository<Companyi18n, Companyi18nKey> {

    /**
     * Checks if an entity exists with the given companyId and chamberI18nId.
     *
     * @param companyId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdCompanyIdAndIdChamberI18nId(UUID companyId, Integer chamberI18nId);
}
