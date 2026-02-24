package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ChAppUserContactService;
import gr.knowledge.pepTest.dto.ChAppUserContactDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChAppUserContactController.class)
class ChAppUserContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChAppUserContactService chAppUserContactService;

    @Test
    void shouldReturnOk() throws Exception {
        given(chAppUserContactService.getAllChAppUserContact()).willReturn(List.of());
        mockMvc.perform(get("/api/ch-app-user-contacts"))
                .andExpect(status().isOk());
    }
}
