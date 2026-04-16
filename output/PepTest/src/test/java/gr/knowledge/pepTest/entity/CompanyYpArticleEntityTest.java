package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpArticleEntityTest {

    /**
     * Tests the CompanyYpArticle no-args constructor.
     */
    @Test
    void testCompanyYpArticleNoArgsConstructor() {
        CompanyYpArticle entity = new CompanyYpArticle();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyYpArticle all-args constructor.
     */
    @Test
    void testCompanyYpArticleAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String title = "test-value";
        String html = "test-value";
        Languages language = new Languages();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Boolean isPublished = Boolean.TRUE;
        CompanyProfile companyProfile = new CompanyProfile();

        CompanyYpArticle entity = new CompanyYpArticle(id, chamberId, company, title, html, language, orderSeq, dateCreated, lastUpdated, recdeleted, isPublished, companyProfile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getHtml()).isEqualTo(html);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getIsPublished()).isEqualTo(isPublished);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyYpArticleSettersAndGetters() {
        CompanyYpArticle entity = new CompanyYpArticle();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String title = "test-value";
        String html = "test-value";
        Languages language = new Languages();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Boolean isPublished = Boolean.TRUE;
        CompanyProfile companyProfile = new CompanyProfile();

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCompany(company);
        entity.setTitle(title);
        entity.setHtml(html);
        entity.setLanguage(language);
        entity.setOrderSeq(orderSeq);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setIsPublished(isPublished);
        entity.setCompanyProfile(companyProfile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getHtml()).isEqualTo(html);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getIsPublished()).isEqualTo(isPublished);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
    }

}
