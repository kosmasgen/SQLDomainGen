package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.MunicipalityI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
import java.util.UUID;

/**
 * Repository for {@link MunicipalityI18n} entity.
 * Provides database access methods for MunicipalityI18n.
 */
@Repository
public interface MunicipalityI18nRepository extends JpaRepository<MunicipalityI18n, MunicipalityI18nKey> {

    /**
     * Checks if an entity exists with the given municipalityId and chamberI18nId.
     *
     * @param municipalityId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdMunicipalityIdAndChamberI18nId(UUID municipalityId, Integer chamberI18nId);
}
