package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nPK;

@Repository
public interface ChAppUserContactI18nRepository extends JpaRepository<ChAppUserContactI18n, ChAppUserContactI18nPK> {
}
