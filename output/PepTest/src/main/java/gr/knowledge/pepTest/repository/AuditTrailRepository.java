package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link AuditTrail} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, UUID> {
}
