package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.Companyi18nKey;
import java.time.LocalDateTime;

class Companyi18nDtoTest {

    /**
     * Tests the Companyi18nDto no-args constructor
     */
    @Test
    void testCompanyi18nDtoNoArgsConstructor() {
        Companyi18nDto dto = new Companyi18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getCity()).isNull();
        assertThat(dto.getCoName()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getObjective()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getStreet()).isNull();
        assertThat(dto.getResponsibleName()).isNull();
    }

    /**
     * Tests the Companyi18nDto all-args constructor
     */
    @Test
    void testCompanyi18nDtoAllArgsConstructor() {
        Companyi18nKey id = new Companyi18nKey();
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        String city = "test-value";
        String coName = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String objective = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String street = "test-value";
        String responsibleName = "test-value";

        Companyi18nDto dto = new Companyi18nDto(id, company, language, city, coName, dateCreated, lastUpdated, objective, recdeleted, street, responsibleName);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getObjective()).isEqualTo(objective);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getResponsibleName()).isEqualTo(responsibleName);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyi18nDtoSettersAndGetters() {
        Companyi18nDto dto = new Companyi18nDto();

        Companyi18nKey id = new Companyi18nKey();
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        String city = "test-value";
        String coName = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String objective = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String street = "test-value";
        String responsibleName = "test-value";

        dto.setId(id);
        dto.setCompany(company);
        dto.setLanguage(language);
        dto.setCity(city);
        dto.setCoName(coName);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setObjective(objective);
        dto.setRecdeleted(recdeleted);
        dto.setStreet(street);
        dto.setResponsibleName(responsibleName);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getObjective()).isEqualTo(objective);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getStreet()).isEqualTo(street);
        assertThat(dto.getResponsibleName()).isEqualTo(responsibleName);
    }

}
