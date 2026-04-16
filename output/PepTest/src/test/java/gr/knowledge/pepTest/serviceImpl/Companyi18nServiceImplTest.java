package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Companyi18n;
import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.repository.Companyi18nRepository;
import gr.knowledge.pepTest.mapper.Companyi18nMapper;
import gr.knowledge.pepTest.entity.Companyi18nKey;
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
class Companyi18nServiceImplTest {

    @Mock
    private Companyi18nRepository companyi18nRepository;

    @Mock
    private Companyi18nMapper companyi18nMapper;

    @InjectMocks
    private Companyi18nServiceImpl companyi18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Companyi18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Companyi18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyi18nsIsCalled() {
        List<Companyi18n> entityList = List.of(createSampleCompanyi18nEntity(), createAnotherCompanyi18nEntity());
        List<Companyi18nDto> dtoList = List.of(createSampleCompanyi18nDto(), createAnotherCompanyi18nDto());

        given(companyi18nRepository.findAll()).willReturn(entityList);
        given(companyi18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<Companyi18nDto> result = companyi18nService.getAllCompanyi18ns();

        assertSame(dtoList, result);
        verify(companyi18nRepository).findAll();
        verify(companyi18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyi18nByIdIsCalled() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        Companyi18n companyi18n = createSampleCompanyi18nEntity();
        Companyi18nDto companyi18nDto = createSampleCompanyi18nDto();

        given(companyi18nRepository.findById(id)).willReturn(Optional.of(companyi18n));
        given(companyi18nMapper.toDTO(companyi18n)).willReturn(companyi18nDto);

        Companyi18nDto result = companyi18nService.getCompanyi18nById(companyId, languageId, chamberI18nId);

        assertSame(companyi18nDto, result);
        verify(companyi18nRepository).findById(id);
        verify(companyi18nMapper).toDTO(companyi18n);
    }

    @Test
    void shouldThrowWhenGetCompanyi18nByIdCannotFindEntity() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        given(companyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyi18nService.getCompanyi18nById(companyId, languageId, chamberI18nId));

        verify(companyi18nRepository).findById(id);
        verify(companyi18nMapper, never()).toDTO(any(Companyi18n.class));
    }

    @Test
    void shouldCreateCompanyi18nWhenCreateCompanyi18nIsCalled() {
        Companyi18nDto requestDto = createSampleCompanyi18nDto();
        Companyi18n mappedEntity = createSampleCompanyi18nEntity();
        Companyi18n savedEntity = createAnotherCompanyi18nEntity();
        Companyi18nDto responseDto = createAnotherCompanyi18nDto();

        given(companyi18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyi18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Companyi18nDto result = companyi18nService.createCompanyi18n(requestDto);

        assertSame(responseDto, result);
        verify(companyi18nMapper).toEntity(requestDto);
        verify(companyi18nRepository).save(mappedEntity);
        verify(companyi18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyi18nWhenEntityExists() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        Companyi18nDto requestDto = createPatchCompanyi18nDto();
        Companyi18n existingEntity = createSampleCompanyi18nEntity();
        Companyi18n savedEntity = createAnotherCompanyi18nEntity();
        Companyi18nDto responseDto = createAnotherCompanyi18nDto();

        given(companyi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyi18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Companyi18nDto result = companyi18nService.updateCompanyi18n(companyId, languageId, chamberI18nId, requestDto);

        assertSame(responseDto, result);
        verify(companyi18nRepository).findById(id);
        verify(companyi18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyi18nRepository).save(existingEntity);
        verify(companyi18nMapper).toDTO(savedEntity);
        verify(companyi18nMapper, never()).toEntity(any(Companyi18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyi18nCannotFindEntity() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        Companyi18nDto requestDto = createPatchCompanyi18nDto();

        given(companyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyi18nService.updateCompanyi18n(companyId, languageId, chamberI18nId, requestDto));

        verify(companyi18nRepository).findById(id);
        verify(companyi18nMapper, never()).partialUpdate(any(), any());
        verify(companyi18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyi18nWhenEntityExists() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        Companyi18n existingEntity = createSampleCompanyi18nEntity();

        given(companyi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyi18nService.deleteCompanyi18n(companyId, languageId, chamberI18nId);

        verify(companyi18nRepository).findById(id);
        verify(companyi18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyi18nCannotFindEntity() {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        Companyi18nKey id = new Companyi18nKey();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        given(companyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyi18nService.deleteCompanyi18n(companyId, languageId, chamberI18nId));

        verify(companyi18nRepository).findById(id);
        verify(companyi18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Companyi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Companyi18n createSampleCompanyi18nEntity() {
        Companyi18n entity = new Companyi18n();
        entity.setCity("city-value-1");
        entity.setCoName("coName-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setObjective("objective-value-1");
        entity.setRecdeleted(true);
        entity.setStreet("street-value-1");
        entity.setResponsibleName("responsibleName-value-1");

        return entity;
    }

    /**
     * Creates a populated Companyi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Companyi18n createAnotherCompanyi18nEntity() {
        Companyi18n entity = new Companyi18n();
        entity.setCity("city-value-2");
        entity.setCoName("coName-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setObjective("objective-value-2");
        entity.setRecdeleted(false);
        entity.setStreet("street-value-2");
        entity.setResponsibleName("responsibleName-value-2");

        return entity;
    }

    /**
     * Creates a populated Companyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Companyi18nDto createSampleCompanyi18nDto() {
        Companyi18nDto dto = new Companyi18nDto();
        dto.setCity("city-value-1");
        dto.setCoName("coName-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setObjective("objective-value-1");
        dto.setRecdeleted(true);
        dto.setStreet("street-value-1");
        dto.setResponsibleName("responsibleName-value-1");

        return dto;
    }

    /**
     * Creates a populated Companyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Companyi18nDto createAnotherCompanyi18nDto() {
        Companyi18nDto dto = new Companyi18nDto();
        dto.setCity("city-value-2");
        dto.setCoName("coName-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setObjective("objective-value-2");
        dto.setRecdeleted(false);
        dto.setStreet("street-value-2");
        dto.setResponsibleName("responsibleName-value-2");

        return dto;
    }

    /**
     * Creates a populated Companyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Companyi18nDto createPatchCompanyi18nDto() {
        Companyi18nDto dto = new Companyi18nDto();
        dto.setCity("city-value-3");
        dto.setCoName("coName-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setObjective("objective-value-3");
        dto.setRecdeleted(true);
        dto.setStreet("street-value-3");
        dto.setResponsibleName("responsibleName-value-3");

        return dto;
    }

}
