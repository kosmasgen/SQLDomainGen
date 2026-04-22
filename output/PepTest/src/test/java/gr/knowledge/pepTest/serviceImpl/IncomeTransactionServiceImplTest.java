package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.IncomeTransaction;
import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.repository.IncomeTransactionRepository;
import gr.knowledge.pepTest.mapper.IncomeTransactionMapper;
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
class IncomeTransactionServiceImplTest {

    @Mock
    private IncomeTransactionRepository incomeTransactionRepository;

    @Mock
    private IncomeTransactionMapper incomeTransactionMapper;

    @InjectMocks
    private IncomeTransactionServiceImpl incomeTransactionService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for IncomeTransaction.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("IncomeTransaction", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllIncomeTransactionsIsCalled() {
        List<IncomeTransaction> entityList = List.of(createSampleIncomeTransactionEntity(), createAnotherIncomeTransactionEntity());
        List<IncomeTransactionDto> dtoList = List.of(createSampleIncomeTransactionDto(), createAnotherIncomeTransactionDto());

        given(incomeTransactionRepository.findAll()).willReturn(entityList);
        given(incomeTransactionMapper.toDTOList(entityList)).willReturn(dtoList);

        List<IncomeTransactionDto> result = incomeTransactionService.getAllIncomeTransactions();

        assertSame(dtoList, result);
        verify(incomeTransactionRepository).findAll();
        verify(incomeTransactionMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetIncomeTransactionByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTransaction incomeTransaction = createSampleIncomeTransactionEntity();
        IncomeTransactionDto incomeTransactionDto = createSampleIncomeTransactionDto();

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.of(incomeTransaction));
        given(incomeTransactionMapper.toDTO(incomeTransaction)).willReturn(incomeTransactionDto);

        IncomeTransactionDto result = incomeTransactionService.getIncomeTransactionById(id);

        assertSame(incomeTransactionDto, result);
        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionMapper).toDTO(incomeTransaction);
    }

    @Test
    void shouldThrowWhenGetIncomeTransactionByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTransactionService.getIncomeTransactionById(id));

        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionMapper, never()).toDTO(any(IncomeTransaction.class));
    }

    @Test
    void shouldCreateIncomeTransactionWhenCreateIncomeTransactionIsCalled() {
        IncomeTransactionDto requestDto = createSampleIncomeTransactionDto();
        IncomeTransaction mappedEntity = createSampleIncomeTransactionEntity();
        IncomeTransaction savedEntity = createAnotherIncomeTransactionEntity();
        IncomeTransactionDto responseDto = createAnotherIncomeTransactionDto();

        given(incomeTransactionMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(incomeTransactionRepository.save(mappedEntity)).willReturn(savedEntity);
        given(incomeTransactionMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeTransactionDto result = incomeTransactionService.createIncomeTransaction(requestDto);

        assertSame(responseDto, result);
        verify(incomeTransactionMapper).toEntity(requestDto);
        verify(incomeTransactionRepository).save(mappedEntity);
        verify(incomeTransactionMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateIncomeTransactionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTransactionDto requestDto = createPatchIncomeTransactionDto();
        IncomeTransaction existingEntity = createSampleIncomeTransactionEntity();
        IncomeTransaction savedEntity = createAnotherIncomeTransactionEntity();
        IncomeTransactionDto responseDto = createAnotherIncomeTransactionDto();

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(incomeTransactionRepository.save(existingEntity)).willReturn(savedEntity);
        given(incomeTransactionMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeTransactionDto result = incomeTransactionService.updateIncomeTransaction(id, requestDto);

        assertSame(responseDto, result);
        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionMapper).partialUpdate(existingEntity, requestDto);
        verify(incomeTransactionRepository).save(existingEntity);
        verify(incomeTransactionMapper).toDTO(savedEntity);
        verify(incomeTransactionMapper, never()).toEntity(any(IncomeTransactionDto.class));
    }

    @Test
    void shouldThrowWhenUpdateIncomeTransactionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTransactionDto requestDto = createPatchIncomeTransactionDto();

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTransactionService.updateIncomeTransaction(id, requestDto));

        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionMapper, never()).partialUpdate(any(), any());
        verify(incomeTransactionRepository, never()).save(any());
    }

    @Test
    void shouldDeleteIncomeTransactionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTransaction existingEntity = createSampleIncomeTransactionEntity();

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.of(existingEntity));

        incomeTransactionService.deleteIncomeTransaction(id);

        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteIncomeTransactionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeTransactionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTransactionService.deleteIncomeTransaction(id));

        verify(incomeTransactionRepository).findById(id);
        verify(incomeTransactionRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated IncomeTransaction fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeTransaction createSampleIncomeTransactionEntity() {
        IncomeTransaction entity = new IncomeTransaction();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberInTransdId(new BigInteger("1"));
        entity.setCdUse("cdUse-value-1");
        entity.setDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setIsMember(1);
        entity.setCompanyId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setAccountCd("accountCd-value-1");
        entity.setAmount(new BigDecimal("1.00"));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("1"));
        entity.setIsEchamber(1);
        entity.setBlockSer("blockSer-value-1");
        entity.setIsKratisi(new BigInteger("1"));
        entity.setChamberCompId(new BigInteger("1"));
        entity.setChamberMethod(new BigInteger("1"));
        entity.setChamberType(new BigInteger("1"));

        return entity;
    }

    /**
     * Creates a populated IncomeTransaction fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeTransaction createAnotherIncomeTransactionEntity() {
        IncomeTransaction entity = new IncomeTransaction();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberInTransdId(new BigInteger("2"));
        entity.setCdUse("cdUse-value-2");
        entity.setDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setIsMember(2);
        entity.setCompanyId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setAccountCd("accountCd-value-2");
        entity.setAmount(new BigDecimal("2.00"));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("2"));
        entity.setIsEchamber(2);
        entity.setBlockSer("blockSer-value-2");
        entity.setIsKratisi(new BigInteger("2"));
        entity.setChamberCompId(new BigInteger("2"));
        entity.setChamberMethod(new BigInteger("2"));
        entity.setChamberType(new BigInteger("2"));

        return entity;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createSampleIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberInTransdId(new BigInteger("1"));
        dto.setCdUse("cdUse-value-1");
        dto.setDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setIsMember(1);
        dto.setCompanyId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setAccountCd("accountCd-value-1");
        dto.setIncomeType(new IncomeTypeDto());
        dto.setAmount(new BigDecimal("1.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("1"));
        dto.setIncomePayMethod(new IncomePaymentMethodDto());
        dto.setIsEchamber(1);
        dto.setBlockSer("blockSer-value-1");
        dto.setIsKratisi(new BigInteger("1"));
        dto.setChamberCompId(new BigInteger("1"));
        dto.setChamberMethod(new BigInteger("1"));
        dto.setChamberType(new BigInteger("1"));

        return dto;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createAnotherIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberInTransdId(new BigInteger("2"));
        dto.setCdUse("cdUse-value-2");
        dto.setDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setIsMember(2);
        dto.setCompanyId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setAccountCd("accountCd-value-2");
        dto.setIncomeType(new IncomeTypeDto());
        dto.setAmount(new BigDecimal("2.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("2"));
        dto.setIncomePayMethod(new IncomePaymentMethodDto());
        dto.setIsEchamber(2);
        dto.setBlockSer("blockSer-value-2");
        dto.setIsKratisi(new BigInteger("2"));
        dto.setChamberCompId(new BigInteger("2"));
        dto.setChamberMethod(new BigInteger("2"));
        dto.setChamberType(new BigInteger("2"));

        return dto;
    }

    /**
     * Creates a populated IncomeTransactionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTransactionDto createPatchIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setChamberId(3);
        dto.setChamberInTransdId(new BigInteger("3"));
        dto.setCdUse("cdUse-value-3");
        dto.setDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setIsMember(3);
        dto.setCompanyId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));
        dto.setAccountCd("accountCd-value-3");
        dto.setIncomeType(new IncomeTypeDto());
        dto.setAmount(new BigDecimal("3.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("3"));
        dto.setIncomePayMethod(new IncomePaymentMethodDto());
        dto.setIsEchamber(3);
        dto.setBlockSer("blockSer-value-3");
        dto.setIsKratisi(new BigInteger("3"));
        dto.setChamberCompId(new BigInteger("3"));
        dto.setChamberMethod(new BigInteger("3"));
        dto.setChamberType(new BigInteger("3"));

        return dto;
    }

}
