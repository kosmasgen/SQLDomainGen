package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;
import java.time.LocalDateTime;

class CompanyStatusViewRulesDtoTest {

    /**
     * Tests the CompanyStatusViewRulesDto no-args constructor
     */
    @Test
    void testCompanyStatusViewRulesDtoNoArgsConstructor() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompanyStatus()).isNull();
        assertThat(dto.getCompanyViewRules()).isNull();
        assertThat(dto.getExcludeCompanies()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the CompanyStatusViewRulesDto all-args constructor
     */
    @Test
    void testCompanyStatusViewRulesDtoAllArgsConstructor() {
        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        CompanyStatusDto companyStatus = new CompanyStatusDto();
        CompanyViewRulesDto companyViewRules = new CompanyViewRulesDto();
        Boolean excludeCompanies = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto(id, companyStatus, companyViewRules, excludeCompanies, dateCreated, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(dto.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(dto.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyStatusViewRulesDtoSettersAndGetters() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        CompanyStatusDto companyStatus = new CompanyStatusDto();
        CompanyViewRulesDto companyViewRules = new CompanyViewRulesDto();
        Boolean excludeCompanies = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setCompanyStatus(companyStatus);
        dto.setCompanyViewRules(companyViewRules);
        dto.setExcludeCompanies(excludeCompanies);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompanyStatus()).isEqualTo(companyStatus);
        assertThat(dto.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(dto.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
