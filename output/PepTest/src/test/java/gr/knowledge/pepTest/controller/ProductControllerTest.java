package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ProductService;
import gr.knowledge.pepTest.dto.ProductDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnOk() throws Exception {
        given(productService.getAllProduct()).willReturn(List.of());
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }
}
