package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.mapper.ProductMapper;
import gr.knowledge.pepTest.entity.Product;
import gr.knowledge.pepTest.repository.ProductRepository;
import gr.knowledge.pepTest.service.ProductService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Product} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Retrieves all products records.
     * @return list of ProductDto
     */
    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Fetching all products records.");
        return productMapper.toDTOList(productRepository.findAll());
    }

    /**
     * Retrieves a product record by id.
     * @param id the product id
     * @return ProductDto
     */
    @Override
    public ProductDto getProductById(UUID id) {
        log.info("Fetching product with id: {}", id);

        Product existingEntity = findProductByIdOrThrow(id);
        return productMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new product record.
     * @param dto input payload
     * @return created {@link ProductDto}
     */
    @Override
    public ProductDto createProduct(ProductDto dto) {
        log.info("Creating product.");

        Product entity = productMapper.toEntity(dto);
        Product savedEntity = productRepository.save(entity);

        return productMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing product record.
     *
     * @param id the product id
     * @param dto input payload
     * @return updated {@link ProductDto}
     */
    @Override
    public ProductDto updateProduct(UUID id, ProductDto dto) {
        log.info("Updating product with id: {}", id);

        Product existingEntity = findProductByIdOrThrow(id);
        productMapper.partialUpdate(existingEntity, dto);
        Product savedEntity = productRepository.save(existingEntity);

        return productMapper.toDTO(savedEntity);
    }

    /**
     * Delete a product record by id.
     * @param id the product id
     */
    @Override
    public void deleteProduct(UUID id) {
        log.info("Deleting product with id: {}", id);

        findProductByIdOrThrow(id);
        productRepository.deleteById(id);
    }

    /**
     * Finds an existing product record by id or throws an exception.
     * @param id the product id
     * @return existing Product entity
     */
    private Product findProductByIdOrThrow(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> createProductNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the product entity.
     @param id the product id
     @return runtime exception
     */
    private RuntimeException createProductNotFoundException(UUID id) {
        log.warn("Product not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Product")
                .message("Product not found with id: " + id)
                .build();
    }

}
