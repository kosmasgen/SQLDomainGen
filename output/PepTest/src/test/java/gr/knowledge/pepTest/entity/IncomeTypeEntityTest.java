package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class IncomeTypeEntityTest {

    /**
     * Tests the IncomeType no-args constructor.
     */
    @Test
    void testIncomeTypeNoArgsConstructor() {
        IncomeType entity = new IncomeType();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the IncomeType all-args constructor.
     */
    @Test
    void testIncomeTypeAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberTypeId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        IncomeType entity = new IncomeType(id, chamberId, chamberTypeId, description, lastUpdated, recdeleted, dateCreated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberTypeId()).isEqualTo(chamberTypeId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testIncomeTypeSettersAndGetters() {
        IncomeType entity = new IncomeType();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberTypeId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberTypeId(chamberTypeId);
        entity.setDescription(description);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setDateCreated(dateCreated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberTypeId()).isEqualTo(chamberTypeId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
    }

}
