package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class UserContactinfoDtoTest {

    /**
     * Tests the UserContactinfoDto no-args constructor
     */
    @Test
    void testUserContactinfoDtoNoArgsConstructor() {
        UserContactinfoDto dto = new UserContactinfoDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getUsername()).isNull();
        assertThat(dto.getEmail()).isNull();
        assertThat(dto.getPhone()).isNull();
        assertThat(dto.getMobile()).isNull();
        assertThat(dto.getContactUrl()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the UserContactinfoDto all-args constructor
     */
    @Test
    void testUserContactinfoDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String username = "test-value";
        String email = "test-value";
        String phone = "test-value";
        String mobile = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        UserContactinfoDto dto = new UserContactinfoDto(id, chamberId, company, username, email, phone, mobile, contactUrl, dateCreated, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getPhone()).isEqualTo(phone);
        assertThat(dto.getMobile()).isEqualTo(mobile);
        assertThat(dto.getContactUrl()).isEqualTo(contactUrl);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testUserContactinfoDtoSettersAndGetters() {
        UserContactinfoDto dto = new UserContactinfoDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String username = "test-value";
        String email = "test-value";
        String phone = "test-value";
        String mobile = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCompany(company);
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setMobile(mobile);
        dto.setContactUrl(contactUrl);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getPhone()).isEqualTo(phone);
        assertThat(dto.getMobile()).isEqualTo(mobile);
        assertThat(dto.getContactUrl()).isEqualTo(contactUrl);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
