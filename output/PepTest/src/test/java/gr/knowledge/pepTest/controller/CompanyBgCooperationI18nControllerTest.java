package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;
import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyBgCooperationI18nController.class)
class CompanyBgCooperationI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyBgCooperationI18nService companyBgCooperationI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyBgCooperationI18nService.getAllCompanyBgCooperationI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/company-bg-cooperation-i18ns"))
                .andExpect(status().isOk());
    }
}
