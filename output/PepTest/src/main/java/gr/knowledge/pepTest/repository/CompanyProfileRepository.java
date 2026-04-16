package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyProfile} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, UUID> {
}
