package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CompanyTitlei18nEntityTest {

    /**
     * Tests the CompanyTitlei18n no-args constructor.
     */
    @Test
    void testCompanyTitlei18nNoArgsConstructor() {
        CompanyTitlei18n entity = new CompanyTitlei18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyTitlei18n all-args constructor.
     */
    @Test
    void testCompanyTitlei18nAllArgsConstructor() {
        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        CompanyTitle companyTitle = new CompanyTitle();
        Languages language = new Languages();
        String title = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyTitlei18n entity = new CompanyTitlei18n(id, companyTitle, language, title, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyTitlei18nSettersAndGetters() {
        CompanyTitlei18n entity = new CompanyTitlei18n();

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        CompanyTitle companyTitle = new CompanyTitle();
        Languages language = new Languages();
        String title = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setCompanyTitle(companyTitle);
        entity.setLanguage(language);
        entity.setTitle(title);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
