package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CorporateStatusi18nService;
import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CorporateStatusi18nController.class)
class CorporateStatusi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CorporateStatusi18nService corporateStatusi18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(corporateStatusi18nService.getAllCorporateStatusi18n()).willReturn(List.of());
        mockMvc.perform(get("/api/corporate-statusi18ns"))
                .andExpect(status().isOk());
    }
}
