package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class UserGeodataDtoTest {

    /**
     * Tests the UserGeodataDto no-args constructor
     */
    @Test
    void testUserGeodataDtoNoArgsConstructor() {
        UserGeodataDto dto = new UserGeodataDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getUsername()).isNull();
        assertThat(dto.getLatitude()).isNull();
        assertThat(dto.getLongitude()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the UserGeodataDto all-args constructor
     */
    @Test
    void testUserGeodataDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String username = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        UserGeodataDto dto = new UserGeodataDto(id, chamberId, company, username, latitude, longitude, dateCreated, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testUserGeodataDtoSettersAndGetters() {
        UserGeodataDto dto = new UserGeodataDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String username = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCompany(company);
        dto.setUsername(username);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
