package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;
import java.time.LocalDateTime;

class CorporateStatusViewRulesDtoTest {

    /**
     * Tests the CorporateStatusViewRulesDto no-args constructor
     */
    @Test
    void testCorporateStatusViewRulesDtoNoArgsConstructor() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCorporateStatus()).isNull();
        assertThat(dto.getCompanyViewRules()).isNull();
        assertThat(dto.getExcludeCompanies()).isNull();
        assertThat(dto.getShowContactInfo()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the CorporateStatusViewRulesDto all-args constructor
     */
    @Test
    void testCorporateStatusViewRulesDtoAllArgsConstructor() {
        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        CorporateStatusDto corporateStatus = new CorporateStatusDto();
        CompanyViewRulesDto companyViewRules = new CompanyViewRulesDto();
        Boolean excludeCompanies = Boolean.TRUE;
        Boolean showContactInfo = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto(id, corporateStatus, companyViewRules, excludeCompanies, showContactInfo, dateCreated, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(dto.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(dto.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(dto.getShowContactInfo()).isEqualTo(showContactInfo);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCorporateStatusViewRulesDtoSettersAndGetters() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        CorporateStatusDto corporateStatus = new CorporateStatusDto();
        CompanyViewRulesDto companyViewRules = new CompanyViewRulesDto();
        Boolean excludeCompanies = Boolean.TRUE;
        Boolean showContactInfo = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setCorporateStatus(corporateStatus);
        dto.setCompanyViewRules(companyViewRules);
        dto.setExcludeCompanies(excludeCompanies);
        dto.setShowContactInfo(showContactInfo);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(dto.getCompanyViewRules()).isEqualTo(companyViewRules);
        assertThat(dto.getExcludeCompanies()).isEqualTo(excludeCompanies);
        assertThat(dto.getShowContactInfo()).isEqualTo(showContactInfo);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
