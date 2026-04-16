package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.math.BigInteger;

/**
 * Repository for {@link CompanyTitle} entity.
 * Provides database access methods for CompanyTitle.
 */
@Repository
public interface CompanyTitleRepository extends JpaRepository<CompanyTitle, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberTitleId.
     *
     * @param chamberId value to check
     * @param chamberTitleId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberTitleId(Integer chamberId, BigInteger chamberTitleId);
}
