package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyTitle;
import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import gr.knowledge.pepTest.entity.CompanyTitlei18nKey;
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

class CompanyTitlei18nMapperTest {

    private CompanyTitlei18nMapper companyTitlei18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyTitlei18nMapper = new CompanyTitlei18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyTitlei18nToCompanyTitlei18nDto() {
        CompanyTitlei18n entity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18nDto expectedDto = createSampleCompanyTitlei18nDto();

        CompanyTitlei18nDto actualDto = companyTitlei18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyTitlei18nDtoToCompanyTitlei18n() {
        CompanyTitlei18nDto dto = createSampleCompanyTitlei18nDto();
        CompanyTitlei18n expectedEntity = createSampleCompanyTitlei18nEntity();

        CompanyTitlei18n actualEntity = companyTitlei18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyTitlei18nListToCompanyTitlei18nDtoList() {
        List<CompanyTitlei18n> entityList = List.of(
                createSampleCompanyTitlei18nEntity(),
                createAnotherCompanyTitlei18nEntity()
        );
        List<CompanyTitlei18nDto> expectedDtoList = List.of(
                createSampleCompanyTitlei18nDto(),
                createAnotherCompanyTitlei18nDto()
        );

        List<CompanyTitlei18nDto> actualDtoList = companyTitlei18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyTitlei18nDtoListToCompanyTitlei18nList() {
        List<CompanyTitlei18nDto> dtoList = List.of(
                createSampleCompanyTitlei18nDto(),
                createAnotherCompanyTitlei18nDto()
        );
        List<CompanyTitlei18n> expectedEntityList = List.of(
                createSampleCompanyTitlei18nEntity(),
                createAnotherCompanyTitlei18nEntity()
        );

        List<CompanyTitlei18n> actualEntityList = companyTitlei18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyTitlei18n() {
        CompanyTitlei18n originalEntity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18n actualEntity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18nDto patchDto = createPatchCompanyTitlei18nDto();
        CompanyTitlei18n patchEntity = companyTitlei18nMapper.toEntity(patchDto);

        companyTitlei18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object companyTitleExpectedValue = patchEntity.getCompanyTitle() != null ? patchEntity.getCompanyTitle() : originalEntity.getCompanyTitle();
        assertThat(actualEntity.getCompanyTitle())
                .usingRecursiveComparison()
                .isEqualTo(companyTitleExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

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
    void shouldReturnEmptyCompanyTitlei18nDtoListForNullOrEmptyCompanyTitlei18nList() {
        assertThat(companyTitlei18nMapper.toDTOList(null)).isEmpty();
        assertThat(companyTitlei18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyTitlei18nListForNullOrEmptyCompanyTitlei18nDtoList() {
        assertThat(companyTitlei18nMapper.toEntityList(null)).isEmpty();
        assertThat(companyTitlei18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyTitlei18n entity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18n expectedEntity = createSampleCompanyTitlei18nEntity();

        companyTitlei18nMapper.partialUpdate(entity, null);
        companyTitlei18nMapper.partialUpdate(null, createPatchCompanyTitlei18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyTitlei18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitlei18n createSampleCompanyTitlei18nEntity() {
        CompanyTitlei18n entity = new CompanyTitlei18n();
        CompanyTitlei18nKey idFixture1 = new CompanyTitlei18nKey();
        entity.setId(idFixture1);
        CompanyTitle companyTitleFixture1 = new CompanyTitle();
        companyTitleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyTitleFixture1.setChamberId(10);
        companyTitleFixture1.setTitle("titleValue1");
        companyTitleFixture1.setChamberTitleId(new BigInteger("1000"));
        companyTitleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setRecdeleted(true);
        entity.setCompanyTitle(companyTitleFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setTitle("titleValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyTitlei18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitlei18n createAnotherCompanyTitlei18nEntity() {
        CompanyTitlei18n entity = new CompanyTitlei18n();
        CompanyTitlei18nKey idFixture2 = new CompanyTitlei18nKey();
        entity.setId(idFixture2);
        CompanyTitle companyTitleFixture2 = new CompanyTitle();
        companyTitleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyTitleFixture2.setChamberId(20);
        companyTitleFixture2.setTitle("titleValue2");
        companyTitleFixture2.setChamberTitleId(new BigInteger("2000"));
        companyTitleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setRecdeleted(false);
        entity.setCompanyTitle(companyTitleFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setTitle("titleValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createSampleCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        CompanyTitlei18nKey idFixture1 = new CompanyTitlei18nKey();
        dto.setId(idFixture1);
        CompanyTitleDto companyTitleFixture1 = new CompanyTitleDto();
        companyTitleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyTitleFixture1.setChamberId(10);
        companyTitleFixture1.setTitle("titleValue1");
        companyTitleFixture1.setChamberTitleId(new BigInteger("1000"));
        companyTitleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setRecdeleted(true);
        dto.setCompanyTitle(companyTitleFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setTitle("titleValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createAnotherCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        CompanyTitlei18nKey idFixture2 = new CompanyTitlei18nKey();
        dto.setId(idFixture2);
        CompanyTitleDto companyTitleFixture2 = new CompanyTitleDto();
        companyTitleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyTitleFixture2.setChamberId(20);
        companyTitleFixture2.setTitle("titleValue2");
        companyTitleFixture2.setChamberTitleId(new BigInteger("2000"));
        companyTitleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setRecdeleted(false);
        dto.setCompanyTitle(companyTitleFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setTitle("titleValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createPatchCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
