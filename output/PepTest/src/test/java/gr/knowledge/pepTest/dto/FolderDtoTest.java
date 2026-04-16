package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class FolderDtoTest {

    /**
     * Tests the FolderDto no-args constructor
     */
    @Test
    void testFolderDtoNoArgsConstructor() {
        FolderDto dto = new FolderDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDescr()).isNull();
        assertThat(dto.getUri()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the FolderDto all-args constructor
     */
    @Test
    void testFolderDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String descr = "test-value";
        String uri = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        FolderDto dto = new FolderDto(id, descr, uri, dateCreated, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getUri()).isEqualTo(uri);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testFolderDtoSettersAndGetters() {
        FolderDto dto = new FolderDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String descr = "test-value";
        String uri = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setDescr(descr);
        dto.setUri(uri);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getUri()).isEqualTo(uri);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
