package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyYpPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyYpPhoto} entity.
 * Provides database access methods for CompanyYpPhoto.
 */
@Repository
public interface CompanyYpPhotoRepository extends JpaRepository<CompanyYpPhoto, UUID> {

    /**
     * Checks if an entity exists with the given companyId and fileName.
     *
     * @param companyId value to check
     * @param fileName value to check
     * @return true if exists, false otherwise
     */
    boolean existsByCompanyIdAndFileName(UUID companyId, String fileName);
}
