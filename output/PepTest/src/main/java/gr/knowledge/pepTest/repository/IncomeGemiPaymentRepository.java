package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link IncomeGemiPayment} entity.
 * Provides database access methods for IncomeGemiPayment.
 */
@Repository
public interface IncomeGemiPaymentRepository extends JpaRepository<IncomeGemiPayment, UUID> {

    /**
     * Checks if an entity exists with the given chamberId, gemiPaymentId, paymentType and cancelFlag.
     *
     * @param chamberId value to check
     * @param gemiPaymentId value to check
     * @param paymentType value to check
     * @param cancelFlag value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndGemiPaymentIdAndPaymentTypeAndCancelFlag(Integer chamberId, BigInteger gemiPaymentId, String paymentType, BigInteger cancelFlag);
}
