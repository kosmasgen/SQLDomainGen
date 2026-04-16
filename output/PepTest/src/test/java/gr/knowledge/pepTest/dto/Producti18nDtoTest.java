package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.Producti18nKey;
import java.time.LocalDateTime;

class Producti18nDtoTest {

    /**
     * Tests the Producti18nDto no-args constructor
     */
    @Test
    void testProducti18nDtoNoArgsConstructor() {
        Producti18nDto dto = new Producti18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getProduct()).isNull();
        assertThat(dto.getShortDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the Producti18nDto all-args constructor
     */
    @Test
    void testProducti18nDtoAllArgsConstructor() {
        Producti18nKey id = new Producti18nKey();
        Integer version = 1;
        String description = "test-value";
        Long chamberI18nId = 1L;
        LanguagesDto language = new LanguagesDto();
        ProductDto product = new ProductDto();
        String shortDescription = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        Producti18nDto dto = new Producti18nDto(id, version, description, chamberI18nId, language, product, shortDescription, dateCreated, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getProduct()).isEqualTo(product);
        assertThat(dto.getShortDescription()).isEqualTo(shortDescription);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testProducti18nDtoSettersAndGetters() {
        Producti18nDto dto = new Producti18nDto();

        Producti18nKey id = new Producti18nKey();
        Integer version = 1;
        String description = "test-value";
        Long chamberI18nId = 1L;
        LanguagesDto language = new LanguagesDto();
        ProductDto product = new ProductDto();
        String shortDescription = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setVersion(version);
        dto.setDescription(description);
        dto.setChamberI18nId(chamberI18nId);
        dto.setLanguage(language);
        dto.setProduct(product);
        dto.setShortDescription(shortDescription);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getProduct()).isEqualTo(product);
        assertThat(dto.getShortDescription()).isEqualTo(shortDescription);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
