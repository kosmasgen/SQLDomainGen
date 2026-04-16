package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ProductEntityTest {

    /**
     * Tests the Product no-args constructor.
     */
    @Test
    void testProductNoArgsConstructor() {
        Product entity = new Product();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Product all-args constructor.
     */
    @Test
    void testProductAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Long chamberProductId = 1L;
        Integer version = 1;
        String cd = "test-value";
        String cdGemi = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Long parentProductId = 1L;
        Boolean recdeleted = Boolean.TRUE;

        Product entity = new Product(id, chamberId, chamberProductId, version, cd, cdGemi, dateCreated, lastUpdated, parentProductId, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProductId()).isEqualTo(chamberProductId);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getCdGemi()).isEqualTo(cdGemi);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getParentProductId()).isEqualTo(parentProductId);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProductSettersAndGetters() {
        Product entity = new Product();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Long chamberProductId = 1L;
        Integer version = 1;
        String cd = "test-value";
        String cdGemi = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Long parentProductId = 1L;
        Boolean recdeleted = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberProductId(chamberProductId);
        entity.setVersion(version);
        entity.setCd(cd);
        entity.setCdGemi(cdGemi);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setParentProductId(parentProductId);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProductId()).isEqualTo(chamberProductId);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getCdGemi()).isEqualTo(cdGemi);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getParentProductId()).isEqualTo(parentProductId);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
