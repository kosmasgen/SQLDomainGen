package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.entity.MunicipalityI18n;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class MunicipalityI18nMapperTest {

    private MunicipalityI18nMapper municipalityI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        municipalityI18nMapper = new MunicipalityI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapMunicipalityI18nToMunicipalityI18nDto() {
        MunicipalityI18n entity = createSampleMunicipalityI18nEntity();
        MunicipalityI18nDto expectedDto = createSampleMunicipalityI18nDto();

        MunicipalityI18nDto actualDto = municipalityI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapMunicipalityI18nDtoToMunicipalityI18n() {
        MunicipalityI18nDto dto = createSampleMunicipalityI18nDto();
        MunicipalityI18n expectedEntity = createSampleMunicipalityI18nEntity();

        MunicipalityI18n actualEntity = municipalityI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapMunicipalityI18nListToMunicipalityI18nDtoList() {
        List<MunicipalityI18n> entityList = List.of(
                createSampleMunicipalityI18nEntity(),
                createAnotherMunicipalityI18nEntity()
        );
        List<MunicipalityI18nDto> expectedDtoList = List.of(
                createSampleMunicipalityI18nDto(),
                createAnotherMunicipalityI18nDto()
        );

        List<MunicipalityI18nDto> actualDtoList = municipalityI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapMunicipalityI18nDtoListToMunicipalityI18nList() {
        List<MunicipalityI18nDto> dtoList = List.of(
                createSampleMunicipalityI18nDto(),
                createAnotherMunicipalityI18nDto()
        );
        List<MunicipalityI18n> expectedEntityList = List.of(
                createSampleMunicipalityI18nEntity(),
                createAnotherMunicipalityI18nEntity()
        );

        List<MunicipalityI18n> actualEntityList = municipalityI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForMunicipalityI18n() {
        MunicipalityI18n originalEntity = createSampleMunicipalityI18nEntity();
        MunicipalityI18n actualEntity = createSampleMunicipalityI18nEntity();
        MunicipalityI18nDto patchDto = createPatchMunicipalityI18nDto();
        MunicipalityI18n patchEntity = municipalityI18nMapper.toEntity(patchDto);

        municipalityI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object municipalityExpectedValue = patchEntity.getMunicipality() != null ? patchEntity.getMunicipality() : originalEntity.getMunicipality();
        assertThat(actualEntity.getMunicipality())
                .usingRecursiveComparison()
                .isEqualTo(municipalityExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object chamberI18nIdExpectedValue = patchEntity.getChamberI18nId() != null ? patchEntity.getChamberI18nId() : originalEntity.getChamberI18nId();
        assertThat(actualEntity.getChamberI18nId())
                .isEqualTo(chamberI18nIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyMunicipalityI18nDtoListForNullOrEmptyMunicipalityI18nList() {
        assertThat(municipalityI18nMapper.toDTOList(null)).isEmpty();
        assertThat(municipalityI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyMunicipalityI18nListForNullOrEmptyMunicipalityI18nDtoList() {
        assertThat(municipalityI18nMapper.toEntityList(null)).isEmpty();
        assertThat(municipalityI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        MunicipalityI18n entity = createSampleMunicipalityI18nEntity();
        MunicipalityI18n expectedEntity = createSampleMunicipalityI18nEntity();

        municipalityI18nMapper.partialUpdate(entity, null);
        municipalityI18nMapper.partialUpdate(null, createPatchMunicipalityI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated MunicipalityI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private MunicipalityI18n createSampleMunicipalityI18nEntity() {
        MunicipalityI18n entity = new MunicipalityI18n();
        MunicipalityI18nKey idFixture1 = new MunicipalityI18nKey();
        entity.setId(idFixture1);
        Municipality municipalityFixture1 = new Municipality();
        municipalityFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        municipalityFixture1.setChamberId(100L);
        municipalityFixture1.setChamberMunicipalityId(100L);
        municipalityFixture1.setDescription("descriptionValue1");
        municipalityFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        municipalityFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        municipalityFixture1.setRecdeleted(true);
        municipalityFixture1.setCd("cdValue1");
        municipalityFixture1.setIsProteasData(true);
        entity.setMunicipality(municipalityFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setChamberI18nId(10);

        return entity;
    }

    /**
     * Creates a populated MunicipalityI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private MunicipalityI18n createAnotherMunicipalityI18nEntity() {
        MunicipalityI18n entity = new MunicipalityI18n();
        MunicipalityI18nKey idFixture2 = new MunicipalityI18nKey();
        entity.setId(idFixture2);
        Municipality municipalityFixture2 = new Municipality();
        municipalityFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        municipalityFixture2.setChamberId(200L);
        municipalityFixture2.setChamberMunicipalityId(200L);
        municipalityFixture2.setDescription("descriptionValue2");
        municipalityFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        municipalityFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        municipalityFixture2.setRecdeleted(false);
        municipalityFixture2.setCd("cdValue2");
        municipalityFixture2.setIsProteasData(false);
        entity.setMunicipality(municipalityFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setChamberI18nId(20);

        return entity;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createSampleMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        MunicipalityI18nKey idFixture1 = new MunicipalityI18nKey();
        dto.setId(idFixture1);
        MunicipalityDto municipalityFixture1 = new MunicipalityDto();
        municipalityFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        municipalityFixture1.setChamberId(100L);
        municipalityFixture1.setChamberMunicipalityId(100L);
        municipalityFixture1.setDescription("descriptionValue1");
        municipalityFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        municipalityFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        municipalityFixture1.setRecdeleted(true);
        municipalityFixture1.setCd("cdValue1");
        municipalityFixture1.setIsProteasData(true);
        dto.setMunicipality(municipalityFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(10);

        return dto;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createAnotherMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        MunicipalityI18nKey idFixture2 = new MunicipalityI18nKey();
        dto.setId(idFixture2);
        MunicipalityDto municipalityFixture2 = new MunicipalityDto();
        municipalityFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        municipalityFixture2.setChamberId(200L);
        municipalityFixture2.setChamberMunicipalityId(200L);
        municipalityFixture2.setDescription("descriptionValue2");
        municipalityFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        municipalityFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        municipalityFixture2.setRecdeleted(false);
        municipalityFixture2.setCd("cdValue2");
        municipalityFixture2.setIsProteasData(false);
        dto.setMunicipality(municipalityFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setChamberI18nId(20);

        return dto;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createPatchMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(30);

        return dto;
    }

}
