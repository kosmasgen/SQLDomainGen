package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class BgPoiDtoTest {

    /**
     * Tests the BgPoiDto no-args constructor
     */
    @Test
    void testBgPoiDtoNoArgsConstructor() {
        BgPoiDto dto = new BgPoiDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getLatitude()).isNull();
        assertThat(dto.getLongitude()).isNull();
    }

    /**
     * Tests the BgPoiDto all-args constructor
     */
    @Test
    void testBgPoiDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String latitude = "test-value";
        String longitude = "test-value";

        BgPoiDto dto = new BgPoiDto(id, chamberId, dateCreated, lastUpdated, recdeleted, latitude, longitude);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testBgPoiDtoSettersAndGetters() {
        BgPoiDto dto = new BgPoiDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String latitude = "test-value";
        String longitude = "test-value";

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
    }

}
