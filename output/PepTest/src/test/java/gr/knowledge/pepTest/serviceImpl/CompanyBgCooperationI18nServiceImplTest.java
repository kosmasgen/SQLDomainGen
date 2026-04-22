package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.CompanyBgCooperationI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationI18nMapper;
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
class CompanyBgCooperationI18nServiceImplTest {

    @Mock
    private CompanyBgCooperationI18nRepository companyBgCooperationI18nRepository;

    @Mock
    private CompanyBgCooperationI18nMapper companyBgCooperationI18nMapper;

    @InjectMocks
    private CompanyBgCooperationI18nServiceImpl companyBgCooperationI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyBgCooperationI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyBgCooperationI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyBgCooperationI18nsIsCalled() {
        List<CompanyBgCooperationI18n> entityList = List.of(createSampleCompanyBgCooperationI18nEntity(), createAnotherCompanyBgCooperationI18nEntity());
        List<CompanyBgCooperationI18nDto> dtoList = List.of(createSampleCompanyBgCooperationI18nDto(), createAnotherCompanyBgCooperationI18nDto());

        given(companyBgCooperationI18nRepository.findAll()).willReturn(entityList);
        given(companyBgCooperationI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyBgCooperationI18nDto> result = companyBgCooperationI18nService.getAllCompanyBgCooperationI18ns();

        assertSame(dtoList, result);
        verify(companyBgCooperationI18nRepository).findAll();
        verify(companyBgCooperationI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyBgCooperationI18nByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationI18n companyBgCooperationI18n = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18nDto companyBgCooperationI18nDto = createSampleCompanyBgCooperationI18nDto();

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.of(companyBgCooperationI18n));
        given(companyBgCooperationI18nMapper.toDTO(companyBgCooperationI18n)).willReturn(companyBgCooperationI18nDto);

        CompanyBgCooperationI18nDto result = companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id);

        assertSame(companyBgCooperationI18nDto, result);
        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nMapper).toDTO(companyBgCooperationI18n);
    }

    @Test
    void shouldThrowWhenGetCompanyBgCooperationI18nByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id));

        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nMapper, never()).toDTO(any(CompanyBgCooperationI18n.class));
    }

    @Test
    void shouldCreateCompanyBgCooperationI18nWhenCreateCompanyBgCooperationI18nIsCalled() {
        CompanyBgCooperationI18nDto requestDto = createSampleCompanyBgCooperationI18nDto();
        CompanyBgCooperationI18n mappedEntity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18n savedEntity = createAnotherCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18nDto responseDto = createAnotherCompanyBgCooperationI18nDto();

        given(companyBgCooperationI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyBgCooperationI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyBgCooperationI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyBgCooperationI18nDto result = companyBgCooperationI18nService.createCompanyBgCooperationI18n(requestDto);

        assertSame(responseDto, result);
        verify(companyBgCooperationI18nMapper).toEntity(requestDto);
        verify(companyBgCooperationI18nRepository).save(mappedEntity);
        verify(companyBgCooperationI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyBgCooperationI18nWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationI18nDto requestDto = createPatchCompanyBgCooperationI18nDto();
        CompanyBgCooperationI18n existingEntity = createSampleCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18n savedEntity = createAnotherCompanyBgCooperationI18nEntity();
        CompanyBgCooperationI18nDto responseDto = createAnotherCompanyBgCooperationI18nDto();

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyBgCooperationI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyBgCooperationI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyBgCooperationI18nDto result = companyBgCooperationI18nService.updateCompanyBgCooperationI18n(id, requestDto);

        assertSame(responseDto, result);
        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyBgCooperationI18nRepository).save(existingEntity);
        verify(companyBgCooperationI18nMapper).toDTO(savedEntity);
        verify(companyBgCooperationI18nMapper, never()).toEntity(any(CompanyBgCooperationI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyBgCooperationI18nCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationI18nDto requestDto = createPatchCompanyBgCooperationI18nDto();

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationI18nService.updateCompanyBgCooperationI18n(id, requestDto));

        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nMapper, never()).partialUpdate(any(), any());
        verify(companyBgCooperationI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyBgCooperationI18nWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationI18n existingEntity = createSampleCompanyBgCooperationI18nEntity();

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyBgCooperationI18nService.deleteCompanyBgCooperationI18n(id);

        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyBgCooperationI18nCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyBgCooperationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationI18nService.deleteCompanyBgCooperationI18n(id));

        verify(companyBgCooperationI18nRepository).findById(id);
        verify(companyBgCooperationI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyBgCooperationI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperationI18n createSampleCompanyBgCooperationI18nEntity() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperationI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperationI18n createAnotherCompanyBgCooperationI18nEntity() {
        CompanyBgCooperationI18n entity = new CompanyBgCooperationI18n();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createSampleCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCooperation(new CompanyBgCooperationDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createAnotherCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setCooperation(new CompanyBgCooperationDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationI18nDto createPatchCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCooperation(new CompanyBgCooperationDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

}
