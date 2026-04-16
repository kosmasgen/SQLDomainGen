package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.IncomeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link IncomeTransaction} entity.
 * Provides database access methods for IncomeTransaction.
 */
@Repository
public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction, UUID> {

    /**
     * Checks if an entity exists with the given chamberId, chamberInTransdId and isKratisi.
     *
     * @param chamberId value to check
     * @param chamberInTransdId value to check
     * @param isKratisi value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberInTransdIdAndIsKratisi(Integer chamberId, BigInteger chamberInTransdId, BigInteger isKratisi);
}
