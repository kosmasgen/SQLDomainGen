package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.DataStagingService;
import gr.knowledge.pepTest.dto.DataStagingDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataStagingController.class)
class DataStagingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataStagingService dataStagingService;

    @Test
    void shouldReturnOk() throws Exception {
        given(dataStagingService.getAllDataStaging()).willReturn(List.of());
        mockMvc.perform(get("/api/data-stagings"))
                .andExpect(status().isOk());
    }
}
