package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import gr.knowledge.pepTest.entity.CompanyStatusi18nKey;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyStatusi18nMapperTest {

    private CompanyStatusi18nMapper companyStatusi18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyStatusi18nMapper = new CompanyStatusi18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusi18nToCompanyStatusi18nDto() {
        CompanyStatusi18n entity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18nDto expectedDto = createSampleCompanyStatusi18nDto();

        CompanyStatusi18nDto actualDto = companyStatusi18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusi18nDtoToCompanyStatusi18n() {
        CompanyStatusi18nDto dto = createSampleCompanyStatusi18nDto();
        CompanyStatusi18n expectedEntity = createSampleCompanyStatusi18nEntity();

        CompanyStatusi18n actualEntity = companyStatusi18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyStatusi18nListToCompanyStatusi18nDtoList() {
        List<CompanyStatusi18n> entityList = List.of(
                createSampleCompanyStatusi18nEntity(),
                createAnotherCompanyStatusi18nEntity()
        );
        List<CompanyStatusi18nDto> expectedDtoList = List.of(
                createSampleCompanyStatusi18nDto(),
                createAnotherCompanyStatusi18nDto()
        );

        List<CompanyStatusi18nDto> actualDtoList = companyStatusi18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyStatusi18nDtoListToCompanyStatusi18nList() {
        List<CompanyStatusi18nDto> dtoList = List.of(
                createSampleCompanyStatusi18nDto(),
                createAnotherCompanyStatusi18nDto()
        );
        List<CompanyStatusi18n> expectedEntityList = List.of(
                createSampleCompanyStatusi18nEntity(),
                createAnotherCompanyStatusi18nEntity()
        );

        List<CompanyStatusi18n> actualEntityList = companyStatusi18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyStatusi18n() {
        CompanyStatusi18n originalEntity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18n actualEntity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18nDto patchDto = createPatchCompanyStatusi18nDto();
        CompanyStatusi18n patchEntity = companyStatusi18nMapper.toEntity(patchDto);

        companyStatusi18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object companyStatusExpectedValue = patchEntity.getCompanyStatus() != null ? patchEntity.getCompanyStatus() : originalEntity.getCompanyStatus();
        assertThat(actualEntity.getCompanyStatus())
                .usingRecursiveComparison()
                .isEqualTo(companyStatusExpectedValue);

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
    void shouldReturnEmptyCompanyStatusi18nDtoListForNullOrEmptyCompanyStatusi18nList() {
        assertThat(companyStatusi18nMapper.toDTOList(null)).isEmpty();
        assertThat(companyStatusi18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyStatusi18nListForNullOrEmptyCompanyStatusi18nDtoList() {
        assertThat(companyStatusi18nMapper.toEntityList(null)).isEmpty();
        assertThat(companyStatusi18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyStatusi18n entity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18n expectedEntity = createSampleCompanyStatusi18nEntity();

        companyStatusi18nMapper.partialUpdate(entity, null);
        companyStatusi18nMapper.partialUpdate(null, createPatchCompanyStatusi18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyStatusi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusi18n createSampleCompanyStatusi18nEntity() {
        CompanyStatusi18n entity = new CompanyStatusi18n();
        CompanyStatusi18nKey idFixture1 = new CompanyStatusi18nKey();
        entity.setId(idFixture1);
        CompanyStatus companyStatusFixture1 = new CompanyStatus();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        entity.setCompanyStatus(companyStatusFixture1);
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
     * Creates a populated CompanyStatusi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusi18n createAnotherCompanyStatusi18nEntity() {
        CompanyStatusi18n entity = new CompanyStatusi18n();
        CompanyStatusi18nKey idFixture2 = new CompanyStatusi18nKey();
        entity.setId(idFixture2);
        CompanyStatus companyStatusFixture2 = new CompanyStatus();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        entity.setCompanyStatus(companyStatusFixture2);
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
     * Creates a populated CompanyStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createSampleCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        CompanyStatusi18nKey idFixture1 = new CompanyStatusi18nKey();
        dto.setId(idFixture1);
        CompanyStatusDto companyStatusFixture1 = new CompanyStatusDto();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        dto.setCompanyStatus(companyStatusFixture1);
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
     * Creates a populated CompanyStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createAnotherCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        CompanyStatusi18nKey idFixture2 = new CompanyStatusi18nKey();
        dto.setId(idFixture2);
        CompanyStatusDto companyStatusFixture2 = new CompanyStatusDto();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        dto.setCompanyStatus(companyStatusFixture2);
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
     * Creates a populated CompanyStatusi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createPatchCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(30);

        return dto;
    }

}
