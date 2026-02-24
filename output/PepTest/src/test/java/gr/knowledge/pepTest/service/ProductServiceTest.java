package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProductRepository;
import gr.knowledge.pepTest.mapper.ProductMapper;
import gr.knowledge.pepTest.serviceImpl.ProductServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(productRepository.findAll()).willReturn(List.of());
        productService.getAllProduct();
        verify(productRepository).findAll();
        verify(productMapper).toDTO(anyList());
    }
}
