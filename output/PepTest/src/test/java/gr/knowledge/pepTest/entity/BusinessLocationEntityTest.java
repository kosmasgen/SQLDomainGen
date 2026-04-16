package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class BusinessLocationEntityTest {

    /**
     * Tests the BusinessLocation no-args constructor.
     */
    @Test
    void testBusinessLocationNoArgsConstructor() {
        BusinessLocation entity = new BusinessLocation();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the BusinessLocation all-args constructor.
     */
    @Test
    void testBusinessLocationAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        BusinessLocation entity = new BusinessLocation(id, code, dateCreated, lastUpdated, recdeleted, blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testBusinessLocationSettersAndGetters() {
        BusinessLocation entity = new BusinessLocation();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        entity.setId(id);
        entity.setCode(code);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setBlobUri(blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

}
