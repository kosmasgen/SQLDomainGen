package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.Professioni18nService;
import gr.knowledge.pepTest.dto.Professioni18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Professioni18nController.class)
class Professioni18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Professioni18nService professioni18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(professioni18nService.getAllProfessioni18n()).willReturn(List.of());
        mockMvc.perform(get("/api/professioni18ns"))
                .andExpect(status().isOk());
    }
}
