package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class AuditTrailDtoTest {

    /**
     * Tests the AuditTrailDto no-args constructor
     */
    @Test
    void testAuditTrailDtoNoArgsConstructor() {
        AuditTrailDto dto = new AuditTrailDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getIp()).isNull();
        assertThat(dto.getCompleteUri()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getProfile()).isNull();
        assertThat(dto.getUriPath()).isNull();
        assertThat(dto.getCountry()).isNull();
    }

    /**
     * Tests the AuditTrailDto all-args constructor
     */
    @Test
    void testAuditTrailDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String ip = "test-value";
        String completeUri = "test-value";
        CompanyDto company = new CompanyDto();
        CompanyProfileDto profile = new CompanyProfileDto();
        String uriPath = "test-value";
        CountryDto country = new CountryDto();

        AuditTrailDto dto = new AuditTrailDto(id, dateCreated, ip, completeUri, company, profile, uriPath, country);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getIp()).isEqualTo(ip);
        assertThat(dto.getCompleteUri()).isEqualTo(completeUri);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getProfile()).isEqualTo(profile);
        assertThat(dto.getUriPath()).isEqualTo(uriPath);
        assertThat(dto.getCountry()).isEqualTo(country);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testAuditTrailDtoSettersAndGetters() {
        AuditTrailDto dto = new AuditTrailDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String ip = "test-value";
        String completeUri = "test-value";
        CompanyDto company = new CompanyDto();
        CompanyProfileDto profile = new CompanyProfileDto();
        String uriPath = "test-value";
        CountryDto country = new CountryDto();

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setIp(ip);
        dto.setCompleteUri(completeUri);
        dto.setCompany(company);
        dto.setProfile(profile);
        dto.setUriPath(uriPath);
        dto.setCountry(country);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getIp()).isEqualTo(ip);
        assertThat(dto.getCompleteUri()).isEqualTo(completeUri);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getProfile()).isEqualTo(profile);
        assertThat(dto.getUriPath()).isEqualTo(uriPath);
        assertThat(dto.getCountry()).isEqualTo(country);
    }

}
