package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ProfessionSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProfessionSystemRepository extends JpaRepository<ProfessionSystem, UUID> {
}
