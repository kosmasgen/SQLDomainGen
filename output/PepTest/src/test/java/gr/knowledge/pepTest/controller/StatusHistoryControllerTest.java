package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.StatusHistoryService;
import gr.knowledge.pepTest.dto.StatusHistoryDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusHistoryController.class)
class StatusHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusHistoryService statusHistoryService;

    @Test
    void shouldReturnOk() throws Exception {
        given(statusHistoryService.getAllStatusHistory()).willReturn(List.of());
        mockMvc.perform(get("/api/status-historys"))
                .andExpect(status().isOk());
    }
}
