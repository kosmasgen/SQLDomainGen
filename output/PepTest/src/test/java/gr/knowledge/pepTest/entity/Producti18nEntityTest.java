package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class Producti18nEntityTest {

    /**
     * Tests the Producti18n no-args constructor.
     */
    @Test
    void testProducti18nNoArgsConstructor() {
        Producti18n entity = new Producti18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Producti18n all-args constructor.
     */
    @Test
    void testProducti18nAllArgsConstructor() {
        Producti18nKey id = new Producti18nKey();
        Integer version = 1;
        String description = "test-value";
        Long chamberI18nId = 1L;
        Languages language = new Languages();
        Product product = new Product();
        String shortDescription = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        Producti18n entity = new Producti18n(id, version, description, chamberI18nId, language, product, shortDescription, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getProduct()).isEqualTo(product);
        assertThat(entity.getShortDescription()).isEqualTo(shortDescription);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProducti18nSettersAndGetters() {
        Producti18n entity = new Producti18n();

        Producti18nKey id = new Producti18nKey();
        Integer version = 1;
        String description = "test-value";
        Long chamberI18nId = 1L;
        Languages language = new Languages();
        Product product = new Product();
        String shortDescription = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setVersion(version);
        entity.setDescription(description);
        entity.setChamberI18nId(chamberI18nId);
        entity.setLanguage(language);
        entity.setProduct(product);
        entity.setShortDescription(shortDescription);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getProduct()).isEqualTo(product);
        assertThat(entity.getShortDescription()).isEqualTo(shortDescription);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
