package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.repository.BusinessLocationI18nRepository;
import gr.knowledge.pepTest.mapper.BusinessLocationI18nMapper;
import gr.knowledge.pepTest.entity.BusinessLocationI18nKey;
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
class BusinessLocationI18nServiceImplTest {

    @Mock
    private BusinessLocationI18nRepository businessLocationI18nRepository;

    @Mock
    private BusinessLocationI18nMapper businessLocationI18nMapper;

    @InjectMocks
    private BusinessLocationI18nServiceImpl businessLocationI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for BusinessLocationI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("BusinessLocationI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllBusinessLocationI18nsIsCalled() {
        List<BusinessLocationI18n> entityList = List.of(createSampleBusinessLocationI18nEntity(), createAnotherBusinessLocationI18nEntity());
        List<BusinessLocationI18nDto> dtoList = List.of(createSampleBusinessLocationI18nDto(), createAnotherBusinessLocationI18nDto());

        given(businessLocationI18nRepository.findAll()).willReturn(entityList);
        given(businessLocationI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<BusinessLocationI18nDto> result = businessLocationI18nService.getAllBusinessLocationI18ns();

        assertSame(dtoList, result);
        verify(businessLocationI18nRepository).findAll();
        verify(businessLocationI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetBusinessLocationI18nByIdIsCalled() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        BusinessLocationI18n businessLocationI18n = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18nDto businessLocationI18nDto = createSampleBusinessLocationI18nDto();

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.of(businessLocationI18n));
        given(businessLocationI18nMapper.toDTO(businessLocationI18n)).willReturn(businessLocationI18nDto);

        BusinessLocationI18nDto result = businessLocationI18nService.getBusinessLocationI18nById(businessLocationId, languageId);

        assertSame(businessLocationI18nDto, result);
        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nMapper).toDTO(businessLocationI18n);
    }

    @Test
    void shouldThrowWhenGetBusinessLocationI18nByIdCannotFindEntity() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationI18nService.getBusinessLocationI18nById(businessLocationId, languageId));

        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nMapper, never()).toDTO(any(BusinessLocationI18n.class));
    }

    @Test
    void shouldCreateBusinessLocationI18nWhenCreateBusinessLocationI18nIsCalled() {
        BusinessLocationI18nDto requestDto = createSampleBusinessLocationI18nDto();
        BusinessLocationI18n mappedEntity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18n savedEntity = createAnotherBusinessLocationI18nEntity();
        BusinessLocationI18nDto responseDto = createAnotherBusinessLocationI18nDto();

        given(businessLocationI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(businessLocationI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(businessLocationI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        BusinessLocationI18nDto result = businessLocationI18nService.createBusinessLocationI18n(requestDto);

        assertSame(responseDto, result);
        verify(businessLocationI18nMapper).toEntity(requestDto);
        verify(businessLocationI18nRepository).save(mappedEntity);
        verify(businessLocationI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateBusinessLocationI18nWhenEntityExists() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        BusinessLocationI18nDto requestDto = createPatchBusinessLocationI18nDto();
        BusinessLocationI18n existingEntity = createSampleBusinessLocationI18nEntity();
        BusinessLocationI18n savedEntity = createAnotherBusinessLocationI18nEntity();
        BusinessLocationI18nDto responseDto = createAnotherBusinessLocationI18nDto();

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(businessLocationI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(businessLocationI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        BusinessLocationI18nDto result = businessLocationI18nService.updateBusinessLocationI18n(businessLocationId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(businessLocationI18nRepository).save(existingEntity);
        verify(businessLocationI18nMapper).toDTO(savedEntity);
        verify(businessLocationI18nMapper, never()).toEntity(any(BusinessLocationI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateBusinessLocationI18nCannotFindEntity() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        BusinessLocationI18nDto requestDto = createPatchBusinessLocationI18nDto();

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationI18nService.updateBusinessLocationI18n(businessLocationId, languageId, requestDto));

        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nMapper, never()).partialUpdate(any(), any());
        verify(businessLocationI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteBusinessLocationI18nWhenEntityExists() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        BusinessLocationI18n existingEntity = createSampleBusinessLocationI18nEntity();

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        businessLocationI18nService.deleteBusinessLocationI18n(businessLocationId, languageId);

        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteBusinessLocationI18nCannotFindEntity() {
        UUID businessLocationId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationI18nKey id = new BusinessLocationI18nKey();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);

        given(businessLocationI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationI18nService.deleteBusinessLocationI18n(businessLocationId, languageId));

        verify(businessLocationI18nRepository).findById(id);
        verify(businessLocationI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated BusinessLocationI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocationI18n createSampleBusinessLocationI18nEntity() {
        BusinessLocationI18n entity = new BusinessLocationI18n();
        entity.setDescription("description-value-1");
        entity.setCode("code-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated BusinessLocationI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocationI18n createAnotherBusinessLocationI18nEntity() {
        BusinessLocationI18n entity = new BusinessLocationI18n();
        entity.setDescription("description-value-2");
        entity.setCode("code-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createSampleBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        dto.setDescription("description-value-1");
        dto.setCode("code-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createAnotherBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        dto.setDescription("description-value-2");
        dto.setCode("code-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated BusinessLocationI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationI18nDto createPatchBusinessLocationI18nDto() {
        BusinessLocationI18nDto dto = new BusinessLocationI18nDto();
        dto.setDescription("description-value-3");
        dto.setCode("code-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
