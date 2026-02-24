package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyProfessionService;
import gr.knowledge.pepTest.dto.CompanyProfessionDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyProfessionController.class)
class CompanyProfessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyProfessionService companyProfessionService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyProfessionService.getAllCompanyProfession()).willReturn(List.of());
        mockMvc.perform(get("/api/company-professions"))
                .andExpect(status().isOk());
    }
}
