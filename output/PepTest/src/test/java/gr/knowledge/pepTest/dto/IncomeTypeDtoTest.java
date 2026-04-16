package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class IncomeTypeDtoTest {

    /**
     * Tests the IncomeTypeDto no-args constructor
     */
    @Test
    void testIncomeTypeDtoNoArgsConstructor() {
        IncomeTypeDto dto = new IncomeTypeDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberTypeId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getDateCreated()).isNull();
    }

    /**
     * Tests the IncomeTypeDto all-args constructor
     */
    @Test
    void testIncomeTypeDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberTypeId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);

        IncomeTypeDto dto = new IncomeTypeDto(id, chamberId, chamberTypeId, description, lastUpdated, recdeleted, dateCreated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberTypeId()).isEqualTo(chamberTypeId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testIncomeTypeDtoSettersAndGetters() {
        IncomeTypeDto dto = new IncomeTypeDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberTypeId = 1;
        String description = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberTypeId(chamberTypeId);
        dto.setDescription(description);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setDateCreated(dateCreated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberTypeId()).isEqualTo(chamberTypeId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
    }

}
