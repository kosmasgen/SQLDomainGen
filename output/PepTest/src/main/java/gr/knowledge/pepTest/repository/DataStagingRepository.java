package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.DataStaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

/**
 * Repository for {@link DataStaging} entity.
 * Provides database access methods for DataStaging.
 */
@Repository
public interface DataStagingRepository extends JpaRepository<DataStaging, Long> {

    /**
     * Checks if an entity exists with the given legacyTableName, legacyRecordId and legacyUpdatedAt.
     *
     * @param legacyTableName value to check
     * @param legacyRecordId value to check
     * @param legacyUpdatedAt value to check
     * @return true if exists, false otherwise
     */
    boolean existsByLegacyTableNameAndLegacyRecordIdAndLegacyUpdatedAt(String legacyTableName, String legacyRecordId, LocalDateTime legacyUpdatedAt);
}
