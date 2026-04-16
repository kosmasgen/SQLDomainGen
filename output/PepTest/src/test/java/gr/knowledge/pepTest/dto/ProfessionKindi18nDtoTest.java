package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
import java.time.LocalDateTime;

class ProfessionKindi18nDtoTest {

    /**
     * Tests the ProfessionKindi18nDto no-args constructor
     */
    @Test
    void testProfessionKindi18nDtoNoArgsConstructor() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getProfessionKind()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
    }

    /**
     * Tests the ProfessionKindi18nDto all-args constructor
     */
    @Test
    void testProfessionKindi18nDtoAllArgsConstructor() {
        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        ProfessionKindDto professionKind = new ProfessionKindDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberI18nId = 1;

        ProfessionKindi18nDto dto = new ProfessionKindi18nDto(id, professionKind, language, recdeleted, description, dateCreated, lastUpdated, chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getProfessionKind()).isEqualTo(professionKind);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testProfessionKindi18nDtoSettersAndGetters() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        ProfessionKindDto professionKind = new ProfessionKindDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberI18nId = 1;

        dto.setId(id);
        dto.setProfessionKind(professionKind);
        dto.setLanguage(language);
        dto.setRecdeleted(recdeleted);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setChamberI18nId(chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getProfessionKind()).isEqualTo(professionKind);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
