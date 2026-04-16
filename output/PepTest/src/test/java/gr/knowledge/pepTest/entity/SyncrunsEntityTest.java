package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class SyncrunsEntityTest {

    /**
     * Tests the Syncruns no-args constructor.
     */
    @Test
    void testSyncrunsNoArgsConstructor() {
        Syncruns entity = new Syncruns();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Syncruns all-args constructor.
     */
    @Test
    void testSyncrunsAllArgsConstructor() {
        Long id = 1L;
        LocalDateTime lastRun = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime tradesLastRun = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean isRunning = Boolean.TRUE;

        Syncruns entity = new Syncruns(id, lastRun, tradesLastRun, isRunning);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getLastRun()).isEqualTo(lastRun);
        assertThat(entity.getTradesLastRun()).isEqualTo(tradesLastRun);
        assertThat(entity.getIsRunning()).isEqualTo(isRunning);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testSyncrunsSettersAndGetters() {
        Syncruns entity = new Syncruns();

        Long id = 1L;
        LocalDateTime lastRun = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime tradesLastRun = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean isRunning = Boolean.TRUE;

        entity.setId(id);
        entity.setLastRun(lastRun);
        entity.setTradesLastRun(tradesLastRun);
        entity.setIsRunning(isRunning);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getLastRun()).isEqualTo(lastRun);
        assertThat(entity.getTradesLastRun()).isEqualTo(tradesLastRun);
        assertThat(entity.getIsRunning()).isEqualTo(isRunning);
    }

}
