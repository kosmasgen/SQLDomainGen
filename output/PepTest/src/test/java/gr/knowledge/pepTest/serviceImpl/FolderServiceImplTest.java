package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Folder;
import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.repository.FolderRepository;
import gr.knowledge.pepTest.mapper.FolderMapper;
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
class FolderServiceImplTest {

    @Mock
    private FolderRepository folderRepository;

    @Mock
    private FolderMapper folderMapper;

    @InjectMocks
    private FolderServiceImpl folderService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Folder.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Folder", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllFoldersIsCalled() {
        List<Folder> entityList = List.of(createSampleFolderEntity(), createAnotherFolderEntity());
        List<FolderDto> dtoList = List.of(createSampleFolderDto(), createAnotherFolderDto());

        given(folderRepository.findAll()).willReturn(entityList);
        given(folderMapper.toDTOList(entityList)).willReturn(dtoList);

        List<FolderDto> result = folderService.getAllFolders();

        assertSame(dtoList, result);
        verify(folderRepository).findAll();
        verify(folderMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetFolderByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Folder folder = createSampleFolderEntity();
        FolderDto folderDto = createSampleFolderDto();

        given(folderRepository.findById(id)).willReturn(Optional.of(folder));
        given(folderMapper.toDTO(folder)).willReturn(folderDto);

        FolderDto result = folderService.getFolderById(id);

        assertSame(folderDto, result);
        verify(folderRepository).findById(id);
        verify(folderMapper).toDTO(folder);
    }

    @Test
    void shouldThrowWhenGetFolderByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(folderRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> folderService.getFolderById(id));

        verify(folderRepository).findById(id);
        verify(folderMapper, never()).toDTO(any(Folder.class));
    }

    @Test
    void shouldCreateFolderWhenCreateFolderIsCalled() {
        FolderDto requestDto = createSampleFolderDto();
        Folder mappedEntity = createSampleFolderEntity();
        Folder savedEntity = createAnotherFolderEntity();
        FolderDto responseDto = createAnotherFolderDto();

        given(folderMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(folderRepository.save(mappedEntity)).willReturn(savedEntity);
        given(folderMapper.toDTO(savedEntity)).willReturn(responseDto);

        FolderDto result = folderService.createFolder(requestDto);

        assertSame(responseDto, result);
        verify(folderMapper).toEntity(requestDto);
        verify(folderRepository).save(mappedEntity);
        verify(folderMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateFolderWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        FolderDto requestDto = createPatchFolderDto();
        Folder existingEntity = createSampleFolderEntity();
        Folder savedEntity = createAnotherFolderEntity();
        FolderDto responseDto = createAnotherFolderDto();

        given(folderRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(folderRepository.save(existingEntity)).willReturn(savedEntity);
        given(folderMapper.toDTO(savedEntity)).willReturn(responseDto);

        FolderDto result = folderService.updateFolder(id, requestDto);

        assertSame(responseDto, result);
        verify(folderRepository).findById(id);
        verify(folderMapper).partialUpdate(existingEntity, requestDto);
        verify(folderRepository).save(existingEntity);
        verify(folderMapper).toDTO(savedEntity);
        verify(folderMapper, never()).toEntity(any(FolderDto.class));
    }

    @Test
    void shouldThrowWhenUpdateFolderCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        FolderDto requestDto = createPatchFolderDto();

        given(folderRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> folderService.updateFolder(id, requestDto));

        verify(folderRepository).findById(id);
        verify(folderMapper, never()).partialUpdate(any(), any());
        verify(folderRepository, never()).save(any());
    }

    @Test
    void shouldDeleteFolderWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Folder existingEntity = createSampleFolderEntity();

        given(folderRepository.findById(id)).willReturn(Optional.of(existingEntity));

        folderService.deleteFolder(id);

        verify(folderRepository).findById(id);
        verify(folderRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteFolderCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(folderRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> folderService.deleteFolder(id));

        verify(folderRepository).findById(id);
        verify(folderRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Folder fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Folder createSampleFolderEntity() {
        Folder entity = new Folder();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDescr("descr-value-1");
        entity.setUri("uri-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Folder fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Folder createAnotherFolderEntity() {
        Folder entity = new Folder();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDescr("descr-value-2");
        entity.setUri("uri-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated FolderDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createSampleFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDescr("descr-value-1");
        dto.setUri("uri-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated FolderDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createAnotherFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDescr("descr-value-2");
        dto.setUri("uri-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated FolderDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createPatchFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setDescr("descr-value-3");
        dto.setUri("uri-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
