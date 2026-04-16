package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ExportCompanyDtoTest {

    /**
     * Tests the ExportCompanyDto no-args constructor
     */
    @Test
    void testExportCompanyDtoNoArgsConstructor() {
        ExportCompanyDto dto = new ExportCompanyDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getActive()).isNull();
        assertThat(dto.getEmeCode()).isNull();
    }

    /**
     * Tests the ExportCompanyDto all-args constructor
     */
    @Test
    void testExportCompanyDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        Boolean active = Boolean.TRUE;
        Long emeCode = 1L;

        ExportCompanyDto dto = new ExportCompanyDto(id, dateCreated, lastUpdated, company, active, emeCode);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getActive()).isEqualTo(active);
        assertThat(dto.getEmeCode()).isEqualTo(emeCode);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testExportCompanyDtoSettersAndGetters() {
        ExportCompanyDto dto = new ExportCompanyDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        Boolean active = Boolean.TRUE;
        Long emeCode = 1L;

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setCompany(company);
        dto.setActive(active);
        dto.setEmeCode(emeCode);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getActive()).isEqualTo(active);
        assertThat(dto.getEmeCode()).isEqualTo(emeCode);
    }

}
