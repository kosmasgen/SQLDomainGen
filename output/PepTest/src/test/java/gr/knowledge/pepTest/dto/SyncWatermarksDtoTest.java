package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class SyncWatermarksDtoTest {

    /**
     * Tests the SyncWatermarksDto no-args constructor
     */
    @Test
    void testSyncWatermarksDtoNoArgsConstructor() {
        SyncWatermarksDto dto = new SyncWatermarksDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getTableName()).isNull();
        assertThat(dto.getLastSyncTimestamp()).isNull();
        assertThat(dto.getUpdatedAt()).isNull();
    }

    /**
     * Tests the SyncWatermarksDto all-args constructor
     */
    @Test
    void testSyncWatermarksDtoAllArgsConstructor() {
        Long id = 1L;
        String tableName = "test-value";
        LocalDateTime lastSyncTimestamp = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 1, 1, 10, 0);

        SyncWatermarksDto dto = new SyncWatermarksDto(id, tableName, lastSyncTimestamp, updatedAt);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getTableName()).isEqualTo(tableName);
        assertThat(dto.getLastSyncTimestamp()).isEqualTo(lastSyncTimestamp);
        assertThat(dto.getUpdatedAt()).isEqualTo(updatedAt);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testSyncWatermarksDtoSettersAndGetters() {
        SyncWatermarksDto dto = new SyncWatermarksDto();

        Long id = 1L;
        String tableName = "test-value";
        LocalDateTime lastSyncTimestamp = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setTableName(tableName);
        dto.setLastSyncTimestamp(lastSyncTimestamp);
        dto.setUpdatedAt(updatedAt);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getTableName()).isEqualTo(tableName);
        assertThat(dto.getLastSyncTimestamp()).isEqualTo(lastSyncTimestamp);
        assertThat(dto.getUpdatedAt()).isEqualTo(updatedAt);
    }

}
