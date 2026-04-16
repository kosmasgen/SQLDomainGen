package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nKey;
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

class CompanyYpArticleI18nMapperTest {

    private CompanyYpArticleI18nMapper companyYpArticleI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyYpArticleI18nMapper = new CompanyYpArticleI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyYpArticleI18nToCompanyYpArticleI18nDto() {
        CompanyYpArticleI18n entity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18nDto expectedDto = createSampleCompanyYpArticleI18nDto();

        CompanyYpArticleI18nDto actualDto = companyYpArticleI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyYpArticleI18nDtoToCompanyYpArticleI18n() {
        CompanyYpArticleI18nDto dto = createSampleCompanyYpArticleI18nDto();
        CompanyYpArticleI18n expectedEntity = createSampleCompanyYpArticleI18nEntity();

        CompanyYpArticleI18n actualEntity = companyYpArticleI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyYpArticleI18nListToCompanyYpArticleI18nDtoList() {
        List<CompanyYpArticleI18n> entityList = List.of(
                createSampleCompanyYpArticleI18nEntity(),
                createAnotherCompanyYpArticleI18nEntity()
        );
        List<CompanyYpArticleI18nDto> expectedDtoList = List.of(
                createSampleCompanyYpArticleI18nDto(),
                createAnotherCompanyYpArticleI18nDto()
        );

        List<CompanyYpArticleI18nDto> actualDtoList = companyYpArticleI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyYpArticleI18nDtoListToCompanyYpArticleI18nList() {
        List<CompanyYpArticleI18nDto> dtoList = List.of(
                createSampleCompanyYpArticleI18nDto(),
                createAnotherCompanyYpArticleI18nDto()
        );
        List<CompanyYpArticleI18n> expectedEntityList = List.of(
                createSampleCompanyYpArticleI18nEntity(),
                createAnotherCompanyYpArticleI18nEntity()
        );

        List<CompanyYpArticleI18n> actualEntityList = companyYpArticleI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyYpArticleI18n() {
        CompanyYpArticleI18n originalEntity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18n actualEntity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18nDto patchDto = createPatchCompanyYpArticleI18nDto();
        CompanyYpArticleI18n patchEntity = companyYpArticleI18nMapper.toEntity(patchDto);

        companyYpArticleI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object companyArticleExpectedValue = patchEntity.getCompanyArticle() != null ? patchEntity.getCompanyArticle() : originalEntity.getCompanyArticle();
        assertThat(actualEntity.getCompanyArticle())
                .usingRecursiveComparison()
                .isEqualTo(companyArticleExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

        Object htmlExpectedValue = patchEntity.getHtml() != null ? patchEntity.getHtml() : originalEntity.getHtml();
        assertThat(actualEntity.getHtml())
                .isEqualTo(htmlExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

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
    void shouldReturnEmptyCompanyYpArticleI18nDtoListForNullOrEmptyCompanyYpArticleI18nList() {
        assertThat(companyYpArticleI18nMapper.toDTOList(null)).isEmpty();
        assertThat(companyYpArticleI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyYpArticleI18nListForNullOrEmptyCompanyYpArticleI18nDtoList() {
        assertThat(companyYpArticleI18nMapper.toEntityList(null)).isEmpty();
        assertThat(companyYpArticleI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyYpArticleI18n entity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18n expectedEntity = createSampleCompanyYpArticleI18nEntity();

        companyYpArticleI18nMapper.partialUpdate(entity, null);
        companyYpArticleI18nMapper.partialUpdate(null, createPatchCompanyYpArticleI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyYpArticleI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticleI18n createSampleCompanyYpArticleI18nEntity() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();
        CompanyYpArticleI18nKey idFixture1 = new CompanyYpArticleI18nKey();
        entity.setId(idFixture1);
        CompanyYpArticle companyArticleFixture1 = new CompanyYpArticle();
        companyArticleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyArticleFixture1.setChamberId(10);
        companyArticleFixture1.setTitle("titleValue1");
        companyArticleFixture1.setHtml("htmlValue1");
        companyArticleFixture1.setOrderSeq(10);
        companyArticleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyArticleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyArticleFixture1.setRecdeleted(true);
        companyArticleFixture1.setIsPublished(true);
        entity.setCompanyArticle(companyArticleFixture1);
        entity.setTitle("titleValue1");
        entity.setHtml("htmlValue1");
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticleI18n createAnotherCompanyYpArticleI18nEntity() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();
        CompanyYpArticleI18nKey idFixture2 = new CompanyYpArticleI18nKey();
        entity.setId(idFixture2);
        CompanyYpArticle companyArticleFixture2 = new CompanyYpArticle();
        companyArticleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyArticleFixture2.setChamberId(20);
        companyArticleFixture2.setTitle("titleValue2");
        companyArticleFixture2.setHtml("htmlValue2");
        companyArticleFixture2.setOrderSeq(20);
        companyArticleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyArticleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyArticleFixture2.setRecdeleted(false);
        companyArticleFixture2.setIsPublished(false);
        entity.setCompanyArticle(companyArticleFixture2);
        entity.setTitle("titleValue2");
        entity.setHtml("htmlValue2");
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createSampleCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        CompanyYpArticleI18nKey idFixture1 = new CompanyYpArticleI18nKey();
        dto.setId(idFixture1);
        CompanyYpArticleDto companyArticleFixture1 = new CompanyYpArticleDto();
        companyArticleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyArticleFixture1.setChamberId(10);
        companyArticleFixture1.setTitle("titleValue1");
        companyArticleFixture1.setHtml("htmlValue1");
        companyArticleFixture1.setOrderSeq(10);
        companyArticleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyArticleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyArticleFixture1.setRecdeleted(true);
        companyArticleFixture1.setIsPublished(true);
        dto.setCompanyArticle(companyArticleFixture1);
        dto.setTitle("titleValue1");
        dto.setHtml("htmlValue1");
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createAnotherCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        CompanyYpArticleI18nKey idFixture2 = new CompanyYpArticleI18nKey();
        dto.setId(idFixture2);
        CompanyYpArticleDto companyArticleFixture2 = new CompanyYpArticleDto();
        companyArticleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyArticleFixture2.setChamberId(20);
        companyArticleFixture2.setTitle("titleValue2");
        companyArticleFixture2.setHtml("htmlValue2");
        companyArticleFixture2.setOrderSeq(20);
        companyArticleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyArticleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyArticleFixture2.setRecdeleted(false);
        companyArticleFixture2.setIsPublished(false);
        dto.setCompanyArticle(companyArticleFixture2);
        dto.setTitle("titleValue2");
        dto.setHtml("htmlValue2");
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createPatchCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        dto.setHtml("htmlValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
