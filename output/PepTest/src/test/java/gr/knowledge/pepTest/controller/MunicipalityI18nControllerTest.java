package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.MunicipalityI18nService;
import gr.knowledge.pepTest.dto.MunicipalityI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MunicipalityI18nController.class)
class MunicipalityI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MunicipalityI18nService municipalityI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(municipalityI18nService.getAllMunicipalityI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/municipality-i18ns"))
                .andExpect(status().isOk());
    }
}
