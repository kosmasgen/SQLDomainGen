package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import gr.knowledge.pepTest.entity.ExportCompany;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.entity.Product;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ExportCompProdCountryMapperTest {

    private ExportCompProdCountryMapper exportCompProdCountryMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        exportCompProdCountryMapper = new ExportCompProdCountryMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapExportCompProdCountryToExportCompProdCountryDto() {
        ExportCompProdCountry entity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountryDto expectedDto = createSampleExportCompProdCountryDto();

        ExportCompProdCountryDto actualDto = exportCompProdCountryMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapExportCompProdCountryDtoToExportCompProdCountry() {
        ExportCompProdCountryDto dto = createSampleExportCompProdCountryDto();
        ExportCompProdCountry expectedEntity = createSampleExportCompProdCountryEntity();

        ExportCompProdCountry actualEntity = exportCompProdCountryMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapExportCompProdCountryListToExportCompProdCountryDtoList() {
        List<ExportCompProdCountry> entityList = List.of(
                createSampleExportCompProdCountryEntity(),
                createAnotherExportCompProdCountryEntity()
        );
        List<ExportCompProdCountryDto> expectedDtoList = List.of(
                createSampleExportCompProdCountryDto(),
                createAnotherExportCompProdCountryDto()
        );

        List<ExportCompProdCountryDto> actualDtoList = exportCompProdCountryMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapExportCompProdCountryDtoListToExportCompProdCountryList() {
        List<ExportCompProdCountryDto> dtoList = List.of(
                createSampleExportCompProdCountryDto(),
                createAnotherExportCompProdCountryDto()
        );
        List<ExportCompProdCountry> expectedEntityList = List.of(
                createSampleExportCompProdCountryEntity(),
                createAnotherExportCompProdCountryEntity()
        );

        List<ExportCompProdCountry> actualEntityList = exportCompProdCountryMapper.toEntityList(dtoList);

        assertThat(actualEntityList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedEntityList);
    }

    /**
     * Verifies that partialUpdate replaces every non-null mapped field,
     * preserves null-patched fields from the original entity,
     * and never changes primary key fields.
     */
    @Test
    void shouldApplyFullPartialUpdateForExportCompProdCountry() {
        ExportCompProdCountry originalEntity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountry actualEntity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountryDto patchDto = createPatchExportCompProdCountryDto();
        ExportCompProdCountry patchEntity = exportCompProdCountryMapper.toEntity(patchDto);

        exportCompProdCountryMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object exportCompanyExpectedValue = patchEntity.getExportCompany() != null ? patchEntity.getExportCompany() : originalEntity.getExportCompany();
        assertThat(actualEntity.getExportCompany())
                .usingRecursiveComparison()
                .isEqualTo(exportCompanyExpectedValue);

        Object countryExpectedValue = patchEntity.getCountry() != null ? patchEntity.getCountry() : originalEntity.getCountry();
        assertThat(actualEntity.getCountry())
                .usingRecursiveComparison()
                .isEqualTo(countryExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object expYearExpectedValue = patchEntity.getExpYear() != null ? patchEntity.getExpYear() : originalEntity.getExpYear();
        assertThat(actualEntity.getExpYear())
                .isEqualTo(expYearExpectedValue);

        Object productExpectedValue = patchEntity.getProduct() != null ? patchEntity.getProduct() : originalEntity.getProduct();
        assertThat(actualEntity.getProduct())
                .usingRecursiveComparison()
                .isEqualTo(productExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyExportCompProdCountryDtoListForNullOrEmptyExportCompProdCountryList() {
        assertThat(exportCompProdCountryMapper.toDTOList(null)).isEmpty();
        assertThat(exportCompProdCountryMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyExportCompProdCountryListForNullOrEmptyExportCompProdCountryDtoList() {
        assertThat(exportCompProdCountryMapper.toEntityList(null)).isEmpty();
        assertThat(exportCompProdCountryMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ExportCompProdCountry entity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountry expectedEntity = createSampleExportCompProdCountryEntity();

        exportCompProdCountryMapper.partialUpdate(entity, null);
        exportCompProdCountryMapper.partialUpdate(null, createPatchExportCompProdCountryDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ExportCompProdCountry fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ExportCompProdCountry createSampleExportCompProdCountryEntity() {
        ExportCompProdCountry entity = new ExportCompProdCountry();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        ExportCompany exportCompanyFixture1 = new ExportCompany();
        exportCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        exportCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        exportCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        exportCompanyFixture1.setActive(true);
        exportCompanyFixture1.setEmeCode(100L);
        entity.setExportCompany(exportCompanyFixture1);
        Country countryFixture1 = new Country();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        entity.setCountry(countryFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setExpYear(10);
        Product productFixture1 = new Product();
        productFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        productFixture1.setChamberId(10);
        productFixture1.setChamberProductId(100L);
        productFixture1.setVersion(10);
        productFixture1.setCd("cdValue1");
        productFixture1.setCdGemi("cdGemiValue1");
        productFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        productFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        productFixture1.setParentProductId(100L);
        productFixture1.setRecdeleted(true);
        entity.setProduct(productFixture1);

        return entity;
    }

    /**
     * Creates a populated ExportCompProdCountry fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ExportCompProdCountry createAnotherExportCompProdCountryEntity() {
        ExportCompProdCountry entity = new ExportCompProdCountry();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        ExportCompany exportCompanyFixture2 = new ExportCompany();
        exportCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        exportCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        exportCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        exportCompanyFixture2.setActive(false);
        exportCompanyFixture2.setEmeCode(200L);
        entity.setExportCompany(exportCompanyFixture2);
        Country countryFixture2 = new Country();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        entity.setCountry(countryFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setExpYear(20);
        Product productFixture2 = new Product();
        productFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        productFixture2.setChamberId(20);
        productFixture2.setChamberProductId(200L);
        productFixture2.setVersion(20);
        productFixture2.setCd("cdValue2");
        productFixture2.setCdGemi("cdGemiValue2");
        productFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        productFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        productFixture2.setParentProductId(200L);
        productFixture2.setRecdeleted(false);
        entity.setProduct(productFixture2);

        return entity;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createSampleExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        ExportCompanyDto exportCompanyFixture1 = new ExportCompanyDto();
        exportCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        exportCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        exportCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        exportCompanyFixture1.setActive(true);
        exportCompanyFixture1.setEmeCode(100L);
        dto.setExportCompany(exportCompanyFixture1);
        CountryDto countryFixture1 = new CountryDto();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        dto.setCountry(countryFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setExpYear(10);
        ProductDto productFixture1 = new ProductDto();
        productFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        productFixture1.setChamberId(10);
        productFixture1.setChamberProductId(100L);
        productFixture1.setVersion(10);
        productFixture1.setCd("cdValue1");
        productFixture1.setCdGemi("cdGemiValue1");
        productFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        productFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        productFixture1.setParentProductId(100L);
        productFixture1.setRecdeleted(true);
        dto.setProduct(productFixture1);

        return dto;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createAnotherExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        ExportCompanyDto exportCompanyFixture2 = new ExportCompanyDto();
        exportCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        exportCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        exportCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        exportCompanyFixture2.setActive(false);
        exportCompanyFixture2.setEmeCode(200L);
        dto.setExportCompany(exportCompanyFixture2);
        CountryDto countryFixture2 = new CountryDto();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        dto.setCountry(countryFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setExpYear(20);
        ProductDto productFixture2 = new ProductDto();
        productFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        productFixture2.setChamberId(20);
        productFixture2.setChamberProductId(200L);
        productFixture2.setVersion(20);
        productFixture2.setCd("cdValue2");
        productFixture2.setCdGemi("cdGemiValue2");
        productFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        productFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        productFixture2.setParentProductId(200L);
        productFixture2.setRecdeleted(false);
        dto.setProduct(productFixture2);

        return dto;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createPatchExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        CountryDto countryFixture3 = new CountryDto();
        countryFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        countryFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        countryFixture3.setRecdeleted(true);
        countryFixture3.setChamberId(30);
        countryFixture3.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setCountry(countryFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setExpYear(30);
        ProductDto productFixture3 = new ProductDto();
        productFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        productFixture3.setChamberId(30);
        productFixture3.setVersion(30);
        productFixture3.setCd("cdValue3");
        productFixture3.setCdGemi("cdGemiValue3");
        productFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        productFixture3.setRecdeleted(true);
        dto.setProduct(productFixture3);

        return dto;
    }

}
