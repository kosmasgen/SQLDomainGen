package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyTitlei18nEntityTest {

    /**
     * Tests the TemporaryCompanyTitlei18n no-args constructor.
     */
    @Test
    void testTemporaryCompanyTitlei18nNoArgsConstructor() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the TemporaryCompanyTitlei18n all-args constructor.
     */
    @Test
    void testTemporaryCompanyTitlei18nAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        TemporaryCompanyTitle companyTitle = new TemporaryCompanyTitle();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Languages language = new Languages();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n(id, version, companyTitle, dateCreated, language, lastUpdated, recdeleted, title, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testTemporaryCompanyTitlei18nSettersAndGetters() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        TemporaryCompanyTitle companyTitle = new TemporaryCompanyTitle();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Languages language = new Languages();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        entity.setId(id);
        entity.setVersion(version);
        entity.setCompanyTitle(companyTitle);
        entity.setDateCreated(dateCreated);
        entity.setLanguage(language);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setTitle(title);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
