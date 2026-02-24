package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyYpArticleService;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyYpArticleController.class)
class CompanyYpArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyYpArticleService companyYpArticleService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyYpArticleService.getAllCompanyYpArticle()).willReturn(List.of());
        mockMvc.perform(get("/api/company-yp-articles"))
                .andExpect(status().isOk());
    }
}
