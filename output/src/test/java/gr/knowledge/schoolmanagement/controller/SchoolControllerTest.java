package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.service.SchoolService;
import gr.knowledge.schoolmanagement.dto.SchoolDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Test
    void shouldReturnOk() throws Exception {
        given(schoolService.getAllSchool()).willReturn(List.of());
        mockMvc.perform(get("/api/schools"))
                .andExpect(status().isOk());
    }
}
