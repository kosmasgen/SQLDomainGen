package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TemporaryCompanyTitleMapperTest {

    private TemporaryCompanyTitleMapper temporaryCompanyTitleMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        temporaryCompanyTitleMapper = new TemporaryCompanyTitleMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyTitleToTemporaryCompanyTitleDto() {
        TemporaryCompanyTitle entity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitleDto expectedDto = createSampleTemporaryCompanyTitleDto();

        TemporaryCompanyTitleDto actualDto = temporaryCompanyTitleMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyTitleDtoToTemporaryCompanyTitle() {
        TemporaryCompanyTitleDto dto = createSampleTemporaryCompanyTitleDto();
        TemporaryCompanyTitle expectedEntity = createSampleTemporaryCompanyTitleEntity();

        TemporaryCompanyTitle actualEntity = temporaryCompanyTitleMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyTitleListToTemporaryCompanyTitleDtoList() {
        List<TemporaryCompanyTitle> entityList = List.of(
                createSampleTemporaryCompanyTitleEntity(),
                createAnotherTemporaryCompanyTitleEntity()
        );
        List<TemporaryCompanyTitleDto> expectedDtoList = List.of(
                createSampleTemporaryCompanyTitleDto(),
                createAnotherTemporaryCompanyTitleDto()
        );

        List<TemporaryCompanyTitleDto> actualDtoList = temporaryCompanyTitleMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyTitleDtoListToTemporaryCompanyTitleList() {
        List<TemporaryCompanyTitleDto> dtoList = List.of(
                createSampleTemporaryCompanyTitleDto(),
                createAnotherTemporaryCompanyTitleDto()
        );
        List<TemporaryCompanyTitle> expectedEntityList = List.of(
                createSampleTemporaryCompanyTitleEntity(),
                createAnotherTemporaryCompanyTitleEntity()
        );

        List<TemporaryCompanyTitle> actualEntityList = temporaryCompanyTitleMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForTemporaryCompanyTitle() {
        TemporaryCompanyTitle originalEntity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitle actualEntity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitleDto patchDto = createPatchTemporaryCompanyTitleDto();
        TemporaryCompanyTitle patchEntity = temporaryCompanyTitleMapper.toEntity(patchDto);

        temporaryCompanyTitleMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object companyIdExpectedValue = patchEntity.getCompanyId() != null ? patchEntity.getCompanyId() : originalEntity.getCompanyId();
        assertThat(actualEntity.getCompanyId())
                .isEqualTo(companyIdExpectedValue);

        Object companyPreregistrationIdExpectedValue = patchEntity.getCompanyPreregistrationId() != null ? patchEntity.getCompanyPreregistrationId() : originalEntity.getCompanyPreregistrationId();
        assertThat(actualEntity.getCompanyPreregistrationId())
                .isEqualTo(companyPreregistrationIdExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object fromDateExpectedValue = patchEntity.getFromDate() != null ? patchEntity.getFromDate() : originalEntity.getFromDate();
        assertThat(actualEntity.getFromDate())
                .isEqualTo(fromDateExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object orderSeqExpectedValue = patchEntity.getOrderSeq() != null ? patchEntity.getOrderSeq() : originalEntity.getOrderSeq();
        assertThat(actualEntity.getOrderSeq())
                .isEqualTo(orderSeqExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

        Object titleLatinExpectedValue = patchEntity.getTitleLatin() != null ? patchEntity.getTitleLatin() : originalEntity.getTitleLatin();
        assertThat(actualEntity.getTitleLatin())
                .isEqualTo(titleLatinExpectedValue);

        Object titleNrmExpectedValue = patchEntity.getTitleNrm() != null ? patchEntity.getTitleNrm() : originalEntity.getTitleNrm();
        assertThat(actualEntity.getTitleNrm())
                .isEqualTo(titleNrmExpectedValue);

        Object titleStatusIdExpectedValue = patchEntity.getTitleStatusId() != null ? patchEntity.getTitleStatusId() : originalEntity.getTitleStatusId();
        assertThat(actualEntity.getTitleStatusId())
                .isEqualTo(titleStatusIdExpectedValue);

        Object toDateExpectedValue = patchEntity.getToDate() != null ? patchEntity.getToDate() : originalEntity.getToDate();
        assertThat(actualEntity.getToDate())
                .isEqualTo(toDateExpectedValue);

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
    void shouldReturnEmptyTemporaryCompanyTitleDtoListForNullOrEmptyTemporaryCompanyTitleList() {
        assertThat(temporaryCompanyTitleMapper.toDTOList(null)).isEmpty();
        assertThat(temporaryCompanyTitleMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyTitleListForNullOrEmptyTemporaryCompanyTitleDtoList() {
        assertThat(temporaryCompanyTitleMapper.toEntityList(null)).isEmpty();
        assertThat(temporaryCompanyTitleMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        TemporaryCompanyTitle entity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitle expectedEntity = createSampleTemporaryCompanyTitleEntity();

        temporaryCompanyTitleMapper.partialUpdate(entity, null);
        temporaryCompanyTitleMapper.partialUpdate(null, createPatchTemporaryCompanyTitleDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated TemporaryCompanyTitle fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitle createSampleTemporaryCompanyTitleEntity() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();
        entity.setId(new BigInteger("1000"));
        entity.setVersion(new BigInteger("1000"));
        entity.setCompanyId(new BigInteger("1000"));
        entity.setCompanyPreregistrationId(new BigInteger("1000"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setOrderSeq(new BigInteger("1000"));
        entity.setRecdeleted(new BigInteger("1000"));
        entity.setTitle("titleValue1");
        entity.setTitleLatin("titleLatinValue1");
        entity.setTitleNrm("titleNrmValue1");
        entity.setTitleStatusId(new BigInteger("1000"));
        entity.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        entity.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitle fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitle createAnotherTemporaryCompanyTitleEntity() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();
        entity.setId(new BigInteger("2000"));
        entity.setVersion(new BigInteger("2000"));
        entity.setCompanyId(new BigInteger("2000"));
        entity.setCompanyPreregistrationId(new BigInteger("2000"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setOrderSeq(new BigInteger("2000"));
        entity.setRecdeleted(new BigInteger("2000"));
        entity.setTitle("titleValue2");
        entity.setTitleLatin("titleLatinValue2");
        entity.setTitleNrm("titleNrmValue2");
        entity.setTitleStatusId(new BigInteger("2000"));
        entity.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        entity.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createSampleTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setId(new BigInteger("1000"));
        dto.setVersion(new BigInteger("1000"));
        dto.setCompanyId(new BigInteger("1000"));
        dto.setCompanyPreregistrationId(new BigInteger("1000"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setOrderSeq(new BigInteger("1000"));
        dto.setRecdeleted(new BigInteger("1000"));
        dto.setTitle("titleValue1");
        dto.setTitleLatin("titleLatinValue1");
        dto.setTitleNrm("titleNrmValue1");
        dto.setTitleStatusId(new BigInteger("1000"));
        dto.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        dto.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createAnotherTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setId(new BigInteger("2000"));
        dto.setVersion(new BigInteger("2000"));
        dto.setCompanyId(new BigInteger("2000"));
        dto.setCompanyPreregistrationId(new BigInteger("2000"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setOrderSeq(new BigInteger("2000"));
        dto.setRecdeleted(new BigInteger("2000"));
        dto.setTitle("titleValue2");
        dto.setTitleLatin("titleLatinValue2");
        dto.setTitleNrm("titleNrmValue2");
        dto.setTitleStatusId(new BigInteger("2000"));
        dto.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        dto.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createPatchTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setVersion(new BigInteger("3000"));
        dto.setCompanyId(new BigInteger("3000"));
        dto.setCompanyPreregistrationId(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setFromDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(new BigInteger("3000"));
        dto.setToDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 3, 13));
        dto.setGemiLastUpdated(LocalDate.of(2024, 3, 13));

        return dto;
    }

}
