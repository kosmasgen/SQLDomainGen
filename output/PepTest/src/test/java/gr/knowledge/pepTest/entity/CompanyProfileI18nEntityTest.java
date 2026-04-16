package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CompanyProfileI18nEntityTest {

    /**
     * Tests the CompanyProfileI18n no-args constructor.
     */
    @Test
    void testCompanyProfileI18nNoArgsConstructor() {
        CompanyProfileI18n entity = new CompanyProfileI18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyProfileI18n all-args constructor.
     */
    @Test
    void testCompanyProfileI18nAllArgsConstructor() {
        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        CompanyProfile companyProfile = new CompanyProfile();
        Languages language = new Languages();
        Boolean recDeleted = Boolean.TRUE;
        String name = "test-value";
        String addressCity = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String objective = "test-value";

        CompanyProfileI18n entity = new CompanyProfileI18n(id, companyProfile, language, recDeleted, name, addressCity, addressRegion, addressStreet, dateCreated, lastUpdated, objective);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getRecDeleted()).isEqualTo(recDeleted);
        assertThat(entity.getName()).isEqualTo(name);
        assertThat(entity.getAddressCity()).isEqualTo(addressCity);
        assertThat(entity.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(entity.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getObjective()).isEqualTo(objective);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyProfileI18nSettersAndGetters() {
        CompanyProfileI18n entity = new CompanyProfileI18n();

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        CompanyProfile companyProfile = new CompanyProfile();
        Languages language = new Languages();
        Boolean recDeleted = Boolean.TRUE;
        String name = "test-value";
        String addressCity = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String objective = "test-value";

        entity.setId(id);
        entity.setCompanyProfile(companyProfile);
        entity.setLanguage(language);
        entity.setRecDeleted(recDeleted);
        entity.setName(name);
        entity.setAddressCity(addressCity);
        entity.setAddressRegion(addressRegion);
        entity.setAddressStreet(addressStreet);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setObjective(objective);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getRecDeleted()).isEqualTo(recDeleted);
        assertThat(entity.getName()).isEqualTo(name);
        assertThat(entity.getAddressCity()).isEqualTo(addressCity);
        assertThat(entity.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(entity.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getObjective()).isEqualTo(objective);
    }

}
