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

class TemporaryCompanyi18nDtoTest {

    /**
     * Tests the TemporaryCompanyi18nDto no-args constructor
     */
    @Test
    void testTemporaryCompanyi18nDtoNoArgsConstructor() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getCity()).isNull();
        assertThat(dto.getCoName()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getMailName()).isNull();
        assertThat(dto.getObjective()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getStreet()).isNull();
        assertThat(dto.getComments()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
        assertThat(dto.getGemiCity()).isNull();
        assertThat(dto.getArticle()).isNull();
    }

    /**
     * Tests the TemporaryCompanyi18nDto all-args constructor
     */
    @Test
    void testTemporaryCompanyi18nDtoAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        String city = "test-value";
        String coName = "test-value";
        TemporaryCompanyDto company = new TemporaryCompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LanguagesDto language = new LanguagesDto();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String mailName = "test-value";
        String objective = "test-value";
        BigInteger recdeleted = new BigInteger("1");
        String street = "test-value";
        String comments = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);
        String gemiCity = "test-value";
        String article = "test-value";

        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto(id, version, city, coName, company, dateCreated, language, lastUpdated, mailName, objective, recdeleted, street, comments, gemiId, gemiDateCreated, gemiLastUpdated, gemiCity, article);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getMailName()).isEqualTo(mailName);
        assertThat(dto.getObjective()).isEqualTo(objective);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getComments()).isEqualTo(comments);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(dto.getGemiCity()).isEqualTo(gemiCity);
        assertThat(dto.getArticle()).isEqualTo(article);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testTemporaryCompanyi18nDtoSettersAndGetters() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        String city = "test-value";
        String coName = "test-value";
        TemporaryCompanyDto company = new TemporaryCompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LanguagesDto language = new LanguagesDto();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String mailName = "test-value";
        String objective = "test-value";
        BigInteger recdeleted = new BigInteger("1");
        String street = "test-value";
        String comments = "test-value";
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);
        String gemiCity = "test-value";
        String article = "test-value";

        dto.setId(id);
        dto.setVersion(version);
        dto.setCity(city);
        dto.setCoName(coName);
        dto.setCompany(company);
        dto.setDateCreated(dateCreated);
        dto.setLanguage(language);
        dto.setLastUpdated(lastUpdated);
        dto.setMailName(mailName);
        dto.setObjective(objective);
        dto.setRecdeleted(recdeleted);
        dto.setStreet(street);
        dto.setComments(comments);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);
        dto.setGemiCity(gemiCity);
        dto.setArticle(article);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getMailName()).isEqualTo(mailName);
        assertThat(dto.getObjective()).isEqualTo(objective);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getComments()).isEqualTo(comments);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(dto.getGemiCity()).isEqualTo(gemiCity);
        assertThat(dto.getArticle()).isEqualTo(article);
    }

}
