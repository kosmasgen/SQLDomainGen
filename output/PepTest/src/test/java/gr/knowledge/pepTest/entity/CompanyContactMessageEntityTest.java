package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyContactMessageEntityTest {

    /**
     * Tests the CompanyContactMessage no-args constructor.
     */
    @Test
    void testCompanyContactMessageNoArgsConstructor() {
        CompanyContactMessage entity = new CompanyContactMessage();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyContactMessage all-args constructor.
     */
    @Test
    void testCompanyContactMessageAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String fullName = "test-value";
        String senderEmail = "test-value";
        String subject = "test-value";
        String message = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessage entity = new CompanyContactMessage(id, fullName, senderEmail, subject, message, dateCreated, companyId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getFullName()).isEqualTo(fullName);
        assertThat(entity.getSenderEmail()).isEqualTo(senderEmail);
        assertThat(entity.getSubject()).isEqualTo(subject);
        assertThat(entity.getMessage()).isEqualTo(message);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyContactMessageSettersAndGetters() {
        CompanyContactMessage entity = new CompanyContactMessage();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String fullName = "test-value";
        String senderEmail = "test-value";
        String subject = "test-value";
        String message = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        entity.setId(id);
        entity.setFullName(fullName);
        entity.setSenderEmail(senderEmail);
        entity.setSubject(subject);
        entity.setMessage(message);
        entity.setDateCreated(dateCreated);
        entity.setCompanyId(companyId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getFullName()).isEqualTo(fullName);
        assertThat(entity.getSenderEmail()).isEqualTo(senderEmail);
        assertThat(entity.getSubject()).isEqualTo(subject);
        assertThat(entity.getMessage()).isEqualTo(message);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
    }

}
