package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.repository.CompanyYpArticleRepository;
import gr.knowledge.pepTest.mapper.CompanyYpArticleMapper;
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
class CompanyYpArticleServiceImplTest {

    @Mock
    private CompanyYpArticleRepository companyYpArticleRepository;

    @Mock
    private CompanyYpArticleMapper companyYpArticleMapper;

    @InjectMocks
    private CompanyYpArticleServiceImpl companyYpArticleService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyYpArticle.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyYpArticle", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyYpArticlesIsCalled() {
        List<CompanyYpArticle> entityList = List.of(createSampleCompanyYpArticleEntity(), createAnotherCompanyYpArticleEntity());
        List<CompanyYpArticleDto> dtoList = List.of(createSampleCompanyYpArticleDto(), createAnotherCompanyYpArticleDto());

        given(companyYpArticleRepository.findAll()).willReturn(entityList);
        given(companyYpArticleMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyYpArticleDto> result = companyYpArticleService.getAllCompanyYpArticles();

        assertSame(dtoList, result);
        verify(companyYpArticleRepository).findAll();
        verify(companyYpArticleMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyYpArticleByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticle companyYpArticle = createSampleCompanyYpArticleEntity();
        CompanyYpArticleDto companyYpArticleDto = createSampleCompanyYpArticleDto();

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.of(companyYpArticle));
        given(companyYpArticleMapper.toDTO(companyYpArticle)).willReturn(companyYpArticleDto);

        CompanyYpArticleDto result = companyYpArticleService.getCompanyYpArticleById(id);

        assertSame(companyYpArticleDto, result);
        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleMapper).toDTO(companyYpArticle);
    }

    @Test
    void shouldThrowWhenGetCompanyYpArticleByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleService.getCompanyYpArticleById(id));

        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleMapper, never()).toDTO(any(CompanyYpArticle.class));
    }

    @Test
    void shouldCreateCompanyYpArticleWhenCreateCompanyYpArticleIsCalled() {
        CompanyYpArticleDto requestDto = createSampleCompanyYpArticleDto();
        CompanyYpArticle mappedEntity = createSampleCompanyYpArticleEntity();
        CompanyYpArticle savedEntity = createAnotherCompanyYpArticleEntity();
        CompanyYpArticleDto responseDto = createAnotherCompanyYpArticleDto();

        given(companyYpArticleMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyYpArticleRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyYpArticleMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpArticleDto result = companyYpArticleService.createCompanyYpArticle(requestDto);

        assertSame(responseDto, result);
        verify(companyYpArticleMapper).toEntity(requestDto);
        verify(companyYpArticleRepository).save(mappedEntity);
        verify(companyYpArticleMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyYpArticleWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleDto requestDto = createPatchCompanyYpArticleDto();
        CompanyYpArticle existingEntity = createSampleCompanyYpArticleEntity();
        CompanyYpArticle savedEntity = createAnotherCompanyYpArticleEntity();
        CompanyYpArticleDto responseDto = createAnotherCompanyYpArticleDto();

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyYpArticleRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyYpArticleMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpArticleDto result = companyYpArticleService.updateCompanyYpArticle(id, requestDto);

        assertSame(responseDto, result);
        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleMapper).partialUpdate(existingEntity, requestDto);
        verify(companyYpArticleRepository).save(existingEntity);
        verify(companyYpArticleMapper).toDTO(savedEntity);
        verify(companyYpArticleMapper, never()).toEntity(any(CompanyYpArticleDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyYpArticleCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleDto requestDto = createPatchCompanyYpArticleDto();

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleService.updateCompanyYpArticle(id, requestDto));

        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleMapper, never()).partialUpdate(any(), any());
        verify(companyYpArticleRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyYpArticleWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticle existingEntity = createSampleCompanyYpArticleEntity();

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyYpArticleService.deleteCompanyYpArticle(id);

        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyYpArticleCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyYpArticleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleService.deleteCompanyYpArticle(id));

        verify(companyYpArticleRepository).findById(id);
        verify(companyYpArticleRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyYpArticle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticle createSampleCompanyYpArticleEntity() {
        CompanyYpArticle entity = new CompanyYpArticle();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setTitle("title-value-1");
        entity.setHtml("html-value-1");
        entity.setOrderSeq(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setIsPublished(true);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticle createAnotherCompanyYpArticleEntity() {
        CompanyYpArticle entity = new CompanyYpArticle();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setTitle("title-value-2");
        entity.setHtml("html-value-2");
        entity.setOrderSeq(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setIsPublished(false);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createSampleCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setTitle("title-value-1");
        dto.setHtml("html-value-1");
        dto.setOrderSeq(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setIsPublished(true);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createAnotherCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setTitle("title-value-2");
        dto.setHtml("html-value-2");
        dto.setOrderSeq(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setIsPublished(false);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createPatchCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setChamberId(3);
        dto.setTitle("title-value-3");
        dto.setHtml("html-value-3");
        dto.setOrderSeq(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setIsPublished(true);

        return dto;
    }

}
