package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nPK;

@Repository
public interface CompanyYpArticleI18nRepository extends JpaRepository<CompanyYpArticleI18n, CompanyYpArticleI18nPK> {
}
