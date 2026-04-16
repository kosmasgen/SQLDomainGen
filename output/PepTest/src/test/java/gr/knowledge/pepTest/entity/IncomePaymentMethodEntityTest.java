package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class IncomePaymentMethodEntityTest {

    /**
     * Tests the IncomePaymentMethod no-args constructor.
     */
    @Test
    void testIncomePaymentMethodNoArgsConstructor() {
        IncomePaymentMethod entity = new IncomePaymentMethod();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the IncomePaymentMethod all-args constructor.
     */
    @Test
    void testIncomePaymentMethodAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberPayMethodId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer recdeleted = 1;

        IncomePaymentMethod entity = new IncomePaymentMethod(id, chamberId, chamberPayMethodId, description, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberPayMethodId()).isEqualTo(chamberPayMethodId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testIncomePaymentMethodSettersAndGetters() {
        IncomePaymentMethod entity = new IncomePaymentMethod();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberPayMethodId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer recdeleted = 1;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberPayMethodId(chamberPayMethodId);
        entity.setDescription(description);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberPayMethodId()).isEqualTo(chamberPayMethodId);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
