package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class ChamberDepartmenti18nEntityTest {

    /**
     * Tests the ChamberDepartmenti18n no-args constructor.
     */
    @Test
    void testChamberDepartmenti18nNoArgsConstructor() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ChamberDepartmenti18n all-args constructor.
     */
    @Test
    void testChamberDepartmenti18nAllArgsConstructor() {
        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        ChamberDepartment department = new ChamberDepartment();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        ChamberDepartmenti18n entity = new ChamberDepartmenti18n(id, department, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDepartment()).isEqualTo(department);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testChamberDepartmenti18nSettersAndGetters() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        ChamberDepartment department = new ChamberDepartment();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;

        entity.setId(id);
        entity.setDepartment(department);
        entity.setLanguage(language);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberI18nId(chamberI18nId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDepartment()).isEqualTo(department);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
    }

}
