package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CountryDtoTest {

    /**
     * Tests the CountryDto no-args constructor
     */
    @Test
    void testCountryDtoNoArgsConstructor() {
        CountryDto dto = new CountryDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getRegionId()).isNull();
        assertThat(dto.getChamberCountryId()).isNull();
    }

    /**
     * Tests the CountryDto all-args constructor
     */
    @Test
    void testCountryDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberId = 1;
        UUID regionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCountryId = 1;

        CountryDto dto = new CountryDto(id, dateCreated, lastUpdated, recdeleted, chamberId, regionId, chamberCountryId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getRegionId()).isEqualTo(regionId);
        assertThat(dto.getChamberCountryId()).isEqualTo(chamberCountryId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCountryDtoSettersAndGetters() {
        CountryDto dto = new CountryDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberId = 1;
        UUID regionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberCountryId = 1;

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setChamberId(chamberId);
        dto.setRegionId(regionId);
        dto.setChamberCountryId(chamberCountryId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getRegionId()).isEqualTo(regionId);
        assertThat(dto.getChamberCountryId()).isEqualTo(chamberCountryId);
    }

}
