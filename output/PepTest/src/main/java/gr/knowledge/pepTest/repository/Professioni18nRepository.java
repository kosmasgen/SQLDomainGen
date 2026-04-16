package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Professioni18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.Professioni18nKey;
import java.util.UUID;

/**
 * Repository for {@link Professioni18n} entity.
 * Provides database access methods for Professioni18n.
 */
@Repository
public interface Professioni18nRepository extends JpaRepository<Professioni18n, Professioni18nKey> {

    /**
     * Checks if an entity exists with the given professionId and chamberI18nId.
     *
     * @param professionId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdProfessionIdAndChamberI18nId(UUID professionId, Integer chamberI18nId);
}
