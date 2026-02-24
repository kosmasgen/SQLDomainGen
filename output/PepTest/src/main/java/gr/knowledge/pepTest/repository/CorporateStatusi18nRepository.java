package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CorporateStatusi18nPK;

@Repository
public interface CorporateStatusi18nRepository extends JpaRepository<CorporateStatusi18n, CorporateStatusi18nPK> {
}
