package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Municipality;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyBgCooperationI18nMapperTest {

    private CompanyBgCooperationI18nMapper companyBgCooperationI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyBgCooperationI18nMapper = new CompanyBgCooperationI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyBgCooperationI18nToCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18n entity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18nDto expectedDto = createSampleCompanyBgCooperationI18nDto();

        CompanyBgCooperationI18nDto actualDto = companyBgCooperationI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyBgCooperationI18nDtoToCompanyBgCooperationI18n() {
        CompanyBgCooperationI18nDto dto = createSampleCompanyBgCooperationI18nDto();
        CompanyBgCooperationI18n expectedEntity = createSampleCompanyBgCooperationI18nEntity();

        CompanyBgCooperationI18n actualEntity = companyBgCooperationI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyBgCooperationI18nListToCompanyBgCooperationI18nDtoList() {
        List<CompanyBgCooperationI18n> entityList = List.of(
                createSampleCompanyBgCooperationI18nEntity(),
                createAnotherCompanyBgCooperationI18nEntity()
        );
        List<CompanyBgCooperationI18nDto> expectedDtoList = List.of(
                createSampleCompanyBgCooperationI18nDto(),
                createAnotherCompanyBgCooperationI18nDto()
        );

        List<CompanyBgCooperationI18nDto> actualDtoList = companyBgCooperationI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyBgCooperationI18nDtoListToCompanyBgCooperationI18nList() {
        List<CompanyBgCooperationI18nDto> dtoList = List.of(
                createSampleCompanyBgCooperationI18nDto(),
                createAnotherCompanyBgCooperationI18nDto()
        );
        List<CompanyBgCooperationI18n> expectedEntityList = List.of(
                createSampleCompanyBgCooperationI18nEntity(),
                createAnotherCompanyBgCooperationI18nEntity()
        );

        List<CompanyBgCooperationI18n> actualEntityList = companyBgCooperationI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyBgCooperationI18n() {
        CompanyBgCooperationI18n originalEntity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18n actualEntity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18nDto patchDto = createPatchCompanyBgCooperationI18nDto();
        CompanyBgCooperationI18n patchEntity = companyBgCooperationI18nMapper.toEntity(patchDto);

        companyBgCooperationI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

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

        Object cooperationExpectedValue = patchEntity.getCooperation() != null ? patchEntity.getCooperation() : originalEntity.getCooperation();
        assertThat(actualEntity.getCooperation())
                .usingRecursiveComparison()
                .isEqualTo(cooperationExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyBgCooperationI18nDtoListForNullOrEmptyCompanyBgCooperationI18nList() {
        assertThat(companyBgCooperationI18nMapper.toDTOList(null)).isEmpty();
        assertThat(companyBgCooperationI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyBgCooperationI18nListForNullOrEmptyCompanyBgCooperationI18nDtoList() {
        assertThat(companyBgCooperationI18nMapper.toEntityList(null)).isEmpty();
        assertThat(companyBgCooperationI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyBgCooperationI18n entity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18n expectedEntity = createSampleCompanyBgCooperationI18nEntity();

        companyBgCooperationI18nMapper.partialUpdate(entity, null);
        companyBgCooperationI18nMapper.partialUpdate(null, createPatchCompanyBgCooperationI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyBgCooperationI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperationI18n createSampleCompanyBgCooperationI18nEntity() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        CompanyBgCooperation cooperationFixture1 = new CompanyBgCooperation();
        cooperationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        cooperationFixture1.setChamberId(10);
        cooperationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        cooperationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        cooperationFixture1.setRecdeleted(true);
        cooperationFixture1.setCooperationStatus("cooperationStatusValue1");
        entity.setCooperation(cooperationFixture1);
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
     * Creates a populated CompanyBgCooperationI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperationI18n createAnotherCompanyBgCooperationI18nEntity() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        CompanyBgCooperation cooperationFixture2 = new CompanyBgCooperation();
        cooperationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        cooperationFixture2.setChamberId(20);
        cooperationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        cooperationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        cooperationFixture2.setRecdeleted(false);
        cooperationFixture2.setCooperationStatus("cooperationStatusValue2");
        entity.setCooperation(cooperationFixture2);
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
     * Creates a populated CompanyBgCooperationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createSampleCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        CompanyBgCooperationDto cooperationFixture1 = new CompanyBgCooperationDto();
        cooperationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        cooperationFixture1.setChamberId(10);
        cooperationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        cooperationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        cooperationFixture1.setRecdeleted(true);
        cooperationFixture1.setCooperationStatus("cooperationStatusValue1");
        dto.setCooperation(cooperationFixture1);
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
     * Creates a populated CompanyBgCooperationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createAnotherCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        CompanyBgCooperationDto cooperationFixture2 = new CompanyBgCooperationDto();
        cooperationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        cooperationFixture2.setChamberId(20);
        cooperationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        cooperationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        cooperationFixture2.setRecdeleted(false);
        cooperationFixture2.setCooperationStatus("cooperationStatusValue2");
        dto.setCooperation(cooperationFixture2);
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
     * Creates a populated CompanyBgCooperationI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createPatchCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        CompanyBgCooperationDto cooperationFixture3 = new CompanyBgCooperationDto();
        cooperationFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        cooperationFixture3.setChamberId(30);
        cooperationFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        cooperationFixture3.setRecdeleted(true);
        dto.setCooperation(cooperationFixture3);

        return dto;
    }

}
