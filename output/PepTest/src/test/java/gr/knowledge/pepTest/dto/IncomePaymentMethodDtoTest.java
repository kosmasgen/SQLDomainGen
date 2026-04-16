package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class IncomePaymentMethodDtoTest {

    /**
     * Tests the IncomePaymentMethodDto no-args constructor
     */
    @Test
    void testIncomePaymentMethodDtoNoArgsConstructor() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberPayMethodId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the IncomePaymentMethodDto all-args constructor
     */
    @Test
    void testIncomePaymentMethodDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberPayMethodId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer recdeleted = 1;

        IncomePaymentMethodDto dto = new IncomePaymentMethodDto(id, chamberId, chamberPayMethodId, description, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberPayMethodId()).isEqualTo(chamberPayMethodId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testIncomePaymentMethodDtoSettersAndGetters() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberPayMethodId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer recdeleted = 1;

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberPayMethodId(chamberPayMethodId);
        dto.setDescription(description);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberPayMethodId()).isEqualTo(chamberPayMethodId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
