package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.repository.IncomeGemiPaymentRepository;
import gr.knowledge.pepTest.mapper.IncomeGemiPaymentMapper;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IncomeGemiPaymentServiceImplTest {

    @Mock
    private IncomeGemiPaymentRepository incomeGemiPaymentRepository;

    @Mock
    private IncomeGemiPaymentMapper incomeGemiPaymentMapper;

    @InjectMocks
    private IncomeGemiPaymentServiceImpl incomeGemiPaymentService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for IncomeGemiPayment.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("IncomeGemiPayment", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllIncomeGemiPaymentsIsCalled() {
        List<IncomeGemiPayment> entityList = List.of(createSampleIncomeGemiPaymentEntity(), createAnotherIncomeGemiPaymentEntity());
        List<IncomeGemiPaymentDto> dtoList = List.of(createSampleIncomeGemiPaymentDto(), createAnotherIncomeGemiPaymentDto());

        given(incomeGemiPaymentRepository.findAll()).willReturn(entityList);
        given(incomeGemiPaymentMapper.toDTOList(entityList)).willReturn(dtoList);

        List<IncomeGemiPaymentDto> result = incomeGemiPaymentService.getAllIncomeGemiPayments();

        assertSame(dtoList, result);
        verify(incomeGemiPaymentRepository).findAll();
        verify(incomeGemiPaymentMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetIncomeGemiPaymentByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeGemiPayment incomeGemiPayment = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPaymentDto incomeGemiPaymentDto = createSampleIncomeGemiPaymentDto();

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.of(incomeGemiPayment));
        given(incomeGemiPaymentMapper.toDTO(incomeGemiPayment)).willReturn(incomeGemiPaymentDto);

        IncomeGemiPaymentDto result = incomeGemiPaymentService.getIncomeGemiPaymentById(id);

        assertSame(incomeGemiPaymentDto, result);
        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentMapper).toDTO(incomeGemiPayment);
    }

    @Test
    void shouldThrowWhenGetIncomeGemiPaymentByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeGemiPaymentService.getIncomeGemiPaymentById(id));

        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentMapper, never()).toDTO(any(IncomeGemiPayment.class));
    }

    @Test
    void shouldCreateIncomeGemiPaymentWhenCreateIncomeGemiPaymentIsCalled() {
        IncomeGemiPaymentDto requestDto = createSampleIncomeGemiPaymentDto();
        IncomeGemiPayment mappedEntity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPayment savedEntity = createAnotherIncomeGemiPaymentEntity();
        IncomeGemiPaymentDto responseDto = createAnotherIncomeGemiPaymentDto();

        given(incomeGemiPaymentMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(incomeGemiPaymentRepository.save(mappedEntity)).willReturn(savedEntity);
        given(incomeGemiPaymentMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeGemiPaymentDto result = incomeGemiPaymentService.createIncomeGemiPayment(requestDto);

        assertSame(responseDto, result);
        verify(incomeGemiPaymentMapper).toEntity(requestDto);
        verify(incomeGemiPaymentRepository).save(mappedEntity);
        verify(incomeGemiPaymentMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateIncomeGemiPaymentWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeGemiPaymentDto requestDto = createPatchIncomeGemiPaymentDto();
        IncomeGemiPayment existingEntity = createSampleIncomeGemiPaymentEntity();
        IncomeGemiPayment savedEntity = createAnotherIncomeGemiPaymentEntity();
        IncomeGemiPaymentDto responseDto = createAnotherIncomeGemiPaymentDto();

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(incomeGemiPaymentRepository.save(existingEntity)).willReturn(savedEntity);
        given(incomeGemiPaymentMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeGemiPaymentDto result = incomeGemiPaymentService.updateIncomeGemiPayment(id, requestDto);

        assertSame(responseDto, result);
        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentMapper).partialUpdate(existingEntity, requestDto);
        verify(incomeGemiPaymentRepository).save(existingEntity);
        verify(incomeGemiPaymentMapper).toDTO(savedEntity);
        verify(incomeGemiPaymentMapper, never()).toEntity(any(IncomeGemiPaymentDto.class));
    }

    @Test
    void shouldThrowWhenUpdateIncomeGemiPaymentCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeGemiPaymentDto requestDto = createPatchIncomeGemiPaymentDto();

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeGemiPaymentService.updateIncomeGemiPayment(id, requestDto));

        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentMapper, never()).partialUpdate(any(), any());
        verify(incomeGemiPaymentRepository, never()).save(any());
    }

    @Test
    void shouldDeleteIncomeGemiPaymentWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeGemiPayment existingEntity = createSampleIncomeGemiPaymentEntity();

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.of(existingEntity));

        incomeGemiPaymentService.deleteIncomeGemiPayment(id);

        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteIncomeGemiPaymentCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeGemiPaymentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeGemiPaymentService.deleteIncomeGemiPayment(id));

        verify(incomeGemiPaymentRepository).findById(id);
        verify(incomeGemiPaymentRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated IncomeGemiPayment fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeGemiPayment createSampleIncomeGemiPaymentEntity() {
        IncomeGemiPayment entity = new IncomeGemiPayment();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setPaymentType("paymentType-value-1");
        entity.setSaleTs(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setChamberAmount(new BigDecimal("1.00"));
        entity.setChamberAmountForCerts(new BigDecimal("1.00"));
        entity.setChamberAmountForPostal(new BigDecimal("1.00"));
        entity.setTotalAmountPaid(new BigDecimal("1.00"));
        entity.setDescr("descr-value-1");
        entity.setPayer("payer-value-1");
        entity.setGemiPaymentId(new BigInteger("1"));
        entity.setCompanyGemiId(new BigInteger("1"));
        entity.setCoName("coName-value-1");
        entity.setCompanyChamberId(new BigInteger("1"));
        entity.setPaymentMethod("paymentMethod-value-1");
        entity.setRi3("ri3-value-1");
        entity.setSubscriptionStartDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setSubscriptionEndDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setCancelFlag(new BigInteger("1"));
        entity.setRefundTs(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRemittanceDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRemittanceAmount(new BigDecimal("1.00"));
        entity.setRemittanceReference("remittanceReference-value-1");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated IncomeGemiPayment fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeGemiPayment createAnotherIncomeGemiPaymentEntity() {
        IncomeGemiPayment entity = new IncomeGemiPayment();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setPaymentType("paymentType-value-2");
        entity.setSaleTs(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setChamberAmount(new BigDecimal("2.00"));
        entity.setChamberAmountForCerts(new BigDecimal("2.00"));
        entity.setChamberAmountForPostal(new BigDecimal("2.00"));
        entity.setTotalAmountPaid(new BigDecimal("2.00"));
        entity.setDescr("descr-value-2");
        entity.setPayer("payer-value-2");
        entity.setGemiPaymentId(new BigInteger("2"));
        entity.setCompanyGemiId(new BigInteger("2"));
        entity.setCoName("coName-value-2");
        entity.setCompanyChamberId(new BigInteger("2"));
        entity.setPaymentMethod("paymentMethod-value-2");
        entity.setRi3("ri3-value-2");
        entity.setSubscriptionStartDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setSubscriptionEndDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setCancelFlag(new BigInteger("2"));
        entity.setRefundTs(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRemittanceDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRemittanceAmount(new BigDecimal("2.00"));
        entity.setRemittanceReference("remittanceReference-value-2");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createSampleIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setPaymentType("paymentType-value-1");
        dto.setSaleTs(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberAmount(new BigDecimal("1.00"));
        dto.setChamberAmountForCerts(new BigDecimal("1.00"));
        dto.setChamberAmountForPostal(new BigDecimal("1.00"));
        dto.setTotalAmountPaid(new BigDecimal("1.00"));
        dto.setDescr("descr-value-1");
        dto.setPayer("payer-value-1");
        dto.setGemiPaymentId(new BigInteger("1"));
        dto.setCompanyGemiId(new BigInteger("1"));
        dto.setCoName("coName-value-1");
        dto.setCompanyChamberId(new BigInteger("1"));
        dto.setPaymentMethod("paymentMethod-value-1");
        dto.setRi3("ri3-value-1");
        dto.setSubscriptionStartDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setSubscriptionEndDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCancelFlag(new BigInteger("1"));
        dto.setRefundTs(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRemittanceDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRemittanceAmount(new BigDecimal("1.00"));
        dto.setRemittanceReference("remittanceReference-value-1");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createAnotherIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setPaymentType("paymentType-value-2");
        dto.setSaleTs(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberAmount(new BigDecimal("2.00"));
        dto.setChamberAmountForCerts(new BigDecimal("2.00"));
        dto.setChamberAmountForPostal(new BigDecimal("2.00"));
        dto.setTotalAmountPaid(new BigDecimal("2.00"));
        dto.setDescr("descr-value-2");
        dto.setPayer("payer-value-2");
        dto.setGemiPaymentId(new BigInteger("2"));
        dto.setCompanyGemiId(new BigInteger("2"));
        dto.setCoName("coName-value-2");
        dto.setCompanyChamberId(new BigInteger("2"));
        dto.setPaymentMethod("paymentMethod-value-2");
        dto.setRi3("ri3-value-2");
        dto.setSubscriptionStartDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setSubscriptionEndDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCancelFlag(new BigInteger("2"));
        dto.setRefundTs(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRemittanceDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRemittanceAmount(new BigDecimal("2.00"));
        dto.setRemittanceReference("remittanceReference-value-2");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated IncomeGemiPaymentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeGemiPaymentDto createPatchIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setChamberId(3);
        dto.setPaymentType("paymentType-value-3");
        dto.setSaleTs(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberAmount(new BigDecimal("3.00"));
        dto.setChamberAmountForCerts(new BigDecimal("3.00"));
        dto.setChamberAmountForPostal(new BigDecimal("3.00"));
        dto.setTotalAmountPaid(new BigDecimal("3.00"));
        dto.setDescr("descr-value-3");
        dto.setPayer("payer-value-3");
        dto.setGemiPaymentId(new BigInteger("3"));
        dto.setCompanyGemiId(new BigInteger("3"));
        dto.setCoName("coName-value-3");
        dto.setCompanyChamberId(new BigInteger("3"));
        dto.setPaymentMethod("paymentMethod-value-3");
        dto.setRi3("ri3-value-3");
        dto.setSubscriptionStartDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setSubscriptionEndDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCancelFlag(new BigInteger("3"));
        dto.setRefundTs(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRemittanceDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRemittanceAmount(new BigDecimal("3.00"));
        dto.setRemittanceReference("remittanceReference-value-3");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
