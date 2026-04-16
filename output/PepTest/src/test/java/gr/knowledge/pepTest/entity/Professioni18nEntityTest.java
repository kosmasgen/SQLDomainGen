package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class Professioni18nEntityTest {

    /**
     * Tests the Professioni18n no-args constructor.
     */
    @Test
    void testProfessioni18nNoArgsConstructor() {
        Professioni18n entity = new Professioni18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Professioni18n all-args constructor.
     */
    @Test
    void testProfessioni18nAllArgsConstructor() {
        Professioni18nKey id = new Professioni18nKey();
        Profession profession = new Profession();
        Languages language = new Languages();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberI18nId = 1;

        Professioni18n entity = new Professioni18n(id, profession, language, recdeleted, description, dateCreated, lastUpdated, chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getProfession()).isEqualTo(profession);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProfessioni18nSettersAndGetters() {
        Professioni18n entity = new Professioni18n();

        Professioni18nKey id = new Professioni18nKey();
        Profession profession = new Profession();
        Languages language = new Languages();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberI18nId = 1;

        entity.setId(id);
        entity.setProfession(profession);
        entity.setLanguage(language);
        entity.setRecdeleted(recdeleted);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setChamberI18nId(chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getProfession()).isEqualTo(profession);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
