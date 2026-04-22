package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.service.SyncWatermarksService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(SyncWatermarksController.class)
class SyncWatermarksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SyncWatermarksService syncWatermarksService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(syncWatermarksService.getAllSyncWatermarkses()).willReturn(List.of(new SyncWatermarksDto()));

        mockMvc.perform(get("/api/sync-watermarks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncWatermarksService).getAllSyncWatermarkses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        Long id = 1L;
        given(syncWatermarksService.getSyncWatermarksById(id)).willReturn(new SyncWatermarksDto());

        mockMvc.perform(get("/api/sync-watermarks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncWatermarksService).getSyncWatermarksById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        Long id = 1L;
        given(syncWatermarksService.getSyncWatermarksById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("SyncWatermarks")
                        .message("SyncWatermarks not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/sync-watermarks/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        SyncWatermarksDto responseDto = new SyncWatermarksDto();
        given(syncWatermarksService.createSyncWatermarks(any(SyncWatermarksDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/sync-watermarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncWatermarksService).createSyncWatermarks(any(SyncWatermarksDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        requestDto.setTableName(null);

        mockMvc.perform(post("/api/sync-watermarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        given(syncWatermarksService.createSyncWatermarks(any(SyncWatermarksDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("SyncWatermarks")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/sync-watermarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        Long id = 1L;
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        requestDto.setTableName(null);

        mockMvc.perform(patch("/api/sync-watermarks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        Long id = 1L;
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        SyncWatermarksDto responseDto = new SyncWatermarksDto();
        given(syncWatermarksService.updateSyncWatermarks(eq(id), any(SyncWatermarksDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/sync-watermarks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncWatermarksService).updateSyncWatermarks(eq(id), any(SyncWatermarksDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        Long id = 1L;
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        given(syncWatermarksService.updateSyncWatermarks(eq(id), any(SyncWatermarksDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("SyncWatermarks")
                        .message("SyncWatermarks not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/sync-watermarks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        Long id = 1L;
        SyncWatermarksDto requestDto = createValidCreateSyncWatermarksDto();
        given(syncWatermarksService.updateSyncWatermarks(eq(id), any(SyncWatermarksDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/sync-watermarks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        Long id = 1L;
        willDoNothing().given(syncWatermarksService).deleteSyncWatermarks(id);

        mockMvc.perform(delete("/api/sync-watermarks/{id}", id))
                .andExpect(status().isNoContent());

        verify(syncWatermarksService).deleteSyncWatermarks(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        Long id = 1L;
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("SyncWatermarks")
                .message("SyncWatermarks not found with id: " + id)
                .build())
                .given(syncWatermarksService).deleteSyncWatermarks(id);

        mockMvc.perform(delete("/api/sync-watermarks/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for SyncWatermarks.
     *
     * @return populated create request dto
     */
    private SyncWatermarksDto createValidCreateSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setTableName("A");
        dto.setLastSyncTimestamp(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setUpdatedAt(LocalDateTime.of(2025, 1, 11, 10, 0));

        return dto;
    }

}
