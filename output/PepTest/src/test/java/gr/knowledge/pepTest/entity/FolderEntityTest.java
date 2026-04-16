package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class FolderEntityTest {

    /**
     * Tests the Folder no-args constructor.
     */
    @Test
    void testFolderNoArgsConstructor() {
        Folder entity = new Folder();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Folder all-args constructor.
     */
    @Test
    void testFolderAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String descr = "test-value";
        String uri = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        Folder entity = new Folder(id, descr, uri, dateCreated, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getUri()).isEqualTo(uri);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testFolderSettersAndGetters() {
        Folder entity = new Folder();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String descr = "test-value";
        String uri = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setDescr(descr);
        entity.setUri(uri);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getUri()).isEqualTo(uri);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
