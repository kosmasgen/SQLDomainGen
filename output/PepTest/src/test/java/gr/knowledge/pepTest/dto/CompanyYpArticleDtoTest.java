package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpArticleDtoTest {

    /**
     * Tests the CompanyYpArticleDto no-args constructor
     */
    @Test
    void testCompanyYpArticleDtoNoArgsConstructor() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getHtml()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getIsPublished()).isNull();
        assertThat(dto.getCompanyProfile()).isNull();
    }

    /**
     * Tests the CompanyYpArticleDto all-args constructor
     */
    @Test
    void testCompanyYpArticleDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String title = "test-value";
        String html = "test-value";
        LanguagesDto language = new LanguagesDto();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Boolean isPublished = Boolean.TRUE;
        CompanyProfileDto companyProfile = new CompanyProfileDto();

        CompanyYpArticleDto dto = new CompanyYpArticleDto(id, chamberId, company, title, html, language, orderSeq, dateCreated, lastUpdated, recdeleted, isPublished, companyProfile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getHtml()).isEqualTo(html);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getIsPublished()).isEqualTo(isPublished);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyYpArticleDtoSettersAndGetters() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String title = "test-value";
        String html = "test-value";
        LanguagesDto language = new LanguagesDto();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Boolean isPublished = Boolean.TRUE;
        CompanyProfileDto companyProfile = new CompanyProfileDto();

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCompany(company);
        dto.setTitle(title);
        dto.setHtml(html);
        dto.setLanguage(language);
        dto.setOrderSeq(orderSeq);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setIsPublished(isPublished);
        dto.setCompanyProfile(companyProfile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getHtml()).isEqualTo(html);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getIsPublished()).isEqualTo(isPublished);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
    }

}
