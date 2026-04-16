package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nKey;
import java.time.LocalDateTime;

class ChamberDepartmenti18nDtoTest {

    /**
     * Tests the ChamberDepartmenti18nDto no-args constructor
     */
    @Test
    void testChamberDepartmenti18nDtoNoArgsConstructor() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDepartment()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getChamberI18nId()).isNull();
    }

    /**
     * Tests the ChamberDepartmenti18nDto all-args constructor
     */
    @Test
    void testChamberDepartmenti18nDtoAllArgsConstructor() {
        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        ChamberDepartmentDto department = new ChamberDepartmentDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto(id, department, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDepartment()).isEqualTo(department);
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
    void testChamberDepartmenti18nDtoSettersAndGetters() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        ChamberDepartmentDto department = new ChamberDepartmentDto();
        LanguagesDto language = new LanguagesDto();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        dto.setId(id);
        dto.setDepartment(department);
        dto.setLanguage(language);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setChamberI18nId(chamberI18nId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDepartment()).isEqualTo(department);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
