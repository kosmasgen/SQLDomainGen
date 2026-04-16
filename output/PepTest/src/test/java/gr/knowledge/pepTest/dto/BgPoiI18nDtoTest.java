package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class BgPoiI18nDtoTest {

    /**
     * Tests the BgPoiI18nDto no-args constructor
     */
    @Test
    void testBgPoiI18nDtoNoArgsConstructor() {
        BgPoiI18nDto dto = new BgPoiI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getPoi()).isNull();
        assertThat(dto.getLanguage()).isNull();
    }

    /**
     * Tests the BgPoiI18nDto all-args constructor
     */
    @Test
    void testBgPoiI18nDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String title = "test-value";
        BgPoiDto poi = new BgPoiDto();
        LanguagesDto language = new LanguagesDto();

        BgPoiI18nDto dto = new BgPoiI18nDto(id, dateCreated, lastUpdated, recdeleted, title, poi, language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getPoi()).isEqualTo(poi);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testBgPoiI18nDtoSettersAndGetters() {
        BgPoiI18nDto dto = new BgPoiI18nDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String title = "test-value";
        BgPoiDto poi = new BgPoiDto();
        LanguagesDto language = new LanguagesDto();

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setTitle(title);
        dto.setPoi(poi);
        dto.setLanguage(language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getPoi()).isEqualTo(poi);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

}
