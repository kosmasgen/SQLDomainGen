package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyProfileService;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyProfileController.class)
class CompanyProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyProfileService companyProfileService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyProfileService.getAllCompanyProfile()).willReturn(List.of());
        mockMvc.perform(get("/api/company-profiles"))
                .andExpect(status().isOk());
    }
}
