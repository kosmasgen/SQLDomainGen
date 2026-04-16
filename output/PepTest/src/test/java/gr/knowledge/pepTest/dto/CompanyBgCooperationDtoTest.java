package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyBgCooperationDtoTest {

    /**
     * Tests the CompanyBgCooperationDto no-args constructor
     */
    @Test
    void testCompanyBgCooperationDtoNoArgsConstructor() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getCoopCompany()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getCooperationStatus()).isNull();
    }

    /**
     * Tests the CompanyBgCooperationDto all-args constructor
     */
    @Test
    void testCompanyBgCooperationDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        CompanyDto coopCompany = new CompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cooperationStatus = "test-value";

        CompanyBgCooperationDto dto = new CompanyBgCooperationDto(id, chamberId, company, coopCompany, dateCreated, lastUpdated, recdeleted, cooperationStatus);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getCoopCompany()).isEqualTo(coopCompany);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCooperationStatus()).isEqualTo(cooperationStatus);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyBgCooperationDtoSettersAndGetters() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        CompanyDto coopCompany = new CompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cooperationStatus = "test-value";

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCompany(company);
        dto.setCoopCompany(coopCompany);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setCooperationStatus(cooperationStatus);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getCoopCompany()).isEqualTo(coopCompany);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCooperationStatus()).isEqualTo(cooperationStatus);
    }

}
