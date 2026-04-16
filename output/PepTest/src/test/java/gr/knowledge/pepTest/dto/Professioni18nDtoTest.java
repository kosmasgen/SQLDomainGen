package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.Professioni18nKey;
import java.time.LocalDateTime;

class Professioni18nDtoTest {

    /**
     * Tests the Professioni18nDto no-args constructor
     */
    @Test
    void testProfessioni18nDtoNoArgsConstructor() {
        Professioni18nDto dto = new Professioni18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getProfession()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
    }

    /**
     * Tests the Professioni18nDto all-args constructor
     */
    @Test
    void testProfessioni18nDtoAllArgsConstructor() {
        Professioni18nKey id = new Professioni18nKey();
        ProfessionDto profession = new ProfessionDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberI18nId = 1;

        Professioni18nDto dto = new Professioni18nDto(id, profession, language, recdeleted, description, dateCreated, lastUpdated, chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getProfession()).isEqualTo(profession);
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
    void testProfessioni18nDtoSettersAndGetters() {
        Professioni18nDto dto = new Professioni18nDto();

        Professioni18nKey id = new Professioni18nKey();
        ProfessionDto profession = new ProfessionDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recdeleted = Boolean.TRUE;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberI18nId = 1;

        dto.setId(id);
        dto.setProfession(profession);
        dto.setLanguage(language);
        dto.setRecdeleted(recdeleted);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setChamberI18nId(chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getProfession()).isEqualTo(profession);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
