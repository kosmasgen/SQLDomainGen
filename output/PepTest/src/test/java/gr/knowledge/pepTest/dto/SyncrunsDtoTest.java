package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class SyncrunsDtoTest {

    /**
     * Tests the SyncrunsDto no-args constructor
     */
    @Test
    void testSyncrunsDtoNoArgsConstructor() {
        SyncrunsDto dto = new SyncrunsDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getLastRun()).isNull();
        assertThat(dto.getTradesLastRun()).isNull();
        assertThat(dto.getIsRunning()).isNull();
    }

    /**
     * Tests the SyncrunsDto all-args constructor
     */
    @Test
    void testSyncrunsDtoAllArgsConstructor() {
        Long id = 1L;
        LocalDateTime lastRun = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime tradesLastRun = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean isRunning = Boolean.TRUE;

        SyncrunsDto dto = new SyncrunsDto(id, lastRun, tradesLastRun, isRunning);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getLastRun()).isEqualTo(lastRun);
        assertThat(dto.getTradesLastRun()).isEqualTo(tradesLastRun);
        assertThat(dto.getIsRunning()).isEqualTo(isRunning);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testSyncrunsDtoSettersAndGetters() {
        SyncrunsDto dto = new SyncrunsDto();

        Long id = 1L;
        LocalDateTime lastRun = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime tradesLastRun = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean isRunning = Boolean.TRUE;

        dto.setId(id);
        dto.setLastRun(lastRun);
        dto.setTradesLastRun(tradesLastRun);
        dto.setIsRunning(isRunning);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getLastRun()).isEqualTo(lastRun);
        assertThat(dto.getTradesLastRun()).isEqualTo(tradesLastRun);
        assertThat(dto.getIsRunning()).isEqualTo(isRunning);
    }

}
