package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.StatsExpenseService;
import gr.knowledge.pepTest.dto.StatsExpenseDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatsExpenseController.class)
class StatsExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsExpenseService statsExpenseService;

    @Test
    void shouldReturnOk() throws Exception {
        given(statsExpenseService.getAllStatsExpense()).willReturn(List.of());
        mockMvc.perform(get("/api/stats-expenses"))
                .andExpect(status().isOk());
    }
}
