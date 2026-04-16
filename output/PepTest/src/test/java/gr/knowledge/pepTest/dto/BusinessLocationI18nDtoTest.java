package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.BusinessLocationI18nKey;
import java.time.LocalDateTime;

class BusinessLocationI18nDtoTest {

    /**
     * Tests the BusinessLocationI18nDto no-args constructor
     */
    @Test
    void testBusinessLocationI18nDtoNoArgsConstructor() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getCode()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getBusinessLocation()).isNull();
        assertThat(dto.getLanguage()).isNull();
    }

    /**
     * Tests the BusinessLocationI18nDto all-args constructor
     */
    @Test
    void testBusinessLocationI18nDtoAllArgsConstructor() {
        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        String description = "test-value";
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BusinessLocationDto businessLocation = new BusinessLocationDto();
        LanguagesDto language = new LanguagesDto();

        BusinessLocationI18nDto dto = new BusinessLocationI18nDto(id, description, code, dateCreated, lastUpdated, recdeleted, businessLocation, language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testBusinessLocationI18nDtoSettersAndGetters() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        String description = "test-value";
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BusinessLocationDto businessLocation = new BusinessLocationDto();
        LanguagesDto language = new LanguagesDto();

        dto.setId(id);
        dto.setDescription(description);
        dto.setCode(code);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setBusinessLocation(businessLocation);
        dto.setLanguage(language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

}
