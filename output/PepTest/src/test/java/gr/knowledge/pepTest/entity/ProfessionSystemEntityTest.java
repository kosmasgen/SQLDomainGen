package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ProfessionSystemEntityTest {

    /**
     * Tests the ProfessionSystem no-args constructor.
     */
    @Test
    void testProfessionSystemNoArgsConstructor() {
        ProfessionSystem entity = new ProfessionSystem();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ProfessionSystem all-args constructor.
     */
    @Test
    void testProfessionSystemAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfSystemId = 1;
        String cd = "test-value";
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        ProfessionSystem entity = new ProfessionSystem(id, chamberId, chamberProfSystemId, cd, description, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfSystemId()).isEqualTo(chamberProfSystemId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProfessionSystemSettersAndGetters() {
        ProfessionSystem entity = new ProfessionSystem();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfSystemId = 1;
        String cd = "test-value";
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberProfSystemId(chamberProfSystemId);
        entity.setCd(cd);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfSystemId()).isEqualTo(chamberProfSystemId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
