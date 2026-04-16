package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nKey;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ChamberDepartmenti18nMapperTest {

    private ChamberDepartmenti18nMapper chamberDepartmenti18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        chamberDepartmenti18nMapper = new ChamberDepartmenti18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapChamberDepartmenti18nToChamberDepartmenti18nDto() {
        ChamberDepartmenti18n entity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18nDto expectedDto = createSampleChamberDepartmenti18nDto();

        ChamberDepartmenti18nDto actualDto = chamberDepartmenti18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapChamberDepartmenti18nDtoToChamberDepartmenti18n() {
        ChamberDepartmenti18nDto dto = createSampleChamberDepartmenti18nDto();
        ChamberDepartmenti18n expectedEntity = createSampleChamberDepartmenti18nEntity();

        ChamberDepartmenti18n actualEntity = chamberDepartmenti18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapChamberDepartmenti18nListToChamberDepartmenti18nDtoList() {
        List<ChamberDepartmenti18n> entityList = List.of(
                createSampleChamberDepartmenti18nEntity(),
                createAnotherChamberDepartmenti18nEntity()
        );
        List<ChamberDepartmenti18nDto> expectedDtoList = List.of(
                createSampleChamberDepartmenti18nDto(),
                createAnotherChamberDepartmenti18nDto()
        );

        List<ChamberDepartmenti18nDto> actualDtoList = chamberDepartmenti18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapChamberDepartmenti18nDtoListToChamberDepartmenti18nList() {
        List<ChamberDepartmenti18nDto> dtoList = List.of(
                createSampleChamberDepartmenti18nDto(),
                createAnotherChamberDepartmenti18nDto()
        );
        List<ChamberDepartmenti18n> expectedEntityList = List.of(
                createSampleChamberDepartmenti18nEntity(),
                createAnotherChamberDepartmenti18nEntity()
        );

        List<ChamberDepartmenti18n> actualEntityList = chamberDepartmenti18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForChamberDepartmenti18n() {
        ChamberDepartmenti18n originalEntity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18n actualEntity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18nDto patchDto = createPatchChamberDepartmenti18nDto();
        ChamberDepartmenti18n patchEntity = chamberDepartmenti18nMapper.toEntity(patchDto);

        chamberDepartmenti18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object departmentExpectedValue = patchEntity.getDepartment() != null ? patchEntity.getDepartment() : originalEntity.getDepartment();
        assertThat(actualEntity.getDepartment())
                .usingRecursiveComparison()
                .isEqualTo(departmentExpectedValue);

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
    void shouldReturnEmptyChamberDepartmenti18nDtoListForNullOrEmptyChamberDepartmenti18nList() {
        assertThat(chamberDepartmenti18nMapper.toDTOList(null)).isEmpty();
        assertThat(chamberDepartmenti18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChamberDepartmenti18nListForNullOrEmptyChamberDepartmenti18nDtoList() {
        assertThat(chamberDepartmenti18nMapper.toEntityList(null)).isEmpty();
        assertThat(chamberDepartmenti18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ChamberDepartmenti18n entity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18n expectedEntity = createSampleChamberDepartmenti18nEntity();

        chamberDepartmenti18nMapper.partialUpdate(entity, null);
        chamberDepartmenti18nMapper.partialUpdate(null, createPatchChamberDepartmenti18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ChamberDepartmenti18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartmenti18n createSampleChamberDepartmenti18nEntity() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();
        ChamberDepartmenti18nKey idFixture1 = new ChamberDepartmenti18nKey();
        entity.setId(idFixture1);
        ChamberDepartment departmentFixture1 = new ChamberDepartment();
        departmentFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        departmentFixture1.setChamberDepartmentId(10);
        departmentFixture1.setChamberId(10);
        departmentFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        departmentFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        departmentFixture1.setRecdeleted(true);
        departmentFixture1.setCd("cdValue1");
        entity.setDepartment(departmentFixture1);
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
     * Creates a populated ChamberDepartmenti18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartmenti18n createAnotherChamberDepartmenti18nEntity() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();
        ChamberDepartmenti18nKey idFixture2 = new ChamberDepartmenti18nKey();
        entity.setId(idFixture2);
        ChamberDepartment departmentFixture2 = new ChamberDepartment();
        departmentFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        departmentFixture2.setChamberDepartmentId(20);
        departmentFixture2.setChamberId(20);
        departmentFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        departmentFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        departmentFixture2.setRecdeleted(false);
        departmentFixture2.setCd("cdValue2");
        entity.setDepartment(departmentFixture2);
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
     * Creates a populated ChamberDepartmenti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createSampleChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        ChamberDepartmenti18nKey idFixture1 = new ChamberDepartmenti18nKey();
        dto.setId(idFixture1);
        ChamberDepartmentDto departmentFixture1 = new ChamberDepartmentDto();
        departmentFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        departmentFixture1.setChamberDepartmentId(10);
        departmentFixture1.setChamberId(10);
        departmentFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        departmentFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        departmentFixture1.setRecdeleted(true);
        departmentFixture1.setCd("cdValue1");
        dto.setDepartment(departmentFixture1);
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
     * Creates a populated ChamberDepartmenti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createAnotherChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        ChamberDepartmenti18nKey idFixture2 = new ChamberDepartmenti18nKey();
        dto.setId(idFixture2);
        ChamberDepartmentDto departmentFixture2 = new ChamberDepartmentDto();
        departmentFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        departmentFixture2.setChamberDepartmentId(20);
        departmentFixture2.setChamberId(20);
        departmentFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        departmentFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        departmentFixture2.setRecdeleted(false);
        departmentFixture2.setCd("cdValue2");
        dto.setDepartment(departmentFixture2);
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
     * Creates a populated ChamberDepartmenti18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createPatchChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        ChamberDepartmentDto departmentFixture3 = new ChamberDepartmentDto();
        departmentFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        departmentFixture3.setChamberDepartmentId(30);
        departmentFixture3.setChamberId(30);
        departmentFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        departmentFixture3.setRecdeleted(true);
        departmentFixture3.setCd("cdValue3");
        dto.setDepartment(departmentFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(30);

        return dto;
    }

}
