package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class BusinessLocationDtoTest {

    /**
     * Tests the BusinessLocationDto no-args constructor
     */
    @Test
    void testBusinessLocationDtoNoArgsConstructor() {
        BusinessLocationDto dto = new BusinessLocationDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCode()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getBlobUri()).isNull();
    }

    /**
     * Tests the BusinessLocationDto all-args constructor
     */
    @Test
    void testBusinessLocationDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        BusinessLocationDto dto = new BusinessLocationDto(id, code, dateCreated, lastUpdated, recdeleted, blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testBusinessLocationDtoSettersAndGetters() {
        BusinessLocationDto dto = new BusinessLocationDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        dto.setId(id);
        dto.setCode(code);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setBlobUri(blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

}
