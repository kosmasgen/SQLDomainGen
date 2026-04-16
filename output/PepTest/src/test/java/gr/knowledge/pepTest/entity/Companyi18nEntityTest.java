package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class Companyi18nEntityTest {

    /**
     * Tests the Companyi18n no-args constructor.
     */
    @Test
    void testCompanyi18nNoArgsConstructor() {
        Companyi18n entity = new Companyi18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Companyi18n all-args constructor.
     */
    @Test
    void testCompanyi18nAllArgsConstructor() {
        Companyi18nKey id = new Companyi18nKey();
        Company company = new Company();
        Languages language = new Languages();
        String city = "test-value";
        String coName = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String objective = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String street = "test-value";
        String responsibleName = "test-value";

        Companyi18n entity = new Companyi18n(id, company, language, city, coName, dateCreated, lastUpdated, objective, recdeleted, street, responsibleName);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getObjective()).isEqualTo(objective);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getResponsibleName()).isEqualTo(responsibleName);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyi18nSettersAndGetters() {
        Companyi18n entity = new Companyi18n();

        Companyi18nKey id = new Companyi18nKey();
        Company company = new Company();
        Languages language = new Languages();
        String city = "test-value";
        String coName = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String objective = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String street = "test-value";
        String responsibleName = "test-value";

        entity.setId(id);
        entity.setCompany(company);
        entity.setLanguage(language);
        entity.setCity(city);
        entity.setCoName(coName);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setObjective(objective);
        entity.setRecdeleted(recdeleted);
        entity.setStreet(street);
        entity.setResponsibleName(responsibleName);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getObjective()).isEqualTo(objective);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getResponsibleName()).isEqualTo(responsibleName);
    }

}
