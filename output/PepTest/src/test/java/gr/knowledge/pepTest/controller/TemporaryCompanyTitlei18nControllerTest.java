package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemporaryCompanyTitlei18nController.class)
class TemporaryCompanyTitlei18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TemporaryCompanyTitlei18nService temporaryCompanyTitlei18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18n()).willReturn(List.of());
        mockMvc.perform(get("/api/temporary-company-titlei18ns"))
                .andExpect(status().isOk());
    }
}
