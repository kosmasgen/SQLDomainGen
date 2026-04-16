package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ProductDtoTest {

    /**
     * Tests the ProductDto no-args constructor
     */
    @Test
    void testProductDtoNoArgsConstructor() {
        ProductDto dto = new ProductDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberProductId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getCd()).isNull();
        assertThat(dto.getCdGemi()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getParentProductId()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the ProductDto all-args constructor
     */
    @Test
    void testProductDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Long chamberProductId = 1L;
        Integer version = 1;
        String cd = "test-value";
        String cdGemi = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Long parentProductId = 1L;
        Boolean recdeleted = Boolean.TRUE;

        ProductDto dto = new ProductDto(id, chamberId, chamberProductId, version, cd, cdGemi, dateCreated, lastUpdated, parentProductId, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberProductId()).isEqualTo(chamberProductId);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getCdGemi()).isEqualTo(cdGemi);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getParentProductId()).isEqualTo(parentProductId);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testProductDtoSettersAndGetters() {
        ProductDto dto = new ProductDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Long chamberProductId = 1L;
        Integer version = 1;
        String cd = "test-value";
        String cdGemi = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Long parentProductId = 1L;
        Boolean recdeleted = Boolean.TRUE;

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberProductId(chamberProductId);
        dto.setVersion(version);
        dto.setCd(cd);
        dto.setCdGemi(cdGemi);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setParentProductId(parentProductId);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberProductId()).isEqualTo(chamberProductId);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getCdGemi()).isEqualTo(cdGemi);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getParentProductId()).isEqualTo(parentProductId);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
