package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.SyncWatermarks;
import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.repository.SyncWatermarksRepository;
import gr.knowledge.pepTest.mapper.SyncWatermarksMapper;
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
class SyncWatermarksServiceImplTest {

    @Mock
    private SyncWatermarksRepository syncWatermarksRepository;

    @Mock
    private SyncWatermarksMapper syncWatermarksMapper;

    @InjectMocks
    private SyncWatermarksServiceImpl syncWatermarksService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for SyncWatermarks.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("SyncWatermarks", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllSyncWatermarksesIsCalled() {
        List<SyncWatermarks> entityList = List.of(createSampleSyncWatermarksEntity(), createAnotherSyncWatermarksEntity());
        List<SyncWatermarksDto> dtoList = List.of(createSampleSyncWatermarksDto(), createAnotherSyncWatermarksDto());

        given(syncWatermarksRepository.findAll()).willReturn(entityList);
        given(syncWatermarksMapper.toDTOList(entityList)).willReturn(dtoList);

        List<SyncWatermarksDto> result = syncWatermarksService.getAllSyncWatermarkses();

        assertSame(dtoList, result);
        verify(syncWatermarksRepository).findAll();
        verify(syncWatermarksMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetSyncWatermarksByIdIsCalled() {
        Long id = 1L;

        SyncWatermarks syncWatermarks = createSampleSyncWatermarksEntity();
        SyncWatermarksDto syncWatermarksDto = createSampleSyncWatermarksDto();

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.of(syncWatermarks));
        given(syncWatermarksMapper.toDTO(syncWatermarks)).willReturn(syncWatermarksDto);

        SyncWatermarksDto result = syncWatermarksService.getSyncWatermarksById(id);

        assertSame(syncWatermarksDto, result);
        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksMapper).toDTO(syncWatermarks);
    }

    @Test
    void shouldThrowWhenGetSyncWatermarksByIdCannotFindEntity() {
        Long id = 1L;

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncWatermarksService.getSyncWatermarksById(id));

        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksMapper, never()).toDTO(any(SyncWatermarks.class));
    }

    @Test
    void shouldCreateSyncWatermarksWhenCreateSyncWatermarksIsCalled() {
        SyncWatermarksDto requestDto = createSampleSyncWatermarksDto();
        SyncWatermarks mappedEntity = createSampleSyncWatermarksEntity();
        SyncWatermarks savedEntity = createAnotherSyncWatermarksEntity();
        SyncWatermarksDto responseDto = createAnotherSyncWatermarksDto();

        given(syncWatermarksMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(syncWatermarksRepository.save(mappedEntity)).willReturn(savedEntity);
        given(syncWatermarksMapper.toDTO(savedEntity)).willReturn(responseDto);

        SyncWatermarksDto result = syncWatermarksService.createSyncWatermarks(requestDto);

        assertSame(responseDto, result);
        verify(syncWatermarksMapper).toEntity(requestDto);
        verify(syncWatermarksRepository).save(mappedEntity);
        verify(syncWatermarksMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateSyncWatermarksWhenEntityExists() {
        Long id = 1L;

        SyncWatermarksDto requestDto = createPatchSyncWatermarksDto();
        SyncWatermarks existingEntity = createSampleSyncWatermarksEntity();
        SyncWatermarks savedEntity = createAnotherSyncWatermarksEntity();
        SyncWatermarksDto responseDto = createAnotherSyncWatermarksDto();

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(syncWatermarksRepository.save(existingEntity)).willReturn(savedEntity);
        given(syncWatermarksMapper.toDTO(savedEntity)).willReturn(responseDto);

        SyncWatermarksDto result = syncWatermarksService.updateSyncWatermarks(id, requestDto);

        assertSame(responseDto, result);
        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksMapper).partialUpdate(existingEntity, requestDto);
        verify(syncWatermarksRepository).save(existingEntity);
        verify(syncWatermarksMapper).toDTO(savedEntity);
        verify(syncWatermarksMapper, never()).toEntity(any(SyncWatermarksDto.class));
    }

    @Test
    void shouldThrowWhenUpdateSyncWatermarksCannotFindEntity() {
        Long id = 1L;

        SyncWatermarksDto requestDto = createPatchSyncWatermarksDto();

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncWatermarksService.updateSyncWatermarks(id, requestDto));

        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksMapper, never()).partialUpdate(any(), any());
        verify(syncWatermarksRepository, never()).save(any());
    }

    @Test
    void shouldDeleteSyncWatermarksWhenEntityExists() {
        Long id = 1L;

        SyncWatermarks existingEntity = createSampleSyncWatermarksEntity();

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.of(existingEntity));

        syncWatermarksService.deleteSyncWatermarks(id);

        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteSyncWatermarksCannotFindEntity() {
        Long id = 1L;

        given(syncWatermarksRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncWatermarksService.deleteSyncWatermarks(id));

        verify(syncWatermarksRepository).findById(id);
        verify(syncWatermarksRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated SyncWatermarks fixture for service tests.
     *
     * @return populated entity fixture
     */
    private SyncWatermarks createSampleSyncWatermarksEntity() {
        SyncWatermarks entity = new SyncWatermarks();
        entity.setId(1L);
        entity.setTableName("tableName-value-1");
        entity.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setUpdatedAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated SyncWatermarks fixture for service tests.
     *
     * @return populated entity fixture
     */
    private SyncWatermarks createAnotherSyncWatermarksEntity() {
        SyncWatermarks entity = new SyncWatermarks();
        entity.setId(2L);
        entity.setTableName("tableName-value-2");
        entity.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setUpdatedAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createSampleSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setId(1L);
        dto.setTableName("tableName-value-1");
        dto.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setUpdatedAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createAnotherSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setId(2L);
        dto.setTableName("tableName-value-2");
        dto.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setUpdatedAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createPatchSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setTableName("tableName-value-3");
        dto.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setUpdatedAt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
