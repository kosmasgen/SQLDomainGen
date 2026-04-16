package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CorporateStatusEntityTest {

    /**
     * Tests the CorporateStatus no-args constructor.
     */
    @Test
    void testCorporateStatusNoArgsConstructor() {
        CorporateStatus entity = new CorporateStatus();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CorporateStatus all-args constructor.
     */
    @Test
    void testCorporateStatusAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCorporateStatusId = 1;
        Integer chamberId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        CorporateStatus entity = new CorporateStatus(id, chamberCorporateStatusId, chamberId, cd, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberCorporateStatusId()).isEqualTo(chamberCorporateStatusId);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCorporateStatusSettersAndGetters() {
        CorporateStatus entity = new CorporateStatus();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCorporateStatusId = 1;
        Integer chamberId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberCorporateStatusId(chamberCorporateStatusId);
        entity.setChamberId(chamberId);
        entity.setCd(cd);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberCorporateStatusId()).isEqualTo(chamberCorporateStatusId);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
