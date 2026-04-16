package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;

class StatusHistoryEntityTest {

    /**
     * Tests the StatusHistory no-args constructor.
     */
    @Test
    void testStatusHistoryNoArgsConstructor() {
        StatusHistory entity = new StatusHistory();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the StatusHistory all-args constructor.
     */
    @Test
    void testStatusHistoryAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberStatusHistoryId = new BigInteger("1");
        Company company = new Company();
        CompanyStatus companyStatus = new CompanyStatus();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String notes = "test-value";
        LocalDateTime regDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime startDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String actionNo = "test-value";

        StatusHistory entity = new StatusHistory(id, chamberId, chamberStatusHistoryId, company, companyStatus, dateCreated, lastUpdated, notes, regDt, startDt, recdeleted, gemiId, gemiDateCreated, gemiLastUpdated, actionNo);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberStatusHistoryId()).isEqualTo(chamberStatusHistoryId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getNotes()).isEqualTo(notes);
        assertThat(entity.getRegDt()).isEqualTo(regDt);
        assertThat(entity.getStartDt()).isEqualTo(startDt);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(entity.getActionNo()).isEqualTo(actionNo);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testStatusHistorySettersAndGetters() {
        StatusHistory entity = new StatusHistory();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberStatusHistoryId = new BigInteger("1");
        Company company = new Company();
        CompanyStatus companyStatus = new CompanyStatus();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String notes = "test-value";
        LocalDateTime regDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime startDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String actionNo = "test-value";

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberStatusHistoryId(chamberStatusHistoryId);
        entity.setCompany(company);
        entity.setCompanyStatus(companyStatus);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setNotes(notes);
        entity.setRegDt(regDt);
        entity.setStartDt(startDt);
        entity.setRecdeleted(recdeleted);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);
        entity.setActionNo(actionNo);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberStatusHistoryId()).isEqualTo(chamberStatusHistoryId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getNotes()).isEqualTo(notes);
        assertThat(entity.getRegDt()).isEqualTo(regDt);
        assertThat(entity.getStartDt()).isEqualTo(startDt);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(entity.getActionNo()).isEqualTo(actionNo);
    }

}
