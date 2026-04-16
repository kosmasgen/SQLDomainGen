package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.StatusHistory;
import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.repository.StatusHistoryRepository;
import gr.knowledge.pepTest.mapper.StatusHistoryMapper;
import java.util.UUID;
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
class StatusHistoryServiceImplTest {

    @Mock
    private StatusHistoryRepository statusHistoryRepository;

    @Mock
    private StatusHistoryMapper statusHistoryMapper;

    @InjectMocks
    private StatusHistoryServiceImpl statusHistoryService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for StatusHistory.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("StatusHistory", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllStatusHistoriesIsCalled() {
        List<StatusHistory> entityList = List.of(createSampleStatusHistoryEntity(), createAnotherStatusHistoryEntity());
        List<StatusHistoryDto> dtoList = List.of(createSampleStatusHistoryDto(), createAnotherStatusHistoryDto());

        given(statusHistoryRepository.findAll()).willReturn(entityList);
        given(statusHistoryMapper.toDTOList(entityList)).willReturn(dtoList);

        List<StatusHistoryDto> result = statusHistoryService.getAllStatusHistories();

        assertSame(dtoList, result);
        verify(statusHistoryRepository).findAll();
        verify(statusHistoryMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetStatusHistoryByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatusHistory statusHistory = createSampleStatusHistoryEntity();
        StatusHistoryDto statusHistoryDto = createSampleStatusHistoryDto();

        given(statusHistoryRepository.findById(id)).willReturn(Optional.of(statusHistory));
        given(statusHistoryMapper.toDTO(statusHistory)).willReturn(statusHistoryDto);

        StatusHistoryDto result = statusHistoryService.getStatusHistoryById(id);

        assertSame(statusHistoryDto, result);
        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryMapper).toDTO(statusHistory);
    }

    @Test
    void shouldThrowWhenGetStatusHistoryByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(statusHistoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statusHistoryService.getStatusHistoryById(id));

        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryMapper, never()).toDTO(any(StatusHistory.class));
    }

    @Test
    void shouldCreateStatusHistoryWhenCreateStatusHistoryIsCalled() {
        StatusHistoryDto requestDto = createSampleStatusHistoryDto();
        StatusHistory mappedEntity = createSampleStatusHistoryEntity();
        StatusHistory savedEntity = createAnotherStatusHistoryEntity();
        StatusHistoryDto responseDto = createAnotherStatusHistoryDto();

        given(statusHistoryMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(statusHistoryRepository.save(mappedEntity)).willReturn(savedEntity);
        given(statusHistoryMapper.toDTO(savedEntity)).willReturn(responseDto);

        StatusHistoryDto result = statusHistoryService.createStatusHistory(requestDto);

        assertSame(responseDto, result);
        verify(statusHistoryMapper).toEntity(requestDto);
        verify(statusHistoryRepository).save(mappedEntity);
        verify(statusHistoryMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateStatusHistoryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatusHistoryDto requestDto = createPatchStatusHistoryDto();
        StatusHistory existingEntity = createSampleStatusHistoryEntity();
        StatusHistory savedEntity = createAnotherStatusHistoryEntity();
        StatusHistoryDto responseDto = createAnotherStatusHistoryDto();

        given(statusHistoryRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(statusHistoryRepository.save(existingEntity)).willReturn(savedEntity);
        given(statusHistoryMapper.toDTO(savedEntity)).willReturn(responseDto);

        StatusHistoryDto result = statusHistoryService.updateStatusHistory(id, requestDto);

        assertSame(responseDto, result);
        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryMapper).partialUpdate(existingEntity, requestDto);
        verify(statusHistoryRepository).save(existingEntity);
        verify(statusHistoryMapper).toDTO(savedEntity);
        verify(statusHistoryMapper, never()).toEntity(any(StatusHistoryDto.class));
    }

    @Test
    void shouldThrowWhenUpdateStatusHistoryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatusHistoryDto requestDto = createPatchStatusHistoryDto();

        given(statusHistoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statusHistoryService.updateStatusHistory(id, requestDto));

        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryMapper, never()).partialUpdate(any(), any());
        verify(statusHistoryRepository, never()).save(any());
    }

    @Test
    void shouldDeleteStatusHistoryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        StatusHistory existingEntity = createSampleStatusHistoryEntity();

        given(statusHistoryRepository.findById(id)).willReturn(Optional.of(existingEntity));

        statusHistoryService.deleteStatusHistory(id);

        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteStatusHistoryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(statusHistoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> statusHistoryService.deleteStatusHistory(id));

        verify(statusHistoryRepository).findById(id);
        verify(statusHistoryRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated StatusHistory fixture for service tests.
     *
     * @return populated entity fixture
     */
    private StatusHistory createSampleStatusHistoryEntity() {
        StatusHistory entity = new StatusHistory();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberStatusHistoryId(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setNotes("notes-value-1");
        entity.setRegDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setStartDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setActionNo("actionNo-value-1");

        return entity;
    }

    /**
     * Creates a populated StatusHistory fixture for service tests.
     *
     * @return populated entity fixture
     */
    private StatusHistory createAnotherStatusHistoryEntity() {
        StatusHistory entity = new StatusHistory();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberStatusHistoryId(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setNotes("notes-value-2");
        entity.setRegDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setStartDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setActionNo("actionNo-value-2");

        return entity;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createSampleStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberStatusHistoryId(new BigInteger("1"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setNotes("notes-value-1");
        dto.setRegDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setStartDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setActionNo("actionNo-value-1");

        return dto;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createAnotherStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberStatusHistoryId(new BigInteger("2"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setNotes("notes-value-2");
        dto.setRegDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setStartDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setActionNo("actionNo-value-2");

        return dto;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createPatchStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setChamberId(3);
        dto.setChamberStatusHistoryId(new BigInteger("3"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setNotes("notes-value-3");
        dto.setRegDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setStartDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setActionNo("actionNo-value-3");

        return dto;
    }

}
