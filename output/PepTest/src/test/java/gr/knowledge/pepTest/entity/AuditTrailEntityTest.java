package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class AuditTrailEntityTest {

    /**
     * Tests the AuditTrail no-args constructor.
     */
    @Test
    void testAuditTrailNoArgsConstructor() {
        AuditTrail entity = new AuditTrail();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the AuditTrail all-args constructor.
     */
    @Test
    void testAuditTrailAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String ip = "test-value";
        String completeUri = "test-value";
        Company company = new Company();
        CompanyProfile profile = new CompanyProfile();
        String uriPath = "test-value";
        Country country = new Country();

        AuditTrail entity = new AuditTrail(id, dateCreated, ip, completeUri, company, profile, uriPath, country);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getIp()).isEqualTo(ip);
        assertThat(entity.getCompleteUri()).isEqualTo(completeUri);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getProfile()).isEqualTo(profile);
        assertThat(entity.getUriPath()).isEqualTo(uriPath);
        assertThat(entity.getCountry()).isEqualTo(country);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testAuditTrailSettersAndGetters() {
        AuditTrail entity = new AuditTrail();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String ip = "test-value";
        String completeUri = "test-value";
        Company company = new Company();
        CompanyProfile profile = new CompanyProfile();
        String uriPath = "test-value";
        Country country = new Country();

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setIp(ip);
        entity.setCompleteUri(completeUri);
        entity.setCompany(company);
        entity.setProfile(profile);
        entity.setUriPath(uriPath);
        entity.setCountry(country);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getIp()).isEqualTo(ip);
        assertThat(entity.getCompleteUri()).isEqualTo(completeUri);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getProfile()).isEqualTo(profile);
        assertThat(entity.getUriPath()).isEqualTo(uriPath);
        assertThat(entity.getCountry()).isEqualTo(country);
    }

}
