package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.BgPoiI18nService;
import gr.knowledge.pepTest.dto.BgPoiI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BgPoiI18nController.class)
class BgPoiI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BgPoiI18nService bgPoiI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(bgPoiI18nService.getAllBgPoiI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/bg-poi-i18ns"))
                .andExpect(status().isOk());
    }
}
