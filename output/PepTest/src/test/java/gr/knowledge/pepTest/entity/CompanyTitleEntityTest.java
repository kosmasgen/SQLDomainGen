package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;

class CompanyTitleEntityTest {

    /**
     * Tests the CompanyTitle no-args constructor.
     */
    @Test
    void testCompanyTitleNoArgsConstructor() {
        CompanyTitle entity = new CompanyTitle();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyTitle all-args constructor.
     */
    @Test
    void testCompanyTitleAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String title = "test-value";
        BigInteger chamberTitleId = new BigInteger("1");
        Company company = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyTitle entity = new CompanyTitle(id, chamberId, title, chamberTitleId, company, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getChamberTitleId()).isEqualTo(chamberTitleId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyTitleSettersAndGetters() {
        CompanyTitle entity = new CompanyTitle();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String title = "test-value";
        BigInteger chamberTitleId = new BigInteger("1");
        Company company = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setTitle(title);
        entity.setChamberTitleId(chamberTitleId);
        entity.setCompany(company);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getChamberTitleId()).isEqualTo(chamberTitleId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
