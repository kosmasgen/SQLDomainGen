package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class UserGeodataEntityTest {

    /**
     * Tests the UserGeodata no-args constructor.
     */
    @Test
    void testUserGeodataNoArgsConstructor() {
        UserGeodata entity = new UserGeodata();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the UserGeodata all-args constructor.
     */
    @Test
    void testUserGeodataAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String username = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        UserGeodata entity = new UserGeodata(id, chamberId, company, username, latitude, longitude, dateCreated, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getUsername()).isEqualTo(username);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testUserGeodataSettersAndGetters() {
        UserGeodata entity = new UserGeodata();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String username = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCompany(company);
        entity.setUsername(username);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getUsername()).isEqualTo(username);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
