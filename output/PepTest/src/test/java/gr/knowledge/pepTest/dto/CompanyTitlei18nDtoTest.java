package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CompanyTitlei18nKey;
import java.time.LocalDateTime;

class CompanyTitlei18nDtoTest {

    /**
     * Tests the CompanyTitlei18nDto no-args constructor
     */
    @Test
    void testCompanyTitlei18nDtoNoArgsConstructor() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompanyTitle()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the CompanyTitlei18nDto all-args constructor
     */
    @Test
    void testCompanyTitlei18nDtoAllArgsConstructor() {
        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        CompanyTitleDto companyTitle = new CompanyTitleDto();
        LanguagesDto language = new LanguagesDto();
        String title = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyTitlei18nDto dto = new CompanyTitlei18nDto(id, companyTitle, language, title, dateCreated, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyTitlei18nDtoSettersAndGetters() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        CompanyTitleDto companyTitle = new CompanyTitleDto();
        LanguagesDto language = new LanguagesDto();
        String title = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setCompanyTitle(companyTitle);
        dto.setLanguage(language);
        dto.setTitle(title);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
