package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.IncomeType;
import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.repository.IncomeTypeRepository;
import gr.knowledge.pepTest.mapper.IncomeTypeMapper;
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
class IncomeTypeServiceImplTest {

    @Mock
    private IncomeTypeRepository incomeTypeRepository;

    @Mock
    private IncomeTypeMapper incomeTypeMapper;

    @InjectMocks
    private IncomeTypeServiceImpl incomeTypeService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for IncomeType.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("IncomeType", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllIncomeTypesIsCalled() {
        List<IncomeType> entityList = List.of(createSampleIncomeTypeEntity(), createAnotherIncomeTypeEntity());
        List<IncomeTypeDto> dtoList = List.of(createSampleIncomeTypeDto(), createAnotherIncomeTypeDto());

        given(incomeTypeRepository.findAll()).willReturn(entityList);
        given(incomeTypeMapper.toDTOList(entityList)).willReturn(dtoList);

        List<IncomeTypeDto> result = incomeTypeService.getAllIncomeTypes();

        assertSame(dtoList, result);
        verify(incomeTypeRepository).findAll();
        verify(incomeTypeMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetIncomeTypeByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeType incomeType = createSampleIncomeTypeEntity();
        IncomeTypeDto incomeTypeDto = createSampleIncomeTypeDto();

        given(incomeTypeRepository.findById(id)).willReturn(Optional.of(incomeType));
        given(incomeTypeMapper.toDTO(incomeType)).willReturn(incomeTypeDto);

        IncomeTypeDto result = incomeTypeService.getIncomeTypeById(id);

        assertSame(incomeTypeDto, result);
        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeMapper).toDTO(incomeType);
    }

    @Test
    void shouldThrowWhenGetIncomeTypeByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeTypeRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTypeService.getIncomeTypeById(id));

        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeMapper, never()).toDTO(any(IncomeType.class));
    }

    @Test
    void shouldCreateIncomeTypeWhenCreateIncomeTypeIsCalled() {
        IncomeTypeDto requestDto = createSampleIncomeTypeDto();
        IncomeType mappedEntity = createSampleIncomeTypeEntity();
        IncomeType savedEntity = createAnotherIncomeTypeEntity();
        IncomeTypeDto responseDto = createAnotherIncomeTypeDto();

        given(incomeTypeMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(incomeTypeRepository.save(mappedEntity)).willReturn(savedEntity);
        given(incomeTypeMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeTypeDto result = incomeTypeService.createIncomeType(requestDto);

        assertSame(responseDto, result);
        verify(incomeTypeMapper).toEntity(requestDto);
        verify(incomeTypeRepository).save(mappedEntity);
        verify(incomeTypeMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateIncomeTypeWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTypeDto requestDto = createPatchIncomeTypeDto();
        IncomeType existingEntity = createSampleIncomeTypeEntity();
        IncomeType savedEntity = createAnotherIncomeTypeEntity();
        IncomeTypeDto responseDto = createAnotherIncomeTypeDto();

        given(incomeTypeRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(incomeTypeRepository.save(existingEntity)).willReturn(savedEntity);
        given(incomeTypeMapper.toDTO(savedEntity)).willReturn(responseDto);

        IncomeTypeDto result = incomeTypeService.updateIncomeType(id, requestDto);

        assertSame(responseDto, result);
        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeMapper).partialUpdate(existingEntity, requestDto);
        verify(incomeTypeRepository).save(existingEntity);
        verify(incomeTypeMapper).toDTO(savedEntity);
        verify(incomeTypeMapper, never()).toEntity(any(IncomeTypeDto.class));
    }

    @Test
    void shouldThrowWhenUpdateIncomeTypeCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeTypeDto requestDto = createPatchIncomeTypeDto();

        given(incomeTypeRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTypeService.updateIncomeType(id, requestDto));

        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeMapper, never()).partialUpdate(any(), any());
        verify(incomeTypeRepository, never()).save(any());
    }

    @Test
    void shouldDeleteIncomeTypeWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        IncomeType existingEntity = createSampleIncomeTypeEntity();

        given(incomeTypeRepository.findById(id)).willReturn(Optional.of(existingEntity));

        incomeTypeService.deleteIncomeType(id);

        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteIncomeTypeCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(incomeTypeRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> incomeTypeService.deleteIncomeType(id));

        verify(incomeTypeRepository).findById(id);
        verify(incomeTypeRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated IncomeType fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeType createSampleIncomeTypeEntity() {
        IncomeType entity = new IncomeType();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberTypeId(1);
        entity.setDescription("description-value-1");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated IncomeType fixture for service tests.
     *
     * @return populated entity fixture
     */
    private IncomeType createAnotherIncomeTypeEntity() {
        IncomeType entity = new IncomeType();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberTypeId(2);
        entity.setDescription("description-value-2");
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createSampleIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberTypeId(1);
        dto.setDescription("description-value-1");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createAnotherIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberTypeId(2);
        dto.setDescription("description-value-2");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createPatchIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setChamberId(3);
        dto.setChamberTypeId(3);
        dto.setDescription("description-value-3");
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
