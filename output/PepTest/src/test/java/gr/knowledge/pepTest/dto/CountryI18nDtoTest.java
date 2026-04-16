package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CountryI18nKey;
import java.time.LocalDateTime;

class CountryI18nDtoTest {

    /**
     * Tests the CountryI18nDto no-args constructor
     */
    @Test
    void testCountryI18nDtoNoArgsConstructor() {
        CountryI18nDto dto = new CountryI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCountry()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getChamberCountryI18nId()).isNull();
    }

    /**
     * Tests the CountryI18nDto all-args constructor
     */
    @Test
    void testCountryI18nDtoAllArgsConstructor() {
        CountryI18nKey id = new CountryI18nKey();
        CountryDto country = new CountryDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberCountryI18nId = 1;

        CountryI18nDto dto = new CountryI18nDto(id, country, language, description, dateCreated, lastUpdated, recdeleted, chamberCountryI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCountry()).isEqualTo(country);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberCountryI18nId()).isEqualTo(chamberCountryI18nId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCountryI18nDtoSettersAndGetters() {
        CountryI18nDto dto = new CountryI18nDto();

        CountryI18nKey id = new CountryI18nKey();
        CountryDto country = new CountryDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberCountryI18nId = 1;

        dto.setId(id);
        dto.setCountry(country);
        dto.setLanguage(language);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setChamberCountryI18nId(chamberCountryI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCountry()).isEqualTo(country);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberCountryI18nId()).isEqualTo(chamberCountryI18nId);
    }

}
