package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;
import java.time.LocalDateTime;

class CompanyProfileI18nDtoTest {

    /**
     * Tests the CompanyProfileI18nDto no-args constructor
     */
    @Test
    void testCompanyProfileI18nDtoNoArgsConstructor() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompanyProfile()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getRecDeleted()).isNull();
        assertThat(dto.getName()).isNull();
        assertThat(dto.getAddressCity()).isNull();
        assertThat(dto.getAddressRegion()).isNull();
        assertThat(dto.getAddressStreet()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getObjective()).isNull();
    }

    /**
     * Tests the CompanyProfileI18nDto all-args constructor
     */
    @Test
    void testCompanyProfileI18nDtoAllArgsConstructor() {
        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        CompanyProfileDto companyProfile = new CompanyProfileDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recDeleted = Boolean.TRUE;
        String name = "test-value";
        String addressCity = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String objective = "test-value";

        CompanyProfileI18nDto dto = new CompanyProfileI18nDto(id, companyProfile, language, recDeleted, name, addressCity, addressRegion, addressStreet, dateCreated, lastUpdated, objective);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getRecDeleted()).isEqualTo(recDeleted);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAddressCity()).isEqualTo(addressCity);
        assertThat(dto.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(dto.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getObjective()).isEqualTo(objective);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyProfileI18nDtoSettersAndGetters() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        CompanyProfileDto companyProfile = new CompanyProfileDto();
        LanguagesDto language = new LanguagesDto();
        Boolean recDeleted = Boolean.TRUE;
        String name = "test-value";
        String addressCity = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String objective = "test-value";

        dto.setId(id);
        dto.setCompanyProfile(companyProfile);
        dto.setLanguage(language);
        dto.setRecDeleted(recDeleted);
        dto.setName(name);
        dto.setAddressCity(addressCity);
        dto.setAddressRegion(addressRegion);
        dto.setAddressStreet(addressStreet);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setObjective(objective);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getRecDeleted()).isEqualTo(recDeleted);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAddressCity()).isEqualTo(addressCity);
        assertThat(dto.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(dto.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getObjective()).isEqualTo(objective);
    }

}
