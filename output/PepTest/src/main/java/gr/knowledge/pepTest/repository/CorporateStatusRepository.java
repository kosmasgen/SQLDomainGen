package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CorporateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CorporateStatus} entity.
 * Provides database access methods for CorporateStatus.
 */
@Repository
public interface CorporateStatusRepository extends JpaRepository<CorporateStatus, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberCorporateStatusId.
     *
     * @param chamberId value to check
     * @param chamberCorporateStatusId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberCorporateStatusId(Integer chamberId, Integer chamberCorporateStatusId);
}
