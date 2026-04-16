package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyStatus} entity.
 * Provides database access methods for CompanyStatus.
 */
@Repository
public interface CompanyStatusRepository extends JpaRepository<CompanyStatus, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberCompanyStatusId.
     *
     * @param chamberId value to check
     * @param chamberCompanyStatusId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberCompanyStatusId(Integer chamberId, Integer chamberCompanyStatusId);
}
