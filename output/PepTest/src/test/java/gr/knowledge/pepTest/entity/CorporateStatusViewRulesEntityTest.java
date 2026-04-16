package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CorporateStatusViewRulesEntityTest {

    /**
     * Tests the CorporateStatusViewRules no-args constructor.
     */
    @Test
    void testCorporateStatusViewRulesNoArgsConstructor() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CorporateStatusViewRules all-args constructor.
     */
    @Test
    void testCorporateStatusViewRulesAllArgsConstructor() {
        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        CorporateStatus corporateStatus = new CorporateStatus();
        CompanyViewRules companyViewRules = new CompanyViewRules();
        Boolean excludeCompanies = Boolean.TRUE;
        Boolean showContactInfo = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        CorporateStatusViewRules entity = new CorporateStatusViewRules(id, corporateStatus, companyViewRules, excludeCompanies, showContactInfo, dateCreated, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(entity.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(entity.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(entity.getShowContactInfo()).isEqualTo(showContactInfo);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCorporateStatusViewRulesSettersAndGetters() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        CorporateStatus corporateStatus = new CorporateStatus();
        CompanyViewRules companyViewRules = new CompanyViewRules();
        Boolean excludeCompanies = Boolean.TRUE;
        Boolean showContactInfo = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setCorporateStatus(corporateStatus);
        entity.setCompanyViewRules(companyViewRules);
        entity.setExcludeCompanies(excludeCompanies);
        entity.setShowContactInfo(showContactInfo);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(entity.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(entity.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(entity.getShowContactInfo()).isEqualTo(showContactInfo);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
