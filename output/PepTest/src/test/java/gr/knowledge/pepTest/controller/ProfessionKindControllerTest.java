package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ProfessionKindService;
import gr.knowledge.pepTest.dto.ProfessionKindDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfessionKindController.class)
class ProfessionKindControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionKindService professionKindService;

    @Test
    void shouldReturnOk() throws Exception {
        given(professionKindService.getAllProfessionKind()).willReturn(List.of());
        mockMvc.perform(get("/api/profession-kinds"))
                .andExpect(status().isOk());
    }
}
