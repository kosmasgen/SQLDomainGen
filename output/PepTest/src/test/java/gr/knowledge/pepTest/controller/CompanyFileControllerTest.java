package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyFileService;
import gr.knowledge.pepTest.dto.CompanyFileDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyFileController.class)
class CompanyFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyFileService companyFileService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyFileService.getAllCompanyFile()).willReturn(List.of());
        mockMvc.perform(get("/api/company-files"))
                .andExpect(status().isOk());
    }
}
