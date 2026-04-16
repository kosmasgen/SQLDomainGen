package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChAppUserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ChAppUserContact} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface ChAppUserContactRepository extends JpaRepository<ChAppUserContact, UUID> {
}
