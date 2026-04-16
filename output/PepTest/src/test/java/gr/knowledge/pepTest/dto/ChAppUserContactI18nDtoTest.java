package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nKey;
import java.time.LocalDateTime;

class ChAppUserContactI18nDtoTest {

    /**
     * Tests the ChAppUserContactI18nDto no-args constructor
     */
    @Test
    void testChAppUserContactI18nDtoNoArgsConstructor() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChAppUserContact()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getCity()).isNull();
        assertThat(dto.getStreet()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the ChAppUserContactI18nDto all-args constructor
     */
    @Test
    void testChAppUserContactI18nDtoAllArgsConstructor() {
        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        ChAppUserContactDto chAppUserContact = new ChAppUserContactDto();
        LanguagesDto language = new LanguagesDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String city = "test-value";
        String street = "test-value";
        Boolean recdeleted = Boolean.TRUE;

        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto(id, chAppUserContact, language, dateCreated, lastUpdated, city, street, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChAppUserContact()).isEqualTo(chAppUserContact);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testChAppUserContactI18nDtoSettersAndGetters() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        ChAppUserContactDto chAppUserContact = new ChAppUserContactDto();
        LanguagesDto language = new LanguagesDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String city = "test-value";
        String street = "test-value";
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setChAppUserContact(chAppUserContact);
        dto.setLanguage(language);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setCity(city);
        dto.setStreet(street);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChAppUserContact()).isEqualTo(chAppUserContact);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
