package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.BusinessLocationI18nPK;

@Repository
public interface BusinessLocationI18nRepository extends JpaRepository<BusinessLocationI18n, BusinessLocationI18nPK> {
}
