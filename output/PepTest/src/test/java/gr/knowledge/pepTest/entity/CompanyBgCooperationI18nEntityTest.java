package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyBgCooperationI18nEntityTest {

    /**
     * Tests the CompanyBgCooperationI18n no-args constructor.
     */
    @Test
    void testCompanyBgCooperationI18nNoArgsConstructor() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyBgCooperationI18n all-args constructor.
     */
    @Test
    void testCompanyBgCooperationI18nAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        CompanyBgCooperation cooperation = new CompanyBgCooperation();
        Languages language = new Languages();

        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n(id, description, dateCreated, lastUpdated, recdeleted, cooperation, language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCooperation()).isEqualTo(cooperation);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyBgCooperationI18nSettersAndGetters() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        CompanyBgCooperation cooperation = new CompanyBgCooperation();
        Languages language = new Languages();

        entity.setId(id);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setCooperation(cooperation);
        entity.setLanguage(language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCooperation()).isEqualTo(cooperation);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

}
