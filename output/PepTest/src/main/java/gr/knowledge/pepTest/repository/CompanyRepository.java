package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link Company} entity.
 * Provides database access methods for Company.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberCompanyId.
     *
     * @param chamberId value to check
     * @param chamberCompanyId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberCompanyId(Integer chamberId, BigInteger chamberCompanyId);
}
