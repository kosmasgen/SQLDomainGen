package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyProfileI18nService;
import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyProfileI18nController.class)
class CompanyProfileI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyProfileI18nService companyProfileI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyProfileI18nService.getAllCompanyProfileI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/company-profile-i18ns"))
                .andExpect(status().isOk());
    }
}
