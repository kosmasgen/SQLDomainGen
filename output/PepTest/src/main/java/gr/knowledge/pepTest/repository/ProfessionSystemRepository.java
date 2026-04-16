package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ProfessionSystem} entity.
 * Provides database access methods for ProfessionSystem.
 */
@Repository
public interface ProfessionSystemRepository extends JpaRepository<ProfessionSystem, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberProfSystemId.
     *
     * @param chamberId value to check
     * @param chamberProfSystemId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberProfSystemId(Integer chamberId, Integer chamberProfSystemId);
}
