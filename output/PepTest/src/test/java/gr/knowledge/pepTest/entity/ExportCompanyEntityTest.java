package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ExportCompanyEntityTest {

    /**
     * Tests the ExportCompany no-args constructor.
     */
    @Test
    void testExportCompanyNoArgsConstructor() {
        ExportCompany entity = new ExportCompany();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ExportCompany all-args constructor.
     */
    @Test
    void testExportCompanyAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Boolean active = Boolean.TRUE;
        Long emeCode = 1L;

        ExportCompany entity = new ExportCompany(id, dateCreated, lastUpdated, company, active, emeCode);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getActive()).isEqualTo(active);
        assertThat(entity.getEmeCode()).isEqualTo(emeCode);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testExportCompanySettersAndGetters() {
        ExportCompany entity = new ExportCompany();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Boolean active = Boolean.TRUE;
        Long emeCode = 1L;

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setCompany(company);
        entity.setActive(active);
        entity.setEmeCode(emeCode);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getActive()).isEqualTo(active);
        assertThat(entity.getEmeCode()).isEqualTo(emeCode);
    }

}
