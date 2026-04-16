package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CompanyYpArticleI18nEntityTest {

    /**
     * Tests the CompanyYpArticleI18n no-args constructor.
     */
    @Test
    void testCompanyYpArticleI18nNoArgsConstructor() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyYpArticleI18n all-args constructor.
     */
    @Test
    void testCompanyYpArticleI18nAllArgsConstructor() {
        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        CompanyYpArticle companyArticle = new CompanyYpArticle();
        String title = "test-value";
        String html = "test-value";
        Languages language = new Languages();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyYpArticleI18n entity = new CompanyYpArticleI18n(id, companyArticle, title, html, language, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyArticle()).isEqualTo(companyArticle);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getHtml()).isEqualTo(html);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyYpArticleI18nSettersAndGetters() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        CompanyYpArticle companyArticle = new CompanyYpArticle();
        String title = "test-value";
        String html = "test-value";
        Languages language = new Languages();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setCompanyArticle(companyArticle);
        entity.setTitle(title);
        entity.setHtml(html);
        entity.setLanguage(language);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyArticle()).isEqualTo(companyArticle);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getHtml()).isEqualTo(html);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
