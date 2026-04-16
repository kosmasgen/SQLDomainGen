package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
import java.util.UUID;

/**
 * Repository for {@link ProfessionKindi18n} entity.
 * Provides database access methods for ProfessionKindi18n.
 */
@Repository
public interface ProfessionKindi18nRepository extends JpaRepository<ProfessionKindi18n, ProfessionKindi18nKey> {

    /**
     * Checks if an entity exists with the given professionKindId and chamberI18nId.
     *
     * @param professionKindId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdProfessionKindIdAndChamberI18nId(UUID professionKindId, Integer chamberI18nId);
}
