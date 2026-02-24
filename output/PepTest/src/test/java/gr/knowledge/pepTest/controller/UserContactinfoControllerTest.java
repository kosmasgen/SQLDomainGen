package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.UserContactinfoService;
import gr.knowledge.pepTest.dto.UserContactinfoDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserContactinfoController.class)
class UserContactinfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserContactinfoService userContactinfoService;

    @Test
    void shouldReturnOk() throws Exception {
        given(userContactinfoService.getAllUserContactinfo()).willReturn(List.of());
        mockMvc.perform(get("/api/user-contactinfos"))
                .andExpect(status().isOk());
    }
}
