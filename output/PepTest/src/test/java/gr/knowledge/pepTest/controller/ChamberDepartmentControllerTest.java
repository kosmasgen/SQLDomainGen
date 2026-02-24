package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ChamberDepartmentService;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChamberDepartmentController.class)
class ChamberDepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChamberDepartmentService chamberDepartmentService;

    @Test
    void shouldReturnOk() throws Exception {
        given(chamberDepartmentService.getAllChamberDepartment()).willReturn(List.of());
        mockMvc.perform(get("/api/chamber-departments"))
                .andExpect(status().isOk());
    }
}
