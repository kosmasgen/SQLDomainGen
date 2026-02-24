package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.mapper.ProductMapper;
import gr.knowledge.pepTest.entity.Product;
import gr.knowledge.pepTest.repository.ProductRepository;
import gr.knowledge.pepTest.service.ProductService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
     * Retrieves all records.
     *
     * @return non-null list of {@link ProductDto}
     */
    @Override
    public List<ProductDto> getAllProduct() {
        log.info("Fetching all product.");
        return productMapper.toDTO(productRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProductDto}
     */
    @Override
    public ProductDto getProductById(UUID id) {
        log.info("Fetching product with id: {}", id);
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        return productMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProductDto}
     */
    @Override
    public ProductDto createProduct(ProductDto dto) {
        log.info("Creating product.");
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);
        return productMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProductDto}
     */
    @Override
    public ProductDto updateProduct(UUID id, ProductDto dto) {
        log.info("Updating product with id: {}", id);
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        Product entity = productMapper.toEntity(dto);
        entity.setId(id);
        Product saved = productRepository.save(entity);
        return productMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProduct(UUID id) {
        log.info("Deleting product with id: {}", id);
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        productRepository.deleteById(id);
    }
}
