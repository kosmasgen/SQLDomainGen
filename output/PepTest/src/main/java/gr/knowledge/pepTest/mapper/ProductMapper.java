package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Product;
import gr.knowledge.pepTest.dto.ProductDto;

/**
 * Mapper for {@link Product} and {@link ProductDto}.
 */
@Component
public class ProductMapper extends BaseMapper<Product, ProductDto> {

    public ProductMapper(ModelMapper modelMapper) {
        super(modelMapper, Product.class, ProductDto.class);
    }
}
