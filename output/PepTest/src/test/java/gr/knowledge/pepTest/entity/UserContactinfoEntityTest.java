package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class UserContactinfoEntityTest {

    /**
     * Tests the UserContactinfo no-args constructor.
     */
    @Test
    void testUserContactinfoNoArgsConstructor() {
        UserContactinfo entity = new UserContactinfo();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the UserContactinfo all-args constructor.
     */
    @Test
    void testUserContactinfoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String username = "test-value";
        String email = "test-value";
        String phone = "test-value";
        String mobile = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        UserContactinfo entity = new UserContactinfo(id, chamberId, company, username, email, phone, mobile, contactUrl, dateCreated, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getUsername()).isEqualTo(username);
        assertThat(entity.getEmail()).isEqualTo(email);
        assertThat(entity.getPhone()).isEqualTo(phone);
        assertThat(entity.getMobile()).isEqualTo(mobile);
        assertThat(entity.getContactUrl()).isEqualTo(contactUrl);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testUserContactinfoSettersAndGetters() {
        UserContactinfo entity = new UserContactinfo();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String username = "test-value";
        String email = "test-value";
        String phone = "test-value";
        String mobile = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCompany(company);
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setMobile(mobile);
        entity.setContactUrl(contactUrl);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getUsername()).isEqualTo(username);
        assertThat(entity.getEmail()).isEqualTo(email);
        assertThat(entity.getPhone()).isEqualTo(phone);
        assertThat(entity.getMobile()).isEqualTo(mobile);
        assertThat(entity.getContactUrl()).isEqualTo(contactUrl);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
