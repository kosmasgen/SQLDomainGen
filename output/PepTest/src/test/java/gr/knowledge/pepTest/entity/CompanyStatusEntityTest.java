package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyStatusEntityTest {

    /**
     * Tests the CompanyStatus no-args constructor.
     */
    @Test
    void testCompanyStatusNoArgsConstructor() {
        CompanyStatus entity = new CompanyStatus();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyStatus all-args constructor.
     */
    @Test
    void testCompanyStatusAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyStatusId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        CompanyStatus entity = new CompanyStatus(id, chamberId, chamberCompanyStatusId, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberCompanyStatusId()).isEqualTo(chamberCompanyStatusId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyStatusSettersAndGetters() {
        CompanyStatus entity = new CompanyStatus();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyStatusId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberCompanyStatusId(chamberCompanyStatusId);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberCompanyStatusId()).isEqualTo(chamberCompanyStatusId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
