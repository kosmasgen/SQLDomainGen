package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import gr.knowledge.pepTest.entity.BusinessLocationI18nKey;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessLocationI18nMapperTest {

    private BusinessLocationI18nMapper businessLocationI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        businessLocationI18nMapper = new BusinessLocationI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapBusinessLocationI18nToBusinessLocationI18nDto() {
        BusinessLocationI18n entity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18nDto expectedDto = createSampleBusinessLocationI18nDto();

        BusinessLocationI18nDto actualDto = businessLocationI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapBusinessLocationI18nDtoToBusinessLocationI18n() {
        BusinessLocationI18nDto dto = createSampleBusinessLocationI18nDto();
        BusinessLocationI18n expectedEntity = createSampleBusinessLocationI18nEntity();

        BusinessLocationI18n actualEntity = businessLocationI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapBusinessLocationI18nListToBusinessLocationI18nDtoList() {
        List<BusinessLocationI18n> entityList = List.of(
                createSampleBusinessLocationI18nEntity(),
                createAnotherBusinessLocationI18nEntity()
        );
        List<BusinessLocationI18nDto> expectedDtoList = List.of(
                createSampleBusinessLocationI18nDto(),
                createAnotherBusinessLocationI18nDto()
        );

        List<BusinessLocationI18nDto> actualDtoList = businessLocationI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapBusinessLocationI18nDtoListToBusinessLocationI18nList() {
        List<BusinessLocationI18nDto> dtoList = List.of(
                createSampleBusinessLocationI18nDto(),
                createAnotherBusinessLocationI18nDto()
        );
        List<BusinessLocationI18n> expectedEntityList = List.of(
                createSampleBusinessLocationI18nEntity(),
                createAnotherBusinessLocationI18nEntity()
        );

        List<BusinessLocationI18n> actualEntityList = businessLocationI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForBusinessLocationI18n() {
        BusinessLocationI18n originalEntity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18n actualEntity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18nDto patchDto = createPatchBusinessLocationI18nDto();
        BusinessLocationI18n patchEntity = businessLocationI18nMapper.toEntity(patchDto);

        businessLocationI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object codeExpectedValue = patchEntity.getCode() != null ? patchEntity.getCode() : originalEntity.getCode();
        assertThat(actualEntity.getCode())
                .isEqualTo(codeExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object businessLocationExpectedValue = patchEntity.getBusinessLocation() != null ? patchEntity.getBusinessLocation() : originalEntity.getBusinessLocation();
        assertThat(actualEntity.getBusinessLocation())
                .usingRecursiveComparison()
                .isEqualTo(businessLocationExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBusinessLocationI18nDtoListForNullOrEmptyBusinessLocationI18nList() {
        assertThat(businessLocationI18nMapper.toDTOList(null)).isEmpty();
        assertThat(businessLocationI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBusinessLocationI18nListForNullOrEmptyBusinessLocationI18nDtoList() {
        assertThat(businessLocationI18nMapper.toEntityList(null)).isEmpty();
        assertThat(businessLocationI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        BusinessLocationI18n entity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18n expectedEntity = createSampleBusinessLocationI18nEntity();

        businessLocationI18nMapper.partialUpdate(entity, null);
        businessLocationI18nMapper.partialUpdate(null, createPatchBusinessLocationI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated BusinessLocationI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocationI18n createSampleBusinessLocationI18nEntity() {
        BusinessLocationI18n entity = new BusinessLocationI18n();
        BusinessLocationI18nKey idFixture1 = new BusinessLocationI18nKey();
        entity.setId(idFixture1);
        entity.setDescription("descriptionValue1");
        entity.setCode("codeValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        BusinessLocation businessLocationFixture1 = new BusinessLocation();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        entity.setBusinessLocation(businessLocationFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);

        return entity;
    }

    /**
     * Creates a populated BusinessLocationI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocationI18n createAnotherBusinessLocationI18nEntity() {
        BusinessLocationI18n entity = new BusinessLocationI18n();
        BusinessLocationI18nKey idFixture2 = new BusinessLocationI18nKey();
        entity.setId(idFixture2);
        entity.setDescription("descriptionValue2");
        entity.setCode("codeValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        BusinessLocation businessLocationFixture2 = new BusinessLocation();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        entity.setBusinessLocation(businessLocationFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);

        return entity;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createSampleBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        BusinessLocationI18nKey idFixture1 = new BusinessLocationI18nKey();
        dto.setId(idFixture1);
        dto.setDescription("descriptionValue1");
        dto.setCode("codeValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        BusinessLocationDto businessLocationFixture1 = new BusinessLocationDto();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        dto.setBusinessLocation(businessLocationFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);

        return dto;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createAnotherBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        BusinessLocationI18nKey idFixture2 = new BusinessLocationI18nKey();
        dto.setId(idFixture2);
        dto.setDescription("descriptionValue2");
        dto.setCode("codeValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        BusinessLocationDto businessLocationFixture2 = new BusinessLocationDto();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        dto.setBusinessLocation(businessLocationFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);

        return dto;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createPatchBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        BusinessLocationDto businessLocationFixture3 = new BusinessLocationDto();
        businessLocationFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        businessLocationFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        businessLocationFixture3.setRecdeleted(true);
        businessLocationFixture3.setBlobUri("blobUriValue3");
        dto.setBusinessLocation(businessLocationFixture3);

        return dto;
    }

}
