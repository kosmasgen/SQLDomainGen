package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.BusinessLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link BusinessLocation} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface BusinessLocationRepository extends JpaRepository<BusinessLocation, UUID> {
}
