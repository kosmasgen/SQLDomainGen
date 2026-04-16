package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class BgPoiEntityTest {

    /**
     * Tests the BgPoi no-args constructor.
     */
    @Test
    void testBgPoiNoArgsConstructor() {
        BgPoi entity = new BgPoi();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the BgPoi all-args constructor.
     */
    @Test
    void testBgPoiAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String latitude = "test-value";
        String longitude = "test-value";

        BgPoi entity = new BgPoi(id, chamberId, dateCreated, lastUpdated, recdeleted, latitude, longitude);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testBgPoiSettersAndGetters() {
        BgPoi entity = new BgPoi();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String latitude = "test-value";
        String longitude = "test-value";

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
    }

}
