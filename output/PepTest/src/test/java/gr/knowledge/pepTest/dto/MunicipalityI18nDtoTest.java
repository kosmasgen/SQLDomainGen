package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
import java.time.LocalDateTime;

class MunicipalityI18nDtoTest {

    /**
     * Tests the MunicipalityI18nDto no-args constructor
     */
    @Test
    void testMunicipalityI18nDtoNoArgsConstructor() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getMunicipality()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
    }

    /**
     * Tests the MunicipalityI18nDto all-args constructor
     */
    @Test
    void testMunicipalityI18nDtoAllArgsConstructor() {
        MunicipalityI18nKey id = new MunicipalityI18nKey();
        MunicipalityDto municipality = new MunicipalityDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        MunicipalityI18nDto dto = new MunicipalityI18nDto(id, municipality, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getMunicipality()).isEqualTo(municipality);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testMunicipalityI18nDtoSettersAndGetters() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        MunicipalityDto municipality = new MunicipalityDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        dto.setId(id);
        dto.setMunicipality(municipality);
        dto.setLanguage(language);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setChamberI18nId(chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getMunicipality()).isEqualTo(municipality);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
