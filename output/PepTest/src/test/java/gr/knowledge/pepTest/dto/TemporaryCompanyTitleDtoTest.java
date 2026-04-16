package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyTitleDtoTest {

    /**
     * Tests the TemporaryCompanyTitleDto no-args constructor
     */
    @Test
    void testTemporaryCompanyTitleDtoNoArgsConstructor() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getCompanyId()).isNull();
        assertThat(dto.getCompanyPreregistrationId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getFromDate()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getTitleLatin()).isNull();
        assertThat(dto.getTitleNrm()).isNull();
        assertThat(dto.getTitleStatusId()).isNull();
        assertThat(dto.getToDate()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
    }

    /**
     * Tests the TemporaryCompanyTitleDto all-args constructor
     */
    @Test
    void testTemporaryCompanyTitleDtoAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        BigInteger companyPreregistrationId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger orderSeq = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        String titleLatin = "test-value";
        String titleNrm = "test-value";
        BigInteger titleStatusId = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto(id, version, companyId, companyPreregistrationId, dateCreated, fromDate, lastUpdated, orderSeq, recdeleted, title, titleLatin, titleNrm, titleStatusId, toDate, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getCompanyPreregistrationId()).isEqualTo(companyPreregistrationId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getTitleLatin()).isEqualTo(titleLatin);
        assertThat(dto.getTitleNrm()).isEqualTo(titleNrm);
        assertThat(dto.getTitleStatusId()).isEqualTo(titleStatusId);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testTemporaryCompanyTitleDtoSettersAndGetters() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        BigInteger companyPreregistrationId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger orderSeq = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        String titleLatin = "test-value";
        String titleNrm = "test-value";
        BigInteger titleStatusId = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        dto.setId(id);
        dto.setVersion(version);
        dto.setCompanyId(companyId);
        dto.setCompanyPreregistrationId(companyPreregistrationId);
        dto.setDateCreated(dateCreated);
        dto.setFromDate(fromDate);
        dto.setLastUpdated(lastUpdated);
        dto.setOrderSeq(orderSeq);
        dto.setRecdeleted(recdeleted);
        dto.setTitle(title);
        dto.setTitleLatin(titleLatin);
        dto.setTitleNrm(titleNrm);
        dto.setTitleStatusId(titleStatusId);
        dto.setToDate(toDate);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getCompanyPreregistrationId()).isEqualTo(companyPreregistrationId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getTitleLatin()).isEqualTo(titleLatin);
        assertThat(dto.getTitleNrm()).isEqualTo(titleNrm);
        assertThat(dto.getTitleStatusId()).isEqualTo(titleStatusId);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
