package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeGemiPaymentMapperTest {

    private IncomeGemiPaymentMapper incomeGemiPaymentMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        incomeGemiPaymentMapper = new IncomeGemiPaymentMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeGemiPaymentToIncomeGemiPaymentDto() {
        IncomeGemiPayment entity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPaymentDto expectedDto = createSampleIncomeGemiPaymentDto();

        IncomeGemiPaymentDto actualDto = incomeGemiPaymentMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeGemiPaymentDtoToIncomeGemiPayment() {
        IncomeGemiPaymentDto dto = createSampleIncomeGemiPaymentDto();
        IncomeGemiPayment expectedEntity = createSampleIncomeGemiPaymentEntity();

        IncomeGemiPayment actualEntity = incomeGemiPaymentMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapIncomeGemiPaymentListToIncomeGemiPaymentDtoList() {
        List<IncomeGemiPayment> entityList = List.of(
                createSampleIncomeGemiPaymentEntity(),
                createAnotherIncomeGemiPaymentEntity()
        );
        List<IncomeGemiPaymentDto> expectedDtoList = List.of(
                createSampleIncomeGemiPaymentDto(),
                createAnotherIncomeGemiPaymentDto()
        );

        List<IncomeGemiPaymentDto> actualDtoList = incomeGemiPaymentMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapIncomeGemiPaymentDtoListToIncomeGemiPaymentList() {
        List<IncomeGemiPaymentDto> dtoList = List.of(
                createSampleIncomeGemiPaymentDto(),
                createAnotherIncomeGemiPaymentDto()
        );
        List<IncomeGemiPayment> expectedEntityList = List.of(
                createSampleIncomeGemiPaymentEntity(),
                createAnotherIncomeGemiPaymentEntity()
        );

        List<IncomeGemiPayment> actualEntityList = incomeGemiPaymentMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForIncomeGemiPayment() {
        IncomeGemiPayment originalEntity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPayment actualEntity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPaymentDto patchDto = createPatchIncomeGemiPaymentDto();
        IncomeGemiPayment patchEntity = incomeGemiPaymentMapper.toEntity(patchDto);

        incomeGemiPaymentMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object paymentTypeExpectedValue = patchEntity.getPaymentType() != null ? patchEntity.getPaymentType() : originalEntity.getPaymentType();
        assertThat(actualEntity.getPaymentType())
                .isEqualTo(paymentTypeExpectedValue);

        Object saleTsExpectedValue = patchEntity.getSaleTs() != null ? patchEntity.getSaleTs() : originalEntity.getSaleTs();
        assertThat(actualEntity.getSaleTs())
                .isEqualTo(saleTsExpectedValue);

        Object chamberAmountExpectedValue = patchEntity.getChamberAmount() != null ? patchEntity.getChamberAmount() : originalEntity.getChamberAmount();
        assertThat(actualEntity.getChamberAmount())
                .isEqualTo(chamberAmountExpectedValue);

        Object chamberAmountForCertsExpectedValue = patchEntity.getChamberAmountForCerts() != null ? patchEntity.getChamberAmountForCerts() : originalEntity.getChamberAmountForCerts();
        assertThat(actualEntity.getChamberAmountForCerts())
                .isEqualTo(chamberAmountForCertsExpectedValue);

        Object chamberAmountForPostalExpectedValue = patchEntity.getChamberAmountForPostal() != null ? patchEntity.getChamberAmountForPostal() : originalEntity.getChamberAmountForPostal();
        assertThat(actualEntity.getChamberAmountForPostal())
                .isEqualTo(chamberAmountForPostalExpectedValue);

        Object totalAmountPaidExpectedValue = patchEntity.getTotalAmountPaid() != null ? patchEntity.getTotalAmountPaid() : originalEntity.getTotalAmountPaid();
        assertThat(actualEntity.getTotalAmountPaid())
                .isEqualTo(totalAmountPaidExpectedValue);

        Object descrExpectedValue = patchEntity.getDescr() != null ? patchEntity.getDescr() : originalEntity.getDescr();
        assertThat(actualEntity.getDescr())
                .isEqualTo(descrExpectedValue);

        Object payerExpectedValue = patchEntity.getPayer() != null ? patchEntity.getPayer() : originalEntity.getPayer();
        assertThat(actualEntity.getPayer())
                .isEqualTo(payerExpectedValue);

        Object gemiPaymentIdExpectedValue = patchEntity.getGemiPaymentId() != null ? patchEntity.getGemiPaymentId() : originalEntity.getGemiPaymentId();
        assertThat(actualEntity.getGemiPaymentId())
                .isEqualTo(gemiPaymentIdExpectedValue);

        Object companyGemiIdExpectedValue = patchEntity.getCompanyGemiId() != null ? patchEntity.getCompanyGemiId() : originalEntity.getCompanyGemiId();
        assertThat(actualEntity.getCompanyGemiId())
                .isEqualTo(companyGemiIdExpectedValue);

        Object coNameExpectedValue = patchEntity.getCoName() != null ? patchEntity.getCoName() : originalEntity.getCoName();
        assertThat(actualEntity.getCoName())
                .isEqualTo(coNameExpectedValue);

        Object companyChamberIdExpectedValue = patchEntity.getCompanyChamberId() != null ? patchEntity.getCompanyChamberId() : originalEntity.getCompanyChamberId();
        assertThat(actualEntity.getCompanyChamberId())
                .isEqualTo(companyChamberIdExpectedValue);

        Object paymentMethodExpectedValue = patchEntity.getPaymentMethod() != null ? patchEntity.getPaymentMethod() : originalEntity.getPaymentMethod();
        assertThat(actualEntity.getPaymentMethod())
                .isEqualTo(paymentMethodExpectedValue);

        Object ri3ExpectedValue = patchEntity.getRi3() != null ? patchEntity.getRi3() : originalEntity.getRi3();
        assertThat(actualEntity.getRi3())
                .isEqualTo(ri3ExpectedValue);

        Object subscriptionStartDateExpectedValue = patchEntity.getSubscriptionStartDate() != null ? patchEntity.getSubscriptionStartDate() : originalEntity.getSubscriptionStartDate();
        assertThat(actualEntity.getSubscriptionStartDate())
                .isEqualTo(subscriptionStartDateExpectedValue);

        Object subscriptionEndDateExpectedValue = patchEntity.getSubscriptionEndDate() != null ? patchEntity.getSubscriptionEndDate() : originalEntity.getSubscriptionEndDate();
        assertThat(actualEntity.getSubscriptionEndDate())
                .isEqualTo(subscriptionEndDateExpectedValue);

        Object cancelFlagExpectedValue = patchEntity.getCancelFlag() != null ? patchEntity.getCancelFlag() : originalEntity.getCancelFlag();
        assertThat(actualEntity.getCancelFlag())
                .isEqualTo(cancelFlagExpectedValue);

        Object refundTsExpectedValue = patchEntity.getRefundTs() != null ? patchEntity.getRefundTs() : originalEntity.getRefundTs();
        assertThat(actualEntity.getRefundTs())
                .isEqualTo(refundTsExpectedValue);

        Object remittanceDtExpectedValue = patchEntity.getRemittanceDt() != null ? patchEntity.getRemittanceDt() : originalEntity.getRemittanceDt();
        assertThat(actualEntity.getRemittanceDt())
                .isEqualTo(remittanceDtExpectedValue);

        Object remittanceAmountExpectedValue = patchEntity.getRemittanceAmount() != null ? patchEntity.getRemittanceAmount() : originalEntity.getRemittanceAmount();
        assertThat(actualEntity.getRemittanceAmount())
                .isEqualTo(remittanceAmountExpectedValue);

        Object remittanceReferenceExpectedValue = patchEntity.getRemittanceReference() != null ? patchEntity.getRemittanceReference() : originalEntity.getRemittanceReference();
        assertThat(actualEntity.getRemittanceReference())
                .isEqualTo(remittanceReferenceExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeGemiPaymentDtoListForNullOrEmptyIncomeGemiPaymentList() {
        assertThat(incomeGemiPaymentMapper.toDTOList(null)).isEmpty();
        assertThat(incomeGemiPaymentMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeGemiPaymentListForNullOrEmptyIncomeGemiPaymentDtoList() {
        assertThat(incomeGemiPaymentMapper.toEntityList(null)).isEmpty();
        assertThat(incomeGemiPaymentMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        IncomeGemiPayment entity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPayment expectedEntity = createSampleIncomeGemiPaymentEntity();

        incomeGemiPaymentMapper.partialUpdate(entity, null);
        incomeGemiPaymentMapper.partialUpdate(null, createPatchIncomeGemiPaymentDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated IncomeGemiPayment fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeGemiPayment createSampleIncomeGemiPaymentEntity() {
        IncomeGemiPayment entity = new IncomeGemiPayment();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setPaymentType("paymentTypeValue1");
        entity.setSaleTs(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setChamberAmount(new BigDecimal("100.50"));
        entity.setChamberAmountForCerts(new BigDecimal("100.50"));
        entity.setChamberAmountForPostal(new BigDecimal("100.50"));
        entity.setTotalAmountPaid(new BigDecimal("100.50"));
        entity.setDescr("descrValue1");
        entity.setPayer("payerValue1");
        entity.setGemiPaymentId(new BigInteger("1000"));
        entity.setCompanyGemiId(new BigInteger("1000"));
        entity.setCoName("coNameValue1");
        entity.setCompanyChamberId(new BigInteger("1000"));
        entity.setPaymentMethod("paymentMethodValue1");
        entity.setRi3("ri3Value1");
        entity.setSubscriptionStartDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setSubscriptionEndDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setCancelFlag(new BigInteger("1000"));
        entity.setRefundTs(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRemittanceDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRemittanceAmount(new BigDecimal("100.50"));
        entity.setRemittanceReference("remittanceReferenceValue1");
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated IncomeGemiPayment fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeGemiPayment createAnotherIncomeGemiPaymentEntity() {
        IncomeGemiPayment entity = new IncomeGemiPayment();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setPaymentType("paymentTypeValue2");
        entity.setSaleTs(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setChamberAmount(new BigDecimal("200.50"));
        entity.setChamberAmountForCerts(new BigDecimal("200.50"));
        entity.setChamberAmountForPostal(new BigDecimal("200.50"));
        entity.setTotalAmountPaid(new BigDecimal("200.50"));
        entity.setDescr("descrValue2");
        entity.setPayer("payerValue2");
        entity.setGemiPaymentId(new BigInteger("2000"));
        entity.setCompanyGemiId(new BigInteger("2000"));
        entity.setCoName("coNameValue2");
        entity.setCompanyChamberId(new BigInteger("2000"));
        entity.setPaymentMethod("paymentMethodValue2");
        entity.setRi3("ri3Value2");
        entity.setSubscriptionStartDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setSubscriptionEndDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setCancelFlag(new BigInteger("2000"));
        entity.setRefundTs(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRemittanceDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRemittanceAmount(new BigDecimal("200.50"));
        entity.setRemittanceReference("remittanceReferenceValue2");
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createSampleIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setPaymentType("paymentTypeValue1");
        dto.setSaleTs(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setChamberAmount(new BigDecimal("100.50"));
        dto.setChamberAmountForCerts(new BigDecimal("100.50"));
        dto.setChamberAmountForPostal(new BigDecimal("100.50"));
        dto.setTotalAmountPaid(new BigDecimal("100.50"));
        dto.setDescr("descrValue1");
        dto.setPayer("payerValue1");
        dto.setGemiPaymentId(new BigInteger("1000"));
        dto.setCompanyGemiId(new BigInteger("1000"));
        dto.setCoName("coNameValue1");
        dto.setCompanyChamberId(new BigInteger("1000"));
        dto.setPaymentMethod("paymentMethodValue1");
        dto.setRi3("ri3Value1");
        dto.setSubscriptionStartDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setSubscriptionEndDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setCancelFlag(new BigInteger("1000"));
        dto.setRefundTs(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRemittanceDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRemittanceAmount(new BigDecimal("100.50"));
        dto.setRemittanceReference("remittanceReferenceValue1");
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createAnotherIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setPaymentType("paymentTypeValue2");
        dto.setSaleTs(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setChamberAmount(new BigDecimal("200.50"));
        dto.setChamberAmountForCerts(new BigDecimal("200.50"));
        dto.setChamberAmountForPostal(new BigDecimal("200.50"));
        dto.setTotalAmountPaid(new BigDecimal("200.50"));
        dto.setDescr("descrValue2");
        dto.setPayer("payerValue2");
        dto.setGemiPaymentId(new BigInteger("2000"));
        dto.setCompanyGemiId(new BigInteger("2000"));
        dto.setCoName("coNameValue2");
        dto.setCompanyChamberId(new BigInteger("2000"));
        dto.setPaymentMethod("paymentMethodValue2");
        dto.setRi3("ri3Value2");
        dto.setSubscriptionStartDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setSubscriptionEndDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setCancelFlag(new BigInteger("2000"));
        dto.setRefundTs(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRemittanceDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRemittanceAmount(new BigDecimal("200.50"));
        dto.setRemittanceReference("remittanceReferenceValue2");
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createPatchIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setChamberId(30);
        dto.setPaymentType("paymentTypeValue3");
        dto.setSaleTs(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setChamberAmount(new BigDecimal("300.50"));
        dto.setChamberAmountForCerts(new BigDecimal("300.50"));
        dto.setChamberAmountForPostal(new BigDecimal("300.50"));
        dto.setTotalAmountPaid(new BigDecimal("300.50"));
        dto.setDescr("descrValue3");
        dto.setPayer("payerValue3");
        dto.setGemiPaymentId(new BigInteger("3000"));
        dto.setCompanyGemiId(new BigInteger("3000"));
        dto.setCompanyChamberId(new BigInteger("3000"));
        dto.setPaymentMethod("paymentMethodValue3");
        dto.setSubscriptionStartDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setSubscriptionEndDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setCancelFlag(new BigInteger("3000"));
        dto.setRefundTs(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRemittanceDt(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
