package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Companyi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.Companyi18nPK;

@Repository
public interface Companyi18nRepository extends JpaRepository<Companyi18n, Companyi18nPK> {
}
