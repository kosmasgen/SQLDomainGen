package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyStatusi18nPK;

@Repository
public interface CompanyStatusi18nRepository extends JpaRepository<CompanyStatusi18n, CompanyStatusi18nPK> {
}
