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

class TemporaryCompanyTitlei18nDtoTest {

    /**
     * Tests the TemporaryCompanyTitlei18nDto no-args constructor
     */
    @Test
    void testTemporaryCompanyTitlei18nDtoNoArgsConstructor() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getCompanyTitle()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
    }

    /**
     * Tests the TemporaryCompanyTitlei18nDto all-args constructor
     */
    @Test
    void testTemporaryCompanyTitlei18nDtoAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        TemporaryCompanyTitleDto companyTitle = new TemporaryCompanyTitleDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LanguagesDto language = new LanguagesDto();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto(id, version, companyTitle, dateCreated, language, lastUpdated, recdeleted, title, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testTemporaryCompanyTitlei18nDtoSettersAndGetters() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        TemporaryCompanyTitleDto companyTitle = new TemporaryCompanyTitleDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LanguagesDto language = new LanguagesDto();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");
        String title = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        dto.setId(id);
        dto.setVersion(version);
        dto.setCompanyTitle(companyTitle);
        dto.setDateCreated(dateCreated);
        dto.setLanguage(language);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setTitle(title);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyTitle()).isEqualTo(companyTitle);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
