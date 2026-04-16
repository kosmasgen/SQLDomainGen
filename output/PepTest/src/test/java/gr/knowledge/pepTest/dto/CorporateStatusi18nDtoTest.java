package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CorporateStatusi18nKey;
import java.time.LocalDateTime;

class CorporateStatusi18nDtoTest {

    /**
     * Tests the CorporateStatusi18nDto no-args constructor
     */
    @Test
    void testCorporateStatusi18nDtoNoArgsConstructor() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCorporateStatus()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
        assertThat(dto.getGroupedDescription()).isNull();
    }

    /**
     * Tests the CorporateStatusi18nDto all-args constructor
     */
    @Test
    void testCorporateStatusi18nDtoAllArgsConstructor() {
        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        CorporateStatusDto corporateStatus = new CorporateStatusDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;
        String groupedDescription = "test-value";

        CorporateStatusi18nDto dto = new CorporateStatusi18nDto(id, corporateStatus, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId, groupedDescription);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(dto.getGroupedDescription()).isEqualTo(groupedDescription);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCorporateStatusi18nDtoSettersAndGetters() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        CorporateStatusDto corporateStatus = new CorporateStatusDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;
        String groupedDescription = "test-value";

        dto.setId(id);
        dto.setCorporateStatus(corporateStatus);
        dto.setLanguage(language);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setChamberI18nId(chamberI18nId);
        dto.setGroupedDescription(groupedDescription);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(dto.getGroupedDescription()).isEqualTo(groupedDescription);
    }

}
