package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyTitlei18nService;
import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyTitlei18nController.class)
class CompanyTitlei18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyTitlei18nService companyTitlei18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyTitlei18nService.getAllCompanyTitlei18n()).willReturn(List.of());
        mockMvc.perform(get("/api/company-titlei18ns"))
                .andExpect(status().isOk());
    }
}
