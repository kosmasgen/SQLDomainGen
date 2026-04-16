package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Syncruns;
import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.repository.SyncrunsRepository;
import gr.knowledge.pepTest.mapper.SyncrunsMapper;
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
class SyncrunsServiceImplTest {

    @Mock
    private SyncrunsRepository syncrunsRepository;

    @Mock
    private SyncrunsMapper syncrunsMapper;

    @InjectMocks
    private SyncrunsServiceImpl syncrunsService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Syncruns.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Syncruns", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllSyncrunsesIsCalled() {
        List<Syncruns> entityList = List.of(createSampleSyncrunsEntity(), createAnotherSyncrunsEntity());
        List<SyncrunsDto> dtoList = List.of(createSampleSyncrunsDto(), createAnotherSyncrunsDto());

        given(syncrunsRepository.findAll()).willReturn(entityList);
        given(syncrunsMapper.toDTOList(entityList)).willReturn(dtoList);

        List<SyncrunsDto> result = syncrunsService.getAllSyncrunses();

        assertSame(dtoList, result);
        verify(syncrunsRepository).findAll();
        verify(syncrunsMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetSyncrunsByIdIsCalled() {
        Long id = 1L;

        Syncruns syncruns = createSampleSyncrunsEntity();
        SyncrunsDto syncrunsDto = createSampleSyncrunsDto();

        given(syncrunsRepository.findById(id)).willReturn(Optional.of(syncruns));
        given(syncrunsMapper.toDTO(syncruns)).willReturn(syncrunsDto);

        SyncrunsDto result = syncrunsService.getSyncrunsById(id);

        assertSame(syncrunsDto, result);
        verify(syncrunsRepository).findById(id);
        verify(syncrunsMapper).toDTO(syncruns);
    }

    @Test
    void shouldThrowWhenGetSyncrunsByIdCannotFindEntity() {
        Long id = 1L;

        given(syncrunsRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncrunsService.getSyncrunsById(id));

        verify(syncrunsRepository).findById(id);
        verify(syncrunsMapper, never()).toDTO(any(Syncruns.class));
    }

    @Test
    void shouldCreateSyncrunsWhenCreateSyncrunsIsCalled() {
        SyncrunsDto requestDto = createSampleSyncrunsDto();
        Syncruns mappedEntity = createSampleSyncrunsEntity();
        Syncruns savedEntity = createAnotherSyncrunsEntity();
        SyncrunsDto responseDto = createAnotherSyncrunsDto();

        given(syncrunsMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(syncrunsRepository.save(mappedEntity)).willReturn(savedEntity);
        given(syncrunsMapper.toDTO(savedEntity)).willReturn(responseDto);

        SyncrunsDto result = syncrunsService.createSyncruns(requestDto);

        assertSame(responseDto, result);
        verify(syncrunsMapper).toEntity(requestDto);
        verify(syncrunsRepository).save(mappedEntity);
        verify(syncrunsMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateSyncrunsWhenEntityExists() {
        Long id = 1L;

        SyncrunsDto requestDto = createPatchSyncrunsDto();
        Syncruns existingEntity = createSampleSyncrunsEntity();
        Syncruns savedEntity = createAnotherSyncrunsEntity();
        SyncrunsDto responseDto = createAnotherSyncrunsDto();

        given(syncrunsRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(syncrunsRepository.save(existingEntity)).willReturn(savedEntity);
        given(syncrunsMapper.toDTO(savedEntity)).willReturn(responseDto);

        SyncrunsDto result = syncrunsService.updateSyncruns(id, requestDto);

        assertSame(responseDto, result);
        verify(syncrunsRepository).findById(id);
        verify(syncrunsMapper).partialUpdate(existingEntity, requestDto);
        verify(syncrunsRepository).save(existingEntity);
        verify(syncrunsMapper).toDTO(savedEntity);
        verify(syncrunsMapper, never()).toEntity(any(SyncrunsDto.class));
    }

    @Test
    void shouldThrowWhenUpdateSyncrunsCannotFindEntity() {
        Long id = 1L;

        SyncrunsDto requestDto = createPatchSyncrunsDto();

        given(syncrunsRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncrunsService.updateSyncruns(id, requestDto));

        verify(syncrunsRepository).findById(id);
        verify(syncrunsMapper, never()).partialUpdate(any(), any());
        verify(syncrunsRepository, never()).save(any());
    }

    @Test
    void shouldDeleteSyncrunsWhenEntityExists() {
        Long id = 1L;

        Syncruns existingEntity = createSampleSyncrunsEntity();

        given(syncrunsRepository.findById(id)).willReturn(Optional.of(existingEntity));

        syncrunsService.deleteSyncruns(id);

        verify(syncrunsRepository).findById(id);
        verify(syncrunsRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteSyncrunsCannotFindEntity() {
        Long id = 1L;

        given(syncrunsRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> syncrunsService.deleteSyncruns(id));

        verify(syncrunsRepository).findById(id);
        verify(syncrunsRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Syncruns fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Syncruns createSampleSyncrunsEntity() {
        Syncruns entity = new Syncruns();
        entity.setId(1L);
        entity.setLastRun(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setTradesLastRun(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setIsRunning(true);

        return entity;
    }

    /**
     * Creates a populated Syncruns fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Syncruns createAnotherSyncrunsEntity() {
        Syncruns entity = new Syncruns();
        entity.setId(2L);
        entity.setLastRun(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setTradesLastRun(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setIsRunning(false);

        return entity;
    }

    /**
     * Creates a populated SyncrunsDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createSampleSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setId(1L);
        dto.setLastRun(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setTradesLastRun(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setIsRunning(true);

        return dto;
    }

    /**
     * Creates a populated SyncrunsDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createAnotherSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setId(2L);
        dto.setLastRun(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setTradesLastRun(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setIsRunning(false);

        return dto;
    }

    /**
     * Creates a populated SyncrunsDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createPatchSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setLastRun(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setTradesLastRun(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setIsRunning(true);

        return dto;
    }

}
