package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyTitleEntityTest {

    /**
     * Tests the TemporaryCompanyTitle no-args constructor.
     */
    @Test
    void testTemporaryCompanyTitleNoArgsConstructor() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the TemporaryCompanyTitle all-args constructor.
     */
    @Test
    void testTemporaryCompanyTitleAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        BigInteger companyPreregistrationId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger orderSeq = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        String titleLatin = "test-value";
        String titleNrm = "test-value";
        BigInteger titleStatusId = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyTitle entity = new TemporaryCompanyTitle(id, version, companyId, companyPreregistrationId, dateCreated, fromDate, lastUpdated, orderSeq, recdeleted, title, titleLatin, titleNrm, titleStatusId, toDate, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getCompanyPreregistrationId()).isEqualTo(companyPreregistrationId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getTitleLatin()).isEqualTo(titleLatin);
        assertThat(entity.getTitleNrm()).isEqualTo(titleNrm);
        assertThat(entity.getTitleStatusId()).isEqualTo(titleStatusId);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testTemporaryCompanyTitleSettersAndGetters() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        BigInteger companyPreregistrationId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger orderSeq = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        String titleLatin = "test-value";
        String titleNrm = "test-value";
        BigInteger titleStatusId = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        entity.setId(id);
        entity.setVersion(version);
        entity.setCompanyId(companyId);
        entity.setCompanyPreregistrationId(companyPreregistrationId);
        entity.setDateCreated(dateCreated);
        entity.setFromDate(fromDate);
        entity.setLastUpdated(lastUpdated);
        entity.setOrderSeq(orderSeq);
        entity.setRecdeleted(recdeleted);
        entity.setTitle(title);
        entity.setTitleLatin(titleLatin);
        entity.setTitleNrm(titleNrm);
        entity.setTitleStatusId(titleStatusId);
        entity.setToDate(toDate);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getCompanyPreregistrationId()).isEqualTo(companyPreregistrationId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getTitleLatin()).isEqualTo(titleLatin);
        assertThat(entity.getTitleNrm()).isEqualTo(titleNrm);
        assertThat(entity.getTitleStatusId()).isEqualTo(titleStatusId);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
