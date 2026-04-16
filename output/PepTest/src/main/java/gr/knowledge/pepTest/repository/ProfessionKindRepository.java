package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ProfessionKind} entity.
 * Provides database access methods for ProfessionKind.
 */
@Repository
public interface ProfessionKindRepository extends JpaRepository<ProfessionKind, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberProfKindId.
     *
     * @param chamberId value to check
     * @param chamberProfKindId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberProfKindId(Integer chamberId, Integer chamberProfKindId);
}
