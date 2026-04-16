package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link StatusHistory} entity.
 * Provides database access methods for StatusHistory.
 */
@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberStatusHistoryId.
     *
     * @param chamberId value to check
     * @param chamberStatusHistoryId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberStatusHistoryId(Integer chamberId, BigInteger chamberStatusHistoryId);
}
