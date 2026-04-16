package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Product;
import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.repository.ProductRepository;
import gr.knowledge.pepTest.mapper.ProductMapper;
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
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Product.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Product", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProductsIsCalled() {
        List<Product> entityList = List.of(createSampleProductEntity(), createAnotherProductEntity());
        List<ProductDto> dtoList = List.of(createSampleProductDto(), createAnotherProductDto());

        given(productRepository.findAll()).willReturn(entityList);
        given(productMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProductDto> result = productService.getAllProducts();

        assertSame(dtoList, result);
        verify(productRepository).findAll();
        verify(productMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProductByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Product product = createSampleProductEntity();
        ProductDto productDto = createSampleProductDto();

        given(productRepository.findById(id)).willReturn(Optional.of(product));
        given(productMapper.toDTO(product)).willReturn(productDto);

        ProductDto result = productService.getProductById(id);

        assertSame(productDto, result);
        verify(productRepository).findById(id);
        verify(productMapper).toDTO(product);
    }

    @Test
    void shouldThrowWhenGetProductByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(productRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> productService.getProductById(id));

        verify(productRepository).findById(id);
        verify(productMapper, never()).toDTO(any(Product.class));
    }

    @Test
    void shouldCreateProductWhenCreateProductIsCalled() {
        ProductDto requestDto = createSampleProductDto();
        Product mappedEntity = createSampleProductEntity();
        Product savedEntity = createAnotherProductEntity();
        ProductDto responseDto = createAnotherProductDto();

        given(productMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(productRepository.save(mappedEntity)).willReturn(savedEntity);
        given(productMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProductDto result = productService.createProduct(requestDto);

        assertSame(responseDto, result);
        verify(productMapper).toEntity(requestDto);
        verify(productRepository).save(mappedEntity);
        verify(productMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProductWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProductDto requestDto = createPatchProductDto();
        Product existingEntity = createSampleProductEntity();
        Product savedEntity = createAnotherProductEntity();
        ProductDto responseDto = createAnotherProductDto();

        given(productRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(productRepository.save(existingEntity)).willReturn(savedEntity);
        given(productMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProductDto result = productService.updateProduct(id, requestDto);

        assertSame(responseDto, result);
        verify(productRepository).findById(id);
        verify(productMapper).partialUpdate(existingEntity, requestDto);
        verify(productRepository).save(existingEntity);
        verify(productMapper).toDTO(savedEntity);
        verify(productMapper, never()).toEntity(any(ProductDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProductCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProductDto requestDto = createPatchProductDto();

        given(productRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> productService.updateProduct(id, requestDto));

        verify(productRepository).findById(id);
        verify(productMapper, never()).partialUpdate(any(), any());
        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProductWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Product existingEntity = createSampleProductEntity();

        given(productRepository.findById(id)).willReturn(Optional.of(existingEntity));

        productService.deleteProduct(id);

        verify(productRepository).findById(id);
        verify(productRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProductCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(productRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> productService.deleteProduct(id));

        verify(productRepository).findById(id);
        verify(productRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Product fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Product createSampleProductEntity() {
        Product entity = new Product();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberProductId(1L);
        entity.setVersion(1);
        entity.setCd("cd-value-1");
        entity.setCdGemi("cdGemi-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setParentProductId(1L);
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Product fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Product createAnotherProductEntity() {
        Product entity = new Product();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberProductId(2L);
        entity.setVersion(2);
        entity.setCd("cd-value-2");
        entity.setCdGemi("cdGemi-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setParentProductId(2L);
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProductDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createSampleProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberProductId(1L);
        dto.setVersion(1);
        dto.setCd("cd-value-1");
        dto.setCdGemi("cdGemi-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setParentProductId(1L);
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProductDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createAnotherProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberProductId(2L);
        dto.setVersion(2);
        dto.setCd("cd-value-2");
        dto.setCdGemi("cdGemi-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setParentProductId(2L);
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProductDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProductDto createPatchProductDto() {
        ProductDto dto = new ProductDto();
        dto.setChamberId(3);
        dto.setChamberProductId(3L);
        dto.setVersion(3);
        dto.setCd("cd-value-3");
        dto.setCdGemi("cdGemi-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setParentProductId(3L);
        dto.setRecdeleted(true);

        return dto;
    }

}
