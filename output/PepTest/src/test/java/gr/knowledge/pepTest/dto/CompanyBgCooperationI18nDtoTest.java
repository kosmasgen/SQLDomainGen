package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyBgCooperationI18nDtoTest {

    /**
     * Tests the CompanyBgCooperationI18nDto no-args constructor
     */
    @Test
    void testCompanyBgCooperationI18nDtoNoArgsConstructor() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getCooperation()).isNull();
        assertThat(dto.getLanguage()).isNull();
    }

    /**
     * Tests the CompanyBgCooperationI18nDto all-args constructor
     */
    @Test
    void testCompanyBgCooperationI18nDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        CompanyBgCooperationDto cooperation = new CompanyBgCooperationDto();
        LanguagesDto language = new LanguagesDto();

        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto(id, description, dateCreated, lastUpdated, recdeleted, cooperation, language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCooperation()).isEqualTo(cooperation);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyBgCooperationI18nDtoSettersAndGetters() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        CompanyBgCooperationDto cooperation = new CompanyBgCooperationDto();
        LanguagesDto language = new LanguagesDto();

        dto.setId(id);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setCooperation(cooperation);
        dto.setLanguage(language);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCooperation()).isEqualTo(cooperation);
        assertThat(dto.getLanguage()).isEqualTo(language);
    }

}
