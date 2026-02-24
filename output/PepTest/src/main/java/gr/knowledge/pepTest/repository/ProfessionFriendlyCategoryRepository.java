package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionFriendlyCategoryRepository extends JpaRepository<ProfessionFriendlyCategory, String> {
}
