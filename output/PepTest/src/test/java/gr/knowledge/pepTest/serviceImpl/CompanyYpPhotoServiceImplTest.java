package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyYpPhoto;
import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.repository.CompanyYpPhotoRepository;
import gr.knowledge.pepTest.mapper.CompanyYpPhotoMapper;
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
class CompanyYpPhotoServiceImplTest {

    @Mock
    private CompanyYpPhotoRepository companyYpPhotoRepository;

    @Mock
    private CompanyYpPhotoMapper companyYpPhotoMapper;

    @InjectMocks
    private CompanyYpPhotoServiceImpl companyYpPhotoService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyYpPhoto.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyYpPhoto", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyYpPhotosIsCalled() {
        List<CompanyYpPhoto> entityList = List.of(createSampleCompanyYpPhotoEntity(), createAnotherCompanyYpPhotoEntity());
        List<CompanyYpPhotoDto> dtoList = List.of(createSampleCompanyYpPhotoDto(), createAnotherCompanyYpPhotoDto());

        given(companyYpPhotoRepository.findAll()).willReturn(entityList);
        given(companyYpPhotoMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyYpPhotoDto> result = companyYpPhotoService.getAllCompanyYpPhotos();

        assertSame(dtoList, result);
        verify(companyYpPhotoRepository).findAll();
        verify(companyYpPhotoMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyYpPhotoByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpPhoto companyYpPhoto = createSampleCompanyYpPhotoEntity();
        CompanyYpPhotoDto companyYpPhotoDto = createSampleCompanyYpPhotoDto();

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.of(companyYpPhoto));
        given(companyYpPhotoMapper.toDTO(companyYpPhoto)).willReturn(companyYpPhotoDto);

        CompanyYpPhotoDto result = companyYpPhotoService.getCompanyYpPhotoById(id);

        assertSame(companyYpPhotoDto, result);
        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoMapper).toDTO(companyYpPhoto);
    }

    @Test
    void shouldThrowWhenGetCompanyYpPhotoByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpPhotoService.getCompanyYpPhotoById(id));

        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoMapper, never()).toDTO(any(CompanyYpPhoto.class));
    }

    @Test
    void shouldCreateCompanyYpPhotoWhenCreateCompanyYpPhotoIsCalled() {
        CompanyYpPhotoDto requestDto = createSampleCompanyYpPhotoDto();
        CompanyYpPhoto mappedEntity = createSampleCompanyYpPhotoEntity();
        CompanyYpPhoto savedEntity = createAnotherCompanyYpPhotoEntity();
        CompanyYpPhotoDto responseDto = createAnotherCompanyYpPhotoDto();

        given(companyYpPhotoMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyYpPhotoRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyYpPhotoMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpPhotoDto result = companyYpPhotoService.createCompanyYpPhoto(requestDto);

        assertSame(responseDto, result);
        verify(companyYpPhotoMapper).toEntity(requestDto);
        verify(companyYpPhotoRepository).save(mappedEntity);
        verify(companyYpPhotoMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyYpPhotoWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpPhotoDto requestDto = createPatchCompanyYpPhotoDto();
        CompanyYpPhoto existingEntity = createSampleCompanyYpPhotoEntity();
        CompanyYpPhoto savedEntity = createAnotherCompanyYpPhotoEntity();
        CompanyYpPhotoDto responseDto = createAnotherCompanyYpPhotoDto();

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyYpPhotoRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyYpPhotoMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpPhotoDto result = companyYpPhotoService.updateCompanyYpPhoto(id, requestDto);

        assertSame(responseDto, result);
        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoMapper).partialUpdate(existingEntity, requestDto);
        verify(companyYpPhotoRepository).save(existingEntity);
        verify(companyYpPhotoMapper).toDTO(savedEntity);
        verify(companyYpPhotoMapper, never()).toEntity(any(CompanyYpPhotoDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyYpPhotoCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpPhotoDto requestDto = createPatchCompanyYpPhotoDto();

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpPhotoService.updateCompanyYpPhoto(id, requestDto));

        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoMapper, never()).partialUpdate(any(), any());
        verify(companyYpPhotoRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyYpPhotoWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpPhoto existingEntity = createSampleCompanyYpPhotoEntity();

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyYpPhotoService.deleteCompanyYpPhoto(id);

        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyYpPhotoCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpPhotoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpPhotoService.deleteCompanyYpPhoto(id));

        verify(companyYpPhotoRepository).findById(id);
        verify(companyYpPhotoRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyYpPhoto fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpPhoto createSampleCompanyYpPhotoEntity() {
        CompanyYpPhoto entity = new CompanyYpPhoto();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setFileName("fileName-value-1");
        entity.setMimeType("mimeType-value-1");
        entity.setFileSize(1);
        entity.setTitle("title-value-1");
        entity.setOrderSeq(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setBlobUri("blobUri-value-1");

        return entity;
    }

    /**
     * Creates a populated CompanyYpPhoto fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpPhoto createAnotherCompanyYpPhotoEntity() {
        CompanyYpPhoto entity = new CompanyYpPhoto();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setFileName("fileName-value-2");
        entity.setMimeType("mimeType-value-2");
        entity.setFileSize(2);
        entity.setTitle("title-value-2");
        entity.setOrderSeq(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setBlobUri("blobUri-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyYpPhotoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpPhotoDto createSampleCompanyYpPhotoDto() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setFileName("fileName-value-1");
        dto.setMimeType("mimeType-value-1");
        dto.setFileSize(1);
        dto.setTitle("title-value-1");
        dto.setOrderSeq(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUri-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyYpPhotoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpPhotoDto createAnotherCompanyYpPhotoDto() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setFileName("fileName-value-2");
        dto.setMimeType("mimeType-value-2");
        dto.setFileSize(2);
        dto.setTitle("title-value-2");
        dto.setOrderSeq(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setBlobUri("blobUri-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyYpPhotoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpPhotoDto createPatchCompanyYpPhotoDto() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();
        dto.setChamberId(3);
        dto.setFileName("fileName-value-3");
        dto.setMimeType("mimeType-value-3");
        dto.setFileSize(3);
        dto.setTitle("title-value-3");
        dto.setOrderSeq(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUri-value-3");

        return dto;
    }

}
