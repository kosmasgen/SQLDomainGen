package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class MunicipalityDtoTest {

    /**
     * Tests the MunicipalityDto no-args constructor
     */
    @Test
    void testMunicipalityDtoNoArgsConstructor() {
        MunicipalityDto dto = new MunicipalityDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberMunicipalityId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getCd()).isNull();
        assertThat(dto.getIsProteasData()).isNull();
    }

    /**
     * Tests the MunicipalityDto all-args constructor
     */
    @Test
    void testMunicipalityDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Long chamberId = 1L;
        Long chamberMunicipalityId = 1L;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";
        Boolean isProteasData = Boolean.TRUE;

        MunicipalityDto dto = new MunicipalityDto(id, chamberId, chamberMunicipalityId, description, dateCreated, lastUpdated, recdeleted, cd, isProteasData);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberMunicipalityId()).isEqualTo(chamberMunicipalityId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getIsProteasData()).isEqualTo(isProteasData);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testMunicipalityDtoSettersAndGetters() {
        MunicipalityDto dto = new MunicipalityDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Long chamberId = 1L;
        Long chamberMunicipalityId = 1L;
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";
        Boolean isProteasData = Boolean.TRUE;

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberMunicipalityId(chamberMunicipalityId);
        dto.setDescription(description);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setCd(cd);
        dto.setIsProteasData(isProteasData);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberMunicipalityId()).isEqualTo(chamberMunicipalityId);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getIsProteasData()).isEqualTo(isProteasData);
    }

}
