package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyTitlei18nPK;

@Repository
public interface CompanyTitlei18nRepository extends JpaRepository<CompanyTitlei18n, CompanyTitlei18nPK> {
}
