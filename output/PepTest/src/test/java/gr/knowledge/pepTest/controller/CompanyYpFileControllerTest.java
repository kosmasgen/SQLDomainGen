package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CompanyYpFileService;
import gr.knowledge.pepTest.dto.CompanyYpFileDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyYpFileController.class)
class CompanyYpFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyYpFileService companyYpFileService;

    @Test
    void shouldReturnOk() throws Exception {
        given(companyYpFileService.getAllCompanyYpFile()).willReturn(List.of());
        mockMvc.perform(get("/api/company-yp-files"))
                .andExpect(status().isOk());
    }
}
