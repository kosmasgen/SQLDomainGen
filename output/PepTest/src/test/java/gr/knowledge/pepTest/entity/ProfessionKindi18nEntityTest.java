package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class ProfessionKindi18nEntityTest {

    /**
     * Tests the ProfessionKindi18n no-args constructor.
     */
    @Test
    void testProfessionKindi18nNoArgsConstructor() {
        ProfessionKindi18n entity = new ProfessionKindi18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ProfessionKindi18n all-args constructor.
     */
    @Test
    void testProfessionKindi18nAllArgsConstructor() {
        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        ProfessionKind professionKind = new ProfessionKind();
        Languages language = new Languages();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberI18nId = 1;

        ProfessionKindi18n entity = new ProfessionKindi18n(id, professionKind, language, recdeleted, description, dateCreated, lastUpdated, chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getProfessionKind()).isEqualTo(professionKind);
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
    void testProfessionKindi18nSettersAndGetters() {
        ProfessionKindi18n entity = new ProfessionKindi18n();

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        ProfessionKind professionKind = new ProfessionKind();
        Languages language = new Languages();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberI18nId = 1;

        entity.setId(id);
        entity.setProfessionKind(professionKind);
        entity.setLanguage(language);
        entity.setRecdeleted(recdeleted);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setChamberI18nId(chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getProfessionKind()).isEqualTo(professionKind);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
