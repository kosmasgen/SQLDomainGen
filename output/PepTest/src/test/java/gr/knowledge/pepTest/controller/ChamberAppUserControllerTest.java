package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ChamberAppUserService;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChamberAppUserController.class)
class ChamberAppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChamberAppUserService chamberAppUserService;

    @Test
    void shouldReturnOk() throws Exception {
        given(chamberAppUserService.getAllChamberAppUser()).willReturn(List.of());
        mockMvc.perform(get("/api/chamber-app-users"))
                .andExpect(status().isOk());
    }
}
