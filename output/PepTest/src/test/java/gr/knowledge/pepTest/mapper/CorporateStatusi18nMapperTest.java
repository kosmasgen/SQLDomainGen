package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import gr.knowledge.pepTest.entity.CorporateStatusi18nKey;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CorporateStatusi18nMapperTest {

    private CorporateStatusi18nMapper corporateStatusi18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        corporateStatusi18nMapper = new CorporateStatusi18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusi18nToCorporateStatusi18nDto() {
        CorporateStatusi18n entity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18nDto expectedDto = createSampleCorporateStatusi18nDto();

        CorporateStatusi18nDto actualDto = corporateStatusi18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusi18nDtoToCorporateStatusi18n() {
        CorporateStatusi18nDto dto = createSampleCorporateStatusi18nDto();
        CorporateStatusi18n expectedEntity = createSampleCorporateStatusi18nEntity();

        CorporateStatusi18n actualEntity = corporateStatusi18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCorporateStatusi18nListToCorporateStatusi18nDtoList() {
        List<CorporateStatusi18n> entityList = List.of(
                createSampleCorporateStatusi18nEntity(),
                createAnotherCorporateStatusi18nEntity()
        );
        List<CorporateStatusi18nDto> expectedDtoList = List.of(
                createSampleCorporateStatusi18nDto(),
                createAnotherCorporateStatusi18nDto()
        );

        List<CorporateStatusi18nDto> actualDtoList = corporateStatusi18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCorporateStatusi18nDtoListToCorporateStatusi18nList() {
        List<CorporateStatusi18nDto> dtoList = List.of(
                createSampleCorporateStatusi18nDto(),
                createAnotherCorporateStatusi18nDto()
        );
        List<CorporateStatusi18n> expectedEntityList = List.of(
                createSampleCorporateStatusi18nEntity(),
                createAnotherCorporateStatusi18nEntity()
        );

        List<CorporateStatusi18n> actualEntityList = corporateStatusi18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCorporateStatusi18n() {
        CorporateStatusi18n originalEntity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18n actualEntity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18nDto patchDto = createPatchCorporateStatusi18nDto();
        CorporateStatusi18n patchEntity = corporateStatusi18nMapper.toEntity(patchDto);

        corporateStatusi18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object corporateStatusExpectedValue = patchEntity.getCorporateStatus() != null ? patchEntity.getCorporateStatus() : originalEntity.getCorporateStatus();
        assertThat(actualEntity.getCorporateStatus())
                .usingRecursiveComparison()
                .isEqualTo(corporateStatusExpectedValue);

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

        Object groupedDescriptionExpectedValue = patchEntity.getGroupedDescription() != null ? patchEntity.getGroupedDescription() : originalEntity.getGroupedDescription();
        assertThat(actualEntity.getGroupedDescription())
                .isEqualTo(groupedDescriptionExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCorporateStatusi18nDtoListForNullOrEmptyCorporateStatusi18nList() {
        assertThat(corporateStatusi18nMapper.toDTOList(null)).isEmpty();
        assertThat(corporateStatusi18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCorporateStatusi18nListForNullOrEmptyCorporateStatusi18nDtoList() {
        assertThat(corporateStatusi18nMapper.toEntityList(null)).isEmpty();
        assertThat(corporateStatusi18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CorporateStatusi18n entity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18n expectedEntity = createSampleCorporateStatusi18nEntity();

        corporateStatusi18nMapper.partialUpdate(entity, null);
        corporateStatusi18nMapper.partialUpdate(null, createPatchCorporateStatusi18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CorporateStatusi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusi18n createSampleCorporateStatusi18nEntity() {
        CorporateStatusi18n entity = new CorporateStatusi18n();
        CorporateStatusi18nKey idFixture1 = new CorporateStatusi18nKey();
        entity.setId(idFixture1);
        CorporateStatus corporateStatusFixture1 = new CorporateStatus();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        entity.setCorporateStatus(corporateStatusFixture1);
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
        entity.setGroupedDescription("groupedDescriptionValue1");

        return entity;
    }

    /**
     * Creates a populated CorporateStatusi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusi18n createAnotherCorporateStatusi18nEntity() {
        CorporateStatusi18n entity = new CorporateStatusi18n();
        CorporateStatusi18nKey idFixture2 = new CorporateStatusi18nKey();
        entity.setId(idFixture2);
        CorporateStatus corporateStatusFixture2 = new CorporateStatus();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        entity.setCorporateStatus(corporateStatusFixture2);
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
        entity.setGroupedDescription("groupedDescriptionValue2");

        return entity;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createSampleCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        CorporateStatusi18nKey idFixture1 = new CorporateStatusi18nKey();
        dto.setId(idFixture1);
        CorporateStatusDto corporateStatusFixture1 = new CorporateStatusDto();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture1);
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
        dto.setGroupedDescription("groupedDescriptionValue1");

        return dto;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createAnotherCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        CorporateStatusi18nKey idFixture2 = new CorporateStatusi18nKey();
        dto.setId(idFixture2);
        CorporateStatusDto corporateStatusFixture2 = new CorporateStatusDto();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        dto.setCorporateStatus(corporateStatusFixture2);
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
        dto.setGroupedDescription("groupedDescriptionValue2");

        return dto;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createPatchCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        CorporateStatusDto corporateStatusFixture3 = new CorporateStatusDto();
        corporateStatusFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        corporateStatusFixture3.setChamberCorporateStatusId(30);
        corporateStatusFixture3.setChamberId(30);
        corporateStatusFixture3.setCd("cdValue3");
        corporateStatusFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        corporateStatusFixture3.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(30);
        dto.setGroupedDescription("groupedDescriptionValue3");

        return dto;
    }

}
