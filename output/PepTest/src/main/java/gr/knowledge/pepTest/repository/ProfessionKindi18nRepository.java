package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.ProfessionKindi18nPK;

@Repository
public interface ProfessionKindi18nRepository extends JpaRepository<ProfessionKindi18n, ProfessionKindi18nPK> {
}
