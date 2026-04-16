package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.service.SyncrunsService;
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
@WebMvcTest(SyncrunsController.class)
class SyncrunsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SyncrunsService syncrunsService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(syncrunsService.getAllSyncrunses()).willReturn(List.of(new SyncrunsDto()));

        mockMvc.perform(get("/api/syncruns"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncrunsService).getAllSyncrunses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        Long id = 1L;
        given(syncrunsService.getSyncrunsById(id)).willReturn(new SyncrunsDto());

        mockMvc.perform(get("/api/syncruns/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncrunsService).getSyncrunsById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        Long id = 1L;
        given(syncrunsService.getSyncrunsById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Syncruns")
                        .message("Syncruns not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/syncruns/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        SyncrunsDto requestDto = createValidCreateSyncrunsDto();
        SyncrunsDto responseDto = new SyncrunsDto();
        given(syncrunsService.createSyncruns(any(SyncrunsDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/syncruns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncrunsService).createSyncruns(any(SyncrunsDto.class));
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        SyncrunsDto requestDto = createValidCreateSyncrunsDto();
        given(syncrunsService.createSyncruns(any(SyncrunsDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Syncruns")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/syncruns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        Long id = 1L;
        SyncrunsDto requestDto = new SyncrunsDto();
        SyncrunsDto responseDto = new SyncrunsDto();
        given(syncrunsService.updateSyncruns(eq(id), any(SyncrunsDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/syncruns/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(syncrunsService).updateSyncruns(eq(id), any(SyncrunsDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        Long id = 1L;
        SyncrunsDto requestDto = new SyncrunsDto();
        given(syncrunsService.updateSyncruns(eq(id), any(SyncrunsDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Syncruns")
                        .message("Syncruns not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/syncruns/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        Long id = 1L;
        willDoNothing().given(syncrunsService).deleteSyncruns(id);

        mockMvc.perform(delete("/api/syncruns/{id}", id))
                .andExpect(status().isNoContent());

        verify(syncrunsService).deleteSyncruns(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        Long id = 1L;
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Syncruns")
                .message("Syncruns not found with id: " + id)
                .build())
                .given(syncrunsService).deleteSyncruns(id);

        mockMvc.perform(delete("/api/syncruns/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Syncruns.
     *
     * @return populated create request dto
     */
    private SyncrunsDto createValidCreateSyncrunsDto() {
        return new SyncrunsDto();
    }

}
