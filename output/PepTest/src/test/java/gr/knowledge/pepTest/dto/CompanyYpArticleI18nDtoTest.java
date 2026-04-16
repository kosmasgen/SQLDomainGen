package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nKey;
import java.time.LocalDateTime;

class CompanyYpArticleI18nDtoTest {

    /**
     * Tests the CompanyYpArticleI18nDto no-args constructor
     */
    @Test
    void testCompanyYpArticleI18nDtoNoArgsConstructor() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompanyArticle()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getHtml()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the CompanyYpArticleI18nDto all-args constructor
     */
    @Test
    void testCompanyYpArticleI18nDtoAllArgsConstructor() {
        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        CompanyYpArticleDto companyArticle = new CompanyYpArticleDto();
        String title = "test-value";
        String html = "test-value";
        LanguagesDto language = new LanguagesDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto(id, companyArticle, title, html, language, dateCreated, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyArticle()).isEqualTo(companyArticle);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getHtml()).isEqualTo(html);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyYpArticleI18nDtoSettersAndGetters() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        CompanyYpArticleDto companyArticle = new CompanyYpArticleDto();
        String title = "test-value";
        String html = "test-value";
        LanguagesDto language = new LanguagesDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setCompanyArticle(companyArticle);
        dto.setTitle(title);
        dto.setHtml(html);
        dto.setLanguage(language);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyArticle()).isEqualTo(companyArticle);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getHtml()).isEqualTo(html);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
