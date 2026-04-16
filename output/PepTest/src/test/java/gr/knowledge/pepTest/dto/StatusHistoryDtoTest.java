package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;

class StatusHistoryDtoTest {

    /**
     * Tests the StatusHistoryDto no-args constructor
     */
    @Test
    void testStatusHistoryDtoNoArgsConstructor() {
        StatusHistoryDto dto = new StatusHistoryDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberStatusHistoryId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getCompanyStatus()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getNotes()).isNull();
        assertThat(dto.getRegDt()).isNull();
        assertThat(dto.getStartDt()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
        assertThat(dto.getActionNo()).isNull();
    }

    /**
     * Tests the StatusHistoryDto all-args constructor
     */
    @Test
    void testStatusHistoryDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberStatusHistoryId = new BigInteger("1");
        CompanyDto company = new CompanyDto();
        CompanyStatusDto companyStatus = new CompanyStatusDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String notes = "test-value";
        LocalDateTime regDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime startDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String actionNo = "test-value";

        StatusHistoryDto dto = new StatusHistoryDto(id, chamberId, chamberStatusHistoryId, company, companyStatus, dateCreated, lastUpdated, notes, regDt, startDt, recdeleted, gemiId, gemiDateCreated, gemiLastUpdated, actionNo);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberStatusHistoryId()).isEqualTo(chamberStatusHistoryId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getNotes()).isEqualTo(notes);
        assertThat(dto.getRegDt()).isEqualTo(regDt);
        assertThat(dto.getStartDt()).isEqualTo(startDt);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(dto.getActionNo()).isEqualTo(actionNo);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testStatusHistoryDtoSettersAndGetters() {
        StatusHistoryDto dto = new StatusHistoryDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberStatusHistoryId = new BigInteger("1");
        CompanyDto company = new CompanyDto();
        CompanyStatusDto companyStatus = new CompanyStatusDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String notes = "test-value";
        LocalDateTime regDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime startDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String actionNo = "test-value";

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberStatusHistoryId(chamberStatusHistoryId);
        dto.setCompany(company);
        dto.setCompanyStatus(companyStatus);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setNotes(notes);
        dto.setRegDt(regDt);
        dto.setStartDt(startDt);
        dto.setRecdeleted(recdeleted);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);
        dto.setActionNo(actionNo);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberStatusHistoryId()).isEqualTo(chamberStatusHistoryId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getNotes()).isEqualTo(notes);
        assertThat(dto.getRegDt()).isEqualTo(regDt);
        assertThat(dto.getStartDt()).isEqualTo(startDt);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(dto.getActionNo()).isEqualTo(actionNo);
    }

}
