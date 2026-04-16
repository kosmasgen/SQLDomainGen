package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CompanyStatusi18nEntityTest {

    /**
     * Tests the CompanyStatusi18n no-args constructor.
     */
    @Test
    void testCompanyStatusi18nNoArgsConstructor() {
        CompanyStatusi18n entity = new CompanyStatusi18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyStatusi18n all-args constructor.
     */
    @Test
    void testCompanyStatusi18nAllArgsConstructor() {
        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        CompanyStatus companyStatus = new CompanyStatus();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        CompanyStatusi18n entity = new CompanyStatusi18n(id, companyStatus, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyStatusi18nSettersAndGetters() {
        CompanyStatusi18n entity = new CompanyStatusi18n();

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        CompanyStatus companyStatus = new CompanyStatus();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        entity.setId(id);
        entity.setCompanyStatus(companyStatus);
        entity.setLanguage(language);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberI18nId(chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
