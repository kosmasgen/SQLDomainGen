package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Professioni18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.Professioni18nPK;

@Repository
public interface Professioni18nRepository extends JpaRepository<Professioni18n, Professioni18nPK> {
}
