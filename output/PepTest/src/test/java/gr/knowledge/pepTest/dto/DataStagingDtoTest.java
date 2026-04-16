package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class DataStagingDtoTest {

    /**
     * Tests the DataStagingDto no-args constructor
     */
    @Test
    void testDataStagingDtoNoArgsConstructor() {
        DataStagingDto dto = new DataStagingDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getLegacyTableName()).isNull();
        assertThat(dto.getLegacyRecordId()).isNull();
        assertThat(dto.getRawData()).isNull();
        assertThat(dto.getLegacyUpdatedAt()).isNull();
        assertThat(dto.getPulledAt()).isNull();
        assertThat(dto.getStatus()).isNull();
    }

    /**
     * Tests the DataStagingDto all-args constructor
     */
    @Test
    void testDataStagingDtoAllArgsConstructor() {
        Long id = 1L;
        String legacyTableName = "test-value";
        String legacyRecordId = "test-value";
        String rawData = "test-value";
        LocalDateTime legacyUpdatedAt = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime pulledAt = LocalDateTime.of(2025, 1, 1, 10, 0);
        String status = "test-value";

        DataStagingDto dto = new DataStagingDto(id, legacyTableName, legacyRecordId, rawData, legacyUpdatedAt, pulledAt, status);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getLegacyTableName()).isEqualTo(legacyTableName);
        assertThat(dto.getLegacyRecordId()).isEqualTo(legacyRecordId);
        assertThat(dto.getRawData()).isEqualTo(rawData);
        assertThat(dto.getLegacyUpdatedAt()).isEqualTo(legacyUpdatedAt);
        assertThat(dto.getPulledAt()).isEqualTo(pulledAt);
        assertThat(dto.getStatus()).isEqualTo(status);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testDataStagingDtoSettersAndGetters() {
        DataStagingDto dto = new DataStagingDto();

        Long id = 1L;
        String legacyTableName = "test-value";
        String legacyRecordId = "test-value";
        String rawData = "test-value";
        LocalDateTime legacyUpdatedAt = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime pulledAt = LocalDateTime.of(2025, 1, 1, 10, 0);
        String status = "test-value";

        dto.setId(id);
        dto.setLegacyTableName(legacyTableName);
        dto.setLegacyRecordId(legacyRecordId);
        dto.setRawData(rawData);
        dto.setLegacyUpdatedAt(legacyUpdatedAt);
        dto.setPulledAt(pulledAt);
        dto.setStatus(status);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getLegacyTableName()).isEqualTo(legacyTableName);
        assertThat(dto.getLegacyRecordId()).isEqualTo(legacyRecordId);
        assertThat(dto.getRawData()).isEqualTo(rawData);
        assertThat(dto.getLegacyUpdatedAt()).isEqualTo(legacyUpdatedAt);
        assertThat(dto.getPulledAt()).isEqualTo(pulledAt);
        assertThat(dto.getStatus()).isEqualTo(status);
    }

}
