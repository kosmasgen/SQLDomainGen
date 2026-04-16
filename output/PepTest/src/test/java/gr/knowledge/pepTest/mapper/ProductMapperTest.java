package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.entity.Product;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        productMapper = new ProductMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProductToProductDto() {
        Product entity = createSampleProductEntity();
        ProductDto expectedDto = createSampleProductDto();

        ProductDto actualDto = productMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProductDtoToProduct() {
        ProductDto dto = createSampleProductDto();
        Product expectedEntity = createSampleProductEntity();

        Product actualEntity = productMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProductListToProductDtoList() {
        List<Product> entityList = List.of(
                createSampleProductEntity(),
                createAnotherProductEntity()
        );
        List<ProductDto> expectedDtoList = List.of(
                createSampleProductDto(),
                createAnotherProductDto()
        );

        List<ProductDto> actualDtoList = productMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProductDtoListToProductList() {
        List<ProductDto> dtoList = List.of(
                createSampleProductDto(),
                createAnotherProductDto()
        );
        List<Product> expectedEntityList = List.of(
                createSampleProductEntity(),
                createAnotherProductEntity()
        );

        List<Product> actualEntityList = productMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProduct() {
        Product originalEntity = createSampleProductEntity();
        Product actualEntity = createSampleProductEntity();
        ProductDto patchDto = createPatchProductDto();
        Product patchEntity = productMapper.toEntity(patchDto);

        productMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberProductIdExpectedValue = patchEntity.getChamberProductId() != null ? patchEntity.getChamberProductId() : originalEntity.getChamberProductId();
        assertThat(actualEntity.getChamberProductId())
                .isEqualTo(chamberProductIdExpectedValue);

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object cdGemiExpectedValue = patchEntity.getCdGemi() != null ? patchEntity.getCdGemi() : originalEntity.getCdGemi();
        assertThat(actualEntity.getCdGemi())
                .isEqualTo(cdGemiExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object parentProductIdExpectedValue = patchEntity.getParentProductId() != null ? patchEntity.getParentProductId() : originalEntity.getParentProductId();
        assertThat(actualEntity.getParentProductId())
                .isEqualTo(parentProductIdExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProductDtoListForNullOrEmptyProductList() {
        assertThat(productMapper.toDTOList(null)).isEmpty();
        assertThat(productMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProductListForNullOrEmptyProductDtoList() {
        assertThat(productMapper.toEntityList(null)).isEmpty();
        assertThat(productMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Product entity = createSampleProductEntity();
        Product expectedEntity = createSampleProductEntity();

        productMapper.partialUpdate(entity, null);
        productMapper.partialUpdate(null, createPatchProductDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Product fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Product createSampleProductEntity() {
        Product entity = new Product();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberProductId(100L);
        entity.setVersion(10);
        entity.setCd("cdValue1");
        entity.setCdGemi("cdGemiValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setParentProductId(100L);
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Product fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Product createAnotherProductEntity() {
        Product entity = new Product();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberProductId(200L);
        entity.setVersion(20);
        entity.setCd("cdValue2");
        entity.setCdGemi("cdGemiValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setParentProductId(200L);
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProductDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createSampleProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberProductId(100L);
        dto.setVersion(10);
        dto.setCd("cdValue1");
        dto.setCdGemi("cdGemiValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setParentProductId(100L);
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProductDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createAnotherProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberProductId(200L);
        dto.setVersion(20);
        dto.setCd("cdValue2");
        dto.setCdGemi("cdGemiValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setParentProductId(200L);
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProductDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createPatchProductDto() {
        ProductDto dto = new ProductDto();
        dto.setChamberId(30);
        dto.setVersion(30);
        dto.setCd("cdValue3");
        dto.setCdGemi("cdGemiValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
