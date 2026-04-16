package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import gr.knowledge.pepTest.entity.Professioni18n;
import gr.knowledge.pepTest.entity.Professioni18nKey;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class Professioni18nMapperTest {

    private Professioni18nMapper professioni18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professioni18nMapper = new Professioni18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessioni18nToProfessioni18nDto() {
        Professioni18n entity = createSampleProfessioni18nEntity();
        Professioni18nDto expectedDto = createSampleProfessioni18nDto();

        Professioni18nDto actualDto = professioni18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessioni18nDtoToProfessioni18n() {
        Professioni18nDto dto = createSampleProfessioni18nDto();
        Professioni18n expectedEntity = createSampleProfessioni18nEntity();

        Professioni18n actualEntity = professioni18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessioni18nListToProfessioni18nDtoList() {
        List<Professioni18n> entityList = List.of(
                createSampleProfessioni18nEntity(),
                createAnotherProfessioni18nEntity()
        );
        List<Professioni18nDto> expectedDtoList = List.of(
                createSampleProfessioni18nDto(),
                createAnotherProfessioni18nDto()
        );

        List<Professioni18nDto> actualDtoList = professioni18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessioni18nDtoListToProfessioni18nList() {
        List<Professioni18nDto> dtoList = List.of(
                createSampleProfessioni18nDto(),
                createAnotherProfessioni18nDto()
        );
        List<Professioni18n> expectedEntityList = List.of(
                createSampleProfessioni18nEntity(),
                createAnotherProfessioni18nEntity()
        );

        List<Professioni18n> actualEntityList = professioni18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfessioni18n() {
        Professioni18n originalEntity = createSampleProfessioni18nEntity();
        Professioni18n actualEntity = createSampleProfessioni18nEntity();
        Professioni18nDto patchDto = createPatchProfessioni18nDto();
        Professioni18n patchEntity = professioni18nMapper.toEntity(patchDto);

        professioni18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object professionExpectedValue = patchEntity.getProfession() != null ? patchEntity.getProfession() : originalEntity.getProfession();
        assertThat(actualEntity.getProfession())
                .usingRecursiveComparison()
                .isEqualTo(professionExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object chamberI18nIdExpectedValue = patchEntity.getChamberI18nId() != null ? patchEntity.getChamberI18nId() : originalEntity.getChamberI18nId();
        assertThat(actualEntity.getChamberI18nId())
                .isEqualTo(chamberI18nIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessioni18nDtoListForNullOrEmptyProfessioni18nList() {
        assertThat(professioni18nMapper.toDTOList(null)).isEmpty();
        assertThat(professioni18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessioni18nListForNullOrEmptyProfessioni18nDtoList() {
        assertThat(professioni18nMapper.toEntityList(null)).isEmpty();
        assertThat(professioni18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Professioni18n entity = createSampleProfessioni18nEntity();
        Professioni18n expectedEntity = createSampleProfessioni18nEntity();

        professioni18nMapper.partialUpdate(entity, null);
        professioni18nMapper.partialUpdate(null, createPatchProfessioni18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Professioni18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Professioni18n createSampleProfessioni18nEntity() {
        Professioni18n entity = new Professioni18n();
        Professioni18nKey idFixture1 = new Professioni18nKey();
        entity.setId(idFixture1);
        Profession professionFixture1 = new Profession();
        professionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionFixture1.setChamberId(10);
        professionFixture1.setChamberProfessionId(10);
        professionFixture1.setCode("codeValue1");
        professionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setRecdeleted(true);
        professionFixture1.setProteasId(new BigInteger("1000"));
        entity.setProfession(professionFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setRecdeleted(true);
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setChamberI18nId(10);

        return entity;
    }

    /**
     * Creates a populated Professioni18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Professioni18n createAnotherProfessioni18nEntity() {
        Professioni18n entity = new Professioni18n();
        Professioni18nKey idFixture2 = new Professioni18nKey();
        entity.setId(idFixture2);
        Profession professionFixture2 = new Profession();
        professionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionFixture2.setChamberId(20);
        professionFixture2.setChamberProfessionId(20);
        professionFixture2.setCode("codeValue2");
        professionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setRecdeleted(false);
        professionFixture2.setProteasId(new BigInteger("2000"));
        entity.setProfession(professionFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setRecdeleted(false);
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setChamberI18nId(20);

        return entity;
    }

    /**
     * Creates a populated Professioni18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createSampleProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        Professioni18nKey idFixture1 = new Professioni18nKey();
        dto.setId(idFixture1);
        ProfessionDto professionFixture1 = new ProfessionDto();
        professionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionFixture1.setChamberId(10);
        professionFixture1.setChamberProfessionId(10);
        professionFixture1.setCode("codeValue1");
        professionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setRecdeleted(true);
        professionFixture1.setProteasId(new BigInteger("1000"));
        dto.setProfession(professionFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setRecdeleted(true);
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setChamberI18nId(10);

        return dto;
    }

    /**
     * Creates a populated Professioni18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createAnotherProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        Professioni18nKey idFixture2 = new Professioni18nKey();
        dto.setId(idFixture2);
        ProfessionDto professionFixture2 = new ProfessionDto();
        professionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionFixture2.setChamberId(20);
        professionFixture2.setChamberProfessionId(20);
        professionFixture2.setCode("codeValue2");
        professionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setRecdeleted(false);
        professionFixture2.setProteasId(new BigInteger("2000"));
        dto.setProfession(professionFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setRecdeleted(false);
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setChamberI18nId(20);

        return dto;
    }

    /**
     * Creates a populated Professioni18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createPatchProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        ProfessionDto professionFixture3 = new ProfessionDto();
        professionFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        professionFixture3.setChamberId(30);
        professionFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        professionFixture3.setRecdeleted(true);
        dto.setProfession(professionFixture3);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setChamberI18nId(30);

        return dto;
    }

}
