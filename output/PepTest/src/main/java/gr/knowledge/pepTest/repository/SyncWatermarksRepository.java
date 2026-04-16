package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.SyncWatermarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link SyncWatermarks} entity.
 * Provides database access methods for SyncWatermarks.
 */
@Repository
public interface SyncWatermarksRepository extends JpaRepository<SyncWatermarks, Long> {

    /**
     * Checks if an entity exists with the given tableName.
     *
     * @param tableName value to check
     * @return true if exists, false otherwise
     */
    boolean existsByTableName(String tableName);
}
