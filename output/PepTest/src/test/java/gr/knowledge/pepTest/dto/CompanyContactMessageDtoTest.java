package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyContactMessageDtoTest {

    /**
     * Tests the CompanyContactMessageDto no-args constructor
     */
    @Test
    void testCompanyContactMessageDtoNoArgsConstructor() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getFullName()).isNull();
        assertThat(dto.getSenderEmail()).isNull();
        assertThat(dto.getSubject()).isNull();
        assertThat(dto.getMessage()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getCompanyId()).isNull();
    }

    /**
     * Tests the CompanyContactMessageDto all-args constructor
     */
    @Test
    void testCompanyContactMessageDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String fullName = "test-value";
        String senderEmail = "test-value";
        String subject = "test-value";
        String message = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessageDto dto = new CompanyContactMessageDto(id, fullName, senderEmail, subject, message, dateCreated, companyId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getFullName()).isEqualTo(fullName);
        assertThat(dto.getSenderEmail()).isEqualTo(senderEmail);
        assertThat(dto.getSubject()).isEqualTo(subject);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyContactMessageDtoSettersAndGetters() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String fullName = "test-value";
        String senderEmail = "test-value";
        String subject = "test-value";
        String message = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        dto.setId(id);
        dto.setFullName(fullName);
        dto.setSenderEmail(senderEmail);
        dto.setSubject(subject);
        dto.setMessage(message);
        dto.setDateCreated(dateCreated);
        dto.setCompanyId(companyId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getFullName()).isEqualTo(fullName);
        assertThat(dto.getSenderEmail()).isEqualTo(senderEmail);
        assertThat(dto.getSubject()).isEqualTo(subject);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
    }

}
