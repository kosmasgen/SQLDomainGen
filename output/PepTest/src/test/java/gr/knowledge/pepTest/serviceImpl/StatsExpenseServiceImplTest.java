package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.StatsExpense;
import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.repository.StatsExpenseRepository;
import gr.knowledge.pepTest.mapper.StatsExpenseMapper;
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
class StatsExpenseServiceImplTest {

    @Mock
    private StatsExpenseRepository statsExpenseRepository;

    @Mock
    private StatsExpenseMapper statsExpenseMapper;

    @InjectMocks
    private StatsExpenseServiceImpl statsExpenseService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for StatsExpense.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("StatsExpense", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllStatsExpensesIsCalled() {
        List<StatsExpense> entityList = List.of(createSampleStatsExpenseEntity(), createAnotherStatsExpenseEntity());
        List<StatsExpenseDto> dtoList = List.of(createSampleStatsExpenseDto(), createAnotherStatsExpenseDto());

        given(statsExpenseRepository.findAll()).willReturn(entityList);
        given(statsExpenseMapper.toDTOList(entityList)).willReturn(dtoList);

        List<StatsExpenseDto> result = statsExpenseService.getAllStatsExpenses();

        assertSame(dtoList, result);
        verify(statsExpenseRepository).findAll();
        verify(statsExpenseMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetStatsExpenseByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatsExpense statsExpense = createSampleStatsExpenseEntity();
        StatsExpenseDto statsExpenseDto = createSampleStatsExpenseDto();

        given(statsExpenseRepository.findById(id)).willReturn(Optional.of(statsExpense));
        given(statsExpenseMapper.toDTO(statsExpense)).willReturn(statsExpenseDto);

        StatsExpenseDto result = statsExpenseService.getStatsExpenseById(id);

        assertSame(statsExpenseDto, result);
        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseMapper).toDTO(statsExpense);
    }

    @Test
    void shouldThrowWhenGetStatsExpenseByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(statsExpenseRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statsExpenseService.getStatsExpenseById(id));

        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseMapper, never()).toDTO(any(StatsExpense.class));
    }

    @Test
    void shouldCreateStatsExpenseWhenCreateStatsExpenseIsCalled() {
        StatsExpenseDto requestDto = createSampleStatsExpenseDto();
        StatsExpense mappedEntity = createSampleStatsExpenseEntity();
        StatsExpense savedEntity = createAnotherStatsExpenseEntity();
        StatsExpenseDto responseDto = createAnotherStatsExpenseDto();

        given(statsExpenseMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(statsExpenseRepository.save(mappedEntity)).willReturn(savedEntity);
        given(statsExpenseMapper.toDTO(savedEntity)).willReturn(responseDto);

        StatsExpenseDto result = statsExpenseService.createStatsExpense(requestDto);

        assertSame(responseDto, result);
        verify(statsExpenseMapper).toEntity(requestDto);
        verify(statsExpenseRepository).save(mappedEntity);
        verify(statsExpenseMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateStatsExpenseWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatsExpenseDto requestDto = createPatchStatsExpenseDto();
        StatsExpense existingEntity = createSampleStatsExpenseEntity();
        StatsExpense savedEntity = createAnotherStatsExpenseEntity();
        StatsExpenseDto responseDto = createAnotherStatsExpenseDto();

        given(statsExpenseRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(statsExpenseRepository.save(existingEntity)).willReturn(savedEntity);
        given(statsExpenseMapper.toDTO(savedEntity)).willReturn(responseDto);

        StatsExpenseDto result = statsExpenseService.updateStatsExpense(id, requestDto);

        assertSame(responseDto, result);
        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseMapper).partialUpdate(existingEntity, requestDto);
        verify(statsExpenseRepository).save(existingEntity);
        verify(statsExpenseMapper).toDTO(savedEntity);
        verify(statsExpenseMapper, never()).toEntity(any(StatsExpenseDto.class));
    }

    @Test
    void shouldThrowWhenUpdateStatsExpenseCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatsExpenseDto requestDto = createPatchStatsExpenseDto();

        given(statsExpenseRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statsExpenseService.updateStatsExpense(id, requestDto));

        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseMapper, never()).partialUpdate(any(), any());
        verify(statsExpenseRepository, never()).save(any());
    }

    @Test
    void shouldDeleteStatsExpenseWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatsExpense existingEntity = createSampleStatsExpenseEntity();

        given(statsExpenseRepository.findById(id)).willReturn(Optional.of(existingEntity));

        statsExpenseService.deleteStatsExpense(id);

        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteStatsExpenseCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(statsExpenseRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statsExpenseService.deleteStatsExpense(id));

        verify(statsExpenseRepository).findById(id);
        verify(statsExpenseRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated StatsExpense fixture for service tests.
     *
     * @return populated entity fixture
     */
    private StatsExpense createSampleStatsExpenseEntity() {
        StatsExpense entity = new StatsExpense();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setAccountSumId(new BigInteger("1"));
        entity.setCdUse("cdUse-value-1");
        entity.setGroupDescr("groupDescr-value-1");
        entity.setMm("mm-value-1");
        entity.setAmount(new BigDecimal("1.00"));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("1"));

        return entity;
    }

    /**
     * Creates a populated StatsExpense fixture for service tests.
     *
     * @return populated entity fixture
     */
    private StatsExpense createAnotherStatsExpenseEntity() {
        StatsExpense entity = new StatsExpense();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setAccountSumId(new BigInteger("2"));
        entity.setCdUse("cdUse-value-2");
        entity.setGroupDescr("groupDescr-value-2");
        entity.setMm("mm-value-2");
        entity.setAmount(new BigDecimal("2.00"));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("2"));

        return entity;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createSampleStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setAccountSumId(new BigInteger("1"));
        dto.setCdUse("cdUse-value-1");
        dto.setGroupDescr("groupDescr-value-1");
        dto.setMm("mm-value-1");
        dto.setAmount(new BigDecimal("1.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createAnotherStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setAccountSumId(new BigInteger("2"));
        dto.setCdUse("cdUse-value-2");
        dto.setGroupDescr("groupDescr-value-2");
        dto.setMm("mm-value-2");
        dto.setAmount(new BigDecimal("2.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("2"));

        return dto;
    }

    /**
     * Creates a populated StatsExpenseDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatsExpenseDto createPatchStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setChamberId(3);
        dto.setAccountSumId(new BigInteger("3"));
        dto.setCdUse("cdUse-value-3");
        dto.setGroupDescr("groupDescr-value-3");
        dto.setMm("mm-value-3");
        dto.setAmount(new BigDecimal("3.00"));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("3"));

        return dto;
    }

}
