package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.service.DataStagingService;
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
@WebMvcTest(DataStagingController.class)
class DataStagingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DataStagingService dataStagingService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(dataStagingService.getAllDataStagings()).willReturn(List.of(new DataStagingDto()));

        mockMvc.perform(get("/api/data-staging"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(dataStagingService).getAllDataStagings();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        Long id = 1L;
        given(dataStagingService.getDataStagingById(id)).willReturn(new DataStagingDto());

        mockMvc.perform(get("/api/data-staging/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(dataStagingService).getDataStagingById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        Long id = 1L;
        given(dataStagingService.getDataStagingById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("DataStaging")
                        .message("DataStaging not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/data-staging/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        DataStagingDto requestDto = createValidCreateDataStagingDto();
        DataStagingDto responseDto = new DataStagingDto();
        given(dataStagingService.createDataStaging(any(DataStagingDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/data-staging")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(dataStagingService).createDataStaging(any(DataStagingDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        DataStagingDto requestDto = createValidCreateDataStagingDto();
        requestDto.setLegacyTableName(null);

        mockMvc.perform(post("/api/data-staging")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        DataStagingDto requestDto = createValidCreateDataStagingDto();
        given(dataStagingService.createDataStaging(any(DataStagingDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("DataStaging")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/data-staging")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        Long id = 1L;
        DataStagingDto requestDto = new DataStagingDto();
        DataStagingDto responseDto = new DataStagingDto();
        given(dataStagingService.updateDataStaging(eq(id), any(DataStagingDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/data-staging/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(dataStagingService).updateDataStaging(eq(id), any(DataStagingDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        Long id = 1L;
        DataStagingDto requestDto = new DataStagingDto();
        given(dataStagingService.updateDataStaging(eq(id), any(DataStagingDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("DataStaging")
                        .message("DataStaging not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/data-staging/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        Long id = 1L;
        willDoNothing().given(dataStagingService).deleteDataStaging(id);

        mockMvc.perform(delete("/api/data-staging/{id}", id))
                .andExpect(status().isNoContent());

        verify(dataStagingService).deleteDataStaging(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        Long id = 1L;
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("DataStaging")
                .message("DataStaging not found with id: " + id)
                .build())
                .given(dataStagingService).deleteDataStaging(id);

        mockMvc.perform(delete("/api/data-staging/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for DataStaging.
     *
     * @return populated create request dto
     */
    private DataStagingDto createValidCreateDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setLegacyTableName("aaaaa");
        dto.setLegacyRecordId("aaaaa");
        dto.setRawData("test");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setPulledAt(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setStatus("aaaaa");

        return dto;
    }

}
