package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChAppUserContactI18nController.class)
class ChAppUserContactI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChAppUserContactI18nService chAppUserContactI18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(chAppUserContactI18nService.getAllChAppUserContactI18n()).willReturn(List.of());
        mockMvc.perform(get("/api/ch-app-user-contact-i18ns"))
                .andExpect(status().isOk());
    }
}
