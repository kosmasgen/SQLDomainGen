package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;

/**
 * Repository for {@link CompanyStatusViewRules} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyStatusViewRulesRepository extends JpaRepository<CompanyStatusViewRules, CompanyStatusViewRulesKey> {
}
