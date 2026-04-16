package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class ChAppUserContactI18nEntityTest {

    /**
     * Tests the ChAppUserContactI18n no-args constructor.
     */
    @Test
    void testChAppUserContactI18nNoArgsConstructor() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ChAppUserContactI18n all-args constructor.
     */
    @Test
    void testChAppUserContactI18nAllArgsConstructor() {
        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        ChAppUserContact chAppUserContact = new ChAppUserContact();
        Languages language = new Languages();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String city = "test-value";
        String street = "test-value";
        Boolean recdeleted = Boolean.TRUE;

        ChAppUserContactI18n entity = new ChAppUserContactI18n(id, chAppUserContact, language, dateCreated, lastUpdated, city, street, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChAppUserContact()).isEqualTo(chAppUserContact);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testChAppUserContactI18nSettersAndGetters() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        ChAppUserContact chAppUserContact = new ChAppUserContact();
        Languages language = new Languages();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String city = "test-value";
        String street = "test-value";
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChAppUserContact(chAppUserContact);
        entity.setLanguage(language);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setCity(city);
        entity.setStreet(street);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChAppUserContact()).isEqualTo(chAppUserContact);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCity()).isEqualTo(city);
        assertThat(entity.getStreet()).isEqualTo(street);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
