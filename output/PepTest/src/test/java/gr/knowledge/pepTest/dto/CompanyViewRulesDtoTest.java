package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyViewRulesDtoTest {

    /**
     * Tests the CompanyViewRulesDto no-args constructor
     */
    @Test
    void testCompanyViewRulesDtoNoArgsConstructor() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getShowMobilePhone()).isNull();
        assertThat(dto.getShowPhones()).isNull();
        assertThat(dto.getShowEmail()).isNull();
        assertThat(dto.getShowAfm()).isNull();
        assertThat(dto.getShowBusinessInformation()).isNull();
    }

    /**
     * Tests the CompanyViewRulesDto all-args constructor
     */
    @Test
    void testCompanyViewRulesDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Long chamberId = 1L;
        Boolean showMobilePhone = Boolean.TRUE;
        Boolean showPhones = Boolean.TRUE;
        Boolean showEmail = Boolean.TRUE;
        Boolean showAfm = Boolean.TRUE;
        Boolean showBusinessInformation = Boolean.TRUE;

        CompanyViewRulesDto dto = new CompanyViewRulesDto(id, dateCreated, lastUpdated, chamberId, showMobilePhone, showPhones, showEmail, showAfm, showBusinessInformation);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getShowMobilePhone()).isEqualTo(showMobilePhone);
        assertThat(dto.getShowPhones()).isEqualTo(showPhones);
        assertThat(dto.getShowEmail()).isEqualTo(showEmail);
        assertThat(dto.getShowAfm()).isEqualTo(showAfm);
        assertThat(dto.getShowBusinessInformation()).isEqualTo(showBusinessInformation);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyViewRulesDtoSettersAndGetters() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Long chamberId = 1L;
        Boolean showMobilePhone = Boolean.TRUE;
        Boolean showPhones = Boolean.TRUE;
        Boolean showEmail = Boolean.TRUE;
        Boolean showAfm = Boolean.TRUE;
        Boolean showBusinessInformation = Boolean.TRUE;

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setChamberId(chamberId);
        dto.setShowMobilePhone(showMobilePhone);
        dto.setShowPhones(showPhones);
        dto.setShowEmail(showEmail);
        dto.setShowAfm(showAfm);
        dto.setShowBusinessInformation(showBusinessInformation);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getShowMobilePhone()).isEqualTo(showMobilePhone);
        assertThat(dto.getShowPhones()).isEqualTo(showPhones);
        assertThat(dto.getShowEmail()).isEqualTo(showEmail);
        assertThat(dto.getShowAfm()).isEqualTo(showAfm);
        assertThat(dto.getShowBusinessInformation()).isEqualTo(showBusinessInformation);
    }

}
