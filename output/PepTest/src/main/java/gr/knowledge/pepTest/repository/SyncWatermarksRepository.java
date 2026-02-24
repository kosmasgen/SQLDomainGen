package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.SyncWatermarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncWatermarksRepository extends JpaRepository<SyncWatermarks, Long> {
}
