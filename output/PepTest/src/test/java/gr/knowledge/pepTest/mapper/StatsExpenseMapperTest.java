package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.entity.StatsExpense;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class StatsExpenseMapperTest {

    private StatsExpenseMapper statsExpenseMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        statsExpenseMapper = new StatsExpenseMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapStatsExpenseToStatsExpenseDto() {
        StatsExpense entity = createSampleStatsExpenseEntity();
        StatsExpenseDto expectedDto = createSampleStatsExpenseDto();

        StatsExpenseDto actualDto = statsExpenseMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapStatsExpenseDtoToStatsExpense() {
        StatsExpenseDto dto = createSampleStatsExpenseDto();
        StatsExpense expectedEntity = createSampleStatsExpenseEntity();

        StatsExpense actualEntity = statsExpenseMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapStatsExpenseListToStatsExpenseDtoList() {
        List<StatsExpense> entityList = List.of(
                createSampleStatsExpenseEntity(),
                createAnotherStatsExpenseEntity()
        );
        List<StatsExpenseDto> expectedDtoList = List.of(
                createSampleStatsExpenseDto(),
                createAnotherStatsExpenseDto()
        );

        List<StatsExpenseDto> actualDtoList = statsExpenseMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapStatsExpenseDtoListToStatsExpenseList() {
        List<StatsExpenseDto> dtoList = List.of(
                createSampleStatsExpenseDto(),
                createAnotherStatsExpenseDto()
        );
        List<StatsExpense> expectedEntityList = List.of(
                createSampleStatsExpenseEntity(),
                createAnotherStatsExpenseEntity()
        );

        List<StatsExpense> actualEntityList = statsExpenseMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForStatsExpense() {
        StatsExpense originalEntity = createSampleStatsExpenseEntity();
        StatsExpense actualEntity = createSampleStatsExpenseEntity();
        StatsExpenseDto patchDto = createPatchStatsExpenseDto();
        StatsExpense patchEntity = statsExpenseMapper.toEntity(patchDto);

        statsExpenseMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object accountSumIdExpectedValue = patchEntity.getAccountSumId() != null ? patchEntity.getAccountSumId() : originalEntity.getAccountSumId();
        assertThat(actualEntity.getAccountSumId())
                .isEqualTo(accountSumIdExpectedValue);

        Object cdUseExpectedValue = patchEntity.getCdUse() != null ? patchEntity.getCdUse() : originalEntity.getCdUse();
        assertThat(actualEntity.getCdUse())
                .isEqualTo(cdUseExpectedValue);

        Object groupDescrExpectedValue = patchEntity.getGroupDescr() != null ? patchEntity.getGroupDescr() : originalEntity.getGroupDescr();
        assertThat(actualEntity.getGroupDescr())
                .isEqualTo(groupDescrExpectedValue);

        Object mmExpectedValue = patchEntity.getMm() != null ? patchEntity.getMm() : originalEntity.getMm();
        assertThat(actualEntity.getMm())
                .isEqualTo(mmExpectedValue);

        Object amountExpectedValue = patchEntity.getAmount() != null ? patchEntity.getAmount() : originalEntity.getAmount();
        assertThat(actualEntity.getAmount())
                .isEqualTo(amountExpectedValue);

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
    void shouldReturnEmptyStatsExpenseDtoListForNullOrEmptyStatsExpenseList() {
        assertThat(statsExpenseMapper.toDTOList(null)).isEmpty();
        assertThat(statsExpenseMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyStatsExpenseListForNullOrEmptyStatsExpenseDtoList() {
        assertThat(statsExpenseMapper.toEntityList(null)).isEmpty();
        assertThat(statsExpenseMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        StatsExpense entity = createSampleStatsExpenseEntity();
        StatsExpense expectedEntity = createSampleStatsExpenseEntity();

        statsExpenseMapper.partialUpdate(entity, null);
        statsExpenseMapper.partialUpdate(null, createPatchStatsExpenseDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated StatsExpense fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private StatsExpense createSampleStatsExpenseEntity() {
        StatsExpense entity = new StatsExpense();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setAccountSumId(new BigInteger("1000"));
        entity.setCdUse("cdUseValue1");
        entity.setGroupDescr("groupDescrValue1");
        entity.setMm("mmValue1");
        entity.setAmount(new BigDecimal("100.50"));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(new BigInteger("1000"));

        return entity;
    }

    /**
     * Creates a populated StatsExpense fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private StatsExpense createAnotherStatsExpenseEntity() {
        StatsExpense entity = new StatsExpense();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setAccountSumId(new BigInteger("2000"));
        entity.setCdUse("cdUseValue2");
        entity.setGroupDescr("groupDescrValue2");
        entity.setMm("mmValue2");
        entity.setAmount(new BigDecimal("200.50"));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(new BigInteger("2000"));

        return entity;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createSampleStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setAccountSumId(new BigInteger("1000"));
        dto.setCdUse("cdUseValue1");
        dto.setGroupDescr("groupDescrValue1");
        dto.setMm("mmValue1");
        dto.setAmount(new BigDecimal("100.50"));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(new BigInteger("1000"));

        return dto;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createAnotherStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setAccountSumId(new BigInteger("2000"));
        dto.setCdUse("cdUseValue2");
        dto.setGroupDescr("groupDescrValue2");
        dto.setMm("mmValue2");
        dto.setAmount(new BigDecimal("200.50"));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(new BigInteger("2000"));

        return dto;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createPatchStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setChamberId(30);
        dto.setAccountSumId(new BigInteger("3000"));
        dto.setCdUse("cdUseValue3");
        dto.setGroupDescr("groupDescrValue3");
        dto.setMm("mmValue3");
        dto.setAmount(new BigDecimal("300.50"));
        dto.setRecdeleted(new BigInteger("3000"));

        return dto;
    }

}
