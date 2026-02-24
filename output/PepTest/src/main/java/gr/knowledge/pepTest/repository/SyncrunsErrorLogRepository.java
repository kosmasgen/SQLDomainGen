package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.SyncrunsErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncrunsErrorLogRepository extends JpaRepository<SyncrunsErrorLog, String> {
}
