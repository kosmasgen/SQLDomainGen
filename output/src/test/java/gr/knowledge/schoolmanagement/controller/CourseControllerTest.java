package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.service.CourseService;
import gr.knowledge.schoolmanagement.dto.CourseDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    void shouldReturnOk() throws Exception {
        given(courseService.getAllCourse()).willReturn(List.of());
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk());
    }
}
