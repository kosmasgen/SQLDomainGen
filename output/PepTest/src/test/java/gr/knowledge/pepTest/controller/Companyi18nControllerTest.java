package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.Companyi18nService;
import gr.knowledge.pepTest.dto.Companyi18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Companyi18nController.class)
class Companyi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Companyi18nService companyi18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyi18nService.getAllCompanyi18n()).willReturn(List.of());
        mockMvc.perform(get("/api/companyi18ns"))
                .andExpect(status().isOk());
    }
}
