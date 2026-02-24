package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.IncomeTransactionService;
import gr.knowledge.pepTest.dto.IncomeTransactionDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncomeTransactionController.class)
class IncomeTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncomeTransactionService incomeTransactionService;

    @Test
    void shouldReturnOk() throws Exception {
        given(incomeTransactionService.getAllIncomeTransaction()).willReturn(List.of());
        mockMvc.perform(get("/api/income-transactions"))
                .andExpect(status().isOk());
    }
}
