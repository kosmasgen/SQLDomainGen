package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.service.TeacherService;
import gr.knowledge.schoolmanagement.dto.TeacherDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void shouldReturnOk() throws Exception {
        given(teacherService.getAllTeacher()).willReturn(List.of());
        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk());
    }
}
