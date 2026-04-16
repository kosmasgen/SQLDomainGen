package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.IncomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link IncomeType} entity.
 * Provides database access methods for IncomeType.
 */
@Repository
public interface IncomeTypeRepository extends JpaRepository<IncomeType, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberTypeId.
     *
     * @param chamberId value to check
     * @param chamberTypeId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberTypeId(Integer chamberId, Integer chamberTypeId);
}
