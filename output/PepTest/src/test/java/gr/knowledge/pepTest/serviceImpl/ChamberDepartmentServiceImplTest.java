package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.repository.ChamberDepartmentRepository;
import gr.knowledge.pepTest.mapper.ChamberDepartmentMapper;
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
class ChamberDepartmentServiceImplTest {

    @Mock
    private ChamberDepartmentRepository chamberDepartmentRepository;

    @Mock
    private ChamberDepartmentMapper chamberDepartmentMapper;

    @InjectMocks
    private ChamberDepartmentServiceImpl chamberDepartmentService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ChamberDepartment.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ChamberDepartment", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllChamberDepartmentsIsCalled() {
        List<ChamberDepartment> entityList = List.of(createSampleChamberDepartmentEntity(), createAnotherChamberDepartmentEntity());
        List<ChamberDepartmentDto> dtoList = List.of(createSampleChamberDepartmentDto(), createAnotherChamberDepartmentDto());

        given(chamberDepartmentRepository.findAll()).willReturn(entityList);
        given(chamberDepartmentMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ChamberDepartmentDto> result = chamberDepartmentService.getAllChamberDepartments();

        assertSame(dtoList, result);
        verify(chamberDepartmentRepository).findAll();
        verify(chamberDepartmentMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetChamberDepartmentByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartment chamberDepartment = createSampleChamberDepartmentEntity();
        ChamberDepartmentDto chamberDepartmentDto = createSampleChamberDepartmentDto();

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.of(chamberDepartment));
        given(chamberDepartmentMapper.toDTO(chamberDepartment)).willReturn(chamberDepartmentDto);

        ChamberDepartmentDto result = chamberDepartmentService.getChamberDepartmentById(id);

        assertSame(chamberDepartmentDto, result);
        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentMapper).toDTO(chamberDepartment);
    }

    @Test
    void shouldThrowWhenGetChamberDepartmentByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmentService.getChamberDepartmentById(id));

        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentMapper, never()).toDTO(any(ChamberDepartment.class));
    }

    @Test
    void shouldCreateChamberDepartmentWhenCreateChamberDepartmentIsCalled() {
        ChamberDepartmentDto requestDto = createSampleChamberDepartmentDto();
        ChamberDepartment mappedEntity = createSampleChamberDepartmentEntity();
        ChamberDepartment savedEntity = createAnotherChamberDepartmentEntity();
        ChamberDepartmentDto responseDto = createAnotherChamberDepartmentDto();

        given(chamberDepartmentMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(chamberDepartmentRepository.save(mappedEntity)).willReturn(savedEntity);
        given(chamberDepartmentMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberDepartmentDto result = chamberDepartmentService.createChamberDepartment(requestDto);

        assertSame(responseDto, result);
        verify(chamberDepartmentMapper).toEntity(requestDto);
        verify(chamberDepartmentRepository).save(mappedEntity);
        verify(chamberDepartmentMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateChamberDepartmentWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmentDto requestDto = createPatchChamberDepartmentDto();
        ChamberDepartment existingEntity = createSampleChamberDepartmentEntity();
        ChamberDepartment savedEntity = createAnotherChamberDepartmentEntity();
        ChamberDepartmentDto responseDto = createAnotherChamberDepartmentDto();

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(chamberDepartmentRepository.save(existingEntity)).willReturn(savedEntity);
        given(chamberDepartmentMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberDepartmentDto result = chamberDepartmentService.updateChamberDepartment(id, requestDto);

        assertSame(responseDto, result);
        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentMapper).partialUpdate(existingEntity, requestDto);
        verify(chamberDepartmentRepository).save(existingEntity);
        verify(chamberDepartmentMapper).toDTO(savedEntity);
        verify(chamberDepartmentMapper, never()).toEntity(any(ChamberDepartmentDto.class));
    }

    @Test
    void shouldThrowWhenUpdateChamberDepartmentCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmentDto requestDto = createPatchChamberDepartmentDto();

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmentService.updateChamberDepartment(id, requestDto));

        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentMapper, never()).partialUpdate(any(), any());
        verify(chamberDepartmentRepository, never()).save(any());
    }

    @Test
    void shouldDeleteChamberDepartmentWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartment existingEntity = createSampleChamberDepartmentEntity();

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.of(existingEntity));

        chamberDepartmentService.deleteChamberDepartment(id);

        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteChamberDepartmentCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chamberDepartmentRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmentService.deleteChamberDepartment(id));

        verify(chamberDepartmentRepository).findById(id);
        verify(chamberDepartmentRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ChamberDepartment fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartment createSampleChamberDepartmentEntity() {
        ChamberDepartment entity = new ChamberDepartment();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberDepartmentId(1);
        entity.setChamberId(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setCd("cd-value-1");

        return entity;
    }

    /**
     * Creates a populated ChamberDepartment fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartment createAnotherChamberDepartmentEntity() {
        ChamberDepartment entity = new ChamberDepartment();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberDepartmentId(2);
        entity.setChamberId(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setCd("cd-value-2");

        return entity;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createSampleChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberDepartmentId(1);
        dto.setChamberId(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCd("cd-value-1");

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createAnotherChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberDepartmentId(2);
        dto.setChamberId(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setCd("cd-value-2");

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createPatchChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setChamberDepartmentId(3);
        dto.setChamberId(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCd("cd-value-3");

        return dto;
    }

}
