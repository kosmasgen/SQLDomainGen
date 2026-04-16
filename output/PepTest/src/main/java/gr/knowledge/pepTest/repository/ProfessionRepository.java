package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Profession} entity.
 * Provides database access methods for Profession.
 */
@Repository
public interface ProfessionRepository extends JpaRepository<Profession, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberProfessionId.
     *
     * @param chamberId value to check
     * @param chamberProfessionId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberProfessionId(Integer chamberId, Integer chamberProfessionId);
}
