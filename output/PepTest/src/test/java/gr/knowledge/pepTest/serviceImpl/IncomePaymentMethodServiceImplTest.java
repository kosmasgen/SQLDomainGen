package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.repository.IncomePaymentMethodRepository;
import gr.knowledge.pepTest.mapper.IncomePaymentMethodMapper;
import java.util.UUID;
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
class IncomePaymentMethodServiceImplTest {

    @Mock
    private IncomePaymentMethodRepository incomePaymentMethodRepository;

    @Mock
    private IncomePaymentMethodMapper incomePaymentMethodMapper;

    @InjectMocks
    private IncomePaymentMethodServiceImpl incomePaymentMethodService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for IncomePaymentMethod.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("IncomePaymentMethod", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllIncomePaymentMethodsIsCalled() {
        List<IncomePaymentMethod> entityList = List.of(createSampleIncomePaymentMethodEntity(), createAnotherIncomePaymentMethodEntity());
        List<IncomePaymentMethodDto> dtoList = List.of(createSampleIncomePaymentMethodDto(), createAnotherIncomePaymentMethodDto());

        given(incomePaymentMethodRepository.findAll()).willReturn(entityList);
        given(incomePaymentMethodMapper.toDTOList(entityList)).willReturn(dtoList);

        List<IncomePaymentMethodDto> result = incomePaymentMethodService.getAllIncomePaymentMethods();

        assertSame(dtoList, result);
        verify(incomePaymentMethodRepository).findAll();
        verify(incomePaymentMethodMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetIncomePaymentMethodByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomePaymentMethod incomePaymentMethod = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethodDto incomePaymentMethodDto = createSampleIncomePaymentMethodDto();

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.of(incomePaymentMethod));
        given(incomePaymentMethodMapper.toDTO(incomePaymentMethod)).willReturn(incomePaymentMethodDto);

        IncomePaymentMethodDto result = incomePaymentMethodService.getIncomePaymentMethodById(id);

        assertSame(incomePaymentMethodDto, result);
        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodMapper).toDTO(incomePaymentMethod);
    }

    @Test
    void shouldThrowWhenGetIncomePaymentMethodByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomePaymentMethodService.getIncomePaymentMethodById(id));

        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodMapper, never()).toDTO(any(IncomePaymentMethod.class));
    }

    @Test
    void shouldCreateIncomePaymentMethodWhenCreateIncomePaymentMethodIsCalled() {
        IncomePaymentMethodDto requestDto = createSampleIncomePaymentMethodDto();
        IncomePaymentMethod mappedEntity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethod savedEntity = createAnotherIncomePaymentMethodEntity();
        IncomePaymentMethodDto responseDto = createAnotherIncomePaymentMethodDto();

        given(incomePaymentMethodMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(incomePaymentMethodRepository.save(mappedEntity)).willReturn(savedEntity);
        given(incomePaymentMethodMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomePaymentMethodDto result = incomePaymentMethodService.createIncomePaymentMethod(requestDto);

        assertSame(responseDto, result);
        verify(incomePaymentMethodMapper).toEntity(requestDto);
        verify(incomePaymentMethodRepository).save(mappedEntity);
        verify(incomePaymentMethodMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateIncomePaymentMethodWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomePaymentMethodDto requestDto = createPatchIncomePaymentMethodDto();
        IncomePaymentMethod existingEntity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethod savedEntity = createAnotherIncomePaymentMethodEntity();
        IncomePaymentMethodDto responseDto = createAnotherIncomePaymentMethodDto();

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(incomePaymentMethodRepository.save(existingEntity)).willReturn(savedEntity);
        given(incomePaymentMethodMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomePaymentMethodDto result = incomePaymentMethodService.updateIncomePaymentMethod(id, requestDto);

        assertSame(responseDto, result);
        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodMapper).partialUpdate(existingEntity, requestDto);
        verify(incomePaymentMethodRepository).save(existingEntity);
        verify(incomePaymentMethodMapper).toDTO(savedEntity);
        verify(incomePaymentMethodMapper, never()).toEntity(any(IncomePaymentMethodDto.class));
    }

    @Test
    void shouldThrowWhenUpdateIncomePaymentMethodCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomePaymentMethodDto requestDto = createPatchIncomePaymentMethodDto();

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomePaymentMethodService.updateIncomePaymentMethod(id, requestDto));

        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodMapper, never()).partialUpdate(any(), any());
        verify(incomePaymentMethodRepository, never()).save(any());
    }

    @Test
    void shouldDeleteIncomePaymentMethodWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomePaymentMethod existingEntity = createSampleIncomePaymentMethodEntity();

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.of(existingEntity));

        incomePaymentMethodService.deleteIncomePaymentMethod(id);

        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteIncomePaymentMethodCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomePaymentMethodRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomePaymentMethodService.deleteIncomePaymentMethod(id));

        verify(incomePaymentMethodRepository).findById(id);
        verify(incomePaymentMethodRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated IncomePaymentMethod fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomePaymentMethod createSampleIncomePaymentMethodEntity() {
        IncomePaymentMethod entity = new IncomePaymentMethod();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberPayMethodId(1);
        entity.setDescription("description-value-1");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(1);

        return entity;
    }

    /**
     * Creates a populated IncomePaymentMethod fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomePaymentMethod createAnotherIncomePaymentMethodEntity() {
        IncomePaymentMethod entity = new IncomePaymentMethod();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberPayMethodId(2);
        entity.setDescription("description-value-2");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(2);

        return entity;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createSampleIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberPayMethodId(1);
        dto.setDescription("description-value-1");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(1);

        return dto;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createAnotherIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberPayMethodId(2);
        dto.setDescription("description-value-2");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(2);

        return dto;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createPatchIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setChamberId(3);
        dto.setChamberPayMethodId(3);
        dto.setDescription("description-value-3");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(3);

        return dto;
    }

}
