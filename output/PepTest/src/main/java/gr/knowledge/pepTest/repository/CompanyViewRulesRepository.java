package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyViewRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyViewRules} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyViewRulesRepository extends JpaRepository<CompanyViewRules, UUID> {
}
