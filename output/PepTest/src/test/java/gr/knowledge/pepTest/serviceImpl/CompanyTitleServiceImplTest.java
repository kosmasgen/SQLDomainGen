package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyTitle;
import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.repository.CompanyTitleRepository;
import gr.knowledge.pepTest.mapper.CompanyTitleMapper;
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
class CompanyTitleServiceImplTest {

    @Mock
    private CompanyTitleRepository companyTitleRepository;

    @Mock
    private CompanyTitleMapper companyTitleMapper;

    @InjectMocks
    private CompanyTitleServiceImpl companyTitleService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyTitle.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyTitle", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyTitlesIsCalled() {
        List<CompanyTitle> entityList = List.of(createSampleCompanyTitleEntity(), createAnotherCompanyTitleEntity());
        List<CompanyTitleDto> dtoList = List.of(createSampleCompanyTitleDto(), createAnotherCompanyTitleDto());

        given(companyTitleRepository.findAll()).willReturn(entityList);
        given(companyTitleMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyTitleDto> result = companyTitleService.getAllCompanyTitles();

        assertSame(dtoList, result);
        verify(companyTitleRepository).findAll();
        verify(companyTitleMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyTitleByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyTitle companyTitle = createSampleCompanyTitleEntity();
        CompanyTitleDto companyTitleDto = createSampleCompanyTitleDto();

        given(companyTitleRepository.findById(id)).willReturn(Optional.of(companyTitle));
        given(companyTitleMapper.toDTO(companyTitle)).willReturn(companyTitleDto);

        CompanyTitleDto result = companyTitleService.getCompanyTitleById(id);

        assertSame(companyTitleDto, result);
        verify(companyTitleRepository).findById(id);
        verify(companyTitleMapper).toDTO(companyTitle);
    }

    @Test
    void shouldThrowWhenGetCompanyTitleByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitleService.getCompanyTitleById(id));

        verify(companyTitleRepository).findById(id);
        verify(companyTitleMapper, never()).toDTO(any(CompanyTitle.class));
    }

    @Test
    void shouldCreateCompanyTitleWhenCreateCompanyTitleIsCalled() {
        CompanyTitleDto requestDto = createSampleCompanyTitleDto();
        CompanyTitle mappedEntity = createSampleCompanyTitleEntity();
        CompanyTitle savedEntity = createAnotherCompanyTitleEntity();
        CompanyTitleDto responseDto = createAnotherCompanyTitleDto();

        given(companyTitleMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyTitleRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyTitleMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyTitleDto result = companyTitleService.createCompanyTitle(requestDto);

        assertSame(responseDto, result);
        verify(companyTitleMapper).toEntity(requestDto);
        verify(companyTitleRepository).save(mappedEntity);
        verify(companyTitleMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyTitleWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyTitleDto requestDto = createPatchCompanyTitleDto();
        CompanyTitle existingEntity = createSampleCompanyTitleEntity();
        CompanyTitle savedEntity = createAnotherCompanyTitleEntity();
        CompanyTitleDto responseDto = createAnotherCompanyTitleDto();

        given(companyTitleRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyTitleRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyTitleMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyTitleDto result = companyTitleService.updateCompanyTitle(id, requestDto);

        assertSame(responseDto, result);
        verify(companyTitleRepository).findById(id);
        verify(companyTitleMapper).partialUpdate(existingEntity, requestDto);
        verify(companyTitleRepository).save(existingEntity);
        verify(companyTitleMapper).toDTO(savedEntity);
        verify(companyTitleMapper, never()).toEntity(any(CompanyTitleDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyTitleCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyTitleDto requestDto = createPatchCompanyTitleDto();

        given(companyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitleService.updateCompanyTitle(id, requestDto));

        verify(companyTitleRepository).findById(id);
        verify(companyTitleMapper, never()).partialUpdate(any(), any());
        verify(companyTitleRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyTitleWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyTitle existingEntity = createSampleCompanyTitleEntity();

        given(companyTitleRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyTitleService.deleteCompanyTitle(id);

        verify(companyTitleRepository).findById(id);
        verify(companyTitleRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyTitleCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitleService.deleteCompanyTitle(id));

        verify(companyTitleRepository).findById(id);
        verify(companyTitleRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyTitle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitle createSampleCompanyTitleEntity() {
        CompanyTitle entity = new CompanyTitle();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setTitle("title-value-1");
        entity.setChamberTitleId(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyTitle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitle createAnotherCompanyTitleEntity() {
        CompanyTitle entity = new CompanyTitle();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setTitle("title-value-2");
        entity.setChamberTitleId(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitleDto createSampleCompanyTitleDto() {
        CompanyTitleDto dto = new CompanyTitleDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setTitle("title-value-1");
        dto.setChamberTitleId(new BigInteger("1"));
        dto.setCompany(new CompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitleDto createAnotherCompanyTitleDto() {
        CompanyTitleDto dto = new CompanyTitleDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setTitle("title-value-2");
        dto.setChamberTitleId(new BigInteger("2"));
        dto.setCompany(new CompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitleDto createPatchCompanyTitleDto() {
        CompanyTitleDto dto = new CompanyTitleDto();
        dto.setChamberId(3);
        dto.setTitle("title-value-3");
        dto.setChamberTitleId(new BigInteger("3"));
        dto.setCompany(new CompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
