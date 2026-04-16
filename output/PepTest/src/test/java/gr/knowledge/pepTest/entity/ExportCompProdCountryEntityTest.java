package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ExportCompProdCountryEntityTest {

    /**
     * Tests the ExportCompProdCountry no-args constructor.
     */
    @Test
    void testExportCompProdCountryNoArgsConstructor() {
        ExportCompProdCountry entity = new ExportCompProdCountry();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ExportCompProdCountry all-args constructor.
     */
    @Test
    void testExportCompProdCountryAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompany exportCompany = new ExportCompany();
        Country country = new Country();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer expYear = 1;
        Product product = new Product();

        ExportCompProdCountry entity = new ExportCompProdCountry(id, exportCompany, country, dateCreated, lastUpdated, recdeleted, expYear, product);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getExportCompany()).isEqualTo(exportCompany);
        assertThat(entity.getCountry()).isEqualTo(country);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getExpYear()).isEqualTo(expYear);
        assertThat(entity.getProduct()).isEqualTo(product);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testExportCompProdCountrySettersAndGetters() {
        ExportCompProdCountry entity = new ExportCompProdCountry();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompany exportCompany = new ExportCompany();
        Country country = new Country();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer expYear = 1;
        Product product = new Product();

        entity.setId(id);
        entity.setExportCompany(exportCompany);
        entity.setCountry(country);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setExpYear(expYear);
        entity.setProduct(product);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getExportCompany()).isEqualTo(exportCompany);
        assertThat(entity.getCountry()).isEqualTo(country);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getExpYear()).isEqualTo(expYear);
        assertThat(entity.getProduct()).isEqualTo(product);
    }

}
