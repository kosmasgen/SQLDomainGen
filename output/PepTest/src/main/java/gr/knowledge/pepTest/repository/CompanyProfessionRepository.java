package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyProfession} entity.
 * Provides database access methods for CompanyProfession.
 */
@Repository
public interface CompanyProfessionRepository extends JpaRepository<CompanyProfession, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberCompanyProfessionId.
     *
     * @param chamberId value to check
     * @param chamberCompanyProfessionId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberCompanyProfessionId(Integer chamberId, Integer chamberCompanyProfessionId);
}
