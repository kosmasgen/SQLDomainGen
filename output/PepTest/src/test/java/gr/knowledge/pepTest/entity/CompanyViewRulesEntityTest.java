package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyViewRulesEntityTest {

    /**
     * Tests the CompanyViewRules no-args constructor.
     */
    @Test
    void testCompanyViewRulesNoArgsConstructor() {
        CompanyViewRules entity = new CompanyViewRules();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyViewRules all-args constructor.
     */
    @Test
    void testCompanyViewRulesAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Long chamberId = 1L;
        Boolean showMobilePhone = Boolean.TRUE;
        Boolean showPhones = Boolean.TRUE;
        Boolean showEmail = Boolean.TRUE;
        Boolean showAfm = Boolean.TRUE;
        Boolean showBusinessInformation = Boolean.TRUE;

        CompanyViewRules entity = new CompanyViewRules(id, dateCreated, lastUpdated, chamberId, showMobilePhone, showPhones, showEmail, showAfm, showBusinessInformation);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getShowMobilePhone()).isEqualTo(showMobilePhone);
        assertThat(entity.getShowPhones()).isEqualTo(showPhones);
        assertThat(entity.getShowEmail()).isEqualTo(showEmail);
        assertThat(entity.getShowAfm()).isEqualTo(showAfm);
        assertThat(entity.getShowBusinessInformation()).isEqualTo(showBusinessInformation);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyViewRulesSettersAndGetters() {
        CompanyViewRules entity = new CompanyViewRules();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Long chamberId = 1L;
        Boolean showMobilePhone = Boolean.TRUE;
        Boolean showPhones = Boolean.TRUE;
        Boolean showEmail = Boolean.TRUE;
        Boolean showAfm = Boolean.TRUE;
        Boolean showBusinessInformation = Boolean.TRUE;

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setChamberId(chamberId);
        entity.setShowMobilePhone(showMobilePhone);
        entity.setShowPhones(showPhones);
        entity.setShowEmail(showEmail);
        entity.setShowAfm(showAfm);
        entity.setShowBusinessInformation(showBusinessInformation);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getShowMobilePhone()).isEqualTo(showMobilePhone);
        assertThat(entity.getShowPhones()).isEqualTo(showPhones);
        assertThat(entity.getShowEmail()).isEqualTo(showEmail);
        assertThat(entity.getShowAfm()).isEqualTo(showAfm);
        assertThat(entity.getShowBusinessInformation()).isEqualTo(showBusinessInformation);
    }

}
