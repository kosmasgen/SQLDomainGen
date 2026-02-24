package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.CountryService;
import gr.knowledge.pepTest.dto.CountryDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void shouldReturnOk() throws Exception {
        given(countryService.getAllCountry()).willReturn(List.of());
        mockMvc.perform(get("/api/countrys"))
                .andExpect(status().isOk());
    }
}
