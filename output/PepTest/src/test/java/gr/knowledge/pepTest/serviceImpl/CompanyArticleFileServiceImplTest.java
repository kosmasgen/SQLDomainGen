package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyArticleFile;
import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.repository.CompanyArticleFileRepository;
import gr.knowledge.pepTest.mapper.CompanyArticleFileMapper;
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
class CompanyArticleFileServiceImplTest {

    @Mock
    private CompanyArticleFileRepository companyArticleFileRepository;

    @Mock
    private CompanyArticleFileMapper companyArticleFileMapper;

    @InjectMocks
    private CompanyArticleFileServiceImpl companyArticleFileService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyArticleFile.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyArticleFile", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyArticleFilesIsCalled() {
        List<CompanyArticleFile> entityList = List.of(createSampleCompanyArticleFileEntity(), createAnotherCompanyArticleFileEntity());
        List<CompanyArticleFileDto> dtoList = List.of(createSampleCompanyArticleFileDto(), createAnotherCompanyArticleFileDto());

        given(companyArticleFileRepository.findAll()).willReturn(entityList);
        given(companyArticleFileMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyArticleFileDto> result = companyArticleFileService.getAllCompanyArticleFiles();

        assertSame(dtoList, result);
        verify(companyArticleFileRepository).findAll();
        verify(companyArticleFileMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyArticleFileByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyArticleFile companyArticleFile = createSampleCompanyArticleFileEntity();
        CompanyArticleFileDto companyArticleFileDto = createSampleCompanyArticleFileDto();

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.of(companyArticleFile));
        given(companyArticleFileMapper.toDTO(companyArticleFile)).willReturn(companyArticleFileDto);

        CompanyArticleFileDto result = companyArticleFileService.getCompanyArticleFileById(id);

        assertSame(companyArticleFileDto, result);
        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileMapper).toDTO(companyArticleFile);
    }

    @Test
    void shouldThrowWhenGetCompanyArticleFileByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyArticleFileService.getCompanyArticleFileById(id));

        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileMapper, never()).toDTO(any(CompanyArticleFile.class));
    }

    @Test
    void shouldCreateCompanyArticleFileWhenCreateCompanyArticleFileIsCalled() {
        CompanyArticleFileDto requestDto = createSampleCompanyArticleFileDto();
        CompanyArticleFile mappedEntity = createSampleCompanyArticleFileEntity();
        CompanyArticleFile savedEntity = createAnotherCompanyArticleFileEntity();
        CompanyArticleFileDto responseDto = createAnotherCompanyArticleFileDto();

        given(companyArticleFileMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyArticleFileRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyArticleFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyArticleFileDto result = companyArticleFileService.createCompanyArticleFile(requestDto);

        assertSame(responseDto, result);
        verify(companyArticleFileMapper).toEntity(requestDto);
        verify(companyArticleFileRepository).save(mappedEntity);
        verify(companyArticleFileMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyArticleFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyArticleFileDto requestDto = createPatchCompanyArticleFileDto();
        CompanyArticleFile existingEntity = createSampleCompanyArticleFileEntity();
        CompanyArticleFile savedEntity = createAnotherCompanyArticleFileEntity();
        CompanyArticleFileDto responseDto = createAnotherCompanyArticleFileDto();

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyArticleFileRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyArticleFileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyArticleFileDto result = companyArticleFileService.updateCompanyArticleFile(id, requestDto);

        assertSame(responseDto, result);
        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileMapper).partialUpdate(existingEntity, requestDto);
        verify(companyArticleFileRepository).save(existingEntity);
        verify(companyArticleFileMapper).toDTO(savedEntity);
        verify(companyArticleFileMapper, never()).toEntity(any(CompanyArticleFileDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyArticleFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyArticleFileDto requestDto = createPatchCompanyArticleFileDto();

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyArticleFileService.updateCompanyArticleFile(id, requestDto));

        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileMapper, never()).partialUpdate(any(), any());
        verify(companyArticleFileRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyArticleFileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyArticleFile existingEntity = createSampleCompanyArticleFileEntity();

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyArticleFileService.deleteCompanyArticleFile(id);

        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyArticleFileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyArticleFileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyArticleFileService.deleteCompanyArticleFile(id));

        verify(companyArticleFileRepository).findById(id);
        verify(companyArticleFileRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyArticleFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyArticleFile createSampleCompanyArticleFileEntity() {
        CompanyArticleFile entity = new CompanyArticleFile();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setOrderSeq(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyArticleFile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyArticleFile createAnotherCompanyArticleFileEntity() {
        CompanyArticleFile entity = new CompanyArticleFile();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setOrderSeq(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createSampleCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setOrderSeq(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createAnotherCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setOrderSeq(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createPatchCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setOrderSeq(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
