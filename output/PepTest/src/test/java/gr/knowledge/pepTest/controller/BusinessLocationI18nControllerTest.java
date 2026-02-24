package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.BusinessLocationI18nService;
import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusinessLocationI18nController.class)
class BusinessLocationI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusinessLocationI18nService businessLocationI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(businessLocationI18nService.getAllBusinessLocationI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/business-location-i18ns"))
                .andExpect(status().isOk());
    }
}
