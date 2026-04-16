package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class MunicipalityEntityTest {

    /**
     * Tests the Municipality no-args constructor.
     */
    @Test
    void testMunicipalityNoArgsConstructor() {
        Municipality entity = new Municipality();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Municipality all-args constructor.
     */
    @Test
    void testMunicipalityAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Long chamberId = 1L;
        Long chamberMunicipalityId = 1L;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";
        Boolean isProteasData = Boolean.TRUE;

        Municipality entity = new Municipality(id, chamberId, chamberMunicipalityId, description, dateCreated, lastUpdated, recdeleted, cd, isProteasData);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberMunicipalityId()).isEqualTo(chamberMunicipalityId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getIsProteasData()).isEqualTo(isProteasData);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testMunicipalitySettersAndGetters() {
        Municipality entity = new Municipality();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Long chamberId = 1L;
        Long chamberMunicipalityId = 1L;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";
        Boolean isProteasData = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberMunicipalityId(chamberMunicipalityId);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setCd(cd);
        entity.setIsProteasData(isProteasData);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberMunicipalityId()).isEqualTo(chamberMunicipalityId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getIsProteasData()).isEqualTo(isProteasData);
    }

}
