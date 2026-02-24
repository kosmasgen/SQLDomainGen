package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryCompanyTitlei18nRepository extends JpaRepository<TemporaryCompanyTitlei18n, Long> {
}
