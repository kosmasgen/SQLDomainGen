package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Syncruns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Syncruns} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface SyncrunsRepository extends JpaRepository<Syncruns, Long> {
}
