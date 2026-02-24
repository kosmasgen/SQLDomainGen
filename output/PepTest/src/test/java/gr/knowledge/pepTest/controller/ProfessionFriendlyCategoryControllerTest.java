package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfessionFriendlyCategoryController.class)
class ProfessionFriendlyCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionFriendlyCategoryService professionFriendlyCategoryService;

    @Test
    void shouldReturnOk() throws Exception {
        given(professionFriendlyCategoryService.getAllProfessionFriendlyCategory()).willReturn(List.of());
        mockMvc.perform(get("/api/profession-friendly-categorys"))
                .andExpect(status().isOk());
    }
}
