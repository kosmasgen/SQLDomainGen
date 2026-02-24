package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.IncomeGemiPaymentService;
import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncomeGemiPaymentController.class)
class IncomeGemiPaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncomeGemiPaymentService incomeGemiPaymentService;

    @Test
    void shouldReturnOk() throws Exception {
        given(incomeGemiPaymentService.getAllIncomeGemiPayment()).willReturn(List.of());
        mockMvc.perform(get("/api/income-gemi-payments"))
                .andExpect(status().isOk());
    }
}
