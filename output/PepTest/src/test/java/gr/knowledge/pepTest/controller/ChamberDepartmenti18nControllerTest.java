package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChamberDepartmenti18nController.class)
class ChamberDepartmenti18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChamberDepartmenti18nService chamberDepartmenti18nService;

    @Test
    void shouldReturnOk() throws Exception {
        given(chamberDepartmenti18nService.getAllChamberDepartmenti18n()).willReturn(List.of());
        mockMvc.perform(get("/api/chamber-departmenti18ns"))
                .andExpect(status().isOk());
    }
}
