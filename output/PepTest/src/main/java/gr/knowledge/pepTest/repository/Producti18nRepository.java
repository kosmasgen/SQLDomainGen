package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Producti18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.Producti18nPK;

@Repository
public interface Producti18nRepository extends JpaRepository<Producti18n, Producti18nPK> {
}
