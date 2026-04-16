package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProfessionMapperTest {

    private ProfessionMapper professionMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professionMapper = new ProfessionMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionToProfessionDto() {
        Profession entity = createSampleProfessionEntity();
        ProfessionDto expectedDto = createSampleProfessionDto();

        ProfessionDto actualDto = professionMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionDtoToProfession() {
        ProfessionDto dto = createSampleProfessionDto();
        Profession expectedEntity = createSampleProfessionEntity();

        Profession actualEntity = professionMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessionListToProfessionDtoList() {
        List<Profession> entityList = List.of(
                createSampleProfessionEntity(),
                createAnotherProfessionEntity()
        );
        List<ProfessionDto> expectedDtoList = List.of(
                createSampleProfessionDto(),
                createAnotherProfessionDto()
        );

        List<ProfessionDto> actualDtoList = professionMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessionDtoListToProfessionList() {
        List<ProfessionDto> dtoList = List.of(
                createSampleProfessionDto(),
                createAnotherProfessionDto()
        );
        List<Profession> expectedEntityList = List.of(
                createSampleProfessionEntity(),
                createAnotherProfessionEntity()
        );

        List<Profession> actualEntityList = professionMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfession() {
        Profession originalEntity = createSampleProfessionEntity();
        Profession actualEntity = createSampleProfessionEntity();
        ProfessionDto patchDto = createPatchProfessionDto();
        Profession patchEntity = professionMapper.toEntity(patchDto);

        professionMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberProfessionIdExpectedValue = patchEntity.getChamberProfessionId() != null ? patchEntity.getChamberProfessionId() : originalEntity.getChamberProfessionId();
        assertThat(actualEntity.getChamberProfessionId())
                .isEqualTo(chamberProfessionIdExpectedValue);

        Object parentProfessionExpectedValue = patchEntity.getParentProfession() != null ? patchEntity.getParentProfession() : originalEntity.getParentProfession();
        assertThat(actualEntity.getParentProfession())
                .usingRecursiveComparison()
                .isEqualTo(parentProfessionExpectedValue);

        Object professionSystemExpectedValue = patchEntity.getProfessionSystem() != null ? patchEntity.getProfessionSystem() : originalEntity.getProfessionSystem();
        assertThat(actualEntity.getProfessionSystem())
                .usingRecursiveComparison()
                .isEqualTo(professionSystemExpectedValue);

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

        Object proteasIdExpectedValue = patchEntity.getProteasId() != null ? patchEntity.getProteasId() : originalEntity.getProteasId();
        assertThat(actualEntity.getProteasId())
                .isEqualTo(proteasIdExpectedValue);

        Object friendlyCatExpectedValue = patchEntity.getFriendlyCat() != null ? patchEntity.getFriendlyCat() : originalEntity.getFriendlyCat();
        assertThat(actualEntity.getFriendlyCat())
                .usingRecursiveComparison()
                .isEqualTo(friendlyCatExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionDtoListForNullOrEmptyProfessionList() {
        assertThat(professionMapper.toDTOList(null)).isEmpty();
        assertThat(professionMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionListForNullOrEmptyProfessionDtoList() {
        assertThat(professionMapper.toEntityList(null)).isEmpty();
        assertThat(professionMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Profession entity = createSampleProfessionEntity();
        Profession expectedEntity = createSampleProfessionEntity();

        professionMapper.partialUpdate(entity, null);
        professionMapper.partialUpdate(null, createPatchProfessionDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Profession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Profession createSampleProfessionEntity() {
        Profession entity = new Profession();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberProfessionId(10);
        Profession parentProfessionFixture1 = new Profession();
        parentProfessionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        parentProfessionFixture1.setChamberId(10);
        parentProfessionFixture1.setChamberProfessionId(10);
        parentProfessionFixture1.setCode("codeValue1");
        parentProfessionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        parentProfessionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        parentProfessionFixture1.setRecdeleted(true);
        parentProfessionFixture1.setProteasId(new BigInteger("1000"));
        entity.setParentProfession(parentProfessionFixture1);
        ProfessionSystem professionSystemFixture1 = new ProfessionSystem();
        professionSystemFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionSystemFixture1.setChamberId(10);
        professionSystemFixture1.setChamberProfSystemId(10);
        professionSystemFixture1.setCd("cdValue1");
        professionSystemFixture1.setDescription("descriptionValue1");
        professionSystemFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionSystemFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionSystemFixture1.setRecdeleted(true);
        entity.setProfessionSystem(professionSystemFixture1);
        entity.setCode("codeValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setProteasId(new BigInteger("1000"));
        ProfessionFriendlyCategory friendlyCatFixture1 = new ProfessionFriendlyCategory();
        friendlyCatFixture1.setId("idValue1");
        friendlyCatFixture1.setDescr("descrValue1");
        entity.setFriendlyCat(friendlyCatFixture1);

        return entity;
    }

    /**
     * Creates a populated Profession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Profession createAnotherProfessionEntity() {
        Profession entity = new Profession();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberProfessionId(20);
        Profession parentProfessionFixture2 = new Profession();
        parentProfessionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        parentProfessionFixture2.setChamberId(20);
        parentProfessionFixture2.setChamberProfessionId(20);
        parentProfessionFixture2.setCode("codeValue2");
        parentProfessionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        parentProfessionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        parentProfessionFixture2.setRecdeleted(false);
        parentProfessionFixture2.setProteasId(new BigInteger("2000"));
        entity.setParentProfession(parentProfessionFixture2);
        ProfessionSystem professionSystemFixture2 = new ProfessionSystem();
        professionSystemFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionSystemFixture2.setChamberId(20);
        professionSystemFixture2.setChamberProfSystemId(20);
        professionSystemFixture2.setCd("cdValue2");
        professionSystemFixture2.setDescription("descriptionValue2");
        professionSystemFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionSystemFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionSystemFixture2.setRecdeleted(false);
        entity.setProfessionSystem(professionSystemFixture2);
        entity.setCode("codeValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setProteasId(new BigInteger("2000"));
        ProfessionFriendlyCategory friendlyCatFixture2 = new ProfessionFriendlyCategory();
        friendlyCatFixture2.setId("idValue2");
        friendlyCatFixture2.setDescr("descrValue2");
        entity.setFriendlyCat(friendlyCatFixture2);

        return entity;
    }

    /**
     * Creates a populated ProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createSampleProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberProfessionId(10);
        ProfessionDto parentProfessionFixture1 = new ProfessionDto();
        parentProfessionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        parentProfessionFixture1.setChamberId(10);
        parentProfessionFixture1.setChamberProfessionId(10);
        parentProfessionFixture1.setCode("codeValue1");
        parentProfessionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        parentProfessionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        parentProfessionFixture1.setRecdeleted(true);
        parentProfessionFixture1.setProteasId(new BigInteger("1000"));
        dto.setParentProfession(parentProfessionFixture1);
        ProfessionSystemDto professionSystemFixture1 = new ProfessionSystemDto();
        professionSystemFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionSystemFixture1.setChamberId(10);
        professionSystemFixture1.setChamberProfSystemId(10);
        professionSystemFixture1.setCd("cdValue1");
        professionSystemFixture1.setDescription("descriptionValue1");
        professionSystemFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionSystemFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionSystemFixture1.setRecdeleted(true);
        dto.setProfessionSystem(professionSystemFixture1);
        dto.setCode("codeValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setProteasId(new BigInteger("1000"));
        ProfessionFriendlyCategoryDto friendlyCatFixture1 = new ProfessionFriendlyCategoryDto();
        friendlyCatFixture1.setId("idValue1");
        friendlyCatFixture1.setDescr("descrValue1");
        dto.setFriendlyCat(friendlyCatFixture1);

        return dto;
    }

    /**
     * Creates a populated ProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createAnotherProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberProfessionId(20);
        ProfessionDto parentProfessionFixture2 = new ProfessionDto();
        parentProfessionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        parentProfessionFixture2.setChamberId(20);
        parentProfessionFixture2.setChamberProfessionId(20);
        parentProfessionFixture2.setCode("codeValue2");
        parentProfessionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        parentProfessionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        parentProfessionFixture2.setRecdeleted(false);
        parentProfessionFixture2.setProteasId(new BigInteger("2000"));
        dto.setParentProfession(parentProfessionFixture2);
        ProfessionSystemDto professionSystemFixture2 = new ProfessionSystemDto();
        professionSystemFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionSystemFixture2.setChamberId(20);
        professionSystemFixture2.setChamberProfSystemId(20);
        professionSystemFixture2.setCd("cdValue2");
        professionSystemFixture2.setDescription("descriptionValue2");
        professionSystemFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionSystemFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionSystemFixture2.setRecdeleted(false);
        dto.setProfessionSystem(professionSystemFixture2);
        dto.setCode("codeValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setProteasId(new BigInteger("2000"));
        ProfessionFriendlyCategoryDto friendlyCatFixture2 = new ProfessionFriendlyCategoryDto();
        friendlyCatFixture2.setId("idValue2");
        friendlyCatFixture2.setDescr("descrValue2");
        dto.setFriendlyCat(friendlyCatFixture2);

        return dto;
    }

    /**
     * Creates a populated ProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createPatchProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setChamberId(30);
        ProfessionSystemDto professionSystemFixture3 = new ProfessionSystemDto();
        professionSystemFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        professionSystemFixture3.setChamberId(30);
        professionSystemFixture3.setCd("cdValue3");
        professionSystemFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        professionSystemFixture3.setRecdeleted(true);
        dto.setProfessionSystem(professionSystemFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        ProfessionFriendlyCategoryDto friendlyCatFixture3 = new ProfessionFriendlyCategoryDto();
        friendlyCatFixture3.setId("idValue3");
        friendlyCatFixture3.setDescr("descrValue3");
        dto.setFriendlyCat(friendlyCatFixture3);

        return dto;
    }

}
