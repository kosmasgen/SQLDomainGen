package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CountryI18nEntityTest {

    /**
     * Tests the CountryI18n no-args constructor.
     */
    @Test
    void testCountryI18nNoArgsConstructor() {
        CountryI18n entity = new CountryI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CountryI18n all-args constructor.
     */
    @Test
    void testCountryI18nAllArgsConstructor() {
        CountryI18nKey id = new CountryI18nKey();
        Country country = new Country();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberCountryI18nId = 1;

        CountryI18n entity = new CountryI18n(id, country, language, description, dateCreated, lastUpdated, recdeleted, chamberCountryI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCountry()).isEqualTo(country);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberCountryI18nId()).isEqualTo(chamberCountryI18nId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCountryI18nSettersAndGetters() {
        CountryI18n entity = new CountryI18n();

        CountryI18nKey id = new CountryI18nKey();
        Country country = new Country();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberCountryI18nId = 1;

        entity.setId(id);
        entity.setCountry(country);
        entity.setLanguage(language);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberCountryI18nId(chamberCountryI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCountry()).isEqualTo(country);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberCountryI18nId()).isEqualTo(chamberCountryI18nId);
    }

}
