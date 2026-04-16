package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CountryEntityTest {

    /**
     * Tests the Country no-args constructor.
     */
    @Test
    void testCountryNoArgsConstructor() {
        Country entity = new Country();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Country all-args constructor.
     */
    @Test
    void testCountryAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberId = 1;
        UUID regionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCountryId = 1;

        Country entity = new Country(id, dateCreated, lastUpdated, recdeleted, chamberId, regionId, chamberCountryId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getRegionId()).isEqualTo(regionId);
        assertThat(entity.getChamberCountryId()).isEqualTo(chamberCountryId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCountrySettersAndGetters() {
        Country entity = new Country();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberId = 1;
        UUID regionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCountryId = 1;

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberId(chamberId);
        entity.setRegionId(regionId);
        entity.setChamberCountryId(chamberCountryId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getRegionId()).isEqualTo(regionId);
        assertThat(entity.getChamberCountryId()).isEqualTo(chamberCountryId);
    }

}
