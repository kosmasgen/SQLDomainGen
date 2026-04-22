package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyFile;
import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.repository.CompanyFileRepository;
import gr.knowledge.pepTest.mapper.CompanyFileMapper;
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
class CompanyFileServiceImplTest {

    @Mock
    private CompanyFileRepository companyFileRepository;

    @Mock
    private CompanyFileMapper companyFileMapper;

    @InjectMocks
    private CompanyFileServiceImpl companyFileService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyFile.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyFile", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyFilesIsCalled() {
        List<CompanyFile> entityList = List.of(createSampleCompanyFileEntity(), createAnotherCompanyFileEntity());
        List<CompanyFileDto> dtoList = List.of(createSampleCompanyFileDto(), createAnotherCompanyFileDto());

        given(companyFileRepository.findAll()).willReturn(entityList);
        given(companyFileMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyFileDto> result = companyFileService.getAllCompanyFiles();

        assertSame(dtoList, result);
        verify(companyFileRepository).findAll();
        verify(companyFileMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyFileByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFile companyFile = createSampleCompanyFileEntity();
        CompanyFileDto companyFileDto = createSampleCompanyFileDto();

        given(companyFileRepository.findById(id)).willReturn(Optional.of(companyFile));
        given(companyFileMapper.toDTO(companyFile)).willReturn(companyFileDto);

        CompanyFileDto result = companyFileService.getCompanyFileById(id);

        assertSame(companyFileDto, result);
        verify(companyFileRepository).findById(id);
        verify(companyFileMapper).toDTO(companyFile);
    }

    @Test
    void shouldThrowWhenGetCompanyFileByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFileService.getCompanyFileById(id));

        verify(companyFileRepository).findById(id);
        verify(companyFileMapper, never()).toDTO(any(CompanyFile.class));
    }

    @Test
    void shouldCreateCompanyFileWhenCreateCompanyFileIsCalled() {
        CompanyFileDto requestDto = createSampleCompanyFileDto();
        CompanyFile mappedEntity = createSampleCompanyFileEntity();
        CompanyFile savedEntity = createAnotherCompanyFileEntity();
        CompanyFileDto responseDto = createAnotherCompanyFileDto();

        given(companyFileMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyFileRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyFileDto result = companyFileService.createCompanyFile(requestDto);

        assertSame(responseDto, result);
        verify(companyFileMapper).toEntity(requestDto);
        verify(companyFileRepository).save(mappedEntity);
        verify(companyFileMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFileDto requestDto = createPatchCompanyFileDto();
        CompanyFile existingEntity = createSampleCompanyFileEntity();
        CompanyFile savedEntity = createAnotherCompanyFileEntity();
        CompanyFileDto responseDto = createAnotherCompanyFileDto();

        given(companyFileRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyFileRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyFileDto result = companyFileService.updateCompanyFile(id, requestDto);

        assertSame(responseDto, result);
        verify(companyFileRepository).findById(id);
        verify(companyFileMapper).partialUpdate(existingEntity, requestDto);
        verify(companyFileRepository).save(existingEntity);
        verify(companyFileMapper).toDTO(savedEntity);
        verify(companyFileMapper, never()).toEntity(any(CompanyFileDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFileDto requestDto = createPatchCompanyFileDto();

        given(companyFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFileService.updateCompanyFile(id, requestDto));

        verify(companyFileRepository).findById(id);
        verify(companyFileMapper, never()).partialUpdate(any(), any());
        verify(companyFileRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFile existingEntity = createSampleCompanyFileEntity();

        given(companyFileRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyFileService.deleteCompanyFile(id);

        verify(companyFileRepository).findById(id);
        verify(companyFileRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFileService.deleteCompanyFile(id));

        verify(companyFileRepository).findById(id);
        verify(companyFileRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyFile createSampleCompanyFileEntity() {
        CompanyFile entity = new CompanyFile();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setFileName("fileName-value-1");
        entity.setFileSize(1);
        entity.setBlobUri("blobUri-value-1");
        entity.setOrderSeq(1);
        entity.setRecdeleted(true);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setIsLogo(true);
        entity.setIsBackground(true);
        entity.setIsEmbedded(true);

        return entity;
    }

    /**
     * Creates a populated CompanyFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyFile createAnotherCompanyFileEntity() {
        CompanyFile entity = new CompanyFile();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setFileName("fileName-value-2");
        entity.setFileSize(2);
        entity.setBlobUri("blobUri-value-2");
        entity.setOrderSeq(2);
        entity.setRecdeleted(false);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setIsLogo(false);
        entity.setIsBackground(false);
        entity.setIsEmbedded(false);

        return entity;
    }

    /**
     * Creates a populated CompanyFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFileDto createSampleCompanyFileDto() {
        CompanyFileDto dto = new CompanyFileDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setFileName("fileName-value-1");
        dto.setFileSize(1);
        dto.setBlobUri("blobUri-value-1");
        dto.setOrderSeq(1);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setIsLogo(true);
        dto.setIsBackground(true);
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setIsEmbedded(true);

        return dto;
    }

    /**
     * Creates a populated CompanyFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFileDto createAnotherCompanyFileDto() {
        CompanyFileDto dto = new CompanyFileDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setFileName("fileName-value-2");
        dto.setFileSize(2);
        dto.setBlobUri("blobUri-value-2");
        dto.setOrderSeq(2);
        dto.setRecdeleted(false);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setIsLogo(false);
        dto.setIsBackground(false);
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setIsEmbedded(false);

        return dto;
    }

    /**
     * Creates a populated CompanyFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFileDto createPatchCompanyFileDto() {
        CompanyFileDto dto = new CompanyFileDto();
        dto.setChamberId(3);
        dto.setFileName("fileName-value-3");
        dto.setFileSize(3);
        dto.setBlobUri("blobUri-value-3");
        dto.setOrderSeq(3);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setIsLogo(true);
        dto.setIsBackground(true);
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setIsEmbedded(true);

        return dto;
    }

}
