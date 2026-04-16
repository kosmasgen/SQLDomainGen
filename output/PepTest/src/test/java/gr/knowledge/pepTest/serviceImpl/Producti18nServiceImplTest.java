package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Producti18n;
import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.repository.Producti18nRepository;
import gr.knowledge.pepTest.mapper.Producti18nMapper;
import gr.knowledge.pepTest.entity.Producti18nKey;
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
class Producti18nServiceImplTest {

    @Mock
    private Producti18nRepository producti18nRepository;

    @Mock
    private Producti18nMapper producti18nMapper;

    @InjectMocks
    private Producti18nServiceImpl producti18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Producti18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Producti18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProducti18nsIsCalled() {
        List<Producti18n> entityList = List.of(createSampleProducti18nEntity(), createAnotherProducti18nEntity());
        List<Producti18nDto> dtoList = List.of(createSampleProducti18nDto(), createAnotherProducti18nDto());

        given(producti18nRepository.findAll()).willReturn(entityList);
        given(producti18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<Producti18nDto> result = producti18nService.getAllProducti18ns();

        assertSame(dtoList, result);
        verify(producti18nRepository).findAll();
        verify(producti18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProducti18nByIdIsCalled() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        Producti18n producti18n = createSampleProducti18nEntity();
        Producti18nDto producti18nDto = createSampleProducti18nDto();

        given(producti18nRepository.findById(id)).willReturn(Optional.of(producti18n));
        given(producti18nMapper.toDTO(producti18n)).willReturn(producti18nDto);

        Producti18nDto result = producti18nService.getProducti18nById(languageId, productId);

        assertSame(producti18nDto, result);
        verify(producti18nRepository).findById(id);
        verify(producti18nMapper).toDTO(producti18n);
    }

    @Test
    void shouldThrowWhenGetProducti18nByIdCannotFindEntity() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        given(producti18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> producti18nService.getProducti18nById(languageId, productId));

        verify(producti18nRepository).findById(id);
        verify(producti18nMapper, never()).toDTO(any(Producti18n.class));
    }

    @Test
    void shouldCreateProducti18nWhenCreateProducti18nIsCalled() {
        Producti18nDto requestDto = createSampleProducti18nDto();
        Producti18n mappedEntity = createSampleProducti18nEntity();
        Producti18n savedEntity = createAnotherProducti18nEntity();
        Producti18nDto responseDto = createAnotherProducti18nDto();

        given(producti18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(producti18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(producti18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Producti18nDto result = producti18nService.createProducti18n(requestDto);

        assertSame(responseDto, result);
        verify(producti18nMapper).toEntity(requestDto);
        verify(producti18nRepository).save(mappedEntity);
        verify(producti18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProducti18nWhenEntityExists() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        Producti18nDto requestDto = createPatchProducti18nDto();
        Producti18n existingEntity = createSampleProducti18nEntity();
        Producti18n savedEntity = createAnotherProducti18nEntity();
        Producti18nDto responseDto = createAnotherProducti18nDto();

        given(producti18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(producti18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(producti18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Producti18nDto result = producti18nService.updateProducti18n(languageId, productId, requestDto);

        assertSame(responseDto, result);
        verify(producti18nRepository).findById(id);
        verify(producti18nMapper).partialUpdate(existingEntity, requestDto);
        verify(producti18nRepository).save(existingEntity);
        verify(producti18nMapper).toDTO(savedEntity);
        verify(producti18nMapper, never()).toEntity(any(Producti18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProducti18nCannotFindEntity() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        Producti18nDto requestDto = createPatchProducti18nDto();

        given(producti18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> producti18nService.updateProducti18n(languageId, productId, requestDto));

        verify(producti18nRepository).findById(id);
        verify(producti18nMapper, never()).partialUpdate(any(), any());
        verify(producti18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProducti18nWhenEntityExists() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        Producti18n existingEntity = createSampleProducti18nEntity();

        given(producti18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        producti18nService.deleteProducti18n(languageId, productId);

        verify(producti18nRepository).findById(id);
        verify(producti18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProducti18nCannotFindEntity() {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Producti18nKey id = new Producti18nKey();
        id.setLanguageId(languageId);
        id.setProductId(productId);

        given(producti18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> producti18nService.deleteProducti18n(languageId, productId));

        verify(producti18nRepository).findById(id);
        verify(producti18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Producti18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Producti18n createSampleProducti18nEntity() {
        Producti18n entity = new Producti18n();
        entity.setVersion(1);
        entity.setDescription("description-value-1");
        entity.setChamberI18nId(1L);
        entity.setShortDescription("shortDescription-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Producti18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Producti18n createAnotherProducti18nEntity() {
        Producti18n entity = new Producti18n();
        entity.setVersion(2);
        entity.setDescription("description-value-2");
        entity.setChamberI18nId(2L);
        entity.setShortDescription("shortDescription-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated Producti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createSampleProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        dto.setVersion(1);
        dto.setDescription("description-value-1");
        dto.setChamberI18nId(1L);
        dto.setShortDescription("shortDescription-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated Producti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createAnotherProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        dto.setVersion(2);
        dto.setDescription("description-value-2");
        dto.setChamberI18nId(2L);
        dto.setShortDescription("shortDescription-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated Producti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Producti18nDto createPatchProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        dto.setVersion(3);
        dto.setDescription("description-value-3");
        dto.setChamberI18nId(3L);
        dto.setShortDescription("shortDescription-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
