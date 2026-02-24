package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CorporateStatusViewRulesController.class)
class CorporateStatusViewRulesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CorporateStatusViewRulesService corporateStatusViewRulesService;

    @Test
    void shouldReturnOk() throws Exception {
        given(corporateStatusViewRulesService.getAllCorporateStatusViewRules()).willReturn(List.of());
        mockMvc.perform(get("/api/corporate-status-view-ruless"))
                .andExpect(status().isOk());
    }
}
