package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CorporateStatusDtoTest {

    /**
     * Tests the CorporateStatusDto no-args constructor
     */
    @Test
    void testCorporateStatusDtoNoArgsConstructor() {
        CorporateStatusDto dto = new CorporateStatusDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberCorporateStatusId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCd()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the CorporateStatusDto all-args constructor
     */
    @Test
    void testCorporateStatusDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCorporateStatusId = 1;
        Integer chamberId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        CorporateStatusDto dto = new CorporateStatusDto(id, chamberCorporateStatusId, chamberId, cd, dateCreated, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberCorporateStatusId()).isEqualTo(chamberCorporateStatusId);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCorporateStatusDtoSettersAndGetters() {
        CorporateStatusDto dto = new CorporateStatusDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCorporateStatusId = 1;
        Integer chamberId = 1;
        String cd = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setChamberCorporateStatusId(chamberCorporateStatusId);
        dto.setChamberId(chamberId);
        dto.setCd(cd);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberCorporateStatusId()).isEqualTo(chamberCorporateStatusId);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
