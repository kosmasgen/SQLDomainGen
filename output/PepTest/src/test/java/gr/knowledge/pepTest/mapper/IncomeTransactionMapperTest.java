package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import gr.knowledge.pepTest.entity.IncomeTransaction;
import gr.knowledge.pepTest.entity.IncomeType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeTransactionMapperTest {

    private IncomeTransactionMapper incomeTransactionMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        incomeTransactionMapper = new IncomeTransactionMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeTransactionToIncomeTransactionDto() {
        IncomeTransaction entity = createSampleIncomeTransactionEntity();
        IncomeTransactionDto expectedDto = createSampleIncomeTransactionDto();

        IncomeTransactionDto actualDto = incomeTransactionMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeTransactionDtoToIncomeTransaction() {
        IncomeTransactionDto dto = createSampleIncomeTransactionDto();
        IncomeTransaction expectedEntity = createSampleIncomeTransactionEntity();

        IncomeTransaction actualEntity = incomeTransactionMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapIncomeTransactionListToIncomeTransactionDtoList() {
        List<IncomeTransaction> entityList = List.of(
                createSampleIncomeTransactionEntity(),
                createAnotherIncomeTransactionEntity()
        );
        List<IncomeTransactionDto> expectedDtoList = List.of(
                createSampleIncomeTransactionDto(),
                createAnotherIncomeTransactionDto()
        );

        List<IncomeTransactionDto> actualDtoList = incomeTransactionMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapIncomeTransactionDtoListToIncomeTransactionList() {
        List<IncomeTransactionDto> dtoList = List.of(
                createSampleIncomeTransactionDto(),
                createAnotherIncomeTransactionDto()
        );
        List<IncomeTransaction> expectedEntityList = List.of(
                createSampleIncomeTransactionEntity(),
                createAnotherIncomeTransactionEntity()
        );

        List<IncomeTransaction> actualEntityList = incomeTransactionMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForIncomeTransaction() {
        IncomeTransaction originalEntity = createSampleIncomeTransactionEntity();
        IncomeTransaction actualEntity = createSampleIncomeTransactionEntity();
        IncomeTransactionDto patchDto = createPatchIncomeTransactionDto();
        IncomeTransaction patchEntity = incomeTransactionMapper.toEntity(patchDto);

        incomeTransactionMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberInTransdIdExpectedValue = patchEntity.getChamberInTransdId() != null ? patchEntity.getChamberInTransdId() : originalEntity.getChamberInTransdId();
        assertThat(actualEntity.getChamberInTransdId())
                .isEqualTo(chamberInTransdIdExpectedValue);

        Object cdUseExpectedValue = patchEntity.getCdUse() != null ? patchEntity.getCdUse() : originalEntity.getCdUse();
        assertThat(actualEntity.getCdUse())
                .isEqualTo(cdUseExpectedValue);

        Object dtExpectedValue = patchEntity.getDt() != null ? patchEntity.getDt() : originalEntity.getDt();
        assertThat(actualEntity.getDt())
                .isEqualTo(dtExpectedValue);

        Object isMemberExpectedValue = patchEntity.getIsMember() != null ? patchEntity.getIsMember() : originalEntity.getIsMember();
        assertThat(actualEntity.getIsMember())
                .isEqualTo(isMemberExpectedValue);

        Object companyIdExpectedValue = patchEntity.getCompanyId() != null ? patchEntity.getCompanyId() : originalEntity.getCompanyId();
        assertThat(actualEntity.getCompanyId())
                .isEqualTo(companyIdExpectedValue);

        Object accountCdExpectedValue = patchEntity.getAccountCd() != null ? patchEntity.getAccountCd() : originalEntity.getAccountCd();
        assertThat(actualEntity.getAccountCd())
                .isEqualTo(accountCdExpectedValue);

        Object incomeTypeExpectedValue = patchEntity.getIncomeType() != null ? patchEntity.getIncomeType() : originalEntity.getIncomeType();
        assertThat(actualEntity.getIncomeType())
                .usingRecursiveComparison()
                .isEqualTo(incomeTypeExpectedValue);

        Object amountExpectedValue = patchEntity.getAmount() != null ? patchEntity.getAmount() : originalEntity.getAmount();
        assertThat(actualEntity.getAmount())
                .isEqualTo(amountExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object incomePayMethodExpectedValue = patchEntity.getIncomePayMethod() != null ? patchEntity.getIncomePayMethod() : originalEntity.getIncomePayMethod();
        assertThat(actualEntity.getIncomePayMethod())
                .usingRecursiveComparison()
                .isEqualTo(incomePayMethodExpectedValue);

        Object isEchamberExpectedValue = patchEntity.getIsEchamber() != null ? patchEntity.getIsEchamber() : originalEntity.getIsEchamber();
        assertThat(actualEntity.getIsEchamber())
                .isEqualTo(isEchamberExpectedValue);

        Object blockSerExpectedValue = patchEntity.getBlockSer() != null ? patchEntity.getBlockSer() : originalEntity.getBlockSer();
        assertThat(actualEntity.getBlockSer())
                .isEqualTo(blockSerExpectedValue);

        Object isKratisiExpectedValue = patchEntity.getIsKratisi() != null ? patchEntity.getIsKratisi() : originalEntity.getIsKratisi();
        assertThat(actualEntity.getIsKratisi())
                .isEqualTo(isKratisiExpectedValue);

        Object chamberCompIdExpectedValue = patchEntity.getChamberCompId() != null ? patchEntity.getChamberCompId() : originalEntity.getChamberCompId();
        assertThat(actualEntity.getChamberCompId())
                .isEqualTo(chamberCompIdExpectedValue);

        Object chamberMethodExpectedValue = patchEntity.getChamberMethod() != null ? patchEntity.getChamberMethod() : originalEntity.getChamberMethod();
        assertThat(actualEntity.getChamberMethod())
                .isEqualTo(chamberMethodExpectedValue);

        Object chamberTypeExpectedValue = patchEntity.getChamberType() != null ? patchEntity.getChamberType() : originalEntity.getChamberType();
        assertThat(actualEntity.getChamberType())
                .isEqualTo(chamberTypeExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeTransactionDtoListForNullOrEmptyIncomeTransactionList() {
        assertThat(incomeTransactionMapper.toDTOList(null)).isEmpty();
        assertThat(incomeTransactionMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeTransactionListForNullOrEmptyIncomeTransactionDtoList() {
        assertThat(incomeTransactionMapper.toEntityList(null)).isEmpty();
        assertThat(incomeTransactionMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        IncomeTransaction entity = createSampleIncomeTransactionEntity();
        IncomeTransaction expectedEntity = createSampleIncomeTransactionEntity();

        incomeTransactionMapper.partialUpdate(entity, null);
        incomeTransactionMapper.partialUpdate(null, createPatchIncomeTransactionDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated IncomeTransaction fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeTransaction createSampleIncomeTransactionEntity() {
        IncomeTransaction entity = new IncomeTransaction();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberInTransdId(new BigInteger("1000"));
        entity.setCdUse("cdUseValue1");
        entity.setDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setIsMember(10);
        entity.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setAccountCd("accountCdValue1");
        IncomeType incomeTypeFixture1 = new IncomeType();
        incomeTypeFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        incomeTypeFixture1.setChamberId(10);
        incomeTypeFixture1.setChamberTypeId(10);
        incomeTypeFixture1.setDescription("descriptionValue1");
        incomeTypeFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        incomeTypeFixture1.setRecdeleted(true);
        incomeTypeFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setIncomeType(incomeTypeFixture1);
        entity.setAmount(new BigDecimal("100.50"));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(new BigInteger("1000"));
        IncomePaymentMethod incomePayMethodFixture1 = new IncomePaymentMethod();
        incomePayMethodFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        incomePayMethodFixture1.setChamberId(10);
        incomePayMethodFixture1.setChamberPayMethodId(10);
        incomePayMethodFixture1.setDescription("descriptionValue1");
        incomePayMethodFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        incomePayMethodFixture1.setRecdeleted(10);
        entity.setIncomePayMethod(incomePayMethodFixture1);
        entity.setIsEchamber(10);
        entity.setBlockSer("blockSerValue1");
        entity.setIsKratisi(new BigInteger("1000"));
        entity.setChamberCompId(new BigInteger("1000"));
        entity.setChamberMethod(new BigInteger("1000"));
        entity.setChamberType(new BigInteger("1000"));

        return entity;
    }

    /**
     * Creates a populated IncomeTransaction fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeTransaction createAnotherIncomeTransactionEntity() {
        IncomeTransaction entity = new IncomeTransaction();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberInTransdId(new BigInteger("2000"));
        entity.setCdUse("cdUseValue2");
        entity.setDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setIsMember(20);
        entity.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setAccountCd("accountCdValue2");
        IncomeType incomeTypeFixture2 = new IncomeType();
        incomeTypeFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        incomeTypeFixture2.setChamberId(20);
        incomeTypeFixture2.setChamberTypeId(20);
        incomeTypeFixture2.setDescription("descriptionValue2");
        incomeTypeFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        incomeTypeFixture2.setRecdeleted(false);
        incomeTypeFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setIncomeType(incomeTypeFixture2);
        entity.setAmount(new BigDecimal("200.50"));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(new BigInteger("2000"));
        IncomePaymentMethod incomePayMethodFixture2 = new IncomePaymentMethod();
        incomePayMethodFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        incomePayMethodFixture2.setChamberId(20);
        incomePayMethodFixture2.setChamberPayMethodId(20);
        incomePayMethodFixture2.setDescription("descriptionValue2");
        incomePayMethodFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        incomePayMethodFixture2.setRecdeleted(20);
        entity.setIncomePayMethod(incomePayMethodFixture2);
        entity.setIsEchamber(20);
        entity.setBlockSer("blockSerValue2");
        entity.setIsKratisi(new BigInteger("2000"));
        entity.setChamberCompId(new BigInteger("2000"));
        entity.setChamberMethod(new BigInteger("2000"));
        entity.setChamberType(new BigInteger("2000"));

        return entity;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createSampleIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberInTransdId(new BigInteger("1000"));
        dto.setCdUse("cdUseValue1");
        dto.setDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setIsMember(10);
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setAccountCd("accountCdValue1");
        IncomeTypeDto incomeTypeFixture1 = new IncomeTypeDto();
        incomeTypeFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        incomeTypeFixture1.setChamberId(10);
        incomeTypeFixture1.setChamberTypeId(10);
        incomeTypeFixture1.setDescription("descriptionValue1");
        incomeTypeFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        incomeTypeFixture1.setRecdeleted(true);
        incomeTypeFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setIncomeType(incomeTypeFixture1);
        dto.setAmount(new BigDecimal("100.50"));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(new BigInteger("1000"));
        IncomePaymentMethodDto incomePayMethodFixture1 = new IncomePaymentMethodDto();
        incomePayMethodFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        incomePayMethodFixture1.setChamberId(10);
        incomePayMethodFixture1.setChamberPayMethodId(10);
        incomePayMethodFixture1.setDescription("descriptionValue1");
        incomePayMethodFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        incomePayMethodFixture1.setRecdeleted(10);
        dto.setIncomePayMethod(incomePayMethodFixture1);
        dto.setIsEchamber(10);
        dto.setBlockSer("blockSerValue1");
        dto.setIsKratisi(new BigInteger("1000"));
        dto.setChamberCompId(new BigInteger("1000"));
        dto.setChamberMethod(new BigInteger("1000"));
        dto.setChamberType(new BigInteger("1000"));

        return dto;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createAnotherIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberInTransdId(new BigInteger("2000"));
        dto.setCdUse("cdUseValue2");
        dto.setDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setIsMember(20);
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setAccountCd("accountCdValue2");
        IncomeTypeDto incomeTypeFixture2 = new IncomeTypeDto();
        incomeTypeFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        incomeTypeFixture2.setChamberId(20);
        incomeTypeFixture2.setChamberTypeId(20);
        incomeTypeFixture2.setDescription("descriptionValue2");
        incomeTypeFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        incomeTypeFixture2.setRecdeleted(false);
        incomeTypeFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setIncomeType(incomeTypeFixture2);
        dto.setAmount(new BigDecimal("200.50"));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(new BigInteger("2000"));
        IncomePaymentMethodDto incomePayMethodFixture2 = new IncomePaymentMethodDto();
        incomePayMethodFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        incomePayMethodFixture2.setChamberId(20);
        incomePayMethodFixture2.setChamberPayMethodId(20);
        incomePayMethodFixture2.setDescription("descriptionValue2");
        incomePayMethodFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        incomePayMethodFixture2.setRecdeleted(20);
        dto.setIncomePayMethod(incomePayMethodFixture2);
        dto.setIsEchamber(20);
        dto.setBlockSer("blockSerValue2");
        dto.setIsKratisi(new BigInteger("2000"));
        dto.setChamberCompId(new BigInteger("2000"));
        dto.setChamberMethod(new BigInteger("2000"));
        dto.setChamberType(new BigInteger("2000"));

        return dto;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createPatchIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setChamberId(30);
        dto.setCdUse("cdUseValue3");
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setAmount(new BigDecimal("300.50"));
        dto.setRecdeleted(new BigInteger("3000"));
        IncomePaymentMethodDto incomePayMethodFixture3 = new IncomePaymentMethodDto();
        incomePayMethodFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        incomePayMethodFixture3.setChamberId(30);
        incomePayMethodFixture3.setChamberPayMethodId(30);
        incomePayMethodFixture3.setRecdeleted(30);
        dto.setIncomePayMethod(incomePayMethodFixture3);
        dto.setBlockSer("blockSerValue3");
        dto.setIsKratisi(new BigInteger("3000"));
        dto.setChamberCompId(new BigInteger("3000"));

        return dto;
    }

}
