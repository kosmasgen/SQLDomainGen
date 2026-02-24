package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.UserGeodataService;
import gr.knowledge.pepTest.dto.UserGeodataDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserGeodataController.class)
class UserGeodataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserGeodataService userGeodataService;

    @Test
    void shouldReturnOk() throws Exception {
        given(userGeodataService.getAllUserGeodata()).willReturn(List.of());
        mockMvc.perform(get("/api/user-geodatas"))
                .andExpect(status().isOk());
    }
}
