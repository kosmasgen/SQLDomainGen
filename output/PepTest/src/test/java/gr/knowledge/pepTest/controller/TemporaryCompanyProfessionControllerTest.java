package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;
import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemporaryCompanyProfessionController.class)
class TemporaryCompanyProfessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TemporaryCompanyProfessionService temporaryCompanyProfessionService;

    @Test
    void shouldReturnOk() throws Exception {
        given(temporaryCompanyProfessionService.getAllTemporaryCompanyProfession()).willReturn(List.of());
        mockMvc.perform(get("/api/temporary-company-professions"))
                .andExpect(status().isOk());
    }
}
