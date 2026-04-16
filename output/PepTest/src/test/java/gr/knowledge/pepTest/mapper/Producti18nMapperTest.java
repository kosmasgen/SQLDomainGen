package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Product;
import gr.knowledge.pepTest.entity.Producti18n;
import gr.knowledge.pepTest.entity.Producti18nKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class Producti18nMapperTest {

    private Producti18nMapper producti18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        producti18nMapper = new Producti18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProducti18nToProducti18nDto() {
        Producti18n entity = createSampleProducti18nEntity();
        Producti18nDto expectedDto = createSampleProducti18nDto();

        Producti18nDto actualDto = producti18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProducti18nDtoToProducti18n() {
        Producti18nDto dto = createSampleProducti18nDto();
        Producti18n expectedEntity = createSampleProducti18nEntity();

        Producti18n actualEntity = producti18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProducti18nListToProducti18nDtoList() {
        List<Producti18n> entityList = List.of(
                createSampleProducti18nEntity(),
                createAnotherProducti18nEntity()
        );
        List<Producti18nDto> expectedDtoList = List.of(
                createSampleProducti18nDto(),
                createAnotherProducti18nDto()
        );

        List<Producti18nDto> actualDtoList = producti18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProducti18nDtoListToProducti18nList() {
        List<Producti18nDto> dtoList = List.of(
                createSampleProducti18nDto(),
                createAnotherProducti18nDto()
        );
        List<Producti18n> expectedEntityList = List.of(
                createSampleProducti18nEntity(),
                createAnotherProducti18nEntity()
        );

        List<Producti18n> actualEntityList = producti18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProducti18n() {
        Producti18n originalEntity = createSampleProducti18nEntity();
        Producti18n actualEntity = createSampleProducti18nEntity();
        Producti18nDto patchDto = createPatchProducti18nDto();
        Producti18n patchEntity = producti18nMapper.toEntity(patchDto);

        producti18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object chamberI18nIdExpectedValue = patchEntity.getChamberI18nId() != null ? patchEntity.getChamberI18nId() : originalEntity.getChamberI18nId();
        assertThat(actualEntity.getChamberI18nId())
                .isEqualTo(chamberI18nIdExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object productExpectedValue = patchEntity.getProduct() != null ? patchEntity.getProduct() : originalEntity.getProduct();
        assertThat(actualEntity.getProduct())
                .usingRecursiveComparison()
                .isEqualTo(productExpectedValue);

        Object shortDescriptionExpectedValue = patchEntity.getShortDescription() != null ? patchEntity.getShortDescription() : originalEntity.getShortDescription();
        assertThat(actualEntity.getShortDescription())
                .isEqualTo(shortDescriptionExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProducti18nDtoListForNullOrEmptyProducti18nList() {
        assertThat(producti18nMapper.toDTOList(null)).isEmpty();
        assertThat(producti18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProducti18nListForNullOrEmptyProducti18nDtoList() {
        assertThat(producti18nMapper.toEntityList(null)).isEmpty();
        assertThat(producti18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Producti18n entity = createSampleProducti18nEntity();
        Producti18n expectedEntity = createSampleProducti18nEntity();

        producti18nMapper.partialUpdate(entity, null);
        producti18nMapper.partialUpdate(null, createPatchProducti18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Producti18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Producti18n createSampleProducti18nEntity() {
        Producti18n entity = new Producti18n();
        Producti18nKey idFixture1 = new Producti18nKey();
        entity.setId(idFixture1);
        entity.setVersion(10);
        entity.setDescription("descriptionValue1");
        entity.setChamberI18nId(100L);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
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
        entity.setShortDescription("shortDescriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Producti18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Producti18n createAnotherProducti18nEntity() {
        Producti18n entity = new Producti18n();
        Producti18nKey idFixture2 = new Producti18nKey();
        entity.setId(idFixture2);
        entity.setVersion(20);
        entity.setDescription("descriptionValue2");
        entity.setChamberI18nId(200L);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
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
        entity.setShortDescription("shortDescriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated Producti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createSampleProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        Producti18nKey idFixture1 = new Producti18nKey();
        dto.setId(idFixture1);
        dto.setVersion(10);
        dto.setDescription("descriptionValue1");
        dto.setChamberI18nId(100L);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
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
        dto.setShortDescription("shortDescriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated Producti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createAnotherProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        Producti18nKey idFixture2 = new Producti18nKey();
        dto.setId(idFixture2);
        dto.setVersion(20);
        dto.setDescription("descriptionValue2");
        dto.setChamberI18nId(200L);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
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
        dto.setShortDescription("shortDescriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated Producti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createPatchProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        dto.setVersion(30);
        dto.setChamberI18nId(300L);
        ProductDto productFixture3 = new ProductDto();
        productFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        productFixture3.setChamberId(30);
        productFixture3.setVersion(30);
        productFixture3.setCd("cdValue3");
        productFixture3.setCdGemi("cdGemiValue3");
        productFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        productFixture3.setRecdeleted(true);
        dto.setProduct(productFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
