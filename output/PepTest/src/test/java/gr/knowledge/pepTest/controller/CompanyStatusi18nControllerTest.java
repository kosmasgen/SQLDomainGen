package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyStatusi18nService;
import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyStatusi18nController.class)
class CompanyStatusi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyStatusi18nService companyStatusi18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyStatusi18nService.getAllCompanyStatusi18n()).willReturn(List.of());
        mockMvc.perform(get("/api/company-statusi18ns"))
                .andExpect(status().isOk());
    }
}
