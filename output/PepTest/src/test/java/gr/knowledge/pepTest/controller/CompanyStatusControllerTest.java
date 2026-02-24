package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyStatusService;
import gr.knowledge.pepTest.dto.CompanyStatusDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyStatusController.class)
class CompanyStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyStatusService companyStatusService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyStatusService.getAllCompanyStatus()).willReturn(List.of());
        mockMvc.perform(get("/api/company-statuss"))
                .andExpect(status().isOk());
    }
}
