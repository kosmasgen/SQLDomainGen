package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.StatsExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link StatsExpense} entity.
 * Provides database access methods for StatsExpense.
 */
@Repository
public interface StatsExpenseRepository extends JpaRepository<StatsExpense, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and accountSumId.
     *
     * @param chamberId value to check
     * @param accountSumId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndAccountSumId(Integer chamberId, BigInteger accountSumId);
}
