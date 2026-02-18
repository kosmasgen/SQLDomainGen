package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.service.CourseStudentService;
import gr.knowledge.schoolmanagement.dto.CourseStudentDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseStudentController.class)
class CourseStudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseStudentService courseStudentService;

    @Test
    void shouldReturnOk() throws Exception {
        given(courseStudentService.getAllCourseStudent()).willReturn(List.of());
        mockMvc.perform(get("/api/course-students"))
                .andExpect(status().isOk());
    }
}
