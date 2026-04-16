package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class BgPoiI18nEntityTest {

    /**
     * Tests the BgPoiI18n no-args constructor.
     */
    @Test
    void testBgPoiI18nNoArgsConstructor() {
        BgPoiI18n entity = new BgPoiI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the BgPoiI18n all-args constructor.
     */
    @Test
    void testBgPoiI18nAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String title = "test-value";
        BgPoi poi = new BgPoi();
        Languages language = new Languages();

        BgPoiI18n entity = new BgPoiI18n(id, dateCreated, lastUpdated, recdeleted, title, poi, language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getPoi()).isEqualTo(poi);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testBgPoiI18nSettersAndGetters() {
        BgPoiI18n entity = new BgPoiI18n();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String title = "test-value";
        BgPoi poi = new BgPoi();
        Languages language = new Languages();

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setTitle(title);
        entity.setPoi(poi);
        entity.setLanguage(language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getPoi()).isEqualTo(poi);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

}
