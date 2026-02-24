package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyStatusViewRulesController.class)
class CompanyStatusViewRulesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyStatusViewRulesService companyStatusViewRulesService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyStatusViewRulesService.getAllCompanyStatusViewRules()).willReturn(List.of());
        mockMvc.perform(get("/api/company-status-view-ruless"))
                .andExpect(status().isOk());
    }
}
