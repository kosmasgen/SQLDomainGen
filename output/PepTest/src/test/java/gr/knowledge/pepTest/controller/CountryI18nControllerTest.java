package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CountryI18nService;
import gr.knowledge.pepTest.dto.CountryI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryI18nController.class)
class CountryI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryI18nService countryI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(countryI18nService.getAllCountryI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/country-i18ns"))
                .andExpect(status().isOk());
    }
}
