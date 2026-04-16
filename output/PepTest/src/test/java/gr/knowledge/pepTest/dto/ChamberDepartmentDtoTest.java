package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ChamberDepartmentDtoTest {

    /**
     * Tests the ChamberDepartmentDto no-args constructor
     */
    @Test
    void testChamberDepartmentDtoNoArgsConstructor() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberDepartmentId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getCd()).isNull();
    }

    /**
     * Tests the ChamberDepartmentDto all-args constructor
     */
    @Test
    void testChamberDepartmentDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberDepartmentId = 1;
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";

        ChamberDepartmentDto dto = new ChamberDepartmentDto(id, chamberDepartmentId, chamberId, dateCreated, lastUpdated, recdeleted, cd);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberDepartmentId()).isEqualTo(chamberDepartmentId);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCd()).isEqualTo(cd);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testChamberDepartmentDtoSettersAndGetters() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberDepartmentId = 1;
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";

        dto.setId(id);
        dto.setChamberDepartmentId(chamberDepartmentId);
        dto.setChamberId(chamberId);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setCd(cd);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberDepartmentId()).isEqualTo(chamberDepartmentId);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCd()).isEqualTo(cd);
    }

}
