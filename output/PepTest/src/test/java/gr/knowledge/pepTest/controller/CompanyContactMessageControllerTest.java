package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyContactMessageService;
import gr.knowledge.pepTest.dto.CompanyContactMessageDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyContactMessageController.class)
class CompanyContactMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyContactMessageService companyContactMessageService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyContactMessageService.getAllCompanyContactMessage()).willReturn(List.of());
        mockMvc.perform(get("/api/company-contact-messages"))
                .andExpect(status().isOk());
    }
}
