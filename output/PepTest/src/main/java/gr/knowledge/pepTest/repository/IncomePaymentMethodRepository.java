package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link IncomePaymentMethod} entity.
 * Provides database access methods for IncomePaymentMethod.
 */
@Repository
public interface IncomePaymentMethodRepository extends JpaRepository<IncomePaymentMethod, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberPayMethodId.
     *
     * @param chamberId value to check
     * @param chamberPayMethodId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberPayMethodId(Integer chamberId, Integer chamberPayMethodId);
}
