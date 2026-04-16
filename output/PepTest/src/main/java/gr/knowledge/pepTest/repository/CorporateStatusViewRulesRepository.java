package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;

/**
 * Repository for {@link CorporateStatusViewRules} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CorporateStatusViewRulesRepository extends JpaRepository<CorporateStatusViewRules, CorporateStatusViewRulesKey> {
}
