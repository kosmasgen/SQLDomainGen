package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyBgCooperationEntityTest {

    /**
     * Tests the CompanyBgCooperation no-args constructor.
     */
    @Test
    void testCompanyBgCooperationNoArgsConstructor() {
        CompanyBgCooperation entity = new CompanyBgCooperation();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyBgCooperation all-args constructor.
     */
    @Test
    void testCompanyBgCooperationAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        Company coopCompany = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cooperationStatus = "test-value";

        CompanyBgCooperation entity = new CompanyBgCooperation(id, chamberId, company, coopCompany, dateCreated, lastUpdated, recdeleted, cooperationStatus);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getCoopCompany()).isEqualTo(coopCompany);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCooperationStatus()).isEqualTo(cooperationStatus);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyBgCooperationSettersAndGetters() {
        CompanyBgCooperation entity = new CompanyBgCooperation();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        Company coopCompany = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cooperationStatus = "test-value";

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCompany(company);
        entity.setCoopCompany(coopCompany);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setCooperationStatus(cooperationStatus);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getCoopCompany()).isEqualTo(coopCompany);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCooperationStatus()).isEqualTo(cooperationStatus);
    }

}
