package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.SyncrunsService;
import gr.knowledge.pepTest.dto.SyncrunsDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SyncrunsController.class)
class SyncrunsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SyncrunsService syncrunsService;

    @Test
    void shouldReturnOk() throws Exception {
        given(syncrunsService.getAllSyncruns()).willReturn(List.of());
        mockMvc.perform(get("/api/syncrunss"))
                .andExpect(status().isOk());
    }
}
