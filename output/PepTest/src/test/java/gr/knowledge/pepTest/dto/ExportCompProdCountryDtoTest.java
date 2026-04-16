package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ExportCompProdCountryDtoTest {

    /**
     * Tests the ExportCompProdCountryDto no-args constructor
     */
    @Test
    void testExportCompProdCountryDtoNoArgsConstructor() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getExportCompany()).isNull();
        assertThat(dto.getCountry()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getExpYear()).isNull();
        assertThat(dto.getProduct()).isNull();
    }

    /**
     * Tests the ExportCompProdCountryDto all-args constructor
     */
    @Test
    void testExportCompProdCountryDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto exportCompany = new ExportCompanyDto();
        CountryDto country = new CountryDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer expYear = 1;
        ProductDto product = new ProductDto();

        ExportCompProdCountryDto dto = new ExportCompProdCountryDto(id, exportCompany, country, dateCreated, lastUpdated, recdeleted, expYear, product);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getExportCompany()).isEqualTo(exportCompany);
        assertThat(dto.getCountry()).isEqualTo(country);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getExpYear()).isEqualTo(expYear);
        assertThat(dto.getProduct()).isEqualTo(product);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testExportCompProdCountryDtoSettersAndGetters() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto exportCompany = new ExportCompanyDto();
        CountryDto country = new CountryDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer expYear = 1;
        ProductDto product = new ProductDto();

        dto.setId(id);
        dto.setExportCompany(exportCompany);
        dto.setCountry(country);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setExpYear(expYear);
        dto.setProduct(product);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getExportCompany()).isEqualTo(exportCompany);
        assertThat(dto.getCountry()).isEqualTo(country);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getExpYear()).isEqualTo(expYear);
        assertThat(dto.getProduct()).isEqualTo(product);
    }

}
