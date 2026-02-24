package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryCompanyi18nRepository extends JpaRepository<TemporaryCompanyi18n, Long> {
}
