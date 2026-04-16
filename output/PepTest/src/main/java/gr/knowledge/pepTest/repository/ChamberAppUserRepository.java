package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChamberAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ChamberAppUser} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface ChamberAppUserRepository extends JpaRepository<ChamberAppUser, UUID> {
}
