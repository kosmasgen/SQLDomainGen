package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.WorkingHours;
import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.repository.WorkingHoursRepository;
import gr.knowledge.pepTest.mapper.WorkingHoursMapper;
import java.util.UUID;
import java.time.LocalTime;

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
class WorkingHoursServiceImplTest {

    @Mock
    private WorkingHoursRepository workingHoursRepository;

    @Mock
    private WorkingHoursMapper workingHoursMapper;

    @InjectMocks
    private WorkingHoursServiceImpl workingHoursService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for WorkingHours.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("WorkingHours", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllWorkingHoursesIsCalled() {
        List<WorkingHours> entityList = List.of(createSampleWorkingHoursEntity(), createAnotherWorkingHoursEntity());
        List<WorkingHoursDto> dtoList = List.of(createSampleWorkingHoursDto(), createAnotherWorkingHoursDto());

        given(workingHoursRepository.findAll()).willReturn(entityList);
        given(workingHoursMapper.toDTOList(entityList)).willReturn(dtoList);

        List<WorkingHoursDto> result = workingHoursService.getAllWorkingHourses();

        assertSame(dtoList, result);
        verify(workingHoursRepository).findAll();
        verify(workingHoursMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetWorkingHoursByIdIsCalled() {
        Long id = 1L;

        WorkingHours workingHours = createSampleWorkingHoursEntity();
        WorkingHoursDto workingHoursDto = createSampleWorkingHoursDto();

        given(workingHoursRepository.findById(id)).willReturn(Optional.of(workingHours));
        given(workingHoursMapper.toDTO(workingHours)).willReturn(workingHoursDto);

        WorkingHoursDto result = workingHoursService.getWorkingHoursById(id);

        assertSame(workingHoursDto, result);
        verify(workingHoursRepository).findById(id);
        verify(workingHoursMapper).toDTO(workingHours);
    }

    @Test
    void shouldThrowWhenGetWorkingHoursByIdCannotFindEntity() {
        Long id = 1L;

        given(workingHoursRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> workingHoursService.getWorkingHoursById(id));

        verify(workingHoursRepository).findById(id);
        verify(workingHoursMapper, never()).toDTO(any(WorkingHours.class));
    }

    @Test
    void shouldCreateWorkingHoursWhenCreateWorkingHoursIsCalled() {
        WorkingHoursDto requestDto = createSampleWorkingHoursDto();
        WorkingHours mappedEntity = createSampleWorkingHoursEntity();
        WorkingHours savedEntity = createAnotherWorkingHoursEntity();
        WorkingHoursDto responseDto = createAnotherWorkingHoursDto();

        given(workingHoursMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(workingHoursRepository.save(mappedEntity)).willReturn(savedEntity);
        given(workingHoursMapper.toDTO(savedEntity)).willReturn(responseDto);

        WorkingHoursDto result = workingHoursService.createWorkingHours(requestDto);

        assertSame(responseDto, result);
        verify(workingHoursMapper).toEntity(requestDto);
        verify(workingHoursRepository).save(mappedEntity);
        verify(workingHoursMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateWorkingHoursWhenEntityExists() {
        Long id = 1L;

        WorkingHoursDto requestDto = createPatchWorkingHoursDto();
        WorkingHours existingEntity = createSampleWorkingHoursEntity();
        WorkingHours savedEntity = createAnotherWorkingHoursEntity();
        WorkingHoursDto responseDto = createAnotherWorkingHoursDto();

        given(workingHoursRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(workingHoursRepository.save(existingEntity)).willReturn(savedEntity);
        given(workingHoursMapper.toDTO(savedEntity)).willReturn(responseDto);

        WorkingHoursDto result = workingHoursService.updateWorkingHours(id, requestDto);

        assertSame(responseDto, result);
        verify(workingHoursRepository).findById(id);
        verify(workingHoursMapper).partialUpdate(existingEntity, requestDto);
        verify(workingHoursRepository).save(existingEntity);
        verify(workingHoursMapper).toDTO(savedEntity);
        verify(workingHoursMapper, never()).toEntity(any(WorkingHoursDto.class));
    }

    @Test
    void shouldThrowWhenUpdateWorkingHoursCannotFindEntity() {
        Long id = 1L;

        WorkingHoursDto requestDto = createPatchWorkingHoursDto();

        given(workingHoursRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> workingHoursService.updateWorkingHours(id, requestDto));

        verify(workingHoursRepository).findById(id);
        verify(workingHoursMapper, never()).partialUpdate(any(), any());
        verify(workingHoursRepository, never()).save(any());
    }

    @Test
    void shouldDeleteWorkingHoursWhenEntityExists() {
        Long id = 1L;

        WorkingHours existingEntity = createSampleWorkingHoursEntity();

        given(workingHoursRepository.findById(id)).willReturn(Optional.of(existingEntity));

        workingHoursService.deleteWorkingHours(id);

        verify(workingHoursRepository).findById(id);
        verify(workingHoursRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteWorkingHoursCannotFindEntity() {
        Long id = 1L;

        given(workingHoursRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> workingHoursService.deleteWorkingHours(id));

        verify(workingHoursRepository).findById(id);
        verify(workingHoursRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated WorkingHours fixture for service tests.
     *
     * @return populated entity fixture
     */
    private WorkingHours createSampleWorkingHoursEntity() {
        WorkingHours entity = new WorkingHours();
        entity.setId(1L);
        entity.setDayOfWeek("dayOfWeek-value-1");
        entity.setOpeningTime(LocalTime.of(10, 0, 0));
        entity.setClosingTime(LocalTime.of(10, 0, 0));
        entity.setIsClosed(true);

        return entity;
    }

    /**
     * Creates a populated WorkingHours fixture for service tests.
     *
     * @return populated entity fixture
     */
    private WorkingHours createAnotherWorkingHoursEntity() {
        WorkingHours entity = new WorkingHours();
        entity.setId(2L);
        entity.setDayOfWeek("dayOfWeek-value-2");
        entity.setOpeningTime(LocalTime.of(11, 0, 0));
        entity.setClosingTime(LocalTime.of(11, 0, 0));
        entity.setIsClosed(false);

        return entity;
    }

    /**
     * Creates a populated WorkingHoursDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private WorkingHoursDto createSampleWorkingHoursDto() {
        WorkingHoursDto dto = new WorkingHoursDto();
        dto.setId(1L);
        dto.setDayOfWeek("dayOfWeek-value-1");
        dto.setOpeningTime(LocalTime.of(10, 0, 0));
        dto.setClosingTime(LocalTime.of(10, 0, 0));
        dto.setIsClosed(true);

        return dto;
    }

    /**
     * Creates a populated WorkingHoursDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private WorkingHoursDto createAnotherWorkingHoursDto() {
        WorkingHoursDto dto = new WorkingHoursDto();
        dto.setId(2L);
        dto.setDayOfWeek("dayOfWeek-value-2");
        dto.setOpeningTime(LocalTime.of(11, 0, 0));
        dto.setClosingTime(LocalTime.of(11, 0, 0));
        dto.setIsClosed(false);

        return dto;
    }

    /**
     * Creates a populated WorkingHoursDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private WorkingHoursDto createPatchWorkingHoursDto() {
        WorkingHoursDto dto = new WorkingHoursDto();
        dto.setDayOfWeek("dayOfWeek-value-3");
        dto.setOpeningTime(LocalTime.of(12, 0, 0));
        dto.setClosingTime(LocalTime.of(12, 0, 0));
        dto.setIsClosed(true);

        return dto;
    }

}
