package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class MunicipalityI18nEntityTest {

    /**
     * Tests the MunicipalityI18n no-args constructor.
     */
    @Test
    void testMunicipalityI18nNoArgsConstructor() {
        MunicipalityI18n entity = new MunicipalityI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the MunicipalityI18n all-args constructor.
     */
    @Test
    void testMunicipalityI18nAllArgsConstructor() {
        MunicipalityI18nKey id = new MunicipalityI18nKey();
        Municipality municipality = new Municipality();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        MunicipalityI18n entity = new MunicipalityI18n(id, municipality, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getMunicipality()).isEqualTo(municipality);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testMunicipalityI18nSettersAndGetters() {
        MunicipalityI18n entity = new MunicipalityI18n();

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        Municipality municipality = new Municipality();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        entity.setId(id);
        entity.setMunicipality(municipality);
        entity.setLanguage(language);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberI18nId(chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getMunicipality()).isEqualTo(municipality);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
