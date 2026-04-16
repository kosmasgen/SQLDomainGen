package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link ProfessionFriendlyCategory} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface ProfessionFriendlyCategoryRepository extends JpaRepository<ProfessionFriendlyCategory, String> {
}
