package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesPK;

@Repository
public interface CompanyStatusViewRulesRepository extends JpaRepository<CompanyStatusViewRules, CompanyStatusViewRulesPK> {
}
