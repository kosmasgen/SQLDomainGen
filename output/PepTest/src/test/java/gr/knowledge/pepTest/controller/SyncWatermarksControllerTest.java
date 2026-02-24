package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.service.SyncWatermarksService;
import gr.knowledge.pepTest.dto.SyncWatermarksDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SyncWatermarksController.class)
class SyncWatermarksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SyncWatermarksService syncWatermarksService;

    @Test
    void shouldReturnOk() throws Exception {
        given(syncWatermarksService.getAllSyncWatermarks()).willReturn(List.of());
        mockMvc.perform(get("/api/sync-watermarkss"))
                .andExpect(status().isOk());
    }
}
