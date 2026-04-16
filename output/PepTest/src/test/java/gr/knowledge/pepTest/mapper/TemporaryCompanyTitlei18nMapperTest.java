package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TemporaryCompanyTitlei18nMapperTest {

    private TemporaryCompanyTitlei18nMapper temporaryCompanyTitlei18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        temporaryCompanyTitlei18nMapper = new TemporaryCompanyTitlei18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyTitlei18nToTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18n entity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18nDto expectedDto = createSampleTemporaryCompanyTitlei18nDto();

        TemporaryCompanyTitlei18nDto actualDto = temporaryCompanyTitlei18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyTitlei18nDtoToTemporaryCompanyTitlei18n() {
        TemporaryCompanyTitlei18nDto dto = createSampleTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18n expectedEntity = createSampleTemporaryCompanyTitlei18nEntity();

        TemporaryCompanyTitlei18n actualEntity = temporaryCompanyTitlei18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyTitlei18nListToTemporaryCompanyTitlei18nDtoList() {
        List<TemporaryCompanyTitlei18n> entityList = List.of(
                createSampleTemporaryCompanyTitlei18nEntity(),
                createAnotherTemporaryCompanyTitlei18nEntity()
        );
        List<TemporaryCompanyTitlei18nDto> expectedDtoList = List.of(
                createSampleTemporaryCompanyTitlei18nDto(),
                createAnotherTemporaryCompanyTitlei18nDto()
        );

        List<TemporaryCompanyTitlei18nDto> actualDtoList = temporaryCompanyTitlei18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyTitlei18nDtoListToTemporaryCompanyTitlei18nList() {
        List<TemporaryCompanyTitlei18nDto> dtoList = List.of(
                createSampleTemporaryCompanyTitlei18nDto(),
                createAnotherTemporaryCompanyTitlei18nDto()
        );
        List<TemporaryCompanyTitlei18n> expectedEntityList = List.of(
                createSampleTemporaryCompanyTitlei18nEntity(),
                createAnotherTemporaryCompanyTitlei18nEntity()
        );

        List<TemporaryCompanyTitlei18n> actualEntityList = temporaryCompanyTitlei18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForTemporaryCompanyTitlei18n() {
        TemporaryCompanyTitlei18n originalEntity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18n actualEntity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18nDto patchDto = createPatchTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18n patchEntity = temporaryCompanyTitlei18nMapper.toEntity(patchDto);

        temporaryCompanyTitlei18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object companyTitleExpectedValue = patchEntity.getCompanyTitle() != null ? patchEntity.getCompanyTitle() : originalEntity.getCompanyTitle();
        assertThat(actualEntity.getCompanyTitle())
                .usingRecursiveComparison()
                .isEqualTo(companyTitleExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyTitlei18nDtoListForNullOrEmptyTemporaryCompanyTitlei18nList() {
        assertThat(temporaryCompanyTitlei18nMapper.toDTOList(null)).isEmpty();
        assertThat(temporaryCompanyTitlei18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyTitlei18nListForNullOrEmptyTemporaryCompanyTitlei18nDtoList() {
        assertThat(temporaryCompanyTitlei18nMapper.toEntityList(null)).isEmpty();
        assertThat(temporaryCompanyTitlei18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        TemporaryCompanyTitlei18n entity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18n expectedEntity = createSampleTemporaryCompanyTitlei18nEntity();

        temporaryCompanyTitlei18nMapper.partialUpdate(entity, null);
        temporaryCompanyTitlei18nMapper.partialUpdate(null, createPatchTemporaryCompanyTitlei18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitlei18n createSampleTemporaryCompanyTitlei18nEntity() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();
        entity.setId(new BigInteger("1000"));
        entity.setVersion(new BigInteger("1000"));
        TemporaryCompanyTitle companyTitleFixture1 = new TemporaryCompanyTitle();
        companyTitleFixture1.setId(new BigInteger("1000"));
        companyTitleFixture1.setVersion(new BigInteger("1000"));
        companyTitleFixture1.setCompanyId(new BigInteger("1000"));
        companyTitleFixture1.setCompanyPreregistrationId(new BigInteger("1000"));
        companyTitleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setOrderSeq(new BigInteger("1000"));
        companyTitleFixture1.setRecdeleted(new BigInteger("1000"));
        companyTitleFixture1.setTitle("titleValue1");
        companyTitleFixture1.setTitleLatin("titleLatinValue1");
        companyTitleFixture1.setTitleNrm("titleNrmValue1");
        companyTitleFixture1.setTitleStatusId(new BigInteger("1000"));
        companyTitleFixture1.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setGemiId(new BigInteger("1000"));
        companyTitleFixture1.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        companyTitleFixture1.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        entity.setCompanyTitle(companyTitleFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(new BigInteger("1000"));
        entity.setTitle("titleValue1");
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        entity.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitlei18n createAnotherTemporaryCompanyTitlei18nEntity() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();
        entity.setId(new BigInteger("2000"));
        entity.setVersion(new BigInteger("2000"));
        TemporaryCompanyTitle companyTitleFixture2 = new TemporaryCompanyTitle();
        companyTitleFixture2.setId(new BigInteger("2000"));
        companyTitleFixture2.setVersion(new BigInteger("2000"));
        companyTitleFixture2.setCompanyId(new BigInteger("2000"));
        companyTitleFixture2.setCompanyPreregistrationId(new BigInteger("2000"));
        companyTitleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setOrderSeq(new BigInteger("2000"));
        companyTitleFixture2.setRecdeleted(new BigInteger("2000"));
        companyTitleFixture2.setTitle("titleValue2");
        companyTitleFixture2.setTitleLatin("titleLatinValue2");
        companyTitleFixture2.setTitleNrm("titleNrmValue2");
        companyTitleFixture2.setTitleStatusId(new BigInteger("2000"));
        companyTitleFixture2.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setGemiId(new BigInteger("2000"));
        companyTitleFixture2.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        companyTitleFixture2.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        entity.setCompanyTitle(companyTitleFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(new BigInteger("2000"));
        entity.setTitle("titleValue2");
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        entity.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createSampleTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setId(new BigInteger("1000"));
        dto.setVersion(new BigInteger("1000"));
        TemporaryCompanyTitleDto companyTitleFixture1 = new TemporaryCompanyTitleDto();
        companyTitleFixture1.setId(new BigInteger("1000"));
        companyTitleFixture1.setVersion(new BigInteger("1000"));
        companyTitleFixture1.setCompanyId(new BigInteger("1000"));
        companyTitleFixture1.setCompanyPreregistrationId(new BigInteger("1000"));
        companyTitleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setOrderSeq(new BigInteger("1000"));
        companyTitleFixture1.setRecdeleted(new BigInteger("1000"));
        companyTitleFixture1.setTitle("titleValue1");
        companyTitleFixture1.setTitleLatin("titleLatinValue1");
        companyTitleFixture1.setTitleNrm("titleNrmValue1");
        companyTitleFixture1.setTitleStatusId(new BigInteger("1000"));
        companyTitleFixture1.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyTitleFixture1.setGemiId(new BigInteger("1000"));
        companyTitleFixture1.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        companyTitleFixture1.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        dto.setCompanyTitle(companyTitleFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(new BigInteger("1000"));
        dto.setTitle("titleValue1");
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        dto.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createAnotherTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setId(new BigInteger("2000"));
        dto.setVersion(new BigInteger("2000"));
        TemporaryCompanyTitleDto companyTitleFixture2 = new TemporaryCompanyTitleDto();
        companyTitleFixture2.setId(new BigInteger("2000"));
        companyTitleFixture2.setVersion(new BigInteger("2000"));
        companyTitleFixture2.setCompanyId(new BigInteger("2000"));
        companyTitleFixture2.setCompanyPreregistrationId(new BigInteger("2000"));
        companyTitleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setOrderSeq(new BigInteger("2000"));
        companyTitleFixture2.setRecdeleted(new BigInteger("2000"));
        companyTitleFixture2.setTitle("titleValue2");
        companyTitleFixture2.setTitleLatin("titleLatinValue2");
        companyTitleFixture2.setTitleNrm("titleNrmValue2");
        companyTitleFixture2.setTitleStatusId(new BigInteger("2000"));
        companyTitleFixture2.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyTitleFixture2.setGemiId(new BigInteger("2000"));
        companyTitleFixture2.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        companyTitleFixture2.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        dto.setCompanyTitle(companyTitleFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(new BigInteger("2000"));
        dto.setTitle("titleValue2");
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        dto.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createPatchTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setVersion(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(new BigInteger("3000"));
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 3, 13));
        dto.setGemiLastUpdated(LocalDate.of(2024, 3, 13));

        return dto;
    }

}
