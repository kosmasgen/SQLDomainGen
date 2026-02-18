package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.service.StudentService;
import gr.knowledge.schoolmanagement.dto.StudentDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void shouldReturnOk() throws Exception {
        given(studentService.getAllStudent()).willReturn(List.of());
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }
}
