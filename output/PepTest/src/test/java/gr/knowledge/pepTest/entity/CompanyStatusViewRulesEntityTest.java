package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CompanyStatusViewRulesEntityTest {

    /**
     * Tests the CompanyStatusViewRules no-args constructor.
     */
    @Test
    void testCompanyStatusViewRulesNoArgsConstructor() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyStatusViewRules all-args constructor.
     */
    @Test
    void testCompanyStatusViewRulesAllArgsConstructor() {
        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        CompanyStatus companyStatus = new CompanyStatus();
        CompanyViewRules companyViewRules = new CompanyViewRules();
        Boolean excludeCompanies = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        CompanyStatusViewRules entity = new CompanyStatusViewRules(id, companyStatus, companyViewRules, excludeCompanies, dateCreated, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(entity.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyStatusViewRulesSettersAndGetters() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        CompanyStatus companyStatus = new CompanyStatus();
        CompanyViewRules companyViewRules = new CompanyViewRules();
        Boolean excludeCompanies = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setCompanyStatus(companyStatus);
        entity.setCompanyViewRules(companyViewRules);
        entity.setExcludeCompanies(excludeCompanies);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(entity.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(entity.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
