package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ProfessionKindEntityTest {

    /**
     * Tests the ProfessionKind no-args constructor.
     */
    @Test
    void testProfessionKindNoArgsConstructor() {
        ProfessionKind entity = new ProfessionKind();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ProfessionKind all-args constructor.
     */
    @Test
    void testProfessionKindAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfKindId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        ProfessionKind entity = new ProfessionKind(id, chamberId, chamberProfKindId, cd, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfKindId()).isEqualTo(chamberProfKindId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProfessionKindSettersAndGetters() {
        ProfessionKind entity = new ProfessionKind();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfKindId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberProfKindId(chamberProfKindId);
        entity.setCd(cd);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfKindId()).isEqualTo(chamberProfKindId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
