package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyYpFile;
import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.repository.CompanyYpFileRepository;
import gr.knowledge.pepTest.mapper.CompanyYpFileMapper;
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
class CompanyYpFileServiceImplTest {

    @Mock
    private CompanyYpFileRepository companyYpFileRepository;

    @Mock
    private CompanyYpFileMapper companyYpFileMapper;

    @InjectMocks
    private CompanyYpFileServiceImpl companyYpFileService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyYpFile.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyYpFile", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyYpFilesIsCalled() {
        List<CompanyYpFile> entityList = List.of(createSampleCompanyYpFileEntity(), createAnotherCompanyYpFileEntity());
        List<CompanyYpFileDto> dtoList = List.of(createSampleCompanyYpFileDto(), createAnotherCompanyYpFileDto());

        given(companyYpFileRepository.findAll()).willReturn(entityList);
        given(companyYpFileMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyYpFileDto> result = companyYpFileService.getAllCompanyYpFiles();

        assertSame(dtoList, result);
        verify(companyYpFileRepository).findAll();
        verify(companyYpFileMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyYpFileByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpFile companyYpFile = createSampleCompanyYpFileEntity();
        CompanyYpFileDto companyYpFileDto = createSampleCompanyYpFileDto();

        given(companyYpFileRepository.findById(id)).willReturn(Optional.of(companyYpFile));
        given(companyYpFileMapper.toDTO(companyYpFile)).willReturn(companyYpFileDto);

        CompanyYpFileDto result = companyYpFileService.getCompanyYpFileById(id);

        assertSame(companyYpFileDto, result);
        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileMapper).toDTO(companyYpFile);
    }

    @Test
    void shouldThrowWhenGetCompanyYpFileByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpFileService.getCompanyYpFileById(id));

        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileMapper, never()).toDTO(any(CompanyYpFile.class));
    }

    @Test
    void shouldCreateCompanyYpFileWhenCreateCompanyYpFileIsCalled() {
        CompanyYpFileDto requestDto = createSampleCompanyYpFileDto();
        CompanyYpFile mappedEntity = createSampleCompanyYpFileEntity();
        CompanyYpFile savedEntity = createAnotherCompanyYpFileEntity();
        CompanyYpFileDto responseDto = createAnotherCompanyYpFileDto();

        given(companyYpFileMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyYpFileRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyYpFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpFileDto result = companyYpFileService.createCompanyYpFile(requestDto);

        assertSame(responseDto, result);
        verify(companyYpFileMapper).toEntity(requestDto);
        verify(companyYpFileRepository).save(mappedEntity);
        verify(companyYpFileMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyYpFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpFileDto requestDto = createPatchCompanyYpFileDto();
        CompanyYpFile existingEntity = createSampleCompanyYpFileEntity();
        CompanyYpFile savedEntity = createAnotherCompanyYpFileEntity();
        CompanyYpFileDto responseDto = createAnotherCompanyYpFileDto();

        given(companyYpFileRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyYpFileRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyYpFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpFileDto result = companyYpFileService.updateCompanyYpFile(id, requestDto);

        assertSame(responseDto, result);
        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileMapper).partialUpdate(existingEntity, requestDto);
        verify(companyYpFileRepository).save(existingEntity);
        verify(companyYpFileMapper).toDTO(savedEntity);
        verify(companyYpFileMapper, never()).toEntity(any(CompanyYpFileDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyYpFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpFileDto requestDto = createPatchCompanyYpFileDto();

        given(companyYpFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpFileService.updateCompanyYpFile(id, requestDto));

        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileMapper, never()).partialUpdate(any(), any());
        verify(companyYpFileRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyYpFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpFile existingEntity = createSampleCompanyYpFileEntity();

        given(companyYpFileRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyYpFileService.deleteCompanyYpFile(id);

        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyYpFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpFileService.deleteCompanyYpFile(id));

        verify(companyYpFileRepository).findById(id);
        verify(companyYpFileRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyYpFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpFile createSampleCompanyYpFileEntity() {
        CompanyYpFile entity = new CompanyYpFile();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setFileName("fileName-value-1");
        entity.setMimeType("mimeType-value-1");
        entity.setFileSize(1);
        entity.setTitle("title-value-1");
        entity.setOrderSeq(1);
        entity.setRecdeleted(true);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setBlobUri("blobUri-value-1");

        return entity;
    }

    /**
     * Creates a populated CompanyYpFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpFile createAnotherCompanyYpFileEntity() {
        CompanyYpFile entity = new CompanyYpFile();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setFileName("fileName-value-2");
        entity.setMimeType("mimeType-value-2");
        entity.setFileSize(2);
        entity.setTitle("title-value-2");
        entity.setOrderSeq(2);
        entity.setRecdeleted(false);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setBlobUri("blobUri-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyYpFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpFileDto createSampleCompanyYpFileDto() {
        CompanyYpFileDto dto = new CompanyYpFileDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setFileName("fileName-value-1");
        dto.setMimeType("mimeType-value-1");
        dto.setFileSize(1);
        dto.setTitle("title-value-1");
        dto.setOrderSeq(1);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setBlobUri("blobUri-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyYpFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpFileDto createAnotherCompanyYpFileDto() {
        CompanyYpFileDto dto = new CompanyYpFileDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setFileName("fileName-value-2");
        dto.setMimeType("mimeType-value-2");
        dto.setFileSize(2);
        dto.setTitle("title-value-2");
        dto.setOrderSeq(2);
        dto.setRecdeleted(false);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setBlobUri("blobUri-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyYpFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpFileDto createPatchCompanyYpFileDto() {
        CompanyYpFileDto dto = new CompanyYpFileDto();
        dto.setChamberId(3);
        dto.setFileName("fileName-value-3");
        dto.setMimeType("mimeType-value-3");
        dto.setFileSize(3);
        dto.setTitle("title-value-3");
        dto.setOrderSeq(3);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setBlobUri("blobUri-value-3");

        return dto;
    }

}
