package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class DataStagingEntityTest {

    /**
     * Tests the DataStaging no-args constructor.
     */
    @Test
    void testDataStagingNoArgsConstructor() {
        DataStaging entity = new DataStaging();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the DataStaging all-args constructor.
     */
    @Test
    void testDataStagingAllArgsConstructor() {
        Long id = 1L;
        String legacyTableName = "test-value";
        String legacyRecordId = "test-value";
        String rawData = "test-value";
        LocalDateTime legacyUpdatedAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime pulledAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String status = "test-value";

        DataStaging entity = new DataStaging(id, legacyTableName, legacyRecordId, rawData, legacyUpdatedAt, pulledAt, status);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getLegacyTableName()).isEqualTo(legacyTableName);
        assertThat(entity.getLegacyRecordId()).isEqualTo(legacyRecordId);
        assertThat(entity.getRawData()).isEqualTo(rawData);
        assertThat(entity.getLegacyUpdatedAt()).isEqualTo(legacyUpdatedAt);
        assertThat(entity.getPulledAt()).isEqualTo(pulledAt);
        assertThat(entity.getStatus()).isEqualTo(status);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testDataStagingSettersAndGetters() {
        DataStaging entity = new DataStaging();

        Long id = 1L;
        String legacyTableName = "test-value";
        String legacyRecordId = "test-value";
        String rawData = "test-value";
        LocalDateTime legacyUpdatedAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime pulledAt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String status = "test-value";

        entity.setId(id);
        entity.setLegacyTableName(legacyTableName);
        entity.setLegacyRecordId(legacyRecordId);
        entity.setRawData(rawData);
        entity.setLegacyUpdatedAt(legacyUpdatedAt);
        entity.setPulledAt(pulledAt);
        entity.setStatus(status);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getLegacyTableName()).isEqualTo(legacyTableName);
        assertThat(entity.getLegacyRecordId()).isEqualTo(legacyRecordId);
        assertThat(entity.getRawData()).isEqualTo(rawData);
        assertThat(entity.getLegacyUpdatedAt()).isEqualTo(legacyUpdatedAt);
        assertThat(entity.getPulledAt()).isEqualTo(pulledAt);
        assertThat(entity.getStatus()).isEqualTo(status);
    }

}
