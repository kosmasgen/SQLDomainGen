package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyi18nEntityTest {

    /**
     * Tests the TemporaryCompanyi18n no-args constructor.
     */
    @Test
    void testTemporaryCompanyi18nNoArgsConstructor() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the TemporaryCompanyi18n all-args constructor.
     */
    @Test
    void testTemporaryCompanyi18nAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        String city = "test-value";
        String coName = "test-value";
        TemporaryCompany company = new TemporaryCompany();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Languages language = new Languages();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
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

        TemporaryCompanyi18n entity = new TemporaryCompanyi18n(id, version, city, coName, company, dateCreated, language, lastUpdated, mailName, objective, recdeleted, street, comments, gemiId, gemiDateCreated, gemiLastUpdated, gemiCity, article);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getMailName()).isEqualTo(mailName);
        assertThat(entity.getObjective()).isEqualTo(objective);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getComments()).isEqualTo(comments);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(entity.getGemiCity()).isEqualTo(gemiCity);
        assertThat(entity.getArticle()).isEqualTo(article);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testTemporaryCompanyi18nSettersAndGetters() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        String city = "test-value";
        String coName = "test-value";
        TemporaryCompany company = new TemporaryCompany();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Languages language = new Languages();
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
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

        entity.setId(id);
        entity.setVersion(version);
        entity.setCity(city);
        entity.setCoName(coName);
        entity.setCompany(company);
        entity.setDateCreated(dateCreated);
        entity.setLanguage(language);
        entity.setLastUpdated(lastUpdated);
        entity.setMailName(mailName);
        entity.setObjective(objective);
        entity.setRecdeleted(recdeleted);
        entity.setStreet(street);
        entity.setComments(comments);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);
        entity.setGemiCity(gemiCity);
        entity.setArticle(article);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getMailName()).isEqualTo(mailName);
        assertThat(entity.getObjective()).isEqualTo(objective);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getComments()).isEqualTo(comments);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
        assertThat(entity.getGemiCity()).isEqualTo(gemiCity);
        assertThat(entity.getArticle()).isEqualTo(article);
    }

}
