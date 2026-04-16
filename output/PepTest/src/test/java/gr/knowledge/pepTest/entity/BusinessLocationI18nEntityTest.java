package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class BusinessLocationI18nEntityTest {

    /**
     * Tests the BusinessLocationI18n no-args constructor.
     */
    @Test
    void testBusinessLocationI18nNoArgsConstructor() {
        BusinessLocationI18n entity = new BusinessLocationI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the BusinessLocationI18n all-args constructor.
     */
    @Test
    void testBusinessLocationI18nAllArgsConstructor() {
        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        String description = "test-value";
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BusinessLocation businessLocation = new BusinessLocation();
        Languages language = new Languages();

        BusinessLocationI18n entity = new BusinessLocationI18n(id, description, code, dateCreated, lastUpdated, recdeleted, businessLocation, language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testBusinessLocationI18nSettersAndGetters() {
        BusinessLocationI18n entity = new BusinessLocationI18n();

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        String description = "test-value";
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BusinessLocation businessLocation = new BusinessLocation();
        Languages language = new Languages();

        entity.setId(id);
        entity.setDescription(description);
        entity.setCode(code);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setBusinessLocation(businessLocation);
        entity.setLanguage(language);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(entity.getLanguage()).isEqualTo(language);
    }

}
