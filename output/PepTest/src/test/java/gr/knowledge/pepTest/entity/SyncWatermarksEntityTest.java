package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class SyncWatermarksEntityTest {

    /**
     * Tests the SyncWatermarks no-args constructor.
     */
    @Test
    void testSyncWatermarksNoArgsConstructor() {
        SyncWatermarks entity = new SyncWatermarks();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the SyncWatermarks all-args constructor.
     */
    @Test
    void testSyncWatermarksAllArgsConstructor() {
        Long id = 1L;
        String tableName = "test-value";
        LocalDateTime lastSyncTimestamp = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        SyncWatermarks entity = new SyncWatermarks(id, tableName, lastSyncTimestamp, updatedAt);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTableName()).isEqualTo(tableName);
        assertThat(entity.getLastSyncTimestamp()).isEqualTo(lastSyncTimestamp);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testSyncWatermarksSettersAndGetters() {
        SyncWatermarks entity = new SyncWatermarks();

        Long id = 1L;
        String tableName = "test-value";
        LocalDateTime lastSyncTimestamp = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setTableName(tableName);
        entity.setLastSyncTimestamp(lastSyncTimestamp);
        entity.setUpdatedAt(updatedAt);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTableName()).isEqualTo(tableName);
        assertThat(entity.getLastSyncTimestamp()).isEqualTo(lastSyncTimestamp);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

}
